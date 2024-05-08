package assignment.server.application;

import assignment.server.controller.Controller;
import assignment.server.controller.StudentController;
import assignment.server.dao.student_dao.StudentDao;
import assignment.server.security.SecurityService;
import assignment.server.service.StudentService;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerApplication {

  public static void main(String[] args) {
    try {
      ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();
      //khoi tao queu && khoi tao list controller
      BlockingQueue<Request> queue = new ArrayBlockingQueue<>(100);
      List<Controller> controllers = new ArrayList<>();

      var studentDao = new StudentDao();
      var studentService = new StudentService(studentDao);

      //Them vao mang controller vua tao
      controllers.add(new StudentController(studentService));

      var securityService = new SecurityService();

      for (int i = 0; i < 3; i++) {
        Worker worker = new Worker(queue, controllers, securityService);
        worker.start();
      }
      System.out.println("Server running ...");
      ServerSocket serverSocket = new ServerSocket(9999);
      while (true) {
        Socket socket = serverSocket.accept();
        var request = new Request();
        request.setSocket(socket);
        queue.offer(request);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
