package gr.hua.gui.DataMiner;

import weka.classifiers.trees.J48;

public class J48Panel extends MiningMethodPanel {
    
    public J48Panel() {
        setName("C4.5");
        title = "C4.5";
    }

    @Override
    public String info() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void setClassifier() {
        classifier = new J48();
    }
}
