軟體框架 MVC
使用技術 Servlet, JSP, HttpFilter, HttpSession, SMTP
資料庫 MySQL
資料安全 雜湊 + 鹽
美化工具 Pure CSS + Google Chart
JavaWebCart 專案目錄結構
src/main/java
           └── cart
                     ├─ controller
	         ├─ dao
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
│                              └─ menu.jsp 購物車上方 Menu
└── index.html ← 從此執行 (並在頁面中點選購物車專題連結)
