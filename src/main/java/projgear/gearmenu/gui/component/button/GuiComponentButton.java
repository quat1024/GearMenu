package projgear.gearmenu.gui.component.button;

import projgear.gearmenu.gui.component.GearGuiComponent;
import projgear.gearmenu.gui.component.label.GuiComponentLabelBase;
import projgear.gearmenu.gui.math.Box;

public abstract class GuiComponentButton extends GearGuiComponent {
	public GuiComponentButton(Box b) {
		super(b);
	}
	
	private GuiComponentLabelBase label = null;
	
	public GuiComponentLabelBase getLabel() {
		return label;
	}
	
	public void setLabel(GuiComponentLabelBase label) {
		this.label = label;
	}
}
