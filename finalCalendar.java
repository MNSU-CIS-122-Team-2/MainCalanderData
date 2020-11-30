package Calendar;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.StyleConstants;


import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;


// Implementing the log in frame into the calendar
public class classproject extends JFrame {
	//creating an array with the name of the months
	static String[] months= {"January","February","March","April","May","June","July","August","September","October","November","December"};
	static String[] weekDays = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
	static String[] days = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", 
							"17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31",};
	static JTextField textfield1;
	static JTextField textfield2;
	static JTextField textfield3;

//	public void populateEvents() throws ClassNotFoundException, SQLException {
//		ArrayList<String> populate = new ArrayList<String>();
//		RetriveEvent user = new RetriveEvent("nathan.mnsu");
//		user.retriveEventForUser();
//		}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		//Creating the buttons that will be implemented in the log in frame
		JButton create = new JButton("Don't have an account?");	
		JButton login = new JButton("log in");	
		JButton Creating= new JButton("Create your account");
    //creating an arraylist and adding the buttons to it, making it easy to get them all to be as similar as possible
    	ArrayList<JButton> buttons= new ArrayList<JButton>();
    
    	buttons.add(create);
    	buttons.add(login);
    	buttons.add(Creating);
    	
    	//Creating  a for loop to give all the buttons a similar set up
    	for(int i=0;i<buttons.size();i++) {
    		
    		buttons.get(i).setFocusable(false);
    		
    		buttons.get(i).setFont(new Font("Arial", Font.PLAIN, 60));
    		
    		buttons.get(i).setHorizontalAlignment(SwingConstants.RIGHT);
    		buttons.get(i).setVerticalAlignment(SwingConstants.BOTTOM);
    		
    		buttons.get(i).setBorder(new LineBorder(Color.black));
    		//creating a light gray background color for all of the days
    		buttons.get(i).setBackground(Color.LIGHT_GRAY);
    	}
    	//giving the buttons their location
    	create.setBounds(375,500,1000,70);	login.setBounds(750,650,250,60);;
    	//creating a new frame
        classproject frame = new classproject();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //Creating textfields to give the user instructions on the log in
        JTextField username=new JTextField("Enter username:");
        JTextField password=new JTextField("Enter password:");
        //creating a title for the frame
        JTextField title=new JTextField("Enter your account");
        //creating textfields where the user will enter information in order to log in
        JTextField passwordset=new JTextField("");
        JTextField userset=new JTextField("");
        //setting the color of the border of the textfields to black to make the fields easy to locate
        passwordset.setBorder(new LineBorder(Color.black));
        userset.setBorder(new LineBorder(Color.black));
       username.setBorder(new LineBorder(Color.black));
       password.setBorder(new LineBorder(Color.black));
       //giving the textfields a location
    	username.setBounds(200, 300, 700, 60);
    	userset.setBounds(1100, 300, 600, 60);
    	password.setBounds(200, 400, 700, 60);
    	passwordset.setBounds(1100, 400, 600, 60);
    	title.setBounds(400, 100, 1300, 142);
    	
    	
    	// indicating which textfields we do not want the user to be able to edit
    	title.setEditable(false);
    	username.setEditable(false);
    	password.setEditable(false);
    	//giving the title a new bigger font
    	title.setFont(new Font("Arial", Font.PLAIN, 120));
    	username.setFont(new Font("Arial", Font.PLAIN, 60));
    	password.setFont(new Font("Arial", Font.PLAIN, 60));
    	userset.setFont(new Font("Arial", Font.PLAIN, 60));
    	passwordset.setFont(new Font("Arial", Font.PLAIN, 60));
    	//adding the buttons and text fields to the frame
    	frame.add(password);
    	frame.add(username);
    	frame.add(title);
    	frame.add(passwordset);
    	frame.add(userset);
    
		//set the frame size
		//frame.setSize(1050, 700);
		frame.setLayout(null);
		frame.setVisible(true);
		
		
		

		//adding each button that we want on this frame
		frame.add(create);
		frame.add(login);

		// disposing the JFrame when the user hits the close button
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// giving the log in button an action listener that will check the username and password
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent login){
				
				//using try statement to catch exceptions
				try {
					
					String h_Pass = "";
                    String salt ="";
                    GetSaltAndHash salt_And_Hash = new GetSaltAndHash(userset.getText());
                    ResultSet pulledSaltAndHash = salt_And_Hash.RetriveSAndH();
                    while (pulledSaltAndHash.next()) {
                        h_Pass=pulledSaltAndHash.getString("H_PASS");
                        salt=pulledSaltAndHash.getString("SALT");
                    }
                    
                    if(h_Pass=="") {
                    	userset.setText("invalid");
                    	passwordset.setText("invalid");
                    }
					//using a process builder to open the python file specified and run it
                    ProcessBuilder processbuilder = new ProcessBuilder("py","c:\\\\Calendar\\\\Validate.py",""+h_Pass,""+salt,""+passwordset.getText());
					//telling the process builder to start running it
					Process process = processbuilder.start();
					//using  a buffered reader to read the output of the processbuilder
					BufferedReader buffer = new BufferedReader(new InputStreamReader(process.getInputStream()));
					// giving a string the value of the output of the python file so its easy to compare
					String check = buffer.readLine();
					//printing out the result
					System.out.println(check);
					//if the result is valid the program will open a new frame with the calendar of the user
					if(check.equals("Valid")) {
						
					frame.dispose();
					
					
				
					
					
				
				

		//creating a new gregorian calendar to help set the current day and year
		GregorianCalendar grego=new GregorianCalendar();
		//creating the buttons for each day of the month
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
  	JButton colors=new JButton("Colors");  JButton share= new JButton("!");  JButton refresh=new JButton("refresh");
  	JButton thirtysecond = new JButton("1");	JButton addEvent = new JButton("+"); // creating a button to add events
  	//creating an array list to put the buttons representing each day
  	ArrayList<JButton> one= new ArrayList<JButton>();
  	//adding all of the buttons
  	one.add(first);one.add(second);one.add(third);one.add(fourth);
  	one.add(fifth);one.add(sixth);one.add(seventh);one.add(eighth);
  	one.add(ninth);one.add(tenth);one.add(eleventh);one.add(twelvth);
  	one.add(thirteenth);one.add(fourteenth);one.add(fifteenth);one.add(sixteenth);
  	one.add(seventeenth);one.add(eighteenth);one.add(nineteenth);one.add(twentieth);
  	one.add(twentyfirst);one.add(twentysecond);one.add(twentythird);one.add(twentyfourth);
  	one.add(twentyfifth);one.add(twentysixth);one.add(twentyseventh);one.add(twentyeighth);
  	one.add(twentyninth);one.add(thirtieth);one.add(thirtyfirst);one.add(thirtysecond);
  	//creating a for loop to get the same results for each button
  	for(int i=0;i<one.size();i++) {
  		//setting the focusable to false so they cannot gain focus
  		one.get(i).setFocusable(false);
  		//giving the information in each button a font
  		one.get(i).setFont(new Font("Arial", Font.PLAIN, 40));
  		//align the info of the button to the right and to the button
  		one.get(i).setHorizontalAlignment(SwingConstants.RIGHT);
  		one.get(i).setVerticalAlignment(SwingConstants.BOTTOM);
  		//creating a border of color black
  		one.get(i).setBorder(new LineBorder(Color.black));
  		//creating a light gray background color for all of the days
  		one.get(i).setBackground(Color.LIGHT_GRAY);
  	}
  	
  	colors.setFocusable(false);
	colors.setFont(new Font("Arial", Font.PLAIN, 40));
	colors.setHorizontalAlignment(SwingConstants.RIGHT);
	colors.setVerticalAlignment(SwingConstants.BOTTOM);
	colors.setBorder(new LineBorder(Color.black));
	colors.setBackground(Color.LIGHT_GRAY);
	
	refresh.setFocusable(false);
	refresh.setFont(new Font("Arial", Font.PLAIN, 40));
	refresh.setHorizontalAlignment(SwingConstants.RIGHT);
	refresh.setVerticalAlignment(SwingConstants.BOTTOM);
	refresh.setBorder(new LineBorder(Color.black));
	refresh.setBackground(Color.LIGHT_GRAY);
	
  	
  	// Implementing an '+' icon to add events to the calendar
  	addEvent.setFocusable(false);
  	addEvent.setFont(new Font("Arial", Font.PLAIN, 60));
  	addEvent.setHorizontalAlignment(SwingConstants.CENTER);
  	addEvent.setVerticalAlignment(SwingConstants.CENTER);
  	addEvent.setBorder(new LineBorder(Color.black));
  	addEvent.setForeground(Color.BLACK);
  	addEvent.setBounds(1010,120, 100, 100);

  	//changing the color of the current day so it is visible
  	one.get(grego.getTime().getDate()-1).setBackground(Color.cyan);
  	//locating each button into the Jframe

    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.DAY_OF_MONTH, 1);
    if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
    	first.setBounds(32,230,158,142);	second.setBounds(190,230,158,142);	third.setBounds(348,230,158,142);
    	fourth.setBounds(506,230,158,142);	fifth.setBounds(664,230,158,142);	sixth.setBounds(822,230,158,142);
    	seventh.setBounds(980,230,158,142);	eighth.setBounds(32,372,158,142);	ninth.setBounds(190,372,158,142);
    	tenth.setBounds(348,372,158,142);	eleventh.setBounds(506,372,158,142);	twelvth.setBounds(664,372,158,142);
    	thirteenth.setBounds(822,372,158,142);	fourteenth.setBounds(980,372,158,142);	fifteenth.setBounds(32,514,158,142);
    	sixteenth.setBounds(190,514,158,142);	seventeenth.setBounds(348,514,158,142);	eighteenth.setBounds(506,514,158,142);
    	nineteenth.setBounds(664,514,158,142);	twentieth.setBounds(822,514,158,142);	twentyfirst.setBounds(980,514,158,142);
    	twentysecond.setBounds(32,656,158,142);	twentythird.setBounds(190,656,158,142);	twentyfourth.setBounds(348,656,158,142);
    	twentyfifth.setBounds(506,656,158,142);	twentysixth.setBounds(664,656,158,142);	twentyseventh.setBounds(822,656,158,142);
    	twentyeighth.setBounds(980,656,158,142);	twentyninth.setBounds(32,798,158,142);	thirtieth.setBounds(190,798,158,142);
    	thirtyfirst.setBounds(348,798,158,142); 	
    }
    if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
    	first.setBounds(190,230,158,142);	second.setBounds(348,230,158,142);	third.setBounds(506,230,158,142);
    	fourth.setBounds(664,230,158,142);	fifth.setBounds(822,230,158,142);	sixth.setBounds(980,230,158,142);
    	seventh.setBounds(32,372,158,142);	eighth.setBounds(190,372,158,142);	ninth.setBounds(348,372,158,142);
    	tenth.setBounds(506,372,158,142);	eleventh.setBounds(664,372,158,142);	twelvth.setBounds(822,372,158,142);
    	thirteenth.setBounds(980,372,158,142);	fourteenth.setBounds(32,514,158,142);	fifteenth.setBounds(190,514,158,142);
    	sixteenth.setBounds(348,514,158,142);	seventeenth.setBounds(506,514,158,142);	eighteenth.setBounds(664,514,158,142);
    	nineteenth.setBounds(822,514,158,142);	twentieth.setBounds(980,514,158,142);	twentyfirst.setBounds(32,656,158,142);
    	twentysecond.setBounds(190,656,158,142);	twentythird.setBounds(348,656,158,142);	twentyfourth.setBounds(506,656,158,142);
    	twentyfifth.setBounds(664,656,158,142);	twentysixth.setBounds(822,656,158,142);	twentyseventh.setBounds(980,656,158,142);
    	twentyeighth.setBounds(32,798,158,142);	twentyninth.setBounds(190,798,158,142);	thirtieth.setBounds(348,798,158,142);
    	thirtyfirst.setBounds(506,798,158,142); 	
    }
    if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
    	first.setBounds(348,230,158,142);	second.setBounds(506,230,158,142);	third.setBounds(664,230,158,142);
    	fourth.setBounds(822,230,158,142);	fifth.setBounds(980,230,158,142);	sixth.setBounds(32,372,158,142);
    	seventh.setBounds(190,372,158,142);	eighth.setBounds(348,372,158,142);	ninth.setBounds(506,372,158,142);
    	tenth.setBounds(664,372,158,142);	eleventh.setBounds(822,372,158,142);	twelvth.setBounds(980,372,158,142);
    	thirteenth.setBounds(32,514,158,142);	fourteenth.setBounds(190,514,158,142);	fifteenth.setBounds(348,514,158,142);
    	sixteenth.setBounds(506,514,158,142);	seventeenth.setBounds(664,514,158,142);	eighteenth.setBounds(822,514,158,142);
    	nineteenth.setBounds(980,514,158,142);	twentieth.setBounds(32,656,158,142);	twentyfirst.setBounds(190,656,158,142);
    	twentysecond.setBounds(348,656,158,142);	twentythird.setBounds(506,656,158,142);	twentyfourth.setBounds(664,656,158,142);
    	twentyfifth.setBounds(822,656,158,142);	twentysixth.setBounds(980,656,158,142);	twentyseventh.setBounds(32,798,158,142);
    	twentyeighth.setBounds(190,798,158,142);	twentyninth.setBounds(348,798,158,142);	thirtieth.setBounds(506,798,158,142);
    	thirtyfirst.setBounds(664,798,158,142); 	
    }
    if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
    	first.setBounds(506,230,158,142);	second.setBounds(664,230,158,142);	third.setBounds(822,230,158,142);
    	fourth.setBounds(980,230,158,142);	fifth.setBounds(32,372,158,142);	sixth.setBounds(190,372,158,142);
    	seventh.setBounds(348,372,158,142);	eighth.setBounds(506,372,158,142);	ninth.setBounds(664,372,158,142);
    	tenth.setBounds(822,372,158,142);	eleventh.setBounds(980,372,158,142);	twelvth.setBounds(32,514,158,142);
    	thirteenth.setBounds(190,514,158,142);	fourteenth.setBounds(348,514,158,142);	fifteenth.setBounds(506,514,158,142);
    	sixteenth.setBounds(664,514,158,142);	seventeenth.setBounds(822,514,158,142);	eighteenth.setBounds(980,514,158,142);
    	nineteenth.setBounds(32,656,158,142);	twentieth.setBounds(190,656,158,142);	twentyfirst.setBounds(348,656,158,142);
    	twentysecond.setBounds(506,656,158,142);	twentythird.setBounds(664,656,158,142);	twentyfourth.setBounds(822,656,158,142);
    	twentyfifth.setBounds(980,656,158,142);	twentysixth.setBounds(32,798,158,142);	twentyseventh.setBounds(190,798,158,142);
    	twentyeighth.setBounds(348,798,158,142);	twentyninth.setBounds(506,798,158,142);	thirtieth.setBounds(664,798,158,142);
    	thirtyfirst.setBounds(822,798,158,142); 	
    }
    if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
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
    }
    if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
    	first.setBounds(822,230,158,142);	second.setBounds(980,230,158,142);	third.setBounds(32,372,158,142);
    	fourth.setBounds(190,372,158,142);	fifth.setBounds(348,372,158,142);	sixth.setBounds(506,372,158,142);
    	seventh.setBounds(664,372,158,142);	eighth.setBounds(822,372,158,142);	ninth.setBounds(980,372,158,142);
    	tenth.setBounds(32,514,158,142);	eleventh.setBounds(190,514,158,142);	twelvth.setBounds(348,514,158,142);
    	thirteenth.setBounds(506,514,158,142);	fourteenth.setBounds(664,514,158,142);	fifteenth.setBounds(822,514,158,142);
    	sixteenth.setBounds(980,514,158,142);	seventeenth.setBounds(32,656,158,142);	eighteenth.setBounds(190,656,158,142);
    	nineteenth.setBounds(348,656,158,142);	twentieth.setBounds(506,656,158,142);	twentyfirst.setBounds(664,656,158,142);
    	twentysecond.setBounds(822,656,158,142);	twentythird.setBounds(980,656,158,142);	twentyfourth.setBounds(32,798,158,142);
    	twentyfifth.setBounds(190,798,158,142);	twentysixth.setBounds(348,798,158,142);	twentyseventh.setBounds(506,798,158,142);
    	twentyeighth.setBounds(664,798,158,142);	twentyninth.setBounds(822,798,158,142);	thirtieth.setBounds(980,798,158,142);
    	thirtyfirst.setBounds(32,940,158,142); 	
    }
    if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
    	first.setBounds(980,230,158,142);	second.setBounds(32,372,158,142);	third.setBounds(190,372,158,142);
    	fourth.setBounds(348,372,158,142);	fifth.setBounds(506,372,158,142);	sixth.setBounds(664,372,158,142);
    	seventh.setBounds(822,372,158,142);	eighth.setBounds(980,372,158,142);	ninth.setBounds(32,514,158,142);
    	tenth.setBounds(190,514,158,142);	eleventh.setBounds(348,514,158,142);	twelvth.setBounds(506,514,158,142);
    	thirteenth.setBounds(664,514,158,142);	fourteenth.setBounds(822,514,158,142);	fifteenth.setBounds(980,514,158,142);
    	sixteenth.setBounds(32,656,158,142);	seventeenth.setBounds(190,656,158,142);	eighteenth.setBounds(348,656,158,142);
    	nineteenth.setBounds(506,656,158,142);	twentieth.setBounds(664,656,158,142);	twentyfirst.setBounds(822,656,158,142);
    	twentysecond.setBounds(980,656,158,142);	twentythird.setBounds(32,798,158,142);	twentyfourth.setBounds(190,798,158,142);
    	twentyfifth.setBounds(348,798,158,142);	twentysixth.setBounds(506,798,158,142);	twentyseventh.setBounds(664,798,158,142);
    	twentyeighth.setBounds(822,798,158,142);	twentyninth.setBounds(980,798,158,142);	thirtieth.setBounds(32,940,158,142);
    	thirtyfirst.setBounds(190,940,158,142); 	
    }
  	
  	first.setLayout(new BorderLayout()); second.setLayout(new BorderLayout()); third.setLayout(new BorderLayout()); 
  	fourth.setLayout(new BorderLayout()); fifth.setLayout(new BorderLayout()); sixth.setLayout(new BorderLayout());
  	seventh.setLayout(new BorderLayout()); eighth.setLayout(new BorderLayout()); ninth.setLayout(new BorderLayout());
  	tenth.setLayout(new BorderLayout()); eleventh.setLayout(new BorderLayout()); twelvth.setLayout(new BorderLayout());
  	thirteenth.setLayout(new BorderLayout()); fourteenth.setLayout(new BorderLayout()); fifteenth.setLayout(new BorderLayout());
  	sixteenth.setLayout(new BorderLayout()); seventeenth.setLayout(new BorderLayout()); eighteenth.setLayout(new BorderLayout());
  	nineteenth.setLayout(new BorderLayout()); twentieth.setLayout(new BorderLayout()); twentyfirst.setLayout(new BorderLayout());
  	twentysecond.setLayout(new BorderLayout()); twentythird.setLayout(new BorderLayout()); twentyfourth.setLayout(new BorderLayout());
  	twentyfifth.setLayout(new BorderLayout()); twentysixth.setLayout(new BorderLayout()); twentyseventh.setLayout(new BorderLayout());
  	twentyeighth.setLayout(new BorderLayout()); twentyninth.setLayout(new BorderLayout()); thirtieth.setLayout(new BorderLayout());
  	thirtyfirst.setLayout(new BorderLayout());
  	
  	// Create new labels to apply to day JButtons. If an event is found for that corresponding day, a colored asterisk, the color matching the one specified when creating an event, will populate the corresponding button. 
  	JLabel blue = new JLabel("*");
  	blue.setForeground(Color.blue);
  	blue.setFont(blue.getFont().deriveFont(72f));
  	JLabel green = new JLabel("*");
  	green.setForeground(Color.green);
  	green.setFont(green.getFont().deriveFont(72f));
  	JLabel yellow = new JLabel("*");
  	yellow.setForeground(Color.yellow);
  	yellow.setFont(yellow.getFont().deriveFont(72f));
  	JLabel red = new JLabel("*");
  	red.setForeground(Color.red);
  	red.setFont(red.getFont().deriveFont(72f));

  	
  	//passing username to get events for
  	RetriveEvent pullevent = new RetriveEvent(userset.getText());
  	String eventName;
  	String eventReason = null;
  	String start_Time = null;
  	String end_Time = null;
  	String eventColor;
  	ArrayList<String> populate = new ArrayList<>();
  	ArrayList<String> populate2 = new ArrayList<>();
  	//try block needed because of sql requitemnt for throwing errors
  	try {
  	      //result set is a datatype that only works when surroned by try blocks and catching sql exceptions
  	      //pulls all the events for the user from the class retriveEventForUser
  	      
  	         ResultSet listofitems=pullevent.retriveEventForUser();
  		 
  	        //testing code for formating if you enable .out.printf line below
  		//System.out.printf("%-20s%-35s%-25s%-25s%s%n","Event Name","Event Notes/Discription","Start Time", "End Time", "Color");
  	      
  	      //while there are more items within the listofitems run
  	      		while(listofitems.next()) {		
  	      //sets a string varible equal to each part of the current item the list has
  	      //you can do a lot with these strings, with date you can split and set each element of the split to an element in an array for easy minpulating
  				eventName = listofitems.getString("EVENTNAME");
  				eventReason = listofitems.getString("REASON");
  				start_Time = listofitems.getString("START_TIME");
  				end_Time = listofitems.getString("END_TIME");
  				eventColor = listofitems.getString("COLOR");
  				String output = (eventName + " " + eventReason + " " + start_Time + " " + end_Time + " " + eventColor);
  				String output2 = (eventName + ", " + eventReason + ":\n" + start_Time + " - " + end_Time + "\n");
  				populate.add(output);
  				populate2.add(output2);
  	         		 //enable to see output in print format
  				//formats out for correcting spacing as a proof of concept for print
  				//System.out.printf("%-20s%-35s%-25s%-25s%s%n",listofitems.getString("EVENTNAME"),listofitems.getString("REASON"),listofitems.getString("START_TIME"),listofitems.getString("END_TIME"),listofitems.getString("COLOR"));
  			}
  	      //catches sql errors
  		} catch (ClassNotFoundException | SQLException e2) {
  				// TODO Auto-generated catch block
  				e2.printStackTrace();
  	}
  	
  	// Iterates through the size of the ArrayList. Adds previously defined variables locally. If a date corresponding to the current month is found, the symbol will be added. 
		for (int j = 0; j < populate.size(); j++) {
			if (populate.get(j).contains(" 1 ")) {
				JLabel blueFirst = new JLabel("*");
		    	blueFirst.setForeground(Color.blue);
		    	blueFirst.setFont(blue.getFont().deriveFont(72f));
		    	JLabel greenFirst = new JLabel("*");
		    	greenFirst.setForeground(Color.green);
		    	greenFirst.setFont(green.getFont().deriveFont(72f));
		    	JLabel yellowFirst = new JLabel("*");
		    	yellowFirst.setForeground(Color.yellow);
		    	yellowFirst.setFont(yellow.getFont().deriveFont(72f));
		    	JLabel redFirst = new JLabel("*");
		    	redFirst.setForeground(Color.red);
		    	redFirst.setFont(red.getFont().deriveFont(72f));
		    	JLabel orange = new JLabel("*");
		    	orange.setForeground(Color.orange);
		    	orange.setFont(orange.getFont().deriveFont(72f));
		    	JLabel violet = new JLabel("*");
		    	violet.setForeground(new Color(186,85,211));
		    	violet.setFont(violet.getFont().deriveFont(72f)); 
				if (populate.get(j).contains("BLUE") || populate.get(j).contains("Blue") || populate.get(j).contains("blue")) {
					first.add(BorderLayout.WEST, blueFirst);
				}
				if (populate.get(j).contains("YELLOW") || populate.get(j).contains("Yellow") || populate.get(j).contains("yellow")) {
					yellowFirst.setHorizontalAlignment(JLabel.CENTER);
					first.add(BorderLayout.CENTER, yellowFirst);
				}
				if (populate.get(j).contains("RED") || populate.get(j).contains("Red") || populate.get(j).contains("red")) {
					first.add(BorderLayout.EAST, redFirst);
				}
				if (populate.get(j).contains("GREEN") || populate.get(j).contains("Green") || populate.get(j).contains("green")) {
					greenFirst.setVerticalAlignment(JLabel.NORTH);
					greenFirst.setHorizontalAlignment(JLabel.CENTER);
					first.add(BorderLayout.CENTER, greenFirst);
				}
				if (populate.get(j).contains("GREEN") || populate.get(j).contains("Green") || populate.get(j).contains("green")) {
					first.add(BorderLayout.WEST, greenFirst);
				}
				if (populate.get(j).contains("ORANGE") || populate.get(j).contains("Orange") || populate.get(j).contains("orange")) {
					orange.setHorizontalAlignment(JLabel.CENTER);
					first.add(BorderLayout.CENTER, orange);
				}
				if (populate.get(j).contains("VIOLET") || populate.get(j).contains("Violet") || populate.get(j).contains("violet")) {
					first.add(BorderLayout.EAST, violet);
				}
			}
		}
		for (int j = 0; j < populate.size(); j++) {
			if (populate.get(j).contains(" 2 ")) {
				JLabel blueSecond = new JLabel("*");
		    	blueSecond.setForeground(Color.blue);
		    	blueSecond.setFont(blue.getFont().deriveFont(72f));
		    	JLabel greenSecond = new JLabel("*");
		    	greenSecond.setForeground(Color.green);
		    	greenSecond.setFont(green.getFont().deriveFont(72f));
		    	JLabel yellowSecond = new JLabel("*");
		    	yellowSecond.setForeground(Color.yellow);
		    	yellowSecond.setFont(yellow.getFont().deriveFont(72f));
		    	JLabel redSecond = new JLabel("*");
		    	redSecond.setForeground(Color.red);
		    	redSecond.setFont(red.getFont().deriveFont(72f));
		    	JLabel orange = new JLabel("*");
		    	orange.setForeground(Color.orange);
		    	orange.setFont(orange.getFont().deriveFont(72f));
		    	JLabel violet = new JLabel("*");
		    	violet.setForeground(new Color(186,85,211));
		    	violet.setFont(violet.getFont().deriveFont(72f)); 
				if (populate.get(j).contains("BLUE") || populate.get(j).contains("Blue") || populate.get(j).contains("blue")) {
					second.add(BorderLayout.WEST, blueSecond);
				}
				if (populate.get(j).contains("YELLOW") || populate.get(j).contains("Yellow") || populate.get(j).contains("yellow")) {
					yellowSecond.setHorizontalAlignment(JLabel.CENTER);
					second.add(BorderLayout.CENTER, yellowSecond);
				}
				if (populate.get(j).contains("RED") || populate.get(j).contains("Red") || populate.get(j).contains("red")) {
					second.add(BorderLayout.EAST, redSecond);
				}
				if (populate.get(j).contains("GREEN") || populate.get(j).contains("Green") || populate.get(j).contains("green")) {
					second.add(BorderLayout.WEST, greenSecond);
				}
				if (populate.get(j).contains("ORANGE") || populate.get(j).contains("Orange") || populate.get(j).contains("orange")) {
					orange.setHorizontalAlignment(JLabel.CENTER);
					second.add(BorderLayout.CENTER, orange);
				}
				if (populate.get(j).contains("VIOLET") || populate.get(j).contains("Violet") || populate.get(j).contains("violet")) {
					second.add(BorderLayout.EAST, violet);
				}
			}
		}
		for (int j = 0; j < populate.size(); j++) {
			if (populate.get(j).contains(" 3 ")) {
				JLabel blueThird = new JLabel("*");
		    	blueThird.setForeground(Color.blue);
		    	blueThird.setFont(blue.getFont().deriveFont(72f));
		    	JLabel greenThird = new JLabel("*");
		    	greenThird.setForeground(Color.green);
		    	greenThird.setFont(green.getFont().deriveFont(72f));
		    	JLabel yellowThird = new JLabel("*");
		    	yellowThird.setForeground(Color.yellow);
		    	yellowThird.setFont(yellow.getFont().deriveFont(72f));
		    	JLabel redThird = new JLabel("*");
		    	redThird.setForeground(Color.red);
		    	redThird.setFont(red.getFont().deriveFont(72f));
		    	JLabel orange = new JLabel("*");
		    	orange.setForeground(Color.orange);
		    	orange.setFont(orange.getFont().deriveFont(72f));
		    	JLabel violet = new JLabel("*");
		    	violet.setForeground(new Color(186,85,211));
		    	violet.setFont(violet.getFont().deriveFont(72f)); 
				if (populate.get(j).contains("BLUE") || populate.get(j).contains("Blue") || populate.get(j).contains("blue")) {
					third.add(BorderLayout.WEST, blueThird);
				}
				if (populate.get(j).contains("YELLOW") || populate.get(j).contains("Yellow") || populate.get(j).contains("yellow")) {
					yellowThird.setHorizontalAlignment(JLabel.CENTER);
					third.add(BorderLayout.CENTER, yellowThird);
				}
				if (populate.get(j).contains("RED") || populate.get(j).contains("Red") || populate.get(j).contains("red")) {
					third.add(BorderLayout.EAST, redThird);
				}
				if (populate.get(j).contains("GREEN") || populate.get(j).contains("Green") || populate.get(j).contains("green")) {
					third.add(BorderLayout.WEST, greenThird);
				}
				if (populate.get(j).contains("ORANGE") || populate.get(j).contains("Orange") || populate.get(j).contains("orange")) {
					orange.setHorizontalAlignment(JLabel.CENTER);
					third.add(BorderLayout.CENTER, orange);
				}
				if (populate.get(j).contains("VIOLET") || populate.get(j).contains("Violet") || populate.get(j).contains("violet")) {
					third.add(BorderLayout.EAST, violet);
				}
			}
		}
		for (int j = 0; j < populate.size(); j++) {
			if (populate.get(j).contains(" 4 ")) {
				JLabel blueFourth = new JLabel("*");
		    	blueFourth.setForeground(Color.blue);
		    	blueFourth.setFont(blue.getFont().deriveFont(72f));
		    	JLabel greenFourth = new JLabel("*");
		    	greenFourth.setForeground(Color.green);
		    	greenFourth.setFont(green.getFont().deriveFont(72f));
		    	JLabel yellowFourth = new JLabel("*");
		    	yellowFourth.setForeground(Color.yellow);
		    	yellowFourth.setFont(yellow.getFont().deriveFont(72f));
		    	JLabel redFourth = new JLabel("*");
		    	redFourth.setForeground(Color.red);
		    	redFourth.setFont(red.getFont().deriveFont(72f));
		    	JLabel orange = new JLabel("*");
		    	orange.setForeground(Color.orange);
		    	orange.setFont(orange.getFont().deriveFont(72f));
		    	JLabel violet = new JLabel("*");
		    	violet.setForeground(new Color(186,85,211));
		    	violet.setFont(violet.getFont().deriveFont(72f)); 
				if (populate.get(j).contains("BLUE") || populate.get(j).contains("Blue") || populate.get(j).contains("blue")) {
					fourth.add(BorderLayout.WEST, blueFourth);
				}
				if (populate.get(j).contains("YELLOW") || populate.get(j).contains("Yellow") || populate.get(j).contains("yellow")) {
					yellowFourth.setHorizontalAlignment(JLabel.CENTER);
					fourth.add(BorderLayout.CENTER, yellowFourth);
				}
				if (populate.get(j).contains("RED") || populate.get(j).contains("Red") || populate.get(j).contains("red")) {
					fourth.add(BorderLayout.EAST, redFourth);
				}
				if (populate.get(j).contains("GREEN") || populate.get(j).contains("Green") || populate.get(j).contains("green")) {
					fourth.add(BorderLayout.WEST, greenFourth);
				}
				if (populate.get(j).contains("ORANGE") || populate.get(j).contains("Orange") || populate.get(j).contains("orange")) {
					orange.setHorizontalAlignment(JLabel.CENTER);
					fourth.add(BorderLayout.CENTER, orange);
				}
				if (populate.get(j).contains("VIOLET") || populate.get(j).contains("Violet") || populate.get(j).contains("violet")) {
					fourth.add(BorderLayout.EAST, violet);
				}
			}
		}
		for (int j = 0; j < populate.size(); j++) {
			if (populate.get(j).contains(" 5 ")) {
				JLabel blueFifth = new JLabel("*");
		    	blueFifth.setForeground(Color.blue);
		    	blueFifth.setFont(blue.getFont().deriveFont(72f));
		    	JLabel greenFifth = new JLabel("*");
		    	greenFifth.setForeground(Color.green);
		    	greenFifth.setFont(green.getFont().deriveFont(72f));
		    	JLabel yellowFifth = new JLabel("*");
		    	yellowFifth.setForeground(Color.yellow);
		    	yellowFifth.setFont(yellow.getFont().deriveFont(72f));
		    	JLabel redFifth = new JLabel("*");
		    	redFifth.setForeground(Color.red);
		    	redFifth.setFont(red.getFont().deriveFont(72f));
		    	JLabel orange = new JLabel("*");
		    	orange.setForeground(Color.orange);
		    	orange.setFont(orange.getFont().deriveFont(72f));
		    	JLabel violet = new JLabel("*");
		    	violet.setForeground(new Color(186,85,211));
		    	violet.setFont(violet.getFont().deriveFont(72f)); 
				if (populate.get(j).contains("BLUE") || populate.get(j).contains("Blue") || populate.get(j).contains("blue")) {
					fifth.add(BorderLayout.WEST, blueFifth);
				}
				if (populate.get(j).contains("YELLOW") || populate.get(j).contains("Yellow") || populate.get(j).contains("yellow")) {
					yellowFifth.setHorizontalAlignment(JLabel.CENTER);
					fifth.add(BorderLayout.CENTER, yellowFifth);
				}
				if (populate.get(j).contains("RED") || populate.get(j).contains("Red") || populate.get(j).contains("red")) {
					fifth.add(BorderLayout.EAST, redFifth);
				}
				if (populate.get(j).contains("GREEN") || populate.get(j).contains("Green") || populate.get(j).contains("green")) {
					fifth.add(BorderLayout.WEST, greenFifth);
				}
				if (populate.get(j).contains("ORANGE") || populate.get(j).contains("Orange") || populate.get(j).contains("orange")) {
					orange.setHorizontalAlignment(JLabel.CENTER);
					fifth.add(BorderLayout.CENTER, orange);
				}
				if (populate.get(j).contains("VIOLET") || populate.get(j).contains("Violet") || populate.get(j).contains("violet")) {
					fifth.add(BorderLayout.EAST, violet);
				}
			}
		}
		for (int j = 0; j < populate.size(); j++) {
			if (populate.get(j).contains(" 6 ")) {
				JLabel blueSixth = new JLabel("*");
		    	blueSixth.setForeground(Color.blue);
		    	blueSixth.setFont(blue.getFont().deriveFont(72f));
		    	JLabel greenSixth = new JLabel("*");
		    	greenSixth.setForeground(Color.green);
		    	greenSixth.setFont(green.getFont().deriveFont(72f));
		    	JLabel yellowSixth = new JLabel("*");
		    	yellowSixth.setForeground(Color.yellow);
		    	yellowSixth.setFont(yellow.getFont().deriveFont(72f));
		    	JLabel redSixth = new JLabel("*");
		    	redSixth.setForeground(Color.red);
		    	redSixth.setFont(red.getFont().deriveFont(72f));
		    	JLabel orange = new JLabel("*");
		    	orange.setForeground(Color.orange);
		    	orange.setFont(orange.getFont().deriveFont(72f));
		    	JLabel violet = new JLabel("*");
		    	violet.setForeground(new Color(186,85,211));
		    	violet.setFont(violet.getFont().deriveFont(72f)); 
				if (populate.get(j).contains("BLUE") || populate.get(j).contains("Blue") || populate.get(j).contains("blue")) {
					sixth.add(BorderLayout.WEST, blueSixth);
				}
				if (populate.get(j).contains("YELLOW") || populate.get(j).contains("Yellow") || populate.get(j).contains("yellow")) {
					yellowSixth.setHorizontalAlignment(JLabel.CENTER);
					sixth.add(BorderLayout.CENTER, yellowSixth);
				}
				if (populate.get(j).contains("RED") || populate.get(j).contains("Red") || populate.get(j).contains("red")) {
					sixth.add(BorderLayout.EAST, redSixth);
				}
				if (populate.get(j).contains("GREEN") || populate.get(j).contains("Green") || populate.get(j).contains("green")) {
					sixth.add(BorderLayout.WEST, greenSixth);
				}
				if (populate.get(j).contains("ORANGE") || populate.get(j).contains("Orange") || populate.get(j).contains("orange")) {
					orange.setHorizontalAlignment(JLabel.CENTER);
					sixth.add(BorderLayout.CENTER, orange);
				}
				if (populate.get(j).contains("VIOLET") || populate.get(j).contains("Violet") || populate.get(j).contains("violet")) {
					sixth.add(BorderLayout.EAST, violet);
				}
			}
		}
		for (int j = 0; j < populate.size(); j++) {
			if (populate.get(j).contains(" 7 ")) {
				JLabel blueSeventh = new JLabel("*");
		    	blueSeventh.setForeground(Color.blue);
		    	blueSeventh.setFont(blue.getFont().deriveFont(72f));
		    	JLabel greenSeventh = new JLabel("*");
		    	greenSeventh.setForeground(Color.green);
		    	greenSeventh.setFont(green.getFont().deriveFont(72f));
		    	JLabel yellowSeventh = new JLabel("*");
		    	yellowSeventh.setForeground(Color.yellow);
		    	yellowSeventh.setFont(yellow.getFont().deriveFont(72f));
		    	JLabel redSeventh = new JLabel("*");
		    	redSeventh.setForeground(Color.red);
		    	redSeventh.setFont(red.getFont().deriveFont(72f));
		    	JLabel orange = new JLabel("*");
		    	orange.setForeground(Color.orange);
		    	orange.setFont(orange.getFont().deriveFont(72f));
		    	JLabel violet = new JLabel("*");
		    	violet.setForeground(new Color(186,85,211));
		    	violet.setFont(violet.getFont().deriveFont(72f)); 
				if (populate.get(j).contains("BLUE") || populate.get(j).contains("Blue") || populate.get(j).contains("blue")) {
					seventh.add(BorderLayout.WEST, blueSeventh);
				}
				if (populate.get(j).contains("YELLOW") || populate.get(j).contains("Yellow") || populate.get(j).contains("yellow")) {
					yellowSeventh.setHorizontalAlignment(JLabel.CENTER);
					seventh.add(BorderLayout.CENTER, yellowSeventh);
				}
				if (populate.get(j).contains("RED") || populate.get(j).contains("Red") || populate.get(j).contains("red")) {
					seventh.add(BorderLayout.EAST, redSeventh);
				}
				if (populate.get(j).contains("GREEN") || populate.get(j).contains("Green") || populate.get(j).contains("green")) {
					seventh.add(BorderLayout.WEST, greenSeventh);
				}
				if (populate.get(j).contains("ORANGE") || populate.get(j).contains("Orange") || populate.get(j).contains("orange")) {
					orange.setHorizontalAlignment(JLabel.CENTER);
					seventh.add(BorderLayout.CENTER, orange);
				}
				if (populate.get(j).contains("VIOLET") || populate.get(j).contains("Violet") || populate.get(j).contains("violet")) {
					seventh.add(BorderLayout.EAST, violet);
				}
			}
		}
		for (int j = 0; j < populate.size(); j++) {
			if (populate.get(j).contains(" 8 ")) {
				JLabel blueEighth = new JLabel("*");
		    	blueEighth.setForeground(Color.blue);
		    	blueEighth.setFont(blue.getFont().deriveFont(72f));
		    	JLabel greenEighth = new JLabel("*");
		    	greenEighth.setForeground(Color.green);
		    	greenEighth.setFont(green.getFont().deriveFont(72f));
		    	JLabel yellowEighth = new JLabel("*");
		    	yellowEighth.setForeground(Color.yellow);
		    	yellowEighth.setFont(yellow.getFont().deriveFont(72f));
		    	JLabel redEighth = new JLabel("*");
		    	redEighth.setForeground(Color.red);
		    	redEighth.setFont(red.getFont().deriveFont(72f));
		    	JLabel orange = new JLabel("*");
		    	orange.setForeground(Color.orange);
		    	orange.setFont(orange.getFont().deriveFont(72f));
		    	JLabel violet = new JLabel("*");
		    	violet.setForeground(new Color(186,85,211));
		    	violet.setFont(violet.getFont().deriveFont(72f)); 
				if (populate.get(j).contains("BLUE") || populate.get(j).contains("Blue") || populate.get(j).contains("blue")) {
					eighth.add(BorderLayout.WEST, blueEighth);
				}
				if (populate.get(j).contains("YELLOW") || populate.get(j).contains("Yellow") || populate.get(j).contains("yellow")) {
					yellowEighth.setHorizontalAlignment(JLabel.CENTER);
					eighth.add(BorderLayout.CENTER, yellowEighth);
				}
				if (populate.get(j).contains("RED") || populate.get(j).contains("Red") || populate.get(j).contains("red")) {
					eighth.add(BorderLayout.EAST, redEighth);
				}
				if (populate.get(j).contains("GREEN") || populate.get(j).contains("Green") || populate.get(j).contains("green")) {
					eighth.add(BorderLayout.WEST, greenEighth);
				}
				if (populate.get(j).contains("ORANGE") || populate.get(j).contains("Orange") || populate.get(j).contains("orange")) {
					orange.setHorizontalAlignment(JLabel.CENTER);
					eighth.add(BorderLayout.CENTER, orange);
				}
				if (populate.get(j).contains("VIOLET") || populate.get(j).contains("Violet") || populate.get(j).contains("violet")) {
					eighth.add(BorderLayout.EAST, violet);
				}
			}
		}
		for (int j = 0; j < populate.size(); j++) {
			if (populate.get(j).contains(" 9 ")) {
				JLabel blueNinth = new JLabel("*");
		    	blueNinth.setForeground(Color.blue);
		    	blueNinth.setFont(blue.getFont().deriveFont(72f));
		    	JLabel greenNinth = new JLabel("*");
		    	greenNinth.setForeground(Color.green);
		    	greenNinth.setFont(green.getFont().deriveFont(72f));
		    	JLabel yellowNinth = new JLabel("*");
		    	yellowNinth.setForeground(Color.yellow);
		    	yellowNinth.setFont(yellow.getFont().deriveFont(72f));
		    	JLabel redNinth = new JLabel("*");
		    	redNinth.setForeground(Color.red);
		    	redNinth.setFont(red.getFont().deriveFont(72f));
		    	JLabel orange = new JLabel("*");
		    	orange.setForeground(Color.orange);
		    	orange.setFont(orange.getFont().deriveFont(72f));
		    	JLabel violet = new JLabel("*");
		    	violet.setForeground(new Color(186,85,211));
		    	violet.setFont(violet.getFont().deriveFont(72f)); 
				if (populate.get(j).contains("BLUE") || populate.get(j).contains("Blue") || populate.get(j).contains("blue")) {
					ninth.add(BorderLayout.WEST, blueNinth);
				}
				if (populate.get(j).contains("YELLOW") || populate.get(j).contains("Yellow") || populate.get(j).contains("yellow")) {
					yellowNinth.setHorizontalAlignment(JLabel.CENTER);
					ninth.add(BorderLayout.CENTER, yellowNinth);
				}
				if (populate.get(j).contains("RED") || populate.get(j).contains("Red") || populate.get(j).contains("red")) {
					ninth.add(BorderLayout.EAST, redNinth);
				}
				if (populate.get(j).contains("GREEN") || populate.get(j).contains("Green") || populate.get(j).contains("green")) {
					ninth.add(BorderLayout.WEST, greenNinth);
				}
				if (populate.get(j).contains("ORANGE") || populate.get(j).contains("Orange") || populate.get(j).contains("orange")) {
					orange.setHorizontalAlignment(JLabel.CENTER);
					ninth.add(BorderLayout.CENTER, orange);
				}
				if (populate.get(j).contains("VIOLET") || populate.get(j).contains("Violet") || populate.get(j).contains("violet")) {
					ninth.add(BorderLayout.EAST, violet);
				}
			}
		}
		for (int j = 0; j < populate.size(); j++) {
			if (populate.get(j).contains(" 10 ")) {
				JLabel blueTenth = new JLabel("*");
		    	blueTenth.setForeground(Color.blue);
		    	blueTenth.setFont(blue.getFont().deriveFont(72f));
		    	JLabel greenTenth = new JLabel("*");
		    	greenTenth.setForeground(Color.green);
		    	greenTenth.setFont(green.getFont().deriveFont(72f));
		    	JLabel yellowTenth = new JLabel("*");
		    	yellowTenth.setForeground(Color.yellow);
		    	yellowTenth.setFont(yellow.getFont().deriveFont(72f));
		    	JLabel redTenth = new JLabel("*");
		    	redTenth.setForeground(Color.red);
		    	redTenth.setFont(red.getFont().deriveFont(72f));
		    	JLabel orange = new JLabel("*");
		    	orange.setForeground(Color.orange);
		    	orange.setFont(orange.getFont().deriveFont(72f));
		    	JLabel violet = new JLabel("*");
		    	violet.setForeground(new Color(186,85,211));
		    	violet.setFont(violet.getFont().deriveFont(72f)); 
				if (populate.get(j).contains("BLUE") || populate.get(j).contains("Blue") || populate.get(j).contains("blue")) {
					tenth.add(BorderLayout.WEST, blueTenth);
				}
				if (populate.get(j).contains("YELLOW") || populate.get(j).contains("Yellow") || populate.get(j).contains("yellow")) {
					yellowTenth.setHorizontalAlignment(JLabel.CENTER);
					tenth.add(BorderLayout.CENTER, yellowTenth);
				}
				if (populate.get(j).contains("RED") || populate.get(j).contains("Red") || populate.get(j).contains("red")) {
					tenth.add(BorderLayout.EAST, redTenth);
				}
				if (populate.get(j).contains("GREEN") || populate.get(j).contains("Green") || populate.get(j).contains("green")) {
					tenth.add(BorderLayout.WEST, greenTenth);
				}
				if (populate.get(j).contains("ORANGE") || populate.get(j).contains("Orange") || populate.get(j).contains("orange")) {
					orange.setHorizontalAlignment(JLabel.CENTER);
					tenth.add(BorderLayout.CENTER, orange);
				}
				if (populate.get(j).contains("VIOLET") || populate.get(j).contains("Violet") || populate.get(j).contains("violet")) {
					tenth.add(BorderLayout.EAST, violet);
				}
			}
		}
		for (int j = 0; j < populate.size(); j++) {
			if (populate.get(j).contains(" 11 ")) {
				JLabel blueEleventh = new JLabel("*");
		    	blueEleventh.setForeground(Color.blue);
		    	blueEleventh.setFont(blue.getFont().deriveFont(72f));
		    	JLabel greenEleventh = new JLabel("*");
		    	greenEleventh.setForeground(Color.green);
		    	greenEleventh.setFont(green.getFont().deriveFont(72f));
		    	JLabel yellowEleventh = new JLabel("*");
		    	yellowEleventh.setForeground(Color.yellow);
		    	yellowEleventh.setFont(yellow.getFont().deriveFont(72f));
		    	JLabel redEleventh = new JLabel("*");
		    	redEleventh.setForeground(Color.red);
		    	redEleventh.setFont(red.getFont().deriveFont(72f));
		    	JLabel orange = new JLabel("*");
		    	orange.setForeground(Color.orange);
		    	orange.setFont(orange.getFont().deriveFont(72f));
		    	JLabel violet = new JLabel("*");
		    	violet.setForeground(new Color(186,85,211));
		    	violet.setFont(violet.getFont().deriveFont(72f)); 
				if (populate.get(j).contains("BLUE") || populate.get(j).contains("Blue") || populate.get(j).contains("blue")) {
					eleventh.add(BorderLayout.WEST, blueEleventh);
				}
				if (populate.get(j).contains("YELLOW") || populate.get(j).contains("Yellow") || populate.get(j).contains("yellow")) {
					yellowEleventh.setHorizontalAlignment(JLabel.CENTER);
					eleventh.add(BorderLayout.CENTER, yellowEleventh);
				}
				if (populate.get(j).contains("RED") || populate.get(j).contains("Red") || populate.get(j).contains("red")) {
					eleventh.add(BorderLayout.EAST, redEleventh);
				}
				if (populate.get(j).contains("GREEN") || populate.get(j).contains("Green") || populate.get(j).contains("green")) {
					eleventh.add(BorderLayout.WEST, greenEleventh);
				}
				if (populate.get(j).contains("ORANGE") || populate.get(j).contains("Orange") || populate.get(j).contains("orange")) {
					orange.setHorizontalAlignment(JLabel.CENTER);
					eleventh.add(BorderLayout.CENTER, orange);
				}
				if (populate.get(j).contains("VIOLET") || populate.get(j).contains("Violet") || populate.get(j).contains("violet")) {
					sixteenth.add(BorderLayout.EAST, violet);
				}
			}
		}
		for (int j = 0; j < populate.size(); j++) {
			if (populate.get(j).contains(" 12 ")) {
				JLabel blueTwlevth = new JLabel("*");
		    	blueTwlevth.setForeground(Color.blue);
		    	blueTwlevth.setFont(blue.getFont().deriveFont(72f));
		    	JLabel greenTwelvth = new JLabel("*");
		    	greenTwelvth.setForeground(Color.green);
		    	greenTwelvth.setFont(green.getFont().deriveFont(72f));
		    	JLabel yellowTwelvth = new JLabel("*");
		    	yellowTwelvth.setForeground(Color.yellow);
		    	yellowTwelvth.setFont(yellow.getFont().deriveFont(72f));
		    	JLabel redTwelvth = new JLabel("*");
		    	redTwelvth.setForeground(Color.red);
		    	redTwelvth.setFont(red.getFont().deriveFont(72f));
		    	JLabel orange = new JLabel("*");
		    	orange.setForeground(Color.orange);
		    	orange.setFont(orange.getFont().deriveFont(72f));
		    	JLabel violet = new JLabel("*");
		    	violet.setForeground(new Color(186,85,211));
		    	violet.setFont(violet.getFont().deriveFont(72f)); 
				if (populate.get(j).contains("BLUE") || populate.get(j).contains("Blue") || populate.get(j).contains("blue")) {
					twelvth.add(BorderLayout.WEST, blueTwlevth);
				}
				if (populate.get(j).contains("YELLOW") || populate.get(j).contains("Yellow") || populate.get(j).contains("yellow")) {
					yellowTwelvth.setHorizontalAlignment(JLabel.CENTER);
					twelvth.add(BorderLayout.CENTER, yellowTwelvth);
				}
				if (populate.get(j).contains("RED") || populate.get(j).contains("Red") || populate.get(j).contains("red")) {
					twelvth.add(BorderLayout.EAST, redTwelvth);
				}
				if (populate.get(j).contains("GREEN") || populate.get(j).contains("Green") || populate.get(j).contains("green")) {
					twelvth.add(BorderLayout.WEST, greenTwelvth);
				}
				if (populate.get(j).contains("ORANGE") || populate.get(j).contains("Orange") || populate.get(j).contains("orange")) {
					orange.setHorizontalAlignment(JLabel.CENTER);
					twelvth.add(BorderLayout.CENTER, orange);
				}
				if (populate.get(j).contains("VIOLET") || populate.get(j).contains("Violet") || populate.get(j).contains("violet")) {
					twelvth.add(BorderLayout.EAST, violet);
				}
			}
		}
		for (int j = 0; j < populate.size(); j++) {
			if (populate.get(j).contains(" 13 ")) {
				JLabel blueThirteenth = new JLabel("*");
		    	blueThirteenth.setForeground(Color.blue);
		    	blueThirteenth.setFont(blue.getFont().deriveFont(72f));
		    	JLabel greenThirteenth = new JLabel("*");
		    	greenThirteenth.setForeground(Color.green);
		    	greenThirteenth.setFont(green.getFont().deriveFont(72f));
		    	JLabel yellowThirteenth = new JLabel("*");
		    	yellowThirteenth.setForeground(Color.yellow);
		    	yellowThirteenth.setFont(yellow.getFont().deriveFont(72f));
		    	JLabel redThirteenth = new JLabel("*");
		    	redThirteenth.setForeground(Color.red);
		    	redThirteenth.setFont(red.getFont().deriveFont(72f));
		    	JLabel orange = new JLabel("*");
		    	orange.setForeground(Color.orange);
		    	orange.setFont(orange.getFont().deriveFont(72f));
		    	JLabel violet = new JLabel("*");
		    	violet.setForeground(new Color(186,85,211));
		    	violet.setFont(violet.getFont().deriveFont(72f)); 
				if (populate.get(j).contains("BLUE") || populate.get(j).contains("Blue") || populate.get(j).contains("blue")) {
					thirteenth.add(BorderLayout.WEST, blueThirteenth);
				}
				if (populate.get(j).contains("YELLOW") || populate.get(j).contains("Yellow") || populate.get(j).contains("yellow")) {
					yellowThirteenth.setHorizontalAlignment(JLabel.CENTER);
					thirteenth.add(BorderLayout.CENTER, yellowThirteenth);
				}
				if (populate.get(j).contains("RED") || populate.get(j).contains("Red") || populate.get(j).contains("red")) {
					thirteenth.add(BorderLayout.EAST, redThirteenth);
				}
				if (populate.get(j).contains("GREEN") || populate.get(j).contains("Green") || populate.get(j).contains("green")) {
					thirteenth.add(BorderLayout.WEST, greenThirteenth);
				}
				if (populate.get(j).contains("ORANGE") || populate.get(j).contains("Orange") || populate.get(j).contains("orange")) {
					orange.setHorizontalAlignment(JLabel.CENTER);
					thirteenth.add(BorderLayout.CENTER, orange);
				}
				if (populate.get(j).contains("VIOLET") || populate.get(j).contains("Violet") || populate.get(j).contains("violet")) {
					thirteenth.add(BorderLayout.EAST, violet);
				}
			}
		}
		for (int j = 0; j < populate.size(); j++) {
			if (populate.get(j).contains(" 14 ")) {
				JLabel blueFourteenth = new JLabel("*");
		    	blueFourteenth.setForeground(Color.blue);
		    	blueFourteenth.setFont(blue.getFont().deriveFont(72f));
		    	JLabel greenFourteenth = new JLabel("*");
		    	greenFourteenth.setForeground(Color.green);
		    	greenFourteenth.setFont(green.getFont().deriveFont(72f));
		    	JLabel yellowFourteenth = new JLabel("*");
		    	yellowFourteenth.setForeground(Color.yellow);
		    	yellowFourteenth.setFont(yellow.getFont().deriveFont(72f));
		    	JLabel redFourteenth = new JLabel("*");
		    	redFourteenth.setForeground(Color.red);
		    	redFourteenth.setFont(red.getFont().deriveFont(72f));
		    	JLabel orange = new JLabel("*");
		    	orange.setForeground(Color.orange);
		    	orange.setFont(orange.getFont().deriveFont(72f));
		    	JLabel violet = new JLabel("*");
		    	violet.setForeground(new Color(186,85,211));
		    	violet.setFont(violet.getFont().deriveFont(72f)); 
				if (populate.get(j).contains("BLUE") || populate.get(j).contains("Blue") || populate.get(j).contains("blue")) {
					fourteenth.add(BorderLayout.WEST, blueFourteenth);
				}
				if (populate.get(j).contains("YELLOW") || populate.get(j).contains("Yellow") || populate.get(j).contains("yellow")) {
					yellowFourteenth.setHorizontalAlignment(JLabel.CENTER);
					fourteenth.add(BorderLayout.CENTER, yellowFourteenth);
				}
				if (populate.get(j).contains("RED") || populate.get(j).contains("Red") || populate.get(j).contains("red")) {
					fourteenth.add(BorderLayout.EAST, redFourteenth);
				}
				if (populate.get(j).contains("GREEN") || populate.get(j).contains("Green") || populate.get(j).contains("green")) {
					fourteenth.add(BorderLayout.WEST, greenFourteenth);
				}
				if (populate.get(j).contains("ORANGE") || populate.get(j).contains("Orange") || populate.get(j).contains("orange")) {
					orange.setHorizontalAlignment(JLabel.CENTER);
					fourteenth.add(BorderLayout.CENTER, orange);
				}
				if (populate.get(j).contains("VIOLET") || populate.get(j).contains("Violet") || populate.get(j).contains("violet")) {
					fourteenth.add(BorderLayout.EAST, violet);
				}
			}
		}
		for (int j = 0; j < populate.size(); j++) {
			if (populate.get(j).contains(" 15 ")) {
				JLabel blueFifteenth = new JLabel("*");
		    	blueFifteenth.setForeground(Color.blue);
		    	blueFifteenth.setFont(blue.getFont().deriveFont(72f));
		    	JLabel greenFifteenth = new JLabel("*");
		    	greenFifteenth.setForeground(Color.green);
		    	greenFifteenth.setFont(green.getFont().deriveFont(72f));
		    	JLabel yellowFifteenth = new JLabel("*");
		    	yellowFifteenth.setForeground(Color.yellow);
		    	yellowFifteenth.setFont(yellow.getFont().deriveFont(72f));
		    	JLabel redFifteenth = new JLabel("*");
		    	redFifteenth.setForeground(Color.red);
		    	redFifteenth.setFont(red.getFont().deriveFont(72f));
		    	JLabel orange = new JLabel("*");
		    	orange.setForeground(Color.orange);
		    	orange.setFont(orange.getFont().deriveFont(72f));
		    	JLabel violet = new JLabel("*");
		    	violet.setForeground(new Color(186,85,211));
		    	violet.setFont(violet.getFont().deriveFont(72f)); 
				if (populate.get(j).contains("BLUE") || populate.get(j).contains("Blue") || populate.get(j).contains("blue")) {
					fifteenth.add(BorderLayout.WEST, blueFifteenth);
				}
				if (populate.get(j).contains("YELLOW") || populate.get(j).contains("Yellow") || populate.get(j).contains("yellow")) {
					yellowFifteenth.setHorizontalAlignment(JLabel.CENTER);
					fifteenth.add(BorderLayout.CENTER, yellowFifteenth);
				}
				if (populate.get(j).contains("RED") || populate.get(j).contains("Red") || populate.get(j).contains("red")) {
					fifteenth.add(BorderLayout.EAST, redFifteenth);
				}
			}
		}

		for (int j = 0; j < populate.size(); j++) {
			if (populate.get(j).contains(" 16 ")) {
				JLabel blueSixteenth = new JLabel("*");
		    	blueSixteenth.setForeground(Color.blue);
		    	blueSixteenth.setFont(blue.getFont().deriveFont(72f));
		    	JLabel greenSixteenth = new JLabel("*");
		    	greenSixteenth.setForeground(Color.green);
		    	greenSixteenth.setFont(green.getFont().deriveFont(72f));
		    	JLabel orange = new JLabel("*");
		    	orange.setForeground(Color.orange);
		    	orange.setFont(orange.getFont().deriveFont(72f));
		    	JLabel violet = new JLabel("*");
		    	violet.setForeground(new Color(186,85,211));
		    	violet.setFont(violet.getFont().deriveFont(72f));    	
		    	JLabel yellowSixteenth = new JLabel("*");
		    	yellowSixteenth.setForeground(Color.yellow);
		    	yellowSixteenth.setFont(yellow.getFont().deriveFont(72f));
		    	JLabel redSixteenth = new JLabel("*");
		    	redSixteenth.setForeground(Color.red);
		    	redSixteenth.setFont(red.getFont().deriveFont(72f));
				if (populate.get(j).contains("BLUE") || populate.get(j).contains("Blue") || populate.get(j).contains("blue")) {
					sixteenth.add(BorderLayout.WEST, blueSixteenth);
				}
				if (populate.get(j).contains("YELLOW") || populate.get(j).contains("Yellow") || populate.get(j).contains("yellow")) {
					yellowSixteenth.setHorizontalAlignment(JLabel.CENTER);
					sixteenth.add(BorderLayout.CENTER, yellowSixteenth);
				}
				if (populate.get(j).contains("RED") || populate.get(j).contains("Red") || populate.get(j).contains("red")) {
					sixteenth.add(BorderLayout.EAST, redSixteenth);
				}
				if (populate.get(j).contains("GREEN") || populate.get(j).contains("Green") || populate.get(j).contains("green")) {
					sixteenth.add(BorderLayout.WEST, greenSixteenth);
				}
				if (populate.get(j).contains("ORANGE") || populate.get(j).contains("Orange") || populate.get(j).contains("orange")) {
					orange.setHorizontalAlignment(JLabel.CENTER);
					sixteenth.add(BorderLayout.CENTER, orange);
				}
				if (populate.get(j).contains("VIOLET") || populate.get(j).contains("Violet") || populate.get(j).contains("violet")) {
					sixteenth.add(BorderLayout.EAST, violet);
				}
			}
		}
		for (int j = 0; j < populate.size(); j++) {
			if (populate.get(j).contains(" 17 ")) {
				JLabel blueSeventeenth = new JLabel("*");
		    	blueSeventeenth.setForeground(Color.blue);
		    	blueSeventeenth.setFont(blue.getFont().deriveFont(72f));
		    	JLabel greenSeventeenth = new JLabel("*");
		    	greenSeventeenth.setForeground(Color.green);
		    	greenSeventeenth.setFont(green.getFont().deriveFont(72f));
		    	JLabel yellowSeventeenth = new JLabel("*");
		    	yellowSeventeenth.setForeground(Color.yellow);
		    	yellowSeventeenth.setFont(yellow.getFont().deriveFont(72f));
		    	JLabel redSeventeenth = new JLabel("*");
		    	redSeventeenth.setForeground(Color.red);
		    	redSeventeenth.setFont(red.getFont().deriveFont(72f));
		    	JLabel orange = new JLabel("*");
		    	orange.setForeground(Color.orange);
		    	orange.setFont(orange.getFont().deriveFont(72f));
		    	JLabel violet = new JLabel("*");
		    	violet.setForeground(new Color(186,85,211));
		    	violet.setFont(violet.getFont().deriveFont(72f)); 
				if (populate.get(j).contains("BLUE") || populate.get(j).contains("Blue") || populate.get(j).contains("blue")) {
					seventeenth.add(BorderLayout.WEST, blueSeventeenth);
				}
				if (populate.get(j).contains("YELLOW") || populate.get(j).contains("Yellow") || populate.get(j).contains("yellow")) {
					yellowSeventeenth.setHorizontalAlignment(JLabel.CENTER);
					seventeenth.add(BorderLayout.CENTER, yellowSeventeenth);
				}
				if (populate.get(j).contains("RED") || populate.get(j).contains("Red") || populate.get(j).contains("red")) {
					seventeenth.add(BorderLayout.EAST, redSeventeenth);
				}
				if (populate.get(j).contains("GREEN") || populate.get(j).contains("Green") || populate.get(j).contains("green")) {
					seventeenth.add(BorderLayout.WEST, greenSeventeenth);
				}
				if (populate.get(j).contains("ORANGE") || populate.get(j).contains("Orange") || populate.get(j).contains("orange")) {
					orange.setHorizontalAlignment(JLabel.CENTER);
					seventeenth.add(BorderLayout.CENTER, orange);
				}
				if (populate.get(j).contains("VIOLET") || populate.get(j).contains("Violet") || populate.get(j).contains("violet")) {
					seventeenth.add(BorderLayout.EAST, violet);
				}
			}
		}
		for (int j = 0; j < populate.size(); j++) {
			if (populate.get(j).contains(" 18 ")) {
				JLabel blueEighteenth = new JLabel("*");
		    	blueEighteenth.setForeground(Color.blue);
		    	blueEighteenth.setFont(blue.getFont().deriveFont(72f));
		    	JLabel greenEighteenth = new JLabel("*");
		    	greenEighteenth.setForeground(Color.green);
		    	greenEighteenth.setFont(green.getFont().deriveFont(72f));
		    	JLabel yellowEighteenth = new JLabel("*");
		    	yellowEighteenth.setForeground(Color.yellow);
		    	yellowEighteenth.setFont(yellow.getFont().deriveFont(72f));
		    	JLabel redEighteenth = new JLabel("*");
		    	redEighteenth.setForeground(Color.red);
		    	redEighteenth.setFont(red.getFont().deriveFont(72f));
		    	JLabel orange = new JLabel("*");
		    	orange.setForeground(Color.orange);
		    	orange.setFont(orange.getFont().deriveFont(72f));
		    	JLabel violet = new JLabel("*");
		    	violet.setForeground(new Color(186,85,211));
		    	violet.setFont(violet.getFont().deriveFont(72f)); 
				if (populate.get(j).contains("BLUE") || populate.get(j).contains("Blue") || populate.get(j).contains("blue")) {
					eighteenth.add(BorderLayout.WEST, blueEighteenth);
				}
				if (populate.get(j).contains("YELLOW") || populate.get(j).contains("Yellow") || populate.get(j).contains("yellow")) {
					yellowEighteenth.setHorizontalAlignment(JLabel.CENTER);
					eighteenth.add(BorderLayout.CENTER, yellowEighteenth);
				}
				if (populate.get(j).contains("RED") || populate.get(j).contains("Red") || populate.get(j).contains("red")) {
					eighteenth.add(BorderLayout.EAST, redEighteenth);
				}
				if (populate.get(j).contains("GREEN") || populate.get(j).contains("Green") || populate.get(j).contains("green")) {
					eighteenth.add(BorderLayout.WEST, greenEighteenth);
				}
				if (populate.get(j).contains("ORANGE") || populate.get(j).contains("Orange") || populate.get(j).contains("orange")) {
					orange.setHorizontalAlignment(JLabel.CENTER);
					eighteenth.add(BorderLayout.CENTER, orange);
				}
				if (populate.get(j).contains("VIOLET") || populate.get(j).contains("Violet") || populate.get(j).contains("violet")) {
					eighteenth.add(BorderLayout.EAST, violet);
				}
			}
		}
		for (int j = 0; j < populate.size(); j++) {
			if (populate.get(j).contains(" 19 ")) {
				JLabel blueNinteenth = new JLabel("*");
		    	blueNinteenth.setForeground(Color.blue);
		    	blueNinteenth.setFont(blue.getFont().deriveFont(72f));
		    	JLabel greenNinteenth = new JLabel("*");
		    	greenNinteenth.setForeground(Color.green);
		    	greenNinteenth.setFont(green.getFont().deriveFont(72f));
		    	JLabel yellowNinteenth = new JLabel("*");
		    	yellowNinteenth.setForeground(Color.yellow);
		    	yellowNinteenth.setFont(yellow.getFont().deriveFont(72f));
		    	JLabel redNineteenth = new JLabel("*");
		    	redNineteenth.setForeground(Color.red);
		    	redNineteenth.setFont(red.getFont().deriveFont(72f));
		    	JLabel orange = new JLabel("*");
		    	orange.setForeground(Color.orange);
		    	orange.setFont(orange.getFont().deriveFont(72f));
		    	JLabel violet = new JLabel("*");
		    	violet.setForeground(new Color(186,85,211));
		    	violet.setFont(violet.getFont().deriveFont(72f)); 
				if (populate.get(j).contains("BLUE") || populate.get(j).contains("Blue") || populate.get(j).contains("blue")) {
					nineteenth.add(BorderLayout.WEST, blueNinteenth);
				}
				if (populate.get(j).contains("YELLOW") || populate.get(j).contains("Yellow") || populate.get(j).contains("yellow")) {
					yellowNinteenth.setHorizontalAlignment(JLabel.CENTER);
					nineteenth.add(BorderLayout.CENTER, yellowNinteenth);
				}
				if (populate.get(j).contains("RED") || populate.get(j).contains("Red") || populate.get(j).contains("red")) {
					nineteenth.add(BorderLayout.EAST, redNineteenth);
				}
				if (populate.get(j).contains("GREEN") || populate.get(j).contains("Green") || populate.get(j).contains("green")) {
					nineteenth.add(BorderLayout.WEST, greenNinteenth);
				}
				if (populate.get(j).contains("ORANGE") || populate.get(j).contains("Orange") || populate.get(j).contains("orange")) {
					orange.setHorizontalAlignment(JLabel.CENTER);
					nineteenth.add(BorderLayout.CENTER, orange);
				}
				if (populate.get(j).contains("VIOLET") || populate.get(j).contains("Violet") || populate.get(j).contains("violet")) {
					nineteenth.add(BorderLayout.EAST, violet);
				}
			}
		}
		for (int j = 0; j < populate.size(); j++) {
			if (populate.get(j).contains(" 20 ")) {
				JLabel blueTwentieth = new JLabel("*");
		    	blueTwentieth.setForeground(Color.blue);
		    	blueTwentieth.setFont(blue.getFont().deriveFont(72f));
		    	JLabel greenTwentieth = new JLabel("*");
		    	greenTwentieth.setForeground(Color.green);
		    	greenTwentieth.setFont(green.getFont().deriveFont(72f));
		    	JLabel yellowTwentieth = new JLabel("*");
		    	yellowTwentieth.setForeground(Color.yellow);
		    	yellowTwentieth.setFont(yellow.getFont().deriveFont(72f));
		    	JLabel redTwentieth = new JLabel("*");
		    	redTwentieth.setForeground(Color.red);
		    	redTwentieth.setFont(red.getFont().deriveFont(72f));
		    	JLabel orange = new JLabel("*");
		    	orange.setForeground(Color.orange);
		    	orange.setFont(orange.getFont().deriveFont(72f));
		    	JLabel violet = new JLabel("*");
		    	violet.setForeground(new Color(186,85,211));
		    	violet.setFont(violet.getFont().deriveFont(72f)); 
				if (populate.get(j).contains("BLUE") || populate.get(j).contains("Blue") || populate.get(j).contains("blue")) {
					twentieth.add(BorderLayout.WEST, blueTwentieth);
				}
				if (populate.get(j).contains("YELLOW") || populate.get(j).contains("Yellow") || populate.get(j).contains("yellow")) {
					yellowTwentieth.setHorizontalAlignment(JLabel.CENTER);
					twentieth.add(BorderLayout.CENTER, yellowTwentieth);
				}
				if (populate.get(j).contains("RED") || populate.get(j).contains("Red") || populate.get(j).contains("red")) {
					twentieth.add(BorderLayout.EAST, redTwentieth);
				}
				if (populate.get(j).contains("GREEN") || populate.get(j).contains("Green") || populate.get(j).contains("green")) {
					twentieth.add(BorderLayout.WEST, greenTwentieth);
				}
				if (populate.get(j).contains("ORANGE") || populate.get(j).contains("Orange") || populate.get(j).contains("orange")) {
					orange.setHorizontalAlignment(JLabel.CENTER);
					twentieth.add(BorderLayout.CENTER, orange);
				}
				if (populate.get(j).contains("VIOLET") || populate.get(j).contains("Violet") || populate.get(j).contains("violet")) {
					twentieth.add(BorderLayout.EAST, violet);
				}
			}
		}
		for (int j = 0; j < populate.size(); j++) {
			if (populate.get(j).contains(" 21 ")) {
				JLabel blueTwentyfirst = new JLabel("*");
		    	blueTwentyfirst.setForeground(Color.blue);
		    	blueTwentyfirst.setFont(blue.getFont().deriveFont(72f));
		    	JLabel greenTwentyfirst = new JLabel("*");
		    	greenTwentyfirst.setForeground(Color.green);
		    	greenTwentyfirst.setFont(green.getFont().deriveFont(72f));
		    	JLabel yellowTwentyfirst = new JLabel("*");
		    	yellowTwentyfirst.setForeground(Color.yellow);
		    	yellowTwentyfirst.setFont(yellow.getFont().deriveFont(72f));
		    	JLabel redTwentyfirst = new JLabel("*");
		    	redTwentyfirst.setForeground(Color.red);
		    	redTwentyfirst.setFont(red.getFont().deriveFont(72f));
		    	JLabel orange = new JLabel("*");
		    	orange.setForeground(Color.orange);
		    	orange.setFont(orange.getFont().deriveFont(72f));
		    	JLabel violet = new JLabel("*");
		    	violet.setForeground(new Color(186,85,211));
		    	violet.setFont(violet.getFont().deriveFont(72f)); 
				if (populate.get(j).contains("BLUE") || populate.get(j).contains("Blue") || populate.get(j).contains("blue")) {
					twentyfirst.add(BorderLayout.WEST, blueTwentyfirst);
				}
				if (populate.get(j).contains("YELLOW") || populate.get(j).contains("Yellow") || populate.get(j).contains("yellow")) {
					yellowTwentyfirst.setHorizontalAlignment(JLabel.CENTER);
					twentyfirst.add(BorderLayout.CENTER, yellowTwentyfirst);
				}
				if (populate.get(j).contains("RED") || populate.get(j).contains("Red") || populate.get(j).contains("red")) {
					twentyfirst.add(BorderLayout.EAST, redTwentyfirst);
				}
				if (populate.get(j).contains("GREEN") || populate.get(j).contains("Green") || populate.get(j).contains("green")) {
					twentyfirst.add(BorderLayout.WEST, greenTwentyfirst);
				}
				if (populate.get(j).contains("ORANGE") || populate.get(j).contains("Orange") || populate.get(j).contains("orange")) {
					orange.setHorizontalAlignment(JLabel.CENTER);
					twentyfirst.add(BorderLayout.CENTER, orange);
				}
				if (populate.get(j).contains("VIOLET") || populate.get(j).contains("Violet") || populate.get(j).contains("violet")) {
					twentyfirst.add(BorderLayout.EAST, violet);
				}
			}
		}
		for (int j = 0; j < populate.size(); j++) {
			if (populate.get(j).contains(" 22 ")) {
				JLabel blueTwentysecond = new JLabel("*");
		    	blueTwentysecond.setForeground(Color.blue);
		    	blueTwentysecond.setFont(blue.getFont().deriveFont(72f));
		    	JLabel greenTwentysecond = new JLabel("*");
		    	greenTwentysecond.setForeground(Color.green);
		    	greenTwentysecond.setFont(green.getFont().deriveFont(72f));
		    	JLabel yellowTwentysecond = new JLabel("*");
		    	yellowTwentysecond.setForeground(Color.yellow);
		    	yellowTwentysecond.setFont(yellow.getFont().deriveFont(72f));
		    	JLabel redTwentysecond = new JLabel("*");
		    	redTwentysecond.setForeground(Color.red);
		    	redTwentysecond.setFont(red.getFont().deriveFont(72f));
		    	JLabel orange = new JLabel("*");
		    	orange.setForeground(Color.orange);
		    	orange.setFont(orange.getFont().deriveFont(72f));
		    	JLabel violet = new JLabel("*");
		    	violet.setForeground(new Color(186,85,211));
		    	violet.setFont(violet.getFont().deriveFont(72f)); 
				if (populate.get(j).contains("BLUE") || populate.get(j).contains("Blue") || populate.get(j).contains("blue")) {
					twentysecond.add(BorderLayout.WEST, blueTwentysecond);
				}
				if (populate.get(j).contains("YELLOW") || populate.get(j).contains("Yellow") || populate.get(j).contains("yellow")) {
					yellowTwentysecond.setHorizontalAlignment(JLabel.CENTER);
					twentysecond.add(BorderLayout.CENTER, yellowTwentysecond);
				}
				if (populate.get(j).contains("RED") || populate.get(j).contains("Red") || populate.get(j).contains("red")) {
					twentysecond.add(BorderLayout.EAST, redTwentysecond);
				}
				if (populate.get(j).contains("GREEN") || populate.get(j).contains("Green") || populate.get(j).contains("green")) {
					twentysecond.add(BorderLayout.WEST, greenTwentysecond);
				}
				if (populate.get(j).contains("ORANGE") || populate.get(j).contains("Orange") || populate.get(j).contains("orange")) {
					orange.setHorizontalAlignment(JLabel.CENTER);
					twentysecond.add(BorderLayout.CENTER, orange);
				}
				if (populate.get(j).contains("VIOLET") || populate.get(j).contains("Violet") || populate.get(j).contains("violet")) {
					twentysecond.add(BorderLayout.EAST, violet);
				}
			}
		}
		for (int j = 0; j < populate.size(); j++) {
			if (populate.get(j).contains(" 23 ")) {
				JLabel blueTwentythird = new JLabel("*");
		    	blueTwentythird.setForeground(Color.blue);
		    	blueTwentythird.setFont(blue.getFont().deriveFont(72f));
		    	JLabel greenTwentythird = new JLabel("*");
		    	greenTwentythird.setForeground(Color.green);
		    	greenTwentythird.setFont(green.getFont().deriveFont(72f));
		    	JLabel yellowTwentythird = new JLabel("*");
		    	yellowTwentythird.setForeground(Color.yellow);
		    	yellowTwentythird.setFont(yellow.getFont().deriveFont(72f));
		    	JLabel redTwentythird = new JLabel("*");
		    	redTwentythird.setForeground(Color.red);
		    	redTwentythird.setFont(red.getFont().deriveFont(72f));
		    	JLabel orange = new JLabel("*");
		    	orange.setForeground(Color.orange);
		    	orange.setFont(orange.getFont().deriveFont(72f));
		    	JLabel violet = new JLabel("*");
		    	violet.setForeground(new Color(186,85,211));
		    	violet.setFont(violet.getFont().deriveFont(72f)); 
				if (populate.get(j).contains("BLUE") || populate.get(j).contains("Blue") || populate.get(j).contains("blue")) {
					twentythird.add(BorderLayout.WEST, blueTwentythird);
				}
				if (populate.get(j).contains("YELLOW") || populate.get(j).contains("Yellow") || populate.get(j).contains("yellow")) {
					yellowTwentythird.setHorizontalAlignment(JLabel.CENTER);
					twentythird.add(BorderLayout.CENTER, yellowTwentythird);
				}
				if (populate.get(j).contains("RED") || populate.get(j).contains("Red") || populate.get(j).contains("red")) {
					twentythird.add(BorderLayout.EAST, redTwentythird);
				}
				if (populate.get(j).contains("GREEN") || populate.get(j).contains("Green") || populate.get(j).contains("green")) {
					twentythird.add(BorderLayout.WEST, greenTwentythird);
				}
				if (populate.get(j).contains("ORANGE") || populate.get(j).contains("Orange") || populate.get(j).contains("orange")) {
					orange.setHorizontalAlignment(JLabel.CENTER);
					twentythird.add(BorderLayout.CENTER, orange);
				}
				if (populate.get(j).contains("VIOLET") || populate.get(j).contains("Violet") || populate.get(j).contains("violet")) {
					twentythird.add(BorderLayout.EAST, violet);
				}
			}
		}
		for (int j = 0; j < populate.size(); j++) {
			if (populate.get(j).contains(" 24 ")) {
				JLabel blueTwentyfourth = new JLabel("*");
		    	blueTwentyfourth.setForeground(Color.blue);
		    	blueTwentyfourth.setFont(blue.getFont().deriveFont(72f));
		    	JLabel greenTwentyfourth = new JLabel("*");
		    	greenTwentyfourth.setForeground(Color.green);
		    	greenTwentyfourth.setFont(green.getFont().deriveFont(72f));
		    	JLabel yellowTwentyfourth = new JLabel("*");
		    	yellowTwentyfourth.setForeground(Color.yellow);
		    	yellowTwentyfourth.setFont(yellow.getFont().deriveFont(72f));
		    	JLabel redTwentyfourth = new JLabel("*");
		    	redTwentyfourth.setForeground(Color.red);
		    	redTwentyfourth.setFont(red.getFont().deriveFont(72f));
		    	JLabel orange = new JLabel("*");
		    	orange.setForeground(Color.orange);
		    	orange.setFont(orange.getFont().deriveFont(72f));
		    	JLabel violet = new JLabel("*");
		    	violet.setForeground(new Color(186,85,211));
		    	violet.setFont(violet.getFont().deriveFont(72f)); 
				if (populate.get(j).contains("BLUE") || populate.get(j).contains("Blue") || populate.get(j).contains("blue")) {
					twentyfourth.add(BorderLayout.WEST, blueTwentyfourth);
				}
				if (populate.get(j).contains("YELLOW") || populate.get(j).contains("Yellow") || populate.get(j).contains("yellow")) {
					yellowTwentyfourth.setHorizontalAlignment(JLabel.CENTER);
					twentyfourth.add(BorderLayout.CENTER, yellowTwentyfourth);
				}
				if (populate.get(j).contains("RED") || populate.get(j).contains("Red") || populate.get(j).contains("red")) {
					twentyfourth.add(BorderLayout.EAST, redTwentyfourth);
				}
				if (populate.get(j).contains("GREEN") || populate.get(j).contains("Green") || populate.get(j).contains("green")) {
					twentyfourth.add(BorderLayout.WEST, greenTwentyfourth);
				}
				if (populate.get(j).contains("ORANGE") || populate.get(j).contains("Orange") || populate.get(j).contains("orange")) {
					orange.setHorizontalAlignment(JLabel.CENTER);
					twentyfourth.add(BorderLayout.CENTER, orange);
				}
				if (populate.get(j).contains("VIOLET") || populate.get(j).contains("Violet") || populate.get(j).contains("violet")) {
					twentyfourth.add(BorderLayout.EAST, violet);
				}
			}
		}
		for (int j = 0; j < populate.size(); j++) {
			if (populate.get(j).contains(" 25 ")) {
				JLabel blueTwentyfifth = new JLabel("*");
		    	blueTwentyfifth.setForeground(Color.blue);
		    	blueTwentyfifth.setFont(blue.getFont().deriveFont(72f));
		    	JLabel greenTwentyfifth = new JLabel("*");
		    	greenTwentyfifth.setForeground(Color.green);
		    	greenTwentyfifth.setFont(green.getFont().deriveFont(72f));
		    	JLabel yellowTwentyfifth = new JLabel("*");
		    	yellowTwentyfifth.setForeground(Color.yellow);
		    	yellowTwentyfifth.setFont(yellow.getFont().deriveFont(72f));
		    	JLabel redTwentyfifth = new JLabel("*");
		    	redTwentyfifth.setForeground(Color.red);
		    	redTwentyfifth.setFont(red.getFont().deriveFont(72f));
		    	JLabel orange = new JLabel("*");
		    	orange.setForeground(Color.orange);
		    	orange.setFont(orange.getFont().deriveFont(72f));
		    	JLabel violet = new JLabel("*");
		    	violet.setForeground(new Color(186,85,211));
		    	violet.setFont(violet.getFont().deriveFont(72f)); 
				if (populate.get(j).contains("BLUE") || populate.get(j).contains("Blue") || populate.get(j).contains("blue")) {
					twentyfifth.add(BorderLayout.WEST, blueTwentyfifth);
				}
				if (populate.get(j).contains("YELLOW") || populate.get(j).contains("Yellow") || populate.get(j).contains("yellow")) {
					yellowTwentyfifth.setHorizontalAlignment(JLabel.CENTER);
					twentyfifth.add(BorderLayout.CENTER, yellowTwentyfifth);
				}
				if (populate.get(j).contains("RED") || populate.get(j).contains("Red") || populate.get(j).contains("red")) {
					twentyfifth.add(BorderLayout.EAST, redTwentyfifth);
				}
				if (populate.get(j).contains("GREEN") || populate.get(j).contains("Green") || populate.get(j).contains("green")) {
					twentyfifth.add(BorderLayout.WEST, greenTwentyfifth);
				}
				if (populate.get(j).contains("ORANGE") || populate.get(j).contains("Orange") || populate.get(j).contains("orange")) {
					orange.setHorizontalAlignment(JLabel.CENTER);
					twentyfifth.add(BorderLayout.CENTER, orange);
				}
				if (populate.get(j).contains("VIOLET") || populate.get(j).contains("Violet") || populate.get(j).contains("violet")) {
					twentyfifth.add(BorderLayout.EAST, violet);
				}
			}
		}
		for (int j = 0; j < populate.size(); j++) {
			if (populate.get(j).contains(" 26 ")) {
				JLabel blueTwentysixth = new JLabel("*");
		    	blueTwentysixth.setForeground(Color.blue);
		    	blueTwentysixth.setFont(blue.getFont().deriveFont(72f));
		    	JLabel greenTwentysixth = new JLabel("*");
		    	greenTwentysixth.setForeground(Color.green);
		    	greenTwentysixth.setFont(green.getFont().deriveFont(72f));
		    	JLabel yellowTwentysixth = new JLabel("*");
		    	yellowTwentysixth.setForeground(Color.yellow);
		    	yellowTwentysixth.setFont(yellow.getFont().deriveFont(72f));
		    	JLabel redTwentysixth = new JLabel("*");
		    	redTwentysixth.setForeground(Color.red);
		    	redTwentysixth.setFont(red.getFont().deriveFont(72f));
		    	JLabel orange = new JLabel("*");
		    	orange.setForeground(Color.orange);
		    	orange.setFont(orange.getFont().deriveFont(72f));
		    	JLabel violet = new JLabel("*");
		    	violet.setForeground(new Color(186,85,211));
		    	violet.setFont(violet.getFont().deriveFont(72f)); 
				if (populate.get(j).contains("BLUE") || populate.get(j).contains("Blue") || populate.get(j).contains("blue")) {
					twentysixth.add(BorderLayout.WEST, blueTwentysixth);
				}
				if (populate.get(j).contains("YELLOW") || populate.get(j).contains("Yellow") || populate.get(j).contains("yellow")) {
					yellowTwentysixth.setHorizontalAlignment(JLabel.CENTER);
					twentysixth.add(BorderLayout.CENTER, yellowTwentysixth);
				}
				if (populate.get(j).contains("RED") || populate.get(j).contains("Red") || populate.get(j).contains("red")) {
					twentysixth.add(BorderLayout.EAST, redTwentysixth);
				}
				if (populate.get(j).contains("GREEN") || populate.get(j).contains("Green") || populate.get(j).contains("green")) {
					twentysixth.add(BorderLayout.WEST, greenTwentysixth);
				}
				if (populate.get(j).contains("ORANGE") || populate.get(j).contains("Orange") || populate.get(j).contains("orange")) {
					orange.setHorizontalAlignment(JLabel.CENTER);
					twentysixth.add(BorderLayout.CENTER, orange);
				}
				if (populate.get(j).contains("VIOLET") || populate.get(j).contains("Violet") || populate.get(j).contains("violet")) {
					twentysixth.add(BorderLayout.EAST, violet);
				}
			}
		}
		for (int j = 0; j < populate.size(); j++) {
			if (populate.get(j).contains(" 27 ")) {
				JLabel blueTwentyseventh = new JLabel("*");
		    	blueTwentyseventh.setForeground(Color.blue);
		    	blueTwentyseventh.setFont(blue.getFont().deriveFont(72f));
		    	JLabel greenTwentyseventh = new JLabel("*");
		    	greenTwentyseventh.setForeground(Color.green);
		    	greenTwentyseventh.setFont(green.getFont().deriveFont(72f));
		    	JLabel yellowTwentyseventh = new JLabel("*");
		    	yellowTwentyseventh.setForeground(Color.yellow);
		    	yellowTwentyseventh.setFont(yellow.getFont().deriveFont(72f));
		    	JLabel redTwentyseventh = new JLabel("*");
		    	redTwentyseventh.setForeground(Color.red);
		    	redTwentyseventh.setFont(red.getFont().deriveFont(72f));
		    	JLabel orange = new JLabel("*");
		    	orange.setForeground(Color.orange);
		    	orange.setFont(orange.getFont().deriveFont(72f));
		    	JLabel violet = new JLabel("*");
		    	violet.setForeground(new Color(186,85,211));
		    	violet.setFont(violet.getFont().deriveFont(72f)); 
				if (populate.get(j).contains("BLUE") || populate.get(j).contains("Blue") || populate.get(j).contains("blue")) {
					twentyseventh.add(BorderLayout.WEST, blueTwentyseventh);
				}
				if (populate.get(j).contains("YELLOW") || populate.get(j).contains("Yellow") || populate.get(j).contains("yellow")) {
					yellowTwentyseventh.setHorizontalAlignment(JLabel.CENTER);
					twentyseventh.add(BorderLayout.CENTER, yellowTwentyseventh);
				}
				if (populate.get(j).contains("RED") || populate.get(j).contains("Red") || populate.get(j).contains("red")) {
					twentyseventh.add(BorderLayout.EAST, redTwentyseventh);
				}
				if (populate.get(j).contains("GREEN") || populate.get(j).contains("Green") || populate.get(j).contains("green")) {
					sixteenth.add(BorderLayout.WEST, greenTwentyseventh);
				}
				if (populate.get(j).contains("ORANGE") || populate.get(j).contains("Orange") || populate.get(j).contains("orange")) {
					orange.setHorizontalAlignment(JLabel.CENTER);
					twentyseventh.add(BorderLayout.CENTER, orange);
				}
				if (populate.get(j).contains("VIOLET") || populate.get(j).contains("Violet") || populate.get(j).contains("violet")) {
					twentyseventh.add(BorderLayout.EAST, violet);
				}
			}
		}
		for (int j = 0; j < populate.size(); j++) {
			if (populate.get(j).contains(" 28 ")) {
				JLabel blueTwentyeighth = new JLabel("*");
		    	blueTwentyeighth.setForeground(Color.blue);
		    	blueTwentyeighth.setFont(blue.getFont().deriveFont(72f));
		    	JLabel greenTwentyeighth = new JLabel("*");
		    	greenTwentyeighth.setForeground(Color.green);
		    	greenTwentyeighth.setFont(green.getFont().deriveFont(72f));
		    	JLabel yellowTwentyeighth = new JLabel("*");
		    	yellowTwentyeighth.setForeground(Color.yellow);
		    	yellowTwentyeighth.setFont(yellow.getFont().deriveFont(72f));
		    	JLabel redTwentyeighth = new JLabel("*");
		    	redTwentyeighth.setForeground(Color.red);
		    	redTwentyeighth.setFont(red.getFont().deriveFont(72f));
		    	JLabel orange = new JLabel("*");
		    	orange.setForeground(Color.orange);
		    	orange.setFont(orange.getFont().deriveFont(72f));
		    	JLabel violet = new JLabel("*");
		    	violet.setForeground(new Color(186,85,211));
		    	violet.setFont(violet.getFont().deriveFont(72f)); 
				if (populate.get(j).contains("BLUE") || populate.get(j).contains("Blue") || populate.get(j).contains("blue")) {
					twentyeighth.add(BorderLayout.WEST, blueTwentyeighth);
				}
				if (populate.get(j).contains("YELLOW") || populate.get(j).contains("Yellow") || populate.get(j).contains("yellow")) {
					yellowTwentyeighth.setHorizontalAlignment(JLabel.CENTER);
					twentyeighth.add(BorderLayout.CENTER, yellowTwentyeighth);
				}
				if (populate.get(j).contains("RED") || populate.get(j).contains("Red") || populate.get(j).contains("red")) {
					twentyeighth.add(BorderLayout.EAST, redTwentyeighth);
				}
				if (populate.get(j).contains("GREEN") || populate.get(j).contains("Green") || populate.get(j).contains("green")) {
					twentyeighth.add(BorderLayout.WEST, greenTwentyeighth);
				}
				if (populate.get(j).contains("ORANGE") || populate.get(j).contains("Orange") || populate.get(j).contains("orange")) {
					orange.setHorizontalAlignment(JLabel.CENTER);
					twentyeighth.add(BorderLayout.CENTER, orange);
				}
				if (populate.get(j).contains("VIOLET") || populate.get(j).contains("Violet") || populate.get(j).contains("violet")) {
					twentyeighth.add(BorderLayout.EAST, violet);
				}
			}
		}
		for (int j = 0; j < populate.size(); j++) {
			if (populate.get(j).contains(" 29 ")) {
				JLabel blueTwentyninth = new JLabel("*");
		    	blueTwentyninth.setForeground(Color.blue);
		    	blueTwentyninth.setFont(blue.getFont().deriveFont(72f));
		    	JLabel greenTwentyninth = new JLabel("*");
		    	greenTwentyninth.setForeground(Color.green);
		    	greenTwentyninth.setFont(green.getFont().deriveFont(72f));
		    	JLabel yellowTwentyninth = new JLabel("*");
		    	yellowTwentyninth.setForeground(Color.yellow);
		    	yellowTwentyninth.setFont(yellow.getFont().deriveFont(72f));
		    	JLabel redTwentyninth = new JLabel("*");
		    	redTwentyninth.setForeground(Color.red);
		    	redTwentyninth.setFont(red.getFont().deriveFont(72f));
		    	JLabel orange = new JLabel("*");
		    	orange.setForeground(Color.orange);
		    	orange.setFont(orange.getFont().deriveFont(72f));
		    	JLabel violet = new JLabel("*");
		    	violet.setForeground(new Color(186,85,211));
		    	violet.setFont(violet.getFont().deriveFont(72f)); 
				if (populate.get(j).contains("BLUE") || populate.get(j).contains("Blue") || populate.get(j).contains("blue")) {
					twentyninth.add(BorderLayout.WEST, blueTwentyninth);
				}
				if (populate.get(j).contains("YELLOW") || populate.get(j).contains("Yellow") || populate.get(j).contains("yellow")) {
					yellowTwentyninth.setHorizontalAlignment(JLabel.CENTER);
					twentyninth.add(BorderLayout.CENTER, yellowTwentyninth);
				}
				if (populate.get(j).contains("RED") || populate.get(j).contains("Red") || populate.get(j).contains("red")) {
					twentyninth.add(BorderLayout.EAST, redTwentyninth);
				}
				if (populate.get(j).contains("GREEN") || populate.get(j).contains("Green") || populate.get(j).contains("green")) {
					twentyninth.add(BorderLayout.WEST, greenTwentyninth);
				}
				if (populate.get(j).contains("ORANGE") || populate.get(j).contains("Orange") || populate.get(j).contains("orange")) {
					orange.setHorizontalAlignment(JLabel.CENTER);
					twentyninth.add(BorderLayout.CENTER, orange);
				}
				if (populate.get(j).contains("VIOLET") || populate.get(j).contains("Violet") || populate.get(j).contains("violet")) {
					twentyninth.add(BorderLayout.EAST, violet);
				}
			}
		}
		for (int j = 0; j < populate.size(); j++) {
			if (populate.get(j).contains(" 30 ")) {
				JLabel blueThirtieth = new JLabel("*");
		    	blueThirtieth.setForeground(Color.blue);
		    	blueThirtieth.setFont(blue.getFont().deriveFont(72f));
		    	JLabel greenThirieth = new JLabel("*");
		    	greenThirieth.setForeground(Color.green);
		    	greenThirieth.setFont(green.getFont().deriveFont(72f));
		    	JLabel yellowThirtieth = new JLabel("*");
		    	yellowThirtieth.setForeground(Color.yellow);
		    	yellowThirtieth.setFont(yellow.getFont().deriveFont(72f));
		    	JLabel redThirtieth = new JLabel("*");
		    	redThirtieth.setForeground(Color.red);
		    	redThirtieth.setFont(red.getFont().deriveFont(72f));
		    	JLabel orange = new JLabel("*");
		    	orange.setForeground(Color.orange);
		    	orange.setFont(orange.getFont().deriveFont(72f));
		    	JLabel violet = new JLabel("*");
		    	violet.setForeground(new Color(186,85,211));
		    	violet.setFont(violet.getFont().deriveFont(72f)); 
				if (populate.get(j).contains("BLUE") || populate.get(j).contains("Blue") || populate.get(j).contains("blue")) {
					thirtieth.add(BorderLayout.WEST, blueThirtieth);
				}
				if (populate.get(j).contains("YELLOW") || populate.get(j).contains("Yellow") || populate.get(j).contains("yellow")) {
					yellowThirtieth.setHorizontalAlignment(JLabel.CENTER);
					thirtieth.add(BorderLayout.CENTER, yellowThirtieth);
				}
				if (populate.get(j).contains("RED") || populate.get(j).contains("Red") || populate.get(j).contains("red")) {
					thirtieth.add(BorderLayout.EAST, redThirtieth);
				}
				if (populate.get(j).contains("GREEN") || populate.get(j).contains("Green") || populate.get(j).contains("green")) {
					thirtieth.add(BorderLayout.WEST, greenThirieth);
				}
				if (populate.get(j).contains("ORANGE") || populate.get(j).contains("Orange") || populate.get(j).contains("orange")) {
					orange.setHorizontalAlignment(JLabel.CENTER);
					thirtieth.add(BorderLayout.CENTER, orange);
				}
				if (populate.get(j).contains("VIOLET") || populate.get(j).contains("Violet") || populate.get(j).contains("violet")) {
					thirtieth.add(BorderLayout.EAST, violet);
				}
			}
		}
		for (int j = 0; j < populate.size(); j++) {
			if (populate.get(j).contains(" 31 ")) {
				JLabel blueThirtyfirst = new JLabel("*");
		    	blueThirtyfirst.setForeground(Color.blue);
		    	blueThirtyfirst.setFont(blue.getFont().deriveFont(72f));
		    	JLabel greenThirtyfirst = new JLabel("*");
		    	greenThirtyfirst.setForeground(Color.green);
		    	greenThirtyfirst.setFont(green.getFont().deriveFont(72f));
		    	JLabel yellowThirtyfirst = new JLabel("*");
		    	yellowThirtyfirst.setForeground(Color.yellow);
		    	yellowThirtyfirst.setFont(yellow.getFont().deriveFont(72f));
		    	JLabel redThirtyfirst = new JLabel("*");
		    	redThirtyfirst.setForeground(Color.red);
		    	redThirtyfirst.setFont(red.getFont().deriveFont(72f));
		    	JLabel orange = new JLabel("*");
		    	orange.setForeground(Color.orange);
		    	orange.setFont(orange.getFont().deriveFont(72f));
		    	JLabel violet = new JLabel("*");
		    	violet.setForeground(new Color(186,85,211));
		    	violet.setFont(violet.getFont().deriveFont(72f)); 
				if (populate.get(j).contains("BLUE") || populate.get(j).contains("Blue") || populate.get(j).contains("blue")) {
					thirtyfirst.add(BorderLayout.WEST, blueThirtyfirst);
				}
				if (populate.get(j).contains("YELLOW") || populate.get(j).contains("Yellow") || populate.get(j).contains("yellow")) {
					yellowThirtyfirst.setHorizontalAlignment(JLabel.CENTER);
					thirtyfirst.add(BorderLayout.CENTER, yellowThirtyfirst);
				}
				if (populate.get(j).contains("RED") || populate.get(j).contains("Red") || populate.get(j).contains("red")) {
					thirtyfirst.add(BorderLayout.EAST, redThirtyfirst);
				}
				if (populate.get(j).contains("GREEN") || populate.get(j).contains("Green") || populate.get(j).contains("green")) {
					sixteenth.add(BorderLayout.WEST, greenThirtyfirst);
				}
				if (populate.get(j).contains("ORANGE") || populate.get(j).contains("Orange") || populate.get(j).contains("orange")) {
					orange.setHorizontalAlignment(JLabel.CENTER);
					thirtyfirst.add(BorderLayout.CENTER, orange);
				}
				if (populate.get(j).contains("VIOLET") || populate.get(j).contains("Violet") || populate.get(j).contains("violet")) {
					thirtyfirst.add(BorderLayout.EAST, violet);
				}
			}
		}
  	
