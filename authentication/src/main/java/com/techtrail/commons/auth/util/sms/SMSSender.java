package com.techtrail.commons.auth.util.sms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SMSSender{
	
	//@Value("${sms.base_url}")
	private String BASE_URL;
	
	//@Value("${sms.key}")
    private String KEY;
	
	//@Value("${sms.sender_id}")
    private String SENDER_ID;
	
	//@Value("${sms.method}")
    private String METHOD;
	
	

	public static String retval = "";	

	public String send(String to, String message)

	{

		String rsp = "";

		try {
			
			// Construct The Post Data
			String data = URLEncoder.encode("api_key", "UTF-8") + "=" + URLEncoder.encode(KEY, "UTF-8");

			data += "&" + URLEncoder.encode("to", "UTF-8") + "=" + URLEncoder.encode(to.toString(), "UTF-8");

			data += "&" + URLEncoder.encode("message", "UTF-8") + "=" + URLEncoder.encode(message, "UTF-8");

			data += "&" + URLEncoder.encode("sender", "UTF-8") + "=" + URLEncoder.encode(SENDER_ID, "UTF-8");
			
			data += "&" + URLEncoder.encode("method", "UTF-8") + "=" + URLEncoder.encode(METHOD, "UTF-8");
			
			// Push the HTTP Request

			URL url = new URL(BASE_URL);

			URLConnection conn = url.openConnection();

			conn.setDoOutput(true);

			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

			wr.write(data);
			
			wr.flush();

			// Read The Response

			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String line;

			while ((line = rd.readLine()) != null) {

				// Process line...

				retval += line;

			}

			wr.close();

			rd.close();

			rsp = retval;

		} catch (Exception e) {

			e.printStackTrace();

		}
		return rsp;

	}

	public static void main(String[] args) {
		
		String [] smsTo = {"7020991316","9834971082"};
		
		String too = String.join(",", smsTo); 
		
		SMSSender sender = new SMSSender();
		
		sender.send(too, "Hello, I am just testing sms");

		//System.out.println(response);

	}
}