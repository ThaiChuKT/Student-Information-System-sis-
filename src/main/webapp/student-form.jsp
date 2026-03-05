<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Student - SIS</title>
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
        .form-control:focus {
            border-color: #667eea;
            box-shadow: 0 0 0 0.2rem rgba(102, 126, 234, 0.25);
        }
    </style>
</head>
<body>
    <div class="form-container">
        <div class="form-header">
            <h2><i class="fas fa-user-plus"></i> Add New Student</h2>
        </div>

        <form action="${pageContext.request.contextPath}/student" method="post" onsubmit="return validateForm()">
            <input type="hidden" name="action" value="insert">
            
            <div class="mb-3">
                <label for="studentCode" class="form-label">
                    <i class="fas fa-id-card"></i> Student Code <span class="text-danger">*</span>
                </label>
                <input type="text" 
                       class="form-control" 
                       id="studentCode" 
                       name="studentCode" 
                       placeholder="e.g., 2007A10"
                       required>
                <div class="form-text">Enter a unique student code (e.g., 2007A10)</div>
            </div>

            <div class="mb-3">
                <label for="fullName" class="form-label">
                    <i class="fas fa-user"></i> Full Name <span class="text-danger">*</span>
                </label>
                <input type="text" 
                       class="form-control" 
                       id="fullName" 
                       name="fullName" 
                       placeholder="Enter full name"
                       required>
            </div>

            <div class="mb-4">
                <label for="address" class="form-label">
                    <i class="fas fa-map-marker-alt"></i> Address <span class="text-danger">*</span>
                </label>
                <textarea class="form-control" 
                          id="address" 
                          name="address" 
                          rows="3" 
                          placeholder="Enter address"
                          required></textarea>
            </div>

            <div class="row">
                <div class="col-md-6 mb-2">
                    <button type="submit" class="btn btn-submit">
                        <i class="fas fa-save"></i> Save Student
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
            const studentCode = document.getElementById('studentCode').value.trim();
            const fullName = document.getElementById('fullName').value.trim();
            const address = document.getElementById('address').value.trim();

            if (!studentCode || !fullName || !address) {
                alert('Please fill in all required fields!');
                return false;
            }

            return true;
        }
    </script>
</body>
</html>
