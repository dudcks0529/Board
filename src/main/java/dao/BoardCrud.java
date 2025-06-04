package dao;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import bbs.BoardDTO;
import notice.FromTo;

public class BoardCrud {
   private final String MAPPER="bbsMapper.";//매퍼의 이름을 선언한다.
   
   public int updateBoard(BoardDTO dto) {
	   SqlSession ss = this.getSession(); int result = 0;
	   try {
		   result = ss.update(MAPPER + "updateBoard", dto);
		   if(result > 0) ss.commit();
		   else ss.rollback();
	   }finally {
		   ss.close();
	   }
	   return result;
   }
   
   public int deleteBoard(Integer no)	{
	   SqlSession ss = this.getSession(); int result = 0;
	   try {
		   result = ss.delete(MAPPER + "deleteBoard", no);
		   if(result > 0) ss.commit();
		   else ss.rollback();
	   }finally {
		   ss.close();
	   }
	   return result;
   }
   
   public BoardDTO getBoard(Integer no) {
	   SqlSession ss = this.getSession(); BoardDTO dto = null;
	   try {
		   dto = ss.selectOne(MAPPER + "getBoard", no);
	   }finally {
		   ss.close();
	   }
	   return dto;
   }
   
   public Integer getTotalCount() {
	   SqlSession ss = this.getSession(); Integer result = 0;
	   try {
		   result = ss.selectOne(MAPPER + "getTotalCount");
		   if(result > 0) ss.commit();
		   else ss.rollback();
	   }finally {
		   ss.close();
	   }
	   return result;
   }
   
   public List<BoardDTO> getBoardList(FromTo ft){
	   SqlSession ss = this.getSession(); List<BoardDTO> list = null;
	   try {
		   list = ss.selectList(MAPPER + "getBoardList", ft);
	   }finally {
		   ss.close();
	   }
	   return list;
   }
   
   public int putBBS(BoardDTO board) {
      SqlSession ss = this.getSession(); int result = 0;
      try {
         result = ss.insert(MAPPER+"putBBS", board);
         if(result > 0) ss.commit();
         else ss.rollback();
      } finally {
         ss.close();
      }
      return result;
   }
   
   public Integer getMaxSeq() {
      SqlSession ss = this.getSession(); Integer result = 0;
      try {
         result = ss.selectOne(MAPPER+"getMaxSeq");
         if(result == null) result = 0;
      } finally {
         ss.close();
      }
      return result;
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
}//자유게시판용 DAO
