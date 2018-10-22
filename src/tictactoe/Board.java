package tictactoe;

import java.util.ArrayList;

public class Board {

    private static final int DEFAULT_ROWS = 3;
    private static final int DEFAULT_COLUMNS = 3;
    private static final int DEFAULT_IN_ROW_TO_WIN = 3;

    private int rows;
    private int columns;
    private int inRowToWin;
    private Cell[][] cells;
    private BoardView view;
    private ArrayList<Sequence> sequences;
    private Sequence winningSequence;

    public Board() {
        rows = DEFAULT_ROWS;
        columns = DEFAULT_COLUMNS;
        inRowToWin = DEFAULT_IN_ROW_TO_WIN;
        winningSequence = null;
        view = new BoardView(rows, columns);

        cells = new Cell[rows][columns];
        for (int i = 0; i < rows; i ++) {
            for (int j = 0; j < columns; j++) {
                cells[i][j] = new Cell(view, i, j);
            }
        }
        
        //remember all possible sequences for easy tests later
        sequences = new ArrayList<>();
        //rows & diagonals from left column
        for (int i = 0; i < rows; i++) {
            sequences.add(new Sequence(i, 0, i, columns - 1));
            if (rows - i  >= inRowToWin) {  //down diagonal
                sequences.add(new Sequence(i, 0, rows - 1, i + rows - 1));
            }
            if ( i + 1 >= inRowToWin) {  //up diagonals
                sequences.add(new Sequence(i, 0, 0, i));
            }
        }
        //columns & additional diagonals from top or bottom row
        for (int i = 0; i < columns; i++) {
            sequences.add(new Sequence(0, i, rows - 1, i));
            if (i != 0 && columns - 1 >= inRowToWin) {  //down diagonals from top
                sequences.add(new Sequence(0, i, i + columns - 1, columns - 1));
            }
            if (i != 0 && columns - i >= inRowToWin) { //up diagonals from bottom
                sequences.add(new Sequence(rows - 1, i, i,columns - 1));
            }

        }
    }

    public boolean playMarker(MarkerType type, int row, int column) {
        if (row < 0 || row >= rows || column < 0 || column >= columns) {
            return false;
        }
        if (cells[row][column].isFilled()) {
            return false;
        }
        cells[row][column].setType(type);
        return true;
    }

    public boolean hasWinner() {
        //return immediately if already known
        if (winningSequence != null) {
            return true;
        }
        for (Sequence sequence : sequences) {
            winningSequence = sequence.getWinner(cells, inRowToWin);
            if (winningSequence != null) {
                view.declareWinner(winningSequence);
                return true;
            }
        }
        return false;
    }

    public void reset() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns;  j++) {
                cells[i][j].setType(null);
            }
        }
        winningSequence = null;
        view.resetWinner();
    }

    public boolean hasNextPlay() {
        if (hasWinner()) {
            return false;
        }
        //if there are available spaces then we can have a next play
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (cells[i][j].getType() == null) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getInRowToWin() {
        return inRowToWin;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public ArrayList<Sequence> getSequences() {
        return sequences;
    }

    public int getNumberRows() {
        return rows;
    }

    public int getNumberColumns() {
        return columns;
    }
}
