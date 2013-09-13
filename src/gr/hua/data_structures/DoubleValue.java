package gr.hua.data_structures;

import weka.core.Instance;

public class DoubleValue extends ColumnValue<Double> implements Cloneable {

    public DoubleValue(Double value) {
        super(value);
    }
    
    private DoubleValue(ColumnValue<Double> oldValue) {
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
            ins.setValue(attribute, curValue);
        }
    }
    
    @Override
    public ColumnValue<Double> clone() {
        return new DoubleValue(this);
    }
}
