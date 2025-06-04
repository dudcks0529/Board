package bbs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardCrud;

/**
 * Servlet implementation class BoardModifyServlet
 */
@WebServlet("/boardModify.do")
public class BoardModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		String seq = request.getParameter("SEQ");
		String title = request.getParameter("TITLE");
		String content = request.getParameter("CONTENT");
		String btn = request.getParameter("BTN");
		BoardCrud dao = new BoardCrud();
		if(btn.equals("삭제")) {
			int result = dao.deleteBoard(Integer.parseInt(seq));
			String url = "index.jsp?BODY=bbsDeleteResult.jsp?R=";
			if(result > 0) {
				url = url + "OK";
			} else {
				url = url + "NOK";
			}
			response.sendRedirect(url);
		} else if(btn.equals("수정")) {
			BoardDTO dto = new BoardDTO();
			dto.setSeq(Integer.parseInt(seq)); dto.setTitle(title); dto.setContent(content);
			int result = dao.updateBoard(dto);
			String url = "index.jsp?BODY=bbsUpdateResult.jsp?R=";
			if(result > 0) {
				url = url + "OK";
			} else {
				url = url + "NOK";
			}
			response.sendRedirect(url);
		}
	}

}
