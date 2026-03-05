package com.lec16.entity;

/**
 * Entity class representing a student's score in a subject
 */
public class StudentScore {
    private int studentScoreId;
    private int studentId;
    private int subjectId;
    private double score1;
    private double score2;
    
    // Additional fields for display purposes
    private String studentCode;
    private String studentName;
    private String subjectCode;
    private String subjectName;
    private int credit;

    public StudentScore() {
    }

    public StudentScore(int studentId, int subjectId, double score1, double score2) {
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.score1 = score1;
        this.score2 = score2;
    }

    public StudentScore(int studentScoreId, int studentId, int subjectId, double score1, double score2) {
        this.studentScoreId = studentScoreId;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.score1 = score1;
        this.score2 = score2;
    }

    /**
     * Calculate final grade based on the formula: Grade = 0.3*score1 + 0.7*score2
     */
    public double calculateGrade() {
        return 0.3 * score1 + 0.7 * score2;
    }

    // Getters and Setters
    public int getStudentScoreId() {
        return studentScoreId;
    }

    public void setStudentScoreId(int studentScoreId) {
        this.studentScoreId = studentScoreId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public double getScore1() {
        return score1;
    }

    public void setScore1(double score1) {
        this.score1 = score1;
    }

    public double getScore2() {
        return score2;
    }

    public void setScore2(double score2) {
        this.score2 = score2;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    @Override
    public String toString() {
        return "StudentScore{" +
                "studentScoreId=" + studentScoreId +
                ", studentId=" + studentId +
                ", subjectId=" + subjectId +
                ", score1=" + score1 +
                ", score2=" + score2 +
                ", grade=" + calculateGrade() +
                '}';
    }
}
