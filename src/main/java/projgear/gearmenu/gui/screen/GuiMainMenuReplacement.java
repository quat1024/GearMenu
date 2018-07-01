package projgear.gearmenu.gui.screen;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;
import projgear.gearmenu.gui.component.GuiComponent;
import projgear.gearmenu.gui.component.label.GuiComponentTranslatedStringLabel;
import projgear.gearmenu.math.Box;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GuiMainMenuReplacement extends GuiScreen {
	List<GuiComponent> components = new ArrayList<>();
	
	@Override
	public void initGui() {
		components.clear();
		
		GuiComponentTranslatedStringLabel lbl = new GuiComponentTranslatedStringLabel(new Box(40, 40, 100, 100));
		lbl.setStr("menu.singleplayer");
		
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		GL11.glClearColor(0.1f, 0.1f, 0.1f, 1f);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		
		for(GuiComponent component : components) {
			component.update(mouseX, mouseY, partialTicks);
			component.draw(mc);
		}
	}
	
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		if(mouseButton == 0) {
			for(GuiComponent component : components) {
				component.handleClick(mouseX, mouseY);
			}
		}
	}
}
