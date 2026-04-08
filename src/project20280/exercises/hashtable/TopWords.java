package project20280.exercises.hashtable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import project20280.hashtable.ChainHashMap;
import project20280.interfaces.Entry;

class TopWords {
	public static void main(String []args) throws FileNotFoundException {
		 File f = new File("C:/Users/jamie/Documents/College Stuff/Stage 2/Spring/COMP20280 Data Structures/Labs/Week 8/sample_text.txt"); // check the path to the file
		 ChainHashMap<String, Integer> counter = new ChainHashMap<String, Integer>();
		
		 // use a Scanner to read words from the file
		 Scanner scanner = new Scanner(f);
		 Integer val;
		 while(scanner.hasNext()) { // read the file word at a time
			 String word = scanner.next().toLowerCase();
			 System.out.println("word:" + word);
			
			 // if word is not in the hashmap, add it with count=1
			 // otherwise, find the entry for this word and increment by 1
			 if ((val = counter.get(word)) != null) {
				counter.put(word, val+1); 
			 } else {
				counter.put(word, 1); 
			 }
		 }
		 
		 scanner.close();
		
		 // sort the key, values...
		 // Can you sort the Entries by the value?
		 Iterable<Entry<String, Integer>> entries = counter.entrySet();
		 ArrayList<Entry<String, Integer>> sorted = new ArrayList<Entry<String, Integer>>();
		 
		 for (Entry<String, Integer> e : entries) {
			 sorted.addLast(e);
		 }
		 
		 sorted.sort((e1, e2) -> e1.getValue() - e2.getValue());
		 
		 for (int i = 1; i <= 10; i++) {
			 Entry<String, Integer> e = sorted.get(sorted.size() - i);
			System.out.println("| " + e.getKey() + " | " + e.getValue() + " |");
		 }
	}
	
}
