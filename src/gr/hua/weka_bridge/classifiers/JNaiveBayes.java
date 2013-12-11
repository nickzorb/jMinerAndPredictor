package gr.hua.weka_bridge.classifiers;

import gr.hua.data_structures.basic.Measures;
import weka.classifiers.bayes.NaiveBayes;

/**
 *
 * @author NickZorb
 */
public class JNaiveBayes  extends JClassifier {

    public JNaiveBayes() {
        super(new NaiveBayes(), "Naive Bayes",
                new String[]{Measures.ROC_STATISTIC,
                    Measures.SUCCESS_PERCENTAGE,
                    Measures.KAPPA_STATISTIC},
                new String[]{}, "An implementation of the Naive Bayes algorithm"
                        + ".");
    }
    
}
