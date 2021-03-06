package gr.hua.data_structures;

import gr.hua.data_manipulation.Action;
import gr.hua.data_manipulation.ActionHandler;
import gr.hua.gui.MainMenu;
import gr.hua.weka_bridge.CloneableAttribute;
import gr.hua.weka_bridge.InstanceGenerator;
import java.util.ArrayList;
import java.util.Arrays;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;

public class DataRow implements Row<ColumnValue>, ActionHandler,
        InstanceGenerator, Cloneable {

    private ArrayList<ColumnValue> values;

    public DataRow() {
        values = new ArrayList();
    }

    private DataRow(DataRow r) {
        values = new ArrayList();
        for (ColumnValue c : r.values) {
            values.add(c.clone());
        }
    }

    @Override
    public void remove(int i) {
        values.remove(i);
    }

    @Override
    public void remove(ColumnValue d) {
        values.remove(d);
    }

    @Override
    public void add(ColumnValue d) {
        values.add(d);
    }

    @Override
    public void add(int i, ColumnValue d) {
        values.add(i, d);
    }

    @Override
    public ColumnValue get(int i) {
        return values.get(i);
    }

    @Override
    public int size() {
        return values.size();
    }

    @Override
    public int columnOf(ColumnValue d) {
        return values.indexOf(d);
    }

    @Override
    public void replace(ColumnValue old, ColumnValue replacement) {
        if (values.contains(old)) {
            values.add(values.indexOf(old), replacement);
            values.remove(old);
        }
    }

    @Override
    public String[] toArray() {
        String[] res = new String[values.size()];
        for (ColumnValue c : values) {
            if (c.isNull()) {
                res[values.indexOf(c)] = "";
            } else {
                res[values.indexOf(c)] = c.getValue().toString();                
            }
        }
        return res;
    }

    @Override
    public double nullPercentage() {
        double nulls = 0;
        for (ColumnValue c : values) {
            if (c.isNull()) {
                nulls++;
            }
        }
        return (nulls / values.size()) * 100;
    }

    @Override
    public String info() {
        StringBuilder res = new StringBuilder();
        res.append("Columns: ").append(values.size()).append("\n");
        res.append("Null Percentage: ").append(nullPercentage());
        return res.toString();
    }

    @Override
    public int applyAction(Action a) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Action[] getCounterActions() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Instance getInstance(FastVector attributes, Instances dataset) {
        Instance res = new Instance(attributes.size());
        res.setDataset(dataset);
        int attributeId = 0;
        for (CloneableAttribute attr : Arrays.copyOf(attributes.toArray(), attributes.size(), CloneableAttribute[].class)) {
            int columnIndex = MainMenu.MANAGER.findColumn(attr.name());
            ColumnValue c = values.get(columnIndex);
            c.populateInstance(res, attributeId);
            attributeId++;
        }
        return res;
    }

    @Override
    public DataRow clone() {
        return new DataRow(this);
    }
}
