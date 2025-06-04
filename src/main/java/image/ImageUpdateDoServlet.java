package image;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.ImageCrud;

/**
 * Servlet implementation class ImageUpdateDoServlet
 */
@WebServlet("/imageUpdateDo.do")
public class ImageUpdateDoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageUpdateDoServlet() {
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
		request.setCharacterEncoding("EUC-KR"); //한글 처리
		String uploadPath = "/upload"; //업로드 파일이 저장될 변수 이름
		int maxLimit = 5 * 1024 * 1024; //업로드 파일의 최대 크기 설정
		ServletContext ctx = this.getServletContext(); //ServletContext 인스턴스 생성
		String realPath = ctx.getRealPath(uploadPath); //upload 폴더의 절대경로를 획득
		String fileName = null; //업로드 성공시 파일이름이 저장될 변수 선언
		String result = ""; //게시글 수정의 성공 유무가 저장될 변수 선언
		try {
			MultipartRequest multipart = new MultipartRequest(request, realPath, maxLimit,
					"euc-kr", new DefaultFileRenamePolicy()); //MultipartRequest 인스턴스 생성
			String w_id = multipart.getParameter("id"); //글번호
			String title = multipart.getParameter("TITLE"); //제목
			String content = multipart.getParameter("CONTENT"); //내용
			String old_image = multipart.getParameter("old_image"); //기존의 이미지 파일이름
			ImageBBS dto = new ImageBBS(); //DTO를 생성한다.
			dto.setTitle(title); dto.setContent(content); dto.setW_id(Integer.parseInt(w_id));
			dto.setImagename(old_image);
			fileName = multipart.getFilesystemName("image_name"); //파일을 업로드 한다.
			if(fileName != null) { //파일이름이 존재하는 경우, 즉 업로드 성공(새로운 파일을 선택함)
				dto.setImagename(fileName); //새로운 파일 이름으로 설정
			}
			ImageCrud dao = new ImageCrud();
			int yesOrNo = dao.updateImageBBS(dto);
			if(yesOrNo > 0) result = "OK";
			else result = "NOK";
		}catch(Exception e) {
			e.printStackTrace();
		}
		//imageUpdateResult.jsp로 페이지를 전환한다.(Redirect)
		response.sendRedirect("index.jsp?BODY=imageUpdateResult.jsp?R="+result);
	}
}
