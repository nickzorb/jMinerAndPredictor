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
package gr.hua.utils;

import java.util.ArrayDeque;
import java.util.Stack;

public class HistoryManager<T> {

    private Stack<T> history;
    private ArrayDeque<T> future;
    private T alteredState;

    public HistoryManager() {
        history = new Stack();
        future = new ArrayDeque();
        alteredState = null;
    }

    public void save() {
        if (alteredState != null) {
            history.push(alteredState);
            if (!future.isEmpty()) {
                future = new ArrayDeque();
            }
            alteredState = null;
        }
    }

    public void revert() {
        if (history.size() > 1) {
            future.push(history.pop());
            alteredState = null;
        }
    }

    public void redo() {
        if (!future.isEmpty()) {
            history.push(future.pop());
            alteredState = null;
        }
    }

    public void reset() {
        history = new Stack();
        future = new ArrayDeque();
        alteredState = null;
    }

    public T getLatestState() {
        if (history.isEmpty()) {
            return null;
        }
        return history.peek();
    }

    public T getAlteredState() {
        return alteredState;
    }

    public void setAlteredState(T state) {
        alteredState = state;
    }
}