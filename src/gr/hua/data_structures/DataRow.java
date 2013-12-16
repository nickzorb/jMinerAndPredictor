/* 
 * Copyright (C) 2013 Harokopio University <Nikolaos Zormpas>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package gr.hua.data_structures;

import gr.hua.data_structures.basic.DataValue;
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

public class DataRow implements Row<DataValue>, ActionHandler,
        InstanceGenerator, Cloneable {

    private ArrayList<DataValue> values;

    public DataRow() {
        values = new ArrayList();
    }

    private DataRow(DataRow r) {
        values = new ArrayList();
        for (DataValue c : r.values) {
            values.add(c.clone());
        }
    }

    @Override
    public void remove(int i) {
        values.remove(i);
    }

    @Override
    public void remove(DataValue d) {
        values.remove(d);
    }

    @Override
    public void add(DataValue d) {
        values.add(d);
    }

    @Override
    public void add(int i, DataValue d) {
        values.add(i, d);
    }

    @Override
    public DataValue get(int i) {
        return values.get(i);
    }

    @Override
    public int size() {
        return values.size();
    }

    @Override
    public int columnOf(DataValue d) {
        return values.indexOf(d);
    }

    @Override
    public void replace(DataValue old, DataValue replacement) {
        if (values.contains(old)) {
            values.add(values.indexOf(old), replacement);
            values.remove(old);
        }
    }

    @Override
    public String[] toArray() {
        String[] res = new String[values.size()];
        for (DataValue c : values) {
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
        for (DataValue c : values) {
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
    public Instance getInstance(FastVector attributes, Instances dataset) {
        Instance res = new Instance(attributes.size());
        res.setDataset(dataset);
        int attributeId = 0;
        for (CloneableAttribute attr : Arrays.copyOf(attributes.toArray(), attributes.size(), CloneableAttribute[].class)) {
            int columnIndex = MainMenu.MANAGER.findColumn(attr.name());
            DataValue c = values.get(columnIndex);
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
