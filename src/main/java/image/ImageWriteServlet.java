package image;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.ImageCrud;

/**
 * Servlet implementation class ImageWriteServlet
 */
@WebServlet("/imageWrite.do")
public class ImageWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageWriteServlet() {
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
	      request.setCharacterEncoding("EUC-KR");
	      String uploadPath = "/upload"; //업로드 될 파일이 저장될 폴더 이름선언
	      int maxLimit = 5 * 1024 * 1024;  //업로드 파일의 최대 크기 설정 5메가
	      String encType = "EUC-KR"; // 사용되는 언어코드 설정
	      ServletContext ctx = this.getServletContext();
	      String realPath = ctx.getRealPath(uploadPath); //upload 폴더의 절대경로를 찾는다
	      String fileName ="";// 파일 업로드가 성공하면 파일이름을 저장할 변수선언
	      System.out.println("파일이 저장된 폴더 : "+realPath);
	      String result ="NOK";
	      
	      try {
	         //파일을 업로드 할 때 사용하는 클래스(MultipartRequest)의 인스턴스를 생성한다.
	         MultipartRequest multipart = new MultipartRequest(request, realPath,
	               maxLimit, encType, new DefaultFileRenamePolicy());
	         fileName = multipart.getFilesystemName("IMAGENAME");
	         if(fileName == null) {
	            System.out.println("업로드 실패");
	         }else {
	        	 result = "OK";
	        	//지금까지 JSP에 있는 파라미터 값을 서블릿에서 불러오는 방법
	        	//HttpServletRequest에 있는 getParameter()을 사용한다
	            //request.getParameter("TITLE");
	        	 
	            //파일을 가지고 있는 JSP(폼)에 있는 파라미터 값을 서블릿에서 불러오는 방법(아래)
	            //MultipartRequest에 있는 getParameter()메서드를 사용한다
	        	String orderNo = multipart.getParameter("orderno");
	        	String groupId = multipart.getParameter("groupid");
	        	String parentId = multipart.getParameter("parentid");
	        	 
	            String title = multipart.getParameter("TITLE");
	            String password = multipart.getParameter("PWD");
	            String content = multipart.getParameter("CONTENT");
	            
	            ImageBBS dto = new ImageBBS();    //DTO를 생성한다.
	            ImageCrud dao = new ImageCrud();  //다오를 생성한다.
	            int number = dao.getMaxNum() + 1; //글번호를 생성한다. 
	            
	            dto.setW_id(number); dto.setTitle(title); dto.setPassword(password);
	            dto.setContent(content); dto.setImagename(fileName);
	            
	            HttpSession session = request.getSession();
	            String id = (String)session.getAttribute("ID"); //세션에서 계정을 찾는다.
	            dto.setWriter(id);
	            if(parentId == null || parentId.equals("")) { //parentId가 없으므로 원글
	            	dto.setGroup_id(number); //그룹번호가 글번호와 같다.
	            	dto.setParent_id(0);
	            	dto.setOrder_no(0);
	            }else { //parentId가 있으므로 답글
	            	dto.setParent_id(Integer.parseInt(parentId));
	            	dto.setGroup_id(Integer.parseInt(groupId));
	            	dto.setOrder_no(Integer.parseInt(orderNo));
	            	dao.updateOrderNo(dto);
	            	//DB에서 답글 순서번호(order_no)를 update한다.
	            }
	            
	            int yesOrNo = dao.insertImageBBS(dto); //이미지게시글 테이블에 insert 실행
	            if(yesOrNo > 0) { //이미지 게시글 등록 성공한 경우
	            	System.out.println("이미지 게시글 등록 성공");
	            } else { //실패한 경우
	            	System.out.println("이미지 게시글 등록 실패");
	            }
	         }
	      }catch(Exception e){
	         
	      }
	      // 파일을 업로드하고 게시글을 DB에 저장한 후 페이지를 uploadResult.jsp로 바꾼다 (Redirect)
	      response.sendRedirect("index.jsp?BODY=uploadResult.jsp?R="+result);
	      }
}
