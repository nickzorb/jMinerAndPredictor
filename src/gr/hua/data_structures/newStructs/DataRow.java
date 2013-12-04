package gr.hua.data_structures.newStructs;

import gr.hua.data_manipulation.Action;
import gr.hua.data_manipulation.ActionHandler;
import gr.hua.data_structures.Row;
import gr.hua.data_structures.basic.DataValue;
import gr.hua.gui.MainMenu;
import gr.hua.utils.Logger;
import gr.hua.weka_bridge.CloneableAttribute;
import gr.hua.weka_bridge.InstanceGenerator;
import java.util.ArrayList;
import java.util.Arrays;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;

/**
 *
 * @author NickZorb
 */
public class DataRow implements Row<DataValue>, ActionHandler,
        InstanceGenerator, Cloneable {

    private final ArrayList<DataValue> data;

    public DataRow(ArrayList<DataValue> data) {
        this.data = data;
    }

    @Override
    public int applyAction(Action a) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Instance getInstance(FastVector attributes, Instances dataset) {
        Instance res = new Instance(attributes.size());
        res.setDataset(dataset);
        int attributeId = 0;
        for (int i = 0; i < attributes.size(); i++) {
            CloneableAttribute attr = (CloneableAttribute) attributes.elementAt(i);
            int columnIndex = MainMenu.MANAGER.findColumn(attr.name());
            DataValue c = get(columnIndex);
            c.populateInstance(res, attributeId);
            attributeId++;
        }
        return res;
    }

    @Override
    public void remove(int i) {
        try {
            data.remove(i);
        } catch (Exception e) {
            Logger.logException(e);
        }
    }

    @Override
    public void remove(DataValue d) {
        data.remove(d);
    }

    @Override
    public void add(DataValue d) {
        data.add(d);
    }

    @Override
    public void add(int i, DataValue d) {
        try {
            data.add(i, d);
        } catch (Exception e) {
            Logger.logException(e);
        }
    }

    @Override
    public DataValue get(int i) {
        try {
            return data.get(i);
        } catch (Exception e) {
            Logger.logException(e);
            return null;
        }
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public int columnOf(DataValue d) {
        return data.indexOf(d);
    }

    @Override
    public void replace(DataValue t, DataValue r) {
        if (data.contains(t)) {
            data.add(data.indexOf(t), r);
            data.remove(t);
        }
    }

    @Override
    public String[] toArray() {
        String[] res = new String[data.size()];
        for (DataValue c : data) {
            res[data.indexOf(c)] = c.getStringValue();
        }
        return res;
    }

    @Override
    public double nullPercentage() {
        double count = 0;
        for (DataValue c : data) {
            if (c.isNull()) {
                count++;
            }
        }
        return count * 100 / data.size();
    }

    @Override
    public String info() {
        StringBuilder res = new StringBuilder();
        res.append("Columns: ").append(data.size()).append("\n");
        res.append("Null Percentage: ").append(nullPercentage());
        return res.toString();
    }

}
