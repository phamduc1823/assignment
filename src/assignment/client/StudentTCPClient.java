package assignment.client;

import assignment.server.model.Student;
import assignment.server.application.Command;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentTCPClient extends TCPClient {

  private Gson gson;

  public StudentTCPClient(String host, int port) {
    super(host, port);
    this.gson = new Gson();
  }

  public List<Student> getList() throws IOException {
    var command = new Command();
    command.setCommandType("/student/get_all_students");
    Map<String, String> headers = new HashMap<>();
    headers.put("token", "123@123a");
    command.setHeaders(headers);

    var response = this.send(command);
    return gson.fromJson(gson.toJson(response.getBody()), new TypeToken<List<Student>>() {
    }.getType());
  }

  public void create(Student student) throws IOException {
    var command = new Command();
    command.setCommandType("/student/create");
    command.setData(gson.toJson(student));
    this.send(command);
  }
}
