package hair;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.FileSystemNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import frame.CustomerDAO;
import frame.CustomerDTO;
import manager.MangerDAO;

public class HairMainFrame extends JFrame implements ActionListener {
	Calendar cal = Calendar.getInstance();
	int year = cal.get(Calendar.YEAR);
	CustomerDTO dto = null;
	Container container = getContentPane();
	CustomerDAO dao = new CustomerDAO();
	List<CustomerDTO> list = new ArrayList<CustomerDTO>();
	HairDAO hairdao = new HairDAO();
	int num = 0;
	String name = "";
	String petName = "";
	int kind = 1;
	int petGender = 1;
	String service = "";
	String [] dogSize = {"소형견","중형견","대형견","특수견"};
	String [] weight = {"4kg미만","4~8kg","8~12kg","12kg초과"};
	ImageIcon icon = new ImageIcon("img/다운로드.jpg");
	ImageIcon centerimg = new ImageIcon("img/동물.jpg");
	// North
	JPanel northPanel = new JPanel();
	JLabel title = new JLabel(" 미용 예약 ");
	Font font = new Font("휴먼편지체", Font.BOLD | Font.ITALIC, 28);
	
	// Center 
	JPanel centerPanel = new JPanel();
	
	// Center - center
	JPanel imgPanel = new JPanel();
	JLabel img = new JLabel();
	
	
	//Center - south
	JPanel selectPanel = new JPanel(); // south 기본 페넬
	JPanel labelPanel1 = new JPanel(); // 요일, 달 라벨
	JPanel selectPanel1 = new JPanel(); // 요일, 달 콤보
	JPanel labelPanel2 = new JPanel(); // 시간, 크기 라벨
	JPanel selectPanel2 = new JPanel(); // 시간, 크기 콤보
	JLabel lMonth = new JLabel("Month");
	JLabel been1 = new JLabel();
	JLabel lDay = new JLabel("일자");
	JLabel been2 = new JLabel();
	JComboBox<String> months = new JComboBox<>();
	JLabel been3 = new JLabel();	
	JComboBox<String> days = new JComboBox<>();
	JLabel been4 = new JLabel();
	JLabel ltime = new JLabel("시간");
	JLabel ldogSize = new JLabel("견종");
	JLabel lweight = new JLabel("무개");
	JLabel been5 = new JLabel();
	JComboBox<String> times = new JComboBox<>();
	JComboBox<String> dogSizes = new JComboBox(dogSize);
	JComboBox<String> weights = new JComboBox(weight);
	JLabel notDog = new JLabel();
	
	JPanel beenPanel = new JPanel();
	// South
	JPanel southPanel = new JPanel();
	JPanel buttons = new JPanel();
	JButton input = new JButton("예약");

	public HairMainFrame(CustomerDTO dto) {
		this.dto = dto;
		this.num = dto.getNum();
		this.name = dto.getName();
		this.petName = dto.getPetName();
		this.kind = dto.getKind();
		this.petGender = dto.getPetGender();
		this.service = dto.getService();
		setSize(380, 500);
		setTitle("미용예약");
		setIconImage(icon.getImage());
		setLocation(600, 300);
		init();
		start();

		setVisible(true);
		setResizable(false);
	}

	private void init() {
		container.setLayout(new BorderLayout());
		// North
		container.add("North",northPanel);
		northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		northPanel.add(title);
		title.setFont(font);
		
		// Center
		container.add("Center",centerPanel);
		centerPanel.setLayout(new BorderLayout());
		centerPanel.add("Center",imgPanel);
		imgPanel.add(img);
		img.setIcon(centerimg);
		centerPanel.add("South",selectPanel);
		selectPanel.setLayout(new GridLayout(1,5,5,5));
		selectPanel.add(labelPanel1);
		labelPanel1.setLayout(new GridLayout(4,1));
		labelPanel1.add(lMonth);
		labelPanel1.add(been1);
		labelPanel1.add(lDay);
		labelPanel1.add(been2);
		selectPanel.add(selectPanel1);
		selectPanel1.setLayout(new GridLayout(4, 1));
		for(int i = 1 ; i<=12 ; i++) {
			months.addItem(String.valueOf(i));
		}
		for(int i = 1 ; i<=31 ; i++) {
			days.addItem(String.valueOf(i));
		}
		for(int i = 10 ; i<=19 ; i++) {
			times.addItem(String.valueOf(i)+"~"+String.valueOf(i+1));
		}
		selectPanel1.add(months);
		selectPanel1.add(been3);
		selectPanel1.add(days);
		selectPanel1.add(been4);
		if(kind==1) {
			selectPanel.add(labelPanel2);
			labelPanel2.setLayout(new GridLayout(4,1));
			labelPanel2.add(ltime);
			labelPanel2.add(ldogSize);
			labelPanel2.add(lweight);	
			labelPanel2.add(been5);
		}else {
			selectPanel.add(labelPanel2);
			labelPanel2.setLayout(new GridLayout(4,1));
			labelPanel2.add(ltime);
			labelPanel2.add(ldogSize);
			labelPanel2.add(lweight);	
			labelPanel2.add(been5);
			ldogSize.setText("종류 : ");
		}
		
		if(kind ==1) {
			selectPanel.add(selectPanel2);
			selectPanel2.setLayout(new GridLayout(4,1));
			selectPanel2.add(times);
			selectPanel2.add(dogSizes);
			selectPanel2.add(weights);
		}else {
			selectPanel.add(selectPanel2);
			selectPanel2.setLayout(new GridLayout(4,1));
			selectPanel2.add(times);
			selectPanel2.add(notDog);
			selectPanel2.add(weights);
			if(kind == 2) {
				notDog.setText("고양이");
			}else {
				notDog.setText("기타");
			}
		}
		
		selectPanel.add(beenPanel);
		// South
		container.add("South",buttons);
		buttons.setLayout(new FlowLayout(FlowLayout.CENTER));
		buttons.add(input);		
	}

	private void start() {
		input.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		setDefaultCloseOperation(HIDE_ON_CLOSE); //
	
		if(e.getSource()==input) {
			String dogSize ="";
			int result = 0;
			String s_month = (String)months.getSelectedItem();
			int month = Integer.parseInt(s_month);
			String s_day = (String)days.getSelectedItem();
			int day = Integer.parseInt(s_day);
			String indate = String.format("%d/%02d/%02d", year,month,day);
			String outdate = (String)times.getSelectedItem() +"시";
			if(kind == 1) {
				dogSize = (String)dogSizes.getSelectedItem();
			}else if(kind == 2) {
				dogSize ="고양이";
			}else if(kind ==3) {
				dogSize ="기타";
			}
			
			String weight = (String)weights.getSelectedItem();
			list = dao.selectName(name);
			for(int i = 0 ; i<list.size(); i++) {
				CustomerDTO dto = list.get(i);
				num = dto.getNum();
			}
			
			result = hairdao.insertHair(num,name,petName,kind,petGender,service,indate,outdate,dogSize,weight);
			if(result > 0 ) {
				HairDTO hairdto = new HairDTO(num,dto,indate,outdate,dogSize,weight);
				
				MangerDAO magerdao = new MangerDAO();
				magerdao.insertManger(hairdto);
				JOptionPane.showMessageDialog(this,"예약완료","성공",JOptionPane.INFORMATION_MESSAGE);
				dispose();

			}else {
				JOptionPane.showMessageDialog(this,"예약실패","실패",JOptionPane.INFORMATION_MESSAGE);
			}
			
		}
	}

}
