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
package gr.hua.gui.FileManager;

import gr.hua.data_manipulation.Action;
import gr.hua.data_structures.DataColumn;
import gr.hua.data_structures.DataRow;
import gr.hua.gui.MainMenu;
import gr.hua.gui.Tab;
import gr.hua.utils.Logger;
import java.io.File;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author r0t0r
 */
public class FileManagerTab extends Tab {

    //Looks and feels
    private String[] laf = {
         "com.jtattoo.plaf.mint.MintLookAndFeel",
         "javax.swing.plaf.nimbus.NimbusLookAndFeel",
         "com.jtattoo.plaf.aluminium.AluminiumLookAndFeel",
         "com.jtattoo.plaf.texture.TextureLookAndFeel",
         UIManager.getSystemLookAndFeelClassName()
         
    };
    private int selectedLaf = 0;
    
    private int index1 = 0, index2 = 0;
    private FileOptionsDialog optionsD;
    private File file = null;
    private DefaultListModel attributesL;

    public FileManagerTab(MainMenu parent, String title) {
        super(parent, title);
        initComponents();
        this.parent = parent;
        parent.registerComponent(MainMenu.LOG_AREAS, logArea);
        optionsD = new FileOptionsDialog(parent, true);
        attributesL = new DefaultListModel();
        attributesList.setModel(attributesL);
        attributesList.setSelectionMode(
                DefaultListSelectionModel.SINGLE_SELECTION);
        attributesList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting() && attributesList.getSelectedIndex() != -1) {
                    DataColumn temp = MainMenu.MANAGER.getColumn(attributesList.getSelectedIndex());
                    if (temp != null) {
                        attributeInfoTA.setText(temp.info());
                        attributeInfoTA.setCaretPosition(0);
                    }
                }
            }
        });
        instancesCB.removeAllItems();
        attributesL.removeAllElements();
        //because otherwise the laf gets all confused
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                setLaF();
            }
        });
    }

    private void loadFile() {
        int error = MainMenu.MANAGER.readFile(file);
        if (error != 0) {
            JOptionPane.showMessageDialog(this,
                    "Found errors! Please check the error log.");
        } else {
            JOptionPane.showMessageDialog(this, "File opened successfully");
        }
        refress();
    }

    private void refress() {
        if (MainMenu.MANAGER.ready()) {
            MainMenu.MANAGER.validate();
        }
        instancesCB.removeAllItems();
        attributesL.removeAllElements();
        if (!MainMenu.MANAGER.ready()) {
            return;
        }
        for (String s : MainMenu.MANAGER.getColumnNames()) {
            attributesL.addElement(s);
        }
        for (int i = 0; i < MainMenu.MANAGER.getNumberOfRows(); i++) {
            instancesCB.addItem(i + 1);
        }
        if (MainMenu.MANAGER.getColumnNames().length <= index1) {
            attributesList.setSelectedIndex(MainMenu.MANAGER.getColumnNames().length - 1);
        } else {
            attributesList.setSelectedIndex(index1);
        }
        if (MainMenu.MANAGER.getNumberOfRows() <= index2) {
            instancesCB.setSelectedIndex(MainMenu.MANAGER.getNumberOfRows() - 1);
        } else {
            instancesCB.setSelectedIndex(index2);
        }
    }
    
    private void setLaF() {
        try {
            UIManager.setLookAndFeel(laf[selectedLaf]);
            SwingUtilities.updateComponentTreeUI(MainMenu.main);
            MainMenu.main.pack();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            Logger.logException(e);
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

        filePath = new javax.swing.JTextField();
        browseB = new javax.swing.JButton();
        openFileB = new javax.swing.JButton();
        quitB = new javax.swing.JButton();
        saveFileB = new javax.swing.JButton();
        optionsB = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        attributesList = new javax.swing.JList();
        jLabel2 = new javax.swing.JLabel();
        deleteInstanceB = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        instanceInfoTA = new javax.swing.JTextArea();
        attributeInfoContainer = new javax.swing.JScrollPane();
        attributeInfoTA = new javax.swing.JTextArea();
        instancesCB = new javax.swing.JComboBox();
        deleteAttributeB = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        logArea = new javax.swing.JTextArea();
        changeLookB = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(800, 640));
        setMinimumSize(new java.awt.Dimension(800, 640));
        setPreferredSize(new java.awt.Dimension(800, 640));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                OnFocusGain(evt);
            }
        });

        browseB.setText("Open");
        browseB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseBActionPerformed(evt);
            }
        });

        openFileB.setText("Reload File");
        openFileB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFileBActionPerformed(evt);
            }
        });

        quitB.setText("Quit");
        quitB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitBActionPerformed(evt);
            }
        });

        saveFileB.setText("Save As");
        saveFileB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveFileBActionPerformed(evt);
            }
        });

        optionsB.setText("File Reading/Writting Options");
        optionsB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optionsBActionPerformed(evt);
            }
        });

        jLabel1.setText("Instances (Rows): ");

        jLabel3.setText("Attributes (Columns):");

        attributesList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(attributesList);

        jLabel2.setText("Instance Info:");

        deleteInstanceB.setText("Delete Instance");
        deleteInstanceB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteInstanceBActionPerformed(evt);
            }
        });

        jLabel4.setText("Attribute Info:");

        instanceInfoTA.setColumns(20);
        instanceInfoTA.setRows(5);
        instanceInfoTA.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        instanceInfoTA.setEnabled(false);
        jScrollPane1.setViewportView(instanceInfoTA);

        attributeInfoTA.setColumns(20);
        attributeInfoTA.setRows(5);
        attributeInfoTA.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        attributeInfoTA.setEnabled(false);
        attributeInfoContainer.setViewportView(attributeInfoTA);

        instancesCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        instancesCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                instancesCBActionPerformed(evt);
            }
        });

        deleteAttributeB.setText("Delete Attribute");
        deleteAttributeB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteAttributeBActionPerformed(evt);
            }
        });

        jLabel5.setText("Log:");

        logArea.setColumns(20);
        logArea.setRows(5);
        logArea.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        logArea.setEnabled(false);
        jScrollPane4.setViewportView(logArea);

        changeLookB.setText("Change Look");
        changeLookB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeLookBActionPerformed(evt);
            }
        });

        jButton1.setText("Save");
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(optionsB)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(instancesCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(deleteInstanceB)
                                .addGap(92, 92, 92))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(33, 33, 33)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel4)
                                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                                            .addComponent(attributeInfoContainer)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(107, 107, 107)
                                        .addComponent(deleteAttributeB)))
                                .addGap(18, 18, 18)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 2, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(108, 108, 108)
                                        .addComponent(changeLookB)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(quitB))))
                            .addComponent(jScrollPane4)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(saveFileB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(browseB)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(openFileB)))))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(15, 15, 15)
                    .addComponent(filePath, javax.swing.GroupLayout.PREFERRED_SIZE, 615, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(170, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(openFileB)
                    .addComponent(browseB))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(optionsB)
                    .addComponent(saveFileB)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(instancesCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(deleteInstanceB)
                                .addGap(11, 11, 11)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(attributeInfoContainer))
                            .addComponent(jScrollPane4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(quitB)
                            .addComponent(deleteAttributeB)
                            .addComponent(changeLookB))))
                .addGap(45, 45, 45))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addComponent(filePath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(604, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void browseBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseBActionPerformed
        JFileChooser fc = new JFileChooser();
        String curPath;
        if (filePath.getText().equals("")) {
            curPath = System.getProperty("user.dir");
        } else {
            curPath = filePath.getText();
        }
        fc.setCurrentDirectory(new File(curPath));
        int res = fc.showOpenDialog(this);
        if (res == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
            filePath.setText(file.getAbsolutePath());
            MainMenu.CURRENT_FILE = file.getName();
            loadFile();
        } else {
            file = null;
        }
    }//GEN-LAST:event_browseBActionPerformed

    private void openFileBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openFileBActionPerformed
        if (file == null && filePath.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please choose a file first!");
            return;
        } else if (file == null) {
            try {
                file = new File(filePath.getText());
                if (!file.exists()) {
                    throw new Exception("Non existing file");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error opening file!");
                return;
            }
        }
        loadFile();
    }//GEN-LAST:event_openFileBActionPerformed

    private void quitBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitBActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Do you want to quit? \nAny unsaved changes will be lost", "Quit", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            parent.dispose();
            Logger.close();
        }
    }//GEN-LAST:event_quitBActionPerformed

    private void saveFileBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveFileBActionPerformed
        if (!MainMenu.MANAGER.ready()) {
            JOptionPane.showMessageDialog(this, "No file currently open!");
            return;
        }
        JFileChooser fc = new JFileChooser();
        String curPath;
        if (filePath.getText().equals("")) {
            curPath = System.getProperty("user.dir");
        } else {
            curPath = filePath.getText();
        }
        fc.setCurrentDirectory(new File(curPath));
        int res = fc.showSaveDialog(this);
        if (res == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
            filePath.setText(file.getAbsolutePath());
            try {
                MainMenu.MANAGER.saveFile(file);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Failed to write to file!");
            }
        }
        JOptionPane.showMessageDialog(this, "File saved!");
    }//GEN-LAST:event_saveFileBActionPerformed

    private void deleteInstanceBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteInstanceBActionPerformed
        if (!MainMenu.MANAGER.ready()) {
            JOptionPane.showMessageDialog(this, "No file currently open!");
            return;
        }
        index1 = attributesList.getSelectedIndex();
        index2 = instancesCB.getSelectedIndex();
        Action action = new Action(Action.RR, null, index2);
        MainMenu.MANAGER.applyAction(action);
        refress();
    }//GEN-LAST:event_deleteInstanceBActionPerformed

    private void deleteAttributeBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteAttributeBActionPerformed
        if (!MainMenu.MANAGER.ready()) {
            JOptionPane.showMessageDialog(this, "No file currently open!");
            return;
        }
        index1 = attributesList.getSelectedIndex();
        index2 = instancesCB.getSelectedIndex();
        Action action = new Action(Action.CR, null, index1);
        MainMenu.MANAGER.applyAction(action);
        refress();
    }//GEN-LAST:event_deleteAttributeBActionPerformed

    private void optionsBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optionsBActionPerformed
        optionsD.display();
    }//GEN-LAST:event_optionsBActionPerformed

    private void OnFocusGain(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_OnFocusGain
        refress();
    }//GEN-LAST:event_OnFocusGain

    private void instancesCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_instancesCBActionPerformed
        if (instancesCB.getSelectedIndex() == -1) {
            return;
        }
        DataRow temp = MainMenu.MANAGER.get(instancesCB.getSelectedIndex());
        if (temp != null) {
            instanceInfoTA.setText(temp.info());
            instanceInfoTA.setCaretPosition(0);
        }
    }//GEN-LAST:event_instancesCBActionPerformed

    private void changeLookBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeLookBActionPerformed
        selectedLaf = (selectedLaf + 1) % laf.length;
        setLaF();
    }//GEN-LAST:event_changeLookBActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (file == null && filePath.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please choose a file first!");
            return;
        } else if (file == null) {
            try {
                file = new File(filePath.getText());
                if (!file.exists()) {
                    throw new Exception("Non existing file");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error finding file!");
                return;
            }
        }
        if (JOptionPane.showConfirmDialog(this, "Are you sure you want to overwrite this file?", "Save", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            try {
                MainMenu.MANAGER.saveFile(file);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Failed to write to file!");
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane attributeInfoContainer;
    private javax.swing.JTextArea attributeInfoTA;
    private javax.swing.JList attributesList;
    private javax.swing.JButton browseB;
    private javax.swing.JButton changeLookB;
    private javax.swing.JButton deleteAttributeB;
    private javax.swing.JButton deleteInstanceB;
    private javax.swing.JTextField filePath;
    private javax.swing.JTextArea instanceInfoTA;
    private javax.swing.JComboBox instancesCB;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea logArea;
    private javax.swing.JButton openFileB;
    private javax.swing.JButton optionsB;
    private javax.swing.JButton quitB;
    private javax.swing.JButton saveFileB;
    // End of variables declaration//GEN-END:variables
}
