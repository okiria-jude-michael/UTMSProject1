/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utms.utmsproject;

/**
 *
 * @author Eng.MIKE
 */
public class Van extends Vehicle implements Maintainable {
    public Van(String vehicleId) {
        super(vehicleId, "Van Model", 8); // Default model and capacity
    }

    @Override
    public boolean isServiceDue() {
        return false; // Simplified for demo
    }

    @Override
    public void performMaintenance() {
        System.out.println("Performing maintenance on van " + getVehicleId());
    }

    public void assignRoute(Route route) {
        System.out.println("Van " + getVehicleId() + " assigned to route " + route.getRouteId());
    }

    public void updateLocation(String location) {
        System.out.println("Van " + getVehicleId() + " updated location to " + location);
    }
}