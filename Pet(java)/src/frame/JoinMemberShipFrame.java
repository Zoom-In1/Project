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
import javax.swing.JTextField;

import manager.JoinDAO;
import manager.JoinDTO;

public class JoinMemberShipFrame extends JFrame implements ActionListener {
	int flage = 0;
	JoinDAO joindao = new JoinDAO();
	Container container = getContentPane();
	ImageIcon icon = new ImageIcon("img/다운로드.jpg");
	String[] firstTel = { "010", "011", "016", "017", "018", "019" };
	String[] majors = { "미용", "호텔" };

	// North 부분
	JPanel northPanel = new JPanel();
	JLabel title = new JLabel(" 회원가입 ");
	Font titleFont = new Font("휴먼편지체", Font.BOLD | Font.ITALIC, 28);

	// Center 부분
	JPanel centerPanel = new JPanel();
	JPanel lPanel = new JPanel();
	JPanel tPanel = new JPanel();
	//
	JPanel t1 = new JPanel();
	JPanel t2 = new JPanel();
	JPanel t3 = new JPanel();

	//
	JPanel birthdayPanel = new JPanel();
	JLabel lName = new JLabel("이름 : ");
	JLabel lbirthday = new JLabel("생일 : ");
	JLabel lIdentityNum = new JLabel("주민번호 : ");
	JLabel lId = new JLabel("ID : ");
	JLabel lPw = new JLabel("비밀번호 : ");
	JLabel lPwcheck = new JLabel("비밀번호 확인 : ");
	JLabel lTel = new JLabel("전화번호");
	JLabel lMajor = new JLabel("분야");
	JTextField tName = new JTextField(10);
	JComboBox<String> years = new JComboBox<>();
	JComboBox<String> month = new JComboBox<>();
	JComboBox<String> day = new JComboBox<>();
	JComboBox<String> major = new JComboBox<>(majors);
	JButton idCheck = new JButton("중복확인");
	JTextField firstNum = new JTextField(6);
	JLabel insertChar = new JLabel("-");
	JTextField secondNum = new JTextField(7);
	JTextField tId = new JTextField(10);
	JPasswordField tPw = new JPasswordField(10);
	JPasswordField tPwCheck = new JPasswordField(10);
	JComboBox tfirstTel = new JComboBox(firstTel);
	JTextField tTel1 = new JTextField(5);
	JTextField tTe12 = new JTextField(5);
	JPanel been = new JPanel();
	//
	JPanel b1 = new JPanel();
	JPanel b2 = new JPanel();
	JPanel b3 = new JPanel();
	JPanel b4 = new JPanel();
	JPanel b5 = new JPanel();
	JPanel b6 = new JPanel();
	JPanel b7 = new JPanel();
	JPanel b8 = new JPanel();
	JPanel b9 = new JPanel();
	JPanel b10 = new JPanel();
	JPanel b11 = new JPanel();

	//
	// South 부분
	JPanel southPanel = new JPanel();
	JButton ok = new JButton("확인");
	JButton cancel = new JButton("취소");
	ButtonGroup bg = new ButtonGroup();

	public JoinMemberShipFrame() {
		setSize(370, 400);
		setIconImage(icon.getImage());
		setTitle("회원가입");
		setLocation(690, 200);

		init();
		start();
		setResizable(false);
		setVisible(true);
	}

