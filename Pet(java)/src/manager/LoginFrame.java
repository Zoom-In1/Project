package manager;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginFrame extends JFrame implements ActionListener {
	JoinDAO joindao = new JoinDAO();
	Container container = getContentPane();
	JPanel titlePanel = new JPanel();
	JLabel title = new JLabel(" 로그인 ");
	Font font = new Font("휴먼편지체", Font.BOLD | Font.ITALIC, 28);

	// center
	JPanel centerPanel = new JPanel();
	JPanel cWestPanel = new JPanel();
	JPanel cCenterPanel = new JPanel();
	JPanel cEastPanel = new JPanel();

	JPanel labelPanel = new JPanel();
	JPanel b1 = new JPanel();
	JPanel b2 = new JPanel();

	JPanel button = new JPanel();
	JPanel inputPanel = new JPanel();

	JPanel b3 = new JPanel();
	JLabel lid = new JLabel("ID : ");
	JLabel lpw = new JLabel("PW : ");
	JTextField tid = new JTextField(10);
	JPasswordField tpw = new JPasswordField(10);
	JButton join = new JButton("로그인");

	// South
	JPanel southPanel = new JPanel();
	JLabel img = new JLabel();
	ImageIcon southimg = new ImageIcon("img/동물.jpg");
	Image Img = southimg.getImage();
	Image IMG = Img.getScaledInstance(300, 40, Image.SCALE_SMOOTH);
	ImageIcon SouthImg = new ImageIcon(IMG);

	public LoginFrame() {
		setSize(300, 300);
		setTitle("로그인");
		setLocation(1000, 400);

		init();
		start();

		setVisible(true);
		setResizable(false);
	}

	private void init() {
		container.setLayout(new BorderLayout());
		container.add("North", titlePanel);
		titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		titlePanel.add(title);
		title.setFont(font);

		container.add("Center", centerPanel);
		centerPanel.setLayout(new BorderLayout());
		centerPanel.add("West", cWestPanel);
		centerPanel.add("Center", cCenterPanel);
		cCenterPanel.setLayout(new GridLayout(1, 3));
		cCenterPanel.add(labelPanel);
		labelPanel.setLayout(new GridLayout(6, 1));
		labelPanel.add(b1);
		labelPanel.add(lid);
		labelPanel.add(lpw);
		cCenterPanel.add(inputPanel);
		inputPanel.setLayout(new GridLayout(6, 1));
		inputPanel.add(b2);
		inputPanel.add(tid);
		inputPanel.add(tpw);
		cCenterPanel.add(button);
		button.setLayout(new GridLayout(5, 1));
		button.add(b3);
		button.add(join);
		centerPanel.add("East", cEastPanel);

		container.add("South", southPanel);
		southPanel.add(img);
		img.setIcon(SouthImg);

	}

	private void start() {
		// TODO Auto-generated method stub
		join.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		if (e.getSource() == join) {
			List<JoinDTO> list = new ArrayList<JoinDTO>();
			String id = tid.getText();
			String pw = tpw.getText();
			list = joindao.selectMemberAll();
			int flage = 0;
//			for (int i = 0; i < list.size(); i++) {
//				JoinDTO dto = list.get(i);
//				if (!id.equals(dto.getId())) {
//					flage = 1;
//					break;
//				} else {
//					dto = joindao.selectMemberID(id);
//					if (!pw.equals(dto.getPw())) {
//						flage = 2;
//						break;
//					} else {
//						flage = 3;
//					}
//				}
//			}
//			if(flage == 3) {
//				new Frame();
//			}
//		}
//	}
//}

			for(int i = 0 ; i<list.size(); i++) {
				JoinDTO dto = list.get(i);
				if(!id.equals(dto.getId())) {
					JOptionPane.showMessageDialog(this,"존재하지 않는 아이디","아이디 없음",JOptionPane.INFORMATION_MESSAGE);
					break;
				}else {
					dto = joindao.selectMemberID(id);
					if(!pw.equals(dto.getPw())) {
						JOptionPane.showMessageDialog(this,"비밀번호 틀림","비밀번호 오류",JOptionPane.INFORMATION_MESSAGE);
						break;
					}else {
						new Frame();
						dispose();
					}
				} break;
			} 
		}
	}

}
