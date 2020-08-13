package RunTheProgram;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;


import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import Connection.MysqlConnection;
import Constants.Constants;
import SendMail.SendMailSSL1;
import SendSms.SendSms1;

public class FailStudents {
	public static void getFailStudents() {
//		Connection connection = null;
		Statement statement = null;
		Statement statement1 = null;
		ResultSet resultset = null;
		ResultSet resultset1 = null;

		int resultId;		
		String resultName;
		String resultMobileNo;
		String resultEmailId;		
		ArrayList<Integer> al1;
		HashMap<String, ArrayList<Integer>> hm = new HashMap<>();

//		try {
			
			try {
			Connection connection = MysqlConnection.getConnection();
			
			 				

				statement = connection.createStatement();
				resultset = statement.executeQuery(
						"select RollNo,name,MobileNo,Email_Id from students"); 
								

				statement1 = connection.createStatement();
				SendMailSSL1 s1 = new SendMailSSL1();
				SendSms1 sm1 = new SendSms1();

				while (resultset.next()) {
					resultId = resultset.getInt(1);
					resultMobileNo = resultset.getString(3);
					resultEmailId = resultset.getString(4);
					String query = "select * from results where RollNo="+resultId;
					resultset1 = statement1.executeQuery(query);
//					System.out.println("resultset1"+resultset1.toString());
					int count=0;
					int resultMark=0;
					while(resultset1.next())
					{
						
						resultMark = resultMark+resultset1.getInt(2);
//						System.out.println("rmark"+rmark);
						count++;						
					}
					
					int average = resultMark/count;
					
					
					if(average<50)
					{
						System.out.println("RollNo: "+resultId);
						System.out.println("Name: "+resultset.getString(2));
						System.out.println("MobileNo: "+resultset.getString(3));
						System.out.println("EmailId: "+resultset.getString(4));
						System.out.println("Average: "+average);
						
						// sending mail to students
						String emailBody = "Hi"+resultset.getString(2)+resultset.getString(4)+"you got less than 50% marks in Test...so study hard.";
						s1.send(Constants.EMAIL_USERNAME , Constants.Email_PASSWORD, resultEmailId, "fail students",
								emailBody);
						
						
						
						try {							
						
						// sending sms to students
						Message message = Message.creator(new PhoneNumber(resultMobileNo), // to
								new PhoneNumber(Constants.TWILIO_REGISTERED_NO), // from
								"you got less than 50% marks so study hard").create();
						}catch(Exception e)
						{
							System.out.println("we got error for message");
							e.printStackTrace();
							
						}
					}
					else
					{
						System.out.println("student is passed");
						
					}
//					 
//				
			}

			} catch (Exception e) {
				
				e.printStackTrace();
			}
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		finally {
			try {
				resultset.close();
				resultset1.close();
				statement.close();
				statement1.close();
//				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}
