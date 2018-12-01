package tictactoe;

import polygon.Polygon;

/**
 * code for checking if there is a winning line
 */

class WinLine extends Polygon {

    WinLine(Location start, Location end) {
        addPoint(start.getRow(), start.getColumn());
        addPoint(end.getRow(), end.getColumn());
    }

    void delete() {
        removeAll();
    }
}
