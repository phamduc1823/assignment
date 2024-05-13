package assignment.server.dao.student_dao;

import assignment.server.dao.DAO;
import assignment.server.dao.DataSource;
import assignment.server.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDao implements DAO<Student, Integer> {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    public DataSource dataSource;

    public StudentDao() throws Exception {
        this.dataSource = new DataSource();
    }

    @Override
    public List<Student> getAll() throws SQLException {
        List<Student> students = new ArrayList<>();
        String query = "select * from student ORDER BY id DESC LIMIT 5";

        try {
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                students.add(new Student(
                        rs.getInt("id"),
                        rs.getString("name")));
            }

            return students;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            con.close();
        }
    }

    @Override
    public Student create(Student student) {
        String query = "insert into student (name) values (?)";
        try {
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, student.getName());
            ps.executeUpdate();
            return student;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Student getById(Integer id) {
        String query = "select * from student where id = ?";

        try {
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                return new Student(
                        rs.getInt("id"),
                        rs.getString("name")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public Student update(Student student) {
        String query = "update student set name = ? where id = ?";

        try {
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, student.getName());
            ps.setInt(2, student.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public Student delete(Integer id) {
        String query = "delete from student where id = ?";
        try {
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
