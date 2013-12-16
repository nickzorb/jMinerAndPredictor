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
