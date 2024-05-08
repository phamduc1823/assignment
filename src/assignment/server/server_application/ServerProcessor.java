package assignment.server.server_application;

import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

public class ServerProcessor extends Thread{
    Socket socket;
    BufferedReader netIn;
    PrintWriter netOut;
    BlockingQueue<Request> commandQueue;

    public ServerProcessor(Socket socket, BlockingQueue<Request> commandQueue) {
        try {
            this.socket = socket;
            this.commandQueue = commandQueue;
            this.netIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.netOut = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                String input = netIn.readLine();
                if(input != null){
                    var command = new Gson().fromJson(input, Command.class);
                    var request = new Request();
                    request.setCommand(command);
                    request.setOut(netOut);
                    this.commandQueue.add(request);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
