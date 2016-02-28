import java.io.*;
import java.util.*;

public class FileIO {
   public static void main(String args[]) throws IOException
   {
	   FileReader fileIn = null;
	   Scanner scanIn = null;

      try {
   	 
         fileIn = new FileReader("C:/Users/JoDan/workspace/332_P2/src/questions.txt");
         scanIn = new Scanner(fileIn);
         Integer count = 0;
         int qCount = 0;
         int aCount = 0;
         
         String quest;
         String a1;
         String a2;
         String a3;
         String a4;
         String ans = "";
         
         
        while(scanIn.hasNextLine()){
        	String temp = scanIn.nextLine();
        	StringBuilder sb = new StringBuilder(temp);
        	ans = "";
			count = 0;
			aCount = 0;
			qCount++;
			sb.deleteCharAt(0);
			sb.append('?');
			quest = sb.toString();
			System.out.println(quest);
        	
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
    		 System.out.println(sb);
         }
         if(aCount == 4){
        	 System.out.println("5)All of the above");
         }
         else if(aCount == 0){
        	 System.out.println("5)None of the above");
         }
         System.out.println("\n"+ans+"\n");
      }
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