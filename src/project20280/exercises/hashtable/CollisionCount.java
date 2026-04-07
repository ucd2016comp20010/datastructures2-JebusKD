package project20280.exercises.hashtable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import project20280.hashtable.ChainHashMap;
import project20280.interfaces.Entry;

class CollisionCount {
	public static void main(String []args) throws FileNotFoundException {
		 File f = new File(args[0]); // check the path to the file
		 int s = Integer.parseInt(args[1]);
		 
		 ChainHashMap<Integer, Integer> counter = new ChainHashMap<Integer, Integer>();
		
		 // use a Scanner to read words from the file
		 Scanner scanner = new Scanner(f);
		 Integer val, key;
		 while(scanner.hasNext()) { // read the file word at a time
			 String word = scanner.next();
			 
			 key = hash_cyclic(word, s);
			 
			 System.out.println("word : " + word + ", key : " + key);
			 
			 // if key is not in the hashmap, add it with count=1
			 // otherwise, find the entry for this key and increment by 1
			 if ((val = counter.get(key)) != null) {
				counter.put(key, val+1); 
			 } else {
				counter.put(key, 1); 
			 }
		 }
		 
		 scanner.close();
		
		 // count collisions
		 Iterable<Entry<Integer, Integer>> entries = counter.entrySet();
		 int count = 0;
		 
		 for (Entry<Integer, Integer> e : entries) {
			 if (e.getValue() > 1) {
				count += (e.getValue() - 1);
			 }
		 }
		 
		 System.out.println("Collisions = " + count);
		
	}
	
	private static int hash_poly(String s, int a) {
		int h = 0;
		int n = s.length();
		for (int i = 0; i < n; i++) {
			char s_i = (char) s.charAt(i);
			int v = s_i * ((int) Math.pow(a, n - i - 1));
			h += v;
		}
		return h;
	}
	
	private static int hash_cyclic(String s, int shift) {
		int h = 0;
		for (int i = 0; i < s.length(); i++) {
			h = (h << shift) | (h >>> (32 - shift));
			h += (int) s.charAt(i);
		}
		return h;
	}
}
