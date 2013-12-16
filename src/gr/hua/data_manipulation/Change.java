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
