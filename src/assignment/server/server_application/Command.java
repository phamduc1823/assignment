package assignment.server.server_application;

import java.io.PrintWriter;

public class Command {
    String commandType;
    String data;
    PrintWriter out;

    public Command() {
    }

    public PrintWriter getOut() {
        return out;
    }

    public void setOut(PrintWriter out) {
        this.out = out;
    }

    public Command(String commandType, String data) {
        this.commandType = commandType;
        this.data = data;
    }

    public String getCommandType() {
        return commandType;
    }

    public void setCommandType(String commandType) {
        this.commandType = commandType;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
