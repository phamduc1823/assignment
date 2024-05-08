package assignment.client;

import assignment.server.model.Student;
import java.io.*;
import java.util.List;
import java.util.Scanner;

public class MainApplication {

  public MainApplication() {
  }

  public void MainApplicationConnect() {
    Scanner scanner = new Scanner(System.in);
    StudentTCPClient studentClient = new StudentTCPClient("localhost", 9999);

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
            List<Student> listStudent = studentClient.getList();
            System.out.println(listStudent);
          } catch (Exception e) {
            e.printStackTrace();
          }
          break;
        case 2:
          Student std = new Student();
          System.out.println("nhap di:");
          std.setId(scanner.nextInt());
          try {
            studentClient.create(std);
          } catch (Exception e) {
            e.printStackTrace();
          }
          break;
        default:
          System.out.println("Invalid input. Please enter a number: ");
          break;
      }
    } while (input != 2);
  }

  public void ClientMenu() {
    System.out.println("1. List of students");
    System.out.print("Choose number: ");
  }
}
