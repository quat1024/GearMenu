package projgear.gearmenu.gui.event;

import java.util.ArrayList;
import java.util.function.Consumer;

public class GuiEventBus<T> {
	private ArrayList<Consumer<T>> events = new ArrayList<>();
	
	public void addEvent(Consumer<T> event) {
		events.add(event);
	}
	
	public void clearEvents() {
		events.clear();
	}
	
	public void post(T inst) {
		for(Consumer<T> event : events) {
			event.accept(inst);
		}
	}
}
