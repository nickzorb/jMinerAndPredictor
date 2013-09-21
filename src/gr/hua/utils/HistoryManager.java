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