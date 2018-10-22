package tictactoe;

import polygon.LineList;

class BoardView extends LineList {

    private static final int CELL_SIDE = 130;
    private static final int MARGIN = 10; //margin around board
    private static final int PADDING = 5; //padding between cell boundaries and marker

    private int rows;
    private int columns;
    private WinLine winLine;

    BoardView(int rows, int columns) {
        setWithVertices(false);
        this.rows = rows;
        this.columns = columns;
        winLine = null;

        //vertical lines
        for(int i = 1; i < columns; i++) {
            addLine(
                    MARGIN + CELL_SIDE * i, MARGIN,
                    MARGIN + CELL_SIDE * i, MARGIN + columns * CELL_SIDE
            );
        }
        //horizontal lines
        for(int i = 1; i < rows; i++) {
            addLine(
                    MARGIN, MARGIN + CELL_SIDE * i,
                    MARGIN + rows * CELL_SIDE, MARGIN + CELL_SIDE * i
            );
        }
    }

    Marker displayMarker(Cell cell) {
        //assert no mark in specified location
        //assert row and column valid
        return MarkerFactory.getMarker(cell.getType(), CELL_SIDE - 2 * PADDING,
                MARGIN + cell.getColumn() * CELL_SIDE + PADDING,
                MARGIN + cell.getRow() * CELL_SIDE + PADDING);
    }


    /**
     * Draw a line including margin between cells
     * @param sequence where win is
     */
    void declareWinner(Sequence sequence) {
        int startRow = sequence.getStartRow();
        int startColumn = sequence.getStartColumn();
        int endRow = sequence.getEndRow();
        int endColumn = sequence.getEndColumn();

        double startXOffset = 0;
        double startYOffset = 0;
        double endXOffset = 0;
        double endYOffset = 0;
        if (startRow == endRow) {
            startYOffset = 0.5;
            endYOffset = 0.5;
            endXOffset = 1;
        } else if (startColumn == endColumn) {
            startXOffset = 0.5;
            endXOffset = 0.5;
            endYOffset = 1;
        } else if (startRow < endRow){ //diagonal down
            endXOffset = 1;
            endYOffset = 1;
        } else { //diagonal up
            startYOffset = 1;
            endXOffset = 1;
        }
        Location startPoint = new Location(
                (int)(MARGIN +  CELL_SIDE * (startColumn + startXOffset)),
                (int)(MARGIN + CELL_SIDE * (startRow + startYOffset)));
        Location endPoint = new Location(
                (int)(MARGIN +  CELL_SIDE * (endColumn + endXOffset)),
                (int)(MARGIN + CELL_SIDE * (endRow + endYOffset)));
        winLine = new WinLine(startPoint, endPoint);
    }

    void resetWinner() {
        if (winLine != null) {
            winLine.delete();
            winLine = null;
        }
    }
}
