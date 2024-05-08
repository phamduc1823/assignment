package assignment.server.application;

//public class ServerProcessor extends Thread {
//
//  Socket socket;
//  BufferedReader netIn;
//  PrintWriter netOut;
//  BlockingQueue<Request> commandQueue;
//
//  public ServerProcessor(Socket socket, BlockingQueue<Request> commandQueue) {
//    try {
//      this.socket = socket;
//      this.commandQueue = commandQueue;
//      this.netIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//      this.netOut = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
//    } catch (IOException e) {
//      throw new RuntimeException(e);
//    }
//  }
//
//  @Override
//  public void run() {
//    try {
//      String input = netIn.readLine();
//      if (input != null) {
//        var command = new Gson().fromJson(input, Command.class);
//        var request = new Request();
//        request.setCommand(command);
//        request.setOut(netOut);
//        this.commandQueue.add(request);
//      }
//    } catch (IOException e) {
//      throw new RuntimeException(e);
//    }
//  }
//}
