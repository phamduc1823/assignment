package assignment.server.server_application;

import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

public class ServerProcessor extends Thread{
    Socket socket;
    BufferedReader in;
    PrintWriter out;
    BlockingQueue<Command> commandQueue;

    public ServerProcessor(Socket socket, BlockingQueue<Command> commandQueue) {
        try {
            this.socket = socket;
            this.commandQueue = commandQueue;
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                String input = in.readLine();
                if(input != null){
                    var command = new Gson().fromJson(input, Command.class);
                    this.commandQueue.add(command);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
