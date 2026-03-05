<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Danh Sách Sinh Viên</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>DANH SÁCH SINH VIÊN</h1>
            <nav class="breadcrumb">
                <a href="${pageContext.request.contextPath}/index.jsp">Trang chủ</a> &raquo;
                <span>Danh sách sinh viên</span>
            </nav>
        </header>
        
        <main>
            <!-- Message Display -->
            <c:if test="${not empty message}">
                <div class="message ${messageType}">
                    ${message}
                </div>
            </c:if>
            
            <!-- Search and Actions -->
            <div class="toolbar">
                <div class="search-box">
                    <form action="${pageContext.request.contextPath}/student" method="get">
                        <input type="hidden" name="action" value="search">
                        <input type="text" name="name" placeholder="Tìm kiếm theo tên..." 
                               value="${searchName}">
                        <button type="submit" class="btn btn-search">Tìm kiếm</button>
                    </form>
                </div>
                <div class="actions">
                    <a href="${pageContext.request.contextPath}/student?action=add" class="btn btn-primary">
                        + Thêm sinh viên mới
                    </a>
                </div>
            </div>
            
            <!-- Student Table -->
            <div class="table-container">
                <c:choose>
                    <c:when test="${empty students}">
                        <div class="empty-message">
                            <p>Không có sinh viên nào trong hệ thống.</p>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <table class="data-table">
                            <thead>
                                <tr>
                                    <th>STT</th>
                                    <th>Mã SV</th>
                                    <th>Họ và tên</th>
                                    <th>Ngày sinh</th>
                                    <th>Giới tính</th>
                                    <th>Email</th>
                                    <th>Số điện thoại</th>
                                    <th>Trạng thái</th>
                                    <th>Thao tác</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="student" items="${students}" varStatus="status">
                                    <tr>
                                        <td>${status.index + 1}</td>
                                        <td><strong>${student.studentCode}</strong></td>
                                        <td>${student.fullName}</td>
                                        <td>
                                            <fmt:formatDate value="${student.dateOfBirth}" pattern="dd/MM/yyyy"/>
                                        </td>
                                        <td>${student.gender}</td>
                                        <td>${student.email}</td>
                                        <td>${student.phone}</td>
                                        <td>
                                            <span class="status-badge status-${student.status}">
                                                <c:choose>
                                                    <c:when test="${student.status == 'Active'}">Đang học</c:when>
                                                    <c:when test="${student.status == 'Inactive'}">Tạm ngừng</c:when>
                                                    <c:when test="${student.status == 'Graduated'}">Đã tốt nghiệp</c:when>
                                                    <c:otherwise>${student.status}</c:otherwise>
                                                </c:choose>
                                            </span>
                                        </td>
                                        <td class="action-buttons">
                                            <a href="${pageContext.request.contextPath}/student?action=edit&id=${student.studentId}" 
                                               class="btn-icon btn-edit" title="Sửa">
                                                ✏️
                                            </a>
                                            <a href="${pageContext.request.contextPath}/score?action=byStudent&studentId=${student.studentId}" 
                                               class="btn-icon btn-view" title="Xem điểm">
                                                📊
                                            </a>
                                            <a href="${pageContext.request.contextPath}/student?action=delete&id=${student.studentId}" 
                                               class="btn-icon btn-delete" 
                                               onclick="return confirm('Bạn có chắc chắn muốn xóa sinh viên này?')" 
                                               title="Xóa">
                                                🗑️
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <div class="table-info">
                            <p>Tổng số sinh viên: <strong>${students.size()}</strong></p>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </main>
        
        <footer>
            <p>&copy; 2026 Student Information System. All rights reserved.</p>
        </footer>
    </div>
    
    <script src="${pageContext.request.contextPath}/js/script.js"></script>
</body>
</html>
