package gr.hua.data_structures;

import gr.hua.data_structures.basic.DataValue;

public interface Column<T> {

    void setName(String name);

    AllowedValues getAllowedValues();

    void setAllowedValues(AllowedValues values);

    void revalidate();

    String[] getValues();

    int size();

    String getName();

    void add(DataValue<T> data);

    int find(String value);

    double nullPercentage();

    String info();
}
