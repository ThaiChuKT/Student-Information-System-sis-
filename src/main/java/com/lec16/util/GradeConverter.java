package com.lec16.util;

/**
 * Utility class for converting numeric scores to letter grades
 */
public class GradeConverter {
    
    /**
     * Convert numeric score to letter grade
     * A: 8.0 to 10
     * B: 6.0 to 7.9
     * D: 4.0 to 5.9
     * F: less than 4.0
     */
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

    /**
     * Get grade with color for display
     */
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

    /**
     * Calculate final grade from two scores
     * Formula: Grade = 0.3 * score1 + 0.7 * score2
     */
    public static double calculateFinalGrade(double score1, double score2) {
        return 0.3 * score1 + 0.7 * score2;
    }
}
