package assignment.server.server_application;

import assignment.server.controller.Controller;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.function.Function;

public class Worker extends Thread {

  private final BlockingQueue<Command> commandsQueue;
  private final Map<String, Function<Command, Object>> functionMap;

  public Worker(BlockingQueue<Command> commandsQueue, List<Controller> controllers) {
    this.commandsQueue = commandsQueue;
    this.functionMap = new HashMap<>();
    for (Controller controller : controllers) {
      this.functionMap.putAll(controller.getMapping());
    }
  }

  @Override
  public void run() {
    while (true) {
      Command command = commandsQueue.poll();
      if (command != null) {
        var response = functionMap.get(command.getCommandType()).apply(command);
        command.getOut().println(new Gson().toJson(response));
      }
    }
  }

}