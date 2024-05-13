package assignment.client;

import assignment.server.application.Response;
import assignment.server.model.Student;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ClientService {
    StudentTCPClient studentClient;
    Scanner scanner;
    Student student;

    public ClientService() {
        this.studentClient = new StudentTCPClient("localhost", 9999);
        this.scanner = new Scanner(System.in);
        this.student = new Student();
    }

    public List<Student> getAll() throws IOException {
        List<Student> listStudent = studentClient.getList();
        return listStudent;
    }

    public SendNotification create(){
        System.out.print("Moi nhap ten: ");
        student.setName(scanner.next());

        SendNotification notification = null;
        try {
            notification = studentClient.create(student);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return notification;
    }

    public SendNotification getById() {
        System.out.print("Moi nhap id: ");
        int id = scanner.nextInt();
        student.setId(id);
        try {
           var sendNotification = studentClient.getById(id);
           return sendNotification;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public SendNotification update(int id) {
        student.setId(id);
        System.out.print("Moi nhap ten: ");
        student.setName(scanner.next());

        try {
            var sendNotification = studentClient.update(student);
            return sendNotification;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public SendNotification delete() {
        try {
            System.out.print("Enter student id: ");
            int studentId = scanner.nextInt();

            var sendNotification = studentClient.delete(studentId);
            return sendNotification;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
