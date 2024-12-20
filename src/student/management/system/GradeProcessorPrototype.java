/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package student.management.system;

/**
 *
 * @author Dell
 */
public class GradeProcessorPrototype implements ProtoType {
    private final double assignmentWeight;
    private final double examWeight;

    public GradeProcessorPrototype(double assignmentWeight, double examWeight) {
        this.assignmentWeight = assignmentWeight;
        this.examWeight = examWeight;
    }
    @Override
    public ProtoType clone() {
        return new GradeProcessorPrototype(assignmentWeight, examWeight);
    }

    public double calculateGrade(double assignmentScore, double examScore) {
        return (assignmentScore * assignmentWeight) + (examScore * examWeight);
    }
}
