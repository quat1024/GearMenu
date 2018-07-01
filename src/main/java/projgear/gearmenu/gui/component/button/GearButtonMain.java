package projgear.gearmenu.gui.component.button;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;
import projgear.gearmenu.gui.math.Box;
import projgear.gearmenu.gui.math.Easer;

public class GearButtonMain extends GuiComponentButton {
	Easer extraButtonHeight;
	
	public GearButtonMain(Box b) {
		super(b);
		
		extraButtonHeight = new Easer(0, .4);
		
		startHoverEvents.addEvent((button) -> {
			extraButtonHeight.setTarget(.4);
			extraButtonHeight.setExp(.8);
		});
		
		endHoverEvents.addEvent((button) -> {
			extraButtonHeight.setTarget(0);
			extraButtonHeight.setExp(.4);
		});
	}
	
	public void update(int mouseX, int mouseY, float partialTicks) {
		super.update(mouseX, mouseY, partialTicks);
		
		extraButtonHeight.update(partialTicks);
	}
	
	@Override
	public void draw(Minecraft mc, float partialTicks) {
		GlStateManager.color(1, 1, 1, 1);
		GlStateManager.disableTexture2D();
		GlStateManager.enableBlend();
		GlStateManager.enableAlpha();
		GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
		
		double heightScale = extraButtonHeight.get();
		double x = getX();
		double y = getY();
		double width = getWidth();
		double height = getHeight();
		
		rect(x, y - (height * heightScale), width, height + (2 * height * heightScale), 0xFF8800);
		
		GlStateManager.disableAlpha();
		GlStateManager.disableBlend();
		GlStateManager.enableTexture2D();
		
		getLabel().draw(mc, partialTicks);
	}
	
	private void rect(double x, double y, double w, double h, int color) {
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder buf = tessellator.getBuffer();
		
		float r = ((color & 0xFF0000) >> 16) / 255f;
		float g = ((color & 0x00FF00) >> 8) / 255f;
		float b = (color & 0x0000FF) / 255f;
		
		buf.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);
		buf.pos(x, y, 0).color(r, g, b, 1f).endVertex();
		buf.pos(x, y + h, 0).color(r, g, b, 1f).endVertex();
		buf.pos(x + w, y + h, 0).color(r, g, b, 1f).endVertex();
		buf.pos(x + w, y, 0).color(r, g, b, 1f).endVertex();
		tessellator.draw();
	}
}
