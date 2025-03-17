package hair;

import frame.CustomerDTO;

public class HairDTO {
	private int num;
	private String name;
	private String petName;
	private int kind;
	private int petGender;
	private String service;
	// 추가된 변수
	private String inDate;
	private String outDate;
	private String dogSize;
	private String weight;
	
	public HairDTO() {
		
	}
	
	public HairDTO(int num,CustomerDTO dto,String inDate, String outDate, String dogSize, String weight) {
		this.num = num;
		this.name = dto.getName();
		this.petName = dto.getPetName();
		this.kind = dto.getKind();
		this.petGender = dto.getPetGender();
		this.service = dto.getService();
		this.inDate = inDate;
		this.outDate = outDate;
		this.dogSize = dogSize;
		this.weight = weight;
	}

	

	// getter & setter
	
	@Override
	public String toString() {
		return "HairDTO [num=" + num + ", name=" + name + ", petName=" + petName + ", kind=" + kind + ", petGender="
				+ petGender + ", service=" + service + ", inDate=" + inDate + ", outDate=" + outDate + ", dogSize="
				+ dogSize + ", weight=" + weight + "]" +"\n";
	}

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

	public String getInDate() {
		return inDate;
	}

	public void setInDate(String inDate) {
		this.inDate = inDate;
	}

	public String getOutDate() {
		return outDate;
	}

	public void setOutDate(String outDate) {
		this.outDate = outDate;
	}

	public String getDogSize() {
		return dogSize;
	}

	public void setDogSize(String dogSize) {
		this.dogSize = dogSize;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}
	
	
	
	
}
