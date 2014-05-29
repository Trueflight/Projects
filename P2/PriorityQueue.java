/*
 * Matthew Goodrich
 * COP3538
 * Project 2
 */
package Project2;

/**
 * A Priority Queue built to contain states, provides standard PQ methods plus a display method
 *
 * @author Matthew
 */
public class PriorityQueue implements Queueable {

    private int maxSize;
    private int numElems = 0;
    private State[] states;

    public PriorityQueue(int size) {
        this.maxSize = size;
        states = new State[maxSize];
    }

    /**
     * Displays the States objects stored in the stack.
     */
    @Override
    public void display() {
        for (int i = numElems - 1; i >= 0; i--) {
            System.out.println(states[i].toString());
        }
    }

    /**
     * Adds a State object to the appropriate location of the queue.
     *
     * Note: The isFull method should be called first to prevent errors.
     *
     * @param incState The State object to add.
     */
    @Override
    public void insert(State incState) {
        int i;
        if (numElems == 0) {
            states[numElems++] = incState;
        } else {
            i = numElems;
            // sorts the states by their population, smallest first
            while (i > 0 && incState.getPop() > states[i - 1].getPop()) {
                states[i] = states[i - 1];
                i--;
            }
            states[i] = incState;
            numElems++;

        }
    }

    /**
     * Removes a State object from the front of the queue.
     *
     * Note: The isEmpty method should be called first to prevent errors.
     *
     * @return The State object that was removed.
     */
    @Override
    public State remove() {
        return states[--numElems];
    }

    /**
     * Determines if the queue is empty.
     *
     * @return True if the queue is empty; otherwise, false.
     */
    @Override
    public boolean isEmpty() {
        return numElems == 0;
    }

    /**
     * Determines if the queue is full.
     *
     * @return True if the queue is full; otherwise, false.
     */
    @Override
    public boolean isFull() {
        return numElems == maxSize;
    }

}
