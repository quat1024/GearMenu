package projgear.gearmenu.gui.math;

public class Box {
	private Box() {}
	
	public static Box fromCornerAndDimensions(double x, double y, double width, double height) {
		Box b = new Box();
		b.x = x;
		b.y = y;
		b.width = width;
		b.height = height;
		return b;
	}
	
	public static Box fromCenter(double centerX, double centerY, double width, double height) {
		Box b = new Box();
		b.x = centerX - width / 2;
		b.y = centerY - height / 2;
		b.width = width;
		b.height = height;
		return b;
	}
	
	public static Box fromCorners(double x1, double y1, double x2, double y2) {
		Box b = new Box();
		b.x = x1;
		b.y = y1;
		b.width = x2 - x1;
		b.height = y2 - y1;
		return b;
	}
	
	private double x, y, width, height;
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getWidth() {
		return width;
	}
	
	public double getHeight() {
		return height;
	}
}
