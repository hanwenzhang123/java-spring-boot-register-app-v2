package com.example.university.util;

public class DepartmentDetails {
    private String id;
    private String name;
    private String chairman;

    public DepartmentDetails(
        String id,
        String name,
        String chairman) {
        this.id = id;
        this.name = name;
        this.chairman = chairman;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getChairman() {
        return chairman;
    }

    public String toString() {
        return id + " " + name + " run by " + chairman;
    }
}
