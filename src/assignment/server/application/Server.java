package assignment.server.application;

import assignment.server.controller.Controller;
import assignment.server.controller.StudentController;
import assignment.server.dao.student_dao.StudentDao;
import assignment.server.security.SecurityService;
import assignment.server.service.StudentService;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Server {

    public static void main(String[] args) {
        try {
            BlockingQueue<Request> queue = new ArrayBlockingQueue<>(10);
            List<Controller> controllers = new ArrayList<>();

            var studentDao = new StudentDao();
            var studentService = new StudentService(studentDao);

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
                System.out.println("Client " + socket.getInetAddress() + " connected!");
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
