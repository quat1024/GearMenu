package projgear.gearmenu.ui.util;

import java.util.Random;

public class MathUtil {
	private MathUtil() {}
	
	public static double nextDouble(Random random, double low, double high) {
		return random.nextDouble() * (high - low) + low;
	}
}
