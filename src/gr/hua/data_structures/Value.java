package gr.hua.data_structures;


public interface Value<T> {

    T getValue();
    void setValue(T value);
    boolean equals(Value<T> value);
    
}
