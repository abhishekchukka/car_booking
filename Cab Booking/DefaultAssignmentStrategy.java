import java.util.List;
import java.util.Map;

public class DefaultAssignmentStrategy implements DriverAssignmentStrategy {

    @Override
    public Driver assignDriver(List<Driver> availableDrivers, Location user, Map<String, Location> driverLocations) {
        Driver nearestDriver = null;
        double minDistance = Double.MAX_VALUE;

        for (Driver driver : availableDrivers) {
            Location driverLocation = driverLocations.get(driver.getID());
            if (driverLocation != null) {
                double distance = user.calculateDistance(driverLocation);
                if (distance < minDistance) {
                    minDistance = distance;
                    nearestDriver = driver;
                }
            }
        }

        return nearestDriver;
    }
}
