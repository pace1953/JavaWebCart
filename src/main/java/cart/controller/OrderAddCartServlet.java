package cart.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import cart.model.dto.ProductDTO;
import cart.service.ProductService;
import cart.service.impl.ProductServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

// 將商品加入到購物車中 "暫存"（將所訂購的商品 "暫時存放" 在session中）
@WebServlet("/product/order/add/cart")
public class OrderAddCartServlet extends HttpServlet{
	
	private ProductService productService = new ProductServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		// 購物車
		List<ProductDTO> cart = null;
		
		// Step1 確認sessing中 有沒有購物車資料
		if(session.getAttribute("cart")== null) {
			// 如果沒有，就建立一個空的購物車
			cart = new ArrayList<>();
		}else {
			// 如果有，就建立目前正在使用中的購物車
			// session.getAttribute() 方法返回的是 Object，所以要強制轉為List<ProductDTO> 類型
			cart = (List<ProductDTO>)session.getAttribute("cart");
		}
		// 得到要購買的商品id
		Integer productId = Integer.parseInt(req.getParameter("productId"));
		
		//根據 productId 取得 ProductDTO
		Optional<ProductDTO> optProductDTO = productService.findAllProducts().stream()
											.filter(dto -> dto.getProductId().equals(productId))
											.findFirst();
		
		// 確保only有效的產品才會被添加到購物車中
		if(optProductDTO.isPresent()) {
			// 於購物車中加入一筆商品
			cart.add(optProductDTO.get());
			
			// 將 cart 資料回存到session
			session.setAttribute("cart", cart);
		}
		
		// 回到訂單主頁
		resp.sendRedirect("/JavaWebCart/product/order");
	}
}
