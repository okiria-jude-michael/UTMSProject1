/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utms.utmsproject;

/**
 *
 * @author Eng.MIKE
 */
public class Lecturer extends User implements TransportService {
    private String faculty;

    public Lecturer(String userId, String name, String email, String password, String faculty) {
        super(userId, name, email, password);
        this.faculty = faculty;
    }

    @Override
    public void scheduleTransport(String routeId, String time) {
        System.out.println("Lecturer scheduled transport for route " + routeId + " at " + time + " (Priority)");
    }

    @Override
    public String trackTransport() {
        return "Lecturer transport on route is on the way!";
    }

    @Override
    public void requestTransport(String routeId, String time) {
        System.out.println("Lecturer " + getName() + " requested transport on route " + routeId + " at " + time + " (Priority)");
        scheduleTransport(routeId, time);
    }
}