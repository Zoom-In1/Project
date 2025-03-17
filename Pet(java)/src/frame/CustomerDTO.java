package frame;

public class CustomerDTO {
	private int num;// 1씩 자동으로 증가하는 테이블 번호
	private String name;
	private String petName;
	private int kind; //(강아지:1 고양이:2 기타:3)
	private int petGender;//(수컷:1 암컷:2)
	private String service;
	
	public CustomerDTO() {
		
	}

	public CustomerDTO(String name, String petName, int kind, int petGender, String service) {
		super();
		this.name = name;
		this.petName = petName;
		this.kind = kind;
		this.petGender = petGender;
		this.service = service;
	}

	@Override
	public String toString() {
		String s_kind = "";
		if(kind==1)s_kind = "강아지";
		else if(kind==2)s_kind = "고양이";
		else if(kind==3)s_kind = "기타";
		
		String s_petGender = "";
		if(petGender==1)s_petGender ="수컷";
		else s_petGender ="암컷";
		
		String str = String.format("%s\t%s\t%s\t%s\t%s\t%s",num,name,petName,s_kind,s_petGender,service);
		return str;
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
	
	
	
	
}
