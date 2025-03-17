package frame;

import java.util.ArrayList;
import java.util.List;

public class functionImpl implements Function {
	CustomerDAO dao = new CustomerDAO();
	@Override
	public CustomerDTO passInfo(String name) {
		CustomerDTO dto = dao.selectCustomer(name);
		
		return dto;
	}
	@Override
	public String title() {
		String str = String.format("%s\t %s\t %s\t %s\t %s\t %s\t %s\t %s\t %s\t %s\n","회원번호", "목적", "소유주 이름", "동물 이름", "종류", "성별","견종", "무게", "입실", "퇴실");
		
		return str;
	}
	
	@Override
	public List<String> kinds(List<Integer> list) {
		List<Integer> i_kinds = new ArrayList<Integer>();
		List<String> kinds = new ArrayList<String>();
		for(int i = 0 ; i<list.size(); i++) {
			if(list.get(i)==1) {
				kinds.add("강아지");
			}else if(list.get(i)==2) {
				kinds.add("고양이");
			}else if(list.get(i)==3) {
				kinds.add("기타");
			}
			
			
		}
		return kinds;
	}
	@Override
	public List<String> genders(List<Integer> list) {
		List<Integer> i_genders = new ArrayList<Integer>();
		List<String> genders = new ArrayList<String>();
		for(int i = 0 ; i<list.size() ; i++) {
			if(list.get(i)==1) {
				genders.add("수컷");
			}else if(list.get(i)==2) {
				genders.add("암컷");
			}
		}
		return genders;
	}
	
}
