package assignment.server.application;

import assignment.server.controller.Controller;
import assignment.server.security.SecurityService;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.function.Function;

public class Worker extends Thread {

  private final Gson gson;
  private final BlockingQueue<Request> commandsQueue;
  private final Map<String, Function<Command, Object>> handlers;
  private final SecurityService securityService;

  public Worker(BlockingQueue<Request> commandsQueue, List<Controller> controllers,
      SecurityService securityService) {
    this.commandsQueue = commandsQueue;
    this.securityService = securityService;
    this.handlers = new HashMap<>();
    this.gson = new Gson();
    for (Controller controller : controllers) {
      this.handlers.putAll(controller.getMapping());
    }
  }

  @Override
  public void run() {
    while (true) {
      try {
        Request request = commandsQueue.poll();
        if (request != null) {
          var socket = request.getSocket();
          BufferedReader reader = new BufferedReader(
              new InputStreamReader(socket.getInputStream()));
          var netOut = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
          try {
            String requestBody = reader.readLine();
            var command = new Gson().fromJson(requestBody, Command.class);
            request.setCommand(command);

            //todo implement security here
            if (command.getHeaders() == null || !securityService.checkSecurity(
                command.getHeaders().get("token"))) {
              var response = new Response();
              response.setStatusCode("403");
              response.setMessage("Forbidden");
              netOut.println(gson.toJson(response));
              return;
            }

            var handler = handlers.get(command.getCommandType());
            if (handler != null) {
              var body = handler.apply(command);
              var response = new Response();
              response.setStatusCode("200");
              response.setMessage("OK");
              response.setBody(body);
              netOut.println(gson.toJson(response));
            } else {
              var response = new Response();
              response.setStatusCode("404");
              response.setMessage("Not found");
              netOut.println(gson.toJson(response));
            }
          } catch (Exception e) {
            e.printStackTrace();
            var response = new Response();
            response.setStatusCode("500");
            response.setMessage("Server Internal Error: " + e.getMessage());
            netOut.println(gson.toJson(response));
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

}