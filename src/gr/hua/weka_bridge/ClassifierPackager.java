package gr.hua.weka_bridge;

import gr.hua.data_structures.Statistics;
import java.io.Serializable;
import weka.classifiers.Classifier;

/**
 *
 * @author NickZorb
 */
public class ClassifierPackager implements Serializable {

    private Classifier classifier;
    private Statistics[] statistics;

    public ClassifierPackager(Classifier clsf, Statistics[] stats) {
        classifier = clsf;
        statistics = stats;
    }
}
