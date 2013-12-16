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
package gr.hua.gui.DataMiner;

import java.util.Arrays;
import javax.swing.DefaultListModel;

public class MyDefaultListModel extends DefaultListModel<JListItem> {

    @Override
    public JListItem[] toArray() {
        return Arrays.copyOf(super.toArray(), size(), JListItem[].class);
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (JListItem item : toArray()) {
            sb.append(item.toString());
            sb.append(',').append(' ');
        }
        sb.deleteCharAt(sb.length());
        sb.deleteCharAt(sb.length());
        sb.append(']');
        return sb.toString();
    }
}
