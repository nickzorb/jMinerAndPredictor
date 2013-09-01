package gr.hua.gui.DataMiner;

import weka.classifiers.bayes.NaiveBayes;

public class NaiveBayesPanel extends MiningMethodPanel {

    public NaiveBayesPanel() {
        setName("Naive Bayes");
        title = "Naive Bayes";
    }

    @Override
    public String info() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void setClassifier() {
        classifier = new NaiveBayes();
    }
}
