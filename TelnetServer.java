/*
*	Authors: J.D. Alberts (u1439 5283) & Unarine Rambani (u1400 4489)
*	Disclaimer: The code in this program was coded  - from scratch - by us alone,
*	but it should be noted that we did employ methodology and advice from several online resources.
*	Among them are: http://pirate.shu.edu/~wachsmut/Teaching/CSAS2214/
*					http://cs.lmu.edu/~ray/notes/javanetexamples/
*					http://stackoverflow.com/questions/
*/

import java.io.*;
import java.net.*;

public class TelnetServer implements Runnable {
	
	ServerSocket ss;
	Socket s;
	BufferedReader br1;
	BufferedReader br2;
	PrintWriter pw;
	Thread t1;
	Thread t2;
	String in = "";
	String out = "";
	FileIO q = new FileIO();

	public TelnetServer(int port) {
		try {
			t1 = new Thread(this);
			t2 = new Thread(this);

			ss = new ServerSocket(port);
			System.out.println("***Server socket open...***");
			
			s = ss.accept();
			System.out.println("***Client has joined chat.***");

			t1.start();
			t2.start();
		}
		catch (Exception e){
			System.out.println("***Chat error occured!***");
		}
	}

	public void run(){
		try {
			String asking = q.question();
			//if(Thread.currentThread().getId() == t1.getId()){
				pw = new PrintWriter(
						s.getOutputStream(), true);
						pw.println(asking);
			//}
			//else {
				
				do{
					br2 = new BufferedReader(new InputStreamReader(s.getInputStream()));
					out = br2.readLine();
					String a = q.answers();
					
					if(a.contains(out)){
						pw = new PrintWriter(s.getOutputStream(), true);
						pw.println("Congratulations!");
					}
				}
				while(!out.equals("quit") && !out.equals("QUIT") && !out.equals("Quit"));
			//}
		}
		catch(Exception e){
			System.out.println("***Chat error occured!***");
		}
	}

	public static void main(String[] args){
		new TelnetServer(Integer.parseInt(args[0]));
	}

}