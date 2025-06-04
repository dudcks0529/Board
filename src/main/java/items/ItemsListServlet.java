package items;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ItemsCrud;
import notice.FromTo;

/**
 * Servlet implementation class ItemsListServlet
 */
@WebServlet("/itemsList.do")
public class ItemsListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemsListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page_num = request.getParameter("PAGE_NUM"); 
		
		//상품정보테이블에서 5가지 상품정보를 검색한다.
		int currentPage = 1; //현재 페이지 설정
		if(page_num != null) currentPage = Integer.parseInt(page_num);
		int startRow = (currentPage - 1) * 5;
		int endRow = ((currentPage -1) * 5) + 6;
		ItemsCrud dao = new ItemsCrud();
		int totalCount = dao.getTotalItems(); //전체 상품 수 검색
		int pageCount = 0; //페이지 수를 위한 변수 선언
		if(totalCount > 0) { //상품이 존재하는 경우, 페이지 수를 계산한다
			pageCount = totalCount / 5;
			if(totalCount % 5 > 0) pageCount++;
			if(endRow > totalCount) endRow = totalCount + 1;
		}
		FromTo ft = new FromTo(); ft.setStart(startRow); ft.setEnd(endRow);
		ArrayList<Items> itemList = (ArrayList<Items>)dao.getItems(ft);
		//검색결과를 HttpServletRequest에 저장한다.
		request.setAttribute("startRow", startRow+1);
		request.setAttribute("endRow", endRow-1);
		request.setAttribute("total", totalCount);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("ITEMS", itemList);
		request.setAttribute("currentPage", currentPage);
		RequestDispatcher r = request.getRequestDispatcher("index.jsp?BODY=itemsList.jsp");
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
