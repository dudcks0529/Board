package items;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ItemsCrud;

/**
 * Servlet implementation class FindNationsServlet
 */
@WebServlet("/findNations.do")
public class FindNationsServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindNationsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      //원산지 테이블(madein_tbl)에서 국가코드와 국가이름을 검색한다.
      //검색된 국가코드와 국가이름을 JSP(inputItems.jsp)로 전달한다.
      ItemsCrud dao = new ItemsCrud();
      ArrayList<MadeIn> list = (ArrayList<MadeIn>)dao.getNations();
      request.setAttribute("NATIONS", list);//조회결과를 HttpServletRequest에 저장
      RequestDispatcher r = request.getRequestDispatcher("index.jsp?BODY=inputItems.jsp");
      r.forward(request, response);
      //inputItems.jsp로 화면을 전환한다.(Forward)
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      doGet(request, response);
   }

}
