import java.util.ArrayList;

/**
 * MyStack is a class that stores elements using the stack guidline (LIFO: Last In, First Out)
 *
 * @param <T> the type of elements stored in the stack.
 */
public class MyStack<T> implements StackInterface<T> {
    private ArrayList<T> stack;  // Store the elements in an ArrayList
    private int maxSize;  // Maximum size of the stack

    // Constructor that sets the stack size
    public MyStack(int size) {
        stack = new ArrayList<>(size);  // Create ArrayList with the specified size
        this.maxSize = size;  // Set the max size
    }

    // Default constructor, makes a stack of size 10
    public MyStack() {
        this(10);  // Default size is 10
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();  // Check if the stack is empty
    }

    @Override
    public boolean isFull() {
        return stack.size() == maxSize;  // Check if the stack is full
    }

    @Override
    public T pop() throws StackUnderflowException {
        if (isEmpty()) {
            throw new StackUnderflowException();  // Can't pop from an empty stack
        }
        return stack.remove(stack.size() - 1);  // Remove and return the top element
    }

    @Override
    public T top() throws StackUnderflowException {
        if (isEmpty()) {
            throw new StackUnderflowException();  // Can't get top element from an empty stack
        }
        return stack.get(stack.size() - 1);  // Return the top element (without removing it)
    }

    @Override
    public int size() {
        return stack.size();  // Return the number of elements in the stack
    }

    @Override
    public boolean push(T e) throws StackOverflowException {
        if (isFull()) {
            throw new StackOverflowException();  // Can't push onto a full stack
        }
        stack.add(e);  // Add the element to the top
        return true;  // Return true to indicate successful push
    }

    @Override
    public String toString() {
        // Loop through the elements and append them to the string
        StringBuilder sb = new StringBuilder();
        for (T element : stack) {
            sb.append(element);  // Append each element to the string
        }
        return sb.toString();
    }

    @Override
    public String toString(String delimiter) {
        // Build a string with elements separated by the given delimiter
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < stack.size(); i++) {
            sb.append(stack.get(i));  // Append element
            if (i < stack.size() - 1) {
                sb.append(delimiter);  // Add delimiter between elements
            }
        }
        return sb.toString();
    }

    @Override
    public void fill(ArrayList<T> list) {
        // Clear current stack and fill it with elements from the list
        stack.clear();
        for (T element : list) {
            try {
                push(element);  // Try to push each element from the list
            } catch (StackOverflowException e) {
                e.printStackTrace();  // If stack is full, print the error
            }
        }
    }
}
