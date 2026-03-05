<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Score - SIS</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 20px;
        }
        .form-container {
            background: white;
            border-radius: 15px;
            padding: 40px;
            box-shadow: 0 10px 30px rgba(0,0,0,0.2);
            max-width: 600px;
            width: 100%;
        }
        .form-header {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 20px;
            border-radius: 10px;
            margin-bottom: 30px;
            text-align: center;
        }
        .btn-submit {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            border: none;
            padding: 12px;
            border-radius: 8px;
            width: 100%;
            font-weight: bold;
            transition: all 0.3s;
        }
        .btn-submit:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(102, 126, 234, 0.4);
            color: white;
        }
        .btn-back {
            background: #6c757d;
            color: white;
            border: none;
            padding: 12px;
            border-radius: 8px;
            width: 100%;
            transition: all 0.3s;
        }
        .btn-back:hover {
            background: #5a6268;
            color: white;
        }
        .form-label {
            font-weight: 600;
            color: #333;
        }
        .form-control:focus, .form-select:focus {
            border-color: #667eea;
            box-shadow: 0 0 0 0.2rem rgba(102, 126, 234, 0.25);
        }
        .score-info {
            background: #f8f9fa;
            padding: 15px;
            border-radius: 8px;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <div class="form-header">
            <h2><i class="fas fa-plus-circle"></i> Add Student Score</h2>
        </div>

        <form action="${pageContext.request.contextPath}/score" method="post" onsubmit="return validateForm()">
            <input type="hidden" name="action" value="insert">
            
            <div class="mb-3">
                <label for="studentId" class="form-label">
                    <i class="fas fa-user"></i> Select Student <span class="text-danger">*</span>
                </label>
                <select class="form-select" id="studentId" name="studentId" required>
                    <option value="">-- Choose a student --</option>
                    <c:forEach var="student" items="${students}">
                        <option value="${student.studentId}">
                            ${student.studentCode} - ${student.fullName}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="mb-3">
                <label for="subjectId" class="form-label">
                    <i class="fas fa-book"></i> Select Subject <span class="text-danger">*</span>
                </label>
                <select class="form-select" id="subjectId" name="subjectId" required>
                    <option value="">-- Choose a subject --</option>
                    <c:forEach var="subject" items="${subjects}">
                        <option value="${subject.subjectId}">
                            ${subject.subjectCode} - ${subject.subjectName} (${subject.credit} credits)
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="row">
                <div class="col-md-6 mb-3">
                    <label for="score1" class="form-label">
                        <i class="fas fa-chart-line"></i> Score 1 (30%) <span class="text-danger">*</span>
                    </label>
                    <input type="number" 
                           class="form-control" 
                           id="score1" 
                           name="score1" 
                           min="0" 
                           max="10" 
                           step="0.1"
                           placeholder="0.0 - 10.0"
                           required
                           oninput="calculateGrade()">
                </div>
                <div class="col-md-6 mb-3">
                    <label for="score2" class="form-label">
                        <i class="fas fa-chart-bar"></i> Score 2 (70%) <span class="text-danger">*</span>
                    </label>
                    <input type="number" 
                           class="form-control" 
                           id="score2" 
                           name="score2" 
                           min="0" 
                           max="10" 
                           step="0.1"
                           placeholder="0.0 - 10.0"
                           required
                           oninput="calculateGrade()">
                </div>
            </div>

            <div class="score-info">
                <h6><i class="fas fa-calculator"></i> Grade Preview</h6>
                <p class="mb-2"><strong>Formula:</strong> Final Grade = 0.3 × Score 1 + 0.7 × Score 2</p>
                <p class="mb-0"><strong>Expected Grade:</strong> <span id="previewGrade" class="text-primary fw-bold">--</span></p>
            </div>

            <div class="row mt-4">
                <div class="col-md-6 mb-2">
                    <button type="submit" class="btn btn-submit">
                        <i class="fas fa-save"></i> Save Score
                    </button>
                </div>
                <div class="col-md-6 mb-2">
                    <a href="${pageContext.request.contextPath}/" class="btn btn-back">
                        <i class="fas fa-arrow-left"></i> Back to Home
                    </a>
                </div>
            </div>
        </form>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        function validateForm() {
            const studentId = document.getElementById('studentId').value;
            const subjectId = document.getElementById('subjectId').value;
            const score1 = parseFloat(document.getElementById('score1').value);
            const score2 = parseFloat(document.getElementById('score2').value);

            if (!studentId || !subjectId) {
                alert('Please select both student and subject!');
                return false;
            }

            if (isNaN(score1) || isNaN(score2)) {
                alert('Please enter valid scores!');
                return false;
            }

            if (score1 < 0 || score1 > 10 || score2 < 0 || score2 > 10) {
                alert('Scores must be between 0 and 10!');
                return false;
            }

            return true;
        }

        function calculateGrade() {
            const score1 = parseFloat(document.getElementById('score1').value) || 0;
            const score2 = parseFloat(document.getElementById('score2').value) || 0;
            const finalGrade = (0.3 * score1 + 0.7 * score2).toFixed(2);
            
            let letterGrade = '';
            let colorClass = '';
            
            if (finalGrade >= 8.0) {
                letterGrade = 'A';
                colorClass = 'text-success';
            } else if (finalGrade >= 6.0) {
                letterGrade = 'B';
                colorClass = 'text-info';
            } else if (finalGrade >= 4.0) {
                letterGrade = 'D';
                colorClass = 'text-warning';
            } else {
                letterGrade = 'F';
                colorClass = 'text-danger';
            }
            
            document.getElementById('previewGrade').innerHTML = 
                `<span class="${colorClass}">${finalGrade} (${letterGrade})</span>`;
        }
    </script>
</body>
</html>
