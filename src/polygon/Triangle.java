package polygon;

public class Triangle extends Polygon {
    public Triangle(int[] xAndY0, int[] xAndY1, int[] xAndY2) {
		addPoint(xAndY0[0], xAndY0[1]);
		addPoint(xAndY1[0], xAndY1[1]);
		addPoint(xAndY2[0], xAndY2[1]);
	}

    public double area() {
		Point a = points.get(0);
		Point b = points.get(1);
		Point c = points.get(2);
		return Math.abs(
			a.getX() * (b.getY() - c.getY()) +
			b.getX() * (c.getY() - a.getY()) +
			c.getX() * (a.getY() - b.getY()));
	}
}


