軟體框架 MVC
使用技術 Servlet, JSP, HttpFilter, HttpSession, SMTP
資料庫 MySQL
資料安全 雜湊 + 鹽
美化工具 Pure CSS + Google Chart
JavaWebCart 專案目錄結構
src/main/java
           └── cart
                ├─ controller ─ UserRegisterServlet.java (會員註冊)
			      ├─ UserListServlet.java (會員列表)
			      ├─ AuthCodeServlet.java (認證碼)
			      ├─ UserLoginServlet.java (登入)
			      └─ UserLogoutServlet.java (登出)
		 ├─ service
   		 │      ├ impl
      		 │      │  └─ UserRegisterServiceImpl.java (實作類, 實現功能)
	 	 │      └─  UserRegisterService.java (介面類 interface, 定義功能)
	         ├─ dao
	  	 │   ├ impl
     		 │   │  └─ UserRegisterDAOImpl.java (實作類, 實現功能)
		 │    └─  UserRegisterDAO.java (介面類 interface, 定義功能)
   		 │
	         ├─ exception
	         ├─ filter
	         ├─ model
                     │     ├─ dto 
                     │     └─ entity
                     ├─ service
                     └─ util ← 一些工具小程式(例如: HashUtil)
                     

src/main/webapp
├── WEB-INF
│        └── view
│                      └─ cart
│                              ├─ index.jsp 購物車主頁
			       ├─ user_list.jsp 會員註冊頁面
│                              └─ menu.jsp 購物車上方 Menu
└── index.html ← 從此執行 (並在頁面中點選購物車專題連結)
