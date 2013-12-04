package gr.hua.experiments.classifiers;

import weka.classifiers.Classifier;
import weka.core.Instances;
import weka.core.OptionHandler;
import weka.core.Randomizable;
import weka.core.WeightedInstancesHandler;

public class nonLinearNeuralNetwork extends Classifier
        implements OptionHandler, WeightedInstancesHandler, Randomizable {

    private static final long serialVersionUID = 6102300921865532295L;
    
    public static void main(String [] argv) {
        runClassifier(new nonLinearNeuralNetwork(), argv);
    }
    
    @Override
    public void buildClassifier(Instances data) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setSeed(int seed) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getSeed() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
