package projgear.gearmenu.gui.component.label;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.translation.I18n;
import projgear.gearmenu.gui.component.GearGuiComponent;
import projgear.gearmenu.gui.math.Box;

public class TranslatedStringLabel extends GuiComponentLabelBase {
	String langKey;
	
	public TranslatedStringLabel(String langKey, Box b) {
		super(b);
		this.langKey = langKey;
	}
	
	public TranslatedStringLabel(String langKey, GearGuiComponent parent) {
		super(parent);
		this.langKey = langKey; //TODO this sucks, 2 constructors on everything?? no way.
	}
	
	@Override
	public void draw(Minecraft mc, float partialTicks) {
		FontRenderer font = Minecraft.getMinecraft().fontRenderer;
		String label = I18n.translateToLocal(langKey);
		
		int x = MathHelper.floor(getX() + getWidth() / 2) - font.getStringWidth(label) / 2;
		int y = MathHelper.floor(getY() + (getHeight() - 8) / 2);
		
		font.drawString(label, x, y, color, textShadow);
	}
}
