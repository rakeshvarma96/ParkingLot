package models;

public class Car {
    private String regNumber;
    private String colour;

    public Car(String regNumber, String colour) {
        this.regNumber = regNumber;
        this.colour = colour;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public String getColour() {
        return colour;
    }
}
