package gr.hua.gui.DataMiner;

import weka.classifiers.meta.RotationForest;

public class RotationForestPanel extends MiningMethodPanel {

    public RotationForestPanel() {
        setName("Rotation Forest");
        title = "Rotation Forest";
        setClassifier();
    }

    @Override
    public String info() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void setClassifier() {
        classifier = new RotationForest();
    }
}