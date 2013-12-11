/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */

package gr.hua.weka_bridge.classifiers;

import gr.hua.data_structures.basic.Measures;
import weka.classifiers.functions.LibSVM;

/**
 *
 * @author NickZorb
 */
public class JLibSVM extends JClassifier {

    public JLibSVM() {
        super(new  LibSVM(), "SVM",
                new String[]{Measures.ROC_STATISTIC,
                    Measures.SUCCESS_PERCENTAGE,
                    Measures.KAPPA_STATISTIC},
                new String[]{}, "A support vector machine algorithm "
                        + "implementation provided by Chih-Chung Chang"
                        + " and Chih-Jen Lin");
    }
}
