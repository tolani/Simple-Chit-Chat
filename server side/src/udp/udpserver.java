// datagramsocket is used to send or recieve datagrampackets
// datagrampacket is data container

// der r four constructors of ds - (),(port num),(port,ineradd),(socketaddress)
// der r four const. of dp-------- FOr Recvng:(empty byte arr,size),(same with offset)
								// For Sendng: (byte arr,size,ip addrs,port),(same wid offset)


//first we ll create one ds at server side using secnd constuctr to specify a port on whch we wil listen to d clients
//after dat we wnt to rcv one dp so we wil create one dp object using first constructr and pass empty array in it to rcv

// den we will call rcv method of ds object 

//den using getdata method of dp we will convrt bytes into String and prints it


package udp;
import java.io.*;
import java.net.*;

public class udpserver 
{

		public static void main(String args[]) throws Exception
		{
			
			DatagramSocket ds=new DatagramSocket(8); // we ve created ds of designated port number 
			
			byte b[]=new byte[1024];        // empty array
			 
			DatagramPacket dp=new DatagramPacket(b,b.length);   // we ve passed dat array to dp object
			
			ds.receive(dp);      // it is method of DatagramSocket to recieve dp dat comes frm client
			
			String s=new String(dp.getData());  //der is one constructor in string like dis and we convert byte data into d string
			
			System.out.println(s.trim());  // printing it
			
			
		}
	
}
