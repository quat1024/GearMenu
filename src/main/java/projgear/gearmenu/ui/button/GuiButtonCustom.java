package projgear.gearmenu.ui.button;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.text.translation.I18n;
import projgear.gearmenu.ui.util.DrawingUtils;
import projgear.gearmenu.ui.util.Easer;

public class GuiButtonCustom extends GuiButton {
	public GuiButtonCustom(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
		super(buttonId, x, y, widthIn, heightIn, buttonText);
	}
	
	protected boolean translated = false;
	protected Easer vheightEaser = new Easer(1, .4d);
	protected Easer colorEaser = new Easer(0, .3d);
	
	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
		if(!translated) {
			displayString = I18n.translateToLocal(displayString);
			translated = true;
		}
		
		this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
		
		vheightEaser.update(partialTicks);
		vheightEaser.setTarget(hovered ? 1.3d : 1d);
		
		colorEaser.update(partialTicks);
		colorEaser.setTarget(hovered ? 1d : 0d);
		
		if (this.visible) {			
			GlStateManager.pushMatrix();
			GlStateManager.translate(x + width / 2, y + height / 2, 0);
			
			double colorProgress = colorEaser.get();
			float red   = (float) ((1 - colorProgress) * .5d + colorProgress * .8d);
			float green = (float) ((1 - colorProgress) * .5d + colorProgress * .8d);
			float blue  = (float) ((1 - colorProgress) * .5d + colorProgress * .3d);
			
			GlStateManager.color(red, green, blue, 1f);
			
			GlStateManager.scale(1d, vheightEaser.get(), 1d);
			DrawingUtils.drawCenteredRectangle(0, 0, width, height);
			GlStateManager.popMatrix();
			
			int textShade = (int) ((1 - colorProgress) * 240 + colorProgress * 30) & 0xFF;
			int packedTextColor = (textShade << 16) | (textShade << 8) | textShade;
			
			DrawingUtils.drawCenteredStringWithoutShadow(mc.fontRenderer, displayString, x + width / 2, y + (height - 8) / 2, packedTextColor);
		}
	}
	
	public void setTranslationKey(String translationKey) {
		displayString = translationKey;
		translated = false;
	}
}
