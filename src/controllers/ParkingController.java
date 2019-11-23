package controllers;

import services.ParkingService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class ParkingController {
    private static final String FILE = "file";
    private static final String TYPE = "type";
    private static final String CREATE = "create_parking_lot";
    private static final String PARK = "park";
    private static final String LEAVE = "leave";
    private static final String STATUS = "status";
    private static final String REGNUMSFORCOLOUR = "registration_numbers_for_cars_with_colour";
    private static final String SLOTNUMFORCOLOUR = "slot_numbers_for_cars_with_colour";
    private static final String SLOTNUMFORREGNUM = "slot_number_for_registration_number";
    private static final String EXIT = "exit";
    public static ParkingService parkingService;

    public static void main(String[] args) throws Exception {
        System.out.println("How do you want this to go ?");
        System.out.println("Enter either of them: file or type.");
        Scanner sc = new Scanner(System.in);
        String chooseFrom = sc.nextLine();
        switch (chooseFrom) {
            case FILE:
                System.out.println("Okay. Now enter file path");
                fromFile(sc.nextLine());
                break;
            case TYPE:
                System.out.println("Go ahead. Fire the commands.");
                fromPrompt(sc);
                break;
            default:
                System.out.println("Enter either of these options: " + FILE + ", " + TYPE);
        }
    }

    public static void fromFile(String filePath) throws Exception {
        File file = new File(filePath);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String command;
        while ((command = br.readLine()) != null && !command.equals(EXIT))
            letsPark(command);
    }

    public static void fromPrompt(Scanner scanner) {
        String command = "";
        while (!command.equals(EXIT)) {
            command = scanner.nextLine();
            letsPark(command);
        }
    }

    public static void letsPark(String choice) {
        if (choice.split(" ")[0].equals(CREATE)) {
            parkingService = new ParkingService(Integer.parseInt(choice.split(" ")[1]));
            return;
        }
        if(parkingService == null) {
            System.out.println("Operation can't be performed. You have to create the parking lot first.");
            return ;
        }
        String commandArray[] = choice.split(" ");
        switch (commandArray[0]) {
            case PARK:
                parkingService.parkCar(commandArray[1], commandArray[2]);
                break;
            case LEAVE:
                parkingService.exitCar(commandArray[1]);
                break;
            case STATUS:
                parkingService.parkingStatus();
                break;
            case REGNUMSFORCOLOUR:
                parkingService.getRegNumbers(commandArray[1]);
                break;
            case SLOTNUMFORCOLOUR:
                parkingService.getSlotNumbers(commandArray[1]);
                break;
            case SLOTNUMFORREGNUM:
                parkingService.getSlotNumForRegNum(commandArray[1]);
                break;
        }
    }
}
