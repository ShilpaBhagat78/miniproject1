package SendMail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

 public class SendMailSSL1 {
	public  void send( String EMAIL_USERNAME,String Email_PASSWORD, String resultEmailId,String subject,String message){  
        //Get properties object    
        Properties properties = new Properties();    
        properties.put("mail.smtp.host", "smtp.gmail.com");    
        properties.put("mail.smtp.socketFactory.port", "465");    
        properties.put("mail.smtp.socketFactory.class",    
                  "javax.net.ssl.SSLSocketFactory");    
        properties.put("mail.smtp.auth", "true");    
        properties.put("mail.smtp.port", "465");    
        //get Session   
        Session session = Session.getDefaultInstance(properties,    
         new javax.mail.Authenticator() {    
         protected PasswordAuthentication getPasswordAuthentication() {    
         return new PasswordAuthentication(EMAIL_USERNAME,Email_PASSWORD);  
         }    
        }); 
        
        //compose message    
        try {    
         MimeMessage message1 = new MimeMessage(session);    
         message1.addRecipient(Message.RecipientType.TO,new InternetAddress(resultEmailId));    
         message1.setSubject(subject);    
         message1.setText(message);    
         //send message  
         Transport.send(message1);    
         System.out.println("message sent successfully");    
        } catch (MessagingException e) {throw new RuntimeException(e);}    
           
  }


}
