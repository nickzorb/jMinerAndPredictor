package gr.hua.data_structures;

import weka.core.Instance;

public class StringValue extends ColumnValue<String> implements Cloneable{

    public StringValue(String value) {
        super(value);
    }

    private StringValue(ColumnValue<String> oldValue) {
        super(oldValue);
    }

    @Override
    public boolean equals(Value<String> oldValue) {
        return curValue.equals(oldValue.getValue());
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
    public ColumnValue<String> clone() {
        return new StringValue(this);
    }
}
