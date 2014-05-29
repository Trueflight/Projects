/*
 * Matthew Goodrich
 * COP3538
 * Project 3
 */
package Project3;

/**
 * A semi generic stack built to contain singly-linked list states that contains standard stack methods plus a display
 *
 * @author Matthew
 * @param <T>
 */
public class Stack<T extends State> implements Stackable<T> {

    private T top;

    /**
     * Displays the items stored in the stack.
     */
    public void display() {
        T temp = top;
        while (temp != null) {
            System.out.println(temp.toString());
            temp = (T) temp.getNext();
        }
        System.out.println("\n");
    }

    /**
     * Determines if the stack is empty.
     *
     * @return True if the stack is empty; otherwise, false.
     */
    public boolean isEmpty() {
        return top == null;
    }

    /**
     * Determines if the stack is full.
     *
     * @return Always false because linked lists cannot be full
     */
    public boolean isFull() {
        return false;
    }

    /**
     * Removes a item from the top of the stack.
     *
     * @return The item that was removed.
     */
    public T pop() {
        T temp = top;
        top = (T) top.getNext();
        return temp;
    }

    /**
     * Adds a item to the top of the stack.
     *
     * @param item The item to add.
     */
    public void push(T item) {
        item.setNext(top);
        top = item;
    }
}
