package tictactoe;


/**
 * the cell class contains code about cell structure, getting cells & filling them
 */

public class Cell {

    private BoardView view;
    private Location location;
    private MarkerType type;
    private Marker marker;
    /**
     * this sets this.view & this.location to view & location
     */
    Cell(BoardView view, int row, int column) {
        this.view = view;
        this.location = new Location(row, column);
        type = null;
    }

    /**
     * sets type
     */
    void setType(MarkerType type) {
        //override whatever may be there
        if (marker != null && this.type != type) {
            marker.delete();
            marker = null;
        }
        this.type  = type;
        if (type != null) {
            marker = view.displayMarker(this);
        }
    }


    /**
     * ifFilled returns type as null
     */

    public boolean isFilled() {
        return type != null;
    }

    /**
     * getType returns type
     */
    public MarkerType getType() {
        return type;
    }
    /**
     * getRow returns location.getRow
     */

    public int getRow() {
        return location.getRow();
    }
    /**
     * getRow returns location.getColumn
     */
    public int getColumn() {
        return location.getColumn();
    }

}
