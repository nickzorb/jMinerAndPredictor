package gr.hua.data_structures;

public interface Value<T> {

    T getValue();
    
    String getStringValue();
    
    Double getDoubleValue();

    void setValue(T value);
    
    void setValue(String value);

    boolean equals(Value<T> value);
}
