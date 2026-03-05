# Student Information System

A web-based student information management system built with Java EE, designed to manage student records, subject information, and academic scores with automatic grade calculation.

## Features

- **Student Management**: Add and manage student records with unique student codes
- **Subject Management**: Maintain course catalog with credit information
- **Score Management**: Record and track student scores across multiple subjects
- **Automatic Grade Calculation**: Weighted grade computation (30% Score1 + 70% Score2)
- **Letter Grade Conversion**: Automatic conversion to letter grades (A/B/D/F)
- **Modern UI**: Responsive web interface built with Bootstrap 5
- **Real-time Validation**: Client-side form validation with instant feedback

## Technologies

- **Backend**: Java EE (Servlet 4.0, JSP, JSTL 1.2)
- **Database**: MySQL 8.0
- **Build Tool**: Apache Maven 3.9.9
- **Frontend**: Bootstrap 5.3.0, Font Awesome 6.4.0
- **Server**: Apache Tomcat 9.0+
- **JDK**: Java 1.8+

## Prerequisites

- JDK 1.8 or higher
- Apache Maven 3.6+
- MySQL 8.0+
- Apache Tomcat 9.0+

## Installation

### 1. Database Setup

Create the MySQL database and tables:

```sql
CREATE DATABASE sis;
USE sis;

CREATE TABLE student_t (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    student_code VARCHAR(20) NOT NULL UNIQUE,
    full_name VARCHAR(100) NOT NULL,
    address VARCHAR(255)
);

CREATE TABLE subject_t (
    subject_id INT AUTO_INCREMENT PRIMARY KEY,
    subject_code VARCHAR(20) NOT NULL UNIQUE,
    subject_name VARCHAR(100) NOT NULL,
    credit INT NOT NULL
);

CREATE TABLE student_score_t (
    student_score_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    subject_id INT NOT NULL,
    score1 DECIMAL(5,2) NOT NULL,
    score2 DECIMAL(5,2) NOT NULL,
    CONSTRAINT fk_student FOREIGN KEY (student_id) REFERENCES student_t(student_id) ON DELETE CASCADE,
    CONSTRAINT fk_subject FOREIGN KEY (subject_id) REFERENCES subject_t(subject_id) ON DELETE CASCADE
);

INSERT INTO subject_t (subject_code, subject_name, credit) VALUES
('JAVA', 'Java Programming', 4),
('PHP', 'PHP Programming', 3),
('WDA', 'Web Development and Applications', 3);
```

### 2. Configuration

Update database connection settings in `DBConnection.java`:

```java
private static final String URL = "jdbc:mysql://localhost:3306/sis";
private static final String USERNAME = "root";
private static final String PASSWORD = "your_password";
```

### 3. Build the Project

```bash
mvn clean package
```

The compiled WAR file will be generated at `target/sis.war`.

### 4. Deploy

Deploy the WAR file to your Tomcat server:

```bash
cp target/sis.war $TOMCAT_HOME/webapps/
```

Or use your IDE's deployment feature.

### 5. Access the Application

Open your browser and navigate to:

```
http://localhost:8080/sis/
```

## Project Structure

```
src/
├── main/
│   ├── java/com/lec16/
│   │   ├── controller/
│   │   │   ├── HomeController.java
│   │   │   ├── StudentController.java
│   │   │   └── ScoreController.java
│   │   ├── dao/
│   │   │   ├── StudentDAO.java
│   │   │   ├── SubjectDAO.java
│   │   │   └── StudentScoreDAO.java
│   │   ├── entity/
│   │   │   ├── Student.java
│   │   │   ├── Subject.java
│   │   │   └── StudentScore.java
│   │   └── util/
│   │       ├── DBConnection.java
│   │       └── GradeConverter.java
│   └── webapp/
│       ├── WEB-INF/
│       │   └── web.xml
│       ├── index.jsp
│       ├── student-form.jsp
│       ├── score-form.jsp
│       └── error.jsp
└── test/
    └── java/
```

## API Endpoints

| Endpoint | Method | Description |
|----------|--------|-------------|
| `/` | GET | Display all student scores |
| `/student?action=add` | GET | Show add student form |
| `/student?action=insert` | POST | Insert new student |
| `/score?action=add` | GET | Show add score form |
| `/score?action=insert` | POST | Insert new score |

## Grade Calculation

The system uses a weighted average formula to calculate final grades:

```
Final Grade = (Score1 × 0.3) + (Score2 × 0.7)
```

Letter grades are assigned as follows:

| Score Range | Letter Grade | Bootstrap Color |
|-------------|--------------|-----------------|
| 8.0 - 10.0 | A | Success (Green) |
| 6.0 - 7.9 | B | Info (Blue) |
| 4.0 - 5.9 | D | Warning (Yellow) |
| 0.0 - 3.9 | F | Danger (Red) |

## Database Schema

### Entity Relationship Diagram

```
┌─────────────┐         ┌──────────────────┐         ┌─────────────┐
│  student_t  │         │ student_score_t  │         │  subject_t  │
├─────────────┤         ├──────────────────┤         ├─────────────┤
│ student_id  │←───┐    │student_score_id  │    ┌───→│ subject_id  │
│ student_code│    └────│ student_id       │    │    │ subject_code│
│ full_name   │         │ subject_id       │────┘    │ subject_name│
│ address     │         │ score1           │         │ credit      │
└─────────────┘         │ score2           │         └─────────────┘
                        └──────────────────┘
```

## Development

### Running Tests

```bash
mvn test
```

### Building for Production

```bash
mvn clean package -DskipTests
```

### Code Style

This project follows standard Java EE conventions:
- MVC architecture pattern
- DAO pattern for data access
- Servlet-based request handling
- JSP for view rendering

## Troubleshooting

### Common Issues

**Database Connection Failed**
- Verify MySQL is running: `mysql -u root -p`
- Check database credentials in `DBConnection.java`
- Ensure database `sis` exists

**Port Already in Use**
- Stop existing Tomcat instance
- Change Tomcat port in `server.xml`

**Build Failed**
- Verify Maven installation: `mvn --version`
- Clean build: `mvn clean install`
- Check Java version: `java -version`

## Contributing

1. Fork the repository
2. Create a feature branch: `git checkout -b feature-name`
3. Commit changes: `git commit -am 'Add feature'`
4. Push to branch: `git push origin feature-name`
5. Submit a pull request

## License

This project is developed for educational purposes at Hanoi University of Science and Technology.

## Contact

For questions or issues, please contact:
- **Institution**: Hanoi University of Science and Technology
- **Course**: Web Development
- **Year**: 2024

---

Built with ❤️ using Java EE and Bootstrap
