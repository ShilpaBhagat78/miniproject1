package SendSms;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SendSms1 {
	
	public static final String ACCOUNT_SID = "xxxxxxxxxxxx";            
    public static final String AUTH_TOKEN = "xxxxxxxxxx";    		
    
  
	public SendSms1()
    {
    	Twilio.init(ACCOUNT_SID, AUTH_TOKEN);    	
    }         
    
}


