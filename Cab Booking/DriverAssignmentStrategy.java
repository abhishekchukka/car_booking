import java.util.*;

public interface DriverAssignmentStrategy {
    Driver assignDriver(List<Driver> availableDrivers, Location rider, Map<String, Location> driverLocations);
}
