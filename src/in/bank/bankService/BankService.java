package in.bank.bankService;

import in.bank.dbConnection.DBConnection;
import java.sql.*;

public class BankService {

	public void deposit(int accNo, double amount) {
		try {
			Connection con = DBConnection.getConnection();
			con.setAutoCommit(false);

			String query = "UPDATE accounts SET balance = balance + ? WHERE acc_no = ?";
			PreparedStatement ps = con.prepareStatement(query);

			ps.setDouble(1, amount);
			ps.setInt(2, accNo);

			ps.executeUpdate();
			con.commit();

			System.out.println("Deposit Successful!");

		} catch (Exception e) {
			System.out.println("Transaction Failed!");
		}
	}

	public void withdraw(int accNo, int pin, double amount) {
		try {
			Connection con = DBConnection.getConnection();
			con.setAutoCommit(false);

			String check = "SELECT balance FROM accounts WHERE acc_no=? AND pin=?";
			PreparedStatement ps1 = con.prepareStatement(check);
			ps1.setInt(1, accNo);
			ps1.setInt(2, pin);

			ResultSet rs = ps1.executeQuery();

			if (rs.next() && rs.getDouble("balance") >= amount) {
				String update = "UPDATE accounts SET balance = balance - ? WHERE acc_no=?";
				PreparedStatement ps2 = con.prepareStatement(update);

				ps2.setDouble(1, amount);
				ps2.setInt(2, accNo);
				ps2.executeUpdate();

				con.commit();
				System.out.println("Withdrawal Successful!");
			} else {
				con.rollback();
				System.out.println("Insufficient Balance!");
			}

		} catch (Exception e) {
			System.out.println("Transaction Failed!");
		}
	}
}