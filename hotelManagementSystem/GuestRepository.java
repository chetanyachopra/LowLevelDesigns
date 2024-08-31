package hotelManagementSystem;

import hotelManagementSystem.entities.Guest;

import java.util.concurrent.ConcurrentHashMap;

public class GuestRepository {
    private ConcurrentHashMap<Integer, Guest> guestMap = new ConcurrentHashMap<>();

    private static GuestRepository __instance__;

    public static synchronized  GuestRepository getInstance() {
        if(__instance__ != null) {
            return __instance__;
        }
        __instance__ = new GuestRepository();
        return __instance__;
    }

    public void addToGuestMap(Guest guest) {guestMap.putIfAbsent(guest.getId(), guest);}
    public ConcurrentHashMap<Integer, Guest> getGuestMap() {
        return guestMap;
    }


}
