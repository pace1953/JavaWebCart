package cart.controller;

import java.io.IOException;

import cart.service.UserRegisterService;
import cart.service.impl.UserRegisterServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 要接收使用者於信件中所按下的確認連結
@WebServlet("/email/confirm")
public class EmailConfirmServlet extends HttpServlet{
	private UserRegisterService userRegisterService = new UserRegisterServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 得到username //
		String username = req.getParameter("username");
		
		// 驗證email (！！重要！！ 這會修改user資料表中 completed 欄位的資訊) //
		userRegisterService.emailConfirmOK(username);
		
		// 準備要給result.jsp 的資料 //
		String resultTitle = "Email驗證結果";
		String resultMessage = "用戶名稱: "+ username + "<p />Email驗證成功";
		req.setAttribute("resultTitle", resultTitle);
		req.setAttribute("resultMessage", resultMessage);
		
		// 重導到result.jsp //
		req.getRequestDispatcher("/WEB-INF/view/cart/result.jsp").forward(req, resp);
	}

}
