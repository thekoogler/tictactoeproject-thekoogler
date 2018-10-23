package polygon;

public class Rectangle extends Polygon {

	enum QuadType {SQUARE, RECTANGLE}

	public Rectangle(int left, int top, int width, int height) {
		addPoint(left , top);
		addPoint(left + width , top);
		addPoint(left + width, top + height);
		addPoint(left , top + height);
	}

	public double area() {
		Point a = points.get(0);
		Point b = points.get(1);
		Point c = points.get(2);
		return a.distance(b) * b.distance(c);
	}
}


