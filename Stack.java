import java.util.ArrayList;
import java.util.List;

public class Stack<T> {
    private List<T> items;
    
    public Stack() {
        items = new ArrayList<>();
    }
    
    public void push(T item) {
        // Push an item onto the stack
        items.add(item);
    }
    
    public T pop() {
        // Remove and return the top item from the stack. Throw an error if the stack is empty
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Pop from empty stack");
        }
        return items.remove(items.size() - 1);
    }
    
    public T top() {
        // Return the top item from the stack without removing it. Throw an error if the stack is empty
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Top from empty stack");
        }
        return items.get(items.size() - 1);
    }
    
    public boolean isEmpty() {
        // Check if the stack is empty
        return items.size() == 0;
    }
    
    public int size() {
        // Return the number of items in the stack
        return items.size();
    }
}