package frame;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import hair.HairDAO;
import hair.HairDTO;
import hair.HairMainFrame;
//import hair.PetHairFrame;
import hotel.PetHotelFrame;
import manager.Frame;
import manager.Frame2;


public class PetradiceMainFrame extends JFrame implements ActionListener {
	CustomerDAO dao = new CustomerDAO();
	HairDAO hairdao = new HairDAO();
	Function f = new functionImpl();
	// 컴포넌트 부분
	Container container = getContentPane();
	ImageIcon icon = new ImageIcon("img/다운로드.jpg");

	// 프레임창 위에 프로그램 이름이 들어갈부분
	// North 부분
	JPanel northPanel = new JPanel();
	JLabel pgrTitle = new JLabel(" Petradice ");
	Font titleFont = new Font("휴먼편지체", Font.BOLD | Font.ITALIC, 28);
	// North 부분 끝

	// Center 부분
	JPanel centerPanel = new JPanel();
	JLabel imgArea = new JLabel();
	ImageIcon icon1 = new ImageIcon("img/동물.jpg");
	// Center 부분 끝

	// South 부분
	JPanel southPanel = new JPanel();
	JPanel radioArea = new JPanel();
	JPanel buttonsArea = new JPanel();
	JButton registration = new JButton("예약");
	JButton checkService = new JButton("조회");
	ButtonGroup bbg = new ButtonGroup();
	String[] kind = { "강아지", "고양이", "기타" };
	String[] gender = { "수컷", "암컷" };
	String[] service = { "미용", "호텔" };

	//
	JPanel s_c_Panel = new JPanel();
	JPanel inputInfo = new JPanel(); // 정보 입력부분
	JPanel been1 = new JPanel(); // 빈페넬
	JPanel been2 = new JPanel(); // 빈페넬
	JPanel lArea = new JPanel(); // 라벨부분 페넬
	JPanel tArea = new JPanel(); // 텍스트필드 & 콤보박스
	//
	JRadioButton customer = new JRadioButton("고객", true);
	JRadioButton manager = new JRadioButton("관리자");
	ButtonGroup rbg = new ButtonGroup();
	JLabel lName = new JLabel("주인이름 : ");
	JLabel lPetName = new JLabel("동물이름 : ");
	JLabel lPetKind = new JLabel("종류 : ");
	JLabel lPetGender = new JLabel("성별 : ");
	JLabel lService = new JLabel("서비스");
	JTextField tName = new JTextField(8);
	JTextField tPetName = new JTextField(8);
	JComboBox selectKind = new JComboBox(kind);
	JComboBox selectGender = new JComboBox(gender);
	JComboBox selectService = new JComboBox(service);
	/// 고객

	/// 매니저 부분
	JButton login = new JButton("로그인");
	JButton serchID_PW = new JButton("ID/PW 찾기");
	JButton joinMemberShip = new JButton("회원가입");
	JLabel lId = new JLabel("아이디 : ");
	JLabel lPw = new JLabel("비밀번호 : ");
	JTextField tId = new JTextField(8);
	// JTextField tPw = new JTextField(8); ▽ 비밀번호 입력시 입력값 안보이도록 passwordField로 변경
	JPasswordField tpw = new JPasswordField(8);
	/// 매니저 부분 끝

	// 컴포넌트 부분 끝

	public PetradiceMainFrame() {
		setSize(400, 500);
		setTitle("Petradice");
		setIconImage(icon.getImage());
		setLocation(300, 200);

		init();
		start();
		setResizable(false);
		setVisible(true);
	}

	private void init() {
		container.setLayout(new BorderLayout());
		/// 고객
		// 기본프레임에 페넬 추가
		container.add("North", northPanel);
		container.add("Center", centerPanel);
		container.add("South", southPanel);
		// 추가된 페넬의 레이아웃 설정
		// North 부분 설정
		northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		northPanel.add(pgrTitle);
		pgrTitle.setFont(titleFont);
		// North 부분 설정 끝

		// Center 부분 설정
		centerPanel.add(imgArea);
		imgArea.setIcon(icon1);
		// Center 부분 설정 끝

		// South 부분 설정
		southPanel.setLayout(new BorderLayout());
		southPanel.add("North", radioArea);
		radioArea.setLayout(new FlowLayout(FlowLayout.CENTER));
		radioArea.add(customer);
		radioArea.add(manager);
		rbg.add(customer);
		rbg.add(manager);
		southPanel.add("South", buttonsArea);
		buttonsArea.setLayout(new FlowLayout(FlowLayout.CENTER));
		buttonsArea.add(registration);
		buttonsArea.add(checkService);
		bbg.add(registration);
		bbg.add(checkService);

		//
		southPanel.add("Center", s_c_Panel);
		s_c_Panel.setLayout(new GridLayout(1, 3));
		s_c_Panel.add(been1);
		s_c_Panel.add(inputInfo);
		inputInfo.setLayout(new BorderLayout());
		inputInfo.add("West", lArea);
		lArea.setLayout(new GridLayout(5, 1, 3, 3));
		lArea.add(lName);
		lArea.add(lPetName);
		lArea.add(lPetKind);
		lArea.add(lPetGender);
		lArea.add(lService);
		inputInfo.add("Center", tArea);
		tArea.setLayout(new GridLayout(5, 1, 3, 3));
		tArea.add(tName);
		tArea.add(tPetName);
		tArea.add(selectKind);
		tArea.add(selectGender);
		tArea.add(selectService);
		s_c_Panel.add(been2);

		//

	}

