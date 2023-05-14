package guest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GuestDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	String sql = "";
	GuestVO vo = null;
	
	
	public GuestDAO() {
		String url = "jdbc:mysql://localhost:3306/javaweb";
		String user = "root";
		String password = "1234";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 검색 실패");
		} catch (SQLException e) {
			System.out.println("데이터베이스 연동 실패");
		}		
	}
	
	// 사용된 객체 소멸(pstmt, rs)
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
				pstmt.close();
			} catch (SQLException e) {}
		}
	}

	
	// 방명록 전체 보기
	public ArrayList<GuestVO> getGuestList(int startIndexNo, int pageSize) {
		ArrayList<GuestVO> vos = new ArrayList<>();
		try {
			sql = "select * from guest order by idx desc limit ?, ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startIndexNo);
			pstmt.setInt(2, pageSize);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				vo = new GuestVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setName(rs.getString("name"));
				vo.setEmail(rs.getString("email"));
				vo.setHomePage(rs.getString("homePage"));
				vo.setContent(rs.getString("content"));
				vo.setVisitDate(rs.getString("visitDate"));
				vo.setHostIp(rs.getString("hostIp"));
				
				vos.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vos;
	}

	
	// 방명록 작성
	public int setGuestInputOk(GuestVO vo) {
		int res = 0;
		try {
			sql = "insert into guest values (default,?,?,?,?,default,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getHomePage());
			pstmt.setString(5, vo.getHostIp());
			
			pstmt.executeUpdate();
			res = 1;
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}

	
	// 총 레코드 건수 ?
	public int getTotRecCnt() {
		int totRecCnt = 0;
		try {
			sql = "select count(idx) as cnt from guest";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			totRecCnt = rs.getInt("cnt");
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return totRecCnt;
	}

	
	// 방명록 자료 삭제
	public int setGuestDelete(int idx) {
		int res = 0;
		try {
			sql = "delete from guest where idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
			res = 1;
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}

	public int getList(String mid, String name, String nickName) {
		int res = 0;
        try {
            sql = "select count(idx) as cnt from guest where name in(?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, mid);
            pstmt.setString(2, name);
            pstmt.setString(3, nickName);
            rs = pstmt.executeQuery();
            
            rs.next();
            res = rs.getInt("cnt");
            
        } catch (SQLException e) {
            System.out.println("SQL 오류 : " + e.getMessage());
        } finally {
            rsClose();
        }
        return res;
	}

	// 페이징 처리
	public ArrayList<GuestVO> getMemberGuestList(int startIndexNo, int pageSize, String mid, String name, String nickName) {
		ArrayList<GuestVO> vos = new ArrayList<>();
        try {
            sql = "select * from guest where name in(?, ?, ?) order by idx desc limit ?,?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, mid);
            pstmt.setString(2, name);
            pstmt.setString(3, nickName);            
            pstmt.setInt(4, startIndexNo);
            pstmt.setInt(5, pageSize);
            rs = pstmt.executeQuery();
            
            while(rs.next()) {
                vo = new GuestVO();
                vo.setIdx(rs.getInt("idx"));
                vo.setName(rs.getString("name"));
                vo.setEmail(rs.getString("email"));
                vo.setHomePage(rs.getString("homePage"));
                vo.setContent(rs.getString("content"));
                vo.setVisitDate(rs.getString("visitDate"));
                vo.setHostIp(rs.getString("hostip"));
                
                vos.add(vo);
            }
        } catch (SQLException e) {
            System.out.println("SQL 오류 : " + e.getMessage());
        } finally {
            rsClose();
        }
        return vos;
	}	
}
