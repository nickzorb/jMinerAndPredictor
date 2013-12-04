package gr.hua.weka_bridge;

import gr.hua.data_structures.Statistics;
import java.io.Serializable;
import weka.classifiers.Classifier;
import weka.core.FastVector;

public class JClassifier implements Serializable {
    private Classifier classifier;
    private FastVector attributes;
    private Statistics statistics;
    
    public JClassifier(Classifier clsf, FastVector atts, Statistics stats) {
        classifier = clsf;
        attributes = atts;
        statistics = stats;
    }
}
