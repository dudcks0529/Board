package bbs;

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

/**
 * Servlet implementation class BbsLoginCheckSurvlet
 */
@WebServlet("/bbsLoginCheck.do")
public class BbsLoginCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BbsLoginCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//일반회원만 게시글을 쓸 수 있으므로, 일반회원으로 로그인한 경우만 write_board.jsp를 보여준다.
		HttpSession session = request.getSession();
		String result = (String)session.getAttribute("ID"); //고객이 입력한 계정을 수신
		String jsp = "";
		if(result == null) { //로그인 안한 경우
			jsp = "noLogin.jsp";
		} else if(result.equals("admin")) { //관리자로 로그인 한 경우
			jsp = "noUser.jsp";
		} else { //일반 사용자로 로그인 한 경우
			jsp = "write_board.jsp";
		}
		response.sendRedirect("index.jsp?BODY="+jsp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}

class OracleXE11g {
	   static final String LIB = "oracle.jdbc.driver.OracleDriver";//상수
	   static final String NAME = "jdbc:oracle:thin:@localhost:1521:XE";//상수
	}
