package notice;

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

import dao.Crud;
import sangsoo.Sangsoo;

/**
 * Servlet implementation class NoticeListSurvlet
 */
@WebServlet("/noticeList.do")
public class NoticeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//페이지 처리에 사용하는 파라미터를 수신한다
		String page_num = request.getParameter("PAGE_NUM");
		int currentPageNumber = 1; //기본으로 현재 페이지는 1
		if(page_num != null) { //page_num 파라미터가 존재하는 경우
			currentPageNumber = Integer.parseInt(page_num);
		} else { //page_num파라미터가 존재하지 않는 경우
			
		}
		int startRow = (currentPageNumber - 1) * Sangsoo.PAGE_NUM; //시작 일련번호
		int endRow = ((currentPageNumber - 1) * Sangsoo.PAGE_NUM) + 6; //종료 일련번호
		
		FromTo ft = new FromTo();
		ft.setStart(startRow); ft.setEnd(endRow);
		Crud dao = new Crud();
		ArrayList<NoticeDTO> list = dao.getNoticeList(ft);
		
		
		//페이지 수를 찾는 작업 시작
		Crud countDao = new Crud();
		int total = countDao.getMaxNoticeNum(); //전체 공지글의 갯수
		int pageCount = total/Sangsoo.PAGE_NUM; //정수 나누기 정수 = 결과도 정수
		if(total % Sangsoo.PAGE_NUM != 0) {
			pageCount++;
		}
		//페이지 수를 찾는 작업 끝
		request.setAttribute("PAGES", pageCount); //페이지 수를 저장한다.
		request.setAttribute("NOTICES", list);
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp?BODY=noticeList.jsp");
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

class NoticeListDAO{
	String qeury = "select num, title, writer, w_date "
			+ "from (select rownum rank, num, title, writer, "
			+ "to_char(write_date, 'YYYY/MM/DD HH24:MI:SS') w_date"
			+ " from notice_tbl order by num desc) "
			+ "where rank > ? and rank < ?";
	Connection con; PreparedStatement pstmt; ResultSet rs; ArrayList<NoticeDTO> list;
	ArrayList<NoticeDTO> doIt(int start, int end){
		try {
			Class.forName(OracleXE11g.LIB);
			con = DriverManager.getConnection(OracleXE11g.NAME,"ID","PWD");
			pstmt = con.prepareStatement(qeury);
			pstmt.setInt(1, start); pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			list = new ArrayList<NoticeDTO>();
			while(rs.next()) {
				NoticeDTO dto = new NoticeDTO();
				dto.setNum(rs.getInt(1)); dto.setTitle(rs.getString(2));
				dto.setWriter(rs.getString(3)); dto.setNotice_date(rs.getString(4));
				list.add(dto);
			}
		}catch(Exception e) {
			System.out.println("공지사항 목록 검색 중 문제발생");
		}finally {
			try {con.close();} catch(Exception e) {}
		}
		return list;
	}
}

class NoticeCountDAO {
	String query = "select count(*) from notice_tbl";
	Connection con; PreparedStatement pstmt; ResultSet rs; int totalCount;
	int doIt() {
		try {
			Class.forName(OracleXE11g.LIB);
			con = DriverManager.getConnection(OracleXE11g.NAME,"ID","PWD");
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				totalCount = rs.getInt(1);
			}
		}catch(Exception e) {
			System.out.println("전체 공지글 갯수를 찾는 작업 중 문제발생!!");
		}finally {
			try {con.close();} catch(Exception e) {}
		}
		return totalCount;
	}
}