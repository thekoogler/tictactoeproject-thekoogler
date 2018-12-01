package tictactoe;

/**
 * class Sequence contains sequencing code
 */

public class Sequence {

    private int startRow;
    private int startColumn;
    private int endRow;
    private int endColumn;

    Sequence(int startRow, int startColumn,  int endRow, int endColumn) {
        this.startRow = startRow;
        this.startColumn = startColumn;
        this.endRow = endRow;
        this.endColumn = endColumn;
    }

    /**
     * Sequence getWinner checks if any winning lines have occurred
     */


    Sequence getWinner(Cell[][] cells, int inRowToWin) {
        SequenceIterator iter = new SequenceIterator(this, cells);
        int count = 0;
        Cell startCell = null;
        while (iter.hasNext()) {
            Cell cell = iter.next();
            if (cell.getType() == null) {
                count = 0;
                startCell = null;
                continue;
            }
            if (startCell == null || cell.getType() != startCell.getType()) {
                count = 1;
                startCell = cell;
                continue;
            }
            count++;
            if (count == inRowToWin) {
                //we have a winner
                return new Sequence(startCell.getRow(), startCell.getColumn(),
                        cell.getRow(), cell.getColumn());
            }
        }
        return null;
    }

    public int getStartRow() {
        return startRow;
    }

    public int getStartColumn() {
        return startColumn;
    }

    public int getEndRow() {
        return endRow;
    }

    public int getEndColumn() {
        return endColumn;
    }


}
