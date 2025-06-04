package items;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddCartServlet
 */
@WebServlet("/addCart.do")
public class AddCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("ID");
		String url = "";
		if(id == null) { //계정이 없는 경우(로그인 안한 경우)
			url = "login.jsp?MSG=Y";
		} else { //계정이 있는경우(로그인 한 경우)
			String code = request.getParameter("CODE"); //상품번호 수신
			Cart cart = (Cart)session.getAttribute("CART");
			if(cart == null) cart = new Cart(id);//로그인 한 계쩡으로 장바구니(Cart)를 생성한다.
			//-> 세션에 장바구니가 없을 때만 Cart가 생성된다.
			cart.addCart(code, 1); //상품번호와 상품갯수(1)을 저장한다.
			session.setAttribute("CART", cart);
			url = "addCartResult.jsp";
		}
		
		response.sendRedirect(url);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
