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
    private Command command;

    public StudentTCPClient(String host, int port) {
        super(host, port);
        this.gson = new Gson();
        this.command = new Command();
    }

    public List<Student> getList() throws IOException {
        command.setCommandType("/student/get_all_students");
        Map<String, String> headers = new HashMap<>();

        headers.put("token", "123@123a");
        command.setHeaders(headers);

        var response = this.send(command);
        return gson.fromJson(gson.toJson(response.getBody()), new TypeToken<List<Student>>() {
        }.getType());
    }

    public SendNotification create(Student student) throws IOException {
        Map<String, String> headers = new HashMap<>();
        headers.put("token", "123@123a");

        command.setCommandType("/student/create");
        command.setData(gson.toJson(student));
        command.setHeaders(headers);

        var response = this.send(command);
        return new SendNotification(
                response.getMessage(),
                gson.fromJson(gson.toJson(response.getBody()), Student.class),
                response.getStatusCode());
    }

    public SendNotification getById(int id) throws IOException {
        Map<String, String> headers = new HashMap<>();
        headers.put("token", "123@123a");

        command.setCommandType("/student/get_by_id");
        command.setData(gson.toJson(id));
        command.setHeaders(headers);
        var response = this.send(command);

        SendNotification sendNotification = new SendNotification(response.getMessage(),
                gson.fromJson(gson.toJson(response.getBody()), Student.class),
                response.getStatusCode());
        sendNotification.setId(id);

        return sendNotification;
    }

    public SendNotification update(Student student) throws IOException {
        Map<String, String> headers = new HashMap<>();

        headers.put("token", "123@123a");
        command.setCommandType("/student/update");
        command.setData(gson.toJson(student));
        command.setHeaders(headers);

        var response = this.send(command);

        return new SendNotification(
                response.getMessage(),
                gson.fromJson(gson.toJson(response.getBody()), Student.class),
                response.getStatusCode());
    }

    public SendNotification delete(int id) throws IOException {
        Map<String, String> headers = new HashMap<>();
        headers.put("token", "123@123a");

        command.setCommandType("/student/delete");
        command.setData(gson.toJson(id));
        command.setHeaders(headers);
        var response = this.send(command);

        return new SendNotification(
                response.getMessage(),
                gson.fromJson(gson.toJson(response.getBody()), Student.class),
                response.getStatusCode());
    }
}
