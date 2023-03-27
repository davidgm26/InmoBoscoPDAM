package com.salesianostriana.pdam.inmoboscoapi.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;

public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String lat;
    private String lon;
    private String name;
    private String title;
    private int totalMeetBooking;
    private double price;
    private ArrayList<String> img = new ArrayList<>();
    private double m2;
    private String description;
    private int totalBedRooms;
    private int totalBaths;
    private int totalVisits;

}
