package cart.filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/user/list", "/product/*"})
public class LoginFilter extends HttpFilter{
	// 有一些頁面是為登入前看不到的，所有可以透過登入的userDTO來判斷是否登入
	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		// 根據 session 屬性，是否有 userDTO 物件來判斷是否已經登入
		HttpSession session = request.getSession();
		
		if(session.getAttribute("userDTO") == null) {
			// 做法1 重導到登入頁面
			response.sendRedirect("/JavaWebCart/user/login");
			
			// 做法2 (頁面顯示請先登入)
			// 給 result.jsp的資訊
/*			request.setAttribute("resultTitle", "請先登入");
			request.setAttribute("resultMessage", "請先登入");
			// 重導到 result.jsp
			request.getRequestDispatcher("/WEB-INF/view/cart/result.jsp/"); */
			
		}else {
			// By Pass
			chain.doFilter(request, response);
		}
	}

}
