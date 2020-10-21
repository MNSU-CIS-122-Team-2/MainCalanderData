package trypackage;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

public class projecttry extends JFrame {
	public static void main(String[] args) {
		JButton first = new JButton("1");	JButton second = new JButton("2"); JButton third = new JButton("3");	JButton fourth = new JButton("4");
    	JButton fifth = new JButton("5");	JButton sixth = new JButton("6");	JButton seventh = new JButton("7");
    	JButton eighth = new JButton("8");	JButton ninth = new JButton("9");	JButton tenth = new JButton("10");
    	JButton eleventh = new JButton("11");	JButton twelvth = new JButton("12");	JButton thirteenth = new JButton("13");
    	JButton fourteenth = new JButton("14");	JButton fifteenth = new JButton("15");	JButton sixteenth = new JButton("16");
    	JButton seventeenth = new JButton("17");	JButton eighteenth = new JButton("18");	JButton nineteenth = new JButton("19");
    	JButton twentieth = new JButton("20");	JButton twentyfirst = new JButton("21");	JButton twentysecond = new JButton("22");
    	JButton twentythird = new JButton("23");	JButton twentyfourth = new JButton("24");	JButton twentyfifth = new JButton("25");
    	JButton twentysixth = new JButton("26");	JButton twentyseventh = new JButton("27");	JButton twentyeighth = new JButton("28");
    	JButton twentyninth = new JButton("29");	JButton thirtieth = new JButton("30");	JButton thirtyfirst = new JButton("31");
    	JButton thirtysecond = new JButton("1");
    	ArrayList<JButton> one= new ArrayList<JButton>();
    	one.add(first);one.add(second);one.add(third);one.add(fourth);
    	one.add(fifth);one.add(sixth);one.add(seventh);one.add(eighth);
    	one.add(ninth);one.add(tenth);one.add(eleventh);one.add(twelvth);
    	one.add(thirteenth);one.add(fourteenth);one.add(fifteenth);one.add(sixteenth);
    	one.add(seventeenth);one.add(eighteenth);one.add(nineteenth);one.add(twentieth);
    	one.add(twentyfirst);one.add(twentysecond);one.add(twentythird);one.add(twentyfourth);
    	one.add(twentyfifth);one.add(twentysixth);one.add(twentyseventh);one.add(twentyeighth);
    	one.add(twentyninth);one.add(thirtieth);one.add(thirtyfirst);one.add(thirtysecond);
    	for(int i=0;i<one.size();i++) {
    		one.get(i).setFocusable(false);
    		one.get(i).setFont(new Font("Arial", Font.PLAIN, 40));
    		one.get(i).setHorizontalAlignment(SwingConstants.RIGHT);
    		one.get(i).setVerticalAlignment(SwingConstants.BOTTOM);
    		one.get(i).setBorder(new LineBorder(Color.black));
    		one.get(i).setBackground(Color.LIGHT_GRAY);
    	}
    	first.setBounds(664,230,158,142);	second.setBounds(822,230,158,142);	third.setBounds(980,230,158,142);
    	fourth.setBounds(32,372,158,142);	fifth.setBounds(190,372,158,142);	sixth.setBounds(348,372,158,142);
    	seventh.setBounds(506,372,158,142);	eighth.setBounds(664,372,158,142);	ninth.setBounds(822,372,158,142);
    	tenth.setBounds(980,372,158,142);	eleventh.setBounds(32,514,158,142);	twelvth.setBounds(190,514,158,142);
    	thirteenth.setBounds(348,514,158,142);	fourteenth.setBounds(506,514,158,142);	fifteenth.setBounds(664,514,158,142);
    	sixteenth.setBounds(822,514,158,142);	seventeenth.setBounds(980,514,158,142);	eighteenth.setBounds(32,656,158,142);
    	nineteenth.setBounds(190,656,158,142);	twentieth.setBounds(348,656,158,142);	twentyfirst.setBounds(506,656,158,142);
    	twentysecond.setBounds(664,656,158,142);	twentythird.setBounds(822,656,158,142);	twentyfourth.setBounds(980,656,158,142);
    	twentyfifth.setBounds(32,798,158,142);	twentysixth.setBounds(190,798,158,142);	twentyseventh.setBounds(348,798,158,142);
    	twentyeighth.setBounds(506,798,158,142);	twentyninth.setBounds(664,798,158,142);	thirtieth.setBounds(822,798,158,142);
    	thirtyfirst.setBounds(980,798,158,142);
    	
        projecttry frame = new projecttry();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        Color color = panel1.getBackground();
    	JTextField October = new JTextField("October");
    	Font Month = new Font("Arial", Font.BOLD, 48);
    	October.setFont(Month);
    	October.setForeground(Color.black);
    	October.setBounds(565,30,200,50);
    	October.setEditable(false);	October.setBorder(BorderFactory.createLineBorder(color));
		panel1.setBounds(20,20,1130,200);
		panel1.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2.0f)));
		panel1.setLayout(new GridLayout(0, 1));
		panel2.setBounds(20,230,1130,710);
		panel2.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2.0f)));
		panel2.setLayout(new GridLayout(0, 1));
		frame.setSize(1200, 1000);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.add(October);
		for(int i=0;i<one.size();i++) {
			frame.add(one.get(i));}
		frame.add(panel1, BorderLayout.CENTER);
		frame.add(panel2, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
