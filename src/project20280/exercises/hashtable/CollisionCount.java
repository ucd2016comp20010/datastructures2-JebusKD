package project20280.exercises.hashtable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import project20280.hashtable.ChainHashMap;
import project20280.interfaces.Entry;

class CollisionCount {
	public static void main(String []args) {
		 File f = new File(args[0]); // check the path to the file
		 
		 new Thread(new PolyThread(f, 41)).start();
		 new Thread(new PolyThread(f, 17)).start();
		 new Thread(new OldThread(f)).start();
		 for (int i = 0; i <= 31; i++) {
			 new Thread(new CycleThread(f, i)).start();
		 }
	}
	
	private static class PolyThread implements Runnable {

		private final File f;
		private final int a;
		
		public PolyThread(File f, int a) {
			this.f = f;
			this.a = a;
		}
		
		@Override
		public void run() {
			 ChainHashMap<Integer, Integer> counter = new ChainHashMap<Integer, Integer>();
				
			 // use a Scanner to read words from the file
			 try (Scanner scanner = new Scanner(f)) {
				 Integer val, key;
				 while(scanner.hasNext()) { // read the file word at a time
					 String word = scanner.next();
					 
					 key = hash_poly(word, a);
					 
					 // if key is not in the hashmap, add it with count=1
					 // otherwise, find the entry for this key and increment by 1
					 if ((val = counter.get(key)) != null) {
						counter.put(key, val+1); 
					 } else {
						counter.put(key, 1); 
					 }
				 }
				
				 // count collisions
				 Iterable<Entry<Integer, Integer>> entries = counter.entrySet();
				 int count = 0;
				 
				 for (Entry<Integer, Integer> e : entries) {
					 if (e.getValue() > 1) {
						 System.out.println("key : " + e.getKey() + ", copies = " + e.getValue());
						count += (e.getValue() - 1);
					 }
				 }
				 
				 System.out.println("hash_poly thread with a = " + a + ", Collisions = " + count);
			 } catch (FileNotFoundException e) {
				 e.printStackTrace();
			 }
		}
		
	}
	
	private static class CycleThread implements Runnable {

		private final File f;
		private final int s;
		
		public CycleThread(File f, int s) {
			this.f = f;
			this.s = s;
		}
		
		@Override
		public void run() {
			 ChainHashMap<Integer, Integer> counter = new ChainHashMap<Integer, Integer>();
				
			 // use a Scanner to read words from the file
			 try (Scanner scanner = new Scanner(f)) {
				 Integer val, key;
				 while(scanner.hasNext()) { // read the file word at a time
					 String word = scanner.next();
					 
					 key = hash_cyclic(word, s);
					 
					 // if key is not in the hashmap, add it with count=1
					 // otherwise, find the entry for this key and increment by 1
					 if ((val = counter.get(key)) != null) {
						counter.put(key, val+1); 
					 } else {
						counter.put(key, 1); 
					 }
				 }
				
				 // count collisions
				 Iterable<Entry<Integer, Integer>> entries = counter.entrySet();
				 int count = 0;
				 
				 for (Entry<Integer, Integer> e : entries) {
					 if (e.getValue() > 1) {
						count += (e.getValue() - 1);
					 }
				 }
				 
			 } catch (FileNotFoundException e) {
				 e.printStackTrace();
			 }
		}
		
	}
	
	private static class OldThread implements Runnable {

		private final File f;
		
		public OldThread(File f) {
			this.f = f;
		}
		
		@Override
		public void run() {
			 ChainHashMap<Integer, Integer> counter = new ChainHashMap<Integer, Integer>();
				
			 // use a Scanner to read words from the file
			 try (Scanner scanner = new Scanner(f)) {
				 Integer val, key;
				 while(scanner.hasNext()) { // read the file word at a time
					 String word = scanner.next();
					 
					 key = CollisionCount.hashCode(word);
					 
					 // if key is not in the hashmap, add it with count=1
					 // otherwise, find the entry for this key and increment by 1
					 if ((val = counter.get(key)) != null) {
						counter.put(key, val+1); 
					 } else {
						counter.put(key, 1); 
					 }
				 }
				
				 // count collisions
				 Iterable<Entry<Integer, Integer>> entries = counter.entrySet();
				 int count = 0;
				 
				 for (Entry<Integer, Integer> e : entries) {
					 if (e.getValue() > 1) {
						count += (e.getValue() - 1);
					 }
				 }
				 
				 System.out.println("hashCode thread, Collisions = " + count);
			 } catch (FileNotFoundException e) {
				 e.printStackTrace();
			 }
		}
		
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
	
	private static int hashCode(String s) {
		int hash = 0;
		int skip = Math.max(1, s.length() / 8);
		for (int i = 0; i < s.length(); i += skip)
			hash = (hash * 37) + s.charAt(i);
		return hash;
	}
}