	private void init() {
		container.setLayout(new BorderLayout());
		// North 부분
		container.add("North", northPanel);
		northPanel.add(title);
		title.setFont(titleFont);

		// Center 부분
		// -- 년도 콤보박스
		for (int i = 1900; i <= 2999; i++) {
			years.addItem(Integer.toString(i));
		}
		// -- 달 콤보박스
		for (int i = 1; i <= 12; i++) {
			month.addItem(Integer.toString(i));
		}
		// -- 일 콤보박스
		for (int i = 1; i <= 31; i++) {
			day.addItem(Integer.toString(i));
		}
		container.add("Center", centerPanel);
		centerPanel.setLayout(new BorderLayout());
		centerPanel.add("West", lPanel);
		lPanel.setLayout(new GridLayout(9, 1));
		lPanel.add(lName);
		lPanel.add(lbirthday);
		lPanel.add(lIdentityNum);
		lPanel.add(lId);
		lPanel.add(lPw);
		lPanel.add(lPwcheck);
		lPanel.add(lTel);
		lPanel.add(lMajor);
		centerPanel.add("Center", tPanel);
		tPanel.setLayout(new GridLayout(1, 3, 3, 3));
		tPanel.add(t1);
		tPanel.add(t2);
		tPanel.add(t3);
		t1.setLayout(new GridLayout(9, 1));
		t2.setLayout(new GridLayout(9, 1));
		t3.setLayout(new GridLayout(9, 1));
		t1.add(tName);
		t1.add(years);
		t1.add(firstNum);
		t1.add(tId);
		t1.add(tPw);
		t1.add(tPwCheck);
		t1.add(tfirstTel);
		t1.add(major);

		t2.add(b1);
		t2.add(month);
		t2.add(secondNum);
		t2.add(b2);
		t2.add(b3);
		t2.add(b4);
		t2.add(tTel1);
		t2.add(b5);

		t3.add(b6);
		t3.add(day);
		t3.add(b7);
		t3.add(idCheck);
		t3.add(b8);
		t3.add(b9);
		t3.add(tTe12);
		t3.add(b10);
		centerPanel.add("East", b11);

		// South 부분
		container.add("South", southPanel);
		southPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		southPanel.add(ok);
		southPanel.add(cancel);
		bg.add(ok);
		bg.add(cancel);

	}

	private void start() {
		ok.addActionListener(this);
		cancel.addActionListener(this);
		idCheck.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		if (e.getSource() == ok) {
			String name = tName.getText();
			int year = Integer.parseInt((String) years.getSelectedItem());
			int months = Integer.parseInt((String) month.getSelectedItem());
			int days = Integer.parseInt((String) day.getSelectedItem());
			String birthday = String.format("%d/%02d/%02d", year, months, days);
			String num1 = firstNum.getText();
			String num2 = secondNum.getText();
			String num = String.format("%s-%s", num1, num2);
			String id = tId.getText();
			String pw = tPw.getText();
			String pwcheck = tPwCheck.getText();
			String t1 = (String) tfirstTel.getSelectedItem();
			String t2 = tTel1.getText();
			String t3 = tTe12.getText();
			String tel = String.format("%s-%s-%s", t1, t2, t3);
			String m = (String) major.getSelectedItem();
			if (name.equals("") || birthday.equals("") || num1.equals("") || num2.equals("") || id.equals("")
					|| pw.equals("") || pwcheck.equals("") || t2.equals("") || t3.equals("")) {
				JOptionPane.showMessageDialog(this, "공백이 존재합니다", "정보누락", JOptionPane.INFORMATION_MESSAGE);
				return;
			} else if (!pw.equals(pwcheck)) {
				JOptionPane.showMessageDialog(this, "비밀번호가 서로 다릅니다", "입력오류", JOptionPane.INFORMATION_MESSAGE);
				return;
			}

			if (flage == 0) {
				JOptionPane.showMessageDialog(this, "중복확인을 해주세요", "경고", JOptionPane.INFORMATION_MESSAGE);
			} else if (flage == 1) {
				JOptionPane.showMessageDialog(this, "회원가입 성공", "감사합니다", JOptionPane.INFORMATION_MESSAGE);
				joindao.insertInfo(name, birthday, num, id, pw, pwcheck, tel, m);
				dispose();
			}

		} else if (e.getSource() == cancel) {
			dispose();
			
		} else if (e.getSource() == idCheck) {
			
			System.exit(0);
			
//			List<JoinDTO> list = joindao.selectMemberAll();
//			if (list == null) {
//				int ok = JOptionPane.showConfirmDialog(this, "사용가능한 아이디입니다 \n사용하시겠습니까?", "가능",
//						JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
//				// ok : 0 / 취소 : 1
//				if (ok == 0) {
//					flage = 1;
//					return;
//				} else {
//					flage = 0;
//					return;
//				}
//
//			} else {
//				for (int i = 0; i < list.size(); i++) {
//					String id = tId.getText();
//					if (id.equals(list.get(i).getId())) {
//						JOptionPane.showMessageDialog(this, "사용중인 아이디입니다", "불가능", JOptionPane.INFORMATION_MESSAGE);
//						flage = 0;
//						break;
//					} else {
//						int ok = JOptionPane.showConfirmDialog(this, "사용가능한 아이디입니다 \n사용하시겠습니까?", "가능",
//								JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
//						if(ok == 0) {
//							flage = 1;
//							return;
//						}else {
//							flage = 0;
//							return;
//						}
//					}
//				}
//			}

		}

	}

}
