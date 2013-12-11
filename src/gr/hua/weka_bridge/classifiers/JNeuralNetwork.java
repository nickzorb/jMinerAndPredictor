package gr.hua.weka_bridge.classifiers;

import gr.hua.data_structures.basic.Measures;
import weka.classifiers.functions.MultilayerPerceptron;

/**
 *
 * @author NickZorb
 */
public class JNeuralNetwork extends JClassifier {

    public JNeuralNetwork() {
        super(new MultilayerPerceptron(), "Neural Network",
                new String[]{Measures.ROC_STATISTIC,
                    Measures.SUCCESS_PERCENTAGE,
                    Measures.KAPPA_STATISTIC,
                    Measures.CORRELATION_COEFICIENT},
                new String[]{}, "A multilayered perceptron implementation.");
    }

}
