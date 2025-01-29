package com.gopal;

import java.util.ArrayList;
import java.util.List;

public class Initializer {
    private List<ParkingSlot> parkingSlots;
    private static Initializer instance;
    private List<ParkingTicket> parkingTickets;
    private String parkingLotId;

    private Initializer() {
        this.parkingSlots = new ArrayList<ParkingSlot>();
        this.parkingTickets = new ArrayList<>();
    }

    public static Initializer getInstance() {
        if (instance == null) {
            instance = new Initializer();
        }
        return instance;
    }

    public List<ParkingSlot> getParkingSlots() {
        return parkingSlots;
    }

    public void setParkingSlots(List<ParkingSlot> parkingSlots) {
        this.parkingSlots = parkingSlots;
    }

    public String getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(String parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    public List<ParkingTicket> getParkingTickets() {
        return parkingTickets;
    }

    public void setParkingTickets(List<ParkingTicket> parkingTickets) {
        this.parkingTickets = parkingTickets;
    }

    @Override
    public String toString() {
        return "Initializer{" +
                "parkingSlots=" + parkingSlots +
                ", parkingTickets=" + parkingTickets +
                ", parkingLotId=" + parkingLotId +
                '}';
    }
}
