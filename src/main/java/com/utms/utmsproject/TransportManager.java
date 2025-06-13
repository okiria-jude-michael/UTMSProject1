/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utms.utmsproject;

/**
 *
 * @author Eng.MIKE
 */
public class TransportManager {
    public void assignDriver(Driver driver, Vehicle vehicle) {
        System.out.println("Assigned driver " + driver.getName() + " to vehicle " + vehicle.getVehicleId());
    }

    public void assignDriver(Driver driver, String shift) {
        System.out.println("Assigned driver " + driver.getName() + " to shift " + shift);
    }

    public void assignDriver(Driver driver, Vehicle vehicle, Route route) {
        System.out.println("Assigned driver " + driver.getName() + " to vehicle " + vehicle.getVehicleId() + " on route " + route.getRouteId());
    }
}