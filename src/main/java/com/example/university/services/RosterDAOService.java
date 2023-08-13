package com.example.university.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import com.example.university.models.Course;
import com.example.university.models.Department;
import com.example.university.models.Student;

import com.example.university.util.DepartmentDetails;
import com.example.university.util.StudentDetails;
import com.example.university.util.CourseDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.ArrayList;


@Repository
@Transactional
public class RosterDAOService implements IRoster {

    @PersistenceContext
    private EntityManager entityManager;

    public RosterDAOService() {
        super();
        System.out.println(this);
    }

    public void createDepartment(DepartmentDetails DepartmentDetails) {
        Department department = new Department(
                DepartmentDetails.getId(),
                DepartmentDetails.getName(),
                DepartmentDetails.getChairman());

        entityManager.persist(department);
    }

    public List<DepartmentDetails> getAllDepartments() {

        List<Department> departments;

        departments = (List<Department>) entityManager.createNamedQuery(
                        "roster.entity.Department.findAllDepartments")
                .getResultList();

        return copyDepartmentsToDetails(departments);
    }

    public void createCourseInDepartment(CourseDetails CourseDetails, String departmentId) {
        Department department = entityManager.find(Department.class, departmentId);
        if (department != null) {
            Course course = new Course(
                    CourseDetails.getId(),
                    CourseDetails.getName(),
                    CourseDetails.getAdvisor());
            entityManager.persist(course);
            course.setDepartment(department);
            department.addCourse(course);
        }

    }

    public List<CourseDetails> getCoursesOfDepartment(String departmentId) {
        List<CourseDetails> detailsList = new ArrayList<CourseDetails>();

        Department department = entityManager.find(Department.class, departmentId);
        if (department != null) {
            for (Course course : department.getCourses()) {
                CourseDetails CourseDetails = new CourseDetails(
                        course.getId(),
                        course.getName(),
                        course.getAdvisor());
                detailsList.add(CourseDetails);
            }
        }

        return detailsList;
    }

    public List<CourseDetails> getAllCourses() {

        List<Course> courses;

        courses = (List<Course>) entityManager.createNamedQuery(
                        "roster.entity.Course.findAllCourses")
                .getResultList();

        return copyCoursesToDetails(courses);
    }

    public void createStudent(String id, String name, String thesis, double gpa) {
        Student student = new Student(id, name, thesis, gpa);
        entityManager.persist(student);
    }


    public void addStudent(String studentId, String courseId) {
        Student student = entityManager.find(Student.class, studentId);
        Course course = entityManager.find(Course.class, courseId);

        if ((student != null) && (course != null)) {
            course.addStudent(student);
        }
    }


    public void dropStudent(String studentId, String courseId) {
        Student student = entityManager.find(Student.class, studentId);
        Course course = entityManager.find(Course.class, courseId);
        if ((student != null) && (course != null)) {
            course.dropStudent(student);
        }
    }

    public List<StudentDetails> getAllStudents() {

        List<Student> students = null;

        students = (List<Student>) entityManager.createNamedQuery(
                        "roster.entity.Student.findAllStudents")
                .getResultList();

        return copyStudentsToDetails(students);
    }

    public DepartmentDetails getDepartmentDetails(String departmentId) {

        DepartmentDetails DepartmentDetails = null;
        Department department = entityManager.find(Department.class, departmentId);
        if (department != null) {
            DepartmentDetails = new DepartmentDetails(
                    department.getId(),
                    department.getName(),
                    department.getChairman());
        }
        return DepartmentDetails;
    }

    public List<DepartmentDetails> getDepartmentsOfStudent(String studentId) {

        List<DepartmentDetails> detailsList = new ArrayList<DepartmentDetails>();
        List<Department> departments = null;
        Student student = entityManager.find(Student.class, studentId);

        if (student != null) {
            departments = (List<Department>) entityManager.createNamedQuery(
                            "roster.entity.Student.findDepartmentsByStudent")
                    .setParameter("student", student)
                    .getResultList();

            for (Department department : departments) {
                DepartmentDetails DepartmentDetails = new DepartmentDetails(
                        department.getId(),
                        department.getName(),
                        department.getChairman());
                detailsList.add(DepartmentDetails);
            }
        }

        return detailsList;
    }

    public StudentDetails getStudentDetails(String studentId) {

        StudentDetails StudentDetails = null;

        Student student = entityManager.find(Student.class, studentId);

        if (student != null) {
            StudentDetails = new StudentDetails(
                    student.getId(),
                    student.getName(),
                    student.getThesis(),
                    student.getGpa());
        }

        return StudentDetails;
    }

    public List<StudentDetails> getStudentsByAdvisor(String advisor) {

        List<Student> students = null;
        students = (List<Student>) entityManager.createNamedQuery(
                        "roster.entity.Student.findStudentsByAdvisor")
                .setParameter("advisor", advisor)
                .getResultList();

        return copyStudentsToDetails(students);
    }

    public List<StudentDetails> getStudentsByHigherGpa(String name) {

        List<Student> students = null;

        students = (List<Student>) entityManager.createNamedQuery(
                        "roster.entity.Student.findStudentsByHigherGpa")
                .setParameter("name", name)
                .getResultList();

        return copyStudentsToDetails(students);
    }

