package tictactoe;


/**
 * location class has code pertaining to which row and column is chosen
 */

public class Location {
    private int row;
    private int column;

    /**
     * this sets this.row & this.column to row & column
     */

    public Location(int row, int column) {
        this.row = row;
        this.column = column;
    }


    /**
     * getRow returns row
     */

    public int getRow() {
        return row;
    }

    /**
     * setRow sets row to this.row
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * getColumn returns column
     */
    public int getColumn() {
        return column;
    }

    /**
     * setRow sets column to this.column
     */
    public void setColumn(int column) {
        this.column = column;
    }

    /**
     * toString returns the row and column in string form
     */

    @Override
    public String toString() {
        return "[" + row + "," + column + "]";
    }
}
