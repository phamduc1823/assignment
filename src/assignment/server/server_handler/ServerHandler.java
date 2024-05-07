package assignment.server.server_handler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ServerHandler {
    public static void main(String[] args) {
        try {
            BlockingQueue<Command> queue = new ArrayBlockingQueue<>(100);

            for (int i = 0; i < 100; i++) {
                Worker worker = new Worker(queue);
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
