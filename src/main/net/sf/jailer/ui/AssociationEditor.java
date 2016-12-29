/*
 * Copyright 2007 - 2017 the original author or authors.
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
package net.sf.jailer.ui;

import java.awt.Component;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;

import net.sf.jailer.datamodel.Association;
import net.sf.jailer.util.CsvFile.Line;

/**
 * Editor for single tables. Part of {@link DataModelEditor}.
 *
 * @author Ralf Wisser
 */
public class AssociationEditor extends javax.swing.JDialog {
    
	/**
	 * All tables (as csv-lines).
	 */
	private Collection<Line> tables;

	/**
	 * All associations (as csv-lines).
	 */
	private Collection<Line> associations;
	
    /** 
     * Creates new form TableEditor
     * 
     * @param tables all tables (as csv-lines)
     * @param associations all associations (as csv-line)
     */
    public AssociationEditor(java.awt.Dialog parent, Collection<Line> tables, List<Line> associations) {
        super(parent, true);
        this.tables = tables;
        this.associations = associations;
        initComponents();
        type.setRenderer(createTypeRenderer());
        pack();
        setLocation(parent.getLocation().x + parent.getSize().width/2 - getPreferredSize().width/2,
    			parent.getLocation().y + parent.getSize().height/2 - getPreferredSize().height/2);
        UIUtil.initPeer();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Erzeugter Quelltext ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel2 = new javax.swing.JPanel();
        source = new net.sf.jailer.ui.JComboBox();
        type = new net.sf.jailer.ui.JComboBox();
        destination = new net.sf.jailer.ui.JComboBox();
        cardinality = new net.sf.jailer.ui.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        joinCondition = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        getContentPane().setLayout(new java.awt.GridBagLayout());

        setTitle("Association");
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        getContentPane().add(jPanel2, gridBagConstraints);

        source.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        source.setMaximumRowCount(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        getContentPane().add(source, gridBagConstraints);

        type.setModel(createTypeModel());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        getContentPane().add(type, gridBagConstraints);

        destination.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        destination.setMaximumRowCount(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        getContentPane().add(destination, gridBagConstraints);

        cardinality.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "1:n", "n:1", "1:1", "n:m" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        getContentPane().add(cardinality, gridBagConstraints);

        jLabel1.setText(" Name ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        getContentPane().add(jLabel1, gridBagConstraints);

        jLabel2.setText(" Join condition* ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        getContentPane().add(jLabel2, gridBagConstraints);

        joinCondition.setText("jTextField2");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(joinCondition, gridBagConstraints);

        jLabel5.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        getContentPane().add(jLabel5, gridBagConstraints);

        jLabel6.setText(" From ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        getContentPane().add(jLabel6, gridBagConstraints);

        jLabel7.setText(" To");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        getContentPane().add(jLabel7, gridBagConstraints);

        jLabel8.setText(" Type");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        getContentPane().add(jLabel8, gridBagConstraints);

        nameField.setText("jTextField1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(nameField, gridBagConstraints);

        jLabel9.setText(" Cardinality ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        getContentPane().add(jLabel9, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jButton1.setText("Ok");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 0);
        jPanel1.add(jButton1, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 12));
        jLabel3.setText(" *use 'A' as alias for From, use 'B' as alias for To.           ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 0, 0);
        jPanel1.add(jLabel3, gridBagConstraints);

        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jButton2.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jButton2ComponentResized(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 0);
        jPanel1.add(jButton2, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 12));
        jLabel4.setText("  Upper case, no space between A/B and dot!");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(jLabel4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 40;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(jPanel1, gridBagConstraints);

        jLabel10.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 20;
        gridBagConstraints.gridy = 0;
        getContentPane().add(jLabel10, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    	setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * On OK.
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    	String msg = null;
    	if (nameField.getText().trim().length() == 0) {
    		msg = "No association name";
    	} else {
    		for (Line l: associations) {
    			if (l != currentAssociation && l.cells.get(5).equalsIgnoreCase(nameField.getText().trim())) {
    				msg = "Association with same name exists";
    				break;
    			}
    		}
    	}
    	if (joinCondition.getText().trim().length() == 0) {
    		msg = "No join condition";
    	}if (msg != null) {
    		JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    	} else {
    		isOk = true;
    		setVisible(false);
    	}
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jButton2ComponentResized
    }//GEN-LAST:event_jButton2ComponentResized

    /**
     * Creates model for {@link AssociationEditor#type}.
     * 
     * @return model for {@link AssociationEditor#type}
     */
    private ComboBoxModel createTypeModel() {
    	DefaultComboBoxModel model = new DefaultComboBoxModel();
    	model.addElement("");
    	model.addElement("A");
    	model.addElement("B");
    	return model;
    }

    /**
     * Creates renderer for {@link AssociationEditor#type}.
     * 
     * @return renderer for {@link AssociationEditor#type}
     */
    private ListCellRenderer createTypeRenderer() {
    	DefaultListCellRenderer renderer = new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				if ("".equals(value)) {
					value = "associates";
				} else if ("A".equals(value)) {
					value = "has dependent (has child)";
				} else if ("B".equals(value)) {
					value = "depends on (has parent)";
				}
			return super.getListCellRendererComponent(list, value, index, isSelected,
						cellHasFocus);
			}
			private static final long serialVersionUID = 1992399605806444015L;
    	};
    	return renderer;
    }
    
    /**
     * Set to <code>true</code> if ok-button is clicked.
     */
    private boolean isOk;
    
    /**
     * Currently edited {@link Association}.
     */
    private Line currentAssociation;
    
    /**
     * Edits an association (as csv-line).
     * 
     * @param association the association-line
     * @return <code>true</code> if association was modified
     */
	public boolean edit(Line association) {
		currentAssociation = association;
		nameField.setText(association.cells.get(5));
		DefaultComboBoxModel tablesModel1 = new DefaultComboBoxModel();
		DefaultComboBoxModel tablesModel2 = new DefaultComboBoxModel();
		Set<String> sortedTableNames = new TreeSet<String>();
		for (Line table: tables) {
			sortedTableNames.add(table.cells.get(0));
		}
		sortedTableNames.add(association.cells.get(0));
		sortedTableNames.add(association.cells.get(1));
		for (String tableName: sortedTableNames) {
			tablesModel1.addElement(tableName);
			tablesModel2.addElement(tableName);
		}
		source.setModel(tablesModel1);
		destination.setModel(tablesModel2);

		source.setSelectedItem(association.cells.get(0));
		destination.setSelectedItem(association.cells.get(1));
		type.setSelectedItem(association.cells.get(2));
		joinCondition.setText(association.cells.get(4));
		String cardinality = association.cells.get(3);
		this.cardinality.setSelectedItem(cardinality);
		nameField.setText(association.cells.get(5));
		
		Object origSource = source.getSelectedItem();
		Object origDestination = destination.getSelectedItem();
		Object origType = type.getSelectedItem();
		Object origCardinality = this.cardinality.getSelectedItem();
		String origJoinCondition = joinCondition.getText();
		String origName = nameField.getText();
		
		isOk = false;
		setVisible(true);
		if (isOk && !(origName.equals(nameField.getText()) 
				&& origSource.equals(source.getSelectedItem())
				&& origDestination.equals(destination.getSelectedItem())
				&& origType.equals(type.getSelectedItem())
				&& origCardinality.equals(this.cardinality.getSelectedItem())
				&& origJoinCondition.equals(joinCondition.getText()))) {
			
			association.cells.set(0, (String) source.getSelectedItem());
			association.cells.set(1, (String) destination.getSelectedItem());
			association.cells.set(2, (String) type.getSelectedItem());
			association.cells.set(3, (String) this.cardinality.getSelectedItem());
			association.cells.set(4, joinCondition.getText());
			association.cells.set(5, nameField.getText());
			association.cells.set(6, "Data Model Editor");
			association.length = 6;
			return true;
		}
		return false;
	}
    
    // Variablendeklaration - nicht modifizieren//GEN-BEGIN:variables
    private net.sf.jailer.ui.JComboBox cardinality;
    private net.sf.jailer.ui.JComboBox destination;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField joinCondition;
    private javax.swing.JTextField nameField;
    private net.sf.jailer.ui.JComboBox source;
    private net.sf.jailer.ui.JComboBox type;
    // Ende der Variablendeklaration//GEN-END:variables
    
    private static final long serialVersionUID = 603961628104674406L;
}
