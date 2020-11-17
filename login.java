package project;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class login {
	public static void main(String[] args) {
		
		JButton create = new JButton("Don't have an account?");	
		JButton login = new JButton("log in");	
		JButton Creating= new JButton("Create your account");
    
    	ArrayList<JButton> buttons= new ArrayList<JButton>();
    
    	buttons.add(create);
    	buttons.add(login);
    	buttons.add(Creating);
    	
    	for(int i=0;i<buttons.size();i++) {
    		
    		buttons.get(i).setFocusable(false);
    		
    		buttons.get(i).setFont(new Font("Arial", Font.PLAIN, 40));
    		
    		buttons.get(i).setHorizontalAlignment(SwingConstants.RIGHT);
    		buttons.get(i).setVerticalAlignment(SwingConstants.BOTTOM);
    		
    		buttons.get(i).setBorder(new LineBorder(Color.black));
    		//creating a light gray background color for all of the days
    		buttons.get(i).setBackground(Color.LIGHT_GRAY);
    	}
    	create.setBounds(375,500,420,70);	login.setBounds(750,400,108,60);;
        idken frame = new idken();
        JTextField username=new JTextField("Enter username:");
        JTextField password=new JTextField("Enter password:");
        JTextField title=new JTextField("Enter your account");
        JTextField passwordset=new JTextField("");
        JTextField userset=new JTextField("");
        passwordset.setBorder(new LineBorder(Color.black));
        userset.setBorder(new LineBorder(Color.black));
       username.setBorder(new LineBorder(Color.black));
       password.setBorder(new LineBorder(Color.black));
       
    	username.setBounds(200, 300, 108, 60);
    	userset.setBounds(400, 300, 300, 60);
    	password.setBounds(200, 400, 108, 60);
    	passwordset.setBounds(400, 400, 300, 60);
    	title.setBounds(200, 100, 350, 102);
    	
    	
    	
    	title.setEditable(false);
    	username.setEditable(false);
    	password.setEditable(false);
    	title.setFont(new Font("Arial", Font.PLAIN, 40));
    	frame.add(password);
    	frame.add(username);
    	frame.add(title);
    	frame.add(passwordset);
    	frame.add(userset);
    
		//set the frame size
		frame.setSize(1050, 700);
		frame.setLayout(null);
		frame.setVisible(true);
		//adding the month and year
		
		

		//adding each button representing each day
		for(int i=0;i<buttons.size();i++) {
			frame.add(buttons.get(i));}

		// disposing the JFrame when the user hits the close button
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){ 
			 idken frame = new idken();
		        JTextField username=new JTextField("Enter  new username:");
		        JTextField password=new JTextField("Enter new password:");
		        JTextField name=new JTextField("Enter your name: ");
		        JTextField lastname=new JTextField("Enter your last name: ");
		        JTextField email=new JTextField("Enter your email: ");
		        
		       
		        
		        JTextField title=new JTextField("Create your account");
		        JTextField passwordset=new JTextField("");
		        JTextField userset=new JTextField("");
		        JTextField name2=new JTextField("");
		        JTextField lastname2=new JTextField("");
		        JTextField email2=new JTextField("");
		        
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
		    	
		    	
		    	
		    	title.setEditable(false);
		    	username.setEditable(false);
		    	password.setEditable(false);
		    	name.setEditable(false);
		    	lastname.setEditable(false);
		    	email.setEditable(false);
		    	title.setFont(new Font("Arial", Font.PLAIN, 40));
		    	
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
				//adding the month and year
				
				

				//adding each button representing each day
				frame.add(Creating);

				// disposing the JFrame when the user hits the close button
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
			
		});
	}
}


