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

public class CoursePrototype implements ProtoType {
    private final String courseID;
    private final String courseName;
    private Connection connection;


    public CoursePrototype( String courseName,String courseID) {
        this.courseID = courseID;
        this.courseName = courseName;
    }

    @Override
    public ProtoType clone() {
        return new CoursePrototype( courseName,courseID);
    }


    public void saveToDatabase(String url) throws SQLException {
        connection = DriverManager.getConnection(url);
        try {
            String query = "INSERT INTO Grade (course_Name, course_Id) VALUES (?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, courseName);
            stmt.setString(2, courseID);
            stmt.executeUpdate();
            System.out.println("Course saved to database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
}
