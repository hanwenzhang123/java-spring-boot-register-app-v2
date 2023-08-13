package com.example.university.controllers;

import com.example.university.services.RosterDAOService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RestController;

import com.example.university.util.DepartmentDetails;
import com.example.university.util.StudentDetails;
import com.example.university.util.CourseDetails;

import java.util.List;

@RestController
public class RosterRestController {

    @Autowired
    RosterDAOService service;

    @GetMapping(value = "/welcome")
    public String hello() {
        return "Welcome to the Application!";
    }

    @DeleteMapping("/delete")
    public String deleteAllData() {
        service.dropAllDepartments();
        service.dropAllStudents();
        service.dropAllCourses();
        return "All data deleted";
    }

    @GetMapping("/init")
    public String dataInit() {
        // Delete all current data


        // Departments
        service.createDepartment(new DepartmentDetails("bscs", "Bachelor in Computer Science", "Dr. John"));
        service.createDepartment(
                new DepartmentDetails("mscs", "Masters in Computer Science", "Dr. Jane"));

        service.createDepartment(
                new DepartmentDetails("cer", "Certificate in Computer Science", "Dr. Jay"));

        // Courses
        service.createCourseInDepartment(
                new CourseDetails("cs101", "Intro to CS", "Anna"),
                "bscs");
        service.createCourseInDepartment(
                new CourseDetails("cs201", "Data Structure", "Brian"),
                "bscs");
        service.createCourseInDepartment(
                new CourseDetails("cs501", "Algorithms", "Charles"),
                "mscs");

        service.createCourseInDepartment(
                new CourseDetails("cs301", "Networking", "David"),
                "bscs");
        service.createCourseInDepartment(
                new CourseDetails("cs205", "Database", "Ellen"),
                "cer");

        service.createCourseInDepartment(
                new CourseDetails("cs401", "Design Patterns", "Frank"),
                "mscs");
        service.createCourseInDepartment(
                new CourseDetails("cs601", "Operation Systems", "George"),
                "mscs");
        service.createCourseInDepartment(
                new CourseDetails("cs105", "Programming", "Hannah"),
                "cer");

        // Students

        // Students, Course cs101
        service.createStudent("P1", "Phil Jones", "goalkeeper", 100.00);
        service.addStudent("P1", "cs101");

        service.createStudent("P2", "Alice Smith", "defender", 505.00);
        service.addStudent("P2", "cs101");

        service.createStudent("P3", "Bob Roberts", "midfielder", 65.00);
        service.addStudent("P3", "cs101");

        service.createStudent("P4", "Grace Phillips", "forward", 100.00);
        service.addStudent("P4", "cs101");

        service.createStudent("P5", "Barney Bold", "defender", 100.00);
        service.addStudent("P5", "cs101");

        // Students, Course cs201
        service.createStudent("P6", "Ian Carlyle", "goalkeeper", 555.00);
        service.addStudent("P6", "cs201");

        service.createStudent(
                "P7",
                "Rebecca Struthers",
                "midfielder",
                777.00);
        service.addStudent("P7", "cs201");

        service.createStudent("P8", "Anne Anderson", "forward", 65.00);
        service.addStudent("P8", "cs201");

        service.createStudent("P9", "Jan Wesley", "defender", 100.00);
        service.addStudent("P9", "cs201");

        service.createStudent("P10", "Terry Smithson", "midfielder", 100.00);
        service.addStudent("P10", "cs201");

        // Students, Course cs301
        service.createStudent("P11", "Ben Shore", "point guard", 188.00);
        service.addStudent("P11", "cs301");

        service.createStudent(
                "P12",
                "Chris Farley",
                "shooting guard",
                577.00);
        service.addStudent("P12", "cs301");

        service.createStudent(
                "P13",
                "Audrey Brown",
                "small forward",
                995.00);
        service.addStudent("P13", "cs301");

        service.createStudent(
                "P14",
                "Jack Patterson",
                "power forward",
                100.00);
        service.addStudent("P14", "cs301");

        service.createStudent("P15", "Candace Lewis", "point guard", 100.00);
        service.addStudent("P15", "cs301");

        // Students, Course cs205
        service.createStudent(
                "P16",
                "Linda Berringer",
                "point guard",
                844.00);
        service.addStudent("P16", "cs205");

        service.createStudent(
                "P17",
                "Bertrand Morris",
                "shooting guard",
                452.00);
        service.addStudent("P17", "cs205");

        service.createStudent("P18", "Nancy White", "small forward", 833.00);
        service.addStudent("P18", "cs205");

        service.createStudent("P19", "Billy Black", "power forward", 444.00);
        service.addStudent("P19", "cs205");

        service.createStudent("P20", "Jodie James", "point guard", 100.00);
        service.addStudent("P20", "cs205");

        // Students, Course cs501
        service.createStudent("P21", "Henry Shute", "goalkeeper", 205.00);
        service.addStudent("P21", "cs501");

        service.createStudent("P22", "Janice Walker", "defender", 857.00);
        service.addStudent("P22", "cs501");

        service.createStudent(
                "P23",
                "Wally Hendricks",
                "midfielder",
                748.00);
        service.addStudent("P23", "cs501");

        service.createStudent("P24", "Gloria Garber", "forward", 777.00);
        service.addStudent("P24", "cs501");

        service.createStudent("P25", "Frank Fletcher", "defender", 399.00);
        service.addStudent("P25", "cs501");

        // Students, no courses
        service.createStudent("P26", "Hobie Jackson", "pitcher", 582.00);
        service.createStudent("P27", "Melinda Kendall", "catcher", 677.00);


        // Students, multiple courses
        service.createStudent(
                "P28",
                "Constance Adams",
                "substitute",
                966.00);
        service.addStudent("P28", "cs105");
        service.addStudent("P28", "cs601");

        // Adding existing students to second department
        service.addStudent("P24", "bscs");
        service.addStudent("P21", "mscs");
        service.addStudent("P9", "mscs");
        service.addStudent("P7", "cert");

        return "Done Inserting Data";
    }

