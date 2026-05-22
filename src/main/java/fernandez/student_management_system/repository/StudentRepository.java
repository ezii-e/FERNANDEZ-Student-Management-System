package fernandez.student_management_system.repository;

import fernandez.student_management_system.model.Student;
import fernandez.student_management_system.util.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {

    public int countAll() throws SQLException {
        String sql = "SELECT COUNT(*) AS total FROM students";

        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("total");
            }
            return 0;
        }
    }

    // ✅ FIXED: ASC ORDER (1 → 2 → 3 ...)
    public List<Student> findPage(int limit, int offset) throws SQLException {

        String sql = "SELECT id, student_number, first_name, last_name, course, year_level, email, phone " +
                "FROM students ORDER BY id ASC LIMIT ? OFFSET ?";

        List<Student> students = new ArrayList<>();

        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, limit);
            statement.setInt(2, offset);

            try (ResultSet rs = statement.executeQuery()) {

                while (rs.next()) {
                    students.add(new Student(
                            rs.getInt("id"),
                            rs.getString("student_number"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("course"),
                            rs.getInt("year_level"),
                            rs.getString("email"),
                            rs.getString("phone")
                    ));
                }
            }
        }

        return students;
    }

    // ✅ FIXED: ASC ORDER
    public List<Student> findAll() throws SQLException {

        String sql = "SELECT id, student_number, first_name, last_name, course, year_level, email, phone " +
                "FROM students ORDER BY id ASC";

        List<Student> students = new ArrayList<>();

        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                students.add(new Student(
                        rs.getInt("id"),
                        rs.getString("student_number"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("course"),
                        rs.getInt("year_level"),
                        rs.getString("email"),
                        rs.getString("phone")
                ));
            }
        }

        return students;
    }

    public void insert(Student student) throws SQLException {

        String sql = "INSERT INTO students (student_number, first_name, last_name, course, year_level, email, phone) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        executeWrite(sql, student, false);
    }

    public void update(Student student) throws SQLException {

        String sql = "UPDATE students SET student_number = ?, first_name = ?, last_name = ?, course = ?, year_level = ?, email = ?, phone = ? WHERE id = ?";

        executeWrite(sql, student, true);
    }

    public void delete(int id) throws SQLException {

        String sql = "DELETE FROM students WHERE id = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    private void executeWrite(String sql, Student student, boolean includeId) throws SQLException {

        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, student.getStudentNumber());
            statement.setString(2, student.getFirstName());
            statement.setString(3, student.getLastName());
            statement.setString(4, student.getCourse());
            statement.setInt(5, student.getYearLevel());
            statement.setString(6, student.getEmail());
            statement.setString(7, student.getPhone());

            if (includeId) {
                statement.setInt(8, student.getId());
            }

            statement.executeUpdate();
        }
    }
}
