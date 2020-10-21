package Calendar;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

public class Calendar extends JFrame {
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
    	
    	first.setFocusable(false); second.setFocusable(false);	third.setFocusable(false);	fourth.setFocusable(false);
    	fifth.setFocusable(false); sixth.setFocusable(false);	seventh.setFocusable(false);	eighth.setFocusable(false);
    	ninth.setFocusable(false); tenth.setFocusable(false);	eleventh.setFocusable(false);	twelvth.setFocusable(false);
    	thirteenth.setFocusable(false); fourteenth.setFocusable(false);	fifteenth.setFocusable(false);	sixteenth.setFocusable(false);
    	seventeenth.setFocusable(false); eighteenth.setFocusable(false);	nineteenth.setFocusable(false);	twentieth.setFocusable(false);
    	twentyfirst.setFocusable(false); twentysecond.setFocusable(false);	twentythird.setFocusable(false);	twentyfourth.setFocusable(false);
    	twentyfifth.setFocusable(false); twentysixth.setFocusable(false);	twentyseventh.setFocusable(false);	twentyeighth.setFocusable(false);
    	twentyninth.setFocusable(false); thirtieth.setFocusable(false);	thirtyfirst.setFocusable(false);
    	
    	first.setFont(new Font("Arial", Font.PLAIN, 40));	first.setHorizontalAlignment(SwingConstants.RIGHT);	first.setVerticalAlignment(SwingConstants.BOTTOM);
    	second.setFont(new Font("Arial", Font.PLAIN, 40));	second.setHorizontalAlignment(SwingConstants.RIGHT);	second.setVerticalAlignment(SwingConstants.BOTTOM);
    	third.setFont(new Font("Arial", Font.PLAIN, 40));	third.setHorizontalAlignment(SwingConstants.RIGHT);	third.setVerticalAlignment(SwingConstants.BOTTOM);
    	fourth.setFont(new Font("Arial", Font.PLAIN, 40));	fourth.setHorizontalAlignment(SwingConstants.RIGHT);	fourth.setVerticalAlignment(SwingConstants.BOTTOM);
    	fifth.setFont(new Font("Arial", Font.PLAIN, 40));	fifth.setHorizontalAlignment(SwingConstants.RIGHT);	fifth.setVerticalAlignment(SwingConstants.BOTTOM);
    	sixth.setFont(new Font("Arial", Font.PLAIN, 40));	sixth.setHorizontalAlignment(SwingConstants.RIGHT);	sixth.setVerticalAlignment(SwingConstants.BOTTOM);
    	seventh.setFont(new Font("Arial", Font.PLAIN, 40));	seventh.setHorizontalAlignment(SwingConstants.RIGHT);	seventh.setVerticalAlignment(SwingConstants.BOTTOM);
    	eighth.setFont(new Font("Arial", Font.PLAIN, 40));	eighth.setHorizontalAlignment(SwingConstants.RIGHT);	eighth.setVerticalAlignment(SwingConstants.BOTTOM);
    	ninth.setFont(new Font("Arial", Font.PLAIN, 40));	ninth.setHorizontalAlignment(SwingConstants.RIGHT);	ninth.setVerticalAlignment(SwingConstants.BOTTOM);
    	tenth.setFont(new Font("Arial", Font.PLAIN, 40));	tenth.setHorizontalAlignment(SwingConstants.RIGHT);	tenth.setVerticalAlignment(SwingConstants.BOTTOM);
    	eleventh.setFont(new Font("Arial", Font.PLAIN, 40));	eleventh.setHorizontalAlignment(SwingConstants.RIGHT);	eleventh.setVerticalAlignment(SwingConstants.BOTTOM);
    	twelvth.setFont(new Font("Arial", Font.PLAIN, 40));	twelvth.setHorizontalAlignment(SwingConstants.RIGHT);	twelvth.setVerticalAlignment(SwingConstants.BOTTOM);
    	thirteenth.setFont(new Font("Arial", Font.PLAIN, 40));	thirteenth.setHorizontalAlignment(SwingConstants.RIGHT);	thirteenth.setVerticalAlignment(SwingConstants.BOTTOM);
    	fourteenth.setFont(new Font("Arial", Font.PLAIN, 40));	fourteenth.setHorizontalAlignment(SwingConstants.RIGHT);	fourteenth.setVerticalAlignment(SwingConstants.BOTTOM);
    	fifteenth.setFont(new Font("Arial", Font.PLAIN, 40));	fifteenth.setHorizontalAlignment(SwingConstants.RIGHT);	fifteenth.setVerticalAlignment(SwingConstants.BOTTOM);
    	sixteenth.setFont(new Font("Arial", Font.PLAIN, 40));	sixteenth.setHorizontalAlignment(SwingConstants.RIGHT);	sixteenth.setVerticalAlignment(SwingConstants.BOTTOM);
    	seventeenth.setFont(new Font("Arial", Font.PLAIN, 40));	seventeenth.setHorizontalAlignment(SwingConstants.RIGHT);	seventeenth.setVerticalAlignment(SwingConstants.BOTTOM);
    	eighteenth.setFont(new Font("Arial", Font.PLAIN, 40));	eighteenth.setHorizontalAlignment(SwingConstants.RIGHT);	eighteenth.setVerticalAlignment(SwingConstants.BOTTOM);
    	nineteenth.setFont(new Font("Arial", Font.PLAIN, 40));	nineteenth.setHorizontalAlignment(SwingConstants.RIGHT);	nineteenth.setVerticalAlignment(SwingConstants.BOTTOM);
    	twentieth.setFont(new Font("Arial", Font.PLAIN, 40));	twentieth.setHorizontalAlignment(SwingConstants.RIGHT);	twentieth.setVerticalAlignment(SwingConstants.BOTTOM);
    	twentyfirst.setFont(new Font("Arial", Font.PLAIN, 40));	twentyfirst.setHorizontalAlignment(SwingConstants.RIGHT);	twentyfirst.setVerticalAlignment(SwingConstants.BOTTOM);
    	twentysecond.setFont(new Font("Arial", Font.PLAIN, 40));	twentysecond.setHorizontalAlignment(SwingConstants.RIGHT);	twentysecond.setVerticalAlignment(SwingConstants.BOTTOM);
    	twentythird.setFont(new Font("Arial", Font.PLAIN, 40));	twentythird.setHorizontalAlignment(SwingConstants.RIGHT);	twentythird.setVerticalAlignment(SwingConstants.BOTTOM);
    	twentyfourth.setFont(new Font("Arial", Font.PLAIN, 40));	twentyfourth.setHorizontalAlignment(SwingConstants.RIGHT);	twentyfourth.setVerticalAlignment(SwingConstants.BOTTOM);
    	twentyfifth.setFont(new Font("Arial", Font.PLAIN, 40));	twentyfifth.setHorizontalAlignment(SwingConstants.RIGHT);	twentyfifth.setVerticalAlignment(SwingConstants.BOTTOM);
    	twentysixth.setFont(new Font("Arial", Font.PLAIN, 40));	twentysixth.setHorizontalAlignment(SwingConstants.RIGHT);	twentysixth.setVerticalAlignment(SwingConstants.BOTTOM);
    	twentyseventh.setFont(new Font("Arial", Font.PLAIN, 40));	twentyseventh.setHorizontalAlignment(SwingConstants.RIGHT);	twentyseventh.setVerticalAlignment(SwingConstants.BOTTOM);
    	twentyeighth.setFont(new Font("Arial", Font.PLAIN, 40));	twentyeighth.setHorizontalAlignment(SwingConstants.RIGHT);	twentyeighth.setVerticalAlignment(SwingConstants.BOTTOM);
    	twentyninth.setFont(new Font("Arial", Font.PLAIN, 40));	twentyninth.setHorizontalAlignment(SwingConstants.RIGHT);	twentyninth.setVerticalAlignment(SwingConstants.BOTTOM);
    	thirtieth.setFont(new Font("Arial", Font.PLAIN, 40));	thirtieth.setHorizontalAlignment(SwingConstants.RIGHT);	thirtieth.setVerticalAlignment(SwingConstants.BOTTOM);
    	thirtyfirst.setFont(new Font("Arial", Font.PLAIN, 40));	thirtyfirst.setHorizontalAlignment(SwingConstants.RIGHT);	thirtyfirst.setVerticalAlignment(SwingConstants.BOTTOM);

    	
    	first.setBorder(new LineBorder(Color.black));	second.setBorder(new LineBorder(Color.black));	third.setBorder(new LineBorder(Color.black));
    	fourth.setBorder(new LineBorder(Color.black));	fifth.setBorder(new LineBorder(Color.black));	sixth.setBorder(new LineBorder(Color.black));
    	seventh.setBorder(new LineBorder(Color.black));	eighth.setBorder(new LineBorder(Color.black));	ninth.setBorder(new LineBorder(Color.black));
    	tenth.setBorder(new LineBorder(Color.black));	eleventh.setBorder(new LineBorder(Color.black));	twelvth.setBorder(new LineBorder(Color.black));
    	thirteenth.setBorder(new LineBorder(Color.black));	fourteenth.setBorder(new LineBorder(Color.black));	fifteenth.setBorder(new LineBorder(Color.black));
    	sixteenth.setBorder(new LineBorder(Color.black));	seventeenth.setBorder(new LineBorder(Color.black));	eighteenth.setBorder(new LineBorder(Color.black));
    	nineteenth.setBorder(new LineBorder(Color.black));	twentieth.setBorder(new LineBorder(Color.black));	twentyfirst.setBorder(new LineBorder(Color.black));
    	twentysecond.setBorder(new LineBorder(Color.black));	twentythird.setBorder(new LineBorder(Color.black));	twentyfourth.setBorder(new LineBorder(Color.black));    
    	twentyfifth.setBorder(new LineBorder(Color.black));	twentysixth.setBorder(new LineBorder(Color.black));	twentyseventh.setBorder(new LineBorder(Color.black));
    	twentyeighth.setBorder(new LineBorder(Color.black));	twentyninth.setBorder(new LineBorder(Color.black));	thirtieth.setBorder(new LineBorder(Color.black));
    	thirtyfirst.setBorder(new LineBorder(Color.black));
    	
