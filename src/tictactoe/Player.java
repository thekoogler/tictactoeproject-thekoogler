package tictactoe;

import java.util.ArrayList;

abstract public class Player {

    private static ArrayList<Player> players = new ArrayList<>();

    private String name;

    public static ArrayList<Player> getPlayers() {
        return players;
    }

    protected Player(String name) {
        this.name = name;
        players.add(this);
    }

    abstract public Location getPlay(tictactoe.MarkerType type, tictactoe.Board board);

    public String getName() {
        return name;
    }
}
