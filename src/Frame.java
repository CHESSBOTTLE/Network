import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

public class Frame extends JFrame{
	
	JLabel jl1,jl2,jl3,jl4,jl5,jl6,jl7;
	static JPanel jp1,jp2,jp3,jp4,jp5;
	JButton jb1,jb2;
	static JTable jt;
	static JScrollPane jsp;
	static DefaultTableModel dTM;
	static myThread mt;
	JComboBox jc1,jc2;
	JTextField jt1, jt2, jt3;
	String colum[] = {"IP","Ping","TTL","HostName","Ports[0+]"," "};
	String menu[] = {"Scan","Go to","Commands","Favorites","Tools","Help"};
	JMenu jm[] = new JMenu[6];
	static Frame fr;
	int q[] = new int[4];
	int p[] = new int[4];
	Image img1 = null;
	Image img2 = null;
	Image img3 = null;
	Image img4 = null;
	Image img5 = null;
	Image img6 = null;
	Image img7 = null;
	Image img8 = null;
	Image img9 = null;
	int cnt=0;
	String jcb1[] = {"IP Range","Random","Text File"};
	String jcb2[] = {"/26","/24","/16","255.192","255.162","255.0","255.0.0","255.0.0.0"};
	String file[] = {".\\dead.png",".\\fetchers.png",".\\ports.png",".\\prefs.png",".\\start.png",
					 ".\\stop.png",".\\kill.png",".\\icon.png"};
	
	Frame() {
		window wd = new window();
		setJMenuBar(wd.menubar);
		image();
		frame();
		wd.add();
		Action();
		window();
	}
	
	void image() {
		File source1 = new File(".\\dead.png");
		File source2 = new File(".\\fetchers.png");
		File source3 = new File(".\\ports.png");
		File source4 = new File(".\\prefs.png");
		File source5 = new File(".\\start.png");
		File source6 = new File(".\\stop.png");
		File source7 = new File(".\\kill.png");
		File source8 = new File(".\\icon.png");	
		File source9 = new File(".\\unknown.png");	
		try {
			img1 = ImageIO.read(source1);
			img2 = ImageIO.read(source2);
			img3 = ImageIO.read(source3);
			img4 = ImageIO.read(source4);
			img5 = ImageIO.read(source5);
			img6 = ImageIO.read(source6);
			img7 = ImageIO.read(source7);
			img8 = ImageIO.read(source8);
			img9 = ImageIO.read(source9);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	void frame() {
		setTitle("IP Range-Angry IP Scanner");

		setLayout(new BorderLayout());
		
		add(jp1= new JPanel(),BorderLayout.NORTH);
		add(jp2 = new JPanel(),BorderLayout.CENTER);
		add(jp3 = new JPanel(new FlowLayout(FlowLayout.LEFT)),BorderLayout.SOUTH);
		jp3.setBorder(new BevelBorder(BevelBorder.LOWERED));
		
		jp1.setLayout(new GridLayout(0, 1));
		jp1.add(jp4 = new JPanel(new FlowLayout(FlowLayout.LEFT)));
		jp1.add(jp5 = new JPanel(new FlowLayout(FlowLayout.LEFT)));
		
		jp4.add(jl1 = new JLabel("    IP Range"));
		jp4.add(jt1 = new JTextField(10));
		jp4.add(jl2 = new JLabel("to"));
		jp4.add(jt2 = new JTextField(10));
		jp4.add(jc1 = new JComboBox());
		for(int i=0; i<3; i++)
			jc1.addItem(jcb1[i]);
		jp4.add(jl5 = new JLabel(new ImageIcon(img2)));
		
		jp5.add(jl3 = new JLabel("Hostname"));
		jp5.add(jt3 = new JTextField(10));
		jp5.add(jb1 = new JButton("IP"));
		jp5.add(jc2 = new JComboBox());
		
		for(int i=0; i<8; i++)
			jc2.addItem(jcb2[i]);
		jp5.add(jb2 = new JButton(new ImageIcon(img5)));
		jb2.setText("Start");
		jp5.add(jl6 = new JLabel(new ImageIcon(img4)));
	
		dTM = new DefaultTableModel(null,colum);
		jt = new JTable(dTM);
		jt.getColumnModel().getColumn(0).setMinWidth(90);
		jp2.add(jsp = new JScrollPane(jt));
		
	}
	void window() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(480, 580);
		setLocationRelativeTo(null);
	}
	
	void Action() {
		jb2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (cnt == 0) {
					if(jt1.getText().equals("") || jt2.getText().equals(""))
						JOptionPane.showMessageDialog(null, "조사할 부분의 ip를 입력해주십시오.");
					else {
						cnt = 1;
						jb2.setIcon(new ImageIcon(img6));
						jb2.setText("Stop");
						hostname();
						addrow();
					}
				}else if(cnt == 1) {
					jb2.setIcon(new ImageIcon(img5));
					jb2.setText("Start");
					mt.interrupt();
					JOptionPane.showMessageDialog(null, "테이블에 데이터 추가를 종료합니다.");
					cnt = 0;
				}
			}
		});
	}
	
	void hostname() {
		try {
			InetAddress ip = InetAddress.getLocalHost();
			jt3.setText(ip.getHostName());
		} catch (UnknownHostException a) {
			a.printStackTrace();
		}
	}
	
	void go1(String e,int i) {
		q[i] = Integer.parseInt(e);
	}
	
	void go2(String e,int i) {
		p[i] = Integer.parseInt(e);
	}
	
	void addrow() {
		String jlt1[] = jt1.getText().split("\\.");
		String jlt2[] = jt2.getText().split("\\.");
		
		JOptionPane.showMessageDialog(null, jlt1);
		JOptionPane.showMessageDialog(null, jlt2);
		
		for(int i=0; i<4; i++) {
			go1(jlt1[i],i);
		}
		for(int i=0; i<4; i++) {
			go2(jlt2[i],i);
		}
		
//		for(int i=0; i<8; i++)
//			JOptionPane.showMessageDialog(null, q[i]);

		dTM.setRowCount(0);
		Object row[] = new Object[5];
	
		int u=-1;
		for(int h = q[0]; h <= p[0]; h++) {
			for(int r = q[1]; r <= p[1]; r++) {
				for(int j = q[2]; j <= p[2]; j++){
					for(int i = q[3]; i <= p[3]; i++){
						u=+1;
						
//						JLabel jimg = new JLabel(new ImageIcon(img3));
//						dTM.setValueAt(jimg, u, i);
//						row[0] = (new ImageIcon(img9)) + "" + h + "." + r + "." + j + "." + i;
						row[0] = h + "." + r + "." + j + "." + i;
						Frame.dTM.addRow(row);
						
					}
				}
			}
		}
		
		int y=-1;
		for(int h = q[0]; h <= p[0]; h++) {
			for(int r = q[1]; r <= p[1]; r++) {
				for(int j = q[2]; j <= p[2]; j++){
					for(int i = q[3]; i <= p[3]; i++){
						y+=1;
						String t = h+ "." + r + "." + j + "." + i;
						mt = new myThread(y, t);
						mt.start();
					}
				}
			}
			jb2.setIcon(new ImageIcon(img5));
			jb2.setText("Start");
		}
	}
	
}

