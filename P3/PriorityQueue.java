/*
 * Matthew Goodrich
 * COP3538
 * Project 3
 */
package Project3;

/**
 * A semi generic PQ built to contain doubly-linked list states that contains standard PQ methods plus a display
 *
 * @author Matthew
 * @param <T>
 */
public class PriorityQueue<T extends State> implements Queueable<T> {

    private T front, rear, item;

    /**
     * Unused Method, use front or rear Display instead
     */
    @Override
    public void display() {
    }

    /**
     * Displays the items queue from front to rear.
     */
    @Override
    public void frontDisplay() {
        T temp = front;
        System.out.println("Linked List from Front to Rear:");
        while (temp != null) {
            System.out.println(temp.toString());
            temp = (T) temp.getNext();
        }
        System.out.println("\n");
    }

    /**
     * Displays the items in the queue from rear to front.
     */
    @Override
    public void rearDisplay() {
        T temp = rear;
        System.out.println("Linked List from Rear to Front:");
        while (temp != null) {
            System.out.println(temp.toString());
            temp = (T) temp.getPrevious();
        }
        System.out.println("\n \n");
    }

    /**
     * Adds an item to the appropriate location of the queue.
     *
     * Note: The isFull method should be called first to prevent errors.
     *
     * @param item The item to add.
     */
    @Override
    public void insert(T item) {
        T current = front;
        this.item = item;
        T temp = null;
        if (front == null) {
            front = item;
            rear = item;

        } else {
            while ((current != null && item.getName().compareTo(current.getName()) > 0)) {  // traverses the linked list comparing names
                temp = current;
                current = (T) current.getNext();
            }
            item.setNext(current);
            item.setPrevious(temp);
            if (item.getNext() != null) {
                item.getNext().setPrevious(item);
            }
            if (temp != null) {
                item.getPrevious().setNext(item);
            }

            if (item.getPrevious() == null) {
                front = item;
            } else if (item.getNext() == null) {
                rear = item;
            }
        }
    }

    /**
     * Determines if the queue is empty.
     *
     * @return True if the queue is empty; otherwise, false.
     */
    @Override
    public boolean isEmpty() {
        return front == null;

    }

    /**
     * Determines if the queue is full.
     *
     * @return always false because linked list cannot be full
     */
    @Override
    public boolean isFull() {
        return false;
    }

    /**
     * Removes an item from the front of the queue.
     *
     * Note: The isEmpty method should be called first to prevent errors.
     *
     * @return The item that was removed.
     */
    @Override
    public T remove() {
        T temp = front;
        front = (T) front.getNext();

        if (front != null) {
            front.setPrevious(null);
        }
        temp.reset();
        return temp;
    }

    /**
     * Removes an item with the specified name from the queue.
     *
     * Note: The isEmpty method should be called first to prevent errors.
     *
     * @param itemName the name of the state being removed
     * @return The item that was removed.
     */
    @Override
    public T remove(String itemName) {
        T current = front;
        T target = null;
        int found = 0;
        while (current != null && found != 1) {
            if ((itemName.compareTo(current.getName()) == 0)) {
                target = current;
                current = (T) current.getNext();

                if (current != null) {
                    current.setPrevious(target.getPrevious());
                } else {
                    rear = (T) target.getPrevious();
                }
                if (target != front) {
                    target.getPrevious().setNext(current);
                } else {
                    front = (T) target.getNext();
                }
                found = 1;

            } else {
                current = (T) current.getNext();
            }

        }
        return target;
    }
}
