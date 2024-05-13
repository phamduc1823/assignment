package assignment.client;

import assignment.server.application.Command;
import assignment.server.application.Response;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TCPClient {

  private String host;
  private int port;
  private Socket socket;
  private Gson gson;

  public TCPClient(String host, int port) {
    this.gson = new Gson();
    this.host = host;
    this.port = port;
  }

  public Response send(Command command) throws IOException {
    try {
      socket = new Socket();
      this.socket.connect(new InetSocketAddress(host, port));

      PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
      BufferedReader netIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));

      out.println(gson.toJson(command));

      String bodyResponse = netIn.readLine();

      var response = gson.fromJson(bodyResponse, Response.class);

      if (!response.getStatusCode().equals("200")) {
        throw new RuntimeException(response.getMessage());
      }

      return response;
    } finally {
      if (socket != null) {
        this.socket.close();
      }
    }
  }
}
