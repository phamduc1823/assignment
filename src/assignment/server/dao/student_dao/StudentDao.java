package assignment.server.dao.student_dao;

import assignment.server.dao.DAO;
import assignment.server.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDao implements DAO<Student, Integer> {

  @Override
  public Student create(Student entity) {
    return null;
  }

  @Override
  public Student update(Student entity) {
    return null;
  }

  @Override
  public Student getById(Integer id) {
    return null;
  }

  @Override
  public void delete(Integer id) {

  }

  @Override
  public List<Student> getAll() {
    return List.of();
  }
}
