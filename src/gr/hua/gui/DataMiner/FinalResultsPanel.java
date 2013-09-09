/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.hua.gui.DataMiner;

import gr.hua.weka_bridge.CloneableAttribute;
import java.io.File;
import weka.core.FastVector;

/**
 *
 * @author r0t0r
 */
public class FinalResultsPanel extends javax.swing.JPanel {

    private String[] classifierNames;
    double[] results;
    FastVector[] attributes;
    String[] descriptions;
    private int ready = 0;

    /**
     * Creates new form FinalResultsPanel
     */
    public FinalResultsPanel(String[] classifiers, double[] dResults,
            FastVector[] fvAttributes, String[] sDescriptions) {
        initComponents();
        classifierNames = classifiers;
        attributes = fvAttributes;
        results = dResults;
        descriptions = sDescriptions;
        sort();
        classifiersCB.removeAllItems();
        for (String s : classifiers) {
            String name = s;
            while (name.contains("/")) {
                name = name.substring(name.indexOf("/") + 1);
            }
            classifiersCB.addItem(name);
        }
        ready = 1;
        classifiersCB.setSelectedIndex(0);
    }

    private void sort() {
        for (int i = 0; i < results.length - 1; i++) {
            for (int j = i + 1; j < results.length; j++) {
                if (results[j] > results[i]) {
                    Object temp;
                    temp = results[j];
                    results[j] = results[i];
                    results[i] = (double) temp;
                    temp = classifierNames[j];
                    classifierNames[j] = classifierNames[i];
                    classifierNames[i] = (String) temp;
                    temp = descriptions[j];
                    descriptions[j] = descriptions[i];
                    descriptions[i] = (String) temp;
                    temp = attributes[j];
                    attributes[j] = attributes[i];
                    attributes[i] = (FastVector) temp;
                }
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        classifiersCB = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        resultL = new javax.swing.JLabel();
        deleteB = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descriptionTA = new javax.swing.JTextArea();

        setMaximumSize(new java.awt.Dimension(200, 400));
        setMinimumSize(new java.awt.Dimension(200, 400));
        setPreferredSize(new java.awt.Dimension(200, 400));

        jLabel1.setText("Saved classifiers:");

        classifiersCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        classifiersCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                classifiersCBActionPerformed(evt);
            }
        });

        jLabel2.setText("Success rate:");

        deleteB.setText("Delete");
        deleteB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBActionPerformed(evt);
            }
        });

        jLabel3.setText("More details:");

        descriptionTA.setColumns(20);
        descriptionTA.setRows(5);
        jScrollPane1.setViewportView(descriptionTA);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(classifiersCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resultL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(deleteB, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(classifiersCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(resultL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteB))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void classifiersCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_classifiersCBActionPerformed
        if (ready != 1) {
            return;
        }
        int idx = classifiersCB.getSelectedIndex();
        if (idx == -1) {
            return;
        }
        resultL.setText(results[idx] + "");
        StringBuilder description = new StringBuilder("Columns:\n\t");
        FastVector f = attributes[idx];
        for (Object o : f.toArray()) {
            CloneableAttribute c = (CloneableAttribute) o;
            description.append(c.name()).append("\n\t");
        }
        description.append("\n").append(descriptions[idx]);
        descriptionTA.setText(description.toString());
    }//GEN-LAST:event_classifiersCBActionPerformed

    private void deleteBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBActionPerformed
        if (classifiersCB.getSelectedIndex() == -1) {
            return;
        }
        int idx = classifiersCB.getSelectedIndex();
        File f = new File(classifierNames[idx]);
        f.delete();
        for (int i = idx; i < classifierNames.length - 1; i++) {
            classifierNames[i] = classifierNames[i + 1];
            classifierNames[i + 1] = null;
            results[i] = results[i + 1];
            results[i + 1] = 0;
            descriptions[i] = descriptions[i + 1];
            descriptions[i + 1] = null;
        }
        classifiersCB.removeAllItems();
        ready = 0;
        for (String s : classifierNames) {
            if (s != null) {
                classifiersCB.addItem(s.substring(s.indexOf("/") + 1));
            }
        }
        ready = 1;
        if (idx >= classifiersCB.getItemCount()) {
            if (idx != 0) {
                idx--;
            } else {
                resultL.setText("");
                descriptionTA.setText("");
                deleteB.setEnabled(false);
                return;
            }
        }
        classifiersCB.setSelectedIndex(idx);
    }//GEN-LAST:event_deleteBActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox classifiersCB;
    private javax.swing.JButton deleteB;
    private javax.swing.JTextArea descriptionTA;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel resultL;
    // End of variables declaration//GEN-END:variables
}
