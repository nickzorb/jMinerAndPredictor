package gr.hua.data_structures;

import weka.core.Instance;

public class IntegerValue extends ColumnValue<Integer> implements Cloneable {

    private IntegerValue(ColumnValue<Integer> oldValue) {
        super(oldValue);
    }

    @Override
    public boolean equals(Value<Integer> oldValue) {
        return curValue == oldValue.getValue();
    }

    @Override
    public IntegerValue clone() {
        return new IntegerValue(this);
    }

    @Override
    public void populateInstance(Instance ins, int attribute) {
        ins.setValue(attribute, curValue);
    }
}
