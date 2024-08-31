package hotelManagementSystem;

import hotelManagementSystem.entities.Guest;
import hotelManagementSystem.utils.GuestEventType;
import hotelManagementSystem.utils.RoomType;

import java.util.List;

public class GuestEvent implements Event {

    List<Observer> observers;

    public GuestEvent(List<Observer> observer) {
        this.observers = observer;
    }

    public void newEvent(GuestEventType guestEventType, Guest guest, RoomType roomType) {
        this.notifyObservers(guestEventType, guest, roomType);
    }

    @Override
    public void notifyObservers(GuestEventType guestEventType, Guest guest, RoomType roomType) {
        observers.forEach(observer -> observer.update(guestEventType, guest, roomType));
    }

}
