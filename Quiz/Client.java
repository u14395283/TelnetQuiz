/*
*	Author: J.D. Alberts
*	Student Number: u1439 5283
*	Disclaimer: The code in this program was coded  - from scratch - by me alone,
*	but it should be noted that I did employ methodology and advice from several online resources.
*	Among them are: http://pirate.shu.edu/~wachsmut/Teaching/CSAS2214/
*					http://cs.lmu.edu/~ray/notes/javanetexamples/
*					http://stackoverflow.com/questions/
*/

import java.io.*;
import java.net.*;

public class Client implements Runnable {
	Socket s;
	BufferedReader br1;
	BufferedReader br2;
	PrintWriter pw;
	Thread t1;
	Thread t2;
	String in = "";
	String out = "";

	public Client(String ip, int port) {
		try {
			t1 = new Thread(this);
			t2 = new Thread(this);
			
			s = new Socket(ip, port);

			t1.start();
			t2.start();
		}
		catch (Exception e){
			System.out.println("***Chat error occured!***");
		}
	}

	public void run(){
		try {
			if(Thread.currentThread().getId() == t1.getId()){
				br2 = new BufferedReader(new InputStreamReader(s.getInputStream()));
				out = "Client 1: " + br2.readLine();
				System.out.println(out);

				while(!out.equals("Client 1: quit") && !out.equals("Client 1: QUIT") && !out.equals("Client 1: Quit")){
					out = "Client 1: " + br2.readLine();
					System.out.println(out);
				}
			}
			else {
				br1 = new BufferedReader(new InputStreamReader(System.in));
				pw = new PrintWriter(s.getOutputStream(), true);
				in = br1.readLine();
				pw.println(in);

				while(!in.equals("quit") && !in.equals("QUIT") && !in.equals("Quit")){
					in = br1.readLine();
					pw.println(in);
				}
			}
		}
		catch(Exception e){
			System.out.println("***Chat error occured!***");
		}

	}

	public static void main(String[] args){
		new Client(args[0], Integer.parseInt(args[1]));
	}

}