package com.lec16.controller;

import com.lec16.dao.StudentScoreDAO;
import com.lec16.entity.StudentScore;
import com.lec16.util.GradeConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Main controller for displaying student information
 */
@WebServlet("/")
public class HomeController extends HttpServlet {
    private StudentScoreDAO scoreDAO;

    @Override
    public void init() {
        scoreDAO = new StudentScoreDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Get all student scores
        List<StudentScore> scores = scoreDAO.getAllStudentScores();
        
        // Set attributes for JSP
        request.setAttribute("scores", scores);
        request.setAttribute("gradeConverter", new GradeConverter());
        
        // Forward to index page
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
