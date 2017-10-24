package net.sf.jailer.ui.databrowser.sqlconsole;

import java.awt.Dimension;
import java.awt.GridBagConstraints;

import javax.swing.JLabel;

public class TabContentPanel extends javax.swing.JPanel {

    /**
     * Creates new form TabContentPanel
     */
    public TabContentPanel() {
        initComponents();
        statementLabel = new JLabel() {
        	@Override
        	public Dimension getMinimumSize() {
				return new Dimension(8, super.getMinimumSize().height);
        	}
        };
        statementLabel.setText("jLabel1");
        GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        panel.add(statementLabel, gridBagConstraints);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        contentPanel = new javax.swing.JPanel();
        panel = new javax.swing.JPanel();
        controlsPanel1 = new javax.swing.JPanel();

        setLayout(new java.awt.GridBagLayout());

        contentPanel.setLayout(new java.awt.BorderLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(contentPanel, gridBagConstraints);

        panel.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        panel.add(controlsPanel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 0);
        add(panel, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPanel contentPanel;
    public javax.swing.JPanel controlsPanel1;
    public javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables
    public javax.swing.JLabel statementLabel;
}
