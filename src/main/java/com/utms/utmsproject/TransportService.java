/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utms.utmsproject;

/**
 *
 * @author Eng.MIKE
 */
// Interface for transport-related services
public interface TransportService {
    void scheduleTransport(String routeId, String time);
    String trackTransport();
}