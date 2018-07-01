package projgear.gearmenu.gui.component;

import net.minecraft.client.Minecraft;
import projgear.gearmenu.gui.event.GuiEventBus;
import projgear.gearmenu.math.IBox;

import javax.annotation.Nullable;

public abstract class GuiComponent implements IBox {
	public abstract void draw(Minecraft mc);
	
	public GuiComponent(@Nullable IBox box) {
		if(box != null) {
			setX(box.getX());
			setY(box.getY());
			setWidth(box.getWidth());
			setHeight(box.getHeight());
		}
	}
	
	@Nullable
	IBox parent;
	
	int x = 0;
	int y = 0;
	int width = 0;
	int height = 0;
	
	public final GuiEventBus<GuiComponent> clickEvents = new GuiEventBus<>();
	public final GuiEventBus<GuiComponent> beginHoverEvents = new GuiEventBus<>();
	public final GuiEventBus<GuiComponent> endHoverEvents = new GuiEventBus<>();
	
	boolean isHovered;
	
	public void update(int mouseX, int mouseY, float partialTicks) {
		boolean wasHovered = isHovered;
		isHovered = x < mouseX && x + width > mouseX && y < mouseY && y + height > mouseY;
		
		if(isHovered && !wasHovered) {
			beginHoverEvents.post(this);
		} else if (!isHovered && wasHovered) {
			endHoverEvents.post(this);
		}
	}
	
	public void handleClick(int mouseX, int mouseY) {
		if(isHovered) clickEvents.post(this);
	}
	
	@Nullable
	public IBox getParent() {
		return parent;
	}
	
	@Override
	public int getX() {
		return x;
	}
	
	@Override
	public int getY() {
		return y;
	}
	
	@Override
	public int getWidth() {
		return width;
	}
	
	@Override
	public int getHeight() {
		return height;
	}
	
	public void setParent(@Nullable IBox parent) {
		this.parent = parent;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
}
