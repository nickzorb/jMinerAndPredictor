package gr.hua.weka_bridge.classifiers;

import gr.hua.data_structures.basic.Measures;
import weka.classifiers.trees.J48;

/**
 *
 * @author NickZorb
 */
public class JC4_5 extends JClassifier {

    public JC4_5() {
        super(new  J48(), "C4.5",
                new String[]{Measures.ROC_STATISTIC,
                    Measures.SUCCESS_PERCENTAGE,
                    Measures.KAPPA_STATISTIC},
                new String[]{}, "This is an implementation of the C4.5 tree"
                        + "algorithm based on information entropy.");
    }
    
}
