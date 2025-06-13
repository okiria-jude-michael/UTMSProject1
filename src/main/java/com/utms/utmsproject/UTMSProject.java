/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.utms.utmsproject;

/**
 *
 * @author Eng.MIKE
 */
import java.util.Arrays;

public class UTMSProject {
    public static void main(String[] args) {
        // Create users
        User student = new Student("john_doe", "John Doe", "john@vu.edu", "password123", "S1001", "Computer Science");
        User lecturer = new Lecturer("dr_smith", "Dr. Smith", "smith@vu.edu", "secure456", "Engineering");
        User transportOfficer = new TransportOfficer("officer1", "Jane Doe", "transport@vu.edu", "admin123", "Scheduler");

        // Polymorphic method calls
        student.requestTransport("R001", "10:00 AM");
        lecturer.requestTransport("R002", "11:00 AM");
        transportOfficer.requestTransport("R003", "12:00 PM");

        // Create drivers
        Driver driver1 = new Driver("D1001", "Alice Driver", "LIC12345", "2025-12-31", 5);
        Driver driver2 = new Driver("D1002", "Bob Driver", "LIC67890", "2024-10-15", 2);

        // Create vehicles
        Bus bus1 = new Bus("BUS001");
        Van van1 = new Van("VAN001");

        // Create route
        Route route1 = new Route("R001", "Main Gate", "Library", Arrays.asList("Student Center", "Science Building"));

        // Demonstrate interfaces
        bus1.assignRoute(route1);
        bus1.updateLocation("Main Gate");
        van1.updateLocation("Parking Lot");

        if (bus1.isServiceDue()) {
            bus1.performMaintenance();
        }

        // Demonstrate method overloading
        TransportManager manager = new TransportManager();
        manager.assignDriver(driver1, bus1);
        manager.assignDriver(driver2, van1);
        manager.assignDriver(driver1, "Morning Shift");
        manager.assignDriver(driver2, van1, route1);
    }
}