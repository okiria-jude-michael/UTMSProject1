/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utms.utmsproject;

/**
 *
 * @author Eng.MIKE
 */
public class Bus extends Vehicle implements Maintainable {
    public Bus(String vehicleId) {
        super(vehicleId, "Bus Model", 30); // Default model and capacity
    }

    @Override
    public boolean isServiceDue() {
        return false; // Simplified for demo
    }

    @Override
    public void performMaintenance() {
        System.out.println("Performing maintenance on bus " + getVehicleId());
    }

    public void assignRoute(Route route) {
        System.out.println("Bus " + getVehicleId() + " assigned to route " + route.getRouteId());
    }

    public void updateLocation(String location) {
        System.out.println("Bus " + getVehicleId() + " updated location to " + location);
    }
}