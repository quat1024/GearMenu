package projgear.gearmenu.ui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.client.GuiModList;
import org.lwjgl.opengl.GL11;
import projgear.gearmenu.GearMenu;
import projgear.gearmenu.ui.button.GuiButtonCustom;
import projgear.gearmenu.ui.util.DrawingUtils;

import java.io.IOException;

public class GuiMainMenuReplacement extends GuiScreen {
	
	private static final String[] buttonTranslationKeys = new String[]{
					"menu.singleplayer",
					"menu.multiplayer",
					"fml.menu.mods",
					"gearmenu.language",
					"gearmenu.options",
					"menu.quit"
	};
	
	int sideGutter;
	int startButtonY;
	
	@Override
	public void initGui() {
		ScaledResolution res = new ScaledResolution(mc);
		float aspect = (float) res.getScaledWidth() / res.getScaledHeight();
		
		int rows;
		
		int buttonHeight;
		int buttonVerticalSpacing;
		
		if(aspect < 1f) {
			//really tall aspect? display on 1 column... i guess?
			rows = 6;
			buttonHeight = 30;
			buttonVerticalSpacing = 7;
		} else if(aspect < 1.7f) {
			//standard aspect. display on 2 rows
			rows = 2;
			buttonHeight = 20;
			buttonVerticalSpacing = 5;
		} else {
			//widescreen aspect. display on 1 row
			rows = 1;
			buttonHeight = 30;
			buttonVerticalSpacing = 10;
		}
		
		int columns = 6 / rows;
		
		sideGutter = 20;
		int buttonPadding = 3;
		
		startButtonY = res.getScaledHeight() / 2;
		
		//screen width = (gutter * 2) + (buttonWidth * columns) + (buttonPadding * (columns - 1))
		//solve for button width:
		int buttonWidth = (res.getScaledWidth() - (sideGutter * 2) - (buttonPadding * (columns - 1))) / columns;
		
		int i=0;
		for(int row = 0; row < rows; row++) {
			for(int column = 0; column < columns; column++) {
				//don't apply spacing between buttons etc when you're the first one in the row or column!
				int effectiveVerticalSpacing = (row == 0) ? 0 : buttonVerticalSpacing;
				int effectiveButtonPadding = (column == 0) ? 0 : buttonPadding;
				
				int buttonX = sideGutter + (buttonWidth + effectiveButtonPadding) * column;
				int buttonY = startButtonY + (buttonHeight + effectiveVerticalSpacing) * row;
				
				String buttonTranslationKey = buttonTranslationKeys[i];
				
				addButton(new GuiButtonCustom(i, buttonX, buttonY, buttonWidth, buttonHeight, buttonTranslationKey));
				i++;
			}
		}
	}
	
	private static final ResourceLocation BG_TILE_RES = new ResourceLocation(GearMenu.MODID, "textures/gui/bg_tile.png");
	private static final int BG_TILE_SIZE = 128;
	
	private static final ResourceLocation LOGO_RES = new ResourceLocation(GearMenu.MODID, "textures/gui/main_logo.png");
	private static final int LOGO_WIDTH = 203;
	private static final int LOGO_HEIGHT = 61;
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {		
		//Just in case
		GL11.glClearColor(.1f, .1f, .1f, 1f);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		//Draw background
		ScaledResolution res = new ScaledResolution(mc);
		
		int neededXTiles = MathHelper.ceil(res.getScaledWidth() / (float) BG_TILE_SIZE);
		int neededYTiles = MathHelper.ceil(res.getScaledHeight() / (float) BG_TILE_SIZE);
		
		mc.renderEngine.bindTexture(BG_TILE_RES);
		GlStateManager.color(1f, 1f, 1f, 1f);
		//Idk why i have to step by 2 here
		//If I don't do it though, the picture tiles too often.
		for(int x = 0; x < neededXTiles; x+=2) {
			for(int y = 0; y < neededYTiles; y+=2) {
				drawTexturedModalRect(x * BG_TILE_SIZE, y * BG_TILE_SIZE, 0, 0, BG_TILE_SIZE * 2, BG_TILE_SIZE * 2);
			}
		}
		
		//Draw GEARS!!!
		double t = Minecraft.getSystemTime() / 3000d;
		
		GlStateManager.color(.3f, .3f, .3f, 1f);
		DrawingUtils.drawGear(res.getScaledWidth() + 5, 0, 25, 50, 1.3, t, 10);
		DrawingUtils.drawGear(-20, res.getScaledHeight(), 30, 65, 1.2, t * 1.2, 9);
		DrawingUtils.drawGear(30, -30, 40, 80, 1.3, t*1.4, 15);
		GlStateManager.color(.25f, .25f, .25f, 1f);
		DrawingUtils.drawGear(140, res.getScaledHeight() + 10, 60, 75, 1.2, (-t * 1.2) - 0.15, 9);
		DrawingUtils.drawGear(res.getScaledWidth(), res.getScaledHeight(), 10, 20, 1.4, t * .7, 10);
		
		//Draw buttons
		super.drawScreen(mouseX, mouseY, partialTicks);
		
		//Draw logo
		GlStateManager.color(1f, 1f, 1f, 1f);
		mc.renderEngine.bindTexture(LOGO_RES);
		drawTexturedModalRect(sideGutter, startButtonY - LOGO_HEIGHT - 20, 0, 0, LOGO_WIDTH, LOGO_HEIGHT);
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		switch(button.id) {
			case 0: {
				mc.displayGuiScreen(new GuiWorldSelection(this));
				break;
			}
			case 1: {
				mc.displayGuiScreen(new GuiMultiplayer(this));
				break;
			}
			case 2: {
				mc.displayGuiScreen(new GuiModList(this));
				break;
			}
			case 3: {
				mc.displayGuiScreen(new GuiLanguage(this, mc.gameSettings, mc.getLanguageManager()));
				break;
			}
			case 4: {
				mc.displayGuiScreen(new GuiOptions(this, mc.gameSettings));
				break;
			}
			case 5: {
				if(button instanceof GuiButtonCustom) {
					//lmao
					((GuiButtonCustom)button).setTranslationKey("gearmenu.bye");
					button.drawButton(mc, 0, 0, 1);
				}
				mc.shutdown();
				break;
			}
		}
	}
}
