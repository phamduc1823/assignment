package assignment.server.server_application;

import assignment.server.controller.Controller;
import assignment.server.controller.StudentController;
import assignment.server.dao.student_dao.StudentDao;
import assignment.server.service.StudentService;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ServerHandler {

  public static void main(String[] args) {
    try {
      BlockingQueue<Command> queue = new ArrayBlockingQueue<>(100);
      List<Controller> controllers = new ArrayList<>();

      var studentDao = new StudentDao();
      var studentService = new StudentService(studentDao);
      controllers.add(new StudentController(studentService));

      for (int i = 0; i < 100; i++) {
        Worker worker = new Worker(queue, controllers);
        worker.start();
      }

      ServerSocket serverSocket = new ServerSocket(2211);
      while (true) {
        Socket socket = serverSocket.accept();
        new ServerProcessor(socket, queue).start();
        System.out.println("Client " + socket.getInetAddress() + " connected!");
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
