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
        Student student = new Student();
        student.setId(1);
        return List.of(student);
//        List<Student> students = new ArrayList<>();
//        String query = "select * from student";
//
//        try {
//            con = dataSource.getConnection();
//            ps = con.prepareStatement(query);
//            rs = ps.executeQuery();
//
//            while (rs.next()) {
//                students.add(new Student(
//                        rs.getInt("id"),
//                        rs.getString("name")));
//            }
//
//            return students;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }finally {
//            con.close();
//        }
    }

    @Override
    public Student create(Student entity) {
        return null;
    }

    @Override
    public Student getById(Integer id) {
        return null;
    }

    @Override
    public Student update(Student entity) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
