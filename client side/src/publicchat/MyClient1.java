// client side there will be two threads one is connecting keybord to d client and take
// messages and send it to d server- which is main thread and another thread will recv d msgs frm server
// and prints dem (which is rcvd frm anthr client to server)


package publicchat;
import java.io.*;
import java.net.*;
import java.util.*;

public class MyClient1 
{
	Socket s;
	DataInputStream din;
	
	public MyClient1() throws Exception
	{
			s=new Socket("localhost",123);
			
			Runnable r=new My(din);  //dis thread will rcv msg frm server n prints dem
			
			Thread t=new Thread(r);
			t.start();
		
		//main thread taking msgs frm keybord and send it to d server	
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String str1;
		do
		{
		str1=br.readLine();
		DataOutputStream dout=new DataOutputStream(s.getOutputStream());
		dout.writeUTF(str1);
		}while(!str1.equals("stop"));
		
		}
public static void main(String args[]) throws Exception
{
	new MyClient1();
}

}

class My implements Runnable
{
	DataInputStream din;
	
	My(DataInputStream din)
	{
		this.din=din;
	}

	public void run()
	{
		String str2;
		try{
				
			do
			{
				str2=din.readUTF();
				System.out.println(str2);
			}while(!str2.equals("stop"));
		}
		catch(Exception e){}
		}
		
	}
	
