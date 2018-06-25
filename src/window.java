import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.BevelBorder;

public class window {
	
	String menu[] = {"Scan","Go to","Commands","Favorites","Tools","Help"};
	JMenu jm[] = new JMenu[6];
	static JMenuBar menubar;
	static Frame fr;
	
	String item1[] = { "Load from file...", "Export all...", "Export selection", "Quit"};
	String item2[] = {"Next alive host","Next open port", "Next dead host", "Previous alive host", 
			"Previous open port", "Previous dead host","Find..."};
	String item3[] = {"Show details", "Rescan IP(s)", "Delete IP(s)", "Copy IP", "Copy details", "Open"};
	String item4[] = {"Add current", "Manage favorites"};
	String item5[] = {"Preferences...", "Fetchers...", "Selection", "Scan statistics"};
	String item6[] = {"Getting Started", "Official Website", "FAQ", "Report an issue", "Plugins", "Command-line usage",
			"Check for newer version...", "About"};
	
	JMenuItem jmt1[] = new JMenuItem[4];
	JMenuItem jmt2[] = new JMenuItem[7];
	JMenuItem jmt3[] = new JMenuItem[6];
	JMenuItem jmt4[] = new JMenuItem[2];
	JMenuItem jmt5[] = new JMenuItem[4];
	JMenuItem jmt6[] = new JMenuItem[8];
	
	public window() {
		
		menubar = new JMenuBar();
		
		for(int i=0; i<6; i++) {
			menubar.add(jm[i] = new JMenu(menu[i]));
		}
		
		for(int i=0; i<4; i++) {
			jm[0].add(jmt1[i] = new JMenuItem(item1[i]));
			if (i==2)
				jm[0].addSeparator();
			if	(i==3) {
				jmt1[i].setAccelerator(KeyStroke.getKeyStroke('L', Event.CTRL_MASK));
				jmt1[i].setAccelerator(KeyStroke.getKeyStroke('Q', Event.CTRL_MASK));
			}
		}
		
		for(int i=0; i<7; i++) {
			jm[1].add(jmt2[i] = new JMenuItem(item2[i]));
			if (i==2 || i==5)
			jm[1].addSeparator();
			if (i == 6) {
				jmt2[0].setAccelerator(KeyStroke.getKeyStroke('H', Event.CTRL_MASK));
				jmt2[1].setAccelerator(KeyStroke.getKeyStroke('J', Event.CTRL_MASK));
				jmt2[2].setAccelerator(KeyStroke.getKeyStroke('K', Event.CTRL_MASK));
				jmt2[3].setAccelerator(KeyStroke.getKeyStroke('H', Event.CTRL_MASK+Event.SHIFT_MASK));
				jmt2[4].setAccelerator(KeyStroke.getKeyStroke('J', Event.CTRL_MASK+Event.SHIFT_MASK));
				jmt2[5].setAccelerator(KeyStroke.getKeyStroke('K', Event.CTRL_MASK+Event.SHIFT_MASK));
				jmt2[6].setAccelerator(KeyStroke.getKeyStroke('F', Event.CTRL_MASK));
			}
		}
		
		for(int i=0; i<6; i++) {
			jm[2].add(jmt3[i] = new JMenuItem(item3[i]));
			if (i==0 || i==2 || i==4)
				jm[2].addSeparator();
			if	(i==3) {
				jmt3[i].setAccelerator(KeyStroke.getKeyStroke('R', Event.CTRL_MASK));
			// del 이 떠야함
				jmt3[i].setAccelerator(KeyStroke.getKeyStroke('D',Event.DELETE));
				jmt3[i].setAccelerator(KeyStroke.getKeyStroke('C', Event.CTRL_MASK));
			}
		}
		
		for(int i=0; i<2; i++) {
			jm[3].add(jmt4[i] = new JMenuItem(item4[i]));
			if	(i==0)
				jmt4[i].setAccelerator(KeyStroke.getKeyStroke('C', Event.CTRL_MASK));
		}
		
		for(int i=0; i<4; i++) {
			jm[4].add(jmt5[i] = new JMenuItem(item5[i]));
			if (i==1)
			jm[4].addSeparator();
		}
		
		for(int i=0; i<8; i++) {
			jm[5].add(jmt6[i] = new JMenuItem(item6[i]));
			if (i==0 || i==4 || i==5 || i==6)
			jm[5].addSeparator();
		}
		
//		setLayout(new BorderLayout());
//		
//		JPanel jp1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		
//		
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		setSize(700, 650);
//	}
//
//	public static void main(String[] args) {
//		new window().setVisible(true);
//
//	}
	}
	void add() {
		
		JLabel jw1 = new JLabel("Ready");
		jw1.setBorder(new BevelBorder(BevelBorder.RAISED));
		jw1.setPreferredSize(new Dimension(120,20));
		jw1.setFont(new Font("Serif", Font.BOLD, 15));
		
		JLabel jw2 = new JLabel("Display All");
		jw2.setBorder(new BevelBorder(BevelBorder.RAISED));
		jw2.setFont(new Font("Serif", Font.BOLD, 15));
		jw2.setPreferredSize(new Dimension(100,20));
		
		JLabel jw3 = new JLabel("Thread: 0");
		jw3.setBorder(new BevelBorder(BevelBorder.RAISED));
		jw3.setPreferredSize(new Dimension(100,20));
		jw3.setFont(new Font("Serif", Font.BOLD, 15));
		
		fr.jp3.add(jw1);
		fr.jp3.add(jw2);
		fr.jp3.add(jw3);
	}
}
