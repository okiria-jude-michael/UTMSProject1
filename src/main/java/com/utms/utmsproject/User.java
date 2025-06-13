/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utms.utmsproject;

/**
 *
 * @author Eng.MIKE
 */
public abstract class User {
    private String userId;
    private String name;
    private String email;
    private String password;

    public User(String userId, String name, String email, String password) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Abstract method for transport requests
    public abstract void requestTransport(String routeId, String time);

    // Getters
    public String getUserId() { return userId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    protected void setEmail(String email) { this.email = email; }
}