package largeinteger;

import largeinteger.LLNode;

/** The LargeInteger class
 *  This class represents a large, non-negative integer using a linked list.
 *  Each node stores a single digit. The nodes represent all digits in *reverse* order:
 *  the least significant digit is the first node, and the most significant the last node.
 *  For example, 135642 is represented as 2->4->6->5->3->1 in that order.
 */
public class LargeInteger {
	private LLNode<Integer> head;	// head of the list
	private int size;				// size (i.e. number of digits)
	
	// Returns size
	public int size() { return size; }
	// Returns the linked list (used only for JUnit test purpose)
	public LLNode<Integer> getList() { return head; }
	
	public LargeInteger() {
		head = null; size = 0;
	}
	
	/** Constructor that takes a String as input and constructs the linked list.
	 *  You can assume that the input is guaranteed to be valid: i.e. every character
	 *  in the string is between '0' and '9', and the first character is never '0'
	 *  (unless '0' is the only character in the string). You can use input.charAt(i)-'0'
	 *  to convert the character at index i to the integer value of that digit.
	 *  Remember: the list nodes must be in reverse order as the characters in the string.
	 */
	public LargeInteger(String input) {
		// TODO
		if (input.equals("0")) {head = new LLNode<Integer>(0, null); size++;}
		else {
		int n = input.length();
		for (int i =0; i < n; i++) {
			LLNode<Integer> newnode = new LLNode<Integer>((input.charAt(i) - '0'), null);
			if (head == null) head = newnode;
			else {
			newnode.link = head;
			head = newnode;
			}
			size++;
		}}
			
	}
	
	/** Divide *this* large integer by 10 and return this.
	 *  Assume integer division: for example, 23/10 = 2, 8/10 = 0 and so on.
	 */
	public LargeInteger divide10() {
		// TODO
		if (size == 0)
			return null;
		else if (size == 1) {
			head.data=0;
			return this; 
		}
		else {
			
			head = head.link;
			size --;
			
			return this;
			
		}
	}
	
	/** Multiply *this* large integer by 10 and return this.
	 *  For example, 23*10 = 230, 0*10 = 0 etc.
	 */
	public LargeInteger multiply10() {
		// TODO

		if (!(size == 1 && head.data == 0)) {
			LLNode<Integer> newhead = new LLNode<Integer>(0,head);
			head=newhead;
		size++;
		}
		return this;
	}
	
	/** Returns a *new* LargeInteger object representing the sum of this large integer
	 *  and another one (given by that). Your code must correctly handle cases such as
	 *  the two input integers have different sizes (e.g. 2+1000=1002), or there is a
	 *  carry over at the highest digit (e.g. 9999+2=10001).
	 */
	public LargeInteger add(LargeInteger that) {
		LLNode<Integer> newthis = head;
		LLNode<Integer> newthat = that.head;
		//LLNode<Integer> newList = new LLNode<Integer> (null,null);
		//LLNode<Integer> newListhead = newList;
		String listafter = "";
		int a = 0;
		
		while (newthis!= null || newthat!= null) {
			
			int sum = 0;
			if (newthis == null) sum += 0;
			else sum += newthis.data;
			
			if (newthat == null) sum+= 0;
			else sum+= newthat.data;
			
			sum = sum + a;
			if (sum > 9) {
				sum = sum%10;
				a = 1;
			}
			else a = 0;
			/*if (newList.data == null) {
				newList = new LLNode<Integer>(sum,null);
				newListhead = newList;
			}
			else {
				
				newList.link = new LLNode<Integer>(sum,null);
				newList = newList.link;
			}*/
			listafter = sum + listafter;
			if (newthis!= null)newthis = newthis.link;
			if (newthat!= null)newthat = newthat.link;
			
		}
		if (a==1) listafter = 1 + listafter ;//newList.link = new LLNode<Integer>(1,null);
		//String listafter = toString(newListhead);
		return new LargeInteger(listafter);
		
	}

	/** Returns a new LargeInteger object representing the result of multiplying
	 *  this large integer with a non-negative integer x. You can assume x is either
	 *  a positive integer or 0. Hint: you can use a loop and call the 'add' method
	 *  above to accomplish the 'multiply'.
	 */
	public LargeInteger multiply(int x) {
		// TODO
		
		String a =  toString(head);
		if (x == 0) {
			LargeInteger headnew = new LargeInteger("0");
			return headnew;
		}

		else {
		LargeInteger headnew = new LargeInteger(a);
		LargeInteger headnew2 = new LargeInteger(a);
		for (int i = x; i >= 2; i--) {
			headnew2 = headnew2.add(headnew);
		}
		
		return headnew2;
		}
	}

	/** Recursive method that converts the list referenced by curr_node back to
	 *  a string representing the integer. Think about what's the base case and
	 *  what it should return. Then think about what it should return in non-base case.
	 *  Hint: refer to the 'printing a list backwards' example we covered in lectures.
	 */
	private String toString(LLNode<Integer> curr_node) {
		// TODO
		if (curr_node == null) return "";
		else
			return toString(curr_node.link) + Integer.toString(curr_node.data);
		
		
	}
	
	/** Convert this list back to a string representing the large integer.
	 *  This is a public method that jump-starts the call to the recursive method above.
	 */
	public String toString() {
		return toString(head);
	}
	
	// Recursive method to compute factorial
	public static LargeInteger factorial(int n) {
		if(n==0) return new LargeInteger("1");
		return factorial(n-1).multiply(n);
	}
	
	// Recursive method to compute power
	public static LargeInteger pow(int x, int y) {
		if(y==0) return new LargeInteger("1");
		return pow(x, y-1).multiply(x);
	}
}
