package projgear.gearmenu.gui.math;

public class Easer {
	double number;
	double target;
	
	double exp;
	
	public Easer(double initial, double exp) {
		this.number = initial;
		this.target = initial;
		this.exp = exp;
	}
	
	public void setTarget(double newTarget) {
		this.target = newTarget;
	}
	
	public void setExp(double newExp) {
		this.exp = newExp;
	}
	
	public void update(double partialTicks) {
		number += (target - number) * exp * partialTicks;
	}
	
	public double get() {
		return number;
	}
}
