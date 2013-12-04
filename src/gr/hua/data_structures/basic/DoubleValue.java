package gr.hua.data_structures.basic;

import gr.hua.gui.MainMenu;
import gr.hua.utils.Logger;
import javax.swing.JOptionPane;
import weka.core.Instance;

public class DoubleValue extends DataValue<Double> implements Cloneable {

    public DoubleValue(Double value) {
        super(value);
    }
    
    private DoubleValue(DataValue<Double> oldValue) {
        super(oldValue);
    }
    
    @Override
    public boolean equals(Value<Double> oldValue) {
        return curValue == oldValue.getValue();
    }

    @Override
    public void populateInstance(Instance ins, int attribute) {
        if (curValue == null) {
            ins.setMissing(attribute);
        } else {
            if (ins.attribute(attribute).isNominal()) {
                ins.setValue(attribute, curValue.toString());
            } else {
                ins.setValue(attribute, curValue.doubleValue());
            }
        }
    }
    
    @Override
    public DataValue<Double> clone() {
        return new DoubleValue(this);
    }
    
    @Override
    public void setValue(String newValue) {
        try {
            setValue(Double.parseDouble(newValue));
        } catch (Exception e) {
            Logger.logException(e);
            JOptionPane.showMessageDialog(MainMenu.main, "Value " + newValue
                    + " not valid for this Double column");
        }
    }

    @Override
    public void setValue(Double value) {
        curValue = value;
    }

    @Override
    public Double getDoubleValue() {
        if (curValue == null) {
            return Double.NEGATIVE_INFINITY;
        }
        return curValue;
    }
}
