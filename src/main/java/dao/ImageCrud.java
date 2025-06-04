package dao;

import java.io.InputStream;
import java.util.ArrayList;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import image.ImageBBS;
import notice.FromTo;

public class ImageCrud {
	   private final String MAPPER = "imageMapper";//매퍼이름 선언
	   
	   public int updateOrderNo(ImageBBS dto) {
		   SqlSession ss = this.getSession(); int result = 0;
		   try {
			   result = ss.update(MAPPER+".updateOrderNo", dto);
			   if(result > 0) ss.commit();
			   else ss.rollback();
		   }finally {
			   ss.close();
		   }
		   return result;
	   }
	   
	   public int updateImageBBS(ImageBBS dto) {
		   SqlSession ss = this.getSession(); int result = 0;
		   try {
			   result = ss.update(MAPPER+".updateImageBBS", dto);
			   if(result > 0) ss.commit();
			   else ss.rollback();
		   }finally {
			   ss.close();
		   }
		   return result;
	   }
	   public int deleteImageBBS(int id) {
		   SqlSession ss = this.getSession(); int result = 0;
		   try {
			   result = ss.delete(MAPPER+".deleteImageBBS", id);
			   if(result > 0) ss.commit();
			   else ss.rollback();
		   }finally {
			   ss.close();
		   }
		   return result;
	   }
	   
	   public ImageBBS getImageDetail(int id) {
		   SqlSession ss = this.getSession(); ImageBBS detail = null;
		   try {
			   detail = ss.selectOne(MAPPER+".getImageDetail", id);
		   }finally {
			   ss.close();
		   }
		   return detail;
	   }
	   
	   public int getTotalImage() {
		   SqlSession ss = this.getSession(); Integer totalCount = 0;
		   try {
			   totalCount = ss.selectOne(MAPPER+".getTotal");
			   if(totalCount == null) totalCount = 0;
		   }finally {
			   ss.close();
		   }
		   return totalCount;
	   }
	   
	   public ArrayList<ImageBBS> getImageList(FromTo ft){
		   SqlSession ss = this.getSession(); ArrayList<ImageBBS> list; //조회결과 저장을 위한 변수
		   try {
			   list = (ArrayList)ss.selectList(MAPPER+".imageList", ft);
		   }finally {
			   ss.close();
		   }
		   return list;
	   }
	   
	   public Integer insertImageBBS(ImageBBS dto) {
	      SqlSession ss = this.getSession(); int result = 0;
	      try {
	         result = ss.insert(MAPPER+".putImageBBS", dto);
	         if(result > 0) ss.commit();
	         else ss.rollback();
	      } finally {
	         ss.close();
	      }
	      return result;
	   }

	
	public Integer getMaxNum() {
		SqlSession ss = this.getSession(); Integer maxNum = 0;
		try {
			maxNum = ss.selectOne(MAPPER+".getMaxNum");
			if(maxNum == null) maxNum = 0;
		}finally {
			ss.close();
		}
		return maxNum;
	}
	
	private SqlSession getSession() {
		String config = "mybatisConfig.xml"; //SqlSession을 생성하기 위해 데이터베이스 정보가 필요하고,
							//데이터베이스 정보를 XML파일에 작성한다. 따라서, SqlSession을 생성할 때
							//XML파일에서 데이터베이스 정보를 불러온다.
		InputStream is = null; //파일을 불러올 때 필요한 객체(InputStream) 선언
		try {
			is = Resources.getResourceAsStream(config); //XML파일을 연다.
		}catch(Exception e) {
			System.out.println("myBatis환경설정 파일이 없거나 잘못 작성되었음!");
		}
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = builder.build(is);
		SqlSession ss = factory.openSession(); //SqlSession 인스턴스 생성
		return ss;
	}
}
