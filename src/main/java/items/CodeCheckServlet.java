package items;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ItemsCrud;

/**
 * Servlet implementation class CodeCheckServlet
 */
@WebServlet("/codeCheck.do")
public class CodeCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CodeCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("CODE");
		//상품정보 테이블(sellingItems_tbl)에서 code에 있는 상품코드를 검색한다.
		ItemsCrud dao = new ItemsCrud(); //DAO생성
		String result = dao.getCode(code);
		request.setAttribute("CODE", code); //입력한 상품 코드 저장
		request.setAttribute("DUP", result); //중복 검사 결과 저장
		//검색결과가 존재하면 '이미 사용 중', 검색결과가 존재하지 않으면 '사용 가능'
		RequestDispatcher r = request.getRequestDispatcher("codeCheckResult.jsp");
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
