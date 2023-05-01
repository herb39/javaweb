package study2.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

// DB에 관련된 것만!!
public class LoginDAO {
	
	// 필드
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	String sql = "";
	
	private LoginVO vo = new LoginVO();

	
	// 생성자
	public LoginDAO() {
		String url = "jdbc:mysql://localhost:3306/javaweb";
		String user = "root";
		String password = "1234";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 검색 실패");
		} catch (SQLException e) {
			System.out.println("Database 연동 실패");			
		}
	}
	
	
	// 메서드
	// 사용한 객체 반납(해제)
	public void pstmtClose() {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {}
		}
	}
	
	public void rsClose() {
		if (rs != null) {
			try {
				rs.close();
				pstmtClose();
			} catch (SQLException e) {}
		}
	}
	

	// 로그인 체크
	public LoginVO getLoginCheck(String mid, String pwd) {
		vo = new LoginVO();
		
		try {
			sql = "select * from login where mid = ? and pwd= ?";
			pstmt = conn.prepareStatement(sql);
			// (?번째 물음표, 넣을값)
			pstmt.setString(1, mid);
			pstmt.setString(2, pwd);
			// query string select 나머지 update
			rs = pstmt.executeQuery();
			// rs.next() 값이 있으면 true
			if (rs.next()) {
				vo.setIdx(rs.getInt("idx"));
				vo.setMid(rs.getString("mid"));
				vo.setPwd(rs.getString("pwd"));
				vo.setName(rs.getString("name"));
				vo.setPoint(rs.getInt("point"));
				vo.setLastDate(rs.getString("lastDate"));
				vo.setTodayCount(rs.getInt("todayCount"));
			}			
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}		
		return vo;
	}

	// 방문 포인트 증가
	public void setPointPlus(String mid, int point, int todayCount) {
		try {
			sql = "update login set point = ?, lastDate = now(), todayCount = ? where mid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, point);
			pstmt.setInt(2, todayCount);
			pstmt.setString(3, mid);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
	}

	// 아이디 검색 (아이디 중복 체크)	
	public LoginVO getMidCheck(String mid) {
		LoginVO vo = new LoginVO();
		try {
			sql = "select * from login where mid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				vo.setIdx(rs.getInt("idx"));
				vo.setMid(rs.getString("mid"));
				vo.setPwd(rs.getString("pwd"));
				vo.setName(rs.getString("name"));
				vo.setPoint(rs.getInt("point"));
				vo.setLastDate(rs.getString("lastDate"));
				vo.setTodayCount(rs.getInt("todayCount"));
				vo.setFailCount(rs.getInt("failCount"));
				
//				Date today = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String strToday = sdf.format(today);        
//        String lastDate = vo.getLastDate();
//        if(!strToday.equals(lastDate.substring(0,10))) vo.setTodayCount(0);
				vo.setTodayCount(compareDate(vo.getLastDate(), vo.getTodayCount()));
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vo;
	}

	// 회원가입
	public void setJoinOk(LoginVO vo) {
		try {
			sql = "insert into login values (default,?,?,?,default,default,default,default)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getMid());
			pstmt.setString(2, vo.getPwd());
			pstmt.setString(3, vo.getName());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
	}

	// 전체 회원 조회
	public ArrayList<LoginVO> getLoginList() {
		ArrayList<LoginVO> vos = new ArrayList<>();		
		try {
			sql = "select * from login order by idx desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				vo = new LoginVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setMid(rs.getString("mid"));
				vo.setPwd(rs.getString("pwd"));
				vo.setName(rs.getString("name"));
				vo.setPoint(rs.getInt("point"));
				vo.setLastDate(rs.getString("lastDate"));
				vo.setTodayCount(rs.getInt("todayCount"));
				
//				Date today = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String strToday = sdf.format(today);        
//        String lastDate = vo.getLastDate();
//        if(!strToday.equals(lastDate.substring(0,10))) vo.setTodayCount(0);
				vo.setTodayCount(compareDate(vo.getLastDate(), vo.getTodayCount()));
				
				vos.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vos;
	}

	// 날짜 비교
	private int compareDate(String lastDate, int todayCount) {
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String strToday = sdf.format(today);        
//	 	String lastDate = vo.getLastDate();
		if(!strToday.equals(lastDate.substring(0,10))) todayCount = 0;
	  
		return todayCount;
	}

	// 개인 정보 수정
	public int setUpdateOk(LoginVO vo) {
		int res = 0;
		try {
			sql = "update login set pwd = ?, name = ? where mid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getPwd());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getMid());
			pstmt.executeUpdate();
			res = 1;
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		
		return res;
	}

	// 회원탈퇴 ()
	public int setDeleteOk(String mid) {
		int res = 0;
		try {
			sql = "delete from login where mid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			pstmt.executeUpdate();
			res = 1;
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}


	public void failCount(int failCount, String mid) {
		try {
			sql = "update login set failCount = ? where mid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, failCount);
			pstmt.setString(2, mid);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		
	}

	
}
