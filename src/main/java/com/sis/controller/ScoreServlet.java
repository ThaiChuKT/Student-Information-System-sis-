package com.sis.controller;

import com.sis.entity.Score;
import com.sis.entity.Student;
import com.sis.entity.Subject;
import com.sis.service.ScoreService;
import com.sis.service.StudentService;
import com.sis.repository.SubjectDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/score")
public class ScoreServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ScoreService scoreService;
    private StudentService studentService;
    private SubjectDAO subjectDAO;
    private SimpleDateFormat dateFormat;
    
    @Override
    public void init() throws ServletException {
        scoreService = new ScoreService();
        studentService = new StudentService();
        subjectDAO = new SubjectDAO();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if (action == null) {
            action = "list";
        }
        
        switch (action) {
            case "list":
                listScores(request, response);
                break;
            case "add":
                showAddForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteScore(request, response);
                break;
            case "byStudent":
                listScoresByStudent(request, response);
                break;
            default:
                listScores(request, response);
                break;
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        String action = request.getParameter("action");
        
        if (action == null) {
            action = "list";
        }
        
        switch (action) {
            case "add":
                addScore(request, response);
                break;
            case "update":
                updateScore(request, response);
                break;
            default:
                listScores(request, response);
                break;
        }
    }
    
    private void listScores(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Score> scores = scoreService.getAllScores();
        request.setAttribute("scores", scores);
        request.getRequestDispatcher("/score/listScore.jsp").forward(request, response);
    }
    
    private void showAddForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Get all students and subjects for dropdown lists
        List<Student> students = studentService.getAllStudents();
        List<Subject> subjects = subjectDAO.getAllSubjects();
        
        request.setAttribute("students", students);
        request.setAttribute("subjects", subjects);
        request.getRequestDispatcher("/score/addScore.jsp").forward(request, response);
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int scoreId = Integer.parseInt(request.getParameter("id"));
        Score score = scoreService.getScoreById(scoreId);
        
        if (score != null) {
            List<Student> students = studentService.getAllStudents();
            List<Subject> subjects = subjectDAO.getAllSubjects();
            
            request.setAttribute("score", score);
            request.setAttribute("students", students);
            request.setAttribute("subjects", subjects);
            request.getRequestDispatcher("/score/addScore.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/score?action=list");
        }
    }
    
    private void addScore(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            Score score = new Score();
            score.setStudentId(Integer.parseInt(request.getParameter("studentId")));
            score.setSubjectId(Integer.parseInt(request.getParameter("subjectId")));
            score.setMidtermScore(Double.parseDouble(request.getParameter("midtermScore")));
            score.setFinalScore(Double.parseDouble(request.getParameter("finalScore")));
            score.setSemester(request.getParameter("semester"));
            score.setYear(Integer.parseInt(request.getParameter("year")));
            
            String examDateStr = request.getParameter("examDate");
            if (examDateStr != null && !examDateStr.trim().isEmpty()) {
                score.setExamDate(dateFormat.parse(examDateStr));
            }
            
            // Validate scores
            if (!scoreService.isValidScore(score.getMidtermScore()) || 
                !scoreService.isValidScore(score.getFinalScore())) {
                request.setAttribute("message", "Điểm phải nằm trong khoảng từ 0 đến 10!");
                request.setAttribute("messageType", "error");
                showAddForm(request, response);
                return;
            }
            
            boolean success = scoreService.addScore(score);
            
            if (success) {
                request.setAttribute("message", "Thêm điểm thành công!");
                request.setAttribute("messageType", "success");
            } else {
                request.setAttribute("message", "Thêm điểm thất bại!");
                request.setAttribute("messageType", "error");
            }
            
        } catch (ParseException e) {
            request.setAttribute("message", "Lỗi định dạng ngày tháng!");
            request.setAttribute("messageType", "error");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            request.setAttribute("message", "Lỗi định dạng số!");
            request.setAttribute("messageType", "error");
            e.printStackTrace();
        } catch (Exception e) {
            request.setAttribute("message", "Có lỗi xảy ra: " + e.getMessage());
            request.setAttribute("messageType", "error");
            e.printStackTrace();
        }
        
        listScores(request, response);
    }
    
    private void updateScore(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            Score score = new Score();
            score.setScoreId(Integer.parseInt(request.getParameter("scoreId")));
            score.setStudentId(Integer.parseInt(request.getParameter("studentId")));
            score.setSubjectId(Integer.parseInt(request.getParameter("subjectId")));
            score.setMidtermScore(Double.parseDouble(request.getParameter("midtermScore")));
            score.setFinalScore(Double.parseDouble(request.getParameter("finalScore")));
            score.setSemester(request.getParameter("semester"));
            score.setYear(Integer.parseInt(request.getParameter("year")));
            
            String examDateStr = request.getParameter("examDate");
            if (examDateStr != null && !examDateStr.trim().isEmpty()) {
                score.setExamDate(dateFormat.parse(examDateStr));
            }
            
            // Validate scores
            if (!scoreService.isValidScore(score.getMidtermScore()) || 
                !scoreService.isValidScore(score.getFinalScore())) {
                request.setAttribute("message", "Điểm phải nằm trong khoảng từ 0 đến 10!");
                request.setAttribute("messageType", "error");
                showEditForm(request, response);
                return;
            }
            
            boolean success = scoreService.updateScore(score);
            
            if (success) {
                request.setAttribute("message", "Cập nhật điểm thành công!");
                request.setAttribute("messageType", "success");
            } else {
                request.setAttribute("message", "Cập nhật điểm thất bại!");
                request.setAttribute("messageType", "error");
            }
            
        } catch (ParseException e) {
            request.setAttribute("message", "Lỗi định dạng ngày tháng!");
            request.setAttribute("messageType", "error");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            request.setAttribute("message", "Lỗi định dạng số!");
            request.setAttribute("messageType", "error");
            e.printStackTrace();
        } catch (Exception e) {
            request.setAttribute("message", "Có lỗi xảy ra: " + e.getMessage());
            request.setAttribute("messageType", "error");
            e.printStackTrace();
        }
        
        listScores(request, response);
    }
    
    private void deleteScore(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            int scoreId = Integer.parseInt(request.getParameter("id"));
            boolean success = scoreService.deleteScore(scoreId);
            
            if (success) {
                request.setAttribute("message", "Xóa điểm thành công!");
                request.setAttribute("messageType", "success");
            } else {
                request.setAttribute("message", "Xóa điểm thất bại!");
                request.setAttribute("messageType", "error");
            }
            
        } catch (Exception e) {
            request.setAttribute("message", "Có lỗi xảy ra: " + e.getMessage());
            request.setAttribute("messageType", "error");
            e.printStackTrace();
        }
        
        listScores(request, response);
    }
    
    private void listScoresByStudent(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        Student student = studentService.getStudentById(studentId);
        List<Score> scores = scoreService.getScoresByStudentId(studentId);
        double gpa = scoreService.calculateGPA(studentId);
        
        request.setAttribute("student", student);
        request.setAttribute("scores", scores);
        request.setAttribute("gpa", gpa);
        request.getRequestDispatcher("/score/listScore.jsp").forward(request, response);
    }
}
