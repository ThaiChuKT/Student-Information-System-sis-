package com.lec16.util;

public class GradeConverter {
    
    public static String convertToGrade(double score) {
        if (score >= 8.0 && score <= 10.0) {
            return "A";
        } else if (score >= 6.0 && score < 8.0) {
            return "B";
        } else if (score >= 4.0 && score < 6.0) {
            return "D";
        } else {
            return "F";
        }
    }

    public static String getGradeColor(String grade) {
        switch (grade) {
            case "A":
                return "success"; // green
            case "B":
                return "info"; // blue
            case "D":
                return "warning"; // yellow
            case "F":
                return "danger"; // red
            default:
                return "secondary"; // gray
        }
    }

    public static double calculateFinalGrade(double score1, double score2) {
        return 0.3 * score1 + 0.7 * score2;
    }
}
