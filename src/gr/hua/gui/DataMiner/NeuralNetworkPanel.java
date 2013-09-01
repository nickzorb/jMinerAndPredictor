package gr.hua.gui.DataMiner;

import weka.classifiers.functions.MultilayerPerceptron;

public class NeuralNetworkPanel extends MiningMethodPanel {

    public NeuralNetworkPanel() {
        setName("Multilayer Perceptron");
        title = "Multilayer Perceptron";
    }

    @Override
    public String info() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void setClassifier() {
        classifier = new MultilayerPerceptron();
    }
}
