<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${score != null ? 'Cập Nhật' : 'Nhập'} Điểm</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>${score != null ? 'CẬP NHẬT' : 'NHẬP'} ĐIỂM</h1>
            <nav class="breadcrumb">
                <a href="${pageContext.request.contextPath}/index.jsp">Trang chủ</a> &raquo;
                <a href="${pageContext.request.contextPath}/score?action=list">Danh sách điểm</a> &raquo;
                <span>${score != null ? 'Cập nhật' : 'Nhập mới'}</span>
            </nav>
        </header>
        
        <main>
            <div class="form-container">
                <form action="${pageContext.request.contextPath}/score" method="post" onsubmit="return validateScoreForm()">
                    <input type="hidden" name="action" value="${score != null ? 'update' : 'add'}">
                    <c:if test="${score != null}">
                        <input type="hidden" name="scoreId" value="${score.scoreId}">
                    </c:if>
                    
                    <div class="form-row">
                        <div class="form-group">
                            <label for="studentId">Sinh viên <span class="required">*</span></label>
                            <select id="studentId" name="studentId" required ${score != null ? 'disabled' : ''}>
                                <option value="">-- Chọn sinh viên --</option>
                                <c:forEach var="student" items="${students}">
                                    <option value="${student.studentId}" 
                                            ${score != null && score.studentId == student.studentId ? 'selected' : ''}>
                                        ${student.studentCode} - ${student.fullName}
                                    </option>
                                </c:forEach>
                            </select>
                            <c:if test="${score != null}">
                                <input type="hidden" name="studentId" value="${score.studentId}">
                            </c:if>
                        </div>
                        
                        <div class="form-group">
                            <label for="subjectId">Môn học <span class="required">*</span></label>
                            <select id="subjectId" name="subjectId" required ${score != null ? 'disabled' : ''}>
                                <option value="">-- Chọn môn học --</option>
                                <c:forEach var="subject" items="${subjects}">
                                    <option value="${subject.subjectId}" 
                                            ${score != null && score.subjectId == subject.subjectId ? 'selected' : ''}>
                                        ${subject.subjectCode} - ${subject.subjectName}
                                    </option>
                                </c:forEach>
                            </select>
                            <c:if test="${score != null}">
                                <input type="hidden" name="subjectId" value="${score.subjectId}">
                            </c:if>
                        </div>
                    </div>
                    
                    <div class="form-row">
                        <div class="form-group">
                            <label for="midtermScore">Điểm giữa kỳ <span class="required">*</span></label>
                            <input type="number" id="midtermScore" name="midtermScore" 
                                   min="0" max="10" step="0.1"
                                   value="${score != null ? score.midtermScore : ''}" required>
                            <small>Điểm từ 0 đến 10</small>
                        </div>
                        
                        <div class="form-group">
                            <label for="finalScore">Điểm cuối kỳ <span class="required">*</span></label>
                            <input type="number" id="finalScore" name="finalScore" 
                                   min="0" max="10" step="0.1"
                                   value="${score != null ? score.finalScore : ''}" required>
                            <small>Điểm từ 0 đến 10</small>
                        </div>
                    </div>
                    
                    <div class="form-row">
                        <div class="form-group">
                            <label for="semester">Học kỳ <span class="required">*</span></label>
                            <select id="semester" name="semester" required>
                                <option value="">-- Chọn học kỳ --</option>
                                <option value="HK1" ${score != null && score.semester == 'HK1' ? 'selected' : ''}>Học kỳ 1</option>
                                <option value="HK2" ${score != null && score.semester == 'HK2' ? 'selected' : ''}>Học kỳ 2</option>
                                <option value="HK3" ${score != null && score.semester == 'HK3' ? 'selected' : ''}>Học kỳ 3 (Hè)</option>
                            </select>
                        </div>
                        
                        <div class="form-group">
                            <label for="year">Năm học <span class="required">*</span></label>
                            <input type="number" id="year" name="year" 
                                   min="2020" max="2030"
                                   value="${score != null ? score.year : ''}" required>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="examDate">Ngày thi</label>
                        <input type="date" id="examDate" name="examDate" 
                               value="${score != null ? score.examDate : ''}">
                    </div>
                    
                    <div class="info-box">
                        <p><strong>Lưu ý:</strong></p>
                        <ul>
                            <li>Điểm trung bình = Điểm giữa kỳ × 40% + Điểm cuối kỳ × 60%</li>
                            <li>Xếp loại sẽ được tính tự động dựa trên điểm trung bình</li>
                            <li>Điểm phải nằm trong khoảng từ 0 đến 10</li>
                        </ul>
                    </div>
                    
                    <div class="form-actions">
                        <button type="submit" class="btn btn-primary">
                            ${score != null ? 'Cập nhật' : 'Nhập điểm'}
                        </button>
                        <a href="${pageContext.request.contextPath}/score?action=list" class="btn btn-secondary">
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
        function validateScoreForm() {
            const midtermScore = parseFloat(document.getElementById('midtermScore').value);
            const finalScore = parseFloat(document.getElementById('finalScore').value);
            
            if (midtermScore < 0 || midtermScore > 10) {
                alert('Điểm giữa kỳ phải nằm trong khoảng từ 0 đến 10!');
                return false;
            }
            
            if (finalScore < 0 || finalScore > 10) {
                alert('Điểm cuối kỳ phải nằm trong khoảng từ 0 đến 10!');
                return false;
            }
            
            return true;
        }
    </script>
</body>
</html>
