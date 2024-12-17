
public class Rider {
    private String name;
    private String id;
    private static int idGenerator = 1;
    private Location location;
    // private List<String> rideHistory; // Simple ride history

    public Rider(String name) {
        this.name = name;
        this.id = "R" + (idGenerator++);
        // this.rideHistory = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getID() {
        return id;
    }

}
