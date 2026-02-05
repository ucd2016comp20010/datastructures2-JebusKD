package project20280.stacksqueues;

import project20280.interfaces.Stack;

class BracketChecker {
	
	private enum ErrorType {
		NONE,
		MATCHING_ERROR,
		MISSING_LEFT_PARENTHESES,
		MISSING_RIGHT_PARENTHESES
	}
	
    private final String input;
    
    public BracketChecker(String in) {
        input = in;
    }
    
    private static boolean bracketMatch(char left, char right) {
    	switch(left) {
    	case '[':
    		return right == ']';
    	case '{':
    		return right == '}';
    	case '(':
    		return right == ')';
    	default:
    		throw new IllegalArgumentException("Non-left bracket passed");
    	}
    }

    public void check() {
    	Stack<Character> charStack = new LinkedStack<Character>();
    	char[] charArray = input.toCharArray();
    	
    	ErrorType flag = ErrorType.NONE;
    	
    	for (Character c : charArray) {
    		switch (c) {
    		case '[':
    		case '{':
    		case '(':
    			charStack.push(c);
    			break;
    		case ']':
    		case '}':
    		case ')':
    			Character found = charStack.pop();
    			if (found == null) flag = ErrorType.MISSING_LEFT_PARENTHESES;
    			else if (!bracketMatch(c, found)) flag = ErrorType.MATCHING_ERROR;
    			break;
    		default:
    			break;
    		}
    		if (flag != ErrorType.NONE) break;
    	}
    }

    public static void main(String[] args) {
        String[] inputs = {
                "[]]()()", // not correct
                "c[d]", // correct\n" +
                "a{b[c]d}e", // correct\n" +
                "a{b(c]d}e", // not correct; ] doesn't match (\n" +
                "a[b{c}d]e}", // not correct; nothing matches final }\n" +
                "a{b(c) ", // // not correct; Nothing matches opening {
        };

        for (String input : inputs) {
            BracketChecker checker = new BracketChecker(input);
            System.out.println("checking: " + input);
            checker.check();
        }
    }
}