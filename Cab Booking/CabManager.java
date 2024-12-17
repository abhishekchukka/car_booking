import java.util.*;

public class CabManager {
    private static final int MAX_CABS_DIST = 10;
    private Map<String, Driver> driverDetails = new HashMap<>();
    private Map<String, String> cabStatus = new HashMap<>();
    private Map<String, Location> driverLocations = new HashMap<>();
    private Map<String, String> activeRides = new HashMap<>();
    // RiderManager ridemanager = new RiderManager();

    public void addOrUpdateCabLocation(String id, Location location) {
        driverLocations.put(id, location);
    }

    public Map<String, Location> getDriverLocations() {
        return driverLocations;
    }

    public void registerDriver(Driver d) {
        driverDetails.put(d.getID(), d);
    }

    public void updateCabStatus(String id, String status) {
        cabStatus.put(id, status);
    }

    public List<Driver> getAvailableCabs(Location userLocation) {
        List<Driver> availableDrivers = new ArrayList<>();
        for (Map.Entry<String, Location> entry : driverLocations.entrySet()) {
            String driverId = entry.getKey();
            Location driverLocation = entry.getValue();

            if (userLocation.calculateDistance(driverLocation) < MAX_CABS_DIST &&
                    cabStatus.get(driverId).equals("free")) {
                availableDrivers.add(driverDetails.get(driverId));
            }
        }
        return availableDrivers;
    }

    public RideDetails bookRide(Rider rider, Driver driver, Location riderLocation, Location Destination) {
        BasicPriceCalculator calc = new BasicPriceCalculator();
        double distance = riderLocation.calculateDistance(Destination);
        double amount = calc.calculatePrice(distance);
        RideDetails rd = new RideDetails(rider, driver, distance, amount);
        activeRides.put(rider.getID(), driver.getID());
        updateCabStatus(driver.getID(), "onRide");
        // System.out.println(rd.getRideDetails());
        return rd;
    }

    public void endRide(RideDetails rideDetails, RiderManager rm) {
        // Extract rider and driver information from RideDetails
        Rider rider = rideDetails.getRider();
        Driver driver = rideDetails.getDriver();

        String driverId = driver.getID();

        if (activeRides.containsKey(rider.getID())) {
            // Add ride to rider's history
            rm.addRideToHistory(rider.getID(), rideDetails);

            // Update cab status and remove active ride
            updateCabStatus(driverId, "free");
            activeRides.remove(rider.getID());

            // Print ride details
            System.out.println("Ride ended: " + rideDetails);
        } else {
            System.out.println("No active ride found for Rider: " + rider.getName());
        }
    }

}
