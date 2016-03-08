/*
*	Authors: J.D. Alberts (u14395283) and U.P. Rambani (u14004489)
*	Disclaimer: The code in this program was coded  - from scratch - by the above authors,
*	but it should be noted that we did employ methodology and advice from several online resources.
*	All source code used was used with permission from the original author.
*	Chief among them are: http://www.jtmelton.com/2007/11/27/a-simple-multi-threaded-java-http-proxy-server/
*/


import java.net.*;
import java.io.*;
import java.util.*;
import java.text.*;

public class ProxyClient extends Thread {
    private Socket soc = null;
    private static final int BYTES_SIZE = 32768;
    InputStream inStream = null;
    
    public ProxyClient(Socket socket) {
        super("ProxyClient");
        this.soc = socket;
    }

    public void run() {
        try {
            DataOutputStream out = new DataOutputStream(soc.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
            
            FileOutputStream logFile = new FileOutputStream("log.txt", true);
            BufferedWriter log = new BufferedWriter(new OutputStreamWriter(logFile));
            
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            log.write(dateFormat.format(date)+" - ");

            String inLine;
            String URLforRequest = "";
            if((inLine = in.readLine()) != null) {
                StringTokenizer request = new StringTokenizer(inLine);
                request.nextToken();
                log.write(inLine);
                log.newLine();
            
                URLforRequest = request.nextToken();
                System.out.println("REQUESTING " + URLforRequest);
                
                /*while((inLine = in.readLine()) != null){
                	log.write(request.nextToken());
                    log.newLine();
                }*/
            }

            BufferedReader read = null;
            try {
                URL url = new URL(URLforRequest);
                URLConnection con = url.openConnection();
                
                con.setDoInput(true);
                con.setDoOutput(false);
                
                HttpURLConnection httpCon = (HttpURLConnection)con;
                System.out.println("HTTP RESPONSE MESSAGE: "+httpCon.getResponseMessage());
                
                inStream = null;
                try {	
                	if (httpCon.getContentLength() > 0) {
                        inStream = httpCon.getInputStream();
                        read = new BufferedReader(new InputStreamReader(inStream));
                	}
                } catch (IOException e) {
                    System.out.println( "IO ERROR: " + e.toString());
                }
                
                byte bt[] = new byte[BYTES_SIZE];
                int i = inStream.read(bt,0,BYTES_SIZE);
                while (i != -1){
                  out.write(bt,0,i);
                  i = inStream.read(bt,0,BYTES_SIZE);
                }
                out.flush();
            } catch (Exception e){
                System.out.println("ERROR WHILE EXECUTING REQUEST: " + e.toString());
                out.writeBytes("");
            }

            if (out != null)
                out.close();
            if (in != null)
                in.close();
            if (read != null)
                read.close();
            if (soc != null)
                soc.close();
            if(log != null)
            	log.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}