package gr.hua.data_structures;

public interface Row<T> {

    void remove(int i);

    void remove(T d);

    void add(T d);

    void add(int i, T d);

    T get(int i);

    int size();

    int columnOf(T d);

    void replace(T t, T r);

    String[] toArray();

    double nullPercentage();

    String info();
}
