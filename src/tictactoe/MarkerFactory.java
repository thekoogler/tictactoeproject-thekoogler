package tictactoe;

public class MarkerFactory {

    public static Marker getMarker(MarkerType type, int size,  int left, int top) {

        switch(type) {
            case O:
                return new OMarker(size, left, top);

            case X:
                return new XMarker(size, left, top);

        }
        return null;
    }
}
