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
import java.util.LinkedList;
import javax.swing.JFrame;

public class Action implements Serializable, Cloneable {

    //action categories:
    public static final String C = "Column";
    public static final String R = "Row";
    public static final String G = "Global";
    public static final String[] actionCategories = {C, R, G};
    //column actions:
    public static final String CR = "Remove Column";
    public static final String CCN = "Rename Column";
    public static final String CD = "Duplicate Column";
    public static final String CFNRV = "Find and replace column values";
    public static final String CTR = "Split to ranges";
//    public static final String CCAV = "Choose allowed values";
//    public static final String CCT = "Change type";
//    public static final String CARX = "Apply regural expression";
    public static final String[] column_actions = {CR, CCN, CD, CFNRV, CTR};
    //row actions:
    public static final String RR = "Remove Row";
    public static final String RD = "Duplicate Row";
    public static final String RFNRV = "Find and replace row values";
//    public static final String RARX = "Apply regural expression";
    public static final String[] row_actions = {RR, RD, RFNRV};
    //global actions:
    public static final String GREC = "Remove empty columns";
    public static final String GRER = "Remove empty rows";
    public static final String GFNRV = "Find and replace global values";
//    public static final String GARX = "Apply regural expression";
    public static final String[] global_actions = {GREC, GRER, GFNRV};
    //all actions by category
    public static final String[][] actions = {column_actions, row_actions, global_actions};
    //the parent is the main window and should be set every time the program runs
    private static JFrame parent;
    //LinkedLists to support serialization
    private LinkedList<Change> changes;
    private String mode;
    private LinkedList<String> flags;
    private Object target;

    public Action(String mode, LinkedList<String> flags, Object target) {
        this.mode = mode;
        this.flags = flags;
        this.target = target;
        changes = new LinkedList();
    }

    protected Action(Action c) {
        mode = c.mode;
        flags = new LinkedList();
        if (c.flags != null) {
            for (String s : c.flags) {
                flags.add(s);
            }
        }
        target = c.target;
        changes = new LinkedList();
        for (Change ch : c.changes) {
            changes.add(ch.clone());
        }
    }

    public static JFrame getParent() {
        return parent;
    }

    public static void setParent(JFrame parent) {
        Action.parent = parent;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public LinkedList<String> getFlags() {
        return flags;
    }

    public void setFlags(LinkedList<String> flags) {
        this.flags = flags;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public void addChange(Change c) {
        changes.add(c);
    }

    public LinkedList<Change> getChanges() {
        return changes;
    }

    @Override
    public Action clone() {
        return new Action(this);
    }
}
