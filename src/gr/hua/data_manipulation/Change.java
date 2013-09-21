package gr.hua.data_manipulation;

import java.io.Serializable;

public class Change implements Serializable, Cloneable {

    private String valueAffected;
    private String newValue;

    public Change(String valueAffected, String newValue) {
        this.valueAffected = valueAffected;
        this.newValue = newValue;
    }

    private Change(Change c) {
        valueAffected = c.valueAffected;
        newValue = c.newValue;
    }

    public String getValueAffected() {
        return valueAffected;
    }

    public void setValueAffected(String value_affected) {
        this.valueAffected = value_affected;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String new_value) {
        this.newValue = new_value;
    }

    @Override
    public Change clone() {
        return new Change(this);
    }
}
