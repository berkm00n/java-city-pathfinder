package classActivities;

//A generic class that implements a resizable array based stack.
public class CustomStack<E> implements Cloneable{
 // Array that stores the elements of the stack.
 private E[] data;
 // The number of elements currently in the stack.
 private int size = 0;
 // Default initial capacity of stack.
 private static final int DEFAULT_CAPACITY = 5;
 // The constructor of this class.
 // It initializes an array with the default capacity to store stack elements.
 public CustomStack() { // O(1) Time Complexity, because it only performs creating an array,
                  // which has a fixed number of operation.
     this(DEFAULT_CAPACITY);
 }
 // Constructor that creates an empty stack with a specified initial capacity while ensuring
 // that the capacity is no less than default capacity.
 public CustomStack(int capacity) { // O(1) Time Complexity, because essentially only an array is created,
                              // which has a fixed number of operations.
     if (capacity < DEFAULT_CAPACITY)
         capacity = DEFAULT_CAPACITY;
     data = (E[]) new Object[capacity];
 }
 
 public int size() {
	 return size; //Returning the size of the stack, used in deptFirst method
 }
 //Checks if the stack is empty or not, if so, returns true.
 public boolean isEmpty() { // O(1) Time Complexity, because it only does a direct comparison by
                            // checking if the size is zero.
     return size == 0;
 }
 // Returns the topmost element of the stack without removing it.
 public E peek() { // O(1) Time Complexity, because returning the top element without removing it
                   // takes fixed number of operations.
     // If empty, returns null.
     if (isEmpty())
         return null;
     // Return the element at the end (top) of the stack.
     return data[size - 1];
 }
 // Removes the topmost element of the stack and returns it.
 public E pop() { // O(1) Amortized Time Complexity, because removing the top element and updating
                  // the size has fixed number of operations. Resizing occurs when the number of
                  // elements falls below one-quarter of the array's capacity. Since it is
                  // infrequent, the overall cost of the method remains fixed, hence amortized O(1).
     // If empty, returns null.
     if (isEmpty())
         return null;
     // Store the element to be removed to return it after it is removed.
     E top = data[size - 1];
     // Reduce the size and remove the top element by dereferencing it.
     data[--size] = null;
     // when only less than a quarter of the data array is used
     // If the stack is not empty and less than a quarter of the array is used, reduce
     // the array size by half.
     if (size < data.length / 4.0 && data.length > DEFAULT_CAPACITY)
         resize(data.length / 2); // by using the resize method
     // Return the top element that was stored previously.
     return top;
 }
 // Adds the given element to the end of the stack.
 public void push(E e) { // O(1) Amortized Time Complexity, because adding an element and updating
                         // the size has fixed number of operations. Resizing occurs when the
                         // array is full and the capacity needs to be doubled. Since it happens
                         // infrequently, the overall cost of the method remains fixed, hence
                         // amortized O(1).
     // If the array is full, doubles the capacity of it.
     if (size == data.length)
         resize(2 * data.length);
     // Adds the given element after the last element and increments the size.
     data[size++] = e;
 }
 // Method that adjusts the array size to the specified size.
 // The given size can't be smaller than default size.
 private void resize(int capacity) { // O(n) Time Complexity, because copying all n elements to a new
                                     // array of a specified capacity requires iterating through each
                                     // element.
     // If the given capacity iss lower than the default capacity, return.
     if (capacity < DEFAULT_CAPACITY)
         return;
     
     
     // Create a new array with the given capacity and then copy existing elements into it.
     E[] newArray = (E[]) new Object[capacity];
     System.arraycopy(data, 0, newArray, 0, size);
     // Set the newArray as the original one.
     data = newArray;
 }
 // Returns a string representation of the stack contents.
 @Override
 public String toString() { // O(n) Time Complexity, because  it iterates through all n elements in
                            // the stack to create a string.
     // If empty, return "[]".
     if (isEmpty())
         return "[]";
     // Initialize the string with "[" to mark the beginning of the elements.
     String str = "[";
     // Iterate through the array to add elements from top to bottom.
     for (int i = size - 1; i >= 0; i--) {
         // Append the string form of the current element.
         str = str + data[i];
         // If it's not the last element, append "," to string.
         if (i != 0)
             // Append "]" to string to show that it's the end of the elements.
             str = str + ", ";
         else
             str = str + "]";
     }
     // Return the final string.
     return str;
 }
 // This method creates a copy of the current stack, including a new array to store elements.
 @Override
 public CustomStack<E> clone() { // O(n) Time Complexity, because creating a new copy of an array,
                                 // requires all n elements to be duplicated into the new array.
     try {
         // Perform a shallow copy of the stack object itself.
         CustomStack<E> cloned = (CustomStack<E>) super.clone();
         // Create a new copy of the data array so the clone has its own array reference.
         cloned.data = data.clone();
         // Return the fully cloned stack.
         return cloned;
     }
     // Catch the CloneNotSupportedException, which shouldn't occur because we implement Cloneable.
     catch (CloneNotSupportedException e) {
         // Throw an AssertionError to indicate an unexpected cloning error.
         throw new AssertionError("Cloning failed, although Cloneable is implemented");
     }
 }
 
 public void clear() {
	    // Set all elements to null to help garbage collection
	    for (int i = 0; i < size; i++) {
	        data[i] = null;
	    }
	    
	    // Reset the size of the stack to 0
	    size = 0;
	}
 
 
}
