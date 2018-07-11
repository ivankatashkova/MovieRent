package movierent.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Component;

import movierent.model.User;

@Component
public class UserDao {
	
	private Connection connection;
	
	public UserDao() {
		connection = DBManager.getInstance().getConnection();
	}
	
	public User getUserById(long id) throws SQLException {
		User user = null;
		String sqlSelectUserById = "SELECT id,first_name,last_name,email,password,isAdmin FROM users WHERE id = ? ;";
		try(PreparedStatement ps = connection.prepareStatement(sqlSelectUserById)){
			ps.setLong(1, id);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				String firstName = result.getString("first_name");
				String lastName = result.getString("last_name");
				String email = result.getString("email");
				String password = result.getString("password");
				boolean isAdmin = result.getBoolean("isAdmin");
				
				user = new User(firstName, lastName,email, password,isAdmin);
				user.setId(id);
			}
		}
		
		return user;
	}
	
	public void register(User user) throws SQLException {
		String sqlInsertUser = "INSERT INTO users (first_name, last_name,email,password,isAdmin) VALUES(?,?,?,?,?)";
		try(PreparedStatement ps = connection.prepareStatement(sqlInsertUser,Statement.RETURN_GENERATED_KEYS)){
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getPassword());
			ps.setInt(5, 0);
			ps.executeUpdate();
		}
	}
	
	public boolean checkEmailIfExist(String email) throws SQLException {
		String sqlCheckEmail = "SELECT email,password FROM users WHERE email = ?";
		try(PreparedStatement ps = connection.prepareStatement(sqlCheckEmail)){
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			int counter = 0;
			while(rs.next()) {
				counter++;
			}
			return counter != 0;
		}
	}

	public boolean checkUser(String email, String password) throws SQLException {
		String sqlCheckUser = "SELECT id,first_name,last_name,email,password FROM users WHERE email = ?";
		try(PreparedStatement ps = connection.prepareStatement(sqlCheckUser)){
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				String pass = rs.getString("password");
				if(password.equals(pass)) {
					return true;
				}
			}
			return false;			
		}
		
		
	}
	
	public User getUserByEmail(String email) throws SQLException {
		User user = null;
		String sqlSelectUserById = "SELECT id,first_name,last_name,email,password,isAdmin,money FROM users WHERE email = ? ;";
		try(PreparedStatement ps = connection.prepareStatement(sqlSelectUserById)){
			ps.setString(1, email);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				long id = result.getLong("id");
				String firstName = result.getString("first_name");
				String lastName = result.getString("last_name");
				String mail = result.getString("email");
				String password = result.getString("password");
				boolean isAdmin = result.getBoolean("isAdmin");
				double money = result.getDouble("money");
				user = new User(id,firstName, lastName,mail, password,isAdmin);
				user.setMoney(money);
			}
		}
		
		return user;
	}
	
	public void changeUserAmount(User user) throws SQLException {
		String sqlUpdateMoney = "UPDATE users SET money = ? WHERE id = ?";
		try(PreparedStatement ps = connection.prepareStatement(sqlUpdateMoney)){
			ps.setDouble(1, user.getMoney());
			ps.setLong(2, user.getId());
			ps.executeUpdate();			
		}
	}
}
