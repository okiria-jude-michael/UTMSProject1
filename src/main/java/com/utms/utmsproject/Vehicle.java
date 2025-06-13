/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utms.utmsproject;

/**
 *
 * @author Eng.MIKE
 */
public class Vehicle {
    private String vehicleId;
    private String model;
    private int capacity;
    private boolean inService;

    public Vehicle(String vehicleId, String model, int capacity) {
        this.vehicleId = vehicleId;
        this.model = model;
        this.capacity = capacity;
        this.inService = true;
    }

    // Getters and setters
    public String getVehicleId() { return vehicleId; }
    public String getModel() { return model; }
    public int getCapacity() { return capacity; }
    public boolean isInService() { return inService; }
    public void setInService(boolean inService) { this.inService = inService; }
}