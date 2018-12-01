package bailey;

import tictactoe.*;

import java.util.ArrayList;

/**
 * class ComputerPlayer creates a computer that knows how to play tic tac toe who can play against real players or other computers
 */

public class ComputerPlayer extends Player {
    private int turnNumber;

    /**
     * the constructor for this class
     */

    public ComputerPlayer(String name) {
        super(name);
        this.turnNumber = 0;

    }

    /**
     * code for filling in squares
     */

    public Location getPlay(tictactoe.MarkerType type, tictactoe.Board board) {
        ArrayList<Sequence> sequences = board.getSequences();
        Cell[][] cells = board.getCells();
        MarkerType[] types;
        if (type == MarkerType.O) {
            types = new MarkerType[]{MarkerType.O, MarkerType.X};
        } else {
            types = new MarkerType[]{MarkerType.X, MarkerType.O};
        }
        for (MarkerType mtype : types) {
            for (Sequence seq : sequences) {
                Location free = checkForTwo(mtype, seq, cells);
                if (free != null) {
                    return free;
                }
            }
        }


/**
 * checks turnnumber to see if it is a good idea to take the middle or not
 */
        Location[] corners = {new Location(0,0), new Location(2,0),
                new Location(0,2), new Location(2,2)};
        if(turnNumber == 0){
            turnNumber++;
            for (Location corner : corners) {
                if(cells[corner.getRow()][corner.getColumn()].isFilled()) {
                    return new Location(1,1);
                }
            }
            return new Location(0, 0);
        }

        /**
         * prioritizes which spots should be filled in which order-with edge spots getting least priority
         */

        if(turnNumber == 1 && cells[1][1].isFilled()){
            turnNumber++;
            for (Location corner : corners) {
                if(cells[corner.getRow()][corner.getColumn()].isFilled()) {
                    int row;
                    int col;
                    if (corner.getRow() == 0) {
                        row = 2;
                    } else {
                        row = 0;
                    }
                    if (corner.getColumn() == 0) {
                        col = 2;
                    } else {
                        col = 0;
                    }
                    return new Location(row, col);
                }
            }
        }

        if(!cells[1][1].isFilled()){
            return new Location(1,1);
        }
        for (Location corner : corners) {
            if(!cells[corner.getRow()][corner.getColumn()].isFilled()) {
                return new Location(corner.getRow(), corner.getColumn());
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!cells[i][j].isFilled()) {
                    return new Location(i, j);
                }
            }
        }
        return null;
    }

    /**
     * checks if there are two in a section-and if there are it will pursue stopping the other playing from winning or it will win itself
     */

    private Location checkForTwo(tictactoe.MarkerType type, Sequence seq, Cell[][] cells) {
        SequenceIterator iter = new SequenceIterator(seq, cells);
        Location free = null;
        int count = 0;
        while (iter.hasNext()) {
            Cell cell = iter.next();
            if (cell.getType() == null) {
                if (free == null) {
                    free = new Location(cell.getRow(), cell.getColumn());
                } else {
                    return null;
                }
            } else if (cell.getType() != type) {
                return null;
            }
            count++;
            if (count == 3) {
                //we have a two
                if (free != null) {
                    return free;
                }
            }
        }
        return null;
    }
}
