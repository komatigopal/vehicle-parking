package com.gopal;

public class ParkingSlot {
    private int id;
    private String parkingSlotType;
    private boolean isOccupied;
    private int floor;
    private int parkingNumber;
    private String parkingLotId;
    private float parkingLotPrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getParkingSlotType() {
        return parkingSlotType;
    }

    public void setParkingSlotType(String parkingSlotType) {
        this.parkingSlotType = parkingSlotType;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getParkingNumber() {
        return parkingNumber;
    }

    public void setParkingNumber(int parkingNumber) {
        this.parkingNumber = parkingNumber;
    }

    public String getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(String parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    public float getParkingLotPrice() {
        return parkingLotPrice;
    }

    public void setParkingLotPrice(float parkingLotPrice) {
        this.parkingLotPrice = parkingLotPrice;
    }

    public ParkingSlot(int id, String parkingSlotType, boolean isOccupied, int floor, int parkingNumber, String parkingLotId, float parkingLotPrice) {
        this.id = id;
        this.parkingSlotType = parkingSlotType;
        this.isOccupied = isOccupied;
        this.floor = floor;
        this.parkingNumber = parkingNumber;
        this.parkingLotId = parkingLotId;
        this.parkingLotPrice = parkingLotPrice;
    }

    @Override
    public String toString() {
        return "ParkingSlot{" +
                "id=" + id +
                ", parkingSlotType='" + parkingSlotType + '\'' +
                ", isOccupied=" + isOccupied +
                ", floor=" + floor +
                ", parkingNumber=" + parkingNumber +
                ", parkingLotId=" + parkingLotId +
                ", parkingLotPrice=" + parkingLotPrice +
                '}';
    }
}
