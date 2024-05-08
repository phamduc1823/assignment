package assignment.client;

import assignment.server.model.Student;
import assignment.server.server_application.Command;
import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class MainApplication {
    Socket socket;
    BufferedReader netIn;
    PrintWriter out;
    BufferedReader userIn;
    public Command command;
    public Scanner scanner;

    public MainApplication() {
        try {
            socket = new Socket("localhost", 9999);
            netIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            userIn = new BufferedReader(new InputStreamReader(System.in));
            scanner = new Scanner(System.in);
            command = new Command();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void MainApplicationConnect() {
        int input;
        do {
            ClientMenu();

            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Please enter a number: ");
                scanner.next();
            }

            input = scanner.nextInt();

            switch (input) {
                case 1:

                    try {
                        command.setCommandType("/student/get_all_students");
                        out.println(new Gson().toJson(command));

                        String response = netIn.readLine();
                        System.out.println("Server response: " + response);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                default:
                    System.out.println("Invalid input. Please enter a number: ");
                    break;
            }
        }while (input != 2);
    }

    public void ClientMenu() {
        System.out.println("1. List of students");
        System.out.print("Choose number: ");
    }
}
