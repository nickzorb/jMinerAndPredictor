package gr.hua.weka_bridge.classifiers;

import gr.hua.data_structures.basic.Measures;
import weka.classifiers.meta.RotationForest;

/**
 *
 * @author NickZorb
 */
public class JRotationForest extends JClassifier {

    public JRotationForest() {
        super(new RotationForest(), "Rotation Forest",
                new String[]{Measures.ROC_STATISTIC,
                    Measures.SUCCESS_PERCENTAGE,
                    Measures.KAPPA_STATISTIC},
                new String[]{}, "This is a meta classifier that uses the C4.5 "
                        + "classifier as its base.");
    }
    
}