    	first.setBackground(Color.LIGHT_GRAY);	second.setBackground(Color.LIGHT_GRAY);	third.setBackground(Color.LIGHT_GRAY);
    	fourth.setBackground(Color.LIGHT_GRAY);	fifth.setBackground(Color.LIGHT_GRAY);	sixth.setBackground(Color.LIGHT_GRAY);
    	seventh.setBackground(Color.LIGHT_GRAY);	eighth.setBackground(Color.LIGHT_GRAY);	ninth.setBackground(Color.LIGHT_GRAY);
    	tenth.setBackground(Color.LIGHT_GRAY);	eleventh.setBackground(Color.LIGHT_GRAY);	twelvth.setBackground(Color.LIGHT_GRAY);
    	thirteenth.setBackground(Color.LIGHT_GRAY);	fourteenth.setBackground(Color.LIGHT_GRAY);	fifteenth.setBackground(Color.LIGHT_GRAY);
    	sixteenth.setBackground(Color.LIGHT_GRAY);	seventeenth.setBackground(Color.LIGHT_GRAY);	eighteenth.setBackground(Color.LIGHT_GRAY);
    	nineteenth.setBackground(Color.LIGHT_GRAY);	twentieth.setBackground(Color.LIGHT_GRAY);	twentyfirst.setBackground(Color.LIGHT_GRAY);
    	twentysecond.setBackground(Color.LIGHT_GRAY);	twentythird.setBackground(Color.LIGHT_GRAY);	twentyfourth.setBackground(Color.LIGHT_GRAY);    
    	twentyfifth.setBackground(Color.LIGHT_GRAY);	twentysixth.setBackground(Color.LIGHT_GRAY);	twentyseventh.setBackground(Color.LIGHT_GRAY); 
    	twentyeighth.setBackground(Color.LIGHT_GRAY);	twentyninth.setBackground(Color.LIGHT_GRAY);	thirtieth.setBackground(Color.LIGHT_GRAY); 
    	thirtyfirst.setBackground(Color.LIGHT_GRAY);
    	
    	
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
    	
        Calendar frame = new Calendar();
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
		frame.add(first);	frame.add(second);	frame.add(third);	frame.add(fourth);	frame.add(fifth);	frame.add(sixth);
		frame.add(seventh);	frame.add(eighth);	frame.add(ninth);	frame.add(tenth);	frame.add(eleventh);	frame.add(twelvth);
		frame.add(thirteenth);	frame.add(fourteenth);	frame.add(fifteenth);	frame.add(sixteenth);	frame.add(seventeenth);	
		frame.add(eighteenth);	frame.add(nineteenth);	frame.add(twentieth);	frame.add(twentyfirst);	frame.add(twentysecond);
		frame.add(twentythird);	frame.add(twentyfourth);	frame.add(twentyfifth);	frame.add(twentysixth);	frame.add(twentyseventh);
		frame.add(twentyeighth);	frame.add(twentyninth);	frame.add(thirtieth);	frame.add(thirtyfirst);
		frame.add(panel1, BorderLayout.CENTER);
		frame.add(panel2, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
    }
}