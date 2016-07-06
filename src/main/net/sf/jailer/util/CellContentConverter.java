/*
 * Copyright 2007 - 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.sf.jailer.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.sf.jailer.Configuration;
import net.sf.jailer.database.DBMS;
import net.sf.jailer.database.Session;

/**
 * Converts a cell-content to valid SQL-literal.
 * 
 * @author Ralf Wisser
 */
public class CellContentConverter {
    
    /**
     * Default time stamp format (for 'to_timestamp' function).
     */
    private static final DateFormat defaultTimestampFormat = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");

    /**
     * Default time stamp format (for 'to_date' function).
     */
    private static final DateFormat defaultDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
	 * All hex digits.
	 */
	private static final char[] hexChar = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	
	private final ResultSetMetaData resultSetMetaData;
	private final Map<Integer, Integer> typeCache = new HashMap<Integer, Integer>();
	private final Map<String, Integer> columnIndex = new HashMap<String, Integer>();
	private final Session session;
	private final Configuration configuration;
	
	/**
	 * Constructor.
	 * 
	 * @param resultSetMetaData meta data of the result set to read from
	 * @param session database session
	 */
	public CellContentConverter(ResultSetMetaData resultSetMetaData, Session session) {
		this.resultSetMetaData = resultSetMetaData;
		this.session = session;
		this.configuration = Configuration.forDbms(session);
	}

    /**
     * Converts a cell-content to valid SQL-literal.
     * 
     * @param object the content
     * @return the SQL-literal
     */
    public String toSql(Object content) {
        if (content == null) {
            return "null";
        }

        if (content instanceof java.sql.Date) {
        	if (configuration.useToTimestampFunction) {
        		String format;
        		synchronized(defaultDateFormat) {
	        		format = defaultDateFormat.format((Date) content);
	       		}
				return "to_date('" + format + "', 'YYYY-MM-DD')";
        	}
        	if (configuration.dateFormat != null) {
        		synchronized(configuration.dateFormat) {
        			return "'" + configuration.dateFormat.format((Date) content) + "'";
        		}
        	}
            return "'" + content + "'";
        }
        if (content instanceof java.sql.Timestamp) {
        	if (configuration.useToTimestampFunction) {
        		String format;
        		String nanoFormat;
        		synchronized(defaultTimestampFormat) {
	        		format = defaultTimestampFormat.format((Date) content);
	        		String nanoString = getNanoString((Timestamp) content, configuration.appendNanosToTimestamp, configuration.nanoSep);
	        		nanoFormat = "FF" + (nanoString.length() - 1);
	    			format += nanoString;
        		}
				return "to_timestamp('" + format + "', 'YYYY-MM-DD HH24.MI.SS." + nanoFormat + "')";
        	} else if (configuration.timestampFormat != null) {
        		String format;
        		synchronized(configuration.timestampFormat) {
	        		format = configuration.timestampFormat.format((Date) content);
	        		if (configuration.appendMillisToTimestamp) {
	        			format += getNanoString((Timestamp) content, configuration.appendNanosToTimestamp, configuration.nanoSep);
	        		}
        		}
				content = format;
        	}
        	if (configuration.timestampPattern != null) {
        		return configuration.timestampPattern.replace("%s", "'" + content + "'");
        	}
            return "'" + content + "'";
        }
        if (content instanceof NCharWrapper) {
        	String prefix = Configuration.forDbms(session).getNcharPrefix();
        	if (prefix == null) {
        		prefix = "";
        	}
			return prefix + "'" + Configuration.forDbms(session).convertToStringLiteral(content.toString()) + "'";
        }
        if (content instanceof String) {
            return "'" + Configuration.forDbms(session).convertToStringLiteral((String) content) + "'";
        }
        if (content instanceof HStoreWrapper) {
            return "'" + Configuration.forDbms(session).convertToStringLiteral(content.toString()) + "'::hstore";
        }
        if (content instanceof byte[]) {
        	byte[] data = (byte[]) content;
        	StringBuilder hex = new StringBuilder((data.length + 1) * 2);
        	for (byte b: data) {
        		hex.append(hexChar[(b >> 4) & 15]);
        		hex.append(hexChar[b & 15]);
        	}
        	return configuration.binaryPattern.replace("%s", hex);
        }
        if (content instanceof Time) {
        	return "'" + content + "'";
        }
        if (SqlUtil.dbms == DBMS.POSTGRESQL) {
        	if (content.getClass().getName().endsWith(".PGobject")) {
        		// PostgreSQL bit values
        		return "B'" + content + "'";
        	}
        }
        if (content instanceof UUID) {
        	if (SqlUtil.dbms == DBMS.POSTGRESQL) {
        		return "'" + content + "'::uuid";
        	}
        	return "'" + content + "'";
        }
        if (Configuration.forDbms(session).isIdentityInserts()) {
        	// Boolean mapping for MSSQL/Sybase
        	if (content instanceof Boolean) {
        		content = Boolean.TRUE.equals(content)? "1" : "0";
        	}
        }
        return content.toString();
    }
    
