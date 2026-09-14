package classActivities;
//A custom queue implemented using a circular array structure
public class CustomQueue<E> {
// Fields to manage queue data and elements.
// Array to store the elements of the queue.
private E[] data;
// The number of elements currently in the queue, initialized at 0.
private int size = 0;
// The index of the first queue element, initialized at 0.
private int first = 0;
// Default initial capacity of queue.
private static final int DEFAULT_CAPACITY = 5;
// Constructor of this class.
// It initializes an array with the given capacity to store queue elements.
public CustomQueue(int capacity) {  // O(1) Time Complexity, because it performs the single
   // task of creating an array with the specified capacity.
   // The reason why this array is created with type Object and later cast to
   // generic type E[] is because in Java, there is no generic arrays.
   data = (E[]) new Object[capacity];
}
public CustomQueue() {  // O(1) Time Complexity, because it only performs creating an array,
   // which has a fixed number of operation.
   this(DEFAULT_CAPACITY);
}
// Checks if the queue is empty or not, if so, returns true.
public boolean isEmpty() { // O(1) Time Complexity, because checking variable size is
   // a direct comparison.
   return size == 0;
}
// Checks the size of the queue.
public int size() { // O(1) Time Complexity, because returning variable size is a
   // simple retrieval.
   return size;
}

// Returns the first element of the queue without removing it.
public E first() { // O(1) Time Complexity
   // If empty, returns null.
   if (isEmpty())
      return null;
   // Return the element at the head of the queue.
   return data[first];
}
// Adds the given element to the end of the queue.
public void enqueue(E e) { // O(1) Amortized Time Complexity, because calculating the tail (end) of
   // the queue and adding a new element takes fixed number of operations.
   // Though ensureCapacity() method has O(n) complexity it is called when
   // resizing is needed, so it is not certain if the method would use it.
   // Invoke this method to double the internal array size only when it is full.
   ensureCapacity();
   // Compute the index at the end by circular indexing.
   int end = (first + size) % data.length;
   // Add the given element to the index at the end of the queue.
   data[end] = e;
   // Increase size by 1.
   size++;
}
// Removes the first element of the queue and returns it.
public E dequeue() { // O(1) Time Complexity, because what it essentially does is checking if
   // the queue is empty, retrieving first element and updating the index with the
   // element after, which all takes a fixed number of operations.
   // If empty, returns null.
   if (isEmpty())
      return null;
   // Store the first element of the queue to return it at the end of the method.
   E reFirst = data[first];
   // Remove the first element of the queue.
   // Dereference the removed element to help garbage collection.
   data[first] = null;
   // Replace the index first using circular indexing.
   first = (first+1) % data.length;
   // Decrease the size by one.
   size--;
   // Return the removed first element of the queue.
   return reFirst;
}
// Creates a new array with twice the original capacity if the internal array is full.
private void ensureCapacity() { // O(n) Time Complexity, because copying all n elements from the old
   // array to the new array requires iterating through each element.
   // Ensures to use this method only when the queue is full.
   if (size < data.length)
      return;
   // Creates a new array twice the originals size
   E[] newArray = (E[]) new Object[2 * data.length];
   // Store the old elements in the new array circularly
   for (int i = 0; i < data.length; i++)
      newArray[i] = data[(first + i) % data.length];
   // Replace the old array with the new one.
   data = newArray;
   // Resetting the variable first to 0.
   first = 0;
}
}