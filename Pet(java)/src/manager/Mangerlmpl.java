package manager;

import java.util.Comparator;
import java.util.List;

public class Mangerlmpl implements Manger {
	
	MangerDAO dao = new MangerDAO();

	@Override
	public String print() {
		
		List<MangerDTO> list = dao.selectAll();
		
		String result = String.format("%s\t %s\t %s\t %s\t %s\t %s\t %s\t %s\t %s\t %s\n",
				"회원번호", "목적", "소유주 이름", "동물 이름", "종류", "성별","구분", "무게", "입실", "퇴실");
		
		for(int i = 0; i < list.size(); i++) {
			
			MangerDTO dto = list.get(i);
			result += dto.toString() + "\n";
		}
		return result;
	}

	@Override
	public String searchCode(int num) {
		
		MangerDTO dto = dao.searchCode(num);
		String result = String.format("%s\t %s\t %s\t %s\t %s\t %s\t %s\t %s\t %s\t %s\n",
				"회원번호", "목적", "소유주 이름", "동물 이름", "종류", "성별","구분", "무게", "입실", "퇴실");
		if(dto != null) {
			
			result += dto.toString();
		}	
		
		return result;
	}
	
	@Override
	public String ascCode(int num) {
		
		List<MangerDTO> list = dao.ascCode(num);
		String result = String.format("%s\t %s\t %s\t %s\t %s\t %s\t %s\t %s\t %s\t %s\n",
				"회원번호", "목적", "소유주 이름", "동물 이름", "종류", "성별","구분", "무게", "입실", "퇴실");
            for(int i = 0; i < list.size(); i++) {
			
			MangerDTO dto = list.get(i);
			result += dto.toString() + "\n";
		}
	
		return result;
		
	}

	@Override
	public String descName(String name) {
		
		List<MangerDTO> list = dao.descName(name);
		String result = String.format("%s\t %s\t %s\t %s\t %s\t %s\t %s\t %s\t %s\t %s\n",
				"회원번호", "목적", "소유주 이름", "동물 이름", "종류", "성별","구분", "무게", "입실", "퇴실");
            for(int i = 0; i < list.size(); i++) {
			
			MangerDTO dto = list.get(i);
			result += dto.toString() + "\n";
		}
		
		return result;
	}
	
	public String searchName(String name) {
		
		List<MangerDTO> list = dao.selectName(name);
		String result = String.format("%s\t %s\t %s\t %s\t %s\t %s\t %s\t %s\t %s\t %s\n",
				"회원번호", "목적", "소유주 이름", "동물 이름", "종류", "성별","구분", "무게", "입실", "퇴실");
            for(int i = 0; i < list.size(); i++) {
			
			MangerDTO dto = list.get(i);
			result += dto.toString() + "\n";
		}
		
		return result;
	}
		
}
