/*
 * Matthew Goodrich
 * COP3538
 * Project 2
 */
package Project2;

/**
 * A stack built to contain states, provides standard stack methods plus a display method
 *
 * @author Matthew
 */
public class Stack implements Stackable {

    private int maxSize;
    private int top = -1;
    private State[] statesA;

    public Stack(int size) {

        this.maxSize = size;
        statesA = new State[maxSize];
    }

    /**
     * Displays the States objects stored in the stack.
     */
    @Override
    public void display() {

        for (int i = 0; i <= top; i++) {
            System.out.println(statesA[i]);
        }
        System.out.println(" ");
    }

    /**
     * Determines if the stack is empty.
     *
     * @return True if the stack is empty; otherwise, false.
     */
    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * Determines if the stack is full.
     *
     * @return True if the stack is full; otherwise, false.
     */
    @Override
    public boolean isFull() {
        return top == maxSize - 1;
    }

    /**
     * Removes a State object from the top of the stack.
     *
     * @return The State object that was removed.
     */
    @Override
    public State pop() {
        return statesA[top--];
    }

    /**
     * Adds a State object to the top of the stack.
     *
     * @param state The State object to add.
     */
    @Override
    public void push(State state) {
        statesA[++top] = state;

    }
}
