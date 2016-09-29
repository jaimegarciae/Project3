/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Katya Malyavina
 * ym5356
 * 16465
 * Regan Stehle 
 * rms3762
 * 16465
 * Slip days used: 0
 * Git URL: https://github.com/kmalyavina/Project3
 * Fall 2016
 */


package assignment3;
import java.util.*;
import java.io.*;

public class Main {
	
	// static variables and constants only here.
	static ArrayList<String> inputs;
	static boolean existsDFS; 	// does a DFS ladder exist?
	static boolean existsBFS;		// does a BFS ladder exist?
	static Set<String> dictionary;
	static HashSet<String> visited = new HashSet<String>();
	static TreeMap<String, Node> nodeMap;

	
	public static void main(String[] args) throws Exception {
		
		Scanner kb;	// input Scanner for commands
		PrintStream ps;	// output file
		// If arguments are specified, read/write from/to files instead of Std IO.
		if (args.length != 0) {
			kb = new Scanner(new File(args[0]));
			ps = new PrintStream(new File(args[1]));
			System.setOut(ps);			// redirect output to ps
		} else {
			kb = new Scanner(System.in);// default from Stdin
			ps = System.out;			// default to Stdout
		}
		initialize();
		
		parse(kb);
		
		//ArrayList<String> BFS = getWordLadderBFS(inputs.get(0), inputs.get(1));	// make BFS word ladder
		ArrayList<String> DFS = getWordLadderDFS(inputs.get(0), inputs.get(1)); // make DFS word ladder
		
//		if(BFS.size() > DFS.size())	
//			printLadder(DFS);
//		else 
			printLadder(DFS);
	}
	
	public static void initialize() {
		// initialize your static variables or constants here.
		// We will call this method before running our JUNIT tests.  So call it 
		// only once at the start of main.
		
		dictionary = makeDictionary();			// create the dictionary graph
		inputs = new ArrayList<String>();		// create ArrayList to hold input
		nodeMap = new TreeMap<String, Node>();
		createAdjacencyList();					//create adjacency list graph
	}
	
	/**
	 * 
	 * @param dictionary
	 */
	static void createAdjacencyList(){
		Iterator<String> scan = dictionary.iterator();
		String word = scan.next();
		Node newWord = new Node(word);
		while(scan.hasNext()){
			nodeMap.put(word.toLowerCase(), newWord);
			word = scan.next();
			newWord = new Node(word);
		}
		nodeMap.put(word.toLowerCase(), newWord);
	}
	
	/**
	 * @param keyboard Scanner connected to System.in
	 * @return ArrayList of 2 Strings containing start word and end word. 
	 * If command is /quit, return empty ArrayList. 
	 */
	public static ArrayList<String> parse(Scanner keyboard) {		
		String input = keyboard.nextLine();		// get the whole input
		Scanner words = new Scanner(input);		// word scanner
		
		if(input.contains("/quit")){			// see if the user wants to quit
			System.exit(0);						// terminate the program
		}
		
		inputs.add(words.next());				// get first word
		inputs.add(words.next());				// get second word
		words.close();							// end scan
		
		return inputs;
	}
	
  	
	/**
	 * Make a word ladder using Depth First Search
	 * @param start
	 * @param end
	 * @return the word ladder
	 */
	public static ArrayList<String> getWordLadderDFS(String start, String end) {
		   // mark start as visited  
		ArrayList<String> ladder = new ArrayList<String>();
		   visited.add(start); 
		   
		  // ArrayList<String> ladder = new ArrayList<String>();
		   
		   for (int i=0; i<start.length(); i++) {  		// go through each of the characters
		     StringBuilder sb = new StringBuilder(start); 
		     // change one letter and check if it's in the end or in the dictionary
		     for (char c='A'; c<='Z'; c++) {  
		       if (c == start.charAt(i)) continue; // skip itself  
		       sb.setCharAt(i, c);  
		       String word = sb.toString();  
		       // if hits end, return 
		       if (word.equals(end))  {
		    	   existsDFS = true;
		    	   return ladder; 
		    	   }
		        
		       // keep going
		       else if (!visited.contains(word) && dictionary.contains(word)) {  
		    	   // recursive call DFS  
		    	   visited.add(word);
		    	   ladder.addAll(getWordLadderDFS(word,end));
		    	   return ladder;  
		       	}
		       
		     }  
		   	
		   }  // changes each letter to try to get to the end word
		   
		   visited.remove(start);
		   return ladder;  
		   
		 } 
	
   /**
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
    public static ArrayList<String> getWordLadderBFS(String start, String end) {
    	ArrayList<String> ladder = new ArrayList<String>();
    	
    	Node current = nodeMap.get(start.toLowerCase());
    	//System.out.println(current.word);
    	Node last = nodeMap.get(end.toLowerCase());
    	boolean found = false;
    	
    	Queue<Node> Q = new LinkedList<Node>();
    	current.distance = 0;
    	current.visited = true;
    	Q.add(current);
    	
    	
    	
    	while(!Q.isEmpty()){
    		current = Q.poll();
    		
    		for(int i = 0; i < current.neighbors.size(); i++){
       			Node neighbor = nodeMap.get(current.neighbors.get(i));
       			if (neighbor == last){
       				existsBFS = true;
        			found = true;
        			break;
        		}
    			if(!neighbor.visited){
    				neighbor.visited = true;
    				neighbor.distance = current.distance + 1;
    				neighbor.predecessor = current;
    				Q.add(neighbor);
    				ladder.add(neighbor.word.toLowerCase());
    				System.out.println(neighbor.word);
    			}
    			
    		}
    	
    	}
    	
    	if(found == true){
    		System.out.println("found");
    	}
	  	
   		// TODO breath first search the dictionary
	  	// if we make a ladder, set existsBFS flag to true
    	// if no ladder, set existsBFS flag to false    	
    	
		return ladder; // return the ladder generated by BFS
	}
    
	public static Set<String>  makeDictionary () {
		Set<String> words = new HashSet<String>();
		Scanner infile = null;
		try {
			infile = new Scanner (new File("short_dict.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Dictionary File not Found!");
			e.printStackTrace();
			System.exit(1);
		}
		while (infile.hasNext()) {
			words.add(infile.next().toUpperCase());
		}
		System.out.println("*** Dictionary Created ***"); // testing 
		return words;
	}
	
	public static void printLadder(ArrayList<String> ladder) {
		
		//** NOTE: 0-rung word ladders may exist - check exists flag for BFS/DFS
		if(!(existsBFS || existsDFS)){			// check to see if a ladder exists
			System.out.println("no word ladder can be found between " + inputs.get(0) + " and " + inputs.get(1) + ".");
			return;
		}
		
		System.out.println("a " + (ladder.size()) + "-rung word ladder exists between " + inputs.get(0) + " and " + inputs.get(1) + ".");

			System.out.println(inputs.get(0));
			for(int k = 0; k < ladder.size(); k++){	
				System.out.println(ladder.get(k));
			}
			System.out.println(inputs.get(1));
		
		
//		else									// print in reverse order
//			System.out.println(inputs.get(0));
//			for(int k = ladder.size() - 1; k > 0; k--){	
//				System.out.println(ladder.get(k));
//			}
//			System.out.println(inputs.get(1));
	}
	// TODO
	// Other private static methods here
}
