package assignment.server.server_application;

import java.io.PrintWriter;

public class Request {
    private Command command;
    private PrintWriter out;

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public PrintWriter getOut() {
        return out;
    }

    public void setOut(PrintWriter out) {
        this.out = out;
    }
}
