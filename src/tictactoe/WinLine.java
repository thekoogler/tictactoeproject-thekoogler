package tictactoe;

import polygon.Polygon;

class WinLine extends Polygon {

    WinLine(Location start, Location end) {
        addPoint(start.getRow(), start.getColumn());
        addPoint(end.getRow(), end.getColumn());
    }

    void delete() {
        removeAll();
    }
}
