// in d serversocket constructor i am creating serversocket object - to which port d server wants to listen
// same socket numbr we r giving to d client constructor bcz we can listen multiple clients on single socket
// at d time of connecting d client by accept() method one unique dynamic port for each client generated
// den we obtain streams for a socket dat accept() method returns to do io operations

// in serverchat() we will read client msg and prints it using obtained inputstream object
// den if we want echoserver den forward same string
// if we want server to type den connect keybord and send it to client

// dont forget to put a loop otherwise only one msg can be sent

import java.io.*;
import java.net.*;


public class MyServer 
{
	ServerSocket ss;           // to specify serversocket port on which server wants to listen and create an object
	Socket s;                  // same to create a sockt for communication using accept() method
	DataInputStream dis;       // IMPORTANT NOTE: to obtain streams for socket object dat is returned by accept() method
	DataOutputStream dos;         


			MyServer()
			{
				try
				{
				
					ss=new ServerSocket(2345);
					System.out.println("server started");
					
					s=ss.accept();  //it is blocking call - dis will wait until client gets connected
								   // ON CONNECTING OF CLIENT-it returns new socket which will be used for frthr communication
					
					System.out.println(s);  //printing d client socket on server side
					
					dis=new DataInputStream(s.getInputStream());   // we r taking Datainput and output streams here bcz dey r high level streams and
					dos=new DataOutputStream(s.getOutputStream()); //dey hv methd to read or write line by line so we r just cnvrtng into it
					
					serverchat();
					}
				catch(Exception e)
				{
					System.out.println(e);
				}
			}



				public void serverchat() throws IOException
				{
					String str,strr;
				
					do{
					
				
					str=dis.readUTF();  //we r using dis to get a message dat a client sent to us and to make its echo and send it again
					
					System.out.println(str); // printing client's message on d server side
					
					BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
					
				// dos.writeUTF("hello :" + str); // we r giving same msg with hello appended to it and writing it to d underlying outputstream
					
					strr=br.readLine();
					
					dos.writeUTF(strr);
					
					}while(!strr.equals("stop")); //if u dont put a loop here den server will be closed aftr one msg only
				}
				
				public static void main(String args[])
				{
					new MyServer();
				}



}




