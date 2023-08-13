package com.example.university.services;

import com.example.university.util.DepartmentDetails;
import com.example.university.util.StudentDetails;
import com.example.university.util.CourseDetails;

import com.example.university.models.Course;

import java.util.List;

public interface IRoster {

        void addStudent(
                String studentId,
                String courseId);

        void createDepartment(DepartmentDetails DepartmentDetails);

        void createStudent(
                String id,
                String name,
                String thesis,
                double gpa);

        void createCourseInDepartment(
                CourseDetails CourseDetails,
                String departmentId);

        void dropStudent(
                String studentId,
                String courseId);

        List<DepartmentDetails> getAllDepartments();

        List<StudentDetails> getAllStudents();

        DepartmentDetails getDepartmentDetails(String departmentId);

        List<DepartmentDetails> getDepartmentsOfStudent(String studentId);

        StudentDetails getStudentDetails(String studentId);

        List<StudentDetails> getStudentsByAdvisor(String advisor);

        List<StudentDetails> getStudentsByHigherGpa(String name);

        List<StudentDetails> getStudentsByDepartmentId(String departmentId);

        List<StudentDetails> getStudentsByThesis(String thesis);

        List<StudentDetails> getStudentsByThesisAndName(
                String thesis,
                String name);

        List<StudentDetails> getStudentsByGpaRange(
                double low,
                double high);

        List<StudentDetails> getStudentsByChairman(String chairman);

        List<StudentDetails> getStudentsNotOnCourse();

        List<StudentDetails> getStudentsOfCourse(String courseId);

        List<String> getChairmansOfStudent(String studentId);

        CourseDetails getCourseDetails(String courseId);

        List<CourseDetails> getCoursesOfDepartment(String departmentId);

        void removeDepartment(String departmentId);

        void removeStudent(String studentId);

        void removeCourse(String courseId);

}
