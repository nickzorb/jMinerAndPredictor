package gr.hua.data_manipulation;

import java.io.Serializable;

public class Change implements Serializable, Cloneable {
    
    private String value_affected;
    private String new_value;

    public Change(String value_affected, String new_value) {
        this.value_affected = value_affected;
        this.new_value = new_value;
    }
    
    private Change(Change c) {
        value_affected = c.value_affected;
        new_value = c.new_value;
    }

    public String getValue_affected() {
        return value_affected;
    }

    public void setValue_affected(String value_affected) {
        this.value_affected = value_affected;
    }

    public String getNew_value() {
        return new_value;
    }

    public void setNew_value(String new_value) {
        this.new_value = new_value;
    }

    @Override
    public Change clone() {
        return new Change(this);
    }
    
}
