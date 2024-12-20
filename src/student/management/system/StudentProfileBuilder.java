/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package student.management.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Dell
 */
public class StudentProfileBuilder {
    private String name;
    private String gen;
    private int age;
    private Connection connection;

    public StudentProfileBuilder Name(String name){
    this.name= name;
    return this;
    }
    public StudentProfileBuilder Gen(String gen){
    this.gen= gen;
    return this;
    }
    public StudentProfileBuilder Age(int age){
    this.age= age;
    return this;
    }
    public StudentProfile bulid(String url,String studentId,String courseId) throws SQLException{
    connection = DriverManager.getConnection(url);
        String query = """
        UPDATE grades 
        SET 
            name = CASE WHEN ? IS NOT NULL THEN ? ELSE name END,
            gender = CASE WHEN ? IS NOT NULL THEN ? ELSE gender END,
            age = CASE WHEN ? > 0 THEN ? ELSE age END
        WHERE student_id = ? AND course_id = ?
    """;
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, name);
            pstmt.setString(2, name);
            pstmt.setString(3,  gen);
            pstmt.setString(4,  gen);
            pstmt.setDouble(5, age);
            pstmt.setDouble(6, age);
            pstmt.setString(7, studentId);
            pstmt.setString(8, courseId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new StudentProfile(name, gen, age);
    }
}
