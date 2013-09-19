/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.hua.gui.DataMiner;

import gr.hua.weka_bridge.CloneableAttribute;
import gr.hua.weka_bridge.Trainer;
import java.util.ArrayList;
import java.util.Properties;
import weka.classifiers.Classifier;
import weka.core.Capabilities;

/**
 *
 * @author r0t0r
 */
public abstract class MiningMethodPanel extends javax.swing.JPanel {

    public static final int NAIVE_BAYES = 0;
    public static final int LOGISTIC_REGRESSION = 1;
    public static final int J48_TREE = 2;
    public static final int NEURAL_NETWORK = 3;
    public static final int JRIP = 4;
    public static final int SVM = 5;
    public static final int ROTATION_FOREST = 6;
    public static final int LINEAR_REGRESSION = 6;
    public static final int[] methods = {NAIVE_BAYES, LOGISTIC_REGRESSION, J48_TREE, NEURAL_NETWORK, JRIP, SVM, ROTATION_FOREST, LINEAR_REGRESSION};
    private static final NaiveBayesPanel nbayes = new NaiveBayesPanel();
    private static final LogisticRegressionPanel lregr = new LogisticRegressionPanel();
    private static final J48Panel j48 = new J48Panel();
    private static final NeuralNetworkPanel nnet = new NeuralNetworkPanel();
    private static final JRipPanel jrip = new JRipPanel();
    private static final LibSVMPanel svm = new LibSVMPanel();
    private static final LinearRegressionPanel mlregr = new LinearRegressionPanel();
    private static final RotationForestPanel rfrst = new RotationForestPanel();
    private static final MiningMethodPanel[] panels = {nbayes, lregr, j48, nnet, jrip, svm, rfrst};
    protected ArrayList<CloneableAttribute> attributes;
    protected CloneableAttribute target;
    protected Properties properties;
    protected String title;
    protected Classifier classifier;
    protected Trainer child;

    /**
     * Creates new form MiningMethodPanel
     */
    public static MiningMethodPanel getInstance(int code) {
        if (code >= panels.length || code < 0) {
            return null;
        }
        return panels[code];
    }

    protected MiningMethodPanel() {
        initComponents();
    }

    public boolean checked() {
        return nameCB.isSelected();
    }

    @Override
    public void setName(String s) {
        nameCB.setText(s);
    }

    public Object mine(ResultsArea out, ArrayList<CloneableAttribute> selAttributes, CloneableAttribute targetAttr, Properties flags) {
        MiningResultsPanel outputPanel = new MiningResultsPanel(this);
        attributes = selAttributes;
        target = targetAttr;
        properties = flags;
        out.add(outputPanel);
        outputPanel.setTitle(title);
        child = new Trainer(classifier, outputPanel, attributes, target, properties);
        child.start();
        return null;
    }

    public void stop() {
        child.stopMining();
    }

    protected abstract void setClassifier();

    public abstract String info();
    
    public boolean supportsNumeric() {
        return classifier.getCapabilities().handles(
                Capabilities.Capability.NUMERIC_CLASS) &&
                !classifier.getCapabilities().handles(
                Capabilities.Capability.NOMINAL_CLASS);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nameCB = new javax.swing.JCheckBox();
        additionalInfo = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setMaximumSize(new java.awt.Dimension(802, 28));
        setMinimumSize(new java.awt.Dimension(802, 28));
        setPreferredSize(new java.awt.Dimension(802, 28));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(nameCB, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(additionalInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nameCB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(additionalInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 3, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel additionalInfo;
    private javax.swing.JCheckBox nameCB;
    // End of variables declaration//GEN-END:variables
}
