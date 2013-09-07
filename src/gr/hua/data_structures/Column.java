package gr.hua.data_structures;

interface Column<T> {

    void setName(String name);

    AllowedValues getAllowedValues();

    void setAllowedValues(AllowedValues values);

    void revalidate();

    String[] getValues();

    int size();

    String getName();

    void add(ColumnValue<T> data);

    int find(String value);

    double nullPercentage();

    String info();
}
