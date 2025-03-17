package manager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import hair.HairDTO;

public class Frame2 extends JFrame implements ActionListener{
	
	Manger manger = new Mangerlmpl();
	MangerDAO dao = new MangerDAO();
	
	Container container = getContentPane();
	ImageIcon imageIcon1 = new ImageIcon("img/다운로드.jpg");
	
	Image img1 = imageIcon1.getImage();
	Image logo = img1.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
	ImageIcon logoImg = new ImageIcon(logo);
	
	JPanel panel_N = new JPanel();
	JPanel panel_C = new JPanel();
	JPanel panel_S = new JPanel();
	JPanel panel_W = new JPanel();
	
	JPanel panel_st = new JPanel();
	JPanel panel_bu = new JPanel();
	JPanel panel_bl1 = new JPanel();
	JPanel panel_bl2 = new JPanel();
	
	
	JPanel panel_ra = new JPanel();
	JPanel panel_text = new JPanel();
	JPanel panel_field = new JPanel();
	JPanel panel_wbu = new JPanel();
	
	JButton button_sel = new JButton("검    색");
	JButton button_del = new JButton("삭    제");
	JButton button_man = new JButton("관리자 창");
	
	JButton button_upd = new JButton("수   정");
	
	String [] strs = {"이름", "회원번호"
			+ ""};
	
	JLabel label_T = new JLabel("Pet Care Research!", JLabel.CENTER);
	JLabel label_B = new JLabel("◀ 관리자 전용", JLabel.LEFT);
	JTextField textField_st = new JTextField(10);
	
	JRadioButton radioButton_in = new JRadioButton("입실 날짜", true);
	JRadioButton radioButton_out = new JRadioButton("퇴실 날짜");
	ButtonGroup buttonGroup1 = new ButtonGroup();
	
	JLabel label_in = new JLabel("입실 날짜", JLabel.LEFT);
	JLabel label_num = new JLabel("회원 번호", JLabel.LEFT);
	JTextField textField_day = new JTextField(10);
	JTextField textField_num = new JTextField(10);

	
	JTextArea textArea = new JTextArea();
	JScrollPane scrollPane = new JScrollPane(textArea);
	
	JLabel label = new JLabel();
	JComboBox<String> combobox = new JComboBox<String>(strs);
	
	public Frame2(HairDTO hairdto) {
		
		  setSize(1010, 330);
	      setTitle("관지자 관리");
	      setLocation(600, 300);
	      init();
	      start();
	      setIconImage(imageIcon1.getImage());
	      setVisible(true);
	}

	private void init() {
		
		container.setLayout(new BorderLayout(5,10));
		container.add("North", panel_N);
		container.add("Center", panel_C);
		container.add("South", panel_S);
		container.add("West", panel_W);
		container.setBackground(new Color(230, 230,120));
		
		panel_W.setBorder(new TitledBorder("입,퇴실 날짜 변경 입력창"));
		panel_W.setLayout(new BorderLayout());
		panel_W.add("North", panel_ra);
		panel_W.add("West", panel_text);
		panel_W.add("Center", panel_field);
		panel_W.add("South", panel_wbu);
		panel_W.setBackground(new Color(230, 230,120));
		
		
		panel_ra.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel_ra.add(radioButton_in);
		panel_ra.add(radioButton_out);
		buttonGroup1.add(radioButton_in);
		buttonGroup1.add(radioButton_out);
		panel_ra.setBackground(new Color(230, 230,120));
		radioButton_in.setBackground(new Color(230, 230,120));
		radioButton_out.setBackground(new Color(230, 230,120));
		
		
		panel_text.setLayout(new GridLayout(3,1,10,5));
		panel_text.add(label_num);
		panel_text.add(label_in);
		panel_text.setBackground(new Color(230, 230,120));
		
		panel_field.setLayout(new GridLayout(3,1,10,5));
		panel_field.add(textField_num);
		panel_field.add(textField_day);
		panel_field.setBackground(new Color(230, 230,120));
		
		panel_wbu.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel_wbu.add(button_upd);
		panel_wbu.setBackground(new Color(230, 230,120));
		
		panel_N.setLayout(new BorderLayout());
		panel_N.add("Center", label_T);
		label_T.setFont(new Font("휴먼편지체", Font.BOLD, 30));
		label_T.setForeground(Color.BLUE);
		label_T.setIcon(logoImg);
		panel_N.setBackground(new Color(230, 230,120));
		
		panel_C.setLayout(new BorderLayout());
		panel_C.add("Center", scrollPane);
		panel_C.add("West", panel_bl1);
		panel_C.add("East", panel_bl2);
		textArea.setEditable(false);
		textArea.setBackground(new Color(240, 240, 240, 240));
		panel_C.setBackground(new Color(230, 230,120));
		panel_bl1.setBackground(new Color(230, 230,120));
		panel_bl2.setBackground(new Color(230, 230,120));
		
		panel_S.setLayout(new FlowLayout());
		panel_S.add("Center", panel_st);
		panel_S.add("East", panel_bu);
		panel_S.setBackground(new Color(230, 230,120));
		
		panel_st.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel_st.add(label);
		panel_st.add(combobox);
		panel_st.add(textField_st);
		panel_st.setBackground(new Color(230, 230,120));
		
		panel_bu.setLayout(new GridLayout(1,4,5,5));
		panel_bu.add(button_sel);
		panel_bu.add(button_del);
		panel_bu.add(button_man);
		panel_bu.add(label_B);
		panel_bu.setBackground(new Color(230, 230,120));
		

		
	}

