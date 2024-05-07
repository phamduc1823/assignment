package assignment.client;

import assignment.server.server_application.Command;
import com.google.gson.Gson;

public class MainApplication {

  public static void main(String[] args) {
    Command command = new Command();
    command.setCommandType("/student/get_by_id");
    command.setData("1");

    //
    client.send(new Gson().toJson(command));
  }
}
