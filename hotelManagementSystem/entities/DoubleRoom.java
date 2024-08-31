package LowLevelDesigns.hotelManagementSystem.entities;

import hotelManagementSystem.utils.RoomType;

public class DoubleRoom extends Room {

    public DoubleRoom(int roomId) {
        super(roomId);
    }

    @Override
    public int getPrice() {
        return 200;
    }

    @Override
    public RoomType getType() {
        return RoomType.DOUBLE;
    }
}
