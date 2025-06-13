/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utms.utmsproject;

/**
 *
 * @author Eng.MIKE
 */
public class Student extends User implements TransportService {
    private String studentId;
    private String department;

    public Student(String userId, String name, String email, String password, String studentId, String department) {
        super(userId, name, email, password);
        this.studentId = studentId;
        this.department = department;
    }

    @Override
    public void scheduleTransport(String routeId, String time) {
        System.out.println("Student scheduled transport for route " + routeId + " at " + time);
    }

    @Override
    public String trackTransport() {
        return "Student transport on route is on the way!";
    }

    @Override
    public void requestTransport(String routeId, String time) {
        System.out.println("Student " + getName() + " requested transport on route " + routeId + " at " + time);
        scheduleTransport(routeId, time);
    }
}