package image;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ImageCrud;

/**
 * Servlet implementation class ImageDetailServlet
 */
@WebServlet("/imageDetail.do")
public class ImageDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("ID"); //이미지 게시글 번호를 수신
		ImageCrud dao = new ImageCrud();
		ImageBBS dto = dao.getImageDetail(Integer.parseInt(id));
		request.setAttribute("DETAIL", dto);
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("ID"); //세션에서 계정을 찾는다
		String url = "";
		
		if(user_id == null) { //1. 로그인을 하지 않은 고객:메뉴를 보여주지 말자
			url="imageDetail.jsp";
		}else if(user_id.equals(dto.getWriter())) { //3.로그인을 한 고객(작성자임):메뉴(수정,삭제,답글)
			url="imageDetailOwner.jsp";
		}else { //2. 로그인을 한 고객(작성자가 아님) : 메뉴(답글)
			url="imageDetailReply.jsp";
		}
		
		RequestDispatcher r = request.getRequestDispatcher("index.jsp?BODY="+url);
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
