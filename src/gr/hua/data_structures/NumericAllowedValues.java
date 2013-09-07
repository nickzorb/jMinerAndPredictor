package gr.hua.data_structures;

public class NumericAllowedValues extends AllowedValues<Double> {

    public NumericAllowedValues() {
        super();
    }

    private NumericAllowedValues(NumericAllowedValues old) {
        super(old);
    }

    @Override
    public boolean isAllowed(Double value) {
        if (getMin() != null && value < getMin()) {
            return false;
        } else if (getMax() != null && value > getMax()) {
            return false;
        } else {
            return inAllowedValues(value);
        }
    }

    @Override
    public AllowedValues clone() {
        return new NumericAllowedValues(this);
    }
}
