package in.bank.main;

import java.util.Scanner;

import in.bank.bankingDAO.BankingDAO;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        BankingDAO dao = new BankingDAO();

        while (true) {
        	System.out.println("=================================");
        	System.out.println("   BANKING MANAGEMENT SYSTEM");
        	System.out.println("=================================");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            
            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {

                case 1:
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Email: ");
                    String email = sc.nextLine();

                    System.out.print("Password: ");
                    String pass = sc.nextLine();

                    if (dao.register(name, email, pass))
                        System.out.println("Registered Successfully!");
                    else
                        System.out.println("Error!");

                    break;

                case 2:
                    System.out.print("Email: ");
                    email = sc.next();

                    System.out.print("Password: ");
                    pass = sc.next();

                    int userId = dao.login(email, pass);

                    if (userId != -1) {
                        System.out.println("Login Successful!");

                        System.out.print("Set PIN: ");
                        int pin = sc.nextInt();

                        dao.createAccount(userId, pin);
                        System.out.println("Account Created!");

                    } else {
                        System.out.println("Invalid Credentials!");
                    }
                    break;

                case 3:
                    System.exit(0);
            }
        }
    }
}
