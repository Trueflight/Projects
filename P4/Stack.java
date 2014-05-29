/*
 * Matthew Goodrich
 * COP3538
 * Project 3
 */
package Project4;

/**
 * A stack built to contain singly-linked list states that contains standard stack methods plus a display
 * @author Matthew
 */
public class Stack implements Stackable {

    private Node top;

    /**
     * Displays the items stored in the stack.
     */
    @Override
    public void display() {
        Node temp = top;
        while (temp != null) {
            System.out.println(temp.getState().toString());
            temp = temp.getNext();
        }
        System.out.println("\n");
    }

    /**
     * Determines if the stack is empty.
     * @return True if the stack is empty; otherwise, false.
     */
    @Override
    public boolean isEmpty() {
        return top == null;
    }

    /**
     * Determines if the stack is full.
     * @return Always false because linked lists cannot be full
     */
    @Override
    public boolean isFull() {
        return false;
    }

    /**
     * Removes a item from the top of the stack.
     * @return The item that was removed.
     */
    @Override
    public Node pop() {
        Node temp = top;
        top = top.getNext();
        return temp;
    }

    /**
     * Adds a item to the top of the stack.
     * @param item The item to add.
     */
    @Override
    public void push(Node item) {
        item.setNext(top);
        top = item;
    }
}
