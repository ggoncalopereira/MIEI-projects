public class Point {

	private double x;
	private double y;
	
	public Point() {
		x = 0;
		y = 0;
	}
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public Point diff(Point p1, Point p2) {
		double x = p1.getX() - p2.getX();
		double y = p1.getY() - p2.getY();
		Point p = new Point(Math.abs(x), Math.abs(y));
		return p;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(x);
		sb.append(",");
		sb.append(y);
		return sb.toString();
	}
	
}