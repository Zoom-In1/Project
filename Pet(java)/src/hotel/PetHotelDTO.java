package hotel;

import frame.CustomerDTO;

public class PetHotelDTO {
	private int num;
	private String name;
	private String petName;
	private int kind; //(강아지:1 고양이:2 기타:3)
	private int petGender; //(수컷:1 암컷:2)
	private String service;
	private String Check_In;
	private String Check_Out;

	
	public PetHotelDTO() {
		
	}


	public PetHotelDTO(int num, String name, String petName, int kind, int petGender, String service, String check_In,
			String check_Out) {
		super();
		this.num = num;
		this.name = name;
		this.petName = petName;
		this.kind = kind;
		this.petGender = petGender;
		this.service = service;
		Check_In = check_In;
		Check_Out = check_Out;
	}


	@Override
	public String toString() {
		String str = "";
		str = String.format("주인이름 : %s\t동물이름 : %s\t종류 : %s\t성별 : %s\t서비스 : %s\t입실시간 : %s\t 퇴실시간 : %s\t\n");
		
		return str;
	}


	// getter & setter
	
	public int getNum() {
		return num;
	}


	public void setNum(int num) {
		this.num = num;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPetName() {
		return petName;
	}


	public void setPetName(String petName) {
		this.petName = petName;
	}


	public int getKind() {
		return kind;
	}


	public void setKind(int kind) {
		this.kind = kind;
	}


	public int getPetGender() {
		return petGender;
	}


	public void setPetGender(int petGender) {
		this.petGender = petGender;
	}


	public String getService() {
		return service;
	}


	public void setService(String service) {
		this.service = service;
	}


	public String getCheck_In() {
		return Check_In;
	}


	public void setCheck_In(String check_In) {
		Check_In = check_In;
	}


	public String getCheck_Out() {
		return Check_Out;
	}


	public void setCheck_Out(String check_Out) {
		Check_Out = check_Out;
	}
	
	
	
	
}
