package LowLevelDesigns.hotelManagementSystem;

import LowLevelDesigns.hotelManagementSystem.entities.Guest;
import hotelManagementSystem.utils.GuestEventType;
import hotelManagementSystem.utils.RoomType;

public interface Observer {
    void update(GuestEventType guestEventType, Guest guest, RoomType roomType) ;
}
