package items;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ItemsCrud;

/**
 * Servlet implementation class CartListServlet
 */
@WebServlet("/cartList.do")
public class CartListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); //세션객체를 생성한다. 왜? 장바구니가 세션에 있으므로
		Cart cart = (Cart)session.getAttribute("CART"); //세션에서 장바구니를 찾는다
		if(cart == null) { //장바구니가 없는 경우
			request.setAttribute("CARTLIST", null); //HttpServletRequest에 null을 저장한다.
		} else { //장바구니가 있는 경우
			ArrayList<String> codeList = cart.getCodeList(); 
			//장바구니에서 상품번호가 저장된 ArrayList를 찾는다.
			ArrayList<Integer> numList = cart.getNumList(); 
			//장바구니에서 상품갯수가 저장된 ArryList를 찾는다.
			int total = 0; //장바구니에 저장된 상품의 총액 계산을 위한 변수 선언
			ItemsCrud dao = new ItemsCrud();
			ArrayList<Items> itemsList = new ArrayList<Items>(); //장바구니의 상품을 위한 ArrayList
			for(int i=0; i < codeList.size(); i++) { //장바구니에 담긴 모든 상품을 차례로 반복
				String code = codeList.get(i); //i번째 상품의 번호를 찾는다.
				Items myItem = dao.getItem(code);
				//상품번호로 상품정보를 찾는다.
				Integer num = numList.get(i); //i번째 상품의 갯수를 찾는다.
				myItem.setNum(num); //상품의 갯수를 DTO에 저장
				int sum = num * myItem.getPrice(); //갯수 * 상품의 가격 -> 소계를 계산
				myItem.setSum(sum); //소계를 DTO에 저장
				total = total + sum; //상품의 가격을 누적한다.
				itemsList.add(myItem);
			}
			request.setAttribute("TOTAL", total); //총계를 HttpServletRequest에 저장한다.
			request.setAttribute("CARTLIST", itemsList); //장바구니의 상품목록을 저장한다.
		}
		RequestDispatcher r = request.getRequestDispatcher("index.jsp?BODY=cartList.jsp");
		r.forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