    @GetMapping("/departments")
    public List<DepartmentDetails> myDepartments() {
        return service.getAllDepartments();
    }

    @GetMapping("/students")
    public List<StudentDetails> myStudents() {
        return service.getAllStudents();
    }

    @GetMapping("/courses")
    public List<CourseDetails> myCourses() {
        return service.getAllCourses();
    }

    @GetMapping("/department/{departmentId}")
    public DepartmentDetails myDepartmentDetails(@PathVariable String departmentId) {
        return service.getDepartmentDetails(departmentId);
    }

    @GetMapping("/department-students/{departmentId}")
    public List<StudentDetails> mydepartmentStudents(@PathVariable String departmentId) {
        return service.getStudentsByDepartmentId(departmentId);
    }

    @GetMapping("/department-courses/{departmentId}")
    public List<CourseDetails> mydepartmentCourses(@PathVariable String departmentId) {
        return service.getCoursesOfDepartment(departmentId);
    }

    @GetMapping("/course/{courseId}")
    public CourseDetails myCourse(@PathVariable String courseId) {
        return service.getCourseDetails(courseId);
    }

    @GetMapping("/course-students/{courseId}")
    public List<StudentDetails> myCourseStudents(@PathVariable String courseId) {
        return service.getStudentsOfCourse(courseId);
    }


    @GetMapping("/student/{studentId}")
    public StudentDetails myStudentDetails(@PathVariable String studentId) {
        return service.getStudentDetails(studentId);
    }
    @GetMapping("/student-departments/{studentId}")
    public List<DepartmentDetails> myStudentDepartments(@PathVariable String studentId) {
        return service.getDepartmentsOfStudent(studentId);
    }

    @GetMapping("/students-thesis/{thesis}")
    public List<StudentDetails> myThesisStudents(@PathVariable String thesis) {
        return service.getStudentsByThesis(thesis);
    }

    @GetMapping("/students-advisor/{advisor}")
    public List<StudentDetails> myAdvisorStudents(@PathVariable String advisor) {
        return service.getStudentsByAdvisor(advisor);
    }

}
