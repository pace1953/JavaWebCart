package cart.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import cart.dao.UserRegisterDAO;
import cart.model.entity.User;

public class UserRegisterDAOImpl extends BaseDao implements UserRegisterDAO{

	@Override
	public int addUser(User user) {
		String sql = "INSERT INTO user(username, hash_password, hash_salt, email) values(?, ?, ?, ?)";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getHashPassword());
			pstmt.setString(3, user.getHashSalt());
			pstmt.setString(4, user.getEmail());
			
			int rowcount = pstmt.executeUpdate();  // 執行更新 //
			return rowcount; // 若rowcount = 1 表示新增一筆成功 //
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int emailConfirmOK(String username) {
		String sql = "UPDATE user SET completed = true WHERE username = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, username);
			
			int rowcount = pstmt.executeUpdate();  //執行更新 //
			return rowcount; // 若rowcount = 1 表示新增一筆成功 //
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	

}
