/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package student.management.system;

/**
 *
 * @author Dell
 */
public class StudentFactory {
    public static Student createStudent(String studentType) {
        switch (studentType.toLowerCase()) {
            case "undergraduate":
                return new UndergraduateStudent();
            case "master":
                return new MasterStudent();
            case "part-time":
                return new PartTimeStudent();
            default:
                throw new IllegalArgumentException("Unknown student type");
        }
    }
}
