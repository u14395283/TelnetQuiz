import java.io.*;
import java.net.*;
public class Server 
{
	private static ServerSocket servSock = null;
	private static BufferedReader input = null;
	private static BufferedWriter output = null;
	
	public static void main(String[] args)
	{
		Colour c = new Colour();
		try 
		{
			servSock = new ServerSocket(Integer.parseInt(args[0]));
			System.out.println(c.colour("blue","Server is now running..."));
		} 
		catch (NumberFormatException e1) 
		{
			// TODO Auto-generated catch block
			System.out.println(c.colour("red","Error: Could not create ServerSocket!"));
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
				
				while(true)
				{	
					input = new BufferedReader(new InputStreamReader(client.getInputStream()));
					output = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
					System.out.println(c.colour("green","Client connected..."));
					output.write(c.colour("yellow",q.question()));
					output.flush();
					
					String answer = q.answers();
					
					String get = input.readLine().trim();
					char test = get.charAt(get.length() - 1);
					System.out.println("Client response: " + get);
					
					if(answer.charAt(0)== test)
					{
						output.write(c.colour("green","Congrats!\n Try again? (yes/no): "));
					}
					else {
						output.write(c.colour("red","Sorry. The answer was: "+answer+"\n Try again? (yes/no): "));
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
				System.out.println(c.colour("red","Failed to create socket"));
			}
		}
	}
}
