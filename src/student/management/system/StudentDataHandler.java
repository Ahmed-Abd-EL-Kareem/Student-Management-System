/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package student.management.system;
import java.sql.*;
/**
 *
 * @author Dell
 */
public class StudentDataHandler {
    private Connection connection;

    public StudentDataHandler(String url) throws SQLException {
        connection = DriverManager.getConnection(url);
    }
    // Method to add a course to the system
    public void addCourseToSystem(String courseId) {
        try {
            String query = "INSERT INTO courseAD (Course_ID) VALUES (?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, courseId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Course added: " + courseId);
            } else {
                System.out.println("Failed to add course: " + courseId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to register a student to a course
    public void registerStudentToCourse(String studentId, String courseId) {
        try {
            String query = "INSERT INTO courses (student_id, course_id) VALUES (?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, studentId);
            stmt.setString(2, courseId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Student " + studentId + " registered to course " + courseId);
            } else {
                System.out.println("Failed to register student " + studentId + " to course " + courseId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
