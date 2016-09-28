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
		
		ArrayList<String> BFS = getWordLadderBFS(inputs.get(0), inputs.get(1));	// make BFS word ladder
		ArrayList<String> DFS = getWordLadderDFS(inputs.get(0), inputs.get(1)); // make DFS word ladder
		
		if(BFS.size() > DFS.size())	
			printLadder(DFS);
		else 
			printLadder(BFS);
	}
	
	public static void initialize() {
		// initialize your static variables or constants here.
		// We will call this method before running our JUNIT tests.  So call it 
		// only once at the start of main.
		
		dictionary = makeDictionary();			// create the dictionary graph
		inputs = new ArrayList<String>();		// create ArrayList to hold input
		createAdjacencyList(dictionary);					//create adjacency list graph
	}
	
	/**
	 * 
	 * @param dictionary
	 */
	static void createAdjacencyList(Set<String> dictionary){
		Iterator<String> scan = dictionary.iterator();
	//do I need to create a graph here 
		HashSet<Node> adjacencyList = new HashSet<Node>();
	
		while(scan.hasNext()){
			Node newWord = new Node(scan.next(), dictionary); //but is it ok that these 
			//Node objects don't have unique names
			//needs to not be a local variable
			//maybe that's why one version is to create Node graph with each BFS/DFS call
			adjacencyList.add(newWord);
		}
		
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
	
	public static ArrayList<String> getWordLadderDFS(String start, String end) {
	  	ArrayList<String> ladder = new ArrayList<String>();
	  	
	  	// include start and end word in the ladder. ladder.get(0) should be the end word
	  	
		// TODO depth first search the dictionary
	  	// if we make a ladder, set existsDFS flag to true
    	// if no ladder, set existsDFS flag to false
		
		return ladder; // return the ladder generated by DFS
	}
	
    public static ArrayList<String> getWordLadderBFS(String start, String end) {
    	ArrayList<String> ladder = new ArrayList<String>();
    	
	  	// include start and end word in the ladder. ladder.get(0) should be the start word
		
		// TODO breath first search the dictionary
	  	// if we make a ladder, set existsBFS flag to true
    	// if no ladder, set existsBFS flag to false
			
		return ladder; // return the ladder generated by BFS
	}
    
	public static Set<String>  makeDictionary () {
		Set<String> words = new HashSet<String>();
		Scanner infile = null;
		try {
			infile = new Scanner (new File("five_letter_words.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Dictionary File not Found!");
			e.printStackTrace();
			System.exit(1);
		}
		while (infile.hasNext()) {
			words.add(infile.next().toUpperCase());
		}
		return words;
	}
	
	public static void printLadder(ArrayList<String> ladder) {
		
		//** NOTE: 0-rung word ladders may exist - check exists flag for BFS/DFS
		if(!(existsBFS && existsDFS)){			// check to see if a ladder exists
			System.out.println("no word ladder can be found between " + inputs.get(0) + "and " + inputs.get(1) + ".");
			return;
		}
		
		System.out.println("a " + (ladder.size() - 2) + "-rung word ladder exists between " + inputs.get(0) + "and " + inputs.get(1) + ".");

		if(ladder.get(0) == inputs.get(0)){ 	// print from start to end 
			for(int k = 0; k < ladder.size(); k++){	
				System.out.println(ladder.get(k));
			}
		}
		
		else									// print in reverse order
			for(int k = ladder.size() - 1; k > 0; k--){	
				System.out.println(ladder.get(k));
			}

	}
	// TODO
	// Other private static methods here
}

