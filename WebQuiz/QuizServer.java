import java.io.*;
import java.net.*;
import java.util.*;


public class QuizServer extends Thread
{	
	private static ServerSocket servSock = null;
	FileIO q = new FileIO();
	public static void main(String argv[]) throws Exception
    {
    	new QuizServer().start();
    }

    public void run()
    {
    	String header = "<!DOCTYPE html><html><head><meta charset='UTF-8'>	<title>Silly Questions Place</title></head><body><form method='POST' action='' ><h1>We sent the old thing to the browser :D</h1>";
        try
        {
        	servSock = new ServerSocket(55555);
        	System.out.println("Server started on port 55555");

        	boolean quit = false;

                boolean again = false;
                boolean val = false;
        	
        	while(!quit)
        	{
        		Socket socket = servSock.accept();
        		PrintWriter out = new PrintWriter(socket.getOutputStream());            
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String line = "";
                Random rand = new Random();

                //Get request from client
                int length = -1;
                while ((line = in.readLine()) != null && (line.length() != 0)) 
                {
                    if (line.indexOf("Content-Length:") > -1) 
                    {
                        length = new Integer(line.substring(line.indexOf("Content-Length:") + 16, line.length())).intValue();
                    }
                }
                /////////////////////

                String request = "";
                
                for (int i = 0; i < length; i++) 
                {
                    int input = in.read();
                    request += (char)input;
                }


                String reply = "";
                if (request.indexOf("answer") > -1)
                {
                    //check if correct, ask to continue
                    reply = header;

                    if (request.indexOf("answer=true") > -1)
                    {
                        reply += "<h1 style='color: green'>That's right!</h1>";
                    }
                    else 
                    {
                        reply += "<h1 style='color: red'>False!, the correct answer is:</h1><br/>";
                        reply += "<p>" + q.answers() + "</p>";
                    }
                    
                    reply += "<h2>Would you like to continue</h2><input type='radio' name='continue' value='YES'> Yeah <br><input type='radio' name='continue' value='NO'> Nah <br><input type='submit' value='Submit'></form></body>";
                	
                }

                else 
                {
                    //display questions, check if continue
                    if (request.indexOf("continue=NO") > -1) 
                    {
                        quit = true;
                    }
                    
                    int n = rand.nextInt(4);
            
                    //generate page
                    reply = header;
                    reply += "<p>" + q.question() + "</p><br/>";    
                    int cnt = 4;
                    if(q.add())
                    	cnt = 5;


                   	 for (int i = 1; i <= cnt; i++)
                        {
                            reply += "<input type='radio' name='answer' value='";
                            if(i == q.answers())
                            	reply += true;
                            else
                            	reply += false;

                            reply += "'> " + i + " </input> <br>";
                        }

                    reply += "<input type='submit' value='submit'></form></body></html>";

                   
                }
                again = true;
                if (!quit)
                {
                    out.println("HTTP/1.0 200 OK");
                    out.println("Content-Type: text/html; charset=utf-8");
                    out.println("");
                    out.println(reply);
                }
                
                out.close();
                socket.close();
        	}
        	servSock.close();   
        }
        catch(Exception e)
        {
        	System.out.println("ERROR: Failed to create server socket...");
        }
    }

}