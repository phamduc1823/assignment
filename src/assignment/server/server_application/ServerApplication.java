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

public class ServerApplication {

  public static void main(String[] args) {
    try {
      //khoi tao queu && khoi tao list controller
      BlockingQueue<Request> queue = new ArrayBlockingQueue<>(100);
      List<Controller> controllers = new ArrayList<>();

      var studentDao = new StudentDao();
      var studentService = new StudentService(studentDao);

      //Them vao mang controller vua tao
      controllers.add(new StudentController(studentService));

      for (int i = 0; i < 3; i++) {
        Worker worker = new Worker(queue, controllers);
        worker.start();
      }
      System.out.println("Server running ...");
      ServerSocket serverSocket = new ServerSocket(9999);
      while (true) {
        Socket socket = serverSocket.accept();
        new ServerProcessor(socket, queue).start();
        System.out.println("Client " + socket.getInetAddress() + " connected!");
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
  }
}