//Calendar JFrame 	
  	//creating a new frame
      classproject frame = new classproject();
      //creating 2 panels, one for the month and year and the other one for the days of the month
      JPanel panel1 = new JPanel();
      JPanel panel2 = new JPanel();
      JPanel panel3 = new JPanel();
      Color color = panel1.getBackground();
      //creating a jtextfield that includes the current month and the current year
  	JTextField month = new JTextField(months[Calendar.getInstance().get(Calendar.MONTH)]+" "+(grego.getTime().getYear()+1900));
  	JTextArea today = new JTextArea(weekDays[Calendar.getInstance().get(Calendar.DAY_OF_WEEK)-1] + ", " + months[Calendar.getInstance().get(Calendar.MONTH)] + " " + Calendar.getInstance().get(Calendar.DATE) + "\n\n");
  	//creating a font for the month and year
  	Font Month = new Font("Arial", Font.BOLD, 60);
  	Font Today = new Font("Arial", Font.BOLD, 32);
  	Font todayPop = new Font("Arial", Font.BOLD, 32);
  	//setting the font
  	month.setFont(Month);
  	today.setFont(Today);
  	//giving the foreground color
  	month.setForeground(Color.BLACK);
  	today.setForeground(Color.BLACK);
  
  	//setting the bounds
  	month.setBounds(400,30,600,100);
  	//not able to edit
  	month.setEditable(false);	month.setBorder(BorderFactory.createLineBorder(color));
  	today.setEditable(false);	today.setBorder(BorderFactory.createLineBorder(color));
  	//setting the bounds of the first panel
		panel1.setBounds(20,20,1130,200);
		//create stroke border
		panel1.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2.0f)));
		//creating a new grid
		panel1.setLayout(new GridLayout(0, 1));
		//setting the bounds for the second panel
		panel2.setBounds(20,230,1130,710);
		//creating the new border
		panel2.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2.0f)));
		panel2.setLayout(new GridLayout(0, 1));
		//set the frame size
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setLayout(null);
		frame.setVisible(true);
		//adding the month and year
		frame.add(month);
		panel3.add(today);
		frame.add(addEvent);

		panel3.setBounds(1175,20,550,920);
		//create stroke border
		panel3.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2.0f)));
		//creating a new grid
		panel3.setLayout(new GridLayout(0, 1));

		ArrayList<String> todayEvents = new ArrayList<>();
		String month2 = months[Calendar.getInstance().get(Calendar.MONTH)].substring(0, 3);
		
		// Create a variable to search the arraylist to find events for today's date. 
		String todayInt = (month2 + " " + Calendar.getInstance().get(Calendar.DATE));
		today.setLineWrap(true);
		// Search for the previously described variable. If found, the side panel is populated with events for today. 
		for (int k = 0; k < populate2.size(); k++) {
			if (populate2.get(k).contains(todayInt)) {
				today.append("\n" + populate2.get(k));
			}
		}
		colors.setBounds(100, 50, 200, 80);
		refresh.setBounds(200, 150, 200, 50);
		frame.add(refresh);
		frame.add(colors);
		
		share.setBounds(100, 150,50, 50);
		share.setBackground(Color.LIGHT_GRAY);
		frame.add(share);
		//adding each button representing each day
		ArrayList<Integer> thirtyone=new ArrayList<Integer>();
		thirtyone.add(0);
		thirtyone.add(2);
		thirtyone.add(4);
		thirtyone.add(6);
		thirtyone.add(7);
		thirtyone.add(9);
		thirtyone.add(11);
		ArrayList<Integer> thirty=new ArrayList<Integer>();
		thirty.add(3);
		thirty.add(5);
		thirty.add(8);
		thirty.add(10);
		ArrayList<Integer> twentyeight=new ArrayList<Integer>();
		twentyeight.add(1);
		
		
		//adding each button representing each day
		if(thirtyone.contains(Calendar.getInstance().get(Calendar.MONTH))) {
		for(int i=0;i<one.size();i++) {
			frame.add(one.get(i));}
		}
		if(thirty.contains(Calendar.getInstance().get(Calendar.MONTH))) {
			for(int i=0;i<30;i++) {
				frame.add(one.get(i));}
			}
		if(twentyeight.contains(Calendar.getInstance().get(Calendar.MONTH))) {
			for(int i=0;i<28;i++) {
				frame.add(one.get(i));}
			}



		//adding the panels
		frame.add(panel1, BorderLayout.CENTER);
		frame.add(panel2, BorderLayout.CENTER);
		frame.add(panel3, BorderLayout.CENTER);
		// disposing the JFrame when the user hits the close button
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		colors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent colors){ 

				  classproject options = new classproject();
			        JPanel title = new JPanel();
			        JPanel option = new JPanel();
			        options.setExtendedState(JFrame.MAXIMIZED_BOTH);
			        Color color = panel1.getBackground();
			        Font schedule = new Font("Arial", Font.BOLD, 48);
			    	JTextField scheduling = new JTextField("Meaning of the Colors");
			    	
			    	JTextField red= new JTextField("");
			    	JTextField red2= new JTextField("Appointments");
			    	
			    	JTextField blue= new JTextField("");
			    	JTextField blue2= new JTextField("Birthdays/Anniversaries");
			    	
			    	JTextField yellow= new JTextField("");
			    	JTextField yellow2= new JTextField("Activities");
			    	
			    	JTextField green= new JTextField("");
			    	JTextField green2= new JTextField("Vacations");
			    	
			    	JTextField pink= new JTextField("");
			    	JTextField pink2= new JTextField("Due Dates");
			    	
			    	JTextField orange= new JTextField("");
			    	JTextField orange2= new JTextField("Dates");
			    	
			    	orange.setBackground(Color.ORANGE);
			    	orange.setBounds(100, 700, 80, 80);
			    	
			    	orange2.setBounds(300, 700, 350, 80);
			    	orange2.setFont(schedule);
			    	
			    	pink.setBackground(Color.MAGENTA);
			    	pink.setBounds(100, 600, 80, 80);
			    	
			    	pink2.setBounds(300, 600, 350, 80);
			    	pink2.setFont(schedule);
			    	
			    	green.setBackground(Color.GREEN);
			    	green.setBounds(100, 500, 80, 80);
			    	
			    	green2.setBounds(300, 500, 350, 80);
			    	green2.setFont(schedule);
			    	
			    	yellow.setBackground(Color.YELLOW);
			    	yellow.setBounds(100, 400, 80, 80);
			    	
			    	yellow2.setBounds(300, 400, 350, 80);
			    	yellow2.setFont(schedule);
			    	
			    	blue.setBackground(Color.BLUE);
			    	blue.setBounds(100, 300, 80, 80);
			    	
			    	blue2.setBounds(300, 300, 650, 80);
			    	blue2.setFont(schedule);
			    	
			    	red.setBackground(Color.RED);
			    	red.setBounds(100, 200, 80, 80);
			    	
			    	red2.setBounds(300, 200, 650, 80);
			    	red2.setFont(schedule);

			    	
			    	scheduling.setFont(schedule);
			    	scheduling.setForeground(Color.black);
			    	scheduling.setBounds(250,50,800,80);
			    	red2.setEditable(false);
			    	blue2.setEditable(false);
			    	yellow2.setEditable(false);
			    	green2.setEditable(false);
			    	pink2.setEditable(false);
			    	orange2.setEditable(false);
			    	scheduling.setEditable(false);	scheduling.setBorder(BorderFactory.createLineBorder(color));
					options.setSize(1000, 1000);
					options.setLayout(null);
					options.setVisible(true);
					options.add(scheduling);
					options.add(red);
					options.add(red2);
					options.add(blue);
					options.add(blue2);
					options.add(yellow);
					options.add(yellow2);
					options.add(green);
					options.add(green2);
					options.add(pink);
					options.add(pink2);
					options.add(orange);
					options.add(orange2);
					
					options.add(title, BorderLayout.CENTER);
					options.add(option, BorderLayout.CENTER);
					options.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		

		
		// Creating new JFrame that opens anytime the user clicks the '+' Icon to 
  	// Adds and saves an event to the database
		addEvent.addActionListener(new ActionListener() 
    	{
			
			// implementing all elements
			
			classproject addElement = new classproject();
			
			JTextField addingEvents;
		    JTextField eventName;
		    JTextArea reason;
		    
		    JComboBox EventEndDate;
		    JComboBox EventStartDate;
		    JComboBox eventStart;
		    JComboBox eventEnd;
		    JComboBox eventColor;
		    
		    JLabel startTimeLabel;
		    JLabel endTimeLabel;
		    JLabel eventColorLabel;
		    
		    JButton submit;
		    
		    GregorianCalendar calendar;
		    
		    
    		public void actionPerformed(ActionEvent events)
    		{
    			// Event Start times
    			
    		    String[] Times = {"12:00 AM", "12:15 AM", "12:30 AM", "12:45 AM", 
    		    		"1:00 AM", "1:15 AM", "1:30 AM", "1:45 AM", "2:00 AM", "2:15 AM",
    		    		"2:30 AM", "2:45 AM", "3:00 AM", "3:15 AM", "3:30 AM", "3:45 AM",
    		    		"4:00 AM", "4:15 AM", "4:30 AM", "4:45 AM", "5:00 AM", "5:15 AM",
    		    		"5:30 AM", "5:45 AM", "6:00 AM", "6:15 AM", "6:30 AM", "6:45 AM",
    		    		"7:00 AM", "7:15 AM", "7:30 AM", "7:45 AM", "8:00 AM", "8:15 AM",
    		    		"8:30 AM", "8:45 AM", "9:00 AM", "9:15 AM", "9:30 AM", "9:45 AM", 
    		    		"10:00 AM", "10:15 AM", "10:30 AM", "10:45 AM", "11:00 AM", "11:15 AM",
    		    		"11:30 AM", "11:45 AM", "12:00 PM", "12:15 PM", "12:30 PM", "12:45 PM",
    		    		"1:00 PM", "1:15 PM", "1:30 PM", "1:45 PM", "2:00 PM", "2:15 PM",
    		    		"2:30 PM", "2:45 PM", "3:00 PM", "3:15 PM", "3:30 PM", "3:45 PM",
    		    		"4:00 PM", "4:15 PM", "4:30 PM", "4:45 PM", "5:00 PM", "5:15 PM",
    		    		"5:30 PM", "5:45 PM", "6:00 PM", "6:15 PM", "6:30 PM", "6:45 PM",
    		    		"7:00 PM", "7:15 PM", "7:30 PM", "7:45 PM", "8:00 PM", "8:15 PM",
    		    		"8:30 PM", "8:45 PM", "9:00 PM", "9:15 PM", "9:30 PM", "9:45 PM", 
    		    		"10:00 PM", "10:15 PM", "10:30 PM", "10:45 PM", "11:00 PM", "11:15 PM",
    		    		"11:30 PM", "11:45 PM"};
    		    
    		    
    		    // Event color options (Primary & Secondary colors) 
    		    // will implement the actual color so user can reference
    		    
    		    String[] Colors = {"None","Red", "Yellow", "Blue",
    		    		"Orange", "Green", "Violet"};
    		    
    		    // creating all elements
    		    addingEvents = new JTextField("New Event");
    		    eventName = new JTextField("title");
    		    EventStartDate =  new JComboBox();
    		    EventEndDate =  new JComboBox();
    		    reason =new JTextArea("Description");
    		    eventStart = new JComboBox(Times);
    		    eventEnd = new JComboBox(Times);
    		    eventColor = new JComboBox(Colors);
    		    startTimeLabel = new JLabel("Starts:");
    		    endTimeLabel =  new JLabel("Ends:");
    		    eventColorLabel = new JLabel("Event Color:");
    		    submit = new JButton("ADD");
    		    
    		    // Necessary adjustments
    		    Font addEvent = new Font("Courier", Font.BOLD, 40);
    		    addingEvents.setFont(new Font("Courier",Font.BOLD,80));
    		    addingEvents.setEditable(false);
    		    addingEvents.setBorder(BorderFactory.createLineBorder(color));
    		    eventName.setFont(addEvent);
    		    EventStartDate.setFont(addEvent);
    		    EventEndDate.setFont(addEvent);
    		    reason.setFont(addEvent);
    		    eventStart.setFont(addEvent);
    		    eventEnd.setFont(addEvent);
    		    eventColor.setFont(addEvent);
    		    startTimeLabel.setFont(addEvent);
    		    endTimeLabel.setFont(addEvent);
    		    eventColorLabel.setFont(addEvent);
    		    submit.setFont(addEvent);
    		    
    		    eventName.setHorizontalAlignment(JTextField.CENTER);
    		    Font EventNameFont = new Font("Arial", Font.ITALIC, 40);
    		    eventName.setFont(EventNameFont);
    		    
    		    
    		    class DateItem{
    		    Date mDate;
    		    
    		    public DateItem(Date date) {
    		    	mDate = date;
    		    }
    		    public Date getDate() {
    		        return mDate;
    		    }
    		    public String toString() {

    		        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

    		        return sdf.format(mDate);
    		    }
    		    }
    		    
    		    Calendar calendar = Calendar.getInstance();
				
    		    for (int i = 0; i < 365; ++i) {
    		    	EventStartDate.addItem(new DateItem(calendar.getTime()));
    		    	EventEndDate.addItem(new DateItem(calendar.getTime()));
    		        calendar.add(Calendar.DATE, 1);
    		    }

    		    // Setting the place of each element on the JPanel
    		    addingEvents.setBounds(750, 30,450,80);
    		    
    		    eventName.setBounds(750, 150, 500, 50);
    		    reason.setBounds(500, 500, 800, 400);
    		    
    		    EventStartDate.setBounds(600, 300, 300, 50);
    		    EventEndDate.setBounds(600, 350, 300, 50);
    		    
    		   
    		    
    		    startTimeLabel.setBounds(10,300, 300,50);
    		    eventStart.setBounds(220, 300, 300, 50);
    		    
    		    endTimeLabel.setBounds(10, 350, 300, 50);
    		    eventEnd.setBounds(220, 350, 300, 50);
    		    
    		    eventColorLabel.setBounds(1000,300,300,50);
    		    eventColor.setBounds(1300, 300, 300, 50);
    		    
    		    submit.setBounds(1350,700,150,80);
    		    
    		    
    		    // Size of the JPane
    		    addElement.setExtendedState(JFrame.MAXIMIZED_BOTH);
    		    addElement.setLayout(null);
    		    addElement.setVisible(true);
    		    
    		    
    		    // Adding all elements to the JPane for visibility.
    		    addElement.add(addingEvents);
    		    addElement.add(eventName);
    		    addElement.add(reason);
    		    
    		    
    		   
    		    addElement.add(EventStartDate);
    		    addElement.add(EventEndDate);
    		   
    		    
    		    addElement.add(eventEnd);
    		    addElement.add(endTimeLabel);
    		    addElement.add(startTimeLabel);
    		    addElement.add(eventStart);
    		    addElement.add(eventColor);
    		    addElement.add(eventColorLabel);
    		    addElement.add(submit);
    		
    		    // When the 'ADD' button is clicked the event is created and saved to the database
    		    // then closes the JPane so user can either add another event or view event by clicking
    		    // on each day
    		    submit.addActionListener(new ActionListener() {
    		        public void actionPerformed(ActionEvent submitting)
    		        {
    		        	String stringEvent = eventName.getText();
    		        	String startTimeDate = (EventStartDate.getSelectedItem().toString() + " "+ eventStart.getSelectedItem().toString());
    		        	String endTimeDate = (EventEndDate.getSelectedItem().toString() + " " + eventEnd.getSelectedItem().toString());
    		        	String stringNotes = reason.getText();
    		        	String selectedColor = eventColor.getSelectedItem().toString();
    		            addElement.dispose();
    		            
    		            
    		            // Creates new event and saves to the database
    		            CreateEvent event = new CreateEvent(userset.getText(), stringEvent, stringNotes, startTimeDate, endTimeDate, selectedColor);
    		            
    		            // Error control
    		            try {
							event.createNewEvent();
						} catch (ClassNotFoundException | SQLException e1) {
							System.out.println(e1);
    		        }}
    		    });
    		    
    		}
    	});
		
		
        try {
        	ResultSet listofitems=pullevent.retriveEventForUser();
    } catch (ClassNotFoundException | SQLException e2) {
        e2.printStackTrace();
    }
  		
        // Creates panes that we can add to
        JPanel option = new JPanel();
        classproject options = new classproject();
        JPanel title = new JPanel();
        
        
        for(int g=0;g<one.size();g++) {
			//creating a new Jframe that will open up when the user clicks any of the day buttons
        	int j = (g+1);
        	LocalDate localDate = LocalDate.of(Calendar.getInstance().get(Calendar.YEAR),  Calendar.getInstance().get(Calendar.MONTH)+1, g+1); 
        	
            // Find the day from the local date
            DayOfWeek dayOfWeek = DayOfWeek.from(localDate);
            
            // first converting the dayOfWeek to a String value then splitting the string
            // between first letter and remaining, first letter is left capital then remaining is
            // lowercase then combine the two strings back together for proper casing
            String convertedToString = dayOfWeek.toString();
            String firstLetter = convertedToString.substring(0,1);
            String remainingLettters = convertedToString.substring(1, convertedToString.length());
            remainingLettters = remainingLettters.toLowerCase();
            String properDayofWeek = firstLetter + remainingLettters;

            // action listener on the day clicked
		one.get(g).addActionListener(new ActionListener() {
			// instance variables
			String l = localDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
			String[] localDateArray = l.split(" ");
         
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd");
			String formattedDay = formatter.format(localDate);
			
			List<String> eventNameArray = new ArrayList<String>(); 
			JTextField addingDailyEvents = new JTextField();
			JLabel eventLabel;
			
			JTextArea eventReasoning = new JTextArea();
			JButton chooseEvent;
			
			String eventName1;
			String eventReason1;
			String start_Time1;
			String end_Time1;
			
			
			public void actionPerformed(ActionEvent event) {
				
		    	//try block needed because of sql requitemnt for throwing errors
		    	try {
		    		
		    	      //pulls all the events for the user from the class retriveEventForUser

		    	         ResultSet listofitems=pullevent.retriveEventForUser();
		    	         JComboBox eventNameCombo = new JComboBox();
		    	         
		    	      		while(listofitems.next()) {
		    	      			// pulling the events from sql database
		    	      			RetriveEvent pullevent1 = new RetriveEvent("nathan.mnsu");
		    	      			
		    	      			eventName1 = listofitems.getString("EVENTNAME");
		        				eventReason1 = listofitems.getString("REASON");
		        				start_Time1 = listofitems.getString("START_TIME");
		        				end_Time1 = listofitems.getString("END_TIME");
		        				
		        				// splitting the list so we can iterate thru
		                        String[] start_Time_List1 = start_Time1.split(" ");
		                        String monthly=start_Time_List1[0];
		                        String daily = start_Time_List1[1];
		                        String yearly = start_Time_List1[2];

		                        
		                        String[] start_Time_List = start_Time1.split(" +");
		                        String startMonth=start_Time_List[0];
		                        int startDay =Integer.parseInt(start_Time_List[1]);
		                        int startYear = Integer.parseInt(start_Time_List[2]);
		                        String startTime = start_Time_List[3];

		                        String[] end_Time_List = end_Time1.split(" +");
		                        String endMonth=end_Time_List[0];
		                        int endDay =Integer.parseInt(end_Time_List[1]);
		                        int endyear = Integer.parseInt(end_Time_List[2]);
		                        String endTime1 = start_Time_List[3];

		                        // allows us to give the month proper numeric value to reference
		        				int monthNum = -1;
		        				if (startMonth.equals("Jan")) {
		        					monthNum = 0;  		
		        				}else if(startMonth.equals("Feb")) {
		        					monthNum = 1;
		        				}else if(startMonth.equals("Mar")) {
		        					monthNum = 2;
		        				}else if(startMonth.equals("Apr")) {
		        					monthNum = 3;
		        				}else if(startMonth.equals("May")) {
		        					monthNum = 4;
		        				}else if(startMonth.equals("Jun")) {
		        					monthNum = 5;
		        				}else if(startMonth.equals("Jul")) {
		        					monthNum = 6;
		        				}else if(startMonth.equals("Aug")) {
		        					monthNum = 7;
		        				}else if(startMonth.equals("Sep")) {
		        					monthNum = 8;
		        				}else if(startMonth.equals("Oct")) {
		        					monthNum = 9;
		        				}else if(startMonth.equals("Nov")) {
		        					monthNum = 10;
		        				}else if(startMonth.equals("Dec")) {
		        					monthNum =	11;
		        				}
		        				int endMonthNum = -1;
		        				if (endMonth.equals("Jan")) {
		        					endMonthNum = 0;
		        				}else if(endMonth.equals("Feb")) {
		        					endMonthNum = 1;
		        				}else if(endMonth.equals("Mar")) {
		        					endMonthNum = 2;
		        				}else if(endMonth.equals("Apr")) {
		        					endMonthNum = 3;
		        				}else if(endMonth.equals("May")) {
		        					endMonthNum = 4;
		        				}else if(endMonth.equals("Jun")) {
		        					endMonthNum = 5;
		        				}else if(endMonth.equals("Jul")) {
		        					endMonthNum = 6;
		        				}else if(endMonth.equals("Aug")) {
		        					endMonthNum = 7;
		        				}else if(endMonth.equals("Sep")) {
		        					endMonthNum = 8;
		        				}else if(endMonth.equals("Oct")) {
		        					endMonthNum = 9;
		        				}else if(endMonth.equals("Nov")) {
		        					endMonthNum = 10;
		        				}else if(endMonth.equals("Dec")) {
		        					endMonthNum = 11;
		        				}
		        			
		        				
		        				// day in year of start date
		        				Calendar startDayInYear = new  GregorianCalendar(2020,monthNum,startDay);
		                        //System.out.println("Start day: "+startDayInYear.get(startDayInYear.DAY_OF_YEAR));

		                        //day in year of end date
		                        Calendar endDayInYear = new  GregorianCalendar(2020,endMonthNum,endDay);
		                       // System.out.println("End day: "+endDayInYear.get(endDayInYear.DAY_OF_YEAR));



		                        //year, month, day--change to month that button is in and day that button is on
		                        Calendar dayInYearOfButton = new  GregorianCalendar(2020,10,j);
		                        int x= dayInYearOfButton.get(dayInYearOfButton.DAY_OF_YEAR);
		                        //System.out.println("Button pressed: "+x);
		        				
		        			
		        				
		        				// checks if event occurs between numeric days so if day occurs between ie. day 246 & 250 
		                        // display these events in the combobox for user to select event
		                        int z =2020;
                    			
		        				if(x>=startDayInYear.get(startDayInYear.DAY_OF_YEAR)&&x<=endDayInYear.get(endDayInYear.DAY_OF_YEAR)&&z==2020) {					
		        					//System.out.printf("%-20s%-35s%-25s%-25s%s%n",listofitems.getString("EVENTNAME"),listofitems.getString("REASON"),listofitems.getString("START_TIME"),listofitems.getString("END_TIME"),listofitems.getString("COLOR"));
		        					
		        					
		        					// adds eventName to the combobox for user to select from
		        					//eventNameArray.add(listofitems.getString("EVENTNAME"));
		        					eventNameCombo.addItem(listofitems.getString("EVENTNAME"));
		        					
		        					
		        				}
		                       
		    	      		}
		    	      	eventLabel = new JLabel("Choose Event:");
		                //System.out.println("Events on this day!\n);
		                chooseEvent = new JButton("CHOOSE");			
		                eventLabel.setBounds(15, 150, 80, 30);
                   		eventNameCombo.setBounds(100,150,300,40);
                   		chooseEvent.setBounds(400,500,100,30);		
                   		eventReasoning = new JTextArea("Description: " + eventReason1 + 
                   					" \n\n\n\n\nStart Time: " + start_Time1 + "\n\nEnd Time: " + end_Time1);
                   			
                   			//System.out.println(eventReason1);
                   		Font reasoning = new Font("Arial", Font.BOLD, 16);
                   		eventReasoning.setFont(reasoning);
                   		
                   		options.add(eventNameCombo);
                   		options.add(chooseEvent);
                   		options.add(eventLabel);	
                   		options.add(eventReasoning);
                   		// action listener on the comboBox so we can display the event the user
                   		// selected such as event notes, start and end times.
                   		chooseEvent.addActionListener(new ActionListener() {
                				public void actionPerformed(ActionEvent name)
                		        {
                					int inteventChosen = eventNameCombo.getSelectedIndex();
                					String eventChosen=eventNameCombo.getItemAt(inteventChosen).toString();
                					
                					eventReasoning.setBounds(100, 200, 650, 275);
                					eventReasoning.setForeground(Color.black);
                					eventReasoning.setBorder(BorderFactory.createLineBorder(color));
                					eventReasoning.setEditable(false);
                					//eventReasoning=null;
                		        }
                				
                				
                			});
		    	      		
		
		    	      //catches sql errors
		    		} catch (ClassNotFoundException | SQLException e2) {
		    				e2.printStackTrace();
		    	}
				
		        // Displays the day clicked on by the user. in the format DAY, month year
		        JTextField scheduling = new JTextField(properDayofWeek + ", " + months[Calendar.getInstance().get(Calendar.MONTH)] + " " + j + "\n\n");
		        scheduling.setHorizontalAlignment(JTextField.CENTER);
		        
		        
		        // Get scheduling varaible to show the day that the user clicks on then use that variable to
		    	// search the data base for dates that match

		    	Font schedule = new Font("Arial", Font.BOLD, 30);
		    	scheduling.setFont(schedule);
		    	scheduling.setForeground(Color.black);
		    	scheduling.setBounds(350,50,400,80);
		    	scheduling.setEditable(false);	scheduling.setBorder(BorderFactory.createLineBorder(color));
		    	
		    	
		    	// settings for panel that opens when the user clicks on a day to view events
				options.setSize(1000, 1000);
				options.setLayout(null);
				options.setVisible(true);
				options.add(scheduling);
				
				options.add(title, BorderLayout.CENTER);
				options.add(option, BorderLayout.CENTER);
				options.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
        }
  
	
			}
					// if the password is invalid, the jtextfield for the username will be set to invalid
			else {
				userset.setText("invalid");
				passwordset.setText("invalid");
			}
			
			


			}
				//catching an exception
		catch(Exception n){/*userset.setText("invalid");*/
			System.out.println("error");}
		
		
		
	}
});
		
		
		// adding an action listener to the create account button
		create.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent account){
				//setting the new frame
			 classproject frame = new classproject();
			 frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			 
			 //adding different texfields indicating what we want the user to enter
		        JTextField username=new JTextField("Enter  new username:");
		        JTextField password=new JTextField("Enter new password:");
		        JTextField name=new JTextField("Enter your name: ");
		        JTextField lastname=new JTextField("Enter your last name: ");
		        JTextField email=new JTextField("Enter your email: ");

		        JTextField title=new JTextField("Create your account");
		        //giving the title a font
		        title.setFont(new Font("Arial", Font.PLAIN, 120));
		        //creating textfields to enter info onto
		        JTextField passwordset=new JTextField("");
		        JTextField userset=new JTextField("");
		        JTextField name2=new JTextField("");
		        JTextField lastname2=new JTextField("");
		        JTextField email2=new JTextField("");
		        
		        username.setFont(new Font("Arial", Font.PLAIN, 50));
		        password.setFont(new Font("Arial", Font.PLAIN, 50));
		        name.setFont(new Font("Arial", Font.PLAIN, 50));
		        lastname.setFont(new Font("Arial", Font.PLAIN, 50));
		        email.setFont(new Font("Arial", Font.PLAIN, 50));
		        
		        userset.setFont(new Font("Arial", Font.PLAIN, 50));
		        passwordset.setFont(new Font("Arial", Font.PLAIN, 50));
		        name2.setFont(new Font("Arial", Font.PLAIN, 50));
		        lastname2.setFont(new Font("Arial", Font.PLAIN, 50));
		        email2.setFont(new Font("Arial", Font.PLAIN, 50));
		        
		        //coloring the borders of the textfields to make them visible
		        passwordset.setBorder(new LineBorder(Color.black));
		        userset.setBorder(new LineBorder(Color.black));
		       username.setBorder(new LineBorder(Color.black));
		       name2.setBorder(new LineBorder(Color.black));
		       password.setBorder(new LineBorder(Color.black));
		       name.setBorder(new LineBorder(Color.black));
		       lastname.setBorder(new LineBorder(Color.black));
		       lastname2.setBorder(new LineBorder(Color.black));
		       email.setBorder(new LineBorder(Color.black));
		       email2.setBorder(new LineBorder(Color.black));
		       
		       //setting the location of all the textfields
		       email.setBounds(200, 700, 700, 60);
		       email2.setBounds(1000, 700, 700, 60);
		       
		       password.setBounds(200, 600, 700, 60);
		       passwordset.setBounds(1000, 600, 700, 60);
		       
		       userset.setBounds(1000, 500, 700, 60);
		       username.setBounds(200, 500, 700, 60);
		       
		       name2.setBounds(1000, 300, 700, 60);
		       name.setBounds(200, 300, 700, 60);
		    	
		    	lastname.setBounds(200, 400, 700, 60);
		    	lastname2.setBounds(1000, 400, 700, 60);
		    	
		    	
		    	title.setBounds(200, 100, 1300, 150);
		    	
		    	
		    	Creating.setBounds(750,800,900,60);
		    	
		    	
		    	//making the textfields that we do not want the user to edit impossible for them to do so
		    	title.setEditable(false);
		    	username.setEditable(false);
		    	password.setEditable(false);
		    	name.setEditable(false);
		    	lastname.setEditable(false);
		    	email.setEditable(false);
		    	
		    	//adding the different items we want on the frame
		    	frame.add(password);
		    	frame.add(username);
		    	frame.add(title);
		    	frame.add(passwordset);
		    	frame.add(userset);
		    	frame.add(name);
		    	frame.add(name2);
		    	frame.add(lastname);
		    	frame.add(lastname2);
		    	frame.add(email);
		    	frame.add(email2);
		    
				
				frame.setLayout(null);
				frame.setVisible(true);
				
				
				

				//adding the button representing the create account
				frame.add(Creating);
				// disposing the JFrame when the user hits the close button
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
				Creating.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent createuser){
						try {
							
							ProcessBuilder processbuilder = new ProcessBuilder("python","c:\\\\Calendar\\\\Hash.py",""+passwordset.getText());
							Process process = processbuilder.start();

							BufferedReader buffer = new BufferedReader(new InputStreamReader(process.getInputStream()));
							
							String result = buffer.readLine();
							String result2=buffer.readLine();
									
							System.out.println(line);
							System.out.println(line2);
						
						
							
						CreateUser creating=new CreateUser(name2.getText(), lastname2.getText(), userset.getText(),email2.getText(),result2,result);
						if(creating.checkDuplicate()==0) {
							System.out.println("username is used");
						}
						else {
						
							frame.dispose();
							
						
						}
						}
						catch(Exception Exception) {
							System.out.println("error");
						}
			}
					});
			
			
		}
		
		
	    });
	}

}
