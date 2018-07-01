package projgear.gearmenu.gui.component.label;

import projgear.gearmenu.gui.component.GearGuiComponent;
import projgear.gearmenu.gui.math.Box;

public abstract class GuiComponentLabelBase extends GearGuiComponent {
	public GuiComponentLabelBase(Box b) {
		super(b);
	}
	
	public GuiComponentLabelBase(GearGuiComponent parent) {
		super(parent);
	}
	
	boolean textShadow = false;
	int color = 0xFFFFFF;
	
	public boolean hasTextShadow() {
		return textShadow;
	}
	
	public void useTextShadow(boolean textShadow) {
		this.textShadow = textShadow;
	}
	
	public int getColor() {
		return color;
	}
	
	public void setColor(int color) {
		this.color = color;
	}
}
