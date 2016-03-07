import java.net.*;
import java.io.*;


public class Proxy {

	public static void main(String[] args) {
		ServerSocket ss = null;
		boolean open = true;
		
		int port = 55555;
		
		try {
			ss = new ServerSocket(port);
			System.out.println("We meet again port " + port + ".");
		}
		catch(IOException ioE){
			System.out.println("Server error! Burn the evidence of your existence and RUN!!!");
			System.exit(-1);
		}
		
		try {
			while(open){
				Socket client = ss.accept();
				String clientAddress = client.getRemoteSocketAddress().toString();
				System.out.println("Client Address: " + clientAddress);
				
				new ProxyClient(client).start();
			}
			if(ss != null)
				ss.close();
		}
		catch(IOException e){
			System.out.println("#*#*#---IO Exception---#*#*#");
		}
	}

}
