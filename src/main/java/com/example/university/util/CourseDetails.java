package com.example.university.util;

public class CourseDetails {
    private String id;
    private String name;

    private String advisor;
    public CourseDetails(
        String id,
        String name,
        String advisor) {
        this.id = id;
        this.name = name;
        this.advisor = advisor;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAdvisor() {
        return advisor;
    }

    public String toString() {

        return id + " " + name + ": " + advisor;
    }
}
