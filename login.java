package Calendar;

//importing necessary items to create the gui

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.sql.*;

public class login {
	public static void main(String[] args) {
		//creating the buttons that we will use throughout the guis
		JButton create = new JButton("Don't have an account?");	
		JButton login = new JButton("log in");	
		JButton Creating= new JButton("Create your account");
    //creating an array list and adding the buttons to it
    	ArrayList<JButton> buttons= new ArrayList<JButton>();
    
    	buttons.add(create);
    	buttons.add(login);
    	buttons.add(Creating);
    	// giving the buttons the same values through a variety of items
    	for(int i=0;i<buttons.size();i++) {
    		//so the user is not able to edit the buttons
    		buttons.get(i).setFocusable(false);
    		//the font of the buttons
    		buttons.get(i).setFont(new Font("Arial", Font.PLAIN, 40));
    		//alignment
    		buttons.get(i).setHorizontalAlignment(SwingConstants.RIGHT);
    		buttons.get(i).setVerticalAlignment(SwingConstants.BOTTOM);
    		//creating a border
    		buttons.get(i).setBorder(new LineBorder(Color.black));
    		//creating a light gray background color 
    		buttons.get(i).setBackground(Color.LIGHT_GRAY);
    	}
    	//setting the location of the buttons
    	create.setBounds(375,500,420,70);	login.setBounds(750,400,108,60);
    	//creating the frame
        classproject frame = new classproject();
        //creating the textfields giving the user a variety of instructions
        JTextField username=new JTextField("Enter username:");
        JTextField password=new JTextField("Enter password:");
        JTextField title=new JTextField("Enter your account");
        //creating TextFields where the user will be able to enter their input
        JTextField passwordset=new JTextField("");
        JTextField userset=new JTextField("");
        //creating borders for the textfields
        passwordset.setBorder(new LineBorder(Color.black));
        userset.setBorder(new LineBorder(Color.black));
       username.setBorder(new LineBorder(Color.black));
       password.setBorder(new LineBorder(Color.black));
       //setting the location of the textfields
    	username.setBounds(200, 300, 108, 60);
    	userset.setBounds(400, 300, 300, 60);
    	password.setBounds(200, 400, 108, 60);
    	passwordset.setBounds(400, 400, 300, 60);
    	title.setBounds(200, 100, 350, 102);
    	
    	//making sure the user is not able to edit the textfields giving him information
    	
    	title.setEditable(false);
    	username.setEditable(false);
    	password.setEditable(false);
    	title.setFont(new Font("Arial", Font.PLAIN, 40));
    	//addin the different items into the frame
    	frame.add(password);
    	frame.add(username);
    	frame.add(title);
    	frame.add(passwordset);
    	frame.add(userset);
    
		//set the frame size
		frame.setSize(1050, 700);
		frame.setLayout(null);
		frame.setVisible(true);
		
		
		

		//adding each button representing each day
		frame.add(create);
		frame.add(login);

		// disposing the JFrame when the user hits the close button
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// creating an action listener for the log in button
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
				//using try statement to catch exceptions
				try {
					//creating two empty strings that will contain the hash and salt
					String h_Pass = "";
                    String salt ="";
                    //using a command to get the hash and salt
                    GetSaltAndHash salt_And_Hash = new GetSaltAndHash(userset.getText());
                    ResultSet pulledSaltAndHash = salt_And_Hash.RetriveSAndH();
                    //giving the empty strings the values of Hash and Salt respectively
                    while (pulledSaltAndHash.next()) {
                        h_Pass=pulledSaltAndHash.getString("H_PASS");
                        salt=pulledSaltAndHash.getString("SALT");
                    }
					//using a process builder to open the python file specified and run it
                    ProcessBuilder processbuilder = new ProcessBuilder("python","c:\\\\Calendar\\\\Validate.py",""+h_Pass,""+salt,""+passwordset.getText());
					//telling the process builder to start running it
					Process process = processbuilder.start();
					//using  a buffered reader to read the output of the processbuilder
					BufferedReader buffer = new BufferedReader(new InputStreamReader(process.getInputStream()));
					// giving a string the value of the output of the python file so its easy to compare
					String check = buffer.readLine();
					//printing out the result
					System.out.println(check);
					//if the result is valid the program will open a new frame with the calendar of the user, to make it easy to see
					//this program will only change the user input into valid
					if(check.equals("Valid")) {
						userset.setText("valid");
					}
					//else the program will change the user input to invalid
					else {
						userset.setText("invalid");
					}
				}
					
					catch( Exception Exception) {
						
					}
				}
			});
		//adding an action listener to the create button
		create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Creat){ 
				//creating a new frame
			 classproject frame = new classproject();
			 //creating  a variety of instructions contained in JTextFields
		        JTextField username=new JTextField("Enter  new username:");
		        JTextField password=new JTextField("Enter new password:");
		        JTextField name=new JTextField("Enter your name: ");
		        JTextField lastname=new JTextField("Enter your last name: ");
		        JTextField email=new JTextField("Enter your email: ");
		        
		       
		        
		        JTextField title=new JTextField("Create your account");
		        //Creating TextFields where the user can enter input
		        JTextField passwordset=new JTextField("");
		        JTextField userset=new JTextField("");
		        JTextField name2=new JTextField("");
		        JTextField lastname2=new JTextField("");
		        JTextField email2=new JTextField("");
		        
		        //creating borders to make the TextFields visible
		        
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
		       
		       //setting the location of the TextFields
		       email.setBounds(200, 700, 150, 60);
		       email2.setBounds(400, 700, 300, 60);
		       
		       password.setBounds(200, 600, 150, 60);
		       passwordset.setBounds(400, 600, 300, 60);
		       
		       userset.setBounds(400, 500, 300, 60);
		       username.setBounds(200, 500, 150, 60);
		       
		       name2.setBounds(400, 300, 300, 60);
		       name.setBounds(200, 300, 150, 60);
		    	
		    	lastname.setBounds(200, 400, 150, 60);
		    	lastname2.setBounds(400, 400, 300, 60);
		    	
		    	
		    	title.setBounds(200, 100, 400, 102);
		    	
		    	
		    	Creating.setBounds(750,400,370,60);
		    	
		    	//making it so the user cannot edit the TextFields giving them instructions
		    	
		    	title.setEditable(false);
		    	username.setEditable(false);
		    	password.setEditable(false);
		    	name.setEditable(false);
		    	lastname.setEditable(false);
		    	email.setEditable(false);
		    	//Setting a font for the title
		    	title.setFont(new Font("Arial", Font.PLAIN, 40));
		    	//adding all the different components
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
		    
				//set the frame size
				frame.setSize(1500, 1000);
				frame.setLayout(null);
				frame.setVisible(true);
				
				
				

				//adding a creating button to create an account
				frame.add(Creating);

				// disposing the JFrame when the user hits the close button
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				//adding ann Action Listener to the button Creating
				Creating.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e){
						try {
							//opening and running the Hash python folder using the password the user enter in order to get a Hash and Salt
							ProcessBuilder processbuilder = new ProcessBuilder("python","c:\\\\Calendar\\\\Hash.py",""+passwordset.getText());
							Process process = processbuilder.start();
							//Reading the output
							BufferedReader buffer = new BufferedReader(new InputStreamReader(process.getInputStream()));
							//Giving strings the value of the outputs so they are easier to run
							String result = buffer.readLine();
							String result2=buffer.readLine();
							
						
						
							//Using the create user method to store the user
						
						CreateUser creating=new CreateUser(name2.getText(), lastname2.getText(), userset.getText(),email2.getText(),result2,result);
						//Checking if the username has been entered, if it has a message will come up
						if(creating.checkDuplicate()==0) {
							System.out.println("username is used");
						}
						else {
						
							
							
						
						}
						}
						//catching exceptions
						catch(Exception Exception) {
							
						}
			}
			
		});
			
			
	}
		});		
}
}
