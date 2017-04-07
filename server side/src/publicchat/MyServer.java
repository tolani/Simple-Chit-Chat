// multithreaded server required which accepts client sockets as each time request arrives and pass dat socket to each thread
// for public chatting we will use one collection arraylist so dat we will also pass dat arraylist to each thread
// which contains all client sockets , so that thread will broadcast the message
//here remember of IOException
//so ultimately server is creating a thread for each client request and it is broadcasting d msg of client to everyone
//and sending it to client only when client say stop and close d thread on stop

package publicchat;
import java.io.*;
import java.net.*;
import java.util.*;


public class MyServer 
	{
	
		ArrayList al=new ArrayList();
		ServerSocket ss;	
		Socket s;
		
		public MyServer() throws IOException
		{
			ss=new ServerSocket(123);
			
			while(true)
			{
				s=ss.accept();
				al.add(s);
				Runnable r=new MyThread(s,al);
				Thread t=new Thread(r);
				t.start();
				
			}
			
		}
		
		public static void main(String[] args) throws IOException
		{
			new MyServer();
			
		}
	

}

class MyThread implements Runnable
{
	Socket s;
	ArrayList al;
	
	MyThread(Socket s,ArrayList al)
	{
		this.s=s;
		this.al=al;
	}
	
	public void run()
	{
		String str;
		
		try
		{
		DataInputStream din=new DataInputStream(s.getInputStream());
		
		
		do{
		str=din.readUTF();
		
		if(!str.equals("stop"))
			talkeveryone(str);               // if client is not typing stop den i will forward dat to everyone
		else
		{
			DataOutputStream dout=new DataOutputStream(s.getOutputStream());
			
			dout.writeUTF(str);  // send it to client only
			
		}}while(!str.equals("stop"));         //if we dont put a loop den it will do it only once
		
		}
			catch(Exception e){}
		}
	
	public void talkeveryone(String str) throws IOException
	{
		Iterator i=al.iterator();
		
		while(i.hasNext())
		{
			//we need differnt sockets here
			Socket sc=(Socket)i.next();//bcz i.next() returns Object so we ve to cast heree
			
			DataOutputStream dout=new DataOutputStream(sc.getOutputStream());
			
			dout.writeUTF(str);  // send it to sc clients one by one
			dout.flush();
			
		}
		
	}
	
}

