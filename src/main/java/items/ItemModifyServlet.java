package items;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ItemsCrud;

/**
 * Servlet implementation class ItemModifyServlet
 */
@WebServlet("/itemModify.do")
public class ItemModifyServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemModifyServlet() {
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
      request.setCharacterEncoding("EUC-KR");//한글처리
      String button = request.getParameter("BTN");//버튼의 제목을 수신
      String code = request.getParameter("CODE");//상품번호 수신
      ItemsCrud dao = new ItemsCrud();
      if(button.equals("삭제")) {//삭제버튼을 누른 경우
         int result = dao.deleteItem(code);
         String url = "index.jsp?BODY=itemDeleteResult.jsp?R=";
         if(result > 0) {//삭제가 성공한 경우
            url = url + "OK";
         } else {//삭제가 실패한 경우
            url = url + "NOK";
         }
         response.sendRedirect(url);//삭제결과 JSP(itemDeleteResult.jsp)로 화면을 전환한다.(Redirect? Forward?)
      } else if(button.equals("수정")) {//수정버튼을 누른 경우
         String name = request.getParameter("NAME"); //상품이름
         String nation = request.getParameter("NATION"); //원산지 국가코드
         String spec = request.getParameter("SPEC"); //상품설명
         String price = request.getParameter("PRICE");//가격
         Items dto = new Items();
         dto.setItem_title(name); dto.setItem_code(code); dto.setNation(nation);
         dto.setItem_spec(spec); dto.setPrice(Integer.parseInt(price));
         int result = dao.updateItem(dto);
         String url = "index.jsp?BODY=itemUpdateResult.jsp?R=";
         if(result > 0) url = url + "OK";
         else url = url + "NOK";
         response.sendRedirect(url);
      }
      
   }

}
