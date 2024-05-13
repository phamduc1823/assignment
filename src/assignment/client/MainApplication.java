package assignment.client;

import assignment.server.model.Student;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class MainApplication {

    public MainApplication() {
    }

    public void MainApplicationConnect() {
        ClientService clientService = new ClientService();
        Scanner scanner = new Scanner(System.in);

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
                        List<Student> list = clientService.getAll();

                        for (Student student : list) {
                            System.out.println(student);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    System.out.println(clientService.create());
                    break;
                case 3:
                    //Show
                    SendNotification data = clientService.getById();
                    System.out.println(data.getBody());
                    int id = data.getId();
                    System.out.println("------------------------------");

                    System.out.println("1. Edit student");
                    System.out.println("2. Back to menu");
                    System.out.print("Choose: ");
                    int choice = scanner.nextInt();

                    switch (choice) {
                        case 1:
                            SendNotification getData = clientService.update(id);
                            System.out.println(getData.getMessage());
                            break;
                        case 2:
                            break;
                        default:
                            System.out.println("Invalid choice");
                            break;
                    }
                    break;
                case 4:
                    System.out.println(clientService.delete());
                    break;
                default:
                    System.out.println("Invalid input. Please enter a number: ");
                    break;
            }
        } while (input != 5);
    }

    public void ClientMenu() {
        System.out.println("1. List of students");
        System.out.println("2. Create students");
        System.out.println("3. Show students");
        System.out.println("4. Delete students");
        System.out.print("Choose number: ");
    }
}