	private void start() {
		
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		button_sel.addActionListener(this);
		button_del.addActionListener(this);
		button_man.addActionListener(this);
		combobox.addActionListener(this);
		
		radioButton_in.addActionListener(this);
		radioButton_out.addActionListener(this);
		button_upd.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == button_sel) {
				String str = (String)combobox.getSelectedItem();
				if(str.equals("회원번호")) {
				String code = textField_st.getText();
				if(code.equals("")) {
					
					JOptionPane.showMessageDialog(this, "회원번호를 입력하세요");
					return;
				}
				
				int num = Integer.parseInt(code);
				
				String result  = manger.searchCode(num);
				textArea.setText(result);
				
				}else if(str.equals("이름")) {
					String name = textField_st.getText();
					if(name.equals("")) {
						
						JOptionPane.showMessageDialog(this, "회원번호를 입력하세요");
						return;
					}
					
					String result  = manger.searchName(name);
					textArea.setText(result);
					
				}
				
			
		}else if(e.getSource() == button_del) {
			
			String str = (String)combobox.getSelectedItem();
	         if(str.equals("회원번호")) {
	            
	         String code = textField_st.getText();
	         int num = Integer.parseInt(code);
	         
	         if(code.equals("")) {
	            
	             JOptionPane.showMessageDialog(this, "회원번호를 입력해주세요");
	             return;
	          }
	         
	         int result = dao.delete(num);
	         

	           if(result > 0) JOptionPane.showMessageDialog(this, "삭제 성공");
	          else JOptionPane.showMessageDialog(this, "삭제 실패");
	         
	         
	         }else if(str.equals("이름")) {
	            
	         String code = textField_st.getText();
	         String name = code;
	         int result = dao.delete(name);
	         

	           if(result > 0) {
	        	   JOptionPane.showMessageDialog(this, "삭제 성공");
	        	   
	           }
	          else JOptionPane.showMessageDialog(this, "삭제 실패");
	         
	         
	          if(name.equals("")) {
	             JOptionPane.showMessageDialog(this, "이름을 입력해주세요");
	             return;
	          }
	         }
		
			
		}else if(e.getSource() == button_man) {
			new LoginFrame();
			//new Frame();
		}
		
		if(radioButton_in.isSelected()) { label_in.setText("입실날짜");
		
		String number = textField_num.getText();
		
		if( ! number.equals("")) {
		int num = Integer.parseInt(number);
		String day = textField_day.getText();
		
		int result = dao.Inupdate(day, num);
		
		 if(result > 0) JOptionPane.showMessageDialog(this, "수정 성공");
	    	else JOptionPane.showMessageDialog(this, "해당 정보가 없습니다");
		 
		 String result2  = manger.searchCode(num);
		 
		 textArea.setText(result2);
		 
		 textField_num.setText("");
		 textField_day.setText("");
		}
		
		
	    }else if(radioButton_out.isSelected()) { label_in.setText("퇴실날짜");
	    
	    String number = textField_num.getText();
	    
		if( ! number.equals("")) {
			int num = Integer.parseInt(number);
			String day = textField_day.getText();
			
			int result = dao.Outupdate(day, num);
			
			 if(result > 0) JOptionPane.showMessageDialog(this, "수정 성공");
		    	else JOptionPane.showMessageDialog(this, "해당 정보가 없습니다");
			 
			 String result2  = manger.searchCode(num);
			 textArea.setText(result2);
				
			 textField_num.setText("");
			 textField_day.setText("");
		
	   
	     }

       }
		
	}
	
}
