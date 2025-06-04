package bbs;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardCrud;
import notice.FromTo;

/**
 * Servlet implementation class BoardListSurvlet
 */
@WebServlet("/boardList.do")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page_num = request.getParameter("PAGE_NUM");
		BoardCrud dao = new BoardCrud();
		int totalCount = dao.getTotalCount(); //게시글의 전체 갯수
		int pageCount = totalCount / 5; //한 페이지에 게시글 5개를 출력한다는 가정
		if(totalCount % 5 != 0) {
			pageCount++;
		}
		int currentPage = 1;
		if(page_num != null) currentPage = Integer.parseInt(page_num);
		int startRow = (currentPage - 1) * 5; 
		int endRow = ((currentPage - 1) * 5) + 6;
		FromTo ft = new FromTo();
		ft.setStart(startRow); ft.setEnd(endRow);
		ArrayList<BoardDTO> al = (ArrayList<BoardDTO>)dao.getBoardList(ft);
		request.setAttribute("BOARD", al); //검색된 게시글을 저장한다.
		request.setAttribute("PAGES", pageCount); //페이지의 갯수를 저장한다
		request.setAttribute("currentPage", currentPage); //현재 페이지
		RequestDispatcher r = request.getRequestDispatcher("index.jsp?BODY=boardList.jsp");
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

class BoardListDAO {
	String countQuery = "select count(*) from free_board";
	int count = 0;
	int getBBSCount() { //게시글의 갯수를 리턴하는 메서드
		try {
			Class.forName(OracleXE11g.LIB);
			con = DriverManager.getConnection(OracleXE11g.NAME,"ID","PWD");
			pstmt = con.prepareStatement(countQuery);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		}catch(Exception e) {
			System.out.println("게시글 건 수를 찾는 중 문제발생!!");
		}finally {
			try {con.close();} catch(Exception e) {}
		}
		return count;
	}
	Connection con; PreparedStatement pstmt; ResultSet rs;
	ArrayList<BoardDTO> al;
	String query = "select id,title,content,"
			+ "to_char(write_date, 'YYYY/MM/DD HH24:MI:SS') from free_board";
	ArrayList<BoardDTO> doIt(){
		try {
			Class.forName(OracleXE11g.LIB);
			con = DriverManager.getConnection(OracleXE11g.NAME,"ID","PWD");
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			al = new ArrayList<BoardDTO>();
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setWriter(rs.getString(1));
				dto.setTitle(rs.getString(2));
				dto.setContent(rs.getString(3));
				dto.setWrite_date(rs.getString(4));
				al.add(dto);
			}
		}catch(Exception e) {
			System.out.println("게시글 리스트 불러오는 중 문제발생!!");
		}finally {
			try {con.close();} catch(Exception e) {}
		}
		return al;
	}
}