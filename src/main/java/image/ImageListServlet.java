package image;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ImageCrud;
import notice.FromTo;
import sangsoo.Sangsoo;

/**
 * Servlet implementation class ImageListServlet
 */
@WebServlet("/imageList.do")
public class ImageListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page_num = request.getParameter("PAGE_NUM");
		int currentPage = 1; //현재 페이지
		//PAGE_NUM 파라미터가 존재하면 현재 페이지를 PAGE_NUM 파라미터 값으로 설정한다.
		if(page_num != null) currentPage = Integer.parseInt(page_num);
		int startRow = (currentPage - 1) * Sangsoo.PAGE_NUM;
		int endRow = ((currentPage - 1) * Sangsoo.PAGE_NUM) + 6;
		FromTo ft = new FromTo();
		ft.setStart(startRow); ft.setEnd(endRow);
		ImageCrud dao = new ImageCrud();
		int totalCount = dao.getTotalImage(); //이미지 게시글의 전체 수
		int pageCount = 0; //페이지 수를 위한 변수 선언
		if(totalCount > 0) {
			pageCount = totalCount / Sangsoo.PAGE_NUM; //한 페이지에 게시글 5개를 출력
			if(totalCount % Sangsoo.PAGE_NUM > 0) pageCount++; //나머지가 존재하면 페이지 수 1증가
		}
		ArrayList<ImageBBS> imageList = dao.getImageList(ft);
		request.setAttribute("pageCount", pageCount); //페이지 수를 저장
		request.setAttribute("currentPage", currentPage); //현재 페이지 수를 저장
		request.setAttribute("LIST", imageList);
		request.setAttribute("TOTAL", totalCount);
		request.setAttribute("START", startRow + 1);
		if(endRow > totalCount) {
			endRow = totalCount + 1;
		}
		request.setAttribute("END", endRow - 1);
		RequestDispatcher r = request.getRequestDispatcher("index.jsp?BODY=imageList.jsp");
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
