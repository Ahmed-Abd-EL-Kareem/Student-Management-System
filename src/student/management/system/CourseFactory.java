/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package student.management.system;

/**
 *
 * @author Dell
 */
public class CourseFactory {
    public static Course createCourse(String courseType) {
        switch (courseType.toLowerCase()) {
            case "core":
                return new CoreCourse();
            case "elective":
                return new ElectiveCourse();
            case "lab":
                return new LabCourse();
            default:
                throw new IllegalArgumentException("Unknown course type");
        }
    }
}
