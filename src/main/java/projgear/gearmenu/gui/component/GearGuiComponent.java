package projgear.gearmenu.gui.component;

import net.minecraft.client.Minecraft;
import projgear.gearmenu.gui.event.GearEventBus;
import projgear.gearmenu.gui.math.Box;

import javax.annotation.Nullable;

public abstract class GearGuiComponent {
	public GearGuiComponent(Box b) {
		this.x = b.getX();
		this.y = b.getY();
		this.width = b.getWidth();
		this.height = b.getHeight();
	}
	
	public GearGuiComponent(GearGuiComponent parent) {
		this.parent = parent;
	}
	
	@Nullable
	private GearGuiComponent parent;
	
	private double x = 0;
	private double y = 0;
	private double width = 0;
	private double height = 0;
	
	public GearEventBus<GearGuiComponent> clickEvents = new GearEventBus<>();
	public GearEventBus<GearGuiComponent> startHoverEvents = new GearEventBus<>();
	public GearEventBus<GearGuiComponent> endHoverEvents = new GearEventBus<>();
	
	public boolean hovered = false;
	
	public void update(int mouseX, int mouseY, float partialTicks) {
		boolean wasHovered = hovered;
		hovered = getX() <= mouseX && getX() + getWidth() >= mouseX && getY() <= mouseY && getY() + getHeight() >= mouseY;
		
		if(hovered && !wasHovered) {
			startHoverEvents.post(this);
		} else if(!hovered && wasHovered) {
			endHoverEvents.post(this);
		}
	}
	
	public void tryClick() {
		if(hovered) {
			clickEvents.post(this);
		}
	}
	
	public double getX() {
		if(parent == null) return x;
		else return parent.getX() + x;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public double getY() {
		if(parent == null) return y;
		else return parent.getY() + y;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public double getWidth() {
		return width;
	}
	
	public void setWidth(double width) {
		this.width = width;
	}
	
	public double getHeight() {
		return height;
	}
	
	public void setHeight(double height) {
		this.height = height;
	}
	
	@Nullable
	public GearGuiComponent getParent() {
		return parent;
	}
	
	public void setParent(@Nullable GearGuiComponent parent) {
		this.parent = parent;
	}
	
	public void clearParent() {
		parent = null;
	}
	
	public Box getBox() {
		return Box.fromCornerAndDimensions(getX(), y, width, height);
	}
	
	public abstract void draw(Minecraft mc, float partialTicks);
}
