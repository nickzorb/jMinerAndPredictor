package gr.hua.data_structures;

import java.util.concurrent.atomic.AtomicLong;
import weka.core.Instance;

public abstract class ColumnValue<T> implements Value<T>, Cloneable {

    private static final AtomicLong nextId = new AtomicLong();
    
    protected int curPopulation;
    protected T curValue;
    /**
     * this is used to avoid conflicts between identical column values.
     */
    private final long id = nextId.incrementAndGet();

    public ColumnValue(T value) {
        curValue = value;
        curPopulation = 1;
    }

    protected ColumnValue(ColumnValue<T> oldValue) {
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

    @Override
    public void setValue(T value) {
        curValue = value;
    }

    public boolean isNull() {
        return curValue == null;
    }

    public abstract void populateInstance(Instance ins, int attribute);

    public abstract ColumnValue<T> clone();
}
