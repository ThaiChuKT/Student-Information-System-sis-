<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Information System</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            padding: 20px 0;
        }
        .container {
            background: white;
            border-radius: 15px;
            padding: 30px;
            box-shadow: 0 10px 30px rgba(0,0,0,0.2);
        }
        .header {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 20px;
            border-radius: 10px;
            margin-bottom: 30px;
        }
        .btn-custom {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 8px;
            transition: all 0.3s;
        }
        .btn-custom:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(102, 126, 234, 0.4);
            color: white;
        }
        .table {
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        .badge-grade {
            font-size: 1rem;
            padding: 8px 15px;
        }
        .action-buttons {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <!-- Header -->
        <div class="header text-center">
            <h1><i class="fas fa-graduation-cap"></i> Student Information System</h1>
            <p class="mb-0">Hanoi University of Science and Technology - 2006</p>
        </div>

        <!-- Alert Messages -->
        <c:if test="${not empty sessionScope.message}">
            <div class="alert alert-${sessionScope.messageType} alert-dismissible fade show" role="alert">
                ${sessionScope.message}
                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
            </div>
            <c:remove var="message" scope="session"/>
            <c:remove var="messageType" scope="session"/>
        </c:if>

        <!-- Action Buttons -->
        <div class="action-buttons row">
            <div class="col-md-6 mb-2">
                <a href="${pageContext.request.contextPath}/student?action=add" class="btn btn-custom w-100">
                    <i class="fas fa-user-plus"></i> + Student
                </a>
            </div>
            <div class="col-md-6 mb-2">
                <a href="${pageContext.request.contextPath}/score?action=add" class="btn btn-custom w-100">
                    <i class="fas fa-plus-circle"></i> + Score
                </a>
            </div>
        </div>

        <!-- Student Scores Table -->
        <h3 class="mb-3"><i class="fas fa-table"></i> Student Scores</h3>
        
        <c:choose>
            <c:when test="${empty scores}">
                <div class="alert alert-info">
                    <i class="fas fa-info-circle"></i> No student scores available. Please add students and scores.
                </div>
            </c:when>
            <c:otherwise>
                <div class="table-responsive">
                    <table class="table table-striped table-hover">
                        <thead class="table-primary">
                            <tr>
                                <th>#</th>
                                <th>Student Code</th>
                                <th>Student Name</th>
                                <th>Subject Code</th>
                                <th>Subject Name</th>
                                <th>Credit</th>
                                <th>Score 1</th>
                                <th>Score 2</th>
                                <th>Final Grade</th>
                                <th>Letter Grade</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="score" items="${scores}" varStatus="status">
                                <c:set var="finalGrade" value="${score.calculateGrade()}" />
                                <c:set var="letterGrade" value="${com.lec16.util.GradeConverter.convertToGrade(finalGrade)}" />
                                <c:set var="gradeColor" value="${com.lec16.util.GradeConverter.getGradeColor(letterGrade)}" />
                                <tr>
                                    <td>${status.count}</td>
                                    <td><strong>${score.studentCode}</strong></td>
                                    <td>${score.studentName}</td>
                                    <td><span class="badge bg-info">${score.subjectCode}</span></td>
                                    <td>${score.subjectName}</td>
                                    <td class="text-center">${score.credit}</td>
                                    <td class="text-center"><fmt:formatNumber value="${score.score1}" pattern="0.00"/></td>
                                    <td class="text-center"><fmt:formatNumber value="${score.score2}" pattern="0.00"/></td>
                                    <td class="text-center"><strong><fmt:formatNumber value="${finalGrade}" pattern="0.00"/></strong></td>
                                    <td class="text-center">
                                        <span class="badge bg-${gradeColor} badge-grade">${letterGrade}</span>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:otherwise>
        </c:choose>

        <!-- Grade Legend -->
        <div class="mt-4 p-3 bg-light rounded">
            <h5><i class="fas fa-info-circle"></i> Grading System</h5>
            <div class="row">
                <div class="col-md-6">
                    <p><strong>Grade Calculation:</strong> Final Grade = 0.3 × Score 1 + 0.7 × Score 2</p>
                </div>
                <div class="col-md-6">
                    <p><strong>Letter Grades:</strong></p>
                    <span class="badge bg-success me-2">A: 8.0-10</span>
                    <span class="badge bg-info me-2">B: 6.0-7.9</span>
                    <span class="badge bg-warning me-2">D: 4.0-5.9</span>
                    <span class="badge bg-danger">F: < 4.0</span>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
