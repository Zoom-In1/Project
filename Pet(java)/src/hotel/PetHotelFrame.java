package hotel;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.zip.CheckedInputStream;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;

import frame.CustomerDAO;
import frame.CustomerDTO;

public class PetHotelFrame extends JFrame implements ActionListener{
	Container container = getContentPane();
	CustomerDTO dto = null;
	int num = 0;
	JComboBox<String> checkInMonth = new JComboBox<>();
	JComboBox<String> checkInDay = new JComboBox<>();
	
	JComboBox<String> checkOutMonth = new JComboBox<>();
	JComboBox<String> checkOutDay = new JComboBox<>();
	
	JLabel checkIn = new JLabel("체크인 : ");
	JLabel checkOut = new JLabel("체크아웃 : ");
	JLabel l1 = new JLabel("월");
	JLabel l2 = new JLabel("일");
	JLabel l3 = new JLabel("월");
	JLabel l4 = new JLabel("일");
	JLabel been1 = new JLabel("    ");
	
	JTextArea info = new JTextArea();
	JScrollPane scroll = new JScrollPane(info);
	
	ImageIcon imageIcon = new ImageIcon("img/다운로드.jpg");
	
	JPanel checkInOutPanel = new JPanel();
	
	JPanel centerPanel = new JPanel();
	
	
	JButton ok = new JButton("예약");
	JButton cancle = new JButton("취소");
	ButtonGroup bg1 = new ButtonGroup();
	JPanel buttons = new JPanel();
	
	public PetHotelFrame(CustomerDTO dto) {
		this.dto = dto;
		num = dto.getNum();
		String name = dto.getName();
		String petName = dto.getPetName();
		int kind = dto.getKind();
		int petGender = dto.getPetGender();
		String service = dto.getService();
		
		for(int i = 1 ; i<=12 ; i++) {
			checkInMonth.addItem(String.valueOf(i));
			checkOutMonth.addItem(String.valueOf(i));
		}
		
		for(int i = 1 ; i<=31 ; i++) {
			checkInDay.addItem(String.valueOf(i));
			checkOutDay.addItem(String.valueOf(i));
		}
		
		setSize(500, 300);
		setTitle("애견 등록 신청");
		setLocation(700, 300);
		init();
		start();
		setVisible(true);
		setIconImage(imageIcon.getImage());

	}
	
	private void init() {
		container.setLayout(new BorderLayout());
		container.add("North",checkInOutPanel);
		
		checkInOutPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		checkInOutPanel.add(checkIn);
		checkInOutPanel.add(checkInMonth);
		checkInOutPanel.add(l1);
		checkInOutPanel.add(checkInDay);
		checkInOutPanel.add(l2);
		checkInOutPanel.add(been1);
		checkInOutPanel.add(checkOut);
		checkInOutPanel.add(checkOutMonth);
		checkInOutPanel.add(l3);
		checkInOutPanel.add(checkOutDay);
		checkInOutPanel.add(l4);
		
		container.add("Center",centerPanel);
		BevelBorder bevelBorder = new BevelBorder(BevelBorder.RAISED);
		TitledBorder titleBorder = new TitledBorder("약관");
		info.setBorder(new CompoundBorder(bevelBorder,titleBorder));
		centerPanel.setLayout(new BorderLayout());
		centerPanel.add("Center",scroll);
		info.setEditable(false);
		
		container.add("South",buttons);
		buttons.setLayout(new FlowLayout(FlowLayout.RIGHT));
		buttons.add(ok);
		buttons.add(cancle);
		bg1.add(ok);
		bg1.add(cancle);
	}

	private void start() {
		ok.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
		if(e.getSource()==ok) {
			// 유효성 검사
			String s_Imonth = (String)checkInMonth.getSelectedItem();
			String s_Omonth = (String)checkOutMonth.getSelectedItem();
			int Imonth = Integer.parseInt(s_Imonth);
			int Omonth = Integer.parseInt(s_Omonth);
			String sIday = (String)checkInDay.getSelectedItem();
			String sOday = (String)checkOutDay.getSelectedItem();
			int Iday = Integer.parseInt(sIday);
			int Oday = Integer.parseInt(sOday);
			if(Imonth>Omonth) {
				JOptionPane.showMessageDialog(this,"체크인 날짜(달)를 확인해 주세요","예약일 확인",JOptionPane.INFORMATION_MESSAGE);
			}else if(Imonth == Omonth && Iday>Oday) {
				JOptionPane.showMessageDialog(this,"체크인 날짜 이전으론 체크아웃 불가합니다","예약일 확인",JOptionPane.INFORMATION_MESSAGE);
			}
			
			
			
			//PetHotelDTO htdto = new PetHotelDTO(num,name,petName,kind,petGender,service,)
		}
	}

}
