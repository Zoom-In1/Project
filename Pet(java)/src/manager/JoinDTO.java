package manager;

public class JoinDTO {
	private String name;
	private String birthday;
	private String num;
	private String id;
	private String pw;
	private String pwcheck;
	private String tel;
	private String major;
	
	public JoinDTO() {
		
	}

	public JoinDTO(String name, String birthday, String num, String id, String pw, String pwcheck, String tel,
			String major) {
		super();
		this.name = name;
		this.birthday = birthday;
		this.num = num;
		this.id = id;
		this.pw = pw;
		this.pwcheck = pwcheck;
		this.tel = tel;
		this.major = major;
	}

	@Override
	public String toString() {
		return "JoinDTO [name=" + name + ", birthday=" + birthday + ", num=" + num + ", id=" + id + ", pw=" + pw
				+ ", pwcheck=" + pwcheck + ", tel=" + tel + ", major=" + major + "]";
	}

	// getter & setter
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getPwcheck() {
		return pwcheck;
	}

	public void setPwcheck(String pwcheck) {
		this.pwcheck = pwcheck;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}
	
	
	
	
}
