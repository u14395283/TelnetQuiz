import java.io.*;
import java.util.*;

public class FileIO {
   public String question() throws IOException
   {
	   FileReader fileIn = null;
	   Scanner scanIn = null;

      try {
   	 
         fileIn = new FileReader("C:/Users/JoDan/workspace/332_P2/src/questions.txt");
         scanIn = new Scanner(fileIn);
         Integer count = 0;
         int qCount = 0;
         int aCount = 0;
         
         //Generate Random Number
         Random rand = new Random();
         int randNum = rand.nextInt(4);
         
         String quest;
        // String a1;
        // String a2;
        // String a3;
        // String a4;
         String ask = "";
         String ans = "";
         
         
        while(scanIn.hasNextLine()&& (qCount < randNum)){
        	ask = "";
        	String temp = scanIn.nextLine();
        	StringBuilder sb = new StringBuilder(temp);
        	ans = "";
			count = 0;
			aCount = 0;
			qCount++;
			sb.deleteCharAt(0);
			sb.append('?');
			quest = sb.toString();
			ask += quest+"\n";
        	
         for(int i = 0; i < 4; i++){
        	 temp = scanIn.nextLine();
        	 sb = new StringBuilder(temp);
    		 count++;
    		 if(sb.charAt(0) == '+'){
    			 aCount++;
    			 ans += count.toString()+",";
    		 }
    		 sb.deleteCharAt(0);
    		 sb.reverse();
    		 sb.append(")"+count);
    		 sb.reverse();    		 
    		 ask += sb+"\n";
         }
         if(aCount == 4){
        	 ask += "5)All of the above\n";
         }
         else if(aCount == 0){
        	 ask += "5)None of the above\n";
         }
      }
        return ask;
      }finally {
         if (fileIn != null) {
            fileIn.close();
            if(scanIn != null){
            	scanIn.close();
            }
         }
      }
   }
}