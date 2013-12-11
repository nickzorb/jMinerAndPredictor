package gr.hua.weka_bridge.classifiers;

import java.io.Serializable;
import weka.classifiers.Classifier;

public abstract class JClassifier implements Serializable {

    private final Classifier classifier;
    private final String name;
    private final String info;
    private final String[] supportedMeasurements;
    private final String[] removedOptions;
    private final Class[] classifiers = {JC4_5.class, JLibSVM.class,
        JLogisticRegression.class, JNaiveBayes.class, JNeuralNetwork.class,
        JRipper.class, JRotationForest.class};

    public static final JClassifier[] CLASSIFIERS = {};

    protected JClassifier(Classifier classifier, String name,
            String[] supportedMeasurements, String[] removedOptions,
            String info) {
        this.classifier = classifier;
        this.name = name;
        this.supportedMeasurements = supportedMeasurements;
        this.removedOptions = removedOptions;
        this.info = info;
    }

    public Classifier getClassifier() {
        return classifier;
    }

    public String getName() {
        return name;
    }

    public String[] getSupportedMeasurements() {
        return supportedMeasurements;
    }

    public String getInfo() {
        return info;
    }

}
