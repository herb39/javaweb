package study.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
			sql = "select * from login where mid=? and pwd=?";
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
	public void setPointPlus(String mid) {
		try {
			sql = "update login set point = point + 10, lastDate = now(), todayCount=? where mid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}	
	}
	
}
