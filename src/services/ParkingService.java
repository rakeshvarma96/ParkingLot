package services;

import models.Car;

import java.util.*;

public class ParkingService {
    private static final String NOT_FOUND = "Not found";
    private static final String FORMAT = "%20s %20s %20s";
    private static final String SLOT_NUM = "Slot No.";
    private static final String REG_NUM = "Registration No";
    private static final String COLOUR = "Colour";
    TreeMap<Integer, Car> parkingDetails;
    TreeSet<Integer> availableSlots;

    public ParkingService(int numSlots){
        parkingDetails = new TreeMap<>();
        availableSlots = new TreeSet<>();
        for(int i=0;i<numSlots;i++)
            availableSlots.add(i+1);
        System.out.println("Created a parking lot with "+ numSlots + " slots");
    }
    public void getSlotNumbers(String clr) {
        StringBuilder sb = new StringBuilder();
        boolean isFound = false;
        for (Map.Entry<Integer, Car> e : parkingDetails.entrySet()) {
            if (e.getValue().getColour().equals(clr)) {
                isFound = true;
                sb.append(e.getValue().getRegNumber());
                sb.append(", ");
            }
        }
        if(isFound)
            System.out.println(sb.toString().replaceAll(", $", ""));
        else
            System.out.println(NOT_FOUND);
    }

    public void getRegNumbers(String clr) {
        StringBuilder sb = new StringBuilder();
        boolean isFound = false;
        for (Map.Entry<Integer, Car> e : parkingDetails.entrySet()) {
            if (e.getValue().getColour().equals(clr)) {
                isFound = true;
                sb.append(e.getKey().toString());
                sb.append(", ");
            }
        }
        if(isFound)
            System.out.println(sb.toString().replaceAll(", $", ""));
        else
            System.out.println(NOT_FOUND);
    }

    public void getSlotNumForRegNum(String regNum) {
        boolean isFound = false;
        for (Map.Entry<Integer, Car> e : parkingDetails.entrySet()) {
            if (e.getValue().getRegNumber().equals(regNum)) {
                isFound = true;
                System.out.println(e.getKey());
                break;
            }
        }
        if(!isFound)
            System.out.println(NOT_FOUND);
    }
    public void parkCar(String regNum, String colour) {
        if(availableSlots.size() > 0){
            int slotAllocated = availableSlots.pollFirst();
            Car c = new Car(regNum, colour);
            parkingDetails.put(slotAllocated, c);
            System.out.println("Allocated slot number: " + slotAllocated);
        } else {
            System.out.println("Sorry, parking lot is full");
        }
    }

    public void exitCar(String s) {
        int slotTobeFreed = Integer.parseInt(s);
        availableSlots.add(slotTobeFreed);
        parkingDetails.remove(slotTobeFreed);
        System.out.println("Slot number " + slotTobeFreed + " is free");
    }

    public void parkingStatus() {
        System.out.printf(FORMAT,SLOT_NUM, REG_NUM, COLOUR);
        for (Map.Entry<Integer, Car> e : parkingDetails.entrySet()) {
            System.out.println();
            System.out.printf(FORMAT, e.getKey(), e.getValue().getRegNumber(), e.getValue().getColour());
        }
        System.out.println();
    }

}
