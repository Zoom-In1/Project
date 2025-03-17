package frame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
	public CustomerDAO() {
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
			//e.printStackTrace();
		}
		return conn;
	}
	
	// insert
	public int insertCustomer(String name, String petName,int kind, int petGender, String service) {
		int result = 0;
		Connection conn = getConnection();
		String sql = "insert into petradice values(num.nextval,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, petName);
			pstmt.setInt(3, kind);
			pstmt.setInt(4, petGender);
			pstmt.setString(5, service);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	// select
	public CustomerDTO selectCustomer(String name) {
		CustomerDTO dto = new CustomerDTO();
		Connection conn = getConnection();
		String sql = "select * from petradice where name = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs  = pstmt.executeQuery();
			
			while(rs.next()) {
				dto.setNum(rs.getInt("num"));
				dto.setName(rs.getString("name"));
				dto.setPetName(rs.getString("petname"));
				dto.setKind(rs.getInt("kind"));
				dto.setPetGender(rs.getInt("petgender"));
				dto.setService(rs.getString("service"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		return dto;
	}
	
	// selectAll
	public List<CustomerDTO> selectAll(){
		List<CustomerDTO> list = new ArrayList<CustomerDTO>();
		Connection conn = getConnection();
		String sql = "select * from petradice order by num asc";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CustomerDTO dto = new CustomerDTO();
				dto.setNum(rs.getInt("num"));
				dto.setName(rs.getString("name"));
				dto.setPetName(rs.getString("petname"));
				dto.setKind(rs.getInt("kind"));
				dto.setPetGender(rs.getInt("petgender"));
				dto.setService(rs.getString("service"));
				
				list.add(dto);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return list;
	}
	
	// selectName
	public List<CustomerDTO> selectName(String name){
		List<CustomerDTO> list = new ArrayList<CustomerDTO>();
		Connection conn = getConnection();
		String sql = "select * from petradice where name = ? order by num asc";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CustomerDTO dto = new CustomerDTO();
				dto.setNum(rs.getInt("num"));
				dto.setName(rs.getString("name"));
				dto.setPetName(rs.getString("petname"));
				dto.setKind(rs.getInt("kind"));
				dto.setPetGender(rs.getInt("petgender"));
				dto.setService(rs.getString("service"));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	// selectNum

	
}
