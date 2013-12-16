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

import gr.hua.data_manipulation.DataManager;
import gr.hua.gui.dialogs.MyDialog;
import gr.hua.utils.PropertiesLoader;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;

/**
 *
 * @author r0t0r
 */
public final class FileOptionsDialog extends MyDialog {

    private int res;
    private static String[] charsets = {"UTF-8", "UTF-16", "UTF-16BE",
        "UTF-16LE", "US-ASCII", "ISO-8859-1"};

    /**
     * Creates new form FileOptionsDialog
     */
    public FileOptionsDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        initLabels();
        charsetSp.setModel(new SpinnerListModel(charsets));
        wCharsetSp.setModel(new SpinnerListModel(charsets));
        ((JSpinner.DefaultEditor) charsetSp.getEditor())
                .getTextField().setEditable(false);
        ((JSpinner.DefaultEditor) wCharsetSp.getEditor())
                .getTextField().setEditable(false);
    }

    private void initLabels() {
        columnSeparatorsL.setText(PropertiesLoader.getProperty(
                DataManager.R_COLUMN_BREAK));
        rowSeparatorsL.setText(PropertiesLoader.getProperty(
                DataManager.R_LINE_BREAK));
        nullValuesL.setText(PropertiesLoader.getProperty(
                DataManager.R_NULL_VALUES));
        literalEndL.setText(PropertiesLoader.getProperty(
                DataManager.R_LITERAL_END));
        literalStartL.setText(PropertiesLoader.getProperty(
                DataManager.R_LITERAL_START));
        columnSeparators.setText(PropertiesLoader.getProperty(
                DataManager.R_COLUMN_BREAK));
        rowSeparators.setText(PropertiesLoader.getProperty(
                DataManager.R_LINE_BREAK));
        nullValues.setText(PropertiesLoader.getProperty(
                DataManager.R_NULL_VALUES));
        literalEnd.setText(PropertiesLoader.getProperty(
                DataManager.R_LITERAL_END));
        literalStart.setText(PropertiesLoader.getProperty(
                DataManager.R_LITERAL_START));
        wColumnSeparatorsL.setText(PropertiesLoader.getProperty(
                DataManager.W_COLUMN_BREAK));
        wRowSeparatorsL.setText(PropertiesLoader.getProperty(
                DataManager.W_LINE_BREAK));
        wNullPlaceholderL.setText(PropertiesLoader.getProperty(
                DataManager.W_NULL));
        wLiteralEndL.setText(PropertiesLoader.getProperty(
                DataManager.W_LITERAL_END));
        wLiteralStartL.setText(PropertiesLoader.getProperty(
                DataManager.W_LITERAL_START));
        wColumnSeparators.setText(PropertiesLoader.getProperty(
                DataManager.W_COLUMN_BREAK));
        wRowSeparators.setText(PropertiesLoader.getProperty(
                DataManager.W_LINE_BREAK));
        wNullPlaceholder.setText(PropertiesLoader.getProperty(
                DataManager.W_NULL));
        wLiteralEnd.setText(PropertiesLoader.getProperty(
                DataManager.W_LITERAL_END));
        wLiteralStart.setText(PropertiesLoader.getProperty(
                DataManager.W_LITERAL_START));
    }

    private void saveProperties() {
        PropertiesLoader.addProperty(DataManager.R_COLUMN_BREAK,
                columnSeparators.getText());
        PropertiesLoader.addProperty(DataManager.R_LINE_BREAK,
                rowSeparators.getText());
        PropertiesLoader.addProperty(DataManager.R_NULL_VALUES,
                nullValues.getText());
        PropertiesLoader.addProperty(DataManager.R_LITERAL_END,
                literalEnd.getText());
        PropertiesLoader.addProperty(DataManager.R_LITERAL_START,
                literalStart.getText());
        PropertiesLoader.addProperty(DataManager.W_COLUMN_BREAK,
                wColumnSeparators.getText().replace("\\n", "\n"));
        PropertiesLoader.addProperty(DataManager.W_LINE_BREAK,
                wRowSeparators.getText().replace("\\n", "\n"));
        PropertiesLoader.addProperty(DataManager.W_NULL,
                wNullPlaceholder.getText().replace("\\n", "\n"));
        PropertiesLoader.addProperty(DataManager.W_LITERAL_START,
                wLiteralStart.getText().replace("\\n", "\n"));
        PropertiesLoader.addProperty(DataManager.W_LITERAL_END,
                wLiteralEnd.getText().replace("\\n", "\n"));
        PropertiesLoader.saveProperties();
    }

    public int display() {
        res = 1;
        initLabels();
        this.setVisible(true);
        return res;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        literalEnd = new javax.swing.JTextField();
        literalStart = new javax.swing.JTextField();
        nullValues = new javax.swing.JTextField();
        rowSeparators = new javax.swing.JTextField();
        columnSeparators = new javax.swing.JTextField();
        columnSeparatorsL = new javax.swing.JLabel();
        rowSeparatorsL = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        literalEndL = new javax.swing.JLabel();
        literalStartL = new javax.swing.JLabel();
        acceptB = new javax.swing.JToggleButton();
        cancelB = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        nullValuesL = new javax.swing.JLabel();
        nullPlaceholderL = new javax.swing.JLabel();
        charsetSp = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        wLiteralEnd = new javax.swing.JTextField();
        wLiteralStart = new javax.swing.JTextField();
        wNullPlaceholder = new javax.swing.JTextField();
        wRowSeparators = new javax.swing.JTextField();
        wColumnSeparators = new javax.swing.JTextField();
        wColumnSeparatorsL = new javax.swing.JLabel();
        wRowSeparatorsL = new javax.swing.JLabel();
        wLiteralStartL = new javax.swing.JLabel();
        wLiteralEndL = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        wCancelB = new javax.swing.JButton();
        wAcceptB = new javax.swing.JToggleButton();
        jLabel11 = new javax.swing.JLabel();
        wNullPlaceholderL = new javax.swing.JLabel();
        wCharsetSp = new javax.swing.JSpinner();

        jButton2.setText("Info");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(600, 450));
        setMinimumSize(new java.awt.Dimension(600, 450));
        setResizable(false);

        jPanel1.setMaximumSize(new java.awt.Dimension(600, 450));
        jPanel1.setMinimumSize(new java.awt.Dimension(600, 450));
        jPanel1.setPreferredSize(new java.awt.Dimension(600, 450));

        jLabel3.setText("Column Separators:");

        jLabel4.setText("Row Separators:");

        jLabel8.setText("Null Values:");

        jLabel12.setText("Literal Start:");

        jLabel15.setText("Literal End:");

        jLabel26.setText("Charset");

        columnSeparatorsL.setText(";");

        rowSeparatorsL.setText("\\n");

        jLabel6.setText("Defaults");

        literalEndL.setText("\"");

        literalStartL.setText("\"");

        acceptB.setText("Accept");
        acceptB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acceptBActionPerformed(evt);
            }
        });

        cancelB.setText("Cancel");
        cancelB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBActionPerformed(evt);
            }
        });

        jLabel2.setText("Information:");

        jLabel9.setText("To define multiple Strings in a field above seperate them by |, e.g. Null Values: ?|NULL|EMPTY ");

        jLabel13.setText("This works because the strings are treated as regural expressions. in certain cases this may");

        jLabel1.setText("result in unexpected behaviour so a quick study of regural expressions is suggested.");

        jLabel5.setText("To use | as a normal character use \\|, also \\\\ is required to use \\");

            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel3)
                                                .addComponent(jLabel4)
                                                .addComponent(jLabel8)
                                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel26))
                                            .addGap(18, 18, 18)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(literalStart, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                                                    .addComponent(literalEnd)
                                                    .addComponent(charsetSp))
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(rowSeparators, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                                                        .addComponent(columnSeparators)
                                                        .addComponent(nullValues))
                                                    .addGap(29, 29, 29)
                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(rowSeparatorsL, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(columnSeparatorsL, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(nullValuesL, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                            .addComponent(literalStartL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(literalEndL, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(jLabel6))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(nullPlaceholderL, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addComponent(jLabel5)
                                                    .addGap(34, 34, 34)
                                                    .addComponent(cancelB))
                                                .addComponent(jLabel1))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(acceptB)))
                                    .addGap(0, 109, Short.MAX_VALUE)))
                            .addContainerGap())))
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(12, 12, 12)
                            .addComponent(jLabel6)
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(columnSeparators, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(19, 19, 19)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(rowSeparators, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(19, 19, 19)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel8)
                                        .addComponent(nullValues, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(columnSeparatorsL)
                                    .addGap(19, 19, 19)
                                    .addComponent(rowSeparatorsL)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(nullValuesL, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(19, 19, 19)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(nullPlaceholderL, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel12)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(36, 36, 36)
                                            .addComponent(jLabel15))))))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(168, 168, 168)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(36, 36, 36)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(literalEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(literalEndL)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(literalStartL)
                                    .addComponent(literalStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(charsetSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel26))))
                    .addGap(18, 18, 18)
                    .addComponent(jLabel2)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel9)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel13)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cancelB)
                                .addComponent(acceptB))
                            .addGap(69, 69, 69))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel5)
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            );

            jTabbedPane1.addTab("File reading options", jPanel1);

            jLabel24.setText("Column Separators:");

            jLabel25.setText("Row Separators:");

            jLabel28.setText("Null Placeholder:");

            jLabel29.setText("Literal Start:");

            jLabel30.setText("Literal End:");

            jLabel31.setText("Charset");

            wColumnSeparatorsL.setText(",");

            wRowSeparatorsL.setText("\\n");

            wLiteralStartL.setText("\"");

            wLiteralEndL.setText("\"");

            jLabel43.setText("Defaults");

            jLabel45.setText("Information:");

            jLabel46.setText("You cannot define multiple Strings or regural expressions here. Any text entered in the fields above will be");

            wCancelB.setText("Cancel");
            wCancelB.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    wCancelBActionPerformed(evt);
                }
            });

            wAcceptB.setText("Accept");
            wAcceptB.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    wAcceptBActionPerformed(evt);
                }
            });

            jLabel11.setText("used literally");

            javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
            jPanel2.setLayout(jPanel2Layout);
            jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(wNullPlaceholderL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(wRowSeparatorsL, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(wColumnSeparatorsL, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(wLiteralStartL, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(wLiteralEndL, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel43))
                    .addGap(166, 166, 166))
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(187, 187, 187))
                                .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addContainerGap())
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel24)
                                        .addComponent(jLabel25)
                                        .addComponent(jLabel28)
                                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGap(18, 18, 18)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(wNullPlaceholder)
                                                .addComponent(wRowSeparators)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                    .addComponent(wColumnSeparators, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(0, 0, Short.MAX_VALUE))))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGap(18, 18, 18)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(wLiteralStart)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                    .addComponent(wLiteralEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(0, 0, Short.MAX_VALUE))
                                                .addComponent(wCharsetSp, javax.swing.GroupLayout.Alignment.TRAILING)))))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel31))
                                    .addGap(0, 0, Short.MAX_VALUE)))
                            .addGap(233, 233, 233))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel11)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(wCancelB)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(wAcceptB)
                            .addGap(105, 105, 105))))
            );
            jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel43)
                    .addGap(18, 18, 18)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel24)
                        .addComponent(wColumnSeparators, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(wColumnSeparatorsL))
                    .addGap(19, 19, 19)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel25)
                        .addComponent(wRowSeparators, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(wRowSeparatorsL))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(wNullPlaceholder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(wNullPlaceholderL, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel29)
                        .addComponent(wLiteralStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(wLiteralStartL))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel30)
                        .addComponent(wLiteralEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(wLiteralEndL))
                    .addGap(15, 15, 15)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel31)
                        .addComponent(wCharsetSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(48, 48, 48)
                    .addComponent(jLabel45)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jLabel46)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(wCancelB)
                        .addComponent(wAcceptB))
                    .addContainerGap(78, Short.MAX_VALUE))
            );

            jTabbedPane1.addTab("File writting options", jPanel2);

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jTabbedPane1)
            );

            jTabbedPane1.getAccessibleContext().setAccessibleName("");

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void acceptBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acceptBActionPerformed
        saveProperties();
        this.setVisible(false);
    }//GEN-LAST:event_acceptBActionPerformed

    private void wAcceptBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wAcceptBActionPerformed
        saveProperties();
        this.setVisible(false);
    }//GEN-LAST:event_wAcceptBActionPerformed

    private void cancelBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBActionPerformed
        res = 0;
        this.setVisible(false);
    }//GEN-LAST:event_cancelBActionPerformed

    private void wCancelBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wCancelBActionPerformed
        res = 0;
        this.setVisible(false);
    }//GEN-LAST:event_wCancelBActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton acceptB;
    private javax.swing.JButton cancelB;
    private javax.swing.JSpinner charsetSp;
    private javax.swing.JTextField columnSeparators;
    private javax.swing.JLabel columnSeparatorsL;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField literalEnd;
    private javax.swing.JLabel literalEndL;
    private javax.swing.JTextField literalStart;
    private javax.swing.JLabel literalStartL;
    private javax.swing.JLabel nullPlaceholderL;
    private javax.swing.JTextField nullValues;
    private javax.swing.JLabel nullValuesL;
    private javax.swing.JTextField rowSeparators;
    private javax.swing.JLabel rowSeparatorsL;
    private javax.swing.JToggleButton wAcceptB;
    private javax.swing.JButton wCancelB;
    private javax.swing.JSpinner wCharsetSp;
    private javax.swing.JTextField wColumnSeparators;
    private javax.swing.JLabel wColumnSeparatorsL;
    private javax.swing.JTextField wLiteralEnd;
    private javax.swing.JLabel wLiteralEndL;
    private javax.swing.JTextField wLiteralStart;
    private javax.swing.JLabel wLiteralStartL;
    private javax.swing.JTextField wNullPlaceholder;
    private javax.swing.JLabel wNullPlaceholderL;
    private javax.swing.JTextField wRowSeparators;
    private javax.swing.JLabel wRowSeparatorsL;
    // End of variables declaration//GEN-END:variables
}
