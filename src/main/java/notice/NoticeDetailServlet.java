package notice;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NoticeDetailSurvlet
 */
@WebServlet("/noticeDetail.do")
public class NoticeDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String num = request.getParameter("NO");
		noticeDetailDAO dao = new noticeDetailDAO();
		NoticeDTO dto = dao.doIt(Integer.parseInt(num));//글번호로 공지글을 검색하고 NoticeDTO에 저장
		request.setAttribute("NOTICE", dto);
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp?BODY=noticeDetail.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}

class noticeDetailDAO{
	String query = "select num, title, writer, content, "
			+ "to_char(write_date, 'YYYY/MM/DD HH24:MI:SS') "
			+ "from notice_tbl where num = ?";
	
	Connection con; PreparedStatement pstmt; ResultSet rs; NoticeDTO dto;
	NoticeDTO doIt(int num){
		try {
			Class.forName(OracleXE11g.LIB);
			con = DriverManager.getConnection(OracleXE11g.NAME,"ID","PWD");
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new NoticeDTO();//조회결과를 저장하기 위해 DTO를 생성한다
				dto.setNum(rs.getInt(1)); dto.setTitle(rs.getString(2));
				dto.setWriter(rs.getString(3)); dto.setContent(rs.getString(4));
				dto.setNotice_date(rs.getString(5));
			}
		}catch(Exception e) {
			System.out.println("공지사항 글 상세 조회 중 문제발생");
		}finally {
			try {con.close();} catch(Exception e) {}
		}
		return dto;
	}
}