package projgear.gearmenu.ui.util;

public class Easer {
	public double value;
	public double target;
	public double exp;
	
	public Easer(double value) {
		this(value, 0.9d);
	}
	
	public Easer(double value, double exp) {
		this.value = value;
		this.target = value;
		this.exp = exp;
	}
	
	public void update(float partialTicks) {
		value += (target - value) * Math.pow(exp, partialTicks);
	}
	
	public double get() {
		return value;
	}
	
	public void setExp(double exp) {
		this.exp = exp;
	}
	
	public void setTarget(double target) {
		this.target = target;
	}
}
