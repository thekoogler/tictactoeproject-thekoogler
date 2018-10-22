package polygon;

public class Square extends Rectangle {
	public Square(int left, int top, int length) {
		super(left, top, length,  length);
	}

    public double area() {
        Point a = points.get(0);
        Point b = points.get(1);
        return Math.pow(a.distance(b), 2);
    }
}


