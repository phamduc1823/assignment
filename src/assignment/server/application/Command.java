package assignment.server.application;


import java.util.Map;

public class Command {

  private String commandType;
  private Map<String, String> headers;
  private String data;

  public Command() {
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

  public Map<String, String> getHeaders() {
    return headers;
  }

  public void setHeaders(Map<String, String> headers) {
    this.headers = headers;
  }
}
