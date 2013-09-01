package gr.hua.weka_bridge;

import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;


public interface InstanceGenerator {

    public Instance getInstance(FastVector attributes, Instances dataset);
    
}
