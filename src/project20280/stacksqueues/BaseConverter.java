package project20280.stacksqueues;

import project20280.interfaces.Stack;

public class BaseConverter {

	public static String convertToBinary(long dec_num) {
		Stack<Integer> remainders = new LinkedStack<Integer>();
		
		while (dec_num > 0) {
			remainders.push((int) (dec_num % 2));
			dec_num /= 2;
		}
		
		String binaryString = "";
		while (!remainders.isEmpty()) {
			binaryString = binaryString.concat(remainders.pop().toString());
		}
		return binaryString;
	}
	
}
