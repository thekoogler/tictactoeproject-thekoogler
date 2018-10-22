package polygon;

import java.util.ArrayList;

public class Polygon extends LineList {

	ArrayList<Point> points;

	public Polygon() {
		points = new ArrayList<>();
	}

	public void addPoint(int x, int y) {
		Point newEndPoint = new Point(x, y);
		points.add(newEndPoint);

		if (points.size() == 1) {
			return; //nothing else to do
		}

		Point startPoint = points.get(0);
		if (points.size() == 2) {
			//create first line
			addLine(startPoint, newEndPoint);
			return;
		}

		Point oldEndPoint = points.get(points.size() - 2);
		if (startPoint != oldEndPoint) {
			//get rid of the old connector
			removeLine(oldEndPoint, startPoint);
		}

		addLine(oldEndPoint, newEndPoint);
		addLine(newEndPoint, startPoint);
	}

	public int getNumberVertices() {
		return points.size();
	}

	public double perimeter() {

		if (points.size() < 3) {
			return 0;
		}

		double perimeter = 0;
		for(int i = 0; i < points.size(); i++) {
			Point p1 = points.get(i);
			Point p2 = points.get(i == points.size() - 1 ? 0 : i + 1);
			perimeter += p1.distance(p2);
		}
		return perimeter;
	}

	public Rectangle boundingRectangle() {
		if (points.size() == 0) {
			return null;
		}
		int leftMost = points.get(0).getX();
		int rightMost = points.get(0).getX();
		int topMost = points.get(0).getY();
		int bottomMost = points.get(0).getY();
		for (int i = 1; i < points.size(); i++) {
			if (points.get(i).getX() < leftMost) {
				leftMost = points.get(i).getX();
			}
			if (points.get(i).getX() > rightMost) {
				rightMost = points.get(i).getX();
			}
			if (points.get(i).getY() < topMost) {
				topMost = points.get(i).getY();
			}
			if (points.get(i).getY() > bottomMost) {
				bottomMost = points.get(i).getY();
			}
		}
		return new Rectangle(leftMost, topMost, rightMost - leftMost, bottomMost - topMost);
	}

	@Override
	public String toString() {
		return this.getClass().getName() + ":" + points;
	}
}


