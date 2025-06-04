package dao;

import java.io.InputStream;
import java.util.ArrayList;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import login.LoginDTO;
import notice.FromTo;
import notice.NoticeDTO;
import user.UserInfo;

public class Crud {//매퍼의 퀴리를 호출
	private final String MAPPER_NAME = "noticeMapper";//매퍼의 이름을 선언한다.
	
	public Integer insertUser(UserInfo dto) {
		SqlSession ss = this.getSession(); Integer result;
		try {
			result = ss.insert(MAPPER_NAME+".putUser", dto);
			if(result > 0) ss.commit();
			else ss.rollback();
		}finally {
			ss.close();
		}
		return result;
	}
	
	public String getIdCheck(String id) {
		SqlSession ss = this.getSession(); String result= null;
		try {
			result = ss.selectOne(MAPPER_NAME+".getId", id);
		}finally {
			ss.close();
		}
		return result;
	}
	
	public String getIdByLogin(LoginDTO dto) {
		SqlSession ss = this.getSession(); String result;
		try {
			result = ss.selectOne(MAPPER_NAME+".getUserIdByLogin",dto);
		}finally {
			ss.close();
		}
		return result;
	}
	
	public Integer getNoticeCount() {
		SqlSession ss = this.getSession(); Integer result;
		try {
			result = ss.selectOne(MAPPER_NAME+".getNoticeCount");
			if(result == null) result = 0;
		}finally {
			ss.close();
		}
		return result;
	}
	
	public ArrayList<NoticeDTO> getNoticeList(FromTo ft) {
		ArrayList<NoticeDTO> list = new ArrayList<NoticeDTO>();
		SqlSession ss = this.getSession();
		try {
			list = (ArrayList)ss.selectList(MAPPER_NAME+".getNoticeList", ft);
		}finally {
			ss.close();
		}
		return list;
	}
	
	public int getMaxNoticeNum() {
		SqlSession ss = this.getSession(); Integer result = 0;
		try {
			result = ss.selectOne(MAPPER_NAME+".getMaxNotice");
			//조회결과가 1행인 경우 사용하는 메소드 : selectOne()
			//조회결과가 n행인 경우 사용하는 메소드 : selectList()
			if(result == null) { //조회결과가 없을 때
				result = 0;
			}
		}finally {
			ss.close();
		}
		return result;
	}
	
	public int insertNotice(NoticeDTO dto) {
		SqlSession ss = this.getSession(); int result = -1;
		try {
			result = ss.insert(MAPPER_NAME+".putNotice",dto);
			if(result > 0) {
				ss.commit();
			}else {
				ss.rollback();
			}
		}finally {
			ss.close();		}
		return result;
	}
	
	public int updateNoitce(NoticeDTO dto) {
		SqlSession ss = this.getSession(); int result = -1;
		try {
			result = ss.update(MAPPER_NAME+".updateMapper", dto);
			if(result > 0) { //업데이트 성공
				ss.commit();
			} else { //업데이트 실패
				ss.rollback();
			}
		}finally {
			ss.close();
		}
		return result;
	}
	
	public int deleteNotice(Integer no) {
		//메퍼 파일의 쿼리를 호출하는 객체(SqlSession)를 생성한다
		SqlSession ss = this. getSession();
		int result = -1;
		try {
			//delete는 int리턴 함수
			result = ss.delete(MAPPER_NAME+".deleteNotice", no); //no는 deleteNotice안 쿼리문에 필요한 num
			if(result > 0) { //삭제가 성공
				ss.commit();
			}else { //삭제 실패
				ss.rollback();
			}
		}finally {
			ss.close();
		}
		return result;
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
