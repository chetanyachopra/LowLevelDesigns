package hotelManagementSystem;

import hotelManagementSystem.entities.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import hotelManagementSystem.utils.GuestEventType;
import hotelManagementSystem.utils.RoomType;

public class Hotel {
    public static void main(String[] args) {
        InventoryRepository inventoryRepository = InventoryRepository.getInstance(intializeAvailableRooms());
        List<Observer> observers = new ArrayList<>();
        observers.add(new BookingService(inventoryRepository, GuestRepository.getInstance()));

        GuestEvent guestEvent = new GuestEvent(observers);

        Guest guest1 = new Guest(1);
        Guest guest2 = new Guest(2);
        guestEvent.newEvent(GuestEventType.BOOKING, guest1, RoomType.SUIT);
        guestEvent.newEvent(GuestEventType.CHECK_IN, guest1, null);
        guestEvent.newEvent(GuestEventType.BOOKING, guest2, RoomType.DELUXE);
        guestEvent.newEvent(GuestEventType.CHECK_IN, guest2, null);
        guestEvent.newEvent(GuestEventType.CHECK_OUT, guest1, null);


    }

    private static ConcurrentHashMap<RoomType, Queue<Room>> intializeAvailableRooms() {
        LinkedList<Room> singleRoom = new LinkedList<>();
        LinkedList<Room> doubleRoom = new LinkedList<>();
        LinkedList<Room> deluxRooms = new LinkedList<>();
        LinkedList<Room> suitRooms = new LinkedList<>();
        for(int i=1;i<=10;i++) {
            singleRoom.add(new SingleRoom(100+i));
            if(i <= 6) doubleRoom.add(new DoubleRoom(200+i));
            if(i <= 3) deluxRooms.add(new DeluxeRoom(300+i));
            if(i <= 1) suitRooms.add(new SuitRoom(400+i));
        }
        ConcurrentHashMap<RoomType, Queue<Room>> availableRooms = new ConcurrentHashMap<>();
        availableRooms.put(RoomType.SINGLE, singleRoom);
        availableRooms.put(RoomType.DOUBLE, doubleRoom);
        availableRooms.put(RoomType.DELUXE, deluxRooms);
        availableRooms.put(RoomType.SUIT, suitRooms);
        return availableRooms;
    }
}
