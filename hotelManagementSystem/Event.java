package hotelManagementSystem;

import hotelManagementSystem.entities.Guest;
import hotelManagementSystem.utils.GuestEventType;
import hotelManagementSystem.utils.RoomType;

public interface Event {
    void notifyObservers(GuestEventType guestEventType, Guest guest, RoomType roomType);
}
