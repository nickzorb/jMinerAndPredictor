package gr.hua.gui.DataMiner;

import gr.hua.utils.Logger;
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
        String[] options = {
            "-N 1000"
        };
        try {
            classifier.setOptions(options);
        } catch (Exception ex) {
            Logger.logException(ex);
        }
    }
}
