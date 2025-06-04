package bbs;

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

import dao.BoardCrud;

/**
 * Servlet implementation class BoardDetailSurvlet
 */
@WebServlet("/boardDetail.do")
public class BoardDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String seq = request.getParameter("SEQ");
		BoardCrud dao = new BoardCrud();
		BoardDTO dto = dao.getBoard(Integer.parseInt(seq));
		request.setAttribute("DTO", dto);
		RequestDispatcher r = request.getRequestDispatcher("index.jsp?BODY=boardDetail.jsp");
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

class BoardDetailDAO{
	Connection con; PreparedStatement pstmt; ResultSet rs;
	BoardDTO dto;
	String query = "select id,title,content,to_char(write_date, 'YYYY/MM/DD HH24:MI:SS') "
			+ "from free_board where title = ?";
	
	BoardDTO doIt(String title) {
		try {
			Class.forName(OracleXE11g.LIB);
			con = DriverManager.getConnection(OracleXE11g.NAME,"ID","PWD");
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, title);
			rs = pstmt.executeQuery();
			rs.next();
			dto = new BoardDTO();
			dto.setWriter(rs.getString(1));
			dto.setTitle(rs.getString(2));
			dto.setContent(rs.getString(3));
			dto.setWrite_date(rs.getString(4));
			
		}catch(Exception e) {
			System.out.println("제목과 일치하는 게시글 조회 중 문제발생!!");
		}finally{
			try {con.close();} catch(Exception e) {}
		}
		return dto;
	}
}
