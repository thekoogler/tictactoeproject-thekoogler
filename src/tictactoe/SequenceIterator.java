package tictactoe;

import tictactoe.Cell;
import tictactoe.Sequence;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SequenceIterator implements Iterator<Cell> {

    private Cell[][] cells;
    private Sequence sequence;
    private int currentRow;
    private int currentColumn;
    private int deltaRow;
    private int deltaColumn;

    public SequenceIterator(Sequence sequence, Cell[][] cells) {
        this.cells = cells;
        this.sequence = sequence;
        deltaRow = sequence.getStartRow() == sequence.getEndRow()
                ? 0
                : (sequence.getStartRow() < sequence.getEndRow() ? 1 : -1);
        deltaColumn = sequence.getStartColumn() == sequence.getEndColumn()
                ? 0
                : (sequence.getStartColumn() < sequence.getEndColumn() ? 1 : -1);
        currentRow = sequence.getStartRow();
        currentColumn = sequence.getStartColumn();
    }

    @Override
    public boolean hasNext() {
        return (currentRow * deltaRow <= sequence.getEndRow() * deltaRow
                && currentColumn * deltaColumn <= sequence.getEndColumn() * deltaColumn);
    }

    @Override
    public Cell next() throws NoSuchElementException {
        if (!hasNext()) {
            throw new NoSuchElementException("At end of sequence");
        }
        Cell cell = cells[currentRow][currentColumn];
        currentRow += deltaRow;
        currentColumn += deltaColumn;
        return cell;
    }
}
