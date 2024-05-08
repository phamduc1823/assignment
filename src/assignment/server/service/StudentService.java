package assignment.server.service;

import assignment.server.dao.student_dao.StudentDao;
import assignment.server.model.Student;

import java.sql.SQLException;
import java.util.List;

public class StudentService {

  private final StudentDao studentDao;

  public StudentService(StudentDao studentDao) {
    this.studentDao = studentDao;
  }

  public List<Student> getAllStudents() {
      try {
          return studentDao.getAll();
      } catch (SQLException e) {
          throw new RuntimeException(e);
      }
  }

  public Student getById(Integer studentId) {
    return studentDao.getById(studentId);
  }

  public Student create(Student studentId) {
    //todo
    return null;
  }
}
