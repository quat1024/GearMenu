package projgear.gearmenu.gui.component.button;

import net.minecraft.client.Minecraft;
import projgear.gearmenu.gui.component.GuiComponent;
import projgear.gearmenu.gui.component.label.GuiComponentLabel;
import projgear.gearmenu.math.IBox;

import javax.annotation.Nullable;

public class GuiComponentButton extends GuiComponent {
	public GuiComponentButton(@Nullable IBox box) {
		super(box);
	}
	
	GuiComponentLabel label;
	
	public GuiComponentLabel getLabel() {
		return label;
	}
	
	public void setLabel(GuiComponentLabel label) {
		label.setParent(this);
		this.label = label;
	}
	
	@Override
	public void draw(Minecraft mc) {
		//draw button lalala
		
		label.draw(mc);
	}
}
