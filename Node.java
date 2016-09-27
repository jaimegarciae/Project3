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

/**
 * 
 * @author rstehle
 *Each word in dictionary becomes a Node, to create a graph where edges c
 *connect words that are separated by one letter
 */
public class Node {
	int distance; //for BFS & DFS, marked as visited/unvisited
	//possibly just create a linked list of edges as an element here, to keep as an adjacency list
	LinkedList<Node> edges; //if this is the way to parameterize this linked list
	
}
