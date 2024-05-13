package assignment.server.application;

import java.net.Socket;

public class Request {

  private Command command;

  private Socket socket;

  public Command getCommand() {
    return command;
  }

  public void setCommand(Command command) {
    this.command = command;
  }

  public Socket getSocket() {
    return socket;
  }

  public void setSocket(Socket socket) {
    this.socket = socket;
  }
}
