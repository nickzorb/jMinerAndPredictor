package gr.hua.gui.DataMiner;

import weka.classifiers.functions.LibSVM;

public class LibSVMPanel extends MiningMethodPanel {

    public LibSVMPanel() {
        setName("SVM");
        title = "SVM";
        setClassifier();
    }

    @Override
    public String info() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void setClassifier() {
        classifier = new LibSVM();
    }
}
