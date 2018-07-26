package shop.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.dao.GeneralWarehouse;
import shop.exception.NotFoundException;
import shop.vo.Product;

import static shop.factory.WarehouseFactory.getWarehouse;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("test/html;charset=utf-8");
		
		String prodCode = request.getParameter("prodCode");
		
		Product product = new Product(prodCode);
		
		GeneralWarehouse warehouse = getWarehouse("mybatis");
		
		String view = null;
		String next = null;
		String message = null;
		
		try {
			warehouse.remove(product);
			
			message = String.format("제품 정보 [%s}가 제되었습니다.", product.getProdCode());
			
		} catch (NotFoundException e) {
			message = e.getMessage();
			
			e.printStackTrace();
		}
		
		request.setAttribute("message", message);
		
		view = "messageJsp";
		
		next = "list";
		request.setAttribute("next", next);
		
		RequestDispatcher reqd = request.getRequestDispatcher(view);
		reqd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