    public List<StudentDetails> getStudentsByDepartmentId(String departmentId) {

        List<Student> students = null;
        Department department = entityManager.find(Department.class, departmentId);
        if (department != null) {
            students = (List<Student>) entityManager.createNamedQuery(
                            "roster.entity.Student.findStudentsByDepartment")
                    .setParameter("department", department)
                    .getResultList();
        }

        return copyStudentsToDetails(students);
    }

    public List<StudentDetails> getStudentsByThesis(String thesis) {

        List<Student> students = null;

        students = (List<Student>) entityManager.createNamedQuery(
                        "roster.entity.Student.findStudentsByThesis")
                .setParameter("thesis", thesis)
                .getResultList();

        return copyStudentsToDetails(students);
    }

    public List<StudentDetails> getStudentsByThesisAndName(String thesis, String name) {
        List<Student> students = null;

        students = (List<Student>) entityManager.createNamedQuery(
                        "roster.entity.Student.findStudentsByThesisAndName")
                .setParameter("name", name)
                .setParameter("thesis", thesis)
                .getResultList();

        return copyStudentsToDetails(students);

    }

    public List<StudentDetails> getStudentsByGpaRange(double low, double high) {
        List<Student> students = null;

        students = (List<Student>) entityManager.createNamedQuery(
                        "roster.entity.Student.findStudentsByGpaRange")
                .setParameter("lowerGpa", low)
                .setParameter("higherGpa", high)
                .getResultList();

        return copyStudentsToDetails(students);
    }

    public List<StudentDetails> getStudentsByChairman(String chairman) {
        List<Student> students = null;

        students = (List<Student>) entityManager.createNamedQuery(
                        "roster.entity.Student.findStudentsByChairman")
                .setParameter("chairman", chairman)
                .getResultList();

        return copyStudentsToDetails(students);

    }

    public List<StudentDetails> getStudentsNotOnCourse() {
        List<Student> students = null;

        students = (List<Student>) entityManager.createNamedQuery(
                        "roster.entity.Student.findStudentsNotOnCourse")
                .getResultList();

        return copyStudentsToDetails(students);
    }

    public List<StudentDetails> getStudentsOfCourse(String courseId) {
        List<StudentDetails> studentList = null;
        Course course = entityManager.find(Course.class, courseId);

        if (course != null) {
            studentList = this.copyStudentsToDetails(
                    (List<Student>) course.getStudents());
        }

        return studentList;
    }

    public List<String> getChairmansOfStudent(String studentId) {

        List<String> chairmansList = new ArrayList<String>();
        List<String> chairmans = null;

        Student student = entityManager.find(Student.class, studentId);
        if (student != null) {
            chairmans = (List<String>) entityManager.createNamedQuery(
                            "roster.entity.Student.findChairmansByStudent")
                    .setParameter("studentId", studentId)
                    .getResultList();

            if (!chairmans.isEmpty()) {
                for (String chairman : chairmans) {
                    chairmansList.add(chairman);
                }
            }
        }

        return chairmansList;

    }

    public CourseDetails getCourseDetails(String courseId) {
        CourseDetails CourseDetails = null;

        Course course = entityManager.find(Course.class, courseId);
        if (course != null) {
            CourseDetails = new CourseDetails(
                    course.getId(),
                    course.getName(),
                    course.getAdvisor());
        }

        return CourseDetails;
    }


    public void removeDepartment(String departmentId) {
        Department department = entityManager.find(Department.class, departmentId);
        if (department != null)
            entityManager.remove(department);
    }

    public void removeStudent(String studentId) {
        Student student = entityManager.find(Student.class, studentId);
        if (student != null)
            entityManager.remove(student);
    }

    public void removeCourse(String courseId) {
        Course course = entityManager.find(Course.class, courseId);
        if (course != null)
            entityManager.remove(course);
    }

    public void dropAllDepartments() {
        entityManager.createQuery("DELETE FROM Department").executeUpdate();
    }

    public void dropAllStudents() {
        entityManager.createQuery("DELETE FROM Student").executeUpdate();
    }

    public void dropAllCourses() {
        entityManager.createQuery("DELETE FROM Course").executeUpdate();
    }

    private List<DepartmentDetails> copyDepartmentsToDetails(List<Department> departments) {
        List<DepartmentDetails> detailsList = new ArrayList<DepartmentDetails>();

        if (departments != null) {
            for (Department department : departments) {
                DepartmentDetails DepartmentDetails = new DepartmentDetails(
                        department.getId(),
                        department.getName(),
                        department.getChairman()
                );
                detailsList.add(DepartmentDetails);
            }
        }

        return detailsList;
    }

    private List<StudentDetails> copyStudentsToDetails(List<Student> students) {
        List<StudentDetails> detailsList = new ArrayList<StudentDetails>();

        if (students != null) {
            for (Student student : students) {
                StudentDetails StudentDetails = new StudentDetails(
                        student.getId(),
                        student.getName(),
                        student.getThesis(),
                        student.getGpa());
                detailsList.add(StudentDetails);
            }
        }

        return detailsList;
    }

    private List<CourseDetails> copyCoursesToDetails(List<Course> courses) {
        List<CourseDetails> detailsList = new ArrayList<CourseDetails>();

        if (courses != null) {
            for (Course course : courses) {
                CourseDetails CourseDetails = new CourseDetails(
                        course.getId(),
                        course.getName(),
                        course.getAdvisor()
                );
                detailsList.add(CourseDetails);
            }
        }

        return detailsList;
    }
}
