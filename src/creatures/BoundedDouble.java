package creatures;

public class BoundedDouble {

    private double value;
    private double min;
    private double max;

    public BoundedDouble(double value, double min, double max) {
        this.value = value;
        this.min = min;
        this.max = max;
    }

    /**
     * Initialize the bounded double with a default min and max (useful to avoid repetitions in the actions weight)
     * @param value
     */
    public BoundedDouble(double value) {
        this(value, 0, 10);
    }

    public double getValue() {
        return value;
    }
    public double getMin() {
        return min;
    }
    public double getMax() {
        return max;
    }

}
