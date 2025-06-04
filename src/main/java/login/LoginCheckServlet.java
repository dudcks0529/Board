package login;

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
 * Servlet implementation class LoginCheckSurvlet
 */
@WebServlet("/LoginCheck.do")
public class LoginCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//HttpSession에 계정이 있는지 확인
		//계정이 있으면, 관리자 계정인지 다시 확인
		//관리자 계정인 경우, 화면을 input_item.jsp로 전환한다.
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("ID"); //HttpSession에서 ID로 계정을 찾는다
		if(id != null) { //로그인 한 경우
			if(id.equals("admin")) { //관리자 계정으로 로그인 한 경우
				response.sendRedirect("index.jsp?BODY=input_item.jsp");
			} else { //일반 회원 계정으로 로그인 한 경우
				response.sendRedirect("index.jsp?BODY=noAdmin.jsp");
			}
		}else { //로그인 안한 경우
			response.sendRedirect("index.jsp?BODY=login.jsp?MSG=Y");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

class LoginCheckDAO {
	String query = "select user_id from user_info where user_id = ? and user_pwd = ?";
	Connection con; PreparedStatement pstmt; ResultSet rs; boolean yesOrNo;
	boolean doIt(String id, String pwd) {
		try {
			Class.forName(OracleXE11g.LIB);
			con = DriverManager.getConnection(OracleXE11g.NAME,"ID","PWD");
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id); pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				yesOrNo = true;
			}
		}catch(Exception e) {
			System.out.println("로그인 검사 중 문제 발생!");
		}finally {
			try {con.close();} catch(Exception e) {}
		}
		return yesOrNo;
	}
}

class OracleXE11g {
	   static final String LIB = "oracle.jdbc.driver.OracleDriver";//상수
	   static final String NAME = "jdbc:oracle:thin:@localhost:1521:XE";//상수
	}
