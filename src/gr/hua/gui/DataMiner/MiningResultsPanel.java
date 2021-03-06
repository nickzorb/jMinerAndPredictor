/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.hua.gui.DataMiner;

import gr.hua.weka_bridge.CloneableAttribute;
import gr.hua.weka_bridge.Trainer;
import java.awt.BorderLayout;
import java.util.Arrays;
import weka.core.FastVector;

/**
 *
 * @author r0t0r
 */
public class MiningResultsPanel extends javax.swing.JPanel {

    private final Object lock = new Object();
    private MiningMethodPanel parent;
    private int status = Trainer.RUNNING;

    /**
     * Creates new form MiningResultsPanel
     */
    public MiningResultsPanel(MiningMethodPanel parent) {
        this.parent = parent;
        initComponents();
    }

    public void setTitle(String s) {
        title.setText(s);
    }

    public void updateText(String s) {
        textArea.setText(s);
    }

    public void updateAttributes(FastVector attributes) {
        StringBuilder text = new StringBuilder("Currently used attributes:\n");
        for (CloneableAttribute a : Arrays.copyOf(attributes.toArray(), attributes.size(), CloneableAttribute[].class)) {
            text.append("\t' ").append(a.name()).append(" '\n");
        }
        attributeArea.setText(text.toString());
    }

    public void showFinishedScreen(FastVector[] attributes, double[] results,
            String[] descriptions, String[] classifiers, String statistic) {
        FinalResultsPanel rp = new FinalResultsPanel(classifiers, results,
                attributes, descriptions, statistic);
        removeAll();
        setLayout(new BorderLayout());
        add(rp);
        validate();
        repaint();
    }

    public Object getLock() {
        return lock;
    }

    public int getStatus() {
        return status;
    }

    public void stopped() {
        jButton1.setText("CONTINUE");
        jButton1.setEnabled(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        attributeArea = new javax.swing.JTextArea();
        title = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        setMaximumSize(new java.awt.Dimension(200, 400));
        setMinimumSize(new java.awt.Dimension(200, 400));
        setPreferredSize(new java.awt.Dimension(200, 400));

        jScrollPane1.setMinimumSize(new java.awt.Dimension(196, 400));
        jScrollPane1.setPreferredSize(null);

        textArea.setColumns(20);
        textArea.setRows(5);
        textArea.setEnabled(false);
        textArea.setMaximumSize(null);
        textArea.setMinimumSize(null);
        textArea.setPreferredSize(null);
        jScrollPane1.setViewportView(textArea);

        jScrollPane2.setMinimumSize(new java.awt.Dimension(196, 200));
        jScrollPane2.setPreferredSize(null);

        attributeArea.setColumns(20);
        attributeArea.setRows(5);
        attributeArea.setEnabled(false);
        attributeArea.setMaximumSize(null);
        attributeArea.setMinimumSize(new java.awt.Dimension(0, 0));
        attributeArea.setPreferredSize(null);
        jScrollPane2.setViewportView(attributeArea);

        title.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jButton1.setText("Stop");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(title, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 178, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (status == Trainer.RUNNING) {
            status = Trainer.STOPPED;
            jButton1.setText("now stopping");
            jButton1.setEnabled(false);
        } else if (status == Trainer.STOPPED) {
            status = Trainer.RUNNING;
            synchronized (lock) {
                lock.notify();
            }
            jButton1.setText("STOP");
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea attributeArea;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea textArea;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
