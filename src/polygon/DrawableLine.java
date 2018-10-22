package polygon;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.Arrays;
import java.util.Random;

/**
 * Line that draws on to a static frame instantiated by this Class
 */
class DrawableLine extends JComponent {

	static private final String FRAME_TITLE = "LINE DRAWER";
	static private final Dimension START_FRAME_DIMS = new Dimension(420, 420);
	static private final int VERTEX_SIZE = 10;
	static private final int LINE_WIDTH = 3;
	static private final int BOUNDS_BUFFER = 5;

	static private Random rand = new Random(10);  //set the seed so that testing is repeatable
	static private JFrame frame = null;

	private final Point topLeft;
	private Point[] points;
	private Color color;
	private boolean withVertices;
	private boolean removed;

	/**
	 * Creates a new line between specified points with vertices
	 * @param start starting point
	 * @param end end point
	 */
	DrawableLine(Point start, Point end) {
		this(start, end, true);
	}

	/**
	 * Creates a new line between specified points
	 * @param start starting point
	 * @param end end point
	 * @param withVertices indicating whether vertices are displayed
	 */
	DrawableLine(Point start, Point end, boolean withVertices) {
		if (frame == null) {
			initializeFrame();
		}

		points = new Point[] {start, end};
		this.withVertices = withVertices;

		//use a random color for each line
		color = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
		removed = false;

		topLeft = new Point(
			start.getX() < end.getX() ? start.getX() : end.getX(),
			start.getY() < end.getY() ? start.getY() : end.getY());

		//bounds include a buffer for the size of the vertices
		setBounds(
				topLeft.getX()  - BOUNDS_BUFFER,
				topLeft.getY() - BOUNDS_BUFFER,
				Math.abs(start.getX() - end.getX()) + 2 * BOUNDS_BUFFER,
				Math.abs(start.getY() - end.getY()) + 2 * BOUNDS_BUFFER);
		frame.add(this, 0);
		frame.validate();
		frame.repaint();
	}

	/**
	 * Create the frame when first line is instantiated
	 */
	private static void initializeFrame() {
		frame = new JFrame();
		frame.setTitle(FRAME_TITLE);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		JPanel contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setPreferredSize(START_FRAME_DIMS);
		frame.setContentPane(contentPane);
		contentPane.setOpaque(true);
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * Removes line from frame
	 */
	void remove() {
		removed = true;
		frame.remove(this);  //so we don't leave it around!
        frame.revalidate();
        frame.repaint();
	}

	/**
	 * Accessor to start point
	 * @return starting point
	 */
	Point getStart() {
		return points[0];
	}

	/**
	 * Accessor to end point
	 * @return end point
	 */
	Point getEnd() {
		return points[1];
	}

	/**
	 * Paints the line and, if specified, the vertices at the end of the line
	 */
	@Override
	protected void paintComponent(Graphics g) {
		if (removed) {
			return;
		}
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(color);
		g2.setStroke(new BasicStroke(LINE_WIDTH));
		//position is relative to the line's component, not the overall frame
		g2.draw(new Line2D.Float(
				points[0].getX() - topLeft.getX() + BOUNDS_BUFFER,
				points[0].getY() - topLeft.getY() + BOUNDS_BUFFER,
				points[1].getX() - topLeft.getX() + BOUNDS_BUFFER,
				points[1].getY() - topLeft.getY() + BOUNDS_BUFFER));

		g2.setColor(Color.BLACK);
		if (withVertices) {
			for (int i = 0; i < 2; i++) {
				Ellipse2D.Double circle = new Ellipse2D.Double(
						points[i].getX() - topLeft.getX() - VERTEX_SIZE / 2 + BOUNDS_BUFFER,
						points[i].getY() - topLeft.getY() - VERTEX_SIZE / 2 + BOUNDS_BUFFER,
						VERTEX_SIZE, VERTEX_SIZE);
				g2.fill(circle);
			}
		}

	}

	@Override
	public String toString() {
		return "DrawableLine:" + Arrays.toString(points);
	}
}


