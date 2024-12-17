import java.util.*;

public class RiderTester {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        CabManager cabManager = new CabManager();
        RiderManager riderManager = new RiderManager();
        DriverAssignmentStrategy driverStrategy = new DefaultAssignmentStrategy();

        Driver driver1 = new Driver();
        Driver driver2 = new Driver();
        driver1.registerDriver("Driver1", 123123, new Car("Maruti", 123));
        driver2.registerDriver("Driver2", 456456, new Car("Audi", 456));
        cabManager.registerDriver(driver1);
        cabManager.registerDriver(driver2);

        cabManager.addOrUpdateCabLocation(driver1.getID(), new Location(10, 10));
        cabManager.addOrUpdateCabLocation(driver2.getID(), new Location(8, 8));
        cabManager.updateCabStatus(driver1.getID(), "free");
        cabManager.updateCabStatus(driver2.getID(), "free");

        Map<String, RideDetails> activeRides = new HashMap<>();

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add a new rider and start a ride");
            System.out.println("2. End an active ride");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                // Add a new rider and start a ride
                System.out.print("Enter the name of the Rider: ");
                String riderName = scanner.nextLine();
                System.out.print("Enter the X coordinate of Rider's location: ");
                int riderX = scanner.nextInt();
                System.out.print("Enter the Y coordinate of Rider's location: ");
                int riderY = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                System.out.print("Enter the X coordinate of Rider's destination: ");
                int destX = scanner.nextInt();
                System.out.print("Enter the Y coordinate of Rider's destination: ");
                int destY = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                Rider rider = new Rider(riderName);
                riderManager.setRiderDetailsForRide(rider);
                riderManager.setOrUpdateRiderLocation(rider.getID(), new Location(riderX, riderY));

                List<Driver> availableDrivers = cabManager
                        .getAvailableCabs(riderManager.getRiderLocation(rider.getID()));
                System.out.println("Available Drivers: " + availableDrivers);

                if (availableDrivers.isEmpty()) {
                    System.out.println("No available drivers for " + rider.getName());
                    continue;
                }

                Driver selectedDriver = driverStrategy.assignDriver(availableDrivers,
                        riderManager.getRiderLocation(rider.getID()), cabManager.getDriverLocations());
                System.out.println("Selected Driver: " + selectedDriver.getName());

                RideDetails rideDetails = cabManager.bookRide(rider, selectedDriver,
                        riderManager.getRiderLocation(rider.getID()), new Location(destX, destY));
                System.out.println("Ride Details: " + rideDetails);

                activeRides.put(rider.getID(), rideDetails);

            } else if (choice == 2) {
                System.out.print("Enter the name of the Rider to end the ride: ");
                String riderName = scanner.nextLine();

                Rider riderToEnd = null;
                for (Map.Entry<String, Rider> entry : riderManager.riderDetails.entrySet()) {
                    if (entry.getValue().getName().equals(riderName)) {
                        riderToEnd = entry.getValue();
                        break;
                    }
                }

                if (riderToEnd == null) {
                    System.out.println("No active ride found for Rider: " + riderName);
                    continue;
                }

                String riderId = riderToEnd.getID();
                RideDetails rideDetails = activeRides.get(riderId);

                if (rideDetails != null) {
                    cabManager.endRide(rideDetails, riderManager);

                    Driver driver = rideDetails.getDriver();
                    Location destination = new Location((int) rideDetails.getDistance(),
                            (int) rideDetails.getDistance());
                    cabManager.addOrUpdateCabLocation(driver.getID(), destination);

                    activeRides.remove(riderId);
                    System.out.println("Ride ended successfully for Rider: " + riderName);
                } else {
                    System.out.println("No active ride found for Rider: " + riderName);
                }

            } else if (choice == 3) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}