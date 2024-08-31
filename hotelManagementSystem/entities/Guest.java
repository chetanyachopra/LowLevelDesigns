package hotelManagementSystem.entities;

import hotelManagementSystem.utils.ReservationStatus;

import java.util.ArrayList;
import java.util.List;

public class Guest {

    int id;
    Integer bill;

    public List<Room> getAssignedRooms() {
        return assignedRooms;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    // One to Many Relationship
    List<Room> assignedRooms = new ArrayList<>();

    public void setBill(Integer bill) {
        this.bill = bill;
    }

    public void addToAssignedRooms(Room room) {
        this.assignedRooms.add(room);
    }

    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    ReservationStatus reservationStatus;

    public Guest(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

}
