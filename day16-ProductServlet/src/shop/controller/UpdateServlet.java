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

/**
 * 제품 1건의 수정요청을 처리하는 서블릿
 * 
 * ----GET---- 1. datail.jsp에서 [수정] 링크를 통해 들어온 요청을 처리 ==>현재 상세보기를 하고 있던 제품을 조회하여
 * 수정할 수 있는 update.jsp로 전달 ==>수정을 위한 화면이동(이미 있는 화면 요청)
 * 
 * ----POST---- 2. update.jsp에서 수정된 내용을 [저장] 버튼을 통해 들어온 요청을 처리 ==> 변경된 입력 내용을 실제
 * update 쿼리를 수행하여 DB에 영구 반영 ==> ==> 2차 뷰를 선택
 * 
 * @author Byun
 *
 */
@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * detail.jsp에서 update?prodCode==xxx로 발생한 GET요청을 처리 1. 전달된 요청 파라미터(prodCode)를 추출
	 * 2. 해당 제품 정보 조회 3. 조회된 정보를 request에 추가 4. 수정 가능한 뷰를 선택 후 화면 이동
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		 0. 한글 처리 (요청, 응답) : 여기서는 필수는 아님
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

//		 1. 모델 생성
//		 (1) 전달된 요청 파라미터(prodCode)를 추출
		String prodCode = request.getParameter("prodCode");
//		 (2) prodCode로 Product 포장 
		Product product = new Product(prodCode);
//		 (3) DB조회에 사용할 객체 준비
		GeneralWarehouse warehouse = getWarehouse("mybatis");

		String view = null;
		String next = null;
		String message = null;

		try {
//		 2. 해당 제품 정보 조회
			Product found = warehouse.get(product);

//		 3. 조회된 정보를 request에 추가
			request.setAttribute("product", found);

			view = "updateJsp";
		} catch (NotFoundException e) {
			message = e.getMessage();
			request.setAttribute("message", message);

			view = "messageJsp";
//		3.(3)
			next = "listJsp";
			request.setAttribute("next", next);
			e.printStackTrace();
		}
		// 결정된 뷰로 화면 이동
		RequestDispatcher reqd;
		reqd = request.getRequestDispatcher(view);
		
		reqd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String prodCode = request.getParameter("prodCode");
		String prodName = request.getParameter("prodName");
		int price = Integer.parseInt(request.getParameter("price"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		
		Product product = new Product(prodCode, prodName, price, quantity);
		
		GeneralWarehouse warehouse = getWarehouse("mybatis");
		
		String view = null;
		String next = null;
		String message = null;
		
		try {
			warehouse.set(product);
			
			message = String.format("제품 정보[%s] 수정을 성공했습니다.", product.getProdCode());
			
			next = "detail?prodCode="+prodCode;
			
		} catch (NotFoundException e) {
			message = e.getMessage();
			
			next = "list";
			
			e.printStackTrace();
		}
		
		request.setAttribute("message", message);
		
		view = "messageJsp";
		
		request.setAttribute("next", next);
		
		RequestDispatcher reqd;
		reqd = request.getRequestDispatcher(view);
		reqd.forward(request, response);
		
	}

}
