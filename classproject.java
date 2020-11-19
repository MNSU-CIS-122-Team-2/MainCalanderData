//importing all the necessary items to create the calendar
import java.awt.BasicStroke;
import java.sql.*;
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
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.text.SimpleDateFormat;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;
import java.util.*;
import java.util.Date;

import javax.swing.DefaultListCellRenderer;


public class classproject extends JFrame {
	//creating an array with the name of the months
	static String[] months= {"January","February","March","April","May","June","July","August","September","October","November","December"};
	static JTextField textfield1;
	static JTextField textfield2;
	static JTextField textfield3;
	

	public static void main(String[] args) {
		//creating a new gregorian calendar to help set the current day and year
		GregorianCalendar e=new GregorianCalendar();
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
    	
    	// Implementing an '+' icon to add events to the calendar
    	addEvent.setFocusable(false);
    	addEvent.setFont(new Font("Arial", Font.PLAIN, 60));
    	addEvent.setHorizontalAlignment(SwingConstants.CENTER);
    	addEvent.setVerticalAlignment(SwingConstants.CENTER);
    	addEvent.setBorder(new LineBorder(Color.black));
    	addEvent.setForeground(Color.BLACK);
    	addEvent.setBounds(1010,120, 100, 100);
    	
    	
    	
    	
    	
    	//changing the color of the current day so it is visible
    	one.get(e.getTime().getDate()-1).setBackground(Color.cyan);
    	//locating each button into the Jframe
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

  //Calendar JFrame 	
    	//creating a new frame
        classproject frame = new classproject();
        //creating 2 panels, one for the month and year and the other one for the days of the month
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        Color color = panel1.getBackground();
        //creating a jtextfield that includes the current month and the current year
    	JTextField month = new JTextField(months[Calendar.getInstance().get(Calendar.MONTH)]+" "+(e.getTime().getYear()+1900));
    	//creating a font for the month and year
    	Font Month = new Font("Arial", Font.BOLD, 48);
    	//setting the font
    	month.setFont(Month);
    	//giving the foreground color
    	month.setForeground(Color.BLACK);
    	//setting the bounds
    	month.setBounds(565,30,500,100);
    	//not able to edit
    	month.setEditable(false);	month.setBorder(BorderFactory.createLineBorder(color));
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
		frame.setSize(1200, 1000);
		frame.setLayout(null);
		frame.setVisible(true);
		//adding the month and year
		frame.add(month);
		frame.add(addEvent);
		

		
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
		    
		    
    		public void actionPerformed(ActionEvent e)
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
    		    Font addEvent = new Font("Courier", Font.BOLD, 28);
    		    addingEvents.setFont(addEvent);
    		    addingEvents.setEditable(false);
    		    addingEvents.setBorder(BorderFactory.createLineBorder(color));
    		    
    		    eventName.setHorizontalAlignment(JTextField.CENTER);
    		    Font EventNameFont = new Font("Arial", Font.ITALIC, 18);
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
    		    addingEvents.setBounds(10, 0,350,80);
    		    eventName.setBounds(120, 80, 200, 45);
    		    reason.setBounds(10, 255, 400, 120);
    		    EventStartDate.setBounds(70, 150, 90, 30);
    		    EventEndDate.setBounds(105, 190, 90, 30);
    		    eventStart.setBounds(180, 150, 80, 30);
    		    eventEnd.setBounds(225, 190, 80, 30);
    		    eventColor.setBounds(335, 150, 80, 30);
    		    startTimeLabel.setBounds(20,150, 50,30);
    		    endTimeLabel.setBounds(60, 190, 50, 30);
    		    eventColorLabel.setBounds(265,150,80,30);
    		    submit.setBounds(335,420,80,30);
    		    
    		    
    		    // Size of the JPane
    		    addElement.setSize(465,550);
    		    addElement.setLayout(null);
    		    addElement.setVisible(true);
    		    
    		    
    		    // Adding all elements to the JPane for visibility.
    		    addElement.add(addingEvents);
    		    addElement.add(eventName);
    		    addElement.add(reason);
    		    addElement.add(EventStartDate);
    		    addElement.add(EventEndDate);
    		    addElement.add(eventStart);
    		    addElement.add(eventEnd);
    		    addElement.add(eventColor);
    		    addElement.add(startTimeLabel);
    		    addElement.add(endTimeLabel);
    		    addElement.add(eventColorLabel);
    		    addElement.add(submit);
    		
    		    // When the 'ADD' button is clicked the event is created and saved to the database
    		    // then closes the JPane so user can either add another event or view event by clicking
    		    // on each day
    		    submit.addActionListener(new ActionListener() {
    		        public void actionPerformed(ActionEvent e)
    		        {
    		        	String stringEvent = eventName.getText();
    		        	String startTimeDate = (EventStartDate.getSelectedItem().toString() + " "+ eventStart.getSelectedItem().toString());
    		        	String endTimeDate = (EventEndDate.getSelectedItem().toString() + " " + eventEnd.getSelectedItem().toString());
    		        	String stringNotes = reason.getText();
    		        	String selectedColor = eventColor.getSelectedItem().toString();
    		            addElement.dispose();
    		            
    		            
    		            // Creates new event and saves to the database
    		            CreateEvent event = new CreateEvent("kj7935", stringEvent, stringNotes, startTimeDate, endTimeDate, selectedColor);
    		            
    		            // Error control
    		            try {
							event.createNewEvent();
						} catch (ClassNotFoundException | SQLException e1) {
							System.out.println(e1);
    		        }}
    		    });
    		    
    		}
    	});
		
		
