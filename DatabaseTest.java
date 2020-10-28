package sql_database;
//default with java, program will not run without jar file jdbc
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
//This class contains multiple user inputs for creating users, creating events for users, and retriving events all from the sql server database
//it contains basic error contorl as of now for catching sql errors
public class DatabaseTest {


	public static void main(String[] args) throws ClassNotFoundException {
		//main try 
		try {
			//boolean for while loop 
			boolean keepgoing = true;
			//main input for all user entered values
			Scanner input = new Scanner(System.in);
			while(keepgoing = true) {
				//presented option to user for choices
				System.out.println("Enter 1 to create new user. Enter 2 to create a new event for user. Enter 3 to retrive events for user: ");
				//x is used for if blocks
				String x=input.nextLine();
				//this if block handles the 
				if(x.equals("2")) {

				    System.out.println("Enter name of event:");
					String eventName=input.nextLine();	
					
					System.out.println("Enter description:");
					String reason=input.nextLine();		
					//sql server handles conversion of string to date
					System.out.println("Enter start date and time formated as MM/DD/YY HR:MIN:SEC PM/AM:");
					String startTimeDate=input.nextLine();
					//sql server handles conversion of string to date
					System.out.println("Enter end date and time formated as MM/DD/YY HR:MIN:SEC PM/AM:");
					String endTimeDate=input.nextLine();
					//color name is for future color of event bracket on calander
					System.out.println("Color Name:");
					String color=input.nextLine();
					
					//passes all inputs above to create an instance event, however one varible that is static in this code is the username which is first varible
					//evntrually this username will be pretermined by the user logining in through the GUI
					
					CreateEvent event = new CreateEvent("kj7935", eventName, reason, startTimeDate, endTimeDate, color);
					
					//calls the create event method that passes all information to sql procedure create event
					event.createNewEvent();
	
					

					//this if block handles creating a new user
				}else if(x.equals("1")) {	
					System.out.println("Enter first Name:");
					String fname=input.next();
					System.out.println("Enter last Name:");
					String lname=input.next();
					System.out.println("Enter user name:");	
					String userName=input.next();
					System.out.println("Enter password Name:");
					String passWord=input.next();
					
					//passes all inputs above to create an object of new user
					CreateUser newuser = new CreateUser(fname,lname,userName,passWord);		
					//calls the method that passes all information to the sql procdure to create an event
					newuser.creatNewUser();
					//this if block handles retriving events based on what user is logged in, in this case its predetermined because the login ui dosent exist yet
				}else if(x.equals("3")) {
					RetriveEvent user = new RetriveEvent("kj7935");
					user.retriveEventForUser();
				
					//breaks while loop
				}else {
					keepgoing = false;
				}
			}
			//catches sql exception, needs to be exapnded upon
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
//this class deals with creating a new user that will have a f name l name user name and a hashed password that will be handled by python hashing script
class CreateUser{
	//4 instaance varibles for various passed varibles
	private String fName;
	private String lName;
	private String userName;
	private String passWord;
	//this are final since they SHOULD not change, static so they do not have to be instiatied, and private since only this class should acess it.
	static private final String url= "jdbc:sqlserver://database-1.cczlwkbidopl.us-east-2.rds.amazonaws.com:1433;databaseName=CALANDER";
	static private final String user= "CISGroup2";
	static private final String pass="Calander122" ;
	//constucotr/setter method
	public CreateUser(String fName, String lName, String userName, String passWord) {
		this.fName=fName;
		this.lName=lName;
		this.userName=userName;
		this.passWord=passWord;	
	}
	//begninng of getblock
	private String getfName() {
		return this.fName;
	}
	private String getlName() {
		return this.lName;
	}
	private String getUserName() {
		return this.userName;
	}
	private String getPassWord() {
		return this.passWord;
	}
	
	
/*
 REDUDANT CODE, KEEPING FOR NOW INCASE OF NEED TO REFRENCE
 
	public void setUserInDB() throws ClassNotFoundException, SQLException {
		String url = "jdbc:sqlserver://database-1.cczlwkbidopl.us-east-2.rds.amazonaws.com:1433;databaseName=CALANDER";
		String user ="CISGroup2";
		String pass="Calander122";
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection testconnection  = DriverManager.getConnection(url,user,pass);
		System.out.println("connection Created");
		String dataEntry="INSERT INTO PERSON (LNAME,FNAME,LOGIN_NAME,H_PASS) VALUES(?,?,?,?)";

		PreparedStatement statement = testconnection.prepareStatement(dataEntry);
		System.out.println(testconnection.getCatalog());

		statement.setString(1, getlName());
		statement.setString(2, getfName());
		statement.setString(3, getUserName());
		statement.setString(4, getPassWord());
		int rowsInserted = statement.executeUpdate();
		if (rowsInserted > 0) {
		    System.out.println("A new user was inserted successfully!");
		}
		testconnection.close();
		
		
	}
*/	
	
	//main method for creating new users to the sql data base
	public void creatNewUser()throws ClassNotFoundException, SQLException {
		//this is the jdbc driver that was refrenced at the top of this file for required. This prvoides the bridge between the microsft sql server and the JVM. THis is essentially
		//refrencing a jarfile 
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");	
		//uses class conection from sql import and driver manager from jdbc for esstablsihing connection to the sql server
		Connection testconnection  = DriverManager.getConnection(url,user,pass);
		
		System.out.println("connection Created");
		//called statment is used for grabing what essentially is methods in the sql server, however they are called procedures the question marks represent varibles to be inserted 
		CallableStatement calledstatement = testconnection.prepareCall("{call INSERT_PERSON(?, ?, ?, ?)}");
		//the @symbols are names of varibles in the sql server and the called statements are setting the strings varibles to the varius get methods
		calledstatement.setString("@FNAME", getfName());
		calledstatement.setString("@LNAME", getlName());
		calledstatement.setString("@LOGIN_NAME", getUserName());
		calledstatement.setString("@H_PASS", getPassWord());
		//executres the called statements
		calledstatement.execute();
		System.out.println("A new user was inserted successfully!");

		//close connection to sql server, bandwith dosent grow on trees!
		testconnection.close();	
	}

	
}
//this class is used for creating an event to the sql server. This one is more complex then creating a person as it makes multiple conenctions to the sql server for data refeence
//it contains mutliple get methods and one constructor /set block
class CreateEvent {
	//instance varibles
	private int UserDB;
	private String eventName;
	private String reason;
	private String startDate;
	private String endDate;
	private String color;
	private String userName;
	//this are final since they SHOULD not change, static so they do not have to be instiatied, and private since only this class should acess it.
	static private final String url= "jdbc:sqlserver://database-1.cczlwkbidopl.us-east-2.rds.amazonaws.com:1433;databaseName=CALANDER";
	static private final String user= "CISGroup2";
	static private final String pass="Calander122" ;
	//setter/constuctor block
	public CreateEvent(String userName, String eventName, String reason, String startTimeDate, String endTimeDate, String color ) {
		this.eventName=eventName;
		this.userName=userName;
		this.reason=reason;
		this.endDate=endTimeDate;
		this.startDate=startTimeDate;
		this.color=color;
		
	//beginning of get block	
	}
	private String getEventName() {
		return eventName;
	}
	private String getReason() {
		return reason;
	}
	private String getUserName() {
		return userName;
	}
	private String getStartDate () {
		return startDate;
		
	}
	private String getEndDate() {
		return endDate;
	}
	private String getColor() {
		return color;
	}
	//this getmethod should get special attetnion as its preforming a vital function
	//This method is getting the user id that will be used for storing event data to correct location based on the userName of the account
	public int getUserDB() throws ClassNotFoundException, SQLException {
		//this is the jdbc driver that was refrenced at the top of this file for required. This prvoides the bridge between the microsft sql server and the JVM. THis is essentially
		//refrencing a jarfile 
		
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		//uses class conection from sql import and driver manager from jdbc for esstablsihing connection to the sql server
		Connection testconnection  = DriverManager.getConnection(url,user,pass);
		
		//called statment is used for grabing what essentially is methods in the sql server, however they are called procedures the question marks represent varibles to be inserted 
		CallableStatement calledstatement = testconnection.prepareCall("{CALL GET_PERSON_ID(?)}");
		System.out.println("connection Created");
		//passes user name to the sql server to find matching user id
		calledstatement.setString("@LOGIN_NAME", userName);
		//executes call stat
		calledstatement.execute();
		//exectues the rest of call get person id procedure
		//result set can contain an array of essentially data objects
		ResultSet result = calledstatement.executeQuery();
		while(result.next()) {
			//gets user id from sql database
			this.UserDB=result.getInt("PERSONID");
			System.out.println("user id gathered sucesfully");
		}
		//close connection
		testconnection.close();	
		//retrun id number
		return UserDB;
	}
	public void createNewEvent() throws SQLException, ClassNotFoundException {
		//this is the jdbc driver that was refrenced at the top of this file for required. This prvoides the bridge between the microsft sql server and the JVM. THis is essentially
				//refrencing a jarfile 
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		//uses class conection from sql import and driver manager from jdbc for esstablsihing connection to the sql server
		Connection testconnection  = DriverManager.getConnection(url,user,pass);
		
		System.out.println("connection Created");
		
		//called statment is used for grabing what essentially is methods in the sql server, however they are called procedures the question marks represent varibles to be inserted 
		CallableStatement calledstatement = testconnection.prepareCall("{call INSERT_APPT(?, ?, ?, ?, ?, ?)}");
		
		//first call statements calls the getMethod for getting user id based on userName
		calledstatement.setInt("@PERSON_ID", getUserDB());
		//rest of call statement block
		calledstatement.setString("@EVENTNAME", getEventName());
		calledstatement.setString("@REASON", getReason());
		calledstatement.setString("@START_TIME", getStartDate());
		calledstatement.setString("@END_TIME", getEndDate());
		calledstatement.setString("@COLOR", getColor());
		calledstatement.execute();
		System.out.println("A new event was inserted successfully!");
		//close connection
		testconnection.close();	
		
		
		
	}
}
//This class retrives events based on the username that corresponds to primary key for person and primary key for appt
class RetriveEvent{
	//instance varibles, two in this case user name and user id
	private String userName;
	private int userID;
	//this are final since they SHOULD not change, static so they do not have to be instiatied, and private since only this class should acess it.
	static private final String url= "jdbc:sqlserver://database-1.cczlwkbidopl.us-east-2.rds.amazonaws.com:1433;databaseName=CALANDER";
	static private final String user= "CISGroup2";
	static private final String pass="Calander122" ;
	//setter / constuctor for retrive class
	public RetriveEvent(String userName) {
		this.userName=userName;
	}
	//this getmethod should get special attetnion as its preforming a vital function
		//This method is getting the user id that will be used for storing event data to correct location based on the userName of the account
	//rest of destription of method can be found in identical method in class CreateEvent
	private int getUserDB() throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection testconnection  = DriverManager.getConnection(url,user,pass);
		CallableStatement calledstatement = testconnection.prepareCall("{CALL GET_PERSON_ID(?)}");
		System.out.println("connection Created");
		calledstatement.setString("@LOGIN_NAME", userName);
		calledstatement.execute();
		ResultSet result = calledstatement.executeQuery();
		while(result.next()) {
			this.userID=result.getInt("PERSONID");
			System.out.println("user id gathered sucseffuly");
		}
		testconnection.close();	
		return userID;
	
	}
	//main method for retriving event from sql server
	public void retriveEventForUser() throws ClassNotFoundException, SQLException {
		//this is the jdbc driver that was refrenced at the top of this file for required. This prvoides the bridge between the microsft sql server and the JVM. THis is essentially
		//refrencing a jarfile 
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		//uses class conection from sql import and driver manager from jdbc for esstablsihing connection to the sql server
		Connection testconnection  = DriverManager.getConnection(url,user,pass);
		//call statment for reading procedure read_appts
		CallableStatement calledstatement = testconnection.prepareCall("{CALL READ_APPTS (?)}");
		
		System.out.println("connection Created");
		//passes instance varible getuserdb method for retriving and passing id to retrive calander events/appointments for
		calledstatement.setInt("@PERSONID", getUserDB());
		calledstatement.execute();
		//exectute the rest of Read appts procedure and outputs results to result as essentaillay an array of calander events
		ResultSet result = calledstatement.executeQuery();
		
		//formats out for correcting spacing as a proof of concept for print
		System.out.printf("%-20s%-35s%-25s%-25s%s%n","Event Name","Event Notes/Discription","Start Time", "End Time", "Color");
		while(result.next()) {
			//formats out for correcting spacing as a proof of concept for print
			System.out.printf("%-20s%-35s%-25s%-25s%s%n",result.getString("EVENTNAME"),result.getString("REASON"),result.getString("START_TIME"),result.getString("END_TIME"),result.getString("COLOR"));
			//System.out.printf("%-20s%n%-20s","Event Name",result.getString("EVENTNAME"));

			

		
		}
		System.out.println("user events gathered sucesffuly");
		
	}
	
}
