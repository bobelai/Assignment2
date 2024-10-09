import java.util.ArrayList;

/**
 * MyQueue is a class that stores elements using the queue principle (FIFO: First In, First Out).
 *
 * @param <T> the type of elements stored in the queue.
 */
public class MyQueue<T> implements QueueInterface<T> {
    private ArrayList<T> queue;  // Store the elements in an ArrayList
    private int maxSize;  // Maximum size of the queue

    // Constructor that sets the queue size
    public MyQueue(int size) {
        queue = new ArrayList<>(size);  // Create ArrayList with the specified size
        this.maxSize = size;  // Set the max size
    }

    // Default constructor, makes a queue of size 10
    public MyQueue() {
        this(10);  // Default size is 10
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();  // Check if the queue is empty
    }

    @Override
    public boolean isFull() {
        return queue.size() == maxSize;  // Check if the queue is full
    }

    @Override
    public T dequeue() throws QueueUnderflowException {
        if (isEmpty()) {
            throw new QueueUnderflowException();  // Can't dequeue from an empty queue
        }
        return queue.remove(0);  // Remove and return the front element
    }

    @Override
    public boolean enqueue(T e) throws QueueOverflowException {
        if (isFull()) {
            throw new QueueOverflowException();  // Can't enqueue onto a full queue
        }
        queue.add(e);  // Add the element to the end
        return true;  // Return true to indicate successful enqueue
    }

    @Override
    public int size() {
        return queue.size();  // Return the number of elements in the queue
    }

    @Override
    public String toString() {
        // Loop through the elements and append them to the string
        StringBuilder sb = new StringBuilder();
        for (T element : queue) {
            sb.append(element);  // Append each element to the string
        }
        return sb.toString();
    }

    @Override
    public String toString(String delimiter) {
        // Build a string with elements separated by the given delimiter
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < queue.size(); i++) {
            sb.append(queue.get(i));  // Append element
            if (i < queue.size() - 1) {
                sb.append(delimiter);  // Add delimiter between elements
            }
        }
        return sb.toString();
    }

    @Override
    public void fill(ArrayList<T> list) {
        // Clear current queue and fill it with elements from the list
        queue.clear();
        for (T element : list) {
            try {
                enqueue(element);  // Try to enqueue each element from the list
            } catch (QueueOverflowException e) {
                e.printStackTrace();  // If queue is full, print the error
            }
        }
    }
}
