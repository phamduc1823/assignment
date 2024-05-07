package assignment.server.server_handler;

import java.util.concurrent.BlockingQueue;

public class Worker extends Thread{
    BlockingQueue<Command> commandsQueue;
    StudentController studentController;
    public Worker(BlockingQueue<Command> commandsQueue) {

        this.commandsQueue = commandsQueue;
    }

    @Override
    public void run() {
        while (true) {
            Command command = commandsQueue.poll();
            if (command != null) {
                //tra ve client
                studentController.getHandler(command.getCommandType()).handle(command);
            }
        }
    }
}