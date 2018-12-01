package tictactoe;

import polygon.LineList;

/**
 * code for marking squares
 */

public class XMarker extends LineList implements Marker {

    XMarker(int size, int left, int top) {
        super(false);
        addLine(left, top, left + size, top + size);
        addLine(left, top + size, left + size, top);
    }

    public void delete() {
        removeAll();
    }
}
