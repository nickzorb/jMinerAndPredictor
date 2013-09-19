package gr.hua.gui.DataMiner;

import weka.classifiers.rules.JRip;

public class JRipPanel extends MiningMethodPanel {

    public JRipPanel() {
        setName("JRip");
        title = "JRip";
        setClassifier();
    }

    @Override
    public String info() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void setClassifier() {
        classifier = new JRip();
    }
}