	private void start() {
		customer.addActionListener(this);
		manager.addActionListener(this);
		registration.addActionListener(this);
		checkService.addActionListener(this);
		login.addActionListener(this);
		serchID_PW.addActionListener(this);
		joinMemberShip.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// 라디오 버튼의 값에 따른 정보입력부분 변경
		if (e.getSource() == customer) {
			// 라벨페넬 재구성
			lArea.remove(lId);
			lArea.remove(lPw);
			lArea.invalidate();
			lArea.setLayout(new GridLayout(5, 1, 3, 3));
			lArea.add(lName);
			lArea.add(lPetName);
			lArea.add(lPetKind);
			lArea.add(lPetGender);
			lArea.add(lService);
			lArea.revalidate();
			lArea.repaint();

			// 텍스트페넬 재구성
			tArea.remove(tId);
			tArea.remove(tpw);
			tArea.invalidate();
			tArea.setLayout(new GridLayout(5, 1, 3, 3));
			tArea.add(tName);
			tArea.add(tPetName);
			tArea.add(selectKind);
			tArea.add(selectGender);
			tArea.add(selectService);
			tArea.revalidate();
			tArea.repaint();

			// 버튼 재구성
			buttonsArea.remove(login);
			buttonsArea.remove(serchID_PW);
			buttonsArea.remove(joinMemberShip);
			buttonsArea.invalidate();
			buttonsArea.add(registration);
			buttonsArea.add(checkService);
			buttonsArea.revalidate();
			buttonsArea.repaint();

		} else if (e.getSource() == manager) {
			// 라벨페넬 재구성
			lArea.remove(lName);
			lArea.remove(lPetName);
			lArea.remove(lPetKind);
			lArea.remove(lPetGender);
			lArea.remove(lService);
			lArea.invalidate();
			lArea.setLayout(new GridLayout(3, 1, 3, 3));
			lArea.add(lId);
			lArea.add(lPw);
			lArea.revalidate();
			lArea.repaint();

			// 텍스트페넬 재구성
			tArea.remove(tName);
			tArea.remove(tPetName);
			tArea.remove(selectKind);
			tArea.remove(selectGender);
			tArea.remove(selectService);
			tArea.invalidate();
			tArea.setLayout(new GridLayout(3, 1, 3, 3));
			tArea.add(tId);
			tArea.add(tpw);
			tpw.setEchoChar('*');

			tArea.revalidate();
			tArea.repaint();

			// 버튼 재구성
			buttonsArea.remove(registration);
			buttonsArea.remove(checkService);
			buttonsArea.invalidate();
			buttonsArea.add(login);
			buttonsArea.add(serchID_PW);
			buttonsArea.add(joinMemberShip);
			buttonsArea.revalidate();
			buttonsArea.repaint();
		}

		// 버튼들 이벤트 구현
		if (e.getSource() == registration) {
			// 예약 버튼
			String name = tName.getText();
			String petName = tPetName.getText();
			String s_kind = (String)selectKind.getSelectedItem();
			String s_gender = (String)selectGender.getSelectedItem();
			int kind = 1;
			if(s_kind.equals("강아지"))kind = 1;
			else if(s_kind.equals("고양이"))kind = 2;
			else if(s_kind.equals("기타"))kind = 3;
			
			int gender = 1;
			if(s_gender.equals("수컷"))gender = 1;
			else if(s_gender.equals("암컷"))gender = 2;
			
			String service = (String)selectService.getSelectedItem();
			if(tName.getText().equals("")) {
				JOptionPane.showMessageDialog(this,"이름을 입력하세요","이름 공백",JOptionPane.INFORMATION_MESSAGE);
				
				return;
			}
			if(tPetName.getText().equals("")) {
				JOptionPane.showMessageDialog(this,"동물이름을 입력하세요","동물 이름 공백",JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			
			int result = dao.insertCustomer(name, petName, kind, gender, service);
			if(result>0) {
				if (selectService.getSelectedItem().equals("호텔")) {
					CustomerDTO dto = new CustomerDTO(name, petName, kind, gender,service);
					new PetHotelFrame(dto);
					
				
				} else if (selectService.getSelectedItem().equals("미용")) {
					
					CustomerDTO dto = new CustomerDTO(name, petName, kind, gender,service);
					new HairMainFrame(dto);
				}
			}
			tName.setText("");
			tPetName.setText("");
			selectKind.setSelectedIndex(0);
			selectGender.setSelectedIndex(0);
			selectService.setSelectedIndex(0);
			

		} else if (e.getSource() == checkService) {
			HairDTO hairdto = new HairDTO();
			
			new Frame2(hairdto);
			
		} else if (e.getSource() == login) {
// 관리자 프레임 나오기
			new Frame();
		} else if (e.getSource() == serchID_PW) {
// 아이디 비밀번호 찾는 프레임 나오기
		} else if (e.getSource() == joinMemberShip) {
			new JoinMemberShipFrame();
		}
	}
}
