public class Car {
    private String name;
    private int carNo;
    private int noOfSeats;
    // private String mileage;
    // private String servicing;

    public Car(String name, int carNo) {
        this.name = name;
        this.carNo = carNo;
    }

    public int getCarNo() {
        return carNo;
    }

    public String getName() {
        return name;
    }

    public int getNoOfSeats() {
        return noOfSeats;
    }

    @Override
    public String toString() {
        return "Car Name: " + name + ", Number: " + carNo;
    }
}
