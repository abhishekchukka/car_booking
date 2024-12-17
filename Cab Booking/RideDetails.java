public class RideDetails {
    private Rider rider;
    private Driver driver;
    private double distance;
    private double price;

    public RideDetails(Rider rider, Driver driver, double distance, double price) {
        this.rider = rider;
        this.driver = driver;
        this.distance = distance;
        this.price = price;
    }

    // Getters
    public Rider getRider() {
        return rider;
    }

    public Driver getDriver() {
        return driver;
    }

    public double getDistance() {
        return distance;
    }

    public double getPrice() {
        return price;
    }

    // Method to update price
    public void setPrice(double price) {
        this.price = price;
    }

    // Method to update distance (optional, if required)
    public void setDistance(double distance) {
        this.distance = distance;
    }

    // Detailed Ride Information
    public String getRideDetails() {
        return "Rider: " + rider.getName() +
                ", Driver: " + driver.getName() +
                ", Distance: " + distance + " km" +
                ", Price: $" + price;
    }

    @Override
    public String toString() {
        return "Rider: " + rider.getName() +
                " had a cab trip with Driver: " + driver.getName() +
                " for a fare: $" + String.format("%.2f", price) +
                " over " + distance + " km.";
    }
}
