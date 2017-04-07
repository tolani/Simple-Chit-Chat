 // SIMPLE ds constructor to send dp
// take one string whch we want to send n convrt it into an array of bytes using getbytes()
// after dat makng dp sending const and pass ol things and send using ds object




package udpclient;
import java.io.*;
import java.net.*;


public class udpclient1 
{

		public static void main(String args[]) throws Exception
		{
			DatagramSocket ds=new DatagramSocket();   // simple constructor no need of more just we wnt to send
			String str="dis msg we will send to udpserver";

			byte b[]=str.getBytes();
			
			InetAddress inet=InetAddress.getLocalHost(); //bcz we r wrkng on local
			
			DatagramPacket dp=new DatagramPacket(b,b.length,inet,8);
			// we ve put dat array in pckt and port also ip address using inet object and size
			
			ds.send(dp);
			
		}
	
	
}
