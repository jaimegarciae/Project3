package assignment3;

import java.util.*;

public class TestDFS {
	
	static HashSet<String> dictionary = new HashSet<String>();
	static HashSet<String> visited = new HashSet<String>();
	   

	 public static ArrayList<String> DFS(String start, String end) {  
	   // mark s as visited  
	   visited.add(start); 
	   
	   ArrayList<String> ladder = new ArrayList<String>();
	   
	   for (int i=0; i<start.length(); ++i) {  
	     StringBuilder sb = new StringBuilder(start);  
	     for (char c='a'; c<='z'; ++c) {  
	       if (c == start.charAt(i)) continue; // skip itself  
	       sb.setCharAt(i, c);  
	       String word = sb.toString();  
	       // if hits end, return 
	       if (word.equals(end))  
	    	   return ladder; 
	        
	       // keep going
	       else if (!visited.contains(word) && dictionary.contains(word)) {  
	    	   // recursive call DFS  
	    	   ladder.addAll(DFS(start,end));
	    	   return ladder;  
	       	}  
	     }  
	   	
	   }  
	   
	   visited.remove(start);
	   return DFS(start,end);  
	   
	 }  
	 
}
