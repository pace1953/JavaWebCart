package cart.service;

import java.util.List;

import cart.model.dto.ProductDTO;
import cart.model.entity.Product;

public interface ProductService {
	// 查詢全部
	List<ProductDTO> findAllProducts();
	
	// 新增
	void add(String productName, String price, String qty, String productImageBase64);
	
	// 刪除(根據指定的productId進行刪除)
	void delete(Integer productId);
}
