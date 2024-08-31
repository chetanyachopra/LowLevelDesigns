package LowLevelDesigns.hotelManagementSystem.entities;

import hotelManagementSystem.utils.RoomType;

public class SuitRoom extends Room {

    public SuitRoom(int roomId) {
        super(roomId);
    }

    @Override
    public int getPrice() {
        return 400;
    }

    @Override
    public RoomType getType() {
    return RoomType.SUIT;
    }
}
