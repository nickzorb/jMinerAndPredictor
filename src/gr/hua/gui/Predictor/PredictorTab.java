/* 
 * Copyright (C) 2013 Harokopio University <Nikolaos Zormpas>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package gr.hua.gui.Predictor;

import gr.hua.gui.MainMenu;
import gr.hua.gui.Tab;
import gr.hua.utils.Logger;
import gr.hua.weka_bridge.Trainer;
import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.zip.GZIPInputStream;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import weka.classifiers.Classifier;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;

/**
 *
 * @author r0t0r
 */
public class PredictorTab extends Tab {

    private ArrayList<Attribute> attributes;
    private ArrayList<String> classifierNames;
    private ArrayList<String> selClassifierNames;
    private ArrayList<Classifier> classifiers;
    private ArrayList<FastVector> attributeSets;
    private HashMap<Integer, String> values;
    private DefaultListModel attributesModel = new DefaultListModel();
    private DefaultListModel classifiersModel = new DefaultListModel();
    private DefaultListModel loadedClassifiersModel = new DefaultListModel();
    private int indx1 = 0, indx2 = 0, indx3 = 0;
    private boolean initialized = false;

    public PredictorTab(MainMenu parent, String title) {
        super(parent, title);
        initComponents();
    }

    private void init() {
        initialized = true;
        attributes = new ArrayList();
        valuesCB.removeAllItems();
        classifierNames = new ArrayList();
        selClassifierNames = new ArrayList();
        classifiers = new ArrayList();
        attributeSets = new ArrayList();
        values = new HashMap();
        attributesL.setSelectionMode(
                DefaultListSelectionModel.SINGLE_SELECTION);
        classifiersL.setSelectionMode(
                DefaultListSelectionModel.SINGLE_SELECTION);
        loadedClassifiersL.setSelectionMode(
                DefaultListSelectionModel.SINGLE_SELECTION);
        attributesL.setModel(attributesModel);
        classifiersL.setModel(classifiersModel);
        loadedClassifiersL.setModel(loadedClassifiersModel);
        attributesModel.removeAllElements();
        classifiersModel.removeAllElements();
        loadedClassifiersModel.removeAllElements();
        File folder = new File(Trainer.TRAINED_CLASSIFIER_PATH);
        File[] files = folder.listFiles();
        for (File f : files) {
            if (f.getName().endsWith(".model")) {
                if (new File(f.getAbsolutePath().replace(".model", ".data")).canRead()) {
                    classifierNames.add(f.getName().substring(0, f.getName().length() - 6));
                }
            }
        }
        valuesCB.setEnabled(false);
        valuesSP.setEnabled(false);
        SpinnerNumberModel spmodel = new SpinnerNumberModel(0,
                Double.NEGATIVE_INFINITY, Double.MAX_VALUE, Double.MIN_VALUE);
        valuesSP.setModel(spmodel);
    }

