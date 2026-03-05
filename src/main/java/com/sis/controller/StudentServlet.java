package com.sis.controller;

import com.sis.entity.Student;
import com.sis.service.StudentService;

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

@WebServlet("/student")
public class StudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private StudentService studentService;
    private SimpleDateFormat dateFormat;
    
    @Override
    public void init() throws ServletException {
        studentService = new StudentService();
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
                listStudents(request, response);
                break;
            case "add":
                showAddForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteStudent(request, response);
                break;
            case "search":
                searchStudents(request, response);
                break;
            default:
                listStudents(request, response);
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
                addStudent(request, response);
                break;
            case "update":
                updateStudent(request, response);
                break;
            default:
                listStudents(request, response);
                break;
        }
    }
    
    private void listStudents(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Student> students = studentService.getAllStudents();
        request.setAttribute("students", students);
        request.getRequestDispatcher("/student/listStudent.jsp").forward(request, response);
    }
    
    private void showAddForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("/student/addStudent.jsp").forward(request, response);
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int studentId = Integer.parseInt(request.getParameter("id"));
        Student student = studentService.getStudentById(studentId);
        
        if (student != null) {
            request.setAttribute("student", student);
            request.getRequestDispatcher("/student/addStudent.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/student?action=list");
        }
    }
    
    private void addStudent(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            Student student = new Student();
            student.setStudentCode(request.getParameter("studentCode"));
            student.setFullName(request.getParameter("fullName"));
            student.setDateOfBirth(dateFormat.parse(request.getParameter("dateOfBirth")));
            student.setGender(request.getParameter("gender"));
            student.setEmail(request.getParameter("email"));
            student.setPhone(request.getParameter("phone"));
            student.setAddress(request.getParameter("address"));
            student.setEnrollmentDate(dateFormat.parse(request.getParameter("enrollmentDate")));
            student.setStatus(request.getParameter("status"));
            
            boolean success = studentService.addStudent(student);
            
            if (success) {
                request.setAttribute("message", "Thêm sinh viên thành công!");
                request.setAttribute("messageType", "success");
            } else {
                request.setAttribute("message", "Thêm sinh viên thất bại! Mã sinh viên có thể đã tồn tại.");
                request.setAttribute("messageType", "error");
            }
            
        } catch (ParseException e) {
            request.setAttribute("message", "Lỗi định dạng ngày tháng!");
            request.setAttribute("messageType", "error");
            e.printStackTrace();
        } catch (Exception e) {
            request.setAttribute("message", "Có lỗi xảy ra: " + e.getMessage());
            request.setAttribute("messageType", "error");
            e.printStackTrace();
        }
        
        listStudents(request, response);
    }
    
    private void updateStudent(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            Student student = new Student();
            student.setStudentId(Integer.parseInt(request.getParameter("studentId")));
            student.setStudentCode(request.getParameter("studentCode"));
            student.setFullName(request.getParameter("fullName"));
            student.setDateOfBirth(dateFormat.parse(request.getParameter("dateOfBirth")));
            student.setGender(request.getParameter("gender"));
            student.setEmail(request.getParameter("email"));
            student.setPhone(request.getParameter("phone"));
            student.setAddress(request.getParameter("address"));
            student.setEnrollmentDate(dateFormat.parse(request.getParameter("enrollmentDate")));
            student.setStatus(request.getParameter("status"));
            
            boolean success = studentService.updateStudent(student);
            
            if (success) {
                request.setAttribute("message", "Cập nhật sinh viên thành công!");
                request.setAttribute("messageType", "success");
            } else {
                request.setAttribute("message", "Cập nhật sinh viên thất bại!");
                request.setAttribute("messageType", "error");
            }
            
        } catch (ParseException e) {
            request.setAttribute("message", "Lỗi định dạng ngày tháng!");
            request.setAttribute("messageType", "error");
            e.printStackTrace();
        } catch (Exception e) {
            request.setAttribute("message", "Có lỗi xảy ra: " + e.getMessage());
            request.setAttribute("messageType", "error");
            e.printStackTrace();
        }
        
        listStudents(request, response);
    }
    
    private void deleteStudent(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            int studentId = Integer.parseInt(request.getParameter("id"));
            boolean success = studentService.deleteStudent(studentId);
            
            if (success) {
                request.setAttribute("message", "Xóa sinh viên thành công!");
                request.setAttribute("messageType", "success");
            } else {
                request.setAttribute("message", "Xóa sinh viên thất bại!");
                request.setAttribute("messageType", "error");
            }
            
        } catch (Exception e) {
            request.setAttribute("message", "Có lỗi xảy ra: " + e.getMessage());
            request.setAttribute("messageType", "error");
            e.printStackTrace();
        }
        
        listStudents(request, response);
    }
    
    private void searchStudents(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String searchName = request.getParameter("name");
        List<Student> students = studentService.searchStudentsByName(searchName);
        request.setAttribute("students", students);
        request.setAttribute("searchName", searchName);
        request.getRequestDispatcher("/student/listStudent.jsp").forward(request, response);
    }
}
