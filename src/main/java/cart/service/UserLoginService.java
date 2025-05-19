package cart.service;

import cart.model.dto.UserDTO;

public interface UserLoginService {

	// 驗證是否登入成功
	// username, password, authcode 為登入時使用者自行輸入的三個必填資訊
	// sessionAuthCode 為目前存在的session驗證碼
	UserDTO login(String username, String password, String authCode, String sessionAuthCode);
	
}
