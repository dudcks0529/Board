package items;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CartModifyServlet
 */
@WebServlet("/cartModify.do")
public class CartModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		String code = request.getParameter("CODE");
		String button = request.getParameter("BTN");
		HttpSession session = request.getSession();
		Cart cart = (Cart)session.getAttribute("CART"); //Cart를 세션에서 찾는다.
		if(button.equals("삭제")) {
			cart.deleteItem(code); //장바구니의 deleteItem()메서드를 사용해서 상품을 Cart에서 삭제
		}else if(button.equals("수정")) {
			String num = request.getParameter("NUM"); //상품의 갯수 파라미터 수신
			cart.modifyItem(code, Integer.parseInt(num));
		}
		response.sendRedirect("cartList.do");
	}

}
