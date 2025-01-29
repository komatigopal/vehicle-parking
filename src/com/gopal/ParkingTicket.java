package com.gopal;

import java.time.LocalDateTime;

public class ParkingTicket {
    private int id;
    private ParkingSlot parkingSlot;
    private Vehicle vehicle;
    private boolean isUnParked = false;
    private LocalDateTime parkingTime;
    private LocalDateTime unParkedTime;
    private float amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ParkingSlot getParkingSlot() {
        return parkingSlot;
    }

    public void setParkingSlot(ParkingSlot parkingSlot) {
        this.parkingSlot = parkingSlot;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public boolean isUnParked() {
        return isUnParked;
    }

    public void setUnParked(boolean unParked) {
        isUnParked = unParked;
    }

    public LocalDateTime getParkingTime() {
        return parkingTime;
    }

    public void setParkingTime(LocalDateTime parkingTime) {
        this.parkingTime = parkingTime;
    }

    public LocalDateTime getUnParkedTime() {
        return unParkedTime;
    }

    public void setUnParkedTime(LocalDateTime unParkedTime) {
        this.unParkedTime = unParkedTime;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public ParkingTicket(int id, ParkingSlot parkingSlot, Vehicle vehicle, boolean isUnParked, LocalDateTime parkingTime) {
        this.id = id;
        this.parkingSlot = parkingSlot;
        this.vehicle = vehicle;
        this.isUnParked = isUnParked;
        this.parkingTime = parkingTime;
    }

    @Override
    public String toString() {
        return "ParkingTicket{" +
                "id=" + id +
                ", parkingSlot=" + parkingSlot +
                ", vehicle=" + vehicle +
                ", isUnParked=" + isUnParked +
                ", parkingTime=" + parkingTime +
                ", unParkedTime=" + unParkedTime +
                ", amount=" + amount +
                '}';
    }
}
