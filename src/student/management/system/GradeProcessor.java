/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package student.management.system;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Dell
 */
public class GradeProcessor {
    private static GradeProcessor instance;
    private Connection connection;
    private GradeProcessor(String url) throws SQLException{
        connection = DriverManager.getConnection(url);
    }
    public static synchronized GradeProcessor getInstance(String url) throws SQLException {
        if (instance == null) {
            instance = new GradeProcessor(url);
        }
        return instance;
    }
     public void addGrade(String studentId, String courseId, double grade) {
        String query = "MERGE INTO grades AS target\n" +
                        "USING (VALUES (?, ?, ?)) AS source (student_id, course_id, grade)\n" +
                        "ON target.student_id = source.student_id AND target.course_id = source.course_id\n" +
                        "WHEN MATCHED THEN\n" +
                        "UPDATE SET grade = source.grade\n" +
                        "WHEN NOT MATCHED THEN\n" +
                        "INSERT (student_id, course_id, grade) VALUES (source.student_id, source.course_id, source.grade);";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, studentId);
            pstmt.setString(2, courseId);
            pstmt.setDouble(3, grade);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String calculateGPA(String studentId) {
    String query = "SELECT AVG(grade) AS average FROM grades WHERE student_id = ?";
    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
        pstmt.setString(1, studentId);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            double average = rs.getDouble("average");
            return convertToGPA(average);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return "";
}

private String convertToGPA(double averageGrade) {
    // Example conversion: adjust based on your grading scale
    if (averageGrade >= 90) return "A+"; 
    else if (averageGrade >= 80) return "A"; 
    else if (averageGrade >= 70) return "B+"; 
    else if (averageGrade >= 60) return "C+";
    else if (averageGrade >= 50) return "D+"; 
    else return "F"; 
}


    public Map<String, Double> getGrade(String studentId) {
       String query = "SELECT course_id, grade FROM grades WHERE student_id = ?";
       Map<String, Double> grades = new HashMap<>();
    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
        pstmt.setString(1, studentId);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            grades.put(rs.getString("course_id"), rs.getDouble("grade"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return grades;
    }
}
