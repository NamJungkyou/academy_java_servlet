package shop.controller;

import static shop.factory.WarehouseFactory.getWarehouse;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.dao.GeneralWarehouse;
import shop.vo.Product;
/**
 * Servlet implementation class ListServlet
 */
@WebServlet("/list")
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 한글 설정
		//	(1) 요청 설정
		request.setCharacterEncoding("utf-8");
		
		// 2.
		GeneralWarehouse warehouse = getWarehouse("mybatis");
		// 3. 
		List<Product> products = warehouse.getAllProducts();
		request.setAttribute("products", products);
		// 4. 
		String view = "listJsp";
		RequestDispatcher reqd;
		reqd = request.getRequestDispatcher(view);
		
		reqd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
