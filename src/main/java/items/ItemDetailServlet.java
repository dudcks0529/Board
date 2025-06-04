package items;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ItemsCrud;

/**
 * Servlet implementation class ItemDetailServlet
 */
@WebServlet("/itemDetail.do")
public class ItemDetailServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String code =request.getParameter("CODE");
    ItemsCrud dao = new ItemsCrud();
    Items dto =dao.getItem(code);
    request.setAttribute("ITEM", dto);
    HttpSession session = request.getSession();
    String userId=(String)session.getAttribute("ID");
    String url="";
    if( userId != null && userId.equals("admin") ){
    	ArrayList<MadeIn> nations = (ArrayList<MadeIn>)dao.getNations();
    	request.setAttribute("NATIONS", nations);
    	url="itemDetailAdmin.jsp";
    }else {
    	url="itemDetail.jsp";
    }
    
    RequestDispatcher r = request.getRequestDispatcher("index.jsp?BODY="+url);
    r.forward(request, response);
       //코드에 있는 상품번호로 상품정보를 검색한다. 검색결과를 저장한다. 페이지를 바꾼다.

   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      doGet(request, response);
   }

}
