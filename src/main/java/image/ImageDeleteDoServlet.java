package image;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ImageCrud;

/**
 * Servlet implementation class ImageDeleteDoServlet
 */
@WebServlet("/imageDeleteDo.do")
public class ImageDeleteDoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageDeleteDoServlet() {
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
		String w_id = request.getParameter("id"); //이미지 게시글 번호 수신
		ImageCrud dao = new ImageCrud();
		int result = dao.deleteImageBBS(Integer.parseInt(w_id));
		if(result > 0) { //삭제가 된 경우 imageDeleteResult.jsp로 화면을 전환한다.
			response.sendRedirect("index.jsp?BODY=imageDeleteResult.jsp?R=OK");
		}else { //삭제가 되지 않은 경우
			response.sendRedirect("index.jsp?BODY=imageDeleteResult.jsp?R=NOK");
		}
	}

}
