package gr.hua.weka_bridge.classifiers;

import gr.hua.data_structures.basic.Measures;
import weka.classifiers.functions.Logistic;

/**
 *
 * @author NickZorb
 */
public class JLogisticRegression extends JClassifier {

    public JLogisticRegression() {
        super(new  Logistic(), "Logistic Regression",
                new String[]{Measures.ROC_STATISTIC,
                    Measures.SUCCESS_PERCENTAGE,
                    Measures.KAPPA_STATISTIC},
                new String[]{}, "A logistic regression implementation.");
    }
}
