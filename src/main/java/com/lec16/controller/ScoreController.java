package com.lec16.controller;

import com.lec16.dao.StudentDAO;
import com.lec16.dao.StudentScoreDAO;
import com.lec16.dao.SubjectDAO;
import com.lec16.entity.StudentScore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/score")
public class ScoreController extends HttpServlet {
    private StudentScoreDAO scoreDAO;
    private StudentDAO studentDAO;
    private SubjectDAO subjectDAO;

    @Override
    public void init() {
        scoreDAO = new StudentScoreDAO();
        studentDAO = new StudentDAO();
        subjectDAO = new SubjectDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "add":
                showAddForm(request, response);
                break;
            case "list":
            default:
                listScores(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("insert".equals(action)) {
            insertScore(request, response);
        }
    }

    private void listScores(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("scores", scoreDAO.getAllStudentScores());
        request.getRequestDispatcher("/score-list.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("students", studentDAO.getAllStudents());
        request.setAttribute("subjects", subjectDAO.getAllSubjects());
        request.getRequestDispatcher("/score-form.jsp").forward(request, response);
    }

    private void insertScore(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int studentId = Integer.parseInt(request.getParameter("studentId"));
            int subjectId = Integer.parseInt(request.getParameter("subjectId"));
            double score1 = Double.parseDouble(request.getParameter("score1"));
            double score2 = Double.parseDouble(request.getParameter("score2"));

            StudentScore score = new StudentScore(studentId, subjectId, score1, score2);
            boolean success = scoreDAO.insertStudentScore(score);

            if (success) {
                request.getSession().setAttribute("message", "Score added successfully!");
                request.getSession().setAttribute("messageType", "success");
            } else {
                request.getSession().setAttribute("message", "Failed to add score!");
                request.getSession().setAttribute("messageType", "danger");
            }
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("message", "Invalid input data!");
            request.getSession().setAttribute("messageType", "danger");
        }

        response.sendRedirect(request.getContextPath() + "/");
    }
}
