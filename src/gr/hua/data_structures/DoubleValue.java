package gr.hua.data_structures;

import weka.core.Instance;

public class DoubleValue extends ColumnValue<Double> implements Cloneable {

    private DoubleValue(ColumnValue<Double> oldValue) {
        super(oldValue);
    }

    @Override
    public boolean equals(Value<Double> oldValue) {
        return curValue == oldValue.getValue();
    }

    @Override
    public ColumnValue<Double> clone() {
        return new DoubleValue(this);
    }

    @Override
    public void populateInstance(Instance ins, int attribute) {
        ins.setValue(attribute, curValue);
    }
}
