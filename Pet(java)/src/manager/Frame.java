package manager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.ScrollPane;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class Frame extends JFrame implements ActionListener {
	
	Container container = getContentPane();
	Manger manger = new Mangerlmpl();
	MangerDAO dao = new MangerDAO();
//	List<MangerDTO> list = new ArrayList<MangerDTO>();
	
	ImageIcon imageIcon = new ImageIcon("img/다운로드.jpg");
	
	Image img = imageIcon.getImage();
	Image logo = img.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
	ImageIcon logoImg = new ImageIcon(logo);
	
	JTextArea textArea = new JTextArea();
	JScrollPane scrollPane = new JScrollPane(textArea);
	
//	Object[] row = new Object[10];
//	Object[] names = {"회원번호", "목적", "동물이름", "소유주 이름", "날짜", "종류", "시간", "크키", "분야", "나이", "성별"};
//	int row = dao.selectAll().size();
//	DefaultTableModel model = new DefaultTableModel(names,row);
//
//	JTable table = new JTable(model);
//	JScrollPane jsScrollPane2 = new JScrollPane(table);
	
	
	JPanel panel_L = new JPanel();
	JPanel panel_N = new JPanel();
	JPanel panel_C = new JPanel();
	JPanel panel_S = new JPanel();
	JPanel panel_R = new JPanel();
	
	JPanel panel_st = new JPanel();
	JPanel panel_bu = new JPanel();
	
	JButton button_sel = new JButton(" 검   색 ");
	JButton button_Asel = new JButton("전 체 보 기");
	JButton button_del = new JButton("(회원번호) 삭   제 ");
	JButton button_asc = new JButton(" 회원 번호(오름차순)  ");
	JButton button_desc = new JButton(" 이   름  (내림차순) ");
	JButton button_man = new JButton(" 예약   확인 ");
	
	JButton button_ok = new JButton("확인");
	
	JLabel label_T = new JLabel("Pet Care Service!", JLabel.CENTER);
	JTextField textField_st = new JTextField(5);
	
	JLabel label_B = new JLabel("*모든 고객정보를 확인 할 수 있습니다*", JLabel.CENTER);
	JLabel label_L = new JLabel("*▲ 사용자 예약 확인창.*", JLabel.CENTER);
	
	// JTextArea textArea = new JTextArea();
	// JScrollPane scrollPane = new JScrollPane(textArea);

	public Frame() {
		  setSize(1055, 400);
	      setTitle("관지자 정보관리");
	      setLocation(500, 300);
	      init();
	      start();
	      setIconImage(imageIcon.getImage());
	      setVisible(true);
	      
	      String result = manger.print();
		  textArea.setText(result);
	      
	}
	
	private void init() {
		
		
		container.setLayout(new BorderLayout(5,5));
		container.add("West", panel_L);
		container.add("Center", panel_C);
		container.add("North", panel_N);
		container.add("South", panel_S);
		container.setBackground(new Color(230, 230,120));
		
		panel_L.setLayout(new BorderLayout(5,5));
		panel_L.add("North", panel_st);
		panel_L.add("Center", panel_bu);
		panel_L.setBackground(new Color(230, 230,120));
		
	    panel_st.setBorder(new TitledBorder("회원번호"));
		panel_st.setLayout(new BorderLayout());
		panel_st.add("Center", textField_st);
		panel_st.setBackground(new Color(230, 230,120));
		
		panel_bu.setLayout(new GridLayout(7,1,5,5)); 
		panel_bu.add(button_sel);
		button_sel.setBorder(new BevelBorder(BevelBorder.RAISED));
		panel_bu.add(button_Asel);
		button_Asel.setBorder(new BevelBorder(BevelBorder.RAISED));
		panel_bu.add(button_del);
		button_del.setBorder(new BevelBorder(BevelBorder.RAISED));
		panel_bu.add(button_asc);
		button_asc.setBorder(new BevelBorder(BevelBorder.RAISED));
		panel_bu.add(button_desc);
		button_desc.setBorder(new BevelBorder(BevelBorder.RAISED));
		panel_bu.add(button_man);
		button_man.setBackground(new Color(100, 240, 240, 100));
		button_man.setBorder(new BevelBorder(BevelBorder.RAISED));
		button_man.setForeground(Color.RED);
		panel_bu.add(label_L);
		panel_bu.setBackground(new Color(230, 230,120));
		
		panel_N.setLayout(new BorderLayout());
		panel_N.add("Center", label_T);
		label_T.setFont(new Font("Dialog", Font.BOLD, 30));
		label_T.setForeground(new Color(255, 20, 30));
		panel_N.setBackground(new Color(230, 230,120));
		label_T.setIcon(logoImg);
		
		
		panel_C.setLayout(new BorderLayout(5,5));
		
//		panel_C.add(jsScrollPane2);
		panel_C.add("East", panel_R);
		textArea.setEditable(false);
		panel_C.add("Center",scrollPane);
		panel_C.setBackground(new Color(230, 230,120));
		
		panel_R.setBackground(new Color(230, 230,120));
		
		panel_S.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panel_S.add(label_B);
		panel_S.add(button_ok);
		button_ok.setBackground(Color.GREEN);
		panel_S.setBackground(new Color(230, 230,120));
			
	}


	private void start() {
		
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		button_sel.addActionListener(this);
		button_Asel.addActionListener(this);
		button_del.addActionListener(this);
		button_asc.addActionListener(this);
		button_desc.addActionListener(this);
		button_man.addActionListener(this);
		button_ok.addActionListener(this);
	
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == button_sel) {
				
			String code = textField_st.getText();
			if( ! code.equals("")) {
			int num = Integer.parseInt(code);
			
			String result  = manger.searchCode(num);
			
			if(!result.equals("")) {
			textArea.setText(result);
			
			}else if (result.equals("")) { 
				JOptionPane.showMessageDialog(this, "회원번호를 입력하세요");
			}
			}
			
			if(code.equals("")) {
				
				JOptionPane.showMessageDialog(this, "회원번호를 입력하세요");
				return;
			}
			
		}else if(e.getSource() == button_Asel) {		
						
		    String result = manger.print();
			textArea.setText(result);
			
		}else if(e.getSource() == button_del) {
			
			String code = textField_st.getText();
			
			if(code.equals("")) {
	    		JOptionPane.showMessageDialog(this, "회원번호를 입력해주세요");
	    		return;
	    	}
			
			int num = Integer.parseInt(code);
	    	
	        int result = dao.delete(num);
	        
	        if(result > 0) JOptionPane.showMessageDialog(this, "삭제 성공");
	    	else JOptionPane.showMessageDialog(this, "삭제 실패");
	    	
	    	
	    	textArea.setText(manger.print());
	    	textField_st.setText("");
			
			
		}else if(e.getSource() == button_asc) {
			
			int num = 0;
			String result = manger.ascCode(num);
			textArea.setText(result);
			
			
		}else if(e.getSource() == button_desc) {
			
			String name = ""; 
			String result = manger.descName(name);
			textArea.setText(result);
			
			
		}else if(e.getSource() == button_man) {
			
			//new Frame2();
		}	
		
		if(e.getSource() == button_ok) {
			dispose();
		}
		
	}

}
