package LowLevelDesigns.hotelManagementSystem.repositories;

import LowLevelDesigns.hotelManagementSystem.entities.Room;
import hotelManagementSystem.utils.RoomType;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class InventoryRepository {


    private ConcurrentHashMap<RoomType, Queue<Room>> availableRooms = new ConcurrentHashMap<>();
    private ConcurrentHashMap<RoomType, Set<Room>> bookedRooms = new ConcurrentHashMap<>();

    private static InventoryRepository __instance__;
    private InventoryRepository(ConcurrentHashMap<RoomType, Queue<Room>> initialRooms){
        initialRooms.forEach(((roomType, rooms) -> availableRooms.put(roomType, new ConcurrentLinkedQueue<>(rooms))));
    }

    public static synchronized InventoryRepository getInstance(ConcurrentHashMap<RoomType, Queue<Room>> initialRooms) {
        if(__instance__ != null) {
            return __instance__;
        }
        __instance__ = new InventoryRepository(initialRooms);
        return __instance__;
    }

    public void addToBookedRooms(Room room) {
        this.bookedRooms.computeIfAbsent(room.getType(), r-> new HashSet<>()).add(room);
    }

    public void addToAvailableRooms(Room room) {
        this.availableRooms.computeIfAbsent(room.getType(), r-> new LinkedList<>()).add(room);
    }

    public void removeFromBookedRooms(Room room) throws Exception {
        if(!this.bookedRooms.containsKey(room.getType()))
            throw new Exception("Room not Booked");
        this.bookedRooms.get(room.getType()).remove(room);
    }

    public ConcurrentHashMap<RoomType, Queue<Room>> getAvailableRooms() {
        return availableRooms;
    }

    public ConcurrentHashMap<RoomType, Set<Room>> getBookedRooms() {
        return bookedRooms;
    }


}
