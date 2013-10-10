/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.hua.gui.DataMiner;

import gr.hua.data_structures.DataColumn;
import gr.hua.gui.MainMenu;
import gr.hua.gui.Tab;
import gr.hua.weka_bridge.CloneableAttribute;
import gr.hua.weka_bridge.Trainer;
import java.util.ArrayList;
import java.util.Properties;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author r0t0r
 */
public class DataMinerTab extends Tab {

    private int id1 = 0, id2 = 0, id3 = 0;
    private ArrayList<JListItem> columns;
    private ArrayList<MiningMethodPanel> alMiningMethods = new ArrayList();
    private boolean ready = false;

    public DataMinerTab(MainMenu parent, String title) {
        super(parent, title);
        initComponents();
        columnsList.setModel(new MyDefaultListModel());
        selColumnsList.setModel(new MyDefaultListModel());
        targetList.setModel(new MyDefaultListModel());
        targetList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        loadMiner();
        miningMethods.setLayout(new BoxLayout(miningMethods, BoxLayout.Y_AXIS));
        for (int i : MiningMethodPanel.methods) {
            miningMethods.add(MiningMethodPanel.getInstance(i));
            alMiningMethods.add(MiningMethodPanel.getInstance(i));
        }
        topResultsSP.setEnabled(false);
        topResultsSP.setModel(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
        resultsL.setEnabled(false);
        miningMethods.revalidate();
        llimitSP.setEnabled(false);
        llimitSP.setModel(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
        limitL.setEnabled(false);
        lowerLimit.setEnabled(false);
        rlimitSP.setEnabled(false);
        rlimitSP.setModel(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
        upperLimit.setEnabled(false);
        limitCB.setEnabled(false);
        ready = true;
    }

    private boolean checkForDuplicates() {
        for (int i = 0; i < columns.size() - 1; i++) {
            for (int j = i + 1; j < columns.size(); j++) {
                if (columns.get(i).getLabel().equals(columns.get(j).getLabel())) {
                    return false;
                }
            }
        }
        if (!autoAttributesCB.isSelected()) {
            if (((MyDefaultListModel) selColumnsList.getModel()).contains(targetList.getSelectedValue())) {
                return false;
            }
        }
        return true;
    }

    private void initColumns() {
        columns = new ArrayList();
        for (int i = 0; i < MainMenu.MANAGER.getNumberOfColumns(); i++) {
            DataColumn temp = MainMenu.MANAGER.getColumn(i);
            columns.add(new JListItem(temp.getName(), i, temp.getAttribute(), columnsList));
        }
    }

    private void loadMiner() {
        if (!ready) {
            return;
        }
        if (!MainMenu.MANAGER.readyForMining()) {
            JOptionPane.showMessageDialog(MainMenu.main, "No file currently "
                    + "open, or there are errors within the file", "Mining not"
                    + "available",
                    JOptionPane.ERROR_MESSAGE);
            MainMenu.tabbedPane.setSelectedIndex(0);
            return;
        }
        ((MyDefaultListModel) columnsList.getModel()).removeAllElements();
        ((MyDefaultListModel) targetList.getModel()).removeAllElements();
        ((MyDefaultListModel) selColumnsList.getModel()).removeAllElements();
        for (JListItem item : columns) {
            ((MyDefaultListModel) item.getParent().getModel()).addElement(item);
        }
        for (JListItem j : ((MyDefaultListModel) columnsList.getModel()).toArray()) {
            ((MyDefaultListModel) targetList.getModel()).addElement(j);
        }
        columnsList.setSelectedIndex(id1);
        selColumnsList.setSelectedIndex(id2);
        targetList.setSelectedIndex(id3);
    }

    private void savePositions() {
        id1 = columnsList.getSelectedIndex();
        id2 = selColumnsList.getSelectedIndex();
        id3 = targetList.getSelectedIndex();
        if (id3 == -1) {
            id3 = 0;
        }
    }

    private void collectProperties(Properties p) {
        if (autoAttributesCB.isSelected() || autoSelAttributesCB.isSelected()) {
            p.setProperty(Trainer.OPTIMIZE_ATTRIBUTES, "ON");
            p.setProperty(Trainer.SET_TOP_RESULTS, topResultsSP.getValue().toString());
            if (limitCB.isSelected()) {
                p.setProperty(Trainer.SET_LOW_LIMIT, llimitSP.getValue().toString());
            }
            if (upperLimit.isSelected()) {
                p.setProperty(Trainer.SET_LIMIT, rlimitSP.getValue().toString());
            }
        }
        if (leaveOneOut.isSelected()) {
            p.setProperty(Trainer.LEAVE_ONE_OUT, "ON");
        }
        if (cstat.isSelected()) {
            p.setProperty(Trainer.STATISTICAL, "ONO");
        }
    }
    
    private CloneableAttribute getTargetAttribute() {
        JListItem item = (JListItem) targetList.getSelectedValue();
        String name = ((CloneableAttribute) item.getValue()).name();
        String[] names = MainMenu.MANAGER.getColumnNames();
        for (int i = 0; i < names.length; i++) {
            if (names[i].equals(name)) {
                return MainMenu.MANAGER.getColumn(i).getForcedNominalAttribute();
            }
        }
        return null;
    }
            
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        canvas1 = new java.awt.Canvas();
        valuesScr = new javax.swing.JScrollPane();
        columnsList = new javax.swing.JList();
        addB = new javax.swing.JButton();
        valuesScr1 = new javax.swing.JScrollPane();
        selColumnsList = new javax.swing.JList();
        removeB = new javax.swing.JButton();
        jLabel37 = new javax.swing.JLabel();
        valuesScr2 = new javax.swing.JScrollPane();
        targetList = new javax.swing.JList();
        jLabel38 = new javax.swing.JLabel();
        mineB = new javax.swing.JButton();
        autoAttributesCB = new javax.swing.JCheckBox();
        jLabel39 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        container = new javax.swing.JScrollPane();
        miningMethods = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        leaveOneOut = new javax.swing.JCheckBox();
        cstat = new javax.swing.JCheckBox();
        autoSelAttributesCB = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        resultsL = new javax.swing.JLabel();
        topResultsSP = new javax.swing.JSpinner();
        limitL = new javax.swing.JLabel();
        limitCB = new javax.swing.JCheckBox();
        lowerLimit = new javax.swing.JLabel();
        llimitSP = new javax.swing.JSpinner();
        rlimitSP = new javax.swing.JSpinner();
        upperLimit = new javax.swing.JCheckBox();

        setMaximumSize(new java.awt.Dimension(800, 600));
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(800, 600));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        valuesScr.setMaximumSize(new java.awt.Dimension(191, 360));
        valuesScr.setMinimumSize(new java.awt.Dimension(191, 360));
        valuesScr.setPreferredSize(new java.awt.Dimension(191, 360));

        columnsList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        columnsList.setMaximumSize(null);
        columnsList.setMinimumSize(null);
        columnsList.setPreferredSize(null);
        valuesScr.setViewportView(columnsList);

        addB.setText("Add");
        addB.setMaximumSize(new java.awt.Dimension(71, 23));
        addB.setMinimumSize(new java.awt.Dimension(90, 23));
        addB.setPreferredSize(new java.awt.Dimension(90, 23));
        addB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBActionPerformed(evt);
            }
        });

        valuesScr1.setMaximumSize(new java.awt.Dimension(191, 360));
        valuesScr1.setMinimumSize(new java.awt.Dimension(191, 360));
        valuesScr1.setPreferredSize(new java.awt.Dimension(191, 360));

        selColumnsList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        selColumnsList.setMaximumSize(null);
        selColumnsList.setMinimumSize(null);
        selColumnsList.setPreferredSize(null);
        valuesScr1.setViewportView(selColumnsList);

        removeB.setText("Remove");
        removeB.setMaximumSize(new java.awt.Dimension(90, 23));
        removeB.setMinimumSize(new java.awt.Dimension(90, 23));
        removeB.setPreferredSize(new java.awt.Dimension(90, 23));
        removeB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeBActionPerformed(evt);
            }
        });

        jLabel37.setText("Select target column:");

        valuesScr2.setMaximumSize(new java.awt.Dimension(149, 130));
        valuesScr2.setMinimumSize(new java.awt.Dimension(149, 130));

        targetList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        targetList.setMaximumSize(null);
        targetList.setMinimumSize(null);
        targetList.setPreferredSize(null);
        valuesScr2.setViewportView(targetList);

        jLabel38.setText("Data Mining methods:");

        mineB.setText("Mine");
        mineB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mineBActionPerformed(evt);
            }
        });

        autoAttributesCB.setText("All attributes");
        autoAttributesCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                autoAttributesCBActionPerformed(evt);
            }
        });

        jLabel39.setText("Select attributes:");

        jLabel1.setText("Selected attributes:");

        javax.swing.GroupLayout miningMethodsLayout = new javax.swing.GroupLayout(miningMethods);
        miningMethods.setLayout(miningMethodsLayout);
        miningMethodsLayout.setHorizontalGroup(
            miningMethodsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 778, Short.MAX_VALUE)
        );
        miningMethodsLayout.setVerticalGroup(
            miningMethodsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 138, Short.MAX_VALUE)
        );

        container.setViewportView(miningMethods);

        jLabel2.setText("Extra Options:");

        jScrollPane2.setMaximumSize(new java.awt.Dimension(190, 170));
        jScrollPane2.setMinimumSize(new java.awt.Dimension(190, 170));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(190, 170));

        leaveOneOut.setText("Leave-one-out");

        cstat.setText("Use C-statistic");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cstat)
                    .addComponent(leaveOneOut, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(leaveOneOut)
                .addGap(3, 3, 3)
                .addComponent(cstat)
                .addContainerGap(119, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel1);

        autoSelAttributesCB.setText("Only those selected");
        autoSelAttributesCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                autoSelAttributesCBActionPerformed(evt);
            }
        });

        jLabel3.setText("Select attributes automatically from:");

        resultsL.setText("Top results to save:");

        limitL.setText("Limit recursion depth:");

        lowerLimit.setText("Lower Limit:");

        llimitSP.setMaximumSize(new java.awt.Dimension(40, 20));
        llimitSP.setMinimumSize(new java.awt.Dimension(40, 20));
        llimitSP.setPreferredSize(new java.awt.Dimension(40, 20));

        rlimitSP.setMaximumSize(new java.awt.Dimension(40, 20));
        rlimitSP.setMinimumSize(new java.awt.Dimension(40, 20));
        rlimitSP.setPreferredSize(new java.awt.Dimension(40, 20));

        upperLimit.setText("Upper Limit:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(container, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(valuesScr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(98, 98, 98)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(addB, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(removeB, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(26, 26, 26)
                                        .addComponent(valuesScr1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(11, 11, 11)
                                        .addComponent(autoAttributesCB))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(limitL)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(autoSelAttributesCB)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(limitCB)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lowerLimit)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(llimitSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(upperLimit)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(resultsL)
                                .addGap(39, 39, 39)
                                .addComponent(topResultsSP, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rlimitSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(mineB))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(valuesScr2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(jLabel1)
                    .addComponent(jLabel37))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(valuesScr2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(topResultsSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(resultsL))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(valuesScr1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(valuesScr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(autoAttributesCB, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(autoSelAttributesCB)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(110, 110, 110)
                                .addComponent(addB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(removeB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel38)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGap(2, 2, 2)
                            .addComponent(limitCB)))
                    .addComponent(mineB)
                    .addComponent(limitL, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lowerLimit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(llimitSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(upperLimit)
                        .addComponent(rlimitSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(container, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBActionPerformed
        savePositions();
        int[] id = columnsList.getSelectedIndices();
        if (id1 != -1) {
            for (int i = 0; i < id.length; i++) {
                ((MyDefaultListModel) columnsList.getModel()).get(id[i]).setParent(selColumnsList);
            }
            loadMiner();
        }
    }//GEN-LAST:event_addBActionPerformed

    private void removeBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeBActionPerformed
        savePositions();
        int[] id = selColumnsList.getSelectedIndices();
        if (id2 != -1) {
            for (int i = 0; i < id.length; i++) {
                ((MyDefaultListModel) selColumnsList.getModel()).get(id[i]).setParent(columnsList);
            }
            loadMiner();
        }
    }//GEN-LAST:event_removeBActionPerformed

    private void mineBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mineBActionPerformed
        ResultsArea resArea = new ResultsArea(MainMenu.main, true);
        Properties prop = new Properties();
        collectProperties(prop);
        CloneableAttribute target = getTargetAttribute();
        if (target == null) {
            JOptionPane.showMessageDialog(this,
                    "Couldn't turn target column into a nominal attribute!");
            return;
        }
        ArrayList<CloneableAttribute> selColumns = new ArrayList();
        for (JListItem j : columns) {
            if (!autoAttributesCB.isSelected() && j.getParent() == selColumnsList) {
                selColumns.add((CloneableAttribute) j.getValue());
            } else if (autoAttributesCB.isSelected()) {
                String name = ((CloneableAttribute) j.getValue()).name();
                if (!name.equals(target.name())) {
                    selColumns.add((CloneableAttribute) j.getValue());
                }
            }
        }
        if (prop.get(Trainer.SET_LOW_LIMIT) != null) {
            if (Integer.parseInt(prop.getProperty(Trainer.SET_LOW_LIMIT)) > selColumns.size()) {
                JOptionPane.showMessageDialog(this, "Invalid low limit", "Low limit error", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (prop.get(Trainer.SET_LIMIT) != null) {
                if (Integer.parseInt(prop.getProperty(Trainer.SET_LOW_LIMIT)) > Integer.parseInt(prop.getProperty(Trainer.SET_LIMIT))) {
                    JOptionPane.showMessageDialog(this, "Low limit should never be less than upper limit", "Low limit error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        }
        for (MiningMethodPanel m : alMiningMethods) {
            if (m.checked()) {
                if (m.supportsNumeric()) {
                    JListItem item = (JListItem) targetList.getSelectedValue();
                    CloneableAttribute tempTarget = (CloneableAttribute) item.getValue();
                    m.mine(resArea, selColumns, tempTarget, prop);
                } else {
                    m.mine(resArea, selColumns, target, prop);
                }
            }
        }
        resArea.open();
        for (MiningMethodPanel m : alMiningMethods) {
            if (m.checked()) {
                m.stop();
            }
        }
    }//GEN-LAST:event_mineBActionPerformed

    private void autoAttributesCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_autoAttributesCBActionPerformed
        columnsList.setEnabled(!autoAttributesCB.isSelected());
        selColumnsList.setEnabled(!autoAttributesCB.isSelected());
        autoSelAttributesCB.setEnabled(!autoAttributesCB.isSelected());
        boolean status = autoSelAttributesCB.isSelected() || autoAttributesCB.isSelected();
        topResultsSP.setEnabled(status);
        resultsL.setEnabled(status);
        rlimitSP.setEnabled(status);
        llimitSP.setEnabled(status);
        limitL.setEnabled(status);
        lowerLimit.setEnabled(status);
        upperLimit.setEnabled(status);
        limitCB.setEnabled(status);
    }//GEN-LAST:event_autoAttributesCBActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        initColumns();
        loadMiner();
    }//GEN-LAST:event_formComponentShown

    private void autoSelAttributesCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_autoSelAttributesCBActionPerformed
        autoAttributesCB.setEnabled(!autoSelAttributesCB.isSelected());
        boolean status = autoSelAttributesCB.isSelected() || autoAttributesCB.isSelected();
        topResultsSP.setEnabled(status);
        resultsL.setEnabled(status);
        llimitSP.setEnabled(status);
        rlimitSP.setEnabled(status);
        limitL.setEnabled(status);
        lowerLimit.setEnabled(status);
        upperLimit.setEnabled(status);
        limitCB.setEnabled(status);
    }//GEN-LAST:event_autoSelAttributesCBActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addB;
    private javax.swing.JCheckBox autoAttributesCB;
    private javax.swing.JCheckBox autoSelAttributesCB;
    private java.awt.Canvas canvas1;
    private javax.swing.JList columnsList;
    private javax.swing.JScrollPane container;
    private javax.swing.JCheckBox cstat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JCheckBox leaveOneOut;
    private javax.swing.JCheckBox limitCB;
    private javax.swing.JLabel limitL;
    private javax.swing.JSpinner llimitSP;
    private javax.swing.JLabel lowerLimit;
    private javax.swing.JButton mineB;
    private javax.swing.JPanel miningMethods;
    private javax.swing.JButton removeB;
    private javax.swing.JLabel resultsL;
    private javax.swing.JSpinner rlimitSP;
    private javax.swing.JList selColumnsList;
    private javax.swing.JList targetList;
    private javax.swing.JSpinner topResultsSP;
    private javax.swing.JCheckBox upperLimit;
    private javax.swing.JScrollPane valuesScr;
    private javax.swing.JScrollPane valuesScr1;
    private javax.swing.JScrollPane valuesScr2;
    // End of variables declaration//GEN-END:variables
}
