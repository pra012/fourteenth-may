package emailApplication;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONException;
import org.json.JSONObject;
import javax.mail.*;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.net.*;
public class sendEmail extends TimerTask {
   public static void main(String[] args) throws JSONException, IOException {
	   Timer t = new Timer();
	   t.schedule(new sendEmail(), 0, 9000); //send email every 9 seconds
	   String url = "http://api.weatherapi.com/v1/current.json?key=b1cd077948f44109b7b132357211405&q=London&aqi=no";\\here you need to enter key=your generated key from weatherapi
	   URL obj = new URL(url);
	   HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	   BufferedReader in = new BufferedReader(
	             new InputStreamReader(con.getInputStream()));
	   String res = in.readLine();
	   JSONObject obj1 = new JSONObject(res);
	   
      String to = "test@example.com";

      String from = "from@example.com";
      final String username = "a23bfc9d921b13";\\ here username from mailtrap
      final String password = "8a852386313143";\\ here pass from mailtrap

      String host = "smtp.mailtrap.io";\\ here hostname from mailtrap

      Properties props = new Properties();
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.port", "2525");
      Session session = Session.getInstance(props,
    	         new javax.mail.Authenticator() {
    	            protected PasswordAuthentication getPasswordAuthentication() {
    	               return new PasswordAuthentication(username, password);
    	            }
    		});

      try {
		    Message message = new MimeMessage(session);
		    message.setFrom(new InternetAddress(from));
		    message.setRecipients(Message.RecipientType.TO,
		               InternetAddress.parse(to));
		    message.setSubject("My first message with JavaMail");
		    message.setText("City: "
		    		+obj1.getJSONObject("location").getString("name")+"\nCurrent Temperature: "+obj1.getJSONObject("current").getString("temp_c"));
		    Transport.send(message);
		
		    System.out.println("Sent message successfully....");

      } catch (MessagingException e) {
         throw new RuntimeException(e);
      }
	   }

	   @Override
	   public void run() {
	       
	   }
}
