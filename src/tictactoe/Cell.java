package tictactoe;

public class Cell {

    private BoardView view;
    private Location location;
    private MarkerType type;
    private Marker marker;

    Cell(BoardView view, int row, int column) {
        this.view = view;
        this.location = new Location(row, column);
        type = null;
    }

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

    public boolean isFilled() {
        return type != null;
    }

    public MarkerType getType() {
        return type;
    }

    public int getRow() {
        return location.getRow();
    }

    public int getColumn() {
        return location.getColumn();
    }

}
