package projgear.gearmenu;

import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.Display;
import projgear.gearmenu.ui.GuiMainMenuReplacement;

@Mod(modid = GearMenu.MODID, name = GearMenu.NAME, version = GearMenu.VERSION, clientSideOnly = true)
public class GearMenu {
	public static final String MODID = "gearmenu";
	public static final String NAME = "Project Gear Main Menu";
	public static final String VERSION = "1.0";
	
	@Mod.EventBusSubscriber
	public static class Events {
		private static boolean changedTitle = false;
		
		@SubscribeEvent
		public static void openGui(GuiOpenEvent e) {
			if(e.getGui() instanceof GuiMainMenu) {
				if(!changedTitle) {
					Display.setTitle(Display.getTitle() + " - Project Gear");
					changedTitle = true;
				}
				e.setGui(new GuiMainMenuReplacement());
			}
		}
	}
}
