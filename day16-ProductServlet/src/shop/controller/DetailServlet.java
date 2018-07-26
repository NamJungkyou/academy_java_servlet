package shop.controller;

import static shop.factory.WarehouseFactory.getWarehouse;

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

/**
 * Servlet implementation class DetailServlet
 */
@WebServlet("/detail")
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 한글처리
				// (1) 요청 한글 처리
				request.setCharacterEncoding("utf-8");
				// (2) 응답 한글 처리
				response.setContentType("text/html;charset=utf-8");
				
				// 2. 모델 생성
				// (1) list.jsp 이름을 클릭하여 넘어온 파라미터 추출
				String prodCode = request.getParameter("prodCode");
	
				
				// (2) Product 타입으로 포장
				Product product = new Product(prodCode);
				
				// (3) DB 입력에 필요한 객체 선언
				GeneralWarehouse warehouse;
				warehouse = getWarehouse("mybatis");
				
				// DB입력 성공/실패시 발생하는 메세지 변수
				String view = null;
				// 3.(2) 메세지 출력 후 이동할 뷰 페이지
				String next = null;
				String message = null;
				try {
					// (4) select 쿼리 수행
					Product found = warehouse.get(product);
					
					// 2.(5) 조회된 상세정보를 request에 속성 추가
					request.setAttribute("product", found);
					
					// 성공했을 때
					view = "detailJsp";
					
				} catch (NotFoundException e) {
					message = String.format(e.getMessage());
					
					// 실패시에 1차 뷰
					view = "messageJsp";
					
					// 실패시 2차 뷰
					next = "list";
					request.setAttribute("next", next);
					e.printStackTrace();
					// 실패시 다시 입력화는 화면으로 자동 이동
				}
				
				// (5) 조회 성공/실패에 따른 발생 메세지를 request에 속성으로 추가
				request.setAttribute("message", message);
				
				
				RequestDispatcher reqd;
				reqd = request.getRequestDispatcher(view);
				
				reqd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
