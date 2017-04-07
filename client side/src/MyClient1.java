// in tcp der r two classes - two sockets - one is for server (LIstner) and one is for client 
// ServerSocket is for Server and Socket class is for client]
//there are two constructors to create a client sockt- 1.Socket(String hostname or ipaddress,int port_number);
	// there are no direct methods nd constuctor to establish a connectn to d server
	// connection is established upon d creation of client socket to d designetd Server
	// we r giving ip address and port numbr of machinr(server) to whch we want to connect-which is end point

// after dat we will OBTAIN input output stream associated with that object TO PERFORM stream operations
// here we will convert dem into DataInputStream and DataOutputStream bcz dis  r higher level streams which can perform 
		//read and write operations line by line frm undrlying i/o streams using methods : read/writeUTF();
// suppose if we use simple i/o streams den methods r -- {int c=in.read()} and {out.write(bytes)} 

// PROGRAM : TO CREATE A SIMPLE CHAT BW CLIENT AND SERVER
//NOW FROM constructor we r calling one function clientchat() >> what it will do ? 
//>> it will first connect keybord for client using BUfferedReader and InputStreamReader and System.in
//den bufferedreader object reads d msg and send it to underlying outputstream(ultimately to d server) using obtained outputstream object

//now client will fetch d server msg frm d underlyin inputstream using readUTF() and prints it 


import java.io.*;
import java.net.*;

public class MyClient1 {
	
	Socket s;
	DataInputStream din;    // bcz we must obtain datainputstream and outputstream associated with the specified socket
	DataOutputStream dou;
	
	MyClient1()                       
	{
		try{
			//s=new Socket(inetaddobject,2345);         // using InetAddress object
			//s=new Socket("localhost",2345);          //by giving host name
			s=new Socket("192.168.1.4",2345);          // by giving ip address of host
			
			// dis will create a Socket to a named host and port but here server progrm is running on 'localhost' so we ve given localhost address
			
			System.out.println(s);
			
			din=new DataInputStream(s.getInputStream()); //it is constructor of dinstream
			dou=new DataOutputStream(s.getOutputStream());
			
			clientchat();
			
			
		}
		catch(Exception e){System.out.println(e);}
	}
	
	public void clientchat() throws IOException
	{	String sr;
		
		do{
	
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in)); 
		// we want client to pass data frm keybord using (system.in) which will return InputStream object of user input
		//we ve passed it to inputStreamReader to create a Reader object 
		//bcz we want to use higher level reader - BUfferedReader for line by line reading---bcz BufferedReader cant directly work on Streams
		//it can work on Reader Objects only(like FileReader,InputStreamReader)
								
		sr=br.readLine();   // it is d method of BufferedReader which returns String
		dou.writeUTF(sr);   // we r giving it to underlying output stream means sending it to server - sending msg to server
		
		System.out.println("server msg : " + din.readUTF());  // printing server's msg by getting it frm underlying inputstream using readUTF() method which return type is string 
		
		}while(!sr.equals("stop"));  // if we dont put a loop den it will send only one message
	}
	
	public static void main(String args[])
	{
		new MyClient1();
	}
	

}
