
public class Driver {
    private String name;
    private String id;
    private Car car;
    private static int idGenerator = 1;
    // private List<String> rideHistory; // Simple ride history

    public Driver() {
        this.id = "D" + (idGenerator++);
        // this.rideHistory = new ArrayList<>();
    }

    public void registerDriver(String name, int licenseNumber, Car car) {
        this.name = name;
        this.car = car;
    }

    public String getName() {
        return name;
    }

    public String getID() {
        return id;
    }

}
