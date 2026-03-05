<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hệ Thống Quản Lý Sinh Viên</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>HỆ THỐNG QUẢN LÝ SINH VIÊN</h1>
            <p class="subtitle">Student Information System</p>
        </header>
        
        <main>
            <div class="welcome-section">
                <h2>Chào mừng đến với hệ thống quản lý sinh viên</h2>
                <p>Hệ thống giúp quản lý thông tin sinh viên và điểm số một cách hiệu quả</p>
            </div>
            
            <div class="menu-grid">
                <div class="menu-card">
                    <div class="card-icon">👨‍🎓</div>
                    <h3>Quản Lý Sinh Viên</h3>
                    <p>Thêm, sửa, xóa thông tin sinh viên</p>
                    <div class="card-actions">
                        <a href="${pageContext.request.contextPath}/student?action=list" class="btn btn-primary">
                            Danh sách sinh viên
                        </a>
                        <a href="${pageContext.request.contextPath}/student?action=add" class="btn btn-secondary">
                            Thêm sinh viên mới
                        </a>
                    </div>
                </div>
                
                <div class="menu-card">
                    <div class="card-icon">📊</div>
                    <h3>Quản Lý Điểm</h3>
                    <p>Nhập và quản lý điểm thi của sinh viên</p>
                    <div class="card-actions">
                        <a href="${pageContext.request.contextPath}/score?action=list" class="btn btn-primary">
                            Danh sách điểm
                        </a>
                        <a href="${pageContext.request.contextPath}/score?action=add" class="btn btn-secondary">
                            Nhập điểm mới
                        </a>
                    </div>
                </div>
            </div>
            
            <div class="info-section">
                <h3>Thông tin hệ thống</h3>
                <ul>
                    <li>Quản lý thông tin sinh viên toàn diện</li>
                    <li>Theo dõi điểm số và kết quả học tập</li>
                    <li>Tính toán điểm trung bình và xếp loại tự động</li>
                    <li>Tìm kiếm và lọc thông tin nhanh chóng</li>
                </ul>
            </div>
        </main>
        
        <footer>
            <p>&copy; 2026 Student Information System. All rights reserved.</p>
        </footer>
    </div>
    
    <script src="${pageContext.request.contextPath}/js/script.js"></script>
</body>
</html>
