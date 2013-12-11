package gr.hua.weka_bridge.classifiers;

import gr.hua.data_structures.basic.Measures;
import weka.classifiers.rules.JRip;

/**
 *
 * @author NickZorb
 */
public class JRipper extends JClassifier {

    public JRipper() {
        super(new  JRip(), "RIPPER",
                new String[]{Measures.ROC_STATISTIC,
                    Measures.SUCCESS_PERCENTAGE,
                    Measures.KAPPA_STATISTIC},
                new String[]{}, "This is a tree algorithm we advanced prunning "
                        + "methods aimed to maximize its efficiency.");
    }
}
