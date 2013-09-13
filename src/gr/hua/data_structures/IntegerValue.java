package gr.hua.data_structures;

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
            ins.setValue(attribute, curValue);
        }
    }
    
    @Override
    public ColumnValue<Integer> clone() {
        return new IntegerValue(this);
    }
}
