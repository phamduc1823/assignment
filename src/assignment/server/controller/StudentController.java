package assignment.server.controller;

import assignment.server.model.Student;
import assignment.server.server_application.Command;
import assignment.server.service.StudentService;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class StudentController implements Controller {

  private final StudentService studentService;
  public final Map<String, Function<Command, Object>> functionMap = new HashMap<>();

  public StudentController(StudentService studentService) {
    this.studentService = studentService;
    functionMap.put("/student/get_all_students", command -> getAll());
    functionMap.put("/student/get_by_id", command -> getById(Integer.parseInt(command.getData())));
    functionMap.put("/student/create",
        command -> create(new Gson().fromJson(command.getData(), Student.class)));
  }

  public List<Student> getAll(){
    return studentService.getAllStudents();
  };

  public Student getById(Integer studentId) {
    return studentService.getById(studentId);
  }

  public Student create(Student studentId) {
    return studentService.create(studentId);
  }

  @Override
  public Map<String, Function<Command, Object>> getMapping() {
    return functionMap;
  }
}
