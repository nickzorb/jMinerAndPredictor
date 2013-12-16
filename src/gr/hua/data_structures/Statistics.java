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

import gr.hua.weka_bridge.AttributeSet;
import java.io.Serializable;
import java.util.HashMap;

public class Statistics implements Serializable, Cloneable {

    private final HashMap<String, Double> results;
    private final AttributeSet attributes;

    public Statistics() {
        results = new HashMap();
        attributes = new AttributeSet();
    }

    public Statistics(AttributeSet attributes) {
        results = new HashMap();
        this.attributes = attributes.clone();
    }

    public Statistics(Statistics s) {
        results = (HashMap<String, Double>) s.results.clone();
        attributes = s.attributes.clone();
    }
    
    public Double getStatistic(String stat) {
        return results.get(stat);
    }
    
    public String[] getStatistics() {
        return results.keySet().toArray(new String[results.size()]);
    }
    
    public void addStat(String stat, Double value) {
        results.put(stat, value);
    }
    
    public void setStat(String stat, Double value) {
       if (results.containsKey(stat)) {
           results.put(stat, value);
       }
    }
    
    public AttributeSet getAttributes() {
        return attributes.clone();
    }
    
    @Override
    public Statistics clone() {
        return new Statistics(this);
    }
}
