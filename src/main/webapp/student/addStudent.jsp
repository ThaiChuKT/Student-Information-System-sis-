<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${student != null ? 'Cập Nhật' : 'Thêm'} Sinh Viên</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>${student != null ? 'CẬP NHẬT' : 'THÊM'} SINH VIÊN</h1>
            <nav class="breadcrumb">
                <a href="${pageContext.request.contextPath}/index.jsp">Trang chủ</a> &raquo;
                <a href="${pageContext.request.contextPath}/student?action=list">Danh sách sinh viên</a> &raquo;
                <span>${student != null ? 'Cập nhật' : 'Thêm mới'}</span>
            </nav>
        </header>
        
        <main>
            <div class="form-container">
                <form action="${pageContext.request.contextPath}/student" method="post" onsubmit="return validateForm()">
                    <input type="hidden" name="action" value="${student != null ? 'update' : 'add'}">
                    <c:if test="${student != null}">
                        <input type="hidden" name="studentId" value="${student.studentId}">
                    </c:if>
                    
                    <div class="form-row">
                        <div class="form-group">
                            <label for="studentCode">Mã sinh viên <span class="required">*</span></label>
                            <input type="text" id="studentCode" name="studentCode" 
                                   value="${student != null ? student.studentCode : ''}" 
                                   required ${student != null ? 'readonly' : ''}>
                        </div>
                        
                        <div class="form-group">
                            <label for="fullName">Họ và tên <span class="required">*</span></label>
                            <input type="text" id="fullName" name="fullName" 
                                   value="${student != null ? student.fullName : ''}" required>
                        </div>
                    </div>
                    
                    <div class="form-row">
                        <div class="form-group">
                            <label for="dateOfBirth">Ngày sinh <span class="required">*</span></label>
                            <input type="date" id="dateOfBirth" name="dateOfBirth" 
                                   value="${student != null ? student.dateOfBirth : ''}" required>
                        </div>
                        
                        <div class="form-group">
                            <label for="gender">Giới tính <span class="required">*</span></label>
                            <select id="gender" name="gender" required>
                                <option value="">-- Chọn giới tính --</option>
                                <option value="Nam" ${student != null && student.gender == 'Nam' ? 'selected' : ''}>Nam</option>
                                <option value="Nữ" ${student != null && student.gender == 'Nữ' ? 'selected' : ''}>Nữ</option>
                                <option value="Khác" ${student != null && student.gender == 'Khác' ? 'selected' : ''}>Khác</option>
                            </select>
                        </div>
                    </div>
                    
                    <div class="form-row">
                        <div class="form-group">
                            <label for="email">Email <span class="required">*</span></label>
                            <input type="email" id="email" name="email" 
                                   value="${student != null ? student.email : ''}" required>
                        </div>
                        
                        <div class="form-group">
                            <label for="phone">Số điện thoại <span class="required">*</span></label>
                            <input type="tel" id="phone" name="phone" 
                                   value="${student != null ? student.phone : ''}" 
                                   pattern="[0-9]{10,11}" required>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="address">Địa chỉ</label>
                        <textarea id="address" name="address" rows="3">${student != null ? student.address : ''}</textarea>
                    </div>
                    
                    <div class="form-row">
                        <div class="form-group">
                            <label for="enrollmentDate">Ngày nhập học <span class="required">*</span></label>
                            <input type="date" id="enrollmentDate" name="enrollmentDate" 
                                   value="${student != null ? student.enrollmentDate : ''}" required>
                        </div>
                        
                        <div class="form-group">
                            <label for="status">Trạng thái <span class="required">*</span></label>
                            <select id="status" name="status" required>
                                <option value="">-- Chọn trạng thái --</option>
                                <option value="Active" ${student != null && student.status == 'Active' ? 'selected' : ''}>Đang học</option>
                                <option value="Inactive" ${student != null && student.status == 'Inactive' ? 'selected' : ''}>Tạm ngừng</option>
                                <option value="Graduated" ${student != null && student.status == 'Graduated' ? 'selected' : ''}>Đã tốt nghiệp</option>
                            </select>
                        </div>
                    </div>
                    
                    <div class="form-actions">
                        <button type="submit" class="btn btn-primary">
                            ${student != null ? 'Cập nhật' : 'Thêm mới'}
                        </button>
                        <a href="${pageContext.request.contextPath}/student?action=list" class="btn btn-secondary">
                            Hủy
                        </a>
                    </div>
                </form>
            </div>
        </main>
        
        <footer>
            <p>&copy; 2026 Student Information System. All rights reserved.</p>
        </footer>
    </div>
    
    <script src="${pageContext.request.contextPath}/js/script.js"></script>
    <script>
        function validateForm() {
            const phone = document.getElementById('phone').value;
            const phonePattern = /^[0-9]{10,11}$/;
            
            if (!phonePattern.test(phone)) {
                alert('Số điện thoại phải có 10-11 chữ số!');
                return false;
            }
            
            return true;
        }
    </script>
</body>
</html>
