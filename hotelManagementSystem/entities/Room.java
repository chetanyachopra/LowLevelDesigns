package LowLevelDesigns.hotelManagementSystem.entities;

import hotelManagementSystem.utils.RoomStatus;
import hotelManagementSystem.utils.RoomType;

public abstract class Room {
    //To uniqiley identify the room
    private int id;
    //To get status of room
    private RoomStatus roomStatus;



    //To find who is staying in current room
    //1 - 1 relationship
    private Guest tenant;

    public Room(int id) {
        this.id = id;
        roomStatus = RoomStatus.AVAILABLE;
    }

    public abstract int getPrice();
    public abstract RoomType getType();

    public RoomStatus getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(RoomStatus roomStatus) {
        this.roomStatus = roomStatus;
    }

    public Guest getTenant() {
        return tenant;
    }

    public void setTenant(Guest tenant) {
        this.tenant = tenant;
    }
}
