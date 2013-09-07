package gr.hua.gui.DataMiner;

import weka.classifiers.functions.Logistic;

public class LogisticRegressionPanel extends MiningMethodPanel {

    public LogisticRegressionPanel() {
        setName("Logistic Regression");
        title = "Logistic Regression";
    }

    @Override
    public String info() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void setClassifier() {
        classifier = new Logistic();
    }
}
