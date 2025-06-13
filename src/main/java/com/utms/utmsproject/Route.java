/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utms.utmsproject;

/**
 *
 * @author Eng.MIKE
 */
import java.util.List;

public class Route {
    private String routeId;
    private String start;
    private String end;
    private List<String> stops;

    public Route(String routeId, String start, String end, List<String> stops) {
        this.routeId = routeId;
        this.start = start;
        this.end = end;
        this.stops = stops;
    }

    public String getRouteId() {
        return routeId;
    }
}