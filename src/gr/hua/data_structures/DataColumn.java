package gr.hua.data_structures;

import gr.hua.data_manipulation.Action;
import gr.hua.data_manipulation.ActionHandler;
import gr.hua.weka_bridge.AttributeGenerator;
import gr.hua.weka_bridge.CloneableAttribute;
import java.util.ArrayList;
import weka.core.FastVector;

public class DataColumn<T extends ColumnValue> implements Column<T>, ActionHandler,
        AttributeGenerator, Cloneable {

    private String name;
    private ArrayList<ColumnValue> values;
    private Class type;
    private AllowedValues allowedValues;

    public DataColumn(String name) {
        this.name = name;
        values = new ArrayList();
        type = String.class;
        allowedValues = new StringAllowedValues();
    }

    private DataColumn(DataColumn<T> old) {
        name = old.name;
        type = old.type;
        values = new ArrayList();
        for (ColumnValue c : old.values) {
            values.add(c.clone());
        }
        allowedValues = old.allowedValues.clone();
    }

    public void increment(int i) {
        values.get(i).alterPopulation(1);
    }

    public ColumnValue get(int i) {
        return values.get(i);
    }

    @Override
    public void setName(String sName) {
        name = sName;
    }

    @Override
    public AllowedValues getAllowedValues() {
        return allowedValues;
    }

    @Override
    public void setAllowedValues(AllowedValues values) {
        allowedValues = values;
    }

    @Override
    public void revalidate() {
        for (ColumnValue c : values) {
            if (!allowedValues.isAllowed(c)) {
                //TODO SHOW ERROR MESSAGE
            }
        }
    }

    @Override
    public String[] getValues() {
        String[] res = new String[values.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = values.get(i).getValue().toString();
        }
        return res;
    }

    @Override
    public int size() {
        return values.size();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void add(ColumnValue<T> data) {
        values.add(data);
    }

    @Override
    public int find(String value) {
        if (value == null) {
            return -1;
        }
        for (int i = 0; i < values.size(); i++) {
            if (value.equals(values.get(i).getValue())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public double nullPercentage() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String info() {
        //TODO INFO
        return "This is some info! (that ! was logical not punctuation)";
    }

    @Override
    public int applyAction(Action a) {
        //TODO APPLY ACTION
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Action[] getCounterActions() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public CloneableAttribute getAttribute() {
        FastVector nominal_values = new FastVector(values.size());
        for (ColumnValue c : values) {
            String curValue = c.getValue() == null ? "" : c.getValue().toString();
            if (!nominal_values.contains(curValue)) {
                nominal_values.addElement(curValue);
            }
        }
        CloneableAttribute res = new CloneableAttribute(name, nominal_values);
        return res;
    }

    @Override
    public DataColumn<T> clone() {
        return new DataColumn(this);
    }
}