RetriveEvent pullevent = new RetriveEvent("kj7935");

        
        try {
        	ResultSet listofitems=pullevent.retriveEventForUser();
        System.out.printf("%-25s%s%n","Start Time", "End Time");
        while(listofitems.next()) {        
        //formats out for correcting spacing as a proof of concept for print
        System.out.printf("%-25s%s%n",listofitems.getString("START_TIME"),listofitems.getString("END_TIME"));
        }
        
    } catch (ClassNotFoundException | SQLException e2) {
        // TODO Auto-generated catch block
        e2.printStackTrace();
    }
        
        
		
		
		
		
		
		
		


		//adding each button representing each day
		for(int i=0;i<one.size();i++) {
			frame.add(one.get(i));}

		
		//adding the panels
		frame.add(panel1, BorderLayout.CENTER);
		frame.add(panel2, BorderLayout.CENTER);
		// disposing the JFrame when the user hits the close button
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		//SchedulingJFRAME	
		//adding 4 new buttons
		JTextField viewEvents;
		
		viewEvents = new JTextField("FUNCTION TO PRINT TODAYS DATE"
				+ "IN FORMAT 'WEDNESDAY NOVEMBER 18, 2020'");
		
		
		/*		
		
		JButton appointment = new JButton("Add an appointment");
		JButton event = new JButton("Add an event");
		JButton cappointments= new JButton("See your appointments");
		JButton cevents= new JButton("See your events");
		//creating a new array list and adding all the buttons
		ArrayList<JButton> two= new ArrayList<JButton>();
		two.add(appointment);
		two.add(event);
		two.add(cappointments);
		two.add(cevents);
		
		

		for(int i=0;i<two.size();i++) 
		{
    		//setting the focusable to false so they cannot gain focus
    		two.get(i).setFocusable(false);
    		//giving the information in each button a font
    		two.get(i).setFont(new Font("Arial", Font.PLAIN, 40));
    		//align the info of the button to the right and to the button
    		two.get(i).setHorizontalAlignment(SwingConstants.RIGHT);
    		two.get(i).setVerticalAlignment(SwingConstants.BOTTOM);
    		//creating a border of color black
    		two.get(i).setBorder(new LineBorder(Color.black));
    	}
		//giving the buttons a different color
		event.setBackground(Color.blue);
		appointment.setBackground(Color.red);
		//setting the bounds and location for each button
		appointment.setBounds(200,400,375,50);	
		event.setBounds(200, 250, 250, 50);
		cappointments.setBounds(200,700,425,50);	
		cevents.setBounds(200, 550, 300, 50);
*/
		
		
    	
    		
    	
		
		
		
		
		
		for(int i=0;i<one.size();i++) {
			//creating a new Jframe that will open up when the user clicks any of the day buttons, that includes the 4 new buttons
		one.get(i).addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e){ 

		  classproject options = new classproject();
	        //JPanel title = new JPanel();
	        JPanel option = new JPanel();
	        Color color = panel1.getBackground();
	    	JTextField scheduling = new JTextField("FUNCTION TO PRINT TODAYS DATE IN FORMAT 'WEDNESDAY NOVEMBER 18, 2020'");
	    	Font schedule = new Font("Arial", Font.BOLD, 12);
	    	scheduling.setFont(schedule);
	    	scheduling.setForeground(Color.black);
	    	scheduling.setBounds(400,100,300,100);
	    	scheduling.setEditable(false);	
	    	scheduling.setBorder(BorderFactory.createLineBorder(color));
			options.setSize(1000, 1000);
			options.setLayout(null);
			options.setVisible(true);
			options.add(scheduling);
			
			
			//for(int i=0;i<two.size();i++) {
				//options.add(two.get(i));}
			//options.add(title, BorderLayout.CENTER);
			//options.add(option, BorderLayout.CENTER);
			
			
			options.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			
			
			
			}
			
		});
		}
		//AppointmentJFrame
		//creating a new JFrame that will come up when the user clicks the appointment button
		
		/*
			appointment.addActionListener(new ActionListener(){
				 public void actionPerformed(ActionEvent e){  
				    	classproject appointment = new classproject();
				        JPanel title = new JPanel();
				        JPanel option = new JPanel();
				        Color color = panel1.getBackground();
				    	JTextField appo = new JTextField("Add appointment");
				    	Font app = new Font("Arial", Font.BOLD, 48);
				    	appo.setFont(app);
				    	appo.setForeground(Color.black);
				    	appo.setBounds(100,30,400,80);
				    	appo.setEditable(false);	
				    	appointment.setSize(600, 500);
				    	appointment.setLayout(null);
				    	appointment.setVisible(true);
				    	appointment.add(appo);
				    	appointment.add(title, BorderLayout.CENTER);
				    	appointment.add(option, BorderLayout.CENTER);
				    	
				    	
				    	
				    	

						frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 	 
				    }
			});
			//EVENTJFRAME
			//creating a new JFrame that will come up when the user clicks the event button
    event.addActionListener(new ActionListener(){ 	
    public void actionPerformed(ActionEvent e){  
    	classproject newevent = new classproject();
        JPanel title = new JPanel();
        JPanel option = new JPanel();
        Color color = panel1.getBackground();
    	JTextField events= new JTextField("Add event");
    	Font event = new Font("Arial", Font.BOLD, 48);
    	events.setFont(event);
    	events.setForeground(Color.black);
    	events.setEditable(false);	
		events.setBounds(100,30,400,80);
		newevent.setSize(600, 500);
		newevent.setLayout(null);
		newevent.setVisible(true);
		newevent.add(events);
		newevent.add(title, BorderLayout.CENTER);
		newevent.add(option, BorderLayout.CENTER);
		newevent.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		




    }


    });*/
		
		
		
		
		
		
		
		
		

	    }


		}
