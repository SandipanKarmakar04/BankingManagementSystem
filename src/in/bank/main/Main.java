package in.bank.main;

import java.util.Scanner;

import in.bank.bankingDAO.BankingDAO;
import in.bank.bankService.BankService;


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

					while (true) {
						System.out.println("\n===== BANK MENU =====");
						System.out.println("1. Deposit");
						System.out.println("2. Withdraw");
						System.out.println("3. Generate Pin");
						System.out.println("4. Exit");
						
						System.out.print("Enter choice: ");
						int choice1 = sc.nextInt();

						switch (choice1) {

						case 1:
							System.out.print("Account No: ");
							int acc1 = sc.nextInt();

							System.out.print("Amount: ");
							double amt1 = sc.nextDouble();

							BankService bn1 = new BankService();
							bn1.deposit(acc1, amt1);
							break;

						case 2:
							System.out.print("Account No: ");
							int acc2 = sc.nextInt();

							System.out.print("PIN: ");
							int pin = sc.nextInt();

							System.out.print("Amount: ");
							double amt2 = sc.nextDouble();

							BankService bn2 = new BankService();
							bn2.withdraw(acc2, pin, amt2);
							break;

						case 3:
							System.out.print("Set PIN: ");
							int pin2 = sc.nextInt();

							dao.createAccount(userId, pin2);
							System.out.println("Account Created!");

						case 4:
							System.out.println("Logging out...");
							System.exit(0);
						}
					}

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



