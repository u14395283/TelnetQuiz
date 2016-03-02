import java.io.*;
import java.util.*;

public class FileIO {
	FileReader fileIn = null;
	Scanner scanIn = null;
	String answers = "";
	
	public FileIO(){
		
	}
	
	//@function returns a string with a random question and answers.
   public String question() throws IOException
   {
      try {
   	 
         fileIn = new FileReader("questions.txt");
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
			ask += quest;
			ask += "\n";
        	
         for(int i = 0; i < 4; i++){
        	 temp = scanIn.nextLine();
        	 sb = new StringBuilder(temp);
    		 count++;
    		 if(sb.charAt(0) == '+'){
    			 aCount++;
    			 ans += count.toString();
    		 }
    		 sb.deleteCharAt(0);
    		 sb.reverse();
    		 sb.append(")"+count);
    		 sb.reverse();    		 
    		 ask += sb;
    		 ask += "\n";
         }
         if(aCount > 1){
        	 ask += "5)More than 1 of the above\n";
        	 ans += "5";
         }
         else if(aCount == 0){
        	 ask += "5)None of the above\n";
        	 ans += "5";
         }
      }
        answers = ans;
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
   
   public String answers(){
	   return answers;
   }
}