package hotelManagementSystem.entities;

import hotelManagementSystem.utils.RoomType;

public class SingleRoom extends Room {

    public SingleRoom(int roomId) {
        super(roomId);
    }

    @Override
    public int getPrice() {
        return 100;
    }

    @Override
    public RoomType getType() {
        return RoomType.SINGLE;
    }
}
