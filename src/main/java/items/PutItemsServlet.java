package items;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ItemsCrud;

/**
 * Servlet implementation class PutItemsServlet
 */
@WebServlet("/putItems.do")
public class PutItemsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PutItemsServlet() {
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
		request.setCharacterEncoding("EUC-KR");//한글처리
		String code = request.getParameter("CODE");
		String name = request.getParameter("NAME");
		String price = request.getParameter("PRICE");
		String nation = request.getParameter("NATION");
		String spec = request.getParameter("SPEC");
		Items dto = new Items();//상품정보 DTO 생성
		dto.setItem_code(code); dto.setItem_title(name); dto.setNation(nation);
		dto.setPrice(Integer.parseInt(price)); dto.setItem_spec(spec);//상품정보를 DTO에 넣는다.
		ItemsCrud dao = new ItemsCrud();
		int result = dao.putItem(dto);//DTO를 DAO의 메서드로 전달해서 상품정보를 삽입한다.
		String url="index.jsp?BODY=putItemsResult.jsp?R=";
		if(result > 0) url = url + "OK";
		else url = url + "NOK";
		//삽입결과 JSP(putItemsResult.jsp)로 화면을 전환한다.(Redirect)
		response.sendRedirect(url);
	}

}















