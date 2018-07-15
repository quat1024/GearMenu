package projgear.gearmenu.ui.util;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;

public class DrawingUtils {
	private DrawingUtils() {}
	
	public static final double TAU = Math.PI * 2; //Yeah
	
	//Set your own color with GLStateManager before calling.
	public static void drawCenteredRectangle(int centerX, int centerY, int width, int height) {
		int left = centerX - width / 2;
		int right = centerX + width / 2;
		int top = centerY - height / 2;
		int bottom = centerY + height / 2;
		
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferbuilder = tessellator.getBuffer();
		GlStateManager.enableBlend();
		GlStateManager.disableTexture2D();
		GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION);
		bufferbuilder.pos((double)left, (double)bottom, 0.0D).endVertex();
		bufferbuilder.pos((double)right, (double)bottom, 0.0D).endVertex();
		bufferbuilder.pos((double)right, (double)top, 0.0D).endVertex();
		bufferbuilder.pos((double)left, (double)top, 0.0D).endVertex();
		tessellator.draw();
		GlStateManager.enableTexture2D();
		GlStateManager.disableBlend();
	}
	
	public static void drawCenteredStringWithoutShadow(FontRenderer font, String text, int x, int y, int color) {
		font.drawString(text, x - font.getStringWidth(text) / 2, y, color, false);
	}
	
	public static void drawGear(double centerX, double centerY, double innerRadius, double outerRadius, double outerRadiusToothMultiplier, double rotation, int teeth) {
		GlStateManager.disableTexture2D();
		GlStateManager.enableBlend();
		GlStateManager.disableAlpha();
		GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
		GlStateManager.pushMatrix();
		GlStateManager.translate(centerX, centerY, 0);
		
		Tessellator t = Tessellator.getInstance();
		BufferBuilder buf = t.getBuffer();
		
		buf.begin(GL11.GL_TRIANGLE_STRIP, DefaultVertexFormats.POSITION);
		
		int vertsPerTooth = 8;
		
		double outerRadiusMul = outerRadius * outerRadiusToothMultiplier;
		
		int maxSteps = teeth * vertsPerTooth;
		for(int step = 0; step <= maxSteps; step++) {
			double angle = rotation + (TAU * step / maxSteps);
			double angleCos = Math.cos(angle);
			double angleSin = Math.sin(angle);
			
			double innerX = angleCos * innerRadius;
			double innerY = angleSin * innerRadius;
			
			double outerRadToothApplied = (step % vertsPerTooth < vertsPerTooth / 2) ? outerRadius : outerRadiusMul;
			
			double outerX = angleCos * outerRadToothApplied;
			double outerY = angleSin * outerRadToothApplied;
			
			buf.pos(innerX, innerY, innerY).endVertex();
			buf.pos(outerX, outerY, outerY).endVertex();
		}
		
		t.draw();
		
		GlStateManager.popMatrix();
		GlStateManager.disableBlend();
		GlStateManager.enableAlpha();
		GlStateManager.enableTexture2D();
	}
}
