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
package gr.hua.weka_bridge;

import java.util.Collections;
import java.util.List;
import weka.core.Attribute;
import weka.core.FastVector;

public class CloneableAttribute extends Attribute implements Cloneable {

    public CloneableAttribute(final String name) {
        super(name);
    }
    
    public CloneableAttribute(final String name, final FastVector values) {
        super(name, values);
    }

    @Override
    public CloneableAttribute clone() {
        if (isNominal()) {
            List<Object> curObjects = Collections.list(enumerateValues());
            FastVector values = new FastVector(curObjects.size());
            for (Object o : curObjects) {
                values.addElement(o);
            }
            return new CloneableAttribute(name(), values);
        } else {
            return new CloneableAttribute(name());
        }
    }
}
