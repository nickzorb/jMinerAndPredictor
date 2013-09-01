package gr.hua.gui.DataMiner;

import javax.swing.JList;


public class JListItem {
    
    private String label;
    private int index;
    private Object value;
    private JList parent;

    public JListItem(String label, int index, Object value, JList parent) {
        this.label = label;
        this.index = index;
        this.value = value;
        this.parent = parent;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public JList getParent() {
        return parent;
    }

    public void setParent(JList parent) {
        this.parent = parent;
    }
    
    @Override
    public String toString() {
        return label;
    }
    
}
