package projgear.gearmenu.gui.component.label;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import projgear.gearmenu.math.IBox;

import javax.annotation.Nullable;

public class GuiComponentTextLabel extends GuiComponentLabel {
	protected GuiComponentTextLabel(@Nullable IBox box) {
		super(box);
	}
	
	boolean textShadow;
	int color;
	String str;
	
	public boolean hasTextShadow() {
		return textShadow;
	}
	
	public void setTextShadow(boolean textShadow) {
		this.textShadow = textShadow;
	}
	
	public int getColor() {
		return color;
	}
	
	public void setColor(int color) {
		this.color = color;
	}
	
	public String getStr() {
		return str;
	}
	
	public void setStr(String str) {
		this.str = str;
	}
	
	@Override
	public void draw(Minecraft mc) {
		FontRenderer renderer = mc.fontRenderer;
		
		renderer.drawString(getDisplayedString(), getX(), getY(), getColor(), hasTextShadow());
	}
	
	protected String getDisplayedString() {
		return getStr();
	}
}
