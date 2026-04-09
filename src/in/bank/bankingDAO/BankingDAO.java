package in.bank.bankingDAO;

import in.bank.dbConnection.DBConnection;
import java.sql.*;
import org.mindrot.jbcrypt.BCrypt;

public class BankingDAO {

	public boolean register(String name, String email, String password) {
	    try {
	        Connection con = DBConnection.getConnection();
	        String query = "INSERT INTO users(name, email, password) VALUES (?, ?, ?)";
	        PreparedStatement ps = con.prepareStatement(query);

	        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

	        ps.setString(1, name);
	        ps.setString(2, email);
	        ps.setString(3, hashedPassword);

	        return ps.executeUpdate() > 0;

	    } catch (Exception e) {
	    	if (e.getMessage().contains("Duplicate")) {
	            System.out.println("Email already registered!");
	        } else {
	            e.printStackTrace();
	        }

	        return false;
	    }
	}

    public int login(String email, String password) {
        try {
            Connection con = DBConnection.getConnection();
            String query = "SELECT id, password FROM users WHERE email=?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String storedHash = rs.getString("password");

                if (BCrypt.checkpw(password, storedHash)) {
                    return rs.getInt("id");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean createAccount(int userId, int pin) {
        try {
            Connection con = DBConnection.getConnection();
            String query = "INSERT INTO accounts(user_id, balance, pin) VALUES (?, 0, ?)";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setInt(2, pin);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            return false;
        }
    }
}
