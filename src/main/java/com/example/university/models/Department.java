package com.example.university.models;

import java.util.Collection;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.HashSet;

@Entity
@Table(name = "DEPARTMENT")
public class Department {
    private Collection<Course> courses;
    private String id;
    private String name;
    private String chairman;

    /** Creates a new instance of Department */
    public Department() {
    }

    public Department(
        String id,
        String name,
        String chairman) {
        this.id = id;
        this.name = name;
        this.chairman = chairman;
    }

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChairman() {
        return chairman;
    }

    public void setChairman(String chairman) {
        this.chairman = chairman;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
    public Collection<Course> getCourses() {
        return courses;
    }

    public void setCourses(Collection<Course> courses) {
        this.courses = courses;
    }

    public void addCourse(Course course) {
        if (this.getCourses() == null) {
            this.courses = new HashSet<>();
        }
        this.getCourses()
            .add(course);
    }

    public void dropCourse(Course course) {
        this.getCourses()
            .remove(course);
    }
}
