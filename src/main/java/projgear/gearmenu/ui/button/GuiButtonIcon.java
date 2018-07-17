package projgear.gearmenu.ui.button;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import projgear.gearmenu.GearMenu;

public class GuiButtonIcon extends GuiButton {
	private final ResourceLocation resloc;
	
	public GuiButtonIcon(int id, int x, int y, int w, int h, String buttonTexture) {
		super(id, x, y, w, h, "");
		
		resloc = new ResourceLocation(GearMenu.MODID, "textures/gui/button/" + buttonTexture + ".png");
	}
	
	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
		if(visible) {
			hovered = mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height;
			
			int vOffset = hovered ? 0 : height;
			
			mc.getTextureManager().bindTexture(resloc);
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.enableBlend();
			GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
			GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
			
			drawTexturedModalRect(x, y, 0, vOffset, width, height);
			drawModalRectWithCustomSizedTexture(x, y, 0, vOffset, width, height, width, height * 2);
			//drawTexturedModalRect(x + width / 2, y, 200 - width / 2, vOffset, width / 2, height);
			
			mouseDragged(mc, mouseX, mouseY);
		}
	}
}
