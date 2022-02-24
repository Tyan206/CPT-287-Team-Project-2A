package infix_expression_parser;

import java.util.NoSuchElementException;

/** A linked-list based stack */
public class Linked_List_Stack<T> {
 /** A singly-linked list node */
	 private class Node {
	     // Data fields
	     T data;  // Some data stored in the node
	     Node next;  // Reference to the next node
	
	     // Constructor
	     Node(T theData) { data = theData; }
	 }
	
	 // Data fields
	 private Node top;  // Node on top of the stack
	 private int numOfItems;  // Number of elements stored in the stack
	
	 // Constructors
	
	 public Linked_List_Stack() {}  // Default constructor
	
	 public Linked_List_Stack(Linked_List_Stack<T> other) {  // Copy constructor
	     numOfItems = other.numOfItems;
	     if (numOfItems > 0) {
	         top = new Node(other.top.data);
	         Node p = top, q = other.top.next;
	         while (q != null) {
	             p.next = new Node(q.data);
	             p = p.next;
	             q = q.next;
	         }
	     }
	 }
	
	 // Methods
	
	 /** Returns the size of the stack.
	     @return: number of elements stored in the stack
	 */
	 public final int size() { return numOfItems; }  // Time complexity: O(1)
	
	 /** Tests whether the stack is empty.
	     @return: {true} if there are no elements stored in the stack; {false} otherwise
	 */
	 public final boolean isEmpty() { return size() == 0; }  // Time complexity: O(1)
	
	 /** Returns the element on top of the stack.
	     @return: value stored in the top node of the stack
	     @throws NoSuchElementException: stack is empty.
	 */
	 public T peek() {
	     if (isEmpty()) { throw new NoSuchElementException("Accessing empty stack"); }
	     return top.data;
	 }  // Time complexity: O(1)
	
	 /** Removes (and returns) the element on top of the stack.
	     @return: value (removed) stored in the top node of the stack
	     @throws NoSuchElementException: stack is empty.
	 */
	 public T pop() {
	     if (isEmpty()) { throw new NoSuchElementException("Accessing empty stack"); }
	     T toBeRemoved = top.data;
	     top = top.next;
	     numOfItems--;
	     return toBeRemoved;
	 }  // Time complexity: O(1)
	
	 /** Adds a new element onto the top of the stack.
	     @param value: new element to add to the stack
	 */
	 public void push(T value) {
	     Node newTop = new Node(value);
	     newTop.next = top;
	     top = newTop;
	     numOfItems++;
	 }
}