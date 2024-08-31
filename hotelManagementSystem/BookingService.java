package hotelManagementSystem;

import hotelManagementSystem.entities.Guest;
import hotelManagementSystem.entities.Room;
import hotelManagementSystem.utils.GuestEventType;
import hotelManagementSystem.utils.ReservationStatus;
import hotelManagementSystem.utils.RoomStatus;
import hotelManagementSystem.utils.RoomType;

import java.util.Queue;
import java.util.List;


public class BookingService implements  Observer {

    InventoryRepository inventoryRepository;
    GuestRepository guestRepository;

    public BookingService(InventoryRepository inventoryRepository, GuestRepository guestRepository) {
        this.inventoryRepository = inventoryRepository;
        this.guestRepository = guestRepository;
    }

    @Override
    public void update(GuestEventType guestEventType, Guest guest, RoomType roomType) {
        switch(guestEventType) {
            case CHECK_IN:
                this.guestCheckIn(guest);
                break;
            case CHECK_OUT:
                //TODO: what if room is not booked.
                this.guestCheckout(guest);
                break;
            case BOOKING:
                try {
                    this.bookRoom(roomType, guest);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
        }

    }

    private void guestCheckIn(Guest guest) {
        List<Room> assignedRooms = this.guestRepository.getGuestMap().get(guest.getId()).getAssignedRooms();
        assignedRooms.forEach(room -> room.setRoomStatus(RoomStatus.OCCUPIED));
        guest.setReservationStatus(ReservationStatus.CHECKED_IN);
    }

    private synchronized void  guestCheckout(Guest guest) {
        List<Room> assignedRooms = guest.getAssignedRooms();
        guest.setReservationStatus(ReservationStatus.CHECKED_OUT);
        assignedRooms.forEach(room -> {
            try {
                inventoryRepository.removeFromBookedRooms(room);
                room.setRoomStatus(RoomStatus.AVAILABLE);
                inventoryRepository.addToAvailableRooms(room);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

    }

    private synchronized void  bookRoom(RoomType roomType, Guest guest) throws Exception {
        Queue<Room> availableRooms = inventoryRepository.getAvailableRooms().get(roomType);
        if(availableRooms == null || availableRooms.isEmpty()) {
            // can also mark gueset as waiting and notify / allocate room once free.
            throw new Exception("Room not found");
        }
        Room room = availableRooms.poll();
        room.setRoomStatus(RoomStatus.BOOKED);
        room.setTenant(guest);
        inventoryRepository.addToBookedRooms(room);

        guest.addToAssignedRooms(room);
        guest.setReservationStatus(ReservationStatus.BOOKED);
        guestRepository.addToGuestMap(guest);

    }
}
