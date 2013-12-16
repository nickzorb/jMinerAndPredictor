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

import java.util.concurrent.atomic.AtomicLong;
import weka.core.Instance;

public abstract class DataValue<T> implements Value<T>, Cloneable {

    private static final AtomicLong nextId = new AtomicLong();
    
    protected int curPopulation;
    protected T curValue;
    /**
     * this is used to avoid conflicts between identical column values.
     */
    private final long id = nextId.incrementAndGet();

    public DataValue(T value) {
        curValue = value;
        curPopulation = 1;
    }

    protected DataValue(DataValue<T> oldValue) {
        assert (oldValue != null);
        curValue = oldValue.curValue;
        curPopulation = oldValue.curPopulation;
    }

    public int getPopulation() {
        return curPopulation;
    }

    public void setPopulation(int population) {
        curPopulation = population;
    }

    public void alterPopulation(int change) {
        curPopulation += change;
    }

    @Override
    public T getValue() {
        return curValue;
    }

    public boolean isNull() {
        return curValue == null;
    }
    
    @Override
    public String getStringValue() {
        return curValue == null ? "" : curValue.toString();
    }

    public abstract void populateInstance(Instance ins, int attribute);

    public abstract DataValue<T> clone();
}
