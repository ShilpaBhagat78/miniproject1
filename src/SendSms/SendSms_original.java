package SendSms;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SendSms_original {
	

	/*Install the Java helper library from twilio.com/docs/libraries/java*/
	    // Find your Account Sid and Auth Token at twilio.com/console
	    public static final String ACCOUNT_SID =
	            "xxxxxxxxxxxxxxxxxxxxxxxx";
	    public static final String AUTH_TOKEN =
	    		"xxxxxxxxxxxxxxxxxxxxxxxxx";

	    public static void main(String[] args) {
	        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

	        Message message = Message
	                .creator(new PhoneNumber("+91xxxxxxx"), // to
	                        new PhoneNumber("+xxxxxxx"), // from
	                        "Where's Wallace?")
	                .create();

	        System.out.println(message.getSid());
	    }
	}



