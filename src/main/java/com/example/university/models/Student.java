package com.example.university.models;

import java.util.Collection;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;


@Entity
@Table(name = "STUDENT")

@NamedQueries({
        @NamedQuery(name = "roster.entity.Department.findAllDepartments",query = "SELECT l FROM Department l"),
        @NamedQuery(name = "roster.entity.Course.findAllCourses",query = "SELECT t FROM Course t"),
        @NamedQuery(name = "roster.entity.Student.findAllStudents",query = "SELECT p FROM Student p"),

        @NamedQuery(name = "roster.entity.Student.findStudentsByAdvisor", query = "SELECT DISTINCT p "
    + "FROM Student p, " + "IN (p.courses) t " + "WHERE t.advisor = :advisor")
    , @NamedQuery(name = "roster.entity.Student.findStudentsByHigherGpa", query = "SELECT DISTINCT p1 "
    + "FROM Student p1, Student p2 " + "WHERE p1.gpa > p2.gpa AND "
    + "p2.name = :name")
    , @NamedQuery(name = "roster.entity.Student.findStudentsByDepartment", query = "SELECT DISTINCT p "
    + "FROM Student p, " + "IN (p.courses) t " + "WHERE t.department = :department")
    , @NamedQuery(name = "roster.entity.Student.findStudentsByThesis", query = "SELECT DISTINCT p "
    + "FROM Student p " + "WHERE p.thesis = :thesis")
    , @NamedQuery(name = "roster.entity.Student.findStudentsByThesisAndName", query = "SELECT DISTINCT p "
    + "FROM Student p " + "WHERE p.thesis = :thesis AND p.name = :name")
    , @NamedQuery(name = "roster.entity.Student.findStudentsByGpaRange", query = "SELECT DISTINCT p "
    + "FROM Student p "
    + "WHERE p.gpa BETWEEN :lowerGpa AND :higherGpa")
    , @NamedQuery(name = "roster.entity.Student.findStudentsByChairman", query = "SELECT DISTINCT p "
    + "FROM Student p, " + "IN (p.courses) t " + "WHERE t.department.chairman = :chairman")
    , @NamedQuery(name = "roster.entity.Student.findStudentsNotOnCourse", query = "SELECT p "
    + "FROM Student p " + "WHERE p.courses IS EMPTY")
    , @NamedQuery(name = "roster.entity.Student.findDepartmentsByStudent", query = "SELECT t.department "
    + "FROM Student p, IN (p.courses) t " + "WHERE p = :student")
    , @NamedQuery(name = "roster.entity.Student.findChairmansByStudent", query = "SELECT DISTINCT t.department.chairman "
    + "FROM Student p, IN (p.courses) t " + "WHERE p.id = :studentId")
})
public class Student {
    private Collection<Course> courses;
    private String id;
    private String name;
    private String thesis;
    private double gpa;

    /** Creates a new instance of Student */
    public Student() {
    }

    public Student(
        String id,
        String name,
        String thesis,
        double gpa) {
        this.id = id;
        this.name = name;
        this.thesis = thesis;
        this.gpa = gpa;
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

    public String getThesis() {
        return thesis;
    }

    public void setThesis(String thesis) {
        this.thesis = thesis;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    @ManyToMany(cascade = CascadeType.REMOVE, mappedBy = "students")
    public Collection<Course> getCourses() {
        return courses;
    }

    public void setCourses(Collection<Course> courses) {
        this.courses = courses;
    }
}
