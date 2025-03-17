package manager;

import java.util.List;

import frame.CustomerDTO;
import hair.HairDAO;
import hair.HairDTO;

public class MangerDTO {
	private String service, petname, name, inday, outday , dogSize, weight;
	private int kind, num, petGender ;
	
	public MangerDTO() {
		
	}
	
	
	public MangerDTO(String service, String petname, String name, String inday, String outday, String dogSize,
			String weight, int kind, int num, int petGender) {
		super();
		this.service = service;
		this.petname = petname;
		this.name = name;
		this.inday = inday;
		this.outday = outday;
		this.dogSize = dogSize;
		this.weight = weight;
		this.kind = kind;
		this.num = num;
		this.petGender = petGender;
	}

	public MangerDTO(int num,CustomerDTO dto,String inDate, String outDate, String dogSize, String weight) {
		this.num = num;
		this.name = dto.getName();
		this.petname = dto.getPetName();
		this.kind = dto.getKind();
		this.petGender = dto.getPetGender();
		this.service = dto.getService();
		this.inday = inDate;
		this.outday = outDate;
		this.dogSize = dogSize;
		this.weight = weight;
	}
	
	
	public MangerDTO(HairDTO hairdto) {
		this.service = hairdto.getService();
		this.petname = hairdto.getPetName();
		this.name = hairdto.getName();
		this.inday = hairdto.getInDate();
		this.outday = hairdto.getOutDate();
		this.dogSize = hairdto.getDogSize();
		this.weight = hairdto.getWeight();
		this.kind = hairdto.getKind();
		this.num = hairdto.getNum();
		this.petGender = hairdto.getPetGender();
	}

	@Override
	public String toString() {
		
		String s_petGender = "";
		
		if(this.petGender == 1) {
			s_petGender = "수컷";
			
		}else if (this.petGender == 2) {
			s_petGender = "암컷";
		}
		
		String str= String.format("%s\t %s\t %s\t %s\t %s\t %s\t %s\t %s\t %s\t %s\t ",
				num, service, name, petname, kind, s_petGender, dogSize, weight, inday, outday);
		return str;
	}
	
	public String getInday() {
		return inday;
	}

	public void setInday(String inday) {
		this.inday = inday;
	}
	
	public String getOutday() {
		return inday;
	}

	public void setOutday(String outday) {
		this.outday = outday;
	}
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getPetname() {
		return petname;
	}

	public void setPetname(String petname) {
		this.petname = petname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String DogSize() {
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


	
	

}
