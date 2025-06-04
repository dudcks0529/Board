package notice;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Crud;

/**
 * Servlet implementation class NoticeModifySurvlet
 */
@WebServlet("/noticeModify.do")
public class NoticeModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeModifyServlet() {
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
		request.setCharacterEncoding("EUC-KR");
		String button = request.getParameter("BTN"); //버튼의 제목이 들어있는 파라미터 수신
		String num = request.getParameter("NO"); //공지글 번호가 들어있는 파라미터 수신
		if(button.equals("수정")) { //수정 버튼을 누른 경우
			Crud dao = new Crud();
			String title = request.getParameter("TITLE");
			String content = request.getParameter("CONTENT");
			NoticeDTO dto = new NoticeDTO();
			dto.setNum(Integer.parseInt(num));
			dto.setTitle(title); dto.setContent(content);
			int result = dao.updateNoitce(dto); //업데이트 실행
			if(result > 0) { //업데이트 성공 시
				response.sendRedirect("noticeList.do"); //공지사항 목록 출력
			} else { //업데이트 실패 시
				
			}
		}else if(button.equals("삭제")) { //삭제 버튼을 누른 경우
			//JDBC방식으로 삭제 작업
//			NoticeDeleteDAO dao = new NoticeDeleteDAO();
//			boolean result = dao.doIt(num);
			Crud dao = new Crud();
			int result = dao.deleteNotice(Integer.parseInt(num));
			if(result > 0) { //공지글 삭제 성공
				response.sendRedirect("noticeList.do");
			} else { //공지글 삭제 실패
				
			}
		}
	}
}

class NoticeDeleteDAO{
	String query = "delete from notice_tbl where num = ?";
	Connection con; PreparedStatement pstmt; boolean result;
	boolean doIt(String num) {
		try {
			Class.forName(OracleXE11g.LIB);
			con = DriverManager.getConnection(OracleXE11g.NAME,"ID","PWD");
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(num));
			pstmt.executeUpdate();
			result = true;
		}catch(Exception e) {
			System.out.println("공지글 삭제 작업 중 문제발생!");
		}finally {
			try {con.close();} catch(Exception e) {}
		}
		return result;
	}
}