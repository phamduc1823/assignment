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

    public Worker(BlockingQueue<Request> commandsQueue, List<Controller> controllers, SecurityService securityService) {
        this.commandsQueue = commandsQueue;
        this.securityService = securityService;
        this.handlers = new HashMap<>();
        this.gson = new Gson();

        for (Controller controller : controllers) {
            this.handlers.putAll(controller.getMapping());
        }
    }

    private void sendResponse(PrintWriter netOut, String statusCode, String message, Object body) {
        Response response = new Response();
        response.setStatusCode(statusCode);
        response.setMessage(message);
        response.setBody(body);
        netOut.println(gson.toJson(response));
    }

    @Override
    public void run() {
        while (true) {
            try {
                Request request = commandsQueue.poll();
                if (request != null) {
                    var socket = request.getSocket();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    var netOut = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

                    try {
                        String requestBody = reader.readLine();
                        var command = new Gson().fromJson(requestBody, Command.class);
                        request.setCommand(command);

                        //todo implement security here
                        if (command.getHeaders() == null || !securityService.checkSecurity(command.getHeaders().get("token"))) {
                            sendResponse(netOut, "403", "Forbidden", null);
                            return;
                        }

                        var handler = handlers.get(command.getCommandType());

                        if (handler != null) {
                            var body = handler.apply(command);
                            sendResponse(netOut, "200", "OK", body);
                        } else {
                            sendResponse(netOut, "403", "Forbidden", null);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        sendResponse(netOut, "500", "Internal Server Error", null);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}