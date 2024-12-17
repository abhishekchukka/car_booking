
import java.util.*;

public class RiderTester {

    public static void main(String[] args) {
        // Initialize the objects
        CabManager cabManager = new CabManager();
        RiderManager riderManager = new RiderManager();
        DriverAssignmentStrategy driverStrategy = new DefaultAssignmentStrategy();
        Rider rider1 = new Rider("Abhi");
        Rider rider2 = new Rider("Abhi2");
        riderManager.setRiderDetailsForRide(rider1);
        riderManager.setRiderDetailsForRide(rider2);
        riderManager.setOrUpdateRiderLocation(rider1.getID(), new Location(5, 5));
        riderManager.setOrUpdateRiderLocation(rider1.getID(), new Location(7, 7));
        Car car = new Car("Maruti", 123);
        Car car2 = new Car("audi", 1234);
        Driver driver1 = new Driver();
        Driver driver2 = new Driver();

        // DriverAssignmentStrategy strategy = new
        // DefaultAssignmentStrategy(cabManager);

        // Register drivers and update their locations and statuses
        driver1.registerDriver("Driver1", 123123, car);
        driver2.registerDriver("Driver2", 456456, car2);

        cabManager.registerDriver(driver1);
        cabManager.registerDriver(driver2);

        cabManager.addOrUpdateCabLocation(driver1.getID(), new Location(10, 10));
        cabManager.addOrUpdateCabLocation(driver2.getID(), new Location(19, 19));

        cabManager.updateCabStatus(driver1.getID(), "free");
        cabManager.updateCabStatus(driver2.getID(), "free");

        List<Driver> availableDrivers = cabManager.getAvailableCabs(riderManager.getRiderLocation(rider1.getID()));
        System.out.println("Available Drivers: " + availableDrivers);

        Driver selectedDriver = driverStrategy.assignDriver(availableDrivers,
                riderManager.getRiderLocation(rider1.getID()),
                cabManager.getDriverLocations());

        System.out.println("Selected Driver for ride :" + selectedDriver.getName());
        RideDetails rd = cabManager.bookRide(rider1, selectedDriver, riderManager.getRiderLocation(rider1.getID()),
                new Location(20, 20));
        System.out.println(rd);
        cabManager.endRide(rd, riderManager);
        // riderManager.addRideToHistory(rider1.getID(), rd);
        System.out.println(riderManager.getRiderHistory().get(rider1.getID()));

    }

}
