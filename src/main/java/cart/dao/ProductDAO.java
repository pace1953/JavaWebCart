package cart.dao;

import java.util.List;

import cart.model.entity.Product;

public interface ProductDAO {
	// 查詢全部
	List<Product> findAllProducts();
	
	// 新增
	void add(Product product);
	
	// 刪除(根據指定的productId進行刪除)
	void delete(Integer productId);
}
