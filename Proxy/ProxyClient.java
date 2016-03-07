
import java.io.*;
import java.net.*;
import java.util.StringTokenizer;

public class ProxyClient extends Thread
{
	private static final int BUFFER_SIZE = 3333;
	private Socket socket = null;
	private DataInputStream ins = null;
	private InputStream inStream = null;

	public ProxyClient(Socket soc)
	{
		socket = soc;
	}
	
	public void run()
	{
		
		
		try 
		{
			
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			FileOutputStream logFile = new FileOutputStream("log.txt", true);
			BufferedWriter log = new BufferedWriter(new OutputStreamWriter(logFile));
			
			String inLine = "";
			String URLtoRequest = "";
			
			if((inLine = in.readLine()) != null) {
                try {
                    StringTokenizer tok = new StringTokenizer(inLine);
                    tok.nextToken();
                    log.write(inLine);
					log.newLine();
                
                	URLtoRequest = tok.nextToken();
                	
                    System.out.println("Request for : " + URLtoRequest);
                    
                    while((inLine = in.readLine()) != null){
    					log.write(inLine);
    					log.newLine();
    				}
                } catch (Exception e) {
                }
            }
			
			if(log != null)
				log.close();
			
			BufferedReader read = null;
			
			try{
				URL el = new URL(URLtoRequest);
				URLConnection con = el.openConnection();
				
				con.setDoInput(true);
				con.setDoOutput(false);
				
				if(con.getContentLengthLong() > 0){
					try {
						inStream = con.getInputStream();
						read = new BufferedReader(new InputStreamReader(inStream));
					}
					catch(IOException ioe){
						System.out.println("#*#*#---IO Exception---#*#*#");
					}
					
					byte bt[] = new byte[BUFFER_SIZE];
					int indx = inStream.read(bt,0,BUFFER_SIZE);
					while(indx != -1){
						out.write(bt,0,indx);
						indx = inStream.read(bt,0,BUFFER_SIZE);
					}
					out.flush();
				}
				
			}
			catch(Exception e){
				System.err.println("#*#*#---Exception: " + e + "---#*#*#");
				out.writeBytes("");
			}

		}
		catch (IOException e) 
		{	
		}
				
	}

	public void open() throws IOException
	{
		ins = new DataInputStream(socket.getInputStream());
	}
	public void close() throws IOException
	{
		if(socket != null)
			socket.close();
		if(ins != null)
			ins.close();
	}
}