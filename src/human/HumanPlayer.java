package human;

import java.util.Scanner;
import tictactoe.Board;
import tictactoe.Location;
import tictactoe.MarkerType;
import tictactoe.Player;

/**
 * Takes console input for plays
 */
public class HumanPlayer extends Player {

    private Scanner scanner;

    public HumanPlayer(String name) {
        super(name);
        scanner = new Scanner(System.in);
    }

    /**
     * Gets a row and column input and returns as a "Point"
     *
     * @param type  the type of marker used by this player
     * @param board the current board position
     * @return play at row and column (as a Point)
     */
    public Location getPlay(MarkerType type, Board board) {
        System.out.print(getName() + ", select play(row, column): ");
        //since we already created a method in PolygonMaker to get a point, use it
        return getLocation(scanner, board.getNumberRows(), board.getNumberColumns());
    }

    /**
     * User picks place to play
     * @param scanner input device
     * @param numberRows number of rows
     * @param numberColumns number of columns
     * @return selected row and column
     */
    private Location getLocation(Scanner scanner, int numberRows, int numberColumns) {
        while (true) {
            System.out.print(": ");
            String pointString = scanner.nextLine();

            if (pointString.length() == 0) {
                return null;
            }

            //comma separated; report error and continue if no comma
            int index = pointString.indexOf(",");
            if (index == -1) {
                System.out.println("Values must be comma-separated, try again.");
                continue;
            }
            String xString = pointString.substring(0, index).trim();
            String yString = pointString.substring(index + 1).trim();

            try {
                //make integers out of xString and yString
                int row = Integer.parseInt(xString);
                int column = Integer.parseInt(yString);
                if (row < 0 || row >= numberRows || column < 0 || column >= numberColumns) {
                    throw new NumberFormatException("Selection out of range");
                }
                return new Location(row, column);
            } catch (NumberFormatException e) {
                //handle bad input
                System.out.println((numberRows == numberColumns
                        ? "Values must be integers between 0 and " + numberRows
                        : "Row must be an integer between 0 and " + numberRows + " and "
                            + "Column between 0 and " + numberColumns)
                        + ", try again.");
            }

        }
    }
}
