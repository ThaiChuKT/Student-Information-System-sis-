package com.lec16.controller;

import com.lec16.dao.StudentDAO;
import com.lec16.entity.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/student")
public class StudentController extends HttpServlet {
    private StudentDAO studentDAO;

    @Override
    public void init() {
        studentDAO = new StudentDAO();
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
                listStudents(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("insert".equals(action)) {
            insertStudent(request, response);
        }
    }

    private void listStudents(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("students", studentDAO.getAllStudents());
        request.getRequestDispatcher("/student-list.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/student-form.jsp").forward(request, response);
    }

    private void insertStudent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        String studentCode = request.getParameter("studentCode");
        String fullName = request.getParameter("fullName");
        String address = request.getParameter("address");

        Student student = new Student(studentCode, fullName, address);
        boolean success = studentDAO.insertStudent(student);

        if (success) {
            request.setAttribute("message", "Student added successfully!");
            request.setAttribute("messageType", "success");
        } else {
            request.setAttribute("message", "Failed to add student!");
            request.setAttribute("messageType", "danger");
        }

        response.sendRedirect(request.getContextPath() + "/");
    }
}