    /**
     * Gets nano string suffix of a timestamp.
     * 
     * @param timestamp the timestamp
     * @param nanoSep 
     */
    private static String getNanoString(Timestamp timestamp, boolean full, char nanoSep) {
    	String zeros = "000000000";
    	int nanos = timestamp.getNanos();
    	String nanosString = Integer.toString(nanos);
    	
    	// Add leading zeros
    	nanosString = zeros.substring(0, (9-nanosString.length())) + nanosString;
    	
    	// Truncate trailing zeros
    	char[] nanosChar = new char[nanosString.length()];
    	nanosString.getChars(0, nanosString.length(), nanosChar, 0);
    	int truncIndex = 8;
    	while (truncIndex > 0 && nanosChar[truncIndex] == '0') {
    		truncIndex--;
    	}
    
    	nanosString = nanoSep + new String(nanosChar, 0, truncIndex + 1);
    	
    	if (!full) {
    		if (nanosString.length() > 4) {
    			return nanosString.substring(0, 4);
    		}
    	}
    	return nanosString;
    }
    
    private static final int TYPE_HSTORE = 10500;
    
    static class HStoreWrapper {
        private final String value;
        public HStoreWrapper(String value) {
            this.value = value;
        }
        public String toString() {
            return value;
        }
    }

    static class NCharWrapper {
        private final String value;
        public NCharWrapper(String value) {
            this.value = value;
        }
        public String toString() {
            return value;
        }
    }

    /**
     * Gets object from result-set.
     * 
     * @param resultSet result-set
     * @param i column index
     * @return object
     */
	public Object getObject(ResultSet resultSet, int i) throws SQLException {
		Integer type = typeCache.get(i);
		if (type == null) {
			try {
				type = resultSetMetaData.getColumnType(i);
				if (SqlUtil.dbms == DBMS.ORACLE) {
					if (type == Types.DATE) {
						type = Types.TIMESTAMP;
					}
				 }
				 if (SqlUtil.dbms == DBMS.POSTGRESQL) {
	                String typeName = resultSetMetaData.getColumnTypeName(i);
	                if ("hstore".equalsIgnoreCase(typeName)) {
	                    type = TYPE_HSTORE;
	                }
	             }
				 // workaround for JDTS bug
				 if (type == Types.VARCHAR) {
					 if ("nvarchar".equalsIgnoreCase(resultSetMetaData.getColumnTypeName(i))) {
						 type = Types.NVARCHAR;
					 }
				 }
				 if (type == Types.CHAR) {
					 if ("nchar".equalsIgnoreCase(resultSetMetaData.getColumnTypeName(i))) {
						 type = Types.NCHAR;
					 }
				 }
				 if (type == Types.OTHER) {
					 if ("rowid".equalsIgnoreCase(resultSetMetaData.getColumnTypeName(i))) {
						 type = Types.ROWID;
					 }
				 }
			} catch (Exception e) {
				type = Types.OTHER;
			}
			typeCache.put(i, type);
		}
		try {
			if (type == Types.ROWID) {
				return resultSet.getString(i);
			}
			if (type == Types.ARRAY) {
				return resultSet.getString(i);
			}
			if (type == Types.TIMESTAMP) {
				return resultSet.getTimestamp(i);
			}
			if (type == Types.DATE) {
				if (SqlUtil.dbms == DBMS.MySQL) {
					// YEAR
					String typeName = resultSetMetaData.getColumnTypeName(i);
					if (typeName != null && typeName.toUpperCase().equals("YEAR")) {
						int result = resultSet.getInt(i);
						if (resultSet.wasNull()) {
							return null;
						}
						return result;
					}
				}
				Date date = resultSet.getDate(i);
				return date;
			}
		} catch (SQLException e) {
			return resultSet.getString(i);
		}
		Object object = resultSet.getObject(i);
		if (type == Types.NCHAR || type == Types.NVARCHAR) {
			if (object instanceof String) {
				object = new NCharWrapper((String) object);
			}
		}
		if (SqlUtil.dbms == DBMS.POSTGRESQL) {
			if (type == TYPE_HSTORE) {
				return new HStoreWrapper(resultSet.getString(i));
            } else if (object instanceof Boolean) {
				String typeName = resultSetMetaData.getColumnTypeName(i);
				if (typeName != null && typeName.toLowerCase().equals("bit")) {
					final String value = Boolean.TRUE.equals(object)? "B'1'" : "B'0'";
					return new Object() {
						public String toString() {
							return value;
						}
					};
				}
			}
		}
		return object;
	};
	
    /**
     * Gets object from result-set.
     * 
     * @param resultSet result-set
     * @param columnName column name
     * @param typeCache for caching types
     * @return object
     */
	public Object getObject(ResultSet resultSet, String columnName) throws SQLException {
		Integer index = columnIndex.get(columnName);
		if (index == null) {
			for (int i = resultSetMetaData.getColumnCount(); i > 0; --i) {
				if (columnName.equalsIgnoreCase(resultSetMetaData.getColumnName(i))) {
					index = i;
					break;
				}
			}
			columnIndex.put(columnName, index);
		}
		return getObject(resultSet, index);
	}

}