package projgear.gearmenu.gui.component.label;

import projgear.gearmenu.gui.component.GuiComponent;
import projgear.gearmenu.math.IBox;

import javax.annotation.Nullable;

public abstract class GuiComponentLabel extends GuiComponent {
	protected GuiComponentLabel(@Nullable IBox box) {
		super(box);
	}
}
