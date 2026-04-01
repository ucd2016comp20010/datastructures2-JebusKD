package project20280.exercises.hashtable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import project20280.hashtable.ChainHashMap;

public class TopWords {
	public static void main(String []args) throws FileNotFoundException {
		 File f = new File("sample_text.txt"); // check the path to the file
		 ChainHashMap<String, Integer> counter = new ChainHashMap<String, Integer>();
		
		 // use a Scanner to read words from the file
		 Scanner scanner = new Scanner(f);
		 Integer val;
		 while(scanner.hasNext()) { // read the file word at a time
			 String word = scanner.next();
			 System.out.println("word:" + word);
			
			 // if word is not in the hashmap, add it with count=1
			 // otherwise, find the entry for this word and increment by 1
			 if ((val = counter.get(word)) != null) {
				counter.put(word, val+1); 
			 }
		 }
		
		 // sort the key, values...
		 // Can you sort the Entries by the value?
	 }
}
