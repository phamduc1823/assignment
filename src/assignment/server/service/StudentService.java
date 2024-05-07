package assignment.server.service;

import assignment.server.dao.student_dao.StudentDao;
import assignment.server.model.Student;

public class StudentService {

  private final StudentDao studentDao;

  public StudentService(StudentDao studentDao) {
    this.studentDao = studentDao;
  }

  public Student getById(Integer studentId) {
    return studentDao.getById(studentId);
  }

  public Student create(Student studentId) {
    //todo
    return null;
  }
}
