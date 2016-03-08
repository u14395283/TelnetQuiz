/*
*	Authors: J.D. Alberts (u14395283) and U.P. Rambani (u14004489)
*	Disclaimer: The code in this program was coded  - from scratch - by the above authors,
*	but it should be noted that we did employ methodology and advice from several online resources.
*	All source code used was used with permission from the original author.
*	Chief among them are: http://www.jtmelton.com/2007/11/27/a-simple-multi-threaded-java-http-proxy-server/
*/

import java.net.*;
import java.io.*;


public class Proxy {

	public static void main(String[] args) {
		ServerSocket ss = null;
		boolean open = true;
		
		try {
			ss = new ServerSocket(55555);
			System.out.println("EXPECTING ARRIVAL AT PORT 55555.");
		}
		catch(IOException ioE){
			System.out.println("SERVER FAILED TO OPEN SOCKET");
			System.exit(-1);
		}
		
		try {
			while(open){
				Socket client = ss.accept();
				String clientAddress = client.getRemoteSocketAddress().toString();
				System.out.println("CLIENT ADDRESS: " + clientAddress);
				
				new ProxyClient(client).start();
			}
			if(ss != null)
				ss.close();
		}
		catch(IOException e){
			System.out.println("IO ERROR IN SERVER");
		}
	}

}
