package hair;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HairDAO {
	public HairDAO() {
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
	
	public int insertHair(int num,String name,String petName, int kind, int petGender, String service,String indate,String outdate,String dogSize,String weight) {
		Connection conn = getConnection();
		String sql = "insert into pethair values(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,num);
			pstmt.setString(2, name);
			pstmt.setString(3, petName);
			pstmt.setInt(4,kind);
			pstmt.setInt(5,petGender);
			pstmt.setString(6,service);
			pstmt.setString(7, indate);
			pstmt.setString(8, outdate);
			pstmt.setString(9, dogSize);
			pstmt.setString(10, weight);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!= null)conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public List<HairDTO> selectHairAll(){
		Connection conn = getConnection();
		List<HairDTO> list = new ArrayList<HairDTO>();
		String sql = "select * from pethair";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				HairDTO hairdto = new HairDTO();
				hairdto.setNum(rs.getInt("num"));
				hairdto.setName(rs.getString("name"));
				hairdto.setPetName(rs.getString("petname"));
				hairdto.setKind(rs.getInt("kind"));
				hairdto.setPetGender(rs.getInt("petGender"));
				hairdto.setService(rs.getString("service"));
				hairdto.setInDate(rs.getString("indate"));
				hairdto.setOutDate(rs.getString("outdate"));
				hairdto.setDogSize(rs.getString("dogsize"));
				hairdto.setWeight(rs.getString("weight"));
				
				list.add(hairdto);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	return list;
	}
}
