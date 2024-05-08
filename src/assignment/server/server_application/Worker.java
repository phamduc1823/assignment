package assignment.server.server_application;

import assignment.server.controller.Controller;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.function.Function;

public class Worker extends Thread {

    private final BlockingQueue<Request> commandsQueue;
    private final Map<String, Function<Command, Object>> functionMap;

    public Worker(BlockingQueue<Request> commandsQueue, List<Controller> controllers) {
        this.commandsQueue = commandsQueue;
        this.functionMap = new HashMap<>();
        for (Controller controller : controllers) {
            this.functionMap.putAll(controller.getMapping());
        }
    }

    @Override
    public void run() {
        while (true) {
            Request request = commandsQueue.poll();

            if (request != null) {
                var command = request.getCommand();
                if (command != null) {
                    var response = functionMap.get(command.getCommandType()).apply(command);
                    request.getOut().println(new Gson().toJson(response));
                    request.getOut().flush();
                }
            }

        }
    }

}