package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JoinDAO {
	public JoinDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Connection getConnection() {
		Connection conn = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "C##dbexam";
		String pw = "m1234";
		try {
			conn = DriverManager.getConnection(url, user, pw);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
	// insert 
	public int insertInfo(String name, String birthday, String num,String id, String pw, String pwcheck, String tel, String m) {
		int result = 0;
		String sql = "insert into member values (?,?,?,?,?,?,?,?)";
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, birthday);
			pstmt.setString(3, num);
			pstmt.setString(4, id);
			pstmt.setString(5, pw);
			pstmt.setString(6, pwcheck);
			pstmt.setString(7, tel);
			pstmt.setString(8, m);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	// selectAll
	public List<JoinDTO> selectMemberAll(){
		List<JoinDTO> list = new ArrayList<JoinDTO>();
		String sql = "select * from member";
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				JoinDTO joindto = new JoinDTO();
				joindto.setName(rs.getString("name"));
				joindto.setBirthday(rs.getString("birthday"));
				joindto.setNum(rs.getString("num"));
				joindto.setId(rs.getString("id"));
				joindto.setPw(rs.getString("pw"));
				joindto.setPwcheck(rs.getString("pwcheck"));
				joindto.setTel(rs.getString("tel"));
				joindto.setMajor(rs.getString("major"));
				
				list.add(joindto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	// selectId 
	public JoinDTO selectMemberID(String id) {
		JoinDTO dto = new JoinDTO();
		String sql = "select * from member where id = ?";
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dto.setName(rs.getString("name"));
				dto.setBirthday(rs.getString("birthday"));
				dto.setNum(rs.getString("num"));
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setPwcheck(rs.getString("pwcheck"));
				dto.setTel(rs.getString("tel"));
				dto.setMajor(rs.getString("major"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return dto;
	}
}
