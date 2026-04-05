package com.devops;

public class StudentMarksProcessor {

    public static int calculateTotal(int[] marks) {
        int total = 0;
        for (int m : marks) {
            total += m;
        }
        return total;
    }

    public static double calculateAverage(int[] marks) {
        if (marks.length == 0) return 0;
        return (double) calculateTotal(marks) / marks.length;
    }

    public static String checkResult(int[] marks) {
        double avg = calculateAverage(marks);
        return (avg >= 40) ? "Pass" : "Fail";
    }

    public static void main(String[] args) {
        System.out.println("Student Marks Service Running...");
    }
}
