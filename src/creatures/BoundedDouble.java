package creatures;

public class BoundedDouble implements Cloneable {

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
    public void setValue(double value) {
        this.value = value;
    }
    public double getMin() {
        return min;
    }
    public double getMax() {
        return max;
    }

    @Override
    public BoundedDouble clone() {
        try {
            return (BoundedDouble) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public String toString() {
        return String.valueOf(value);
    }

}
