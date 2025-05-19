	package cart.dao.impl;
	
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.ArrayList;
	import java.util.List;
	
	import cart.dao.OrderDAO;
	import cart.model.entity.Order;
	import cart.model.entity.OrderItem;
	
	public class OrderDAOImpl extends BaseDao implements OrderDAO{
	
		@Override
		public Integer addOrder(Integer userId) {
	
			String sql = "INSERT INTO `order` (user_id) values(?)";
			
			// 得到MySQL新增後的 order_id資料
			Integer orderId = null;
			
			// 因為後續要取得新增後自動生成的 order_id 所以要加上 Statement.RETURN_GENERATED_KEYS 參數設定
			try(PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
				pstmt.setInt(1, userId);
				
				// 執行更新
				pstmt.executeUpdate();
				
				// 取得 order_id
				ResultSet generateKeys = pstmt.getGeneratedKeys();
				
				// 如果有得到 Key 資料
				if(generateKeys.next()) {
					
					// 取得新增後，MySQL(資料庫)自動生成的order_id
					orderId = generateKeys.getInt(1);
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
			return orderId;
		}
	
		@Override
		public void addOrderItem(Integer orderId, Integer productId, Integer quantity) {
			String sql = "INSERT INTO order_item(order_id, product_id, quantity) values(?, ?, ?)";
			
			try(PreparedStatement pstmt = conn.prepareStatement(sql)){
				pstmt.setInt(1, orderId);
				pstmt.setInt(2, productId);
				pstmt.setInt(3, quantity);
				
				pstmt.executeUpdate();
			}catch (SQLException e) {
				e.printStackTrace();
				
				// 加一個return，如果新增失敗就在這裡結束，不要再進行扣抵庫存
				return;
			}
			
			// 買完後要扣抵庫存
			sql = "UPDATE product SET qty = qty - ? WHERE product_id = ?";
			
			try(PreparedStatement pstmt = conn.prepareStatement(sql)){
				pstmt.setInt(1, quantity);
				pstmt.setInt(2, productId);
				
				pstmt.executeUpdate();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
		@Override
		public List<Order> findAllOrdersByUserId(Integer userId) {
			List<Order> orders = new ArrayList<Order>();
			String sql = "SELECT order_id, user_id, order_date FROM `order` WHERE user_id =?";
			try(PreparedStatement pstmt = conn.prepareStatement(sql)){
				pstmt.setInt(1, userId);
				
				try(ResultSet rs = pstmt.executeQuery()){
					while(rs.next()) {
						// Mapping 
						Order order = new Order();
						order.setOrderId(rs.getInt("order_id"));
						order.setUserId(rs.getInt("user_id"));
						order.setOrderDate(rs.getDate("order_date"));
						
						// 注入到orders集合中
						orders.add(order);
					}
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
			return orders;
		}
	
		@Override
		public List<OrderItem> findAllOrderItemsByOrderId(Integer orderId) {
			List<OrderItem> items = new ArrayList<>();
			String sql = "SELECT item_id, order_id, product_id, quantity FROM order_item WHERE order_id =?";
			try(PreparedStatement pstmt = conn.prepareStatement(sql)){
				pstmt.setInt(1, orderId);
				
				try(ResultSet rs = pstmt.executeQuery()){
					while(rs.next()) {
						OrderItem item = new OrderItem();
						item.setItemId(rs.getInt("item_id"));
						item.setOrderId(rs.getInt("order_id"));
						item.setProductId(rs.getInt("product_id"));
						item.setQuantity(rs.getInt("quantity"));
						
						items.add(item);
					}
				}	
			}catch (SQLException e) {
				e.printStackTrace();
			}
			return items;
		}
	
	}
