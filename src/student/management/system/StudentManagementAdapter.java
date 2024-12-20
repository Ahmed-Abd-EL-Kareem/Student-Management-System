/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package student.management.system;

/**
 *
 * @author Dell
 */
public class StudentManagementAdapter implements StudentManagement{
    private StudentDataHandler legacyHandler;

    public StudentManagementAdapter(StudentDataHandler legacyHandler) {
        this.legacyHandler = legacyHandler;
    }

    @Override
    public void addCourse(String courseId) {
        legacyHandler.addCourseToSystem(courseId);
    }

    @Override
    public void registerStudent(String studentId, String courseId) {
        legacyHandler.registerStudentToCourse(studentId, courseId);
    }

}