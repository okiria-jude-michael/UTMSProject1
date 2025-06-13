/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utms.utmsproject;

/**
 *
 * @author Eng.MIKE
 */
public class TransportOfficer extends User implements TransportService {
    private String role;

    public TransportOfficer(String userId, String name, String email, String password, String role) {
        super(userId, name, email, password);
        this.role = role;
    }

    @Override
    public void scheduleTransport(String routeId, String time) {
        System.out.println("Transport Officer scheduled transport for route " + routeId + " at " + time);
    }

    @Override
    public String trackTransport() {
        return "Transport Officer transport on route is on the way!";
    }

    @Override
    public void requestTransport(String routeId, String time) {
        System.out.println("Transport Officer " + getName() + " scheduled transport on route " + routeId + " at " + time);
        scheduleTransport(routeId, time);
    }
}