    private void load() {
        classifiersModel.removeAllElements();
        loadedClassifiersModel.removeAllElements();
        attributesModel.removeAllElements();
        for (String s : classifierNames) {
            classifiersModel.addElement(s);
        }
        for (String s : selClassifierNames) {
            loadedClassifiersModel.addElement(s);
        }
        for (Attribute a : attributes) {
            attributesModel.addElement(a.name());
        }
        if (indx1 <= classifiersModel.getSize()) {
            if (indx1 == classifiersModel.getSize()) {
                indx1--;
            }
            classifiersL.setSelectedIndex(indx1);
        }
        if (indx2 <= loadedClassifiersModel.getSize()) {
            if (indx2 == loadedClassifiersModel.getSize()) {
                indx2--;
            }
            loadedClassifiersL.setSelectedIndex(indx2);
        }
        if (indx3 <= attributesModel.getSize()) {
            if (indx3 == attributesModel.getSize()) {
                indx3--;
            }
            attributesL.setSelectedIndex(indx3);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        classifiersL = new javax.swing.JList();
        loadB = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        attributesL = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        resultsTA = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        valuesCB = new javax.swing.JComboBox();
        predictB = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        valueTF = new javax.swing.JTextField();
        changeB = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        loadedClassifiersL = new javax.swing.JList();
        removeB = new javax.swing.JButton();
        refressB = new javax.swing.JButton();
        valuesSP = new javax.swing.JSpinner();
        jButton1 = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(800, 600));
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(800, 600));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                onFocusGained(evt);
            }
        });

        jLabel1.setText("Available trained algorithms:");

        classifiersL.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(classifiersL);

        loadB.setText("Add");
        loadB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadBActionPerformed(evt);
            }
        });

        jLabel2.setText("Results:");

        jLabel3.setText("Available Attributes");

        attributesL.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        attributesL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                attributesLMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(attributesL);

        resultsTA.setColumns(20);
        resultsTA.setRows(5);
        resultsTA.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        resultsTA.setEnabled(false);
        jScrollPane3.setViewportView(resultsTA);

        jLabel4.setText("Set Value:");

        valuesCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        predictB.setText("Predict");
        predictB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                predictBActionPerformed(evt);
            }
        });

        jLabel5.setText("Current Value:");

        valueTF.setEnabled(false);

        changeB.setText("Change");
        changeB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeBActionPerformed(evt);
            }
        });

        jLabel6.setText("Loaded trained algorithms:");

        loadedClassifiersL.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(loadedClassifiersL);

        removeB.setText("Remove");
        removeB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeBActionPerformed(evt);
            }
        });

        refressB.setText("Refress");
        refressB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refressBActionPerformed(evt);
            }
        });

        jButton1.setText("Algorithm Info");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(18, 18, 18)
                                        .addComponent(valueTF, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(valuesSP, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(valuesCB, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jButton1))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(predictB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(changeB, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))))
                                .addContainerGap(11, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(removeB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(loadB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(refressB))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(24, 24, 24))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jScrollPane2)
                                        .addContainerGap())))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                                .addComponent(jScrollPane4))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(128, 128, 128)
                                .addComponent(loadB)
                                .addGap(18, 18, 18)
                                .addComponent(removeB)
                                .addGap(18, 18, 18)
                                .addComponent(refressB)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(valueTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(changeB)
                            .addComponent(valuesCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(valuesSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(predictB)
                            .addComponent(jButton1)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void predictBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_predictBActionPerformed
        StringBuilder result = new StringBuilder("Results: \n\t");
        for (int i = 0; i < classifiers.size(); i++) {
            Classifier curClass = classifiers.get(i);
            result.append(curClass.getClass()).append(":\n");
            FastVector attributeSet = attributeSets.get(i);
            Instances currentInstances = new Instances("prediction", attributeSet, 1);
            currentInstances.setClassIndex(attributeSet.size() - 1);
            Instance curInstance = new Instance(attributeSet.size());
            curInstance.setDataset(currentInstances);
            currentInstances.add(curInstance);
            for (int j = 0; j < attributeSet.size(); j++) {
                Attribute curAttribute = (Attribute) attributeSet.elementAt(j);
                result.append("'").append(curAttribute.name()).append("' : '");
                if (values.get(attributes.indexOf(curAttribute)) == null) {
                    result.append("', ");
                    curInstance.setMissing(j);
                } else {
                    result.append(values.get(attributes.indexOf(curAttribute))).append("', ");
                    if (curAttribute.isNominal()) {
                        curInstance.setValue(j, values.get(attributes.indexOf(curAttribute)));
                    } else {
                        String sValue = values.get(attributes.indexOf(curAttribute));
                        Double dValue = Double.parseDouble(sValue);
                        if (dValue.toString().equals(sValue)) {
                            curInstance.setValue(j, dValue);
                        } else {
                            Integer iValue = Integer.parseInt(sValue);
                            if (iValue.toString().equals(sValue)) {
                                curInstance.setValue(j, iValue);
                            } else {
                                curInstance.setMissing(j);
                            }
                        }
                    }
                }
            }
            try {
                Double res = curClass.classifyInstance(curInstance);
                result.setLength(result.length() - 2);
                result.append("\n").append("\t\tPrediction: ").append(res);
                result.append("\n\t");
            } catch (Exception ex) {
                Logger.logException(ex);
            }
        }
        resultsTA.setText(result.toString());
    }//GEN-LAST:event_predictBActionPerformed

    private void onFocusGained(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_onFocusGained
        if (!initialized) {
            init();
        }
        load();
    }//GEN-LAST:event_onFocusGained

    private void loadBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadBActionPerformed
        indx1 = classifiersL.getSelectedIndex();
        indx2 = loadedClassifiersL.getSelectedIndex();
        indx3 = attributesL.getSelectedIndex();
        if (indx1 == -1) {
            return;
        }
        ((DefaultListModel) attributesL.getModel()).removeAllElements();
        ObjectInputStream ois = null;
        try {
            String fileName = classifierNames.get(indx1);
            File f = new File(Trainer.TRAINED_CLASSIFIER_PATH + fileName + ".model");
            File fa = new File(Trainer.TRAINED_CLASSIFIER_PATH + fileName + ".data");
            if (!f.canRead() || !fa.canRead()) {
                Logger.logException(new Exception("Couldn't read classifier"));
                return;
            }
            int indx = classifiers.size();
            ois = new ObjectInputStream(new GZIPInputStream(
                    new FileInputStream(f)));
            classifiers.add((Classifier) ois.readObject());
            ois = new ObjectInputStream(new GZIPInputStream(
                    new FileInputStream(fa)));
            attributeSets.add((FastVector) ois.readObject());
            for (Object o : attributeSets.get(indx).toArray()) {
                Attribute cur = (Attribute) o;
                if (!attributes.contains(cur)) {
                    attributes.add(cur);
                }
            }
            selClassifierNames.add(classifierNames.get(indx1));
            classifierNames.remove(indx1);
        } catch (Exception e) {
            Logger.logException(e);
        } finally {
            try {
                ois.close();
            } catch (Exception ex) {
                Logger.logException(ex);
            }
        }
        load();
    }//GEN-LAST:event_loadBActionPerformed

    private void changeBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeBActionPerformed
        if (valuesCB.isEnabled()) {
            int indx = valuesCB.getSelectedIndex();
            values.put(indx3, (String) valuesCB.getItemAt(indx));
            valueTF.setText((String) valuesCB.getItemAt(indx));
        } else if (valuesSP.isEnabled()) {
            values.put(indx3, valuesSP.getValue().toString());
            valueTF.setText(values.get(indx3));
        }
    }//GEN-LAST:event_changeBActionPerformed

    private void removeBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeBActionPerformed
        indx1 = classifiersL.getSelectedIndex();
        indx2 = loadedClassifiersL.getSelectedIndex();
        indx3 = attributesL.getSelectedIndex();
        if (indx2 < 0) {
            return;
        }
        classifierNames.add(selClassifierNames.get(indx2));
        selClassifierNames.remove(indx2);
        classifiers.remove(indx2);
        attributeSets.remove(indx2);
        load();
    }//GEN-LAST:event_removeBActionPerformed

    private void attributesLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_attributesLMouseClicked
        indx3 = attributesL.getSelectedIndex();
        if (indx3 < 0) {
            return;
        }
        Attribute curAttr = attributes.get(indx3);
        valuesCB.removeAllItems();
        if (curAttr.isNominal()) {
            valuesCB.setEnabled(true);
            valuesSP.setEnabled(false);
            Enumeration m = curAttr.enumerateValues();
            while (m.hasMoreElements()) {
                valuesCB.addItem(m.nextElement());
            }
        } else {
            valuesCB.setEnabled(false);
            valuesSP.setEnabled(true);
        }
        String curValue = values.get(indx3);
        valueTF.setText(curValue == null ? "" : curValue);
    }//GEN-LAST:event_attributesLMouseClicked

    private void refressBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refressBActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Are you sure?\n "
                + "This will reset all current selections and attempt to\n"
                + "load all available classifiers\n", "Reset",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            init();
            load();
        }
    }//GEN-LAST:event_refressBActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        indx2 = loadedClassifiersL.getSelectedIndex();
        JTextArea textArea = new JTextArea(classifiers.get(indx2).toString());
        textArea.setColumns(30);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setSize(textArea.getPreferredSize().width, 1);
        JScrollPane temp = new JScrollPane();
        temp.setPreferredSize(new Dimension(400,600));
        temp.setViewportView(textArea);
        JOptionPane.showMessageDialog(
            this, temp, "Classifier Info", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButton1ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList attributesL;
    private javax.swing.JButton changeB;
    private javax.swing.JList classifiersL;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JButton loadB;
    private javax.swing.JList loadedClassifiersL;
    private javax.swing.JButton predictB;
    private javax.swing.JButton refressB;
    private javax.swing.JButton removeB;
    private javax.swing.JTextArea resultsTA;
    private javax.swing.JTextField valueTF;
    private javax.swing.JComboBox valuesCB;
    private javax.swing.JSpinner valuesSP;
    // End of variables declaration//GEN-END:variables
}
