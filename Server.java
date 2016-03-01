import java.io.*;
import java.net.*;
public class Server 
{
	private static ServerSocket servSock = null;
	private static BufferedReader input = null;
	private static BufferedWriter output = null;
	
	public static void main(String[] args)
	{
		try 
		{
			servSock = new ServerSocket(Integer.parseInt(args[0]));
			System.out.println("Server is now running...");
		} 
		catch (NumberFormatException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		catch (IOException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		boolean quit = false;
		
		while(quit == false)
		{
			try
			{
				Socket client = servSock.accept();
				
				input = new BufferedReader(new InputStreamReader(client.getInputStream()));
				output = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
				System.out.println("Client connected...");
				output.write("Welcome to the silly question place");
				output.flush();
			}
			catch(Exception e)
			{
				System.out.println("Failed to create socket");
			}
			try 
			{
				String get = input.readLine();
			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
