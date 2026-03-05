package com.lec16.dao;

import com.lec16.entity.Subject;
import com.lec16.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAO {

    public List<Subject> getAllSubjects() {
        List<Subject> subjects = new ArrayList<>();
        String sql = "SELECT * FROM subject_t ORDER BY subject_id";
        
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Subject subject = new Subject();
                subject.setSubjectId(rs.getInt("subject_id"));
                subject.setSubjectCode(rs.getString("subject_code"));
                subject.setSubjectName(rs.getString("subject_name"));
                subject.setCredit(rs.getInt("credit"));
                subjects.add(subject);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return subjects;
    }

    public Subject getSubjectById(int subjectId) {
        String sql = "SELECT * FROM subject_t WHERE subject_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, subjectId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                Subject subject = new Subject();
                subject.setSubjectId(rs.getInt("subject_id"));
                subject.setSubjectCode(rs.getString("subject_code"));
                subject.setSubjectName(rs.getString("subject_name"));
                subject.setCredit(rs.getInt("credit"));
                return subject;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    public boolean insertSubject(Subject subject) {
        String sql = "INSERT INTO subject_t (subject_code, subject_name, credit) VALUES (?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, subject.getSubjectCode());
            pstmt.setString(2, subject.getSubjectName());
            pstmt.setInt(3, subject.getCredit());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateSubject(Subject subject) {
        String sql = "UPDATE subject_t SET subject_code = ?, subject_name = ?, credit = ? WHERE subject_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, subject.getSubjectCode());
            pstmt.setString(2, subject.getSubjectName());
            pstmt.setInt(3, subject.getCredit());
            pstmt.setInt(4, subject.getSubjectId());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteSubject(int subjectId) {
        String sql = "DELETE FROM subject_t WHERE subject_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, subjectId);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
