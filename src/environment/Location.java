package environment;

//Serializable for the DataFormat transfer
public class Location {

    private int col;
    private int row;

    public Location(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getCol() {
        if (col < 0) {
            return 0;
        } else {
            return col;
        }
    }

    public int getRow() {
        if (row < 0) {
            return 0;
        } else {
            return row;
        }
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public Location add(int row, int col) {
        return new Location(this.row + row, this.col + col);
    }

    public boolean equals(Location location) {
        return this.col == location.col && this.row == location.row;
    }

    public boolean isInside(Object[][] array) {
        return row >= 0 && col >= 0 && row < array.length && col < array[0].length;
    }
    public String toString() {
        return "(Col " + col + ":" + "Row " + row + ")";
    }
}