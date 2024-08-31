package hotelManagementSystem.entities;

import hotelManagementSystem.utils.RoomType;

public class DeluxeRoom extends Room {
    public DeluxeRoom(int roomId) {
        super(roomId);
    }
    @Override
    public int getPrice() {
        return 300;
    }

    @Override
    public RoomType getType() {
        return RoomType.DELUXE;
    }
}
