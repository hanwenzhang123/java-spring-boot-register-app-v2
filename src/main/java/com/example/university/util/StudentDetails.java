package com.example.university.util;

public class StudentDetails  {
    private String id;
    private String name;
    private String thesis;
    private double gpa;

    public StudentDetails(
        String id,
        String name,
        String thesis,
        double gpa) {
        this.id = id;
        this.name = name;
        this.thesis = thesis;
        this.gpa = gpa;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getThesis() {
        return thesis;
    }

    public double getGpa() {
        return gpa;
    }

    public String toString() {
        String s = id + " " + name + " working on " + thesis + " with gpa " + gpa;

        return s;
    }
}
