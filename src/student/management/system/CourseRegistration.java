/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package student.management.system;

/**
 *
 * @author Dell
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseRegistration {
   private static CourseRegistration instance;
    private Connection connection;

    // Private constructor to establish database connection
    private CourseRegistration(String url) throws SQLException {
        connection = DriverManager.getConnection(url);

    }

    // Static method to get the single instance
    public static synchronized CourseRegistration getInstance(String url) throws SQLException {
        if (instance == null) {
            instance = new CourseRegistration( url);
        }
        return instance;
    }

    // Enroll a student in a course
    public void enroll(String studentId, String courseId )  {
        String query = "INSERT INTO Courses (Student_ID, Course_ID) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, studentId);
            stmt.setString(2, courseId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get list of students enrolled in a course
    public List<String> getEnrollments(String courseId ) {
        List<String> students = new ArrayList<>();
        String query = "SELECT Student_ID FROM Courses WHERE Course_ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, courseId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                students.add(rs.getString("Student_ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

}
