package projgear.gearmenu.gui.math;

public class MathUtil {
	public static double rangeRemap(double value, double low1, double high1, double low2, double high2) {
		return low2 + (value - low1) * (high2 - low2) / (high1 - low1);
	}
}
