package gr.hua.data_structures;

public class StringAllowedValues extends AllowedValues<String> {

    public StringAllowedValues() {
        super();
    }

    private StringAllowedValues(StringAllowedValues old) {
        super(old);
    }

    @Override
    public boolean isAllowed(String value) {
        return inAllowedValues(value);
    }

    @Override
    public AllowedValues clone() {
        return new StringAllowedValues(this);
    }
}
