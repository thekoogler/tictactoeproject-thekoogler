
import java.util.Scanner;
import polygon.Polygon;
import polygon.Square;
import polygon.Triangle;
import polygon.Rectangle;

public class PolygonMaker {

    public static void main(String args[]) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Making Polygons");
/*
		new Square(new Point(150,150), new Point(240,240));
		Polygon t1 = new Triangle(new Point(5,5), new Point(200,200), new Point(0,200));

		//Polygon t2 = makeTriangle(scanner);
		Polygon t2 = new Triangle(new Point(205,15), new Point(200,200), new Point(19,300));
		System.out.println("t1 bounding rectangle:" + t1.boundingRectangle());
*/


        while (true) {

            System.out.print("Make Polygon(P), Triangle(T), Rectangle(R), Square(S) or quit: ");
            String doMore = scanner.nextLine();

            if (doMore.length() == 0 || doMore.toUpperCase().startsWith("Q")) {
                break;
            }
            String type = doMore.toUpperCase().substring(0, 1);
            switch (type) {
                case "P":
                    makePolygon(scanner);
                    break;
                case "T":
                    makeTriangle(scanner);
                    break;
                case "S":
                    makeSquare(scanner);
                    break;
                case "R":
                    makeRectangle(scanner);
                    break;
                default:
                    System.out.println("Bad input");
            }
        }
        System.out.println("bye");
				System.exit(0);
    }

    static Polygon makePolygon(Scanner scanner) {
        System.out.println("Constructing a new Polygon\n"
                + "Enter some points, x,y; simply hit enter to end polygon.");

        //Construct the polygon here
        Polygon polygon = new Polygon();

        while(true) {
            System.out.print("Enter next point");
            int[] xAndY = getPoint(scanner);

            if (xAndY == null) {
                break;
            }

            //Add a point to the polygon
            polygon.addPoint(xAndY[0], xAndY[1]);

            //say how many points now in the polygon
            System.out.println("Polygon now has " + polygon.getNumberVertices() + " point"
                    + (polygon.getNumberVertices() != 1 ? "s" : ""));
        }

        //say what the perimeter is of polygon
        System.out.println("Polygon perimeter is " + polygon.perimeter());

        return polygon;
    }

    static Polygon makeTriangle(Scanner scanner) {
        System.out.println("Constructing a new Triangle\n"
                + "Enter three points x,y");

        int[][] xAndYPoints = new int[3][2];
        int validPoints = 0;
        while (validPoints < 3) {
            System.out.print("Enter point #" + validPoints);
            xAndYPoints[validPoints] = getPoint(scanner);

            if (xAndYPoints[validPoints]  == null) {
                System.out.println("You must enter 3 points!");
                continue;
            }
            validPoints++;
        }
        Triangle triangle = new Triangle(xAndYPoints[0], xAndYPoints[1], xAndYPoints[2]);

        //say what the perimeter is of triangle
        System.out.println("Triangle perimeter is " + triangle.perimeter());
        System.out.println("Triangle area is " + triangle.area());
        return triangle;
    }

    static Polygon makeSquare(Scanner scanner) {
        System.out.println("Constructing a new Square");
        System.out.print("Enter left top point (x,y)");
        int[] leftTop = getPoint(scanner);
        System.out.print("Enter sideLength");
        Integer length = getLength(scanner);
        if (length == null) {
            System.out.println("No length entered, making square cancelled");
            return null;
        }
        Square square = new Square(leftTop[0], leftTop[1], length);
        System.out.println("Square perimeter is " + square.perimeter());
        System.out.println("Square area is " + square.area());
        return square;
    }

    static Polygon makeRectangle(Scanner scanner) {
        System.out.println("Constructing a new Rectangle");
        System.out.print("Enter left top point (x,y)");
        int[] leftTop = getPoint(scanner);
        System.out.print("Enter length of top");
        Integer topLength = getLength(scanner);
        if (topLength == null) {
            System.out.println("No length entered, making rectangle cancelled");
            return null;
        }
        System.out.print("Enter length of left side");
        Integer sideLength = getLength(scanner);
        if (sideLength == null) {
            System.out.println("No length entered, making rectangle cancelled");
            return null;
        }
        Rectangle rectangle = new Rectangle(leftTop[0], leftTop[1], topLength, sideLength);
        System.out.println("Rectangle perimeter is " + rectangle.perimeter());
        System.out.println("Rectangle area is " + rectangle.area());
        return rectangle;
    }

    private static int[] getPoint(Scanner scanner) {
        while(true) {
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
                int x = Integer.parseInt(xString);
                int y = Integer.parseInt(yString);
                return new int[] {x, y};
            } catch (NumberFormatException e) {
                //handle bad input
                System.out.println("Values must be integers, try again.");
            }

        }
    }

    private static Integer getLength(Scanner scanner) {
        while (true) {
            System.out.print(": ");
            String intString = scanner.nextLine();
            if (intString.length() == 0) {
                return null;
            }
            try {
                int length = Integer.parseInt(intString);
                if (length <= 0) {
                    throw new NumberFormatException("Length cannot be negative");
                }
                return length;
            } catch (NumberFormatException e) {
                System.out.println("Value must be a positive integer, try again.");
                continue;
            }
        }
    }
}

