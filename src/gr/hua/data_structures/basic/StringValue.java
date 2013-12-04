package gr.hua.data_structures.basic;

import weka.core.Instance;

public class StringValue extends DataValue<String> implements Cloneable{

    public StringValue(String value) {
        super(value);
    }

    private StringValue(DataValue<String> oldValue) {
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
    public DataValue<String> clone() {
        return new StringValue(this);
    }

    @Override
    public void setValue(String value) {
        curValue = value;
    }

    @Override
    public Double getDoubleValue() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
