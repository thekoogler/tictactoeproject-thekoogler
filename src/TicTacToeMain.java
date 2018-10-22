import human.HumanPlayer;
import tictactoe.Board;
import tictactoe.Location;
import tictactoe.MarkerType;
import tictactoe.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class TicTacToeMain {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // make players
        new HumanPlayer("Doug");
        new HumanPlayer("Prateek");
        new HumanPlayer("Other");
        ArrayList<Player> playerList = Player.getPlayers();

        System.out.println("Welcome to Tic Tac Toe");
        Board board = new Board();
        Player[] players = new Player[2];

        while(true) {
            System.out.println("New Game");
            board.reset();
            if(false) {
                players[0] = playerList.get(1);
                players[1] = playerList.get(2);
            } else {
                for (int i = 0; i < 2; i++) {
                    while (players[i] == null) {
                        System.out.println("Select Player#" + i
                                + "[" + (i == 0 ? MarkerType.X : MarkerType.O) + "'s]");
                        for (int j = 0; j < playerList.size(); j++) {
                            if (i == 1 && playerList.get(j) == players[0]) {
                                continue;
                            }
                            System.out.println(j + "." + playerList.get(j).getName());
                        }
                        System.out.print(": ");
                        String playerChoice = scanner.nextLine();
                        int numberChoice = 0;
                        try {
                            numberChoice = Integer.parseInt(playerChoice);
                        } catch (NumberFormatException e) {
                            System.out.println("You must make a choice between 0 and " + (playerList.size() - 1));
                            continue;
                        }
                        players[i] = playerList.get(numberChoice);
                    }
                }
            }

            //play game
            int currentPlayer = 1;
            while (board.hasNextPlay()) {
                currentPlayer = (currentPlayer + 1) % 2;
                MarkerType currentType = currentPlayer == 0 ? MarkerType.X : MarkerType.O;
                boolean playOk = false;
                while (!playOk) {

                    Location point = players[currentPlayer].getPlay(currentType, board);
                    if (point != null) {
                        System.out.println("Player #" + currentPlayer
                                + " " + players[currentPlayer].getName() + " plays at " + point);
                        playOk = board.playMarker(currentType, point.getRow(), point.getColumn());
                    }
                    if (!playOk) {
                        System.out.println("Invalid play, try again");
                    }
                }
            }
            if (board.hasWinner()) {
                System.out.println("Player #" + currentPlayer + " won!");
            } else {
                System.out.println("Game ended in a tie.");
            }
            System.out.print("Any key to continue or Q to quit: ");
            String toContinue = scanner.nextLine();
            if (toContinue.toUpperCase().equals("Q")) {
                break;
            }
        }
        System.out.println("Bye");
        System.exit(0);
    }

}
