package polygon;

public class Rectangle extends Polygon {

	enum QuadType {SQUARE, RECTANGLE}

	public Rectangle(int left, int top, int xLength, int yLength) {
		addPoint(left , top);
        addPoint(left + xLength , top);
        addPoint(left + xLength, top + yLength);
        addPoint(left , top + yLength);
	}

	public double area() {
		Point a = points.get(0);
		Point b = points.get(1);
		Point c = points.get(2);
		return a.distance(b) * b.distance(c);
	}
}