class myThread extends Thread {
	
	String ip;
	int row1;
	String line;
	Object row[] = new Object[5];
	Pattern pattern;
	Matcher matcher;
	String name[] = {"\\d+ms","TTL=(\\w+)"};
	int a = 0;

	public myThread(int row, String ip) {
		this.ip = ip;
		this.row1 = row;
	}

	@Override
	public void run() {
		try {
			String line;
			InetAddress address = InetAddress.getByName(ip);
			Object row[] = new Object[5];

			if (address.isReachable(200)) {
				Runtime runtime = Runtime.getRuntime();
				Process p = runtime.exec("ping -a " + ip);
//				System.out.println("ping -a " + ip);
				InputStream is = p.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				while ((line = br.readLine()) != null) {
					Port po = new  Port(this.ip);
					row[4] = po.openPorts;
					//System.out.println(line);
					Pattern pattern = Pattern.compile("TTL=(\\w+)");
					Matcher matcher = pattern.matcher(line);
					if (matcher.find())
						row[2] = matcher.group(1);
					pattern = Pattern.compile("\\d+ms");
					matcher = pattern.matcher(line);
					if (matcher.find())
						row[1] = matcher.group(0);
					pattern = Pattern.compile("Ping\\s(.+.+)\\s32");
					matcher = pattern.matcher(line);
					pattern = Pattern.compile("Ping\\s(.+.+)\\s\\[");
					matcher = pattern.matcher(line);
					if (matcher.find())
						row[3] = matcher.group(1);
				}

			} else {
				row[1] = "[n/s]";
				row[2] = "[n/s]";
				row[3] = "[n/s]";
				row[4] = "[n/a]";
			}
			//PingExample2.jL[0].setText("Started" + ip);
			for (int i=1; i<5; i++)
			Frame.dTM.setValueAt(row[i], this.row1, i);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}