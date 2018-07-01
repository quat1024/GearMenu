package projgear.gearmenu.gui.screen;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.client.GuiModList;
import org.apache.commons.lang3.tuple.Pair;
import org.lwjgl.opengl.GL11;
import projgear.gearmenu.gui.component.GearGuiComponent;
import projgear.gearmenu.gui.component.button.*;
import projgear.gearmenu.gui.component.label.TranslatedStringLabel;
import projgear.gearmenu.gui.math.Box;

import java.io.IOException;
import java.util.List;

public class GuiMainMenuReplacement extends GuiScreen {
	NonNullList<GearGuiComponent> guiObjects;
	
	final List<Pair<String, Runnable>> buttonProps = ImmutableList.of(
					Pair.of("menu.singleplayer", () -> mc.displayGuiScreen(new GuiWorldSelection(this))),
					Pair.of("menu.multiplayer", () -> mc.displayGuiScreen(new GuiMultiplayer(this))),
					Pair.of("fml.menu.mods", () -> mc.displayGuiScreen(new GuiModList(this))),
					Pair.of("gearmenu.options", () -> mc.displayGuiScreen(new GuiOptions(this, mc.gameSettings))),
					Pair.of("gearmenu.language", () -> mc.displayGuiScreen(new GuiLanguage(this, mc.gameSettings, mc.getLanguageManager()))),
					Pair.of("menu.quit", () -> mc.shutdown())
	);
	
	public void initGui() {		
		guiObjects = NonNullList.create();
		float aspect = width / (float) height;
		
		if(aspect > 1.6) {
			//widescreen-like; display on one row
			
			double border = 20;
			double buttonWidth = (width - border * 2) / 6;
			for(int i = 0; i < 6; i++) {
				double startX = border + buttonWidth * i;
				
				GearButtonMain b = new GearButtonMain(Box.fromCornerAndDimensions(startX, height / 2, buttonWidth, 20));
				b.setLabel(new TranslatedStringLabel(buttonProps.get(i).getLeft(), b));
				final Runnable action = buttonProps.get(i).getRight();
				b.clickEvents.addEvent((clicked) -> {
					action.run();
				});
				
				guiObjects.add(b);
			}
		} else {
			//display on two rows
			double border = 20;
			double buttonWidth = (width - border * 2) / 3;
			
			int i=0;
			for(int row = 0; row <= 1; row++) {
				for(int column = -1; column <= 1; column++) {
					double centerX = (width / 2) + buttonWidth * column;
					double centerY = (height / 2) + 35 * row;
					
					GearButtonMain b = new GearButtonMain(Box.fromCenter(centerX, centerY, buttonWidth, 20));
					b.setLabel(new TranslatedStringLabel(buttonProps.get(i).getLeft(), b));
					final Runnable action = buttonProps.get(i).getRight();
					b.clickEvents.addEvent((clicked) -> {
						action.run();
					});
					
					guiObjects.add(b);
					i++;
				}
			}
		}
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {		
		//todo Textured background, not glCleared background
		GlStateManager.clearColor(0.1f, 0.1f, 0.1f, 1);
		GlStateManager.clear(GL11.GL_COLOR_BUFFER_BIT);
		
		//Hello!
		drawCenteredString(fontRenderer, "Hi Minecraft!", width/2, height/3, 0xFF8800);
		
		Minecraft mc = Minecraft.getMinecraft();
		
		for(GearGuiComponent b : guiObjects) {
			b.update(mouseX, mouseY, partialTicks);
			b.draw(mc, partialTicks);
		}
	}
	
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		if(mouseButton == 0) {
			for(GearGuiComponent b : guiObjects) {
				b.tryClick();
			}
		}
	}
	
	@Override
	public void onGuiClosed() {
		super.onGuiClosed();
	}
}
