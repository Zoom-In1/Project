package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import hair.HairDTO;

public class MangerDAO {
	
	public MangerDAO() {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		
		}
		
	}
	
	 public Connection getConnection() {
			

			Connection conn = null;
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "C##dbexam";
			String password = "m1234";

			try {
				conn = DriverManager.getConnection(url, user, password);
				
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
			// 접속 성공 : Connection 객체, 접속 실패 : null
			return conn;	
	 }
	 
	 public int insertManger(HairDTO hairdto) {
		 int result = 0;
		 int num = hairdto.getNum();
		 String service = hairdto.getService();
		 String name = hairdto.getName();
		 String petName = hairdto.getPetName();
		 String inday = hairdto.getInDate();
		 String outday = hairdto.getOutDate();
		 String dogSize = hairdto.getDogSize();
		 String weight = hairdto.getWeight();
		 int kind = hairdto.getKind();
		 int petGender = hairdto.getPetGender();
		 Connection conn = getConnection();
		 String sql = "insert into manager values (?,?,?,?,?,?,?,?,?,?)";
		 PreparedStatement pstmt = null;
		 try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, service);
			pstmt.setString(3, petName);
			pstmt.setString(4, name);
			pstmt.setString(5, inday);
			pstmt.setString(6, outday);
			pstmt.setString(7, dogSize);
			pstmt.setString(8, weight);
			pstmt.setInt(9, kind);
			pstmt.setInt(10, petGender);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return result;
	 }
	 
	 public List<MangerDTO> selectAll() {
		 String sql = "select * from manager";
		 List<MangerDTO> list = new ArrayList<MangerDTO>();
		 
		 Connection conn = getConnection();
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 
		 try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				 
				MangerDTO dto = new MangerDTO();
				dto.setNum(rs.getInt("num"));
				dto.setService(rs.getString("service"));
				dto.setPetname(rs.getString("petName"));
				dto.setName(rs.getString("name"));
				dto.setInday(rs.getString("indate"));
				dto.setOutday(rs.getString("outdate"));
				dto.setKind(rs.getInt("kind"));
				dto.setWeight(rs.getString("weight"));
				dto.setDogSize(rs.getString("dogsize"));
				dto.setPetGender(rs.getInt("petgender"));
				
				list.add(dto);
			
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			
				try {
					if(rs != null) rs.close();
					if(pstmt != null)pstmt.close();
					if(conn != null)conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		 
		 
		 return list;
	 }
	 
	 public MangerDTO searchCode(int num) {
		 String sql = "select * from manager where num = ?";
		 MangerDTO dto = null;
		 
		 Connection conn = getConnection();
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 
		 try {
		 
		 pstmt = conn.prepareStatement(sql);
		 pstmt.setInt(1, num);
		 rs = pstmt.executeQuery();
		 
		 while(rs.next()) {
			 
			dto = new MangerDTO();
			dto.setNum(rs.getInt("num"));
			dto.setService(rs.getString("service"));
			dto.setPetname(rs.getString("petName"));
			dto.setName(rs.getString("name"));
			dto.setInday(rs.getString("indate"));
			dto.setOutday(rs.getString("outdate"));
			dto.setKind(rs.getInt("kind"));
			dto.setWeight(rs.getString("weight"));
			dto.setDogSize(rs.getString("dogsize"));
			dto.setPetGender(rs.getInt("petgender"));
				 
		 }
		 
		 }catch (SQLException e) {
			 e.printStackTrace();
		}finally {
			try {
				
				if(rs != null)rs.close();
				if(pstmt != null)pstmt.close();
				if(conn != null)conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		
		}
		 
		 return dto;
	 }
	 
	 public int delete(int num) {
		 
		 String sql = "delete manager where num = ?";
		 int result = 0;
		 
		 Connection conn = getConnection();
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 
		 try {
			 // 2)SQL 작업
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			 
			// 1. SQL문 전송 / 2. 응답 기다림 / 3. 전달된 값 리턴
			// => delete 경우, 처리된 데이터 갯수 저장
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			// 3) 접속 끊기
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null)conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		 return result;	
		 
	 }
	 
	public List<MangerDTO> ascCode(int num) {
		
		String sql = "select * from manager order by ? asc";
		List<MangerDTO> list = new ArrayList<MangerDTO>();
		
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MangerDTO dto = new MangerDTO();
				dto.setNum(rs.getInt("num"));
				dto.setService(rs.getString("service"));
				dto.setPetname(rs.getString("petName"));
				dto.setName(rs.getString("name"));
				dto.setInday(rs.getString("indate"));
				dto.setOutday(rs.getString("outdate"));
				dto.setKind(rs.getInt("kind"));
				dto.setWeight(rs.getString("weight"));
				dto.setDogSize(rs.getString("dogsize"));
				dto.setPetGender(rs.getInt("petgender"));
				
				list.add(dto);
				
				Comparator<MangerDTO> comparator = new Comparator<MangerDTO>() {

					@Override
					public int compare(MangerDTO dto1, MangerDTO dto2) {
						
						return dto1.getNum() > dto2.getNum()? 1 : -1;
						//return dto1.getNum().compareTo(dto2.getCode());
					}
				};
				
				list.sort(comparator);
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			
				try {
					if(rs != null) rs.close();
					if(pstmt != null)pstmt.close();
					if(conn != null)conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		 
		 
		 return list;
	 }
	
public List<MangerDTO> descName(String name) {
		
		String sql = "select * from manager order by ? desc";
		List<MangerDTO> list = new ArrayList<MangerDTO>();
		
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MangerDTO dto = new MangerDTO();
				dto.setNum(rs.getInt("num"));
				dto.setService(rs.getString("service"));
				dto.setPetname(rs.getString("petName"));
				dto.setName(rs.getString("name"));
				dto.setInday(rs.getString("indate"));
				dto.setOutday(rs.getString("outdate"));
				dto.setKind(rs.getInt("kind"));
				dto.setWeight(rs.getString("weight"));
				dto.setDogSize(rs.getString("dogsize"));
				dto.setPetGender(rs.getInt("petgender"));
				
				list.add(dto);
				
				Comparator<MangerDTO> comparator = new Comparator<MangerDTO>() {

					@Override
					public int compare(MangerDTO dto1, MangerDTO dto2) {
						
						return dto2.getName().compareTo(dto1.getName());
					}
				};
				
				list.sort(comparator);
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			
				try {
					if(rs != null) rs.close();
					if(pstmt != null)pstmt.close();
					if(conn != null)conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		 
		 
		 return list;
	 }

public  int Inupdate(String day, int num) {
	
	String sql = "update manager set indate = ? where num = ?";
	int result = 0;
	 
	 Connection conn = getConnection();
	 PreparedStatement pstmt = null;
	 ResultSet rs = null;
	 
	 try {
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, day);
		pstmt.setInt(2, num);
		
	   result = pstmt.executeUpdate();
			
			
	} catch (SQLException e) {
		
		e.printStackTrace();
	}finally {
		
			try {
				
				if(pstmt != null)pstmt.close();
				if(conn != null)conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	 
	 
	 return result;
}

public  int Outupdate(String day, int num) {
	
	String sql = "update manager set outdate = ? where num = ?";
	int result = 0;
	 
	 Connection conn = getConnection();
	 PreparedStatement pstmt = null;
	 ResultSet rs = null;
	 
	 try {
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, day);
		pstmt.setInt(2, num);
		
	   result = pstmt.executeUpdate();
			
			
	} catch (SQLException e) {
		
		e.printStackTrace();
	}finally {
		
			try {
				
				if(pstmt != null)pstmt.close();
				if(conn != null)conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	 
	 return result;
}
				 

public List<MangerDTO> selectName(String name) {
	 String sql = "select * from manager where name = ? ";
	 List<MangerDTO> list = new ArrayList<MangerDTO>();
	 
	 Connection conn = getConnection();
	 PreparedStatement pstmt = null;
	 ResultSet rs = null;
	 
	 try {
		 pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
		while(rs.next()) {
			 
			MangerDTO dto = new MangerDTO();
			dto.setNum(rs.getInt("num"));
			dto.setService(rs.getString("service"));
			dto.setPetname(rs.getString("petName"));
			dto.setName(rs.getString("name"));
			dto.setInday(rs.getString("indate"));
			dto.setOutday(rs.getString("outdate"));
			dto.setKind(rs.getInt("kind"));
			dto.setWeight(rs.getString("weight"));
			dto.setDogSize(rs.getString("dogsize"));
			dto.setPetGender(rs.getInt("petgender"));
			
			list.add(dto);
		
			
		}
	} catch (SQLException e) {
		
		e.printStackTrace();
	}finally {
		
			try {
				if(rs != null) rs.close();
				if(pstmt != null)pstmt.close();
				if(conn != null)conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	 
	 
	 return list;
}
public int delete(String name) {
    
    String sql = "delete manager where name = ?";
    int result = 0;
    
    Connection conn = getConnection();
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
    try {
       // 2)SQL 작업
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, name);
      
       
      // 1. SQL문 전송 / 2. 응답 기다림 / 3. 전달된 값 리턴
      // => delete 경우, 처리된 데이터 갯수 저장
      
      result = pstmt.executeUpdate();
   } catch (SQLException e) {
      
      e.printStackTrace();
   }finally {
      // 3) 접속 끊기
      try {
         if(pstmt != null) pstmt.close();
         if(conn != null)conn.close();
         
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
    return result;   
    
}
}