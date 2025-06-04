package bbs;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BoardCrud;

/**
 * Servlet implementation class WriteBoardServlet
 */
@WebServlet("/writeBoard.do")
public class WriteBoardServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteBoardServlet() {
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
      request.setCharacterEncoding("EUC-KR");//method가 post인 경우의 한글처리를 한다.
      String title = request.getParameter("TITLE");//파라미터 TITLE을 수신한다.
      String content = request.getParameter("CONTENT");//파라미터 CONTENT를 수신한다.
      HttpSession session = request.getSession();//HttpSession 인스턴스 생성
      String id = (String)session.getAttribute("ID");
      BoardCrud dao = new BoardCrud();//자유게시판용 DAO를 생성
      int seq = dao.getMaxSeq() + 1;//글번호
      BoardDTO dto = new BoardDTO();//DTO를 생성
      dto.setWriter(id); dto.setTitle(title); dto.setContent(content); dto.setSeq(seq);
      int result = dao.putBBS(dto);
      String url = "index.jsp?BODY=bbsInputResult.jsp?R=";
      if(result > 0) {//게시글 등록이 성공한 경우
         url = url + "OK";
      }else {//게시글 등록이 실패한 경우
         url = url + "NOK";
      }
      response.sendRedirect(url);
   }
}
class WriteInsertDAO{
   Connection con; PreparedStatement pstmt; 
   String query = "insert into free_board values(?,?,?,sysdate)";
   boolean doIt(BoardDTO dto) {
      boolean yesOrNo = false;
      try {
         Class.forName(OracleXE11g.LIB);
         con = DriverManager.getConnection(OracleXE11g.NAME, "hr","hr");
         pstmt = con.prepareStatement(query);
         pstmt.setString(1, dto.getWriter());//첫번째 물음표에 작성자를 String으로 넣는다.
         pstmt.setString(2, dto.getTitle());//두번째 물음표에 제목을 String으로 넣는다.
         pstmt.setString(3, dto.getContent());//세번째 물음표에 글내용을 String으로 넣는다.
         pstmt.executeUpdate();//insert를 실행한다.
         yesOrNo = true;//insert에 문제가 없는 경우
      }catch(Exception e) {
         e.printStackTrace();
         System.out.println("게시글 삽입 중 문제발생!");
      }finally {
         try {con.close();} catch(Exception e) {}
      }
      return yesOrNo;
   }
}














