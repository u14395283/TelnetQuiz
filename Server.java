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
				FileIO q = new FileIO();
				
				while(true){	
					input = new BufferedReader(new InputStreamReader(client.getInputStream()));
					output = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
					System.out.println("Client connected...");
					output.write(q.question());
					output.flush();
					
					String answer = q.answers();
					
					String get = input.readLine().trim();
					char test = get.charAt(get.length() - 1);
					System.out.println(get + "lol");
					
					if(answer.charAt(0)== test){
						output.write("Congrats!\n Try again? (yes/no)");
					}
					else {
						output.write("Sorry. The answer was: "+answer+"\n Try again? (yes/no)");
					}
					
					output.flush();
					get = input.readLine();
					
					if(get.equals("no")){
						quit = true;
						break;
					}
				}
			}
			catch(Exception e)
			{
				System.out.println("Failed to create socket");
			}
		}
	}
}
