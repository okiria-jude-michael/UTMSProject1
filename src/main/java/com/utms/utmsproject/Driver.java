/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utms.utmsproject;

/**
 *
 * @author Eng.MIKE
 */
public class Driver extends User {
    private String licenseNumber;
    private String licenseExpiry;
    private int yearsOfExperience;

    public Driver(String userId, String name, String licenseNumber, String licenseExpiry, int yearsOfExperience) {
        super(userId, name, userId + "@vu.edu", "defaultPass"); // Email and password derived
        this.licenseNumber = licenseNumber;
        this.licenseExpiry = licenseExpiry;
        this.yearsOfExperience = yearsOfExperience;
    }

    @Override
    public void requestTransport(String routeId, String time) {
        System.out.println("Driver " + getName() + " assigned to route " + routeId + " at " + time);
    }

    // Getter for name used in TransportManager
    public String getName() {
        return super.getName();
    }
}