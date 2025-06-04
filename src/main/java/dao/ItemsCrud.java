package dao;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import items.Items;
import items.MadeIn;
import notice.FromTo;

public class ItemsCrud {
   private final String MAPPER = "itemsMapper.";
   
   public int updateItem(Items dto) {
		SqlSession ss = this.getSession(); int result = 0;
		try {
			result = ss.update(MAPPER+"updateItem", dto);
			if(result > 0) ss.commit();
			else ss.rollback();
		}finally {
			ss.close();
		}
		return result;
	}//상품정보 수정 메서드
   
   public int deleteItem(String id) {
	      SqlSession ss = this.getSession(); int result = 0;
	      try {
	         result = ss.delete(MAPPER+"deleteItem", id);
	         if(result > 0) ss.commit();
	         else ss.rollback();
	      } finally {
	         ss.close();
	      }
	      return result;
	   }
   
   public Items getItem(String code) {
	   SqlSession ss = this.getSession(); Items dto;
	   try {
		   dto = ss.selectOne(MAPPER+"getItem", code);
	   }finally {
		   ss.close();
	   }
	   return dto;
   }
   
   public int getTotalItems() {
	   SqlSession ss = this.getSession(); Integer count = 0;
	   try {
		   count = ss.selectOne(MAPPER+"getTotalItems");
		   if(count == null) count = 0;
	   }finally {
		   ss.close();
	   }
	   return count;
   }
   
   public List<Items> getItems(FromTo ft){
	   SqlSession ss = this.getSession(); List<Items> list;
	   try {
		   list = ss.selectList(MAPPER+"getItems", ft);
	   }finally {
		   ss.close();
	   }
	   return list;
   }
   
   public int putItem(Items item) {
		SqlSession ss = this.getSession(); int result = 0;
		try {
			result = ss.insert(MAPPER+"putItem", item);
			if(result > 0) ss.commit();
			else ss.rollback();
		}finally {
			ss.close();
		}
		return result;
	}
   
   public String getCode(String code) { //상품코드 중복 검사에 사용되는 매퍼의 쿼리를 호출하는 메소드
	   SqlSession ss = this.getSession(); String result;
	   try {
		   result = ss.selectOne(this.MAPPER+"getCode", code); //검색결과가 1건 이므로 selectOne()사용
	   }finally {
		   ss.close();
	   }
	   return result;
   }
   
   public List<MadeIn> getNations() {
      SqlSession ss = this.getSession(); List<MadeIn> list = null;
      try {
         list = ss.selectList(this.MAPPER+"getNations");
      } finally {
         ss.close();
      }
      return list;
   }
   
   private SqlSession getSession() {
      String config = "mybatisConfig.xml";//SqlSession을 생성하기 위해 데이터베이스 정보가 필요하고, 
                     //데이터베이스 정보를 XML파일에 작성한다. 따라서, SqlSession을 생성할 때
                     //XML파일에서 데이터베이스 정보를 불러온다.
      InputStream is = null;//파일을 불러올 때 필요한 객체(InputStream) 선언
      try {
         is = Resources.getResourceAsStream(config);//XML파일을 연다.
      }catch(Exception e) {
         System.out.println("myBatis환경설정 파일이 없거나 잘못 작성되었음!");
      }
      SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
      SqlSessionFactory factory = builder.build(is);
      SqlSession ss = factory.openSession();//SqlSession 인스턴스 생성!!!
      return ss;
   }
}
