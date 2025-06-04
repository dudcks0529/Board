package notice;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Crud;

/**
 * Servlet implementation class WriteNoticeSurvlet
 */
@WebServlet("/writeNotice.do")
public class WriteNoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteNoticeServlet() {
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
		String title = request.getParameter("TITLE");
		String content = request.getParameter("CONTENT");
//		NoticeNumDAO dao = new NoticeNumDAO();
		Crud dao = new Crud(); //마이바티스용 dao를 생성한다.
		int newNumber = dao.getMaxNoticeNum() + 1; //새 게시글 번호를 찾는다.(기존의 글번호 +1)
		NoticeDTO dto = new NoticeDTO();
		dto.setNum(newNumber); dto.setTitle(title);
		dto.setContent(content);
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("ID");
		dto.setWriter(id);
//		NoticeWriteDao write_dao = new NoticeWriteDao();
		
		int result = dao.insertNotice(dto);
		//페이지를 공지사항 목록으로 바꾼다.
		if(result > 0) { 
			response.sendRedirect("noticeList.do");//페이지를 공지사항 목록 페이지로 바꾼다.
		} else { //페이지를 공지사항 등록 실패 페이지로 바꾼다.
			
		}
	}

}
class OracleXE11g {
	   static final String LIB = "oracle.jdbc.driver.OracleDriver";//상수
	   static final String NAME = "jdbc:oracle:thin:@localhost:1521:XE";//상수
	}

class NoticeNumDAO{
	String query = "select max(num) from notice_tbl";
	Connection con; PreparedStatement pstmt; ResultSet rs; int max;
	int findMax() {
		try {
			Class.forName(OracleXE11g.LIB);
			con = DriverManager.getConnection(OracleXE11g.NAME,"ID","PWD");
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			if(rs.next()) { //조회결과가 존재하는 경우(가장 큰 글번호)
				max = rs.getInt(1);
			} else { //조회 결과가 없는 경우(현재 글이 없는 경우)
				max = 0;
			}
		}catch(Exception e) {
			System.out.println("가장 큰 공지사항 번호 검색 중 문제발생!!");
		}finally {
			try {con.close();} catch(Exception e){}
		}
		return max;
	}
}

class NoticeWriteDao {
	String query = "insert into notice_tbl values(?,?,?,sysdate,?)";
	Connection con; PreparedStatement pstmt;
	boolean result;
	boolean doIt(NoticeDTO dto) {
		try {
			Class.forName(OracleXE11g.LIB);
			con = DriverManager.getConnection(OracleXE11g.NAME,"ID","PWD");
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, dto.getNum()); pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent()); pstmt.setString(4, dto.getWriter());
			pstmt.executeUpdate();
			result = true;
		}catch(Exception e) {
			System.out.println("공지사항 등록 중 문제발생");
		}finally {
			try {con.close();} catch(Exception e) {}
		}
		return result;
	}
}