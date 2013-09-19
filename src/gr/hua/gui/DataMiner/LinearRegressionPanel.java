package gr.hua.gui.DataMiner;

import weka.classifiers.functions.LinearRegression;

public class LinearRegressionPanel extends MiningMethodPanel {

    public LinearRegressionPanel() {
        setName("Linear Regression");
        title = "Linear Regression";
        setClassifier();
    }

    @Override
    public String info() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void setClassifier() {
        classifier = new LinearRegression();
    }
}
