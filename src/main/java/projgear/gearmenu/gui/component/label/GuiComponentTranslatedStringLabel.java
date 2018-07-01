package projgear.gearmenu.gui.component.label;

import net.minecraft.util.text.translation.I18n;
import projgear.gearmenu.math.IBox;

import javax.annotation.Nullable;

public class GuiComponentTranslatedStringLabel extends GuiComponentTextLabel {
	public GuiComponentTranslatedStringLabel(@Nullable IBox box) {
		super(box);
	}
	
	@Override
	protected String getDisplayedString() {
		return I18n.translateToLocal(getStr());
	}
}
