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
import gr.hua.data_structures.basic.IntegerValue;
import gr.hua.data_structures.basic.DoubleValue;
import gr.hua.data_manipulation.Action;
import gr.hua.data_manipulation.ActionHandler;
import gr.hua.gui.MainMenu;
import gr.hua.utils.NumParser;
import gr.hua.weka_bridge.AttributeGenerator;
import gr.hua.weka_bridge.CloneableAttribute;
import java.util.ArrayList;
import weka.core.FastVector;

public class DataColumn<T extends DataValue> implements Column<T>, ActionHandler,
        AttributeGenerator, Cloneable {

    public static final double THRESHOLD = 98.0 / 100.0;
    private String name;
    private ArrayList<DataValue> values;
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
        for (DataValue c : old.values) {
            values.add(c.clone());
        }
        allowedValues = old.allowedValues.clone();
    }

    public Class getType() {
        return type;
    }

    public void setType(Class c) {
        type = c;
    }

    public void increment(int i) {
        values.get(i).alterPopulation(1);
    }

    public DataValue get(int i) {
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
        if (type == String.class) {
            checkIfNumerical();
        } //else {
//            for (DataValue cv : values) {
//                if (!allowedValues.isAllowed(cv)) {
//                    //TODO SHOW ERROR MESSAGE
//                }
//            }
//        }
    }

    private void checkIfNumerical() {
        int intCount = 0;
        int doubleCount = 0;
        for (DataValue cv : values) {
            if (cv.isNull()) {
                intCount++;
                doubleCount++;
                continue;
            }
            Integer tempInt = NumParser.parseInt(cv.getValue().toString());
            Double tempDouble = NumParser.parseDouble(cv.getValue().toString());
            if (tempInt != null) {
                intCount++;
            }
            if (tempDouble != null) {
                doubleCount++;
            }
        }
        if (intCount > values.size() * THRESHOLD && intCount >= doubleCount) {
            toInteger();
        } else if (doubleCount > values.size() * THRESHOLD) {
            toDouble();
        }
    }

    private void toInteger() {
        type = Integer.class;
        ArrayList<DataValue> newValues = new ArrayList();
        for (DataValue cv : values) {
            DataValue newValue = null;
            Integer temp = null;
            if (!cv.isNull()) {
                temp = NumParser.parseInt(cv.getValue().toString());
            }
            if (temp == null) {
                for (DataValue ncv : newValues) {
                    if (ncv.isNull()) {
                        newValue = ncv;
                        ncv.alterPopulation(cv.getPopulation());
                        break;
                    }
                }
                if (newValue == null) {
                    newValue = new IntegerValue(null);
                    newValue.setPopulation(cv.getPopulation());
                    newValues.add(newValue);
                }
            } else {
                for (DataValue ncv : newValues) {
                    if (ncv.isNull()) {
                        continue;
                    }
                    if (ncv.getValue().toString().equals(temp.toString())) {
                        ncv.alterPopulation(cv.getPopulation());
                        newValue = ncv;
                        break;
                    }
                }
                if (newValue == null) {
                    newValue = new IntegerValue(temp);
                    newValue.setPopulation(cv.getPopulation());
                    newValues.add(newValue);
                }
            }
            for (int i = 0; i < MainMenu.MANAGER.getNumberOfRows(); i++) {
                MainMenu.MANAGER.get(i).replace(cv, newValue);
            }
        }
        values = newValues;
    }

    private void toDouble() {
        type = Double.class;
        ArrayList<DataValue> newValues = new ArrayList();
        for (DataValue cv : values) {
            DataValue newValue = null;
            Double temp = null;
            if (!cv.isNull()) {
                temp = NumParser.parseDouble(cv.getValue().toString());
            }
            if (temp == null) {
                for (DataValue ncv : newValues) {
                    if (ncv.isNull()) {
                        newValue = ncv;
                        ncv.alterPopulation(cv.getPopulation());
                        break;
                    }
                }
                if (newValue == null) {
                    newValue = new DoubleValue(null);
                    newValue.setPopulation(cv.getPopulation());
                    newValues.add(newValue);
                }
            } else {
                newValue = new DoubleValue(temp);
                newValue.setPopulation(cv.getPopulation());
                newValues.add(newValue);
            }
            for (int i = 0; i < MainMenu.MANAGER.getNumberOfRows(); i++) {
                MainMenu.MANAGER.get(i).replace(cv, newValue);
            }
        }
        values = newValues;
    }

    @Override
    public String[] getValues() {
        String[] res = new String[values.size()];
        for (int i = 0; i < res.length; i++) {
            if (values.get(i).isNull()) {
                res[i] = "";
            } else {
                res[i] = values.get(i).getValue().toString();
            }
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
    public void add(DataValue<T> data) {
        values.add(data);
    }

    public void remove(DataValue<T> data) {
        values.remove(data);
    }

    @Override
    public int find(String value) {
        if (value == null) {
            for (int i = 0; i < values.size(); i++) {
                if (values.get(i).isNull()) {
                    return i;
                }
            }
            return -1;
        }
        for (int i = 0; i < values.size(); i++) {
            if (values.get(i).isNull()) {
                continue;
            }
            if (value.equals(values.get(i).getValue().toString())) {
                return i;
            }
        }
        return -1;
    }

    private int totalPopulation() {
        int res = 0;
        for (DataValue cv : values) {
            res += cv.getPopulation();
        }
        return res;
    }

    private int totalPopulationNotNull() {
        int res = 0;
        for (DataValue cv : values) {
            if (!cv.isNull()) {
                res += cv.getPopulation();
            }
        }
        return res;
    }

    @Override
    public double nullPercentage() {
        for (DataValue cv : values) {
            if (cv.isNull()) {
                return (double) cv.getPopulation() / (double) totalPopulation();
            }
        }
        return 0;
    }

    public double[] minMaxAvg() {
        double[] res = new double[3];
        double population = totalPopulationNotNull();
        res[0] = Integer.MAX_VALUE;
        res[1] = Integer.MIN_VALUE;
        res[2] = 0;
        for (DataValue cv : values) {
            if (!cv.isNull()) {
                double value = 0;
                if (type == Double.class) {
                    value = (double) cv.getValue();
                } else {
                    value = (int) cv.getValue();
                }
                res[2] += cv.getPopulation() * value / population;
                if (res[0] > value) {
                    res[0] = value;
                }
                if (res[1] < value) {
                    res[1] = value;
                }
            }
        }
        return res;
    }

    private double stdev(double avg) {
        double population = totalPopulationNotNull();
        double res = 0;
        for (DataValue cv : values) {
            if (!cv.isNull()) {
                double dif;
                if (type == Double.class) {
                    dif = (double) cv.getValue() - avg;
                } else {
                    dif = (int) cv.getValue() - avg;
                }
                res += cv.getPopulation() * dif * dif / population;
            }
        }
        return Math.sqrt(res);
    }

    private String gatherNumericalStatistics() {
        StringBuilder res = new StringBuilder("->Name: ").append(name);
        res.append("\n    ->Total Population: ").append(totalPopulation());
        res.append("\n    ->Null Percentage: ").append(nullPercentage() * 100);
        res.append("%");
        res.append("\n    ->Type: Numerical\n    ->Statistics:");
        double[] statistics = minMaxAvg();
        res.append("\n        ->").append("min: ").append(statistics[0]);
        res.append("\n        ->").append("max: ").append(statistics[1]);
        res.append("\n        ->").append("avg: ").append(statistics[2]);
        res.append("\n        ->").append("σ: ").append(stdev(statistics[2]));
        res.append("\n    ->VALUES:");
        for (DataValue cv : values) {
            if (!cv.isNull()) {
                res.append("\n        ->").append(cv.getValue());
                res.append(" : ").append(cv.getPopulation());
            }
        }
        return res.toString();
    }

    private String gatherNominalStatistics() {
        StringBuilder res = new StringBuilder("->Name: ").append(name);
        res.append("\n    ->Total Population: ").append(totalPopulation());
        res.append("\n    ->Null Percentage: ").append(nullPercentage() * 100);
        res.append("%");
        res.append("\n    ->Type: Nominal\n    ->VALUES:");
        for (DataValue cv : values) {
            if (!cv.isNull()) {
                res.append("\n        ->").append(cv.getValue());
                res.append(" : ").append(cv.getPopulation());
            }
        }
        return res.toString();
    }

    @Override
    public String info() {
        if (type == String.class) {
            return gatherNominalStatistics();
        } else {
            return gatherNumericalStatistics();
        }
    }

    @Override
    public int applyAction(Action a) {
        //TODO APPLY ACTION
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private CloneableAttribute getNominalAttribute() {
        FastVector nominal_values = new FastVector(values.size());
        for (DataValue c : values) {
            String curValue = c.getValue() == null ? "" : c.getValue().toString();
            if (!nominal_values.contains(curValue)) {
                nominal_values.addElement(curValue);
            }
        }
        CloneableAttribute res = new CloneableAttribute(name, nominal_values);
        return res;
    }

    @Override
    public CloneableAttribute getAttribute() {
        if (type == String.class) {
            return getNominalAttribute();
        } else {
            return new CloneableAttribute(name);
        }
    }

    @Override
    public CloneableAttribute getForcedNominalAttribute() {
        return getNominalAttribute();
    }

    @Override
    public DataColumn<T> clone() {
        return new DataColumn(this);
    }
}
