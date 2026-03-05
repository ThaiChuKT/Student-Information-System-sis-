package com.lec16.dao;

import com.lec16.entity.StudentScore;
import com.lec16.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO class for StudentScore operations
 */
public class StudentScoreDAO {

    /**
     * Insert a new student score
     */
    public boolean insertStudentScore(StudentScore score) {
        String sql = "INSERT INTO student_score_t (student_id, subject_id, score1, score2) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, score.getStudentId());
            pstmt.setInt(2, score.getSubjectId());
            pstmt.setDouble(3, score.getScore1());
            pstmt.setDouble(4, score.getScore2());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Get all student scores with full details
     */
    public List<StudentScore> getAllStudentScores() {
        List<StudentScore> scores = new ArrayList<>();
        String sql = "SELECT ss.*, st.student_code, st.full_name, " +
                     "sub.subject_code, sub.subject_name, sub.credit " +
                     "FROM student_score_t ss " +
                     "JOIN student_t st ON ss.student_id = st.student_id " +
                     "JOIN subject_t sub ON ss.subject_id = sub.subject_id " +
                     "ORDER BY st.student_code, sub.subject_code";
        
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                StudentScore score = new StudentScore();
                score.setStudentScoreId(rs.getInt("student_score_id"));
                score.setStudentId(rs.getInt("student_id"));
                score.setSubjectId(rs.getInt("subject_id"));
                score.setScore1(rs.getDouble("score1"));
                score.setScore2(rs.getDouble("score2"));
                score.setStudentCode(rs.getString("student_code"));
                score.setStudentName(rs.getString("full_name"));
                score.setSubjectCode(rs.getString("subject_code"));
                score.setSubjectName(rs.getString("subject_name"));
                score.setCredit(rs.getInt("credit"));
                scores.add(score);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return scores;
    }

    /**
     * Get scores by student ID
     */
    public List<StudentScore> getScoresByStudentId(int studentId) {
        List<StudentScore> scores = new ArrayList<>();
        String sql = "SELECT ss.*, st.student_code, st.full_name, " +
                     "sub.subject_code, sub.subject_name, sub.credit " +
                     "FROM student_score_t ss " +
                     "JOIN student_t st ON ss.student_id = st.student_id " +
                     "JOIN subject_t sub ON ss.subject_id = sub.subject_id " +
                     "WHERE ss.student_id = ? " +
                     "ORDER BY sub.subject_code";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, studentId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                StudentScore score = new StudentScore();
                score.setStudentScoreId(rs.getInt("student_score_id"));
                score.setStudentId(rs.getInt("student_id"));
                score.setSubjectId(rs.getInt("subject_id"));
                score.setScore1(rs.getDouble("score1"));
                score.setScore2(rs.getDouble("score2"));
                score.setStudentCode(rs.getString("student_code"));
                score.setStudentName(rs.getString("full_name"));
                score.setSubjectCode(rs.getString("subject_code"));
                score.setSubjectName(rs.getString("subject_name"));
                score.setCredit(rs.getInt("credit"));
                scores.add(score);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return scores;
    }

    /**
     * Get score by ID
     */
    public StudentScore getScoreById(int scoreId) {
        String sql = "SELECT ss.*, st.student_code, st.full_name, " +
                     "sub.subject_code, sub.subject_name, sub.credit " +
                     "FROM student_score_t ss " +
                     "JOIN student_t st ON ss.student_id = st.student_id " +
                     "JOIN subject_t sub ON ss.subject_id = sub.subject_id " +
                     "WHERE ss.student_score_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, scoreId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                StudentScore score = new StudentScore();
                score.setStudentScoreId(rs.getInt("student_score_id"));
                score.setStudentId(rs.getInt("student_id"));
                score.setSubjectId(rs.getInt("subject_id"));
                score.setScore1(rs.getDouble("score1"));
                score.setScore2(rs.getDouble("score2"));
                score.setStudentCode(rs.getString("student_code"));
                score.setStudentName(rs.getString("full_name"));
                score.setSubjectCode(rs.getString("subject_code"));
                score.setSubjectName(rs.getString("subject_name"));
                score.setCredit(rs.getInt("credit"));
                return score;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    /**
     * Update student score
     */
    public boolean updateStudentScore(StudentScore score) {
        String sql = "UPDATE student_score_t SET student_id = ?, subject_id = ?, score1 = ?, score2 = ? " +
                     "WHERE student_score_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, score.getStudentId());
            pstmt.setInt(2, score.getSubjectId());
            pstmt.setDouble(3, score.getScore1());
            pstmt.setDouble(4, score.getScore2());
            pstmt.setInt(5, score.getStudentScoreId());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Delete student score
     */
    public boolean deleteStudentScore(int scoreId) {
        String sql = "DELETE FROM student_score_t WHERE student_score_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, scoreId);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Check if score exists for student and subject
     */
    public boolean isScoreExists(int studentId, int subjectId) {
        String sql = "SELECT COUNT(*) FROM student_score_t WHERE student_id = ? AND subject_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, studentId);
            pstmt.setInt(2, subjectId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
}
