package creatures;

public class Attribute<T> {

    private T attribute;
    private double value;
    private double min;
    private double max;

    public Attribute(T attribute, double value) {

    }
    public T getAttribute() {
        return attribute;
    }
    public double getValue() {
        return value;
    }

}
