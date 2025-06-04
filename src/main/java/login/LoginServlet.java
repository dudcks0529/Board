package login;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CartCrud;
import dao.Crud;
import items.Cart;
import items.Cart_tbl;

/**
 * Servlet implementation class LoginSurvlet
 */
@WebServlet("/Login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		String id = request.getParameter("ID");
		String password = request.getParameter("PWD");
		//팝업창을 통한 로그인인지, index.jsp를 통한 로그인인지 구분해야한다.
		String popup = request.getParameter("POPUP");
		Crud dao = new Crud();
		LoginDTO dto = new LoginDTO(); dto.setId(id); dto.setPassword(password);
		String yesOrNo = dao.getIdByLogin(dto);
		//boolean yesOrNo = dao.doIt(id, password);
		if(yesOrNo != null) { //로그인 성공한 경우
			HttpSession session = request.getSession();
			session.setAttribute("ID", id);
			
			//데이터베이스에서 장바구니 테이블(cart_tbl)을 검색한다.
			CartCrud cartDao = new CartCrud();
			ArrayList<Cart_tbl> list = (ArrayList<Cart_tbl>)cartDao.getCart(id);
			if(list != null) { //장바구니 테이블에 데이터가 존재하는 경우, 즉, 장바구니에 상품이 존재
				Cart cart = new Cart(id); //계정으로 장바구니(Cart)를 생성한다.
				for(int i=0; i<list.size(); i++) { //장바구니 테이블에 있는 상품의 갯수만큼 반복
					Cart_tbl ct = list.get(i); //i번째 검색결과를 dto에 저장한다.
					cart.getCodeList().add(ct.getCode());
					//위, Cart에 있는 codeList에 테이블에 있는 상품 코드를 저장
					cart.getNumList().add(ct.getNum());
					//위, Cart에 있는 numList에 테이블에 있는 상품 갯수를 저장
				}
				session.setAttribute("CART", cart); //세션에 장바구니를 저장한다.
			}
			
			if(popup != null) { //팝업창을 통한 로그인
				response.sendRedirect("loginResult.jsp");
			} else { //index를 통한 로그인
				response.sendRedirect("index.jsp?BODY=loginSuccess.jsp");
			}
		}else { //로그인 실패한 경우
			if(popup != null) { //팝업창을 통한 로그인 실패
				response.sendRedirect("login.jsp?MSG=Y.jsp");
			} else { //index를 통한 로그인 실패
				response.sendRedirect("index.jsp?BODY=login.jsp?MSG=Y");
			}
		}
	}

}
