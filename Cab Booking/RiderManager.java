import java.util.*;

public class RiderManager {
    Map<String, Rider> riderDetails = new HashMap<>();
    Map<String, Location> riderLocation = new HashMap<>();
    Map<String, List<RideDetails>> riderHistory = new HashMap<>();

    public void addRideToHistory(String id, RideDetails r) {
        List<RideDetails> rides = riderHistory.get(id);
        if (rides == null) {
            rides = new ArrayList<>();
            riderHistory.put(id, rides);
        }
        rides.add(r);

    }

    public Map<String, List<RideDetails>> getRiderHistory() {
        return riderHistory;
    }

    public void setRiderDetailsForRide(Rider r) {
        riderDetails.put(r.getID(), r);
    }

    public void setOrUpdateRiderLocation(String id, Location rider) {
        riderLocation.put(id, rider);
    }

    public Location getRiderLocation(String id) {
        return riderLocation.get(id);
    }

}
