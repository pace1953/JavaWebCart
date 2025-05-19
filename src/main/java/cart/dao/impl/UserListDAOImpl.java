package cart.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cart.dao.UserListDAO;
import cart.model.entity.User;

public class UserListDAOImpl extends BaseDao implements UserListDAO{

	@Override
	public List<User> findAllUsers() {
		List<User> userList = new ArrayList<>();
		
		String sql = "SELECT id, username, email, completed FROM user";
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery()){
			
			while(rs.next()) {
				User user  = new User();

				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setEmail(rs.getString("email"));
				user.setCompleted(rs.getBoolean("completed"));
				
				userList.add(user);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}
	
}
