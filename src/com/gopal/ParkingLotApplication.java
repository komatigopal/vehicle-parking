package com.gopal;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

public class ParkingLotApplication {
    private static Initializer initializer;
    private static List<ParkingSlot> parkingSlots;
    private static List<ParkingTicket> parkingTickets;
    private static int parkingTicketNumber = 1;

    public static void main(String[] args) {
        initializer = Initializer.getInstance();
        Scanner scanner = new Scanner(System.in);
        parkingSlots = initializer.getParkingSlots();
        parkingTickets = initializer.getParkingTickets();
        boolean isExit = false;
        while (!isExit) {
            String[] input = scanner.nextLine().split(" ");
            switch (input[0]) {
                case "create_parking_lot":
                    prepareParkingSlots(input);
                    break;

                case "park_vehicle":
                    parkVehicle(input);
                    break;

                case "unpark_vehicle":
                    unParkVehicle(input);
                    break;

                case "display":
                    display(input);
                    break;

                case "exit":
                    isExit = true;
                    break;
            }
        }
        scanner.close();
    }

    private static void display(String[] input) {
        String description = input[1], vehicleType = input[2];
        switch (description) {
            case "free_count":
                freeCount(vehicleType);
                break;

            case "free_slots":
                freeSlots(vehicleType);
                break;

            case "occupied_slots":
                occupiedSlots(vehicleType);
                break;
        }
    }

    private static void occupiedSlots(String vehicleType) {
        List<ParkingSlot> occupiedParkingSlots = parkingSlots.stream().filter(parkingSlot -> parkingSlot.getParkingSlotType().equals(vehicleType) && parkingSlot.isOccupied()).toList();
        Map<Integer, Set<ParkingSlot>> floorWiseOccupiedParkingSlots = occupiedParkingSlots.stream().collect(Collectors.groupingBy(ParkingSlot::getFloor, toSet()));
        if (!floorWiseOccupiedParkingSlots.isEmpty()) {
            for (Integer floor : floorWiseOccupiedParkingSlots.keySet()) {
                System.out.println("In Floor No - " + floor + ", " + floorWiseOccupiedParkingSlots.get(floor) + " slots are free");
            }
        } else {
            System.out.println("Parking Lot Empty");
        }
    }

    private static void freeSlots(String vehicleType) {
        List<ParkingSlot> freeParkingSlots = parkingSlots.stream().filter(parkingSlot -> parkingSlot.getParkingSlotType().equals(vehicleType) && !parkingSlot.isOccupied()).toList();
        Map<Integer, Set<ParkingSlot>> floorWiseFreeParkingSlots = freeParkingSlots.stream().collect(Collectors.groupingBy(ParkingSlot::getFloor, toSet()));
        if (!floorWiseFreeParkingSlots.isEmpty()) {
            for (Integer floor : floorWiseFreeParkingSlots.keySet()) {
                System.out.println("In Floor No - " + floor + ", " + floorWiseFreeParkingSlots.get(floor) + " slots are free");
            }
        } else {
            System.out.println("Parking Lot Full");
        }
    }

    private static void freeCount(String vehicleType) {
        List<ParkingSlot> freeParkingSlots = parkingSlots.stream().filter(parkingSlot -> parkingSlot.getParkingSlotType().equals(vehicleType) && !parkingSlot.isOccupied()).toList();
        Map<Integer, Long> floorWiseFreeParkingSlotCount = freeParkingSlots.stream().collect(Collectors.groupingBy(ParkingSlot::getFloor, Collectors.counting()));
        if (!floorWiseFreeParkingSlotCount.isEmpty()) {
            for (Integer floor : floorWiseFreeParkingSlotCount.keySet()) {
                System.out.println("In Floor No - " + floor + ", " + floorWiseFreeParkingSlotCount.get(floor) + " slots are free");
            }
        } else {
            System.out.println("Parking Lot Full");
        }
    }

    private static void unParkVehicle(String[] input) {
        int unParkingId = Integer.parseInt(input[1]);
        LocalDateTime unParkingTime = LocalDateTime.now();
        Optional<ParkingTicket> parkingTicketOptional = parkingTickets.stream().filter(parkingTicket -> parkingTicket.getId() == unParkingId || !parkingTicket.isUnParked()).findFirst();
        if (parkingTicketOptional.isPresent()) {
            ParkingTicket parkingTicket = parkingTicketOptional.get();
            parkingTicket.setUnParkedTime(unParkingTime);
            float amount = parkingTicket.getParkingSlot().getParkingLotPrice() * (parkingTicket.getUnParkedTime().until(parkingTicket.getParkingTime(), ChronoUnit.SECONDS));
            parkingTicket.setAmount(amount);
            parkingTicket.setUnParked(true);

            parkingTicket.getParkingSlot().setOccupied(false);
            String message = "Vehicle Un Parked Ticket Number - " + unParkingId + " and vehicle number - " + parkingTicket.getVehicle().getVehicleNumber() + " with parking charges - " + parkingTicket.getAmount();
            System.out.println(message);
        } else {
            System.out.println("Invalid Ticket");
        }
    }

    private static void parkVehicle(String[] input) {
        String vehicleType = input[1], vehicleId = input[2], vehicleColor = input[3];
        boolean isOccupied = true, isUnParked = false;
        LocalDateTime parkingTime = LocalDateTime.now();
        Optional<ParkingSlot> parkingSlotOptional = parkingSlots.stream().filter(parkingSlot -> parkingSlot.getParkingSlotType().equals(vehicleType) && !parkingSlot.isOccupied()).findFirst();
        if (parkingSlotOptional.isPresent()) {
            ParkingSlot parkingSlot = parkingSlotOptional.get();
            parkingSlot.setOccupied(isOccupied);

            Vehicle vehicle = new Vehicle(vehicleId, vehicleType, vehicleColor);

            ParkingTicket parkingTicket = new ParkingTicket(parkingTicketNumber++, parkingSlot, vehicle, isUnParked, parkingTime);
            parkingTickets.add(parkingTicket);

            String message = "Vehicle parked at Floor No - " + parkingSlot.getFloor() + " and parkingNumber - " + parkingSlot.getParkingNumber() + " with ticket number - " + parkingTicketNumber + " at time " + parkingTime;
            System.out.println(message);
        } else {
            System.out.println("Parking Lot Full");
        }
    }

    private static void prepareParkingSlots(String[] input) {
        String parkingLotId = input[1];
        int floors = Integer.parseInt(input[2]), noOfSlotsPerFloor = Integer.parseInt(input[3]);
        for (int floor = 1; floor <= floors; floor++) {
            for (int parkingSlotNumber = 1; parkingSlotNumber <= noOfSlotsPerFloor; parkingSlotNumber++) {
                int id = parkingSlotNumber * floor;
                String vehicleType = "CAR";
                boolean isOccupied = false;
                float parkingLotPrice = 3f;
                if (parkingSlotNumber == 1) {
                    vehicleType = "TRUCK";
                    parkingLotPrice = 5f;
                } else if (parkingSlotNumber == 2 || parkingSlotNumber == 3) {
                    vehicleType = "BIKE";
                    parkingLotPrice = 1f;
                }
                ParkingSlot parkingSlot = new ParkingSlot(id, vehicleType, isOccupied, floor, parkingSlotNumber, parkingLotId, parkingLotPrice);
                initializer.getParkingSlots().add(parkingSlot);
            }
        }
        initializer.setParkingLotId(parkingLotId);
        System.out.println("Parking Creation Done - " + initializer);
    }
}
