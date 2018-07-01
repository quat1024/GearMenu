package projgear.gearmenu.gui.event;

import java.util.ArrayList;

public class GearEventBus<EVENTORIGINATOR> {
	private ArrayList<IEvent<EVENTORIGINATOR>> subscribers = new ArrayList<>();
	
	public void addEvent(IEvent<EVENTORIGINATOR> event) {
		subscribers.add(event);
	}
	
	public void post(EVENTORIGINATOR obj) {
		for(IEvent<EVENTORIGINATOR> listener : subscribers) {
			listener.run(obj);
		}
	}
}
