package gr.hua.data_structures;

import gr.hua.gui.MainMenu;
import gr.hua.utils.Logger;
import javax.swing.JOptionPane;
import weka.core.Instance;

public class IntegerValue extends ColumnValue<Integer> implements Cloneable {

    public IntegerValue(Integer value) {
        super(value);
    }

    private IntegerValue(ColumnValue<Integer> oldValue) {
        super(oldValue);
    }

    @Override
    public boolean equals(Value<Integer> oldValue) {
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
                ins.setValue(attribute, curValue.intValue());
            }
        }
    }

    @Override
    public ColumnValue<Integer> clone() {
        return new IntegerValue(this);
    }

    @Override
    public void setValue(String newValue) {
        try {
            setValue(Integer.parseInt(newValue));
        } catch (Exception e) {
            Logger.logException(e);
            JOptionPane.showMessageDialog(MainMenu.main, "Value " + newValue
                    + " not valid for this Integer column");
        }
    }

    @Override
    public void setValue(Integer value) {
        curValue = value;
    }

    @Override
    public String getStringValue() {
        return curValue.toString();
    }

    @Override
    public double getDoubleValue() {
        return (double) curValue.intValue();
    }
}
