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

import java.util.ArrayList;

public abstract class AllowedValues<T> implements Cloneable {

    private ArrayList<T> values = new ArrayList();
    private T min = null;
    private T max = null;

    protected AllowedValues() {
        min = null;
        max = null;
    }

    protected AllowedValues(AllowedValues<T> old) {
        min = old.min;
        max = old.max;
        values = new ArrayList();
        for (T t : old.values) {
            values.add(t);
        }
    }

    public void allow(T value) {
        values.add(value);
    }

    public void forbid(T value) {
        values.remove(value);
    }

    public void setMin(T limit) {
        min = limit;
    }

    public void setMax(T limit) {
        max = limit;
    }

    public T getMin() {
        return min;
    }

    public T getMax() {
        return max;
    }

    protected boolean inAllowedValues(T value) {
        if (!values.isEmpty() && !values.contains(value)) {
            return false;
        }
        return true;
    }

    public abstract boolean isAllowed(T value);

    public abstract AllowedValues clone();
}
