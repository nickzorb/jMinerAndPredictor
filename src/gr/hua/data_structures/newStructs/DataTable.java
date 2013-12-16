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
package gr.hua.data_structures.newStructs;

import gr.hua.data_structures.basic.DataValue;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class DataTable implements Serializable {

    private final ArrayList<ArrayList<DataValue>> values;
    private ArrayList<String> columnNames;
    private long latestChange;

    public DataTable() {
        values = new ArrayList();
    }

    public DataTable(DataTable old) {
        values = new ArrayList(old.values.size());
        for (ArrayList<DataValue> l : old.values) {
            ArrayList<DataValue> cur = new ArrayList(l.size());
            for (DataValue d : l) {
                cur.add(d.clone());
            }
            values.add(cur);
        }
        columnNames = new ArrayList();
        for (String s : old.columnNames) {
            columnNames.add(s);
        }
        latestChange = Calendar.getInstance().getTimeInMillis();
    }

    
    
    public DataRow getRow(int i) {
        if (i > height()) {
            return null;
        } else if (i == height()) {
            ArrayList<DataValue> row = new ArrayList();
            values.add(row);
        }
        return new DataRow(values.get(i));
    }

    public DataColumn getColumn(int i) {
        if (i > width()) {
            return null;
        } else if (i == width()) {
            columnNames.add("");
        }
        return new DataColumn(this, i);
    }

    public DataColumn getColumn(String s) {
        int column = columnNames.indexOf(s);
        if (column == -1) {
            return null;
        }
        return new DataColumn(this, column);
    }
    
    public void addValueAt(int column, int row) {
        
    }
    
    public String[] getColumnNames() {
        return columnNames.toArray(new String[columnNames.size()]);
    }
    
    private int height() {
        return getNumOfRows();
    }
    
    private int width() {
        return getNumOfColumns();
    }
    
    private int width(int i) {
        return getNumOfValuesForRow(i);
    }
    
    public int getNumOfRows() {
        return values.size();
    }
    
    public int getNumOfColumns() {
        return columnNames.size();
    }
    
    public int getNumOfValuesForRow(int i) {
        if (i > height()) {
            return -1;
        }
        return values.get(i).size();
    }

    public DataTable clone(DataTable old) {
        return new DataTable(old);
    }
}
