/*
 * Matthew Goodrich
 * COP3538
 * Project 3
 */
package Project3;

/**
 * Class containing the necessary methods for a linked list node
 * @author Matthew
 * @param <T>
 */
public class Node<T extends State> implements Linkable<T> {

    private T next, previous;

    /**
     * Returns the node to the right of the current node.
     *
     * @return the node to the right of the current node.
     */
    public T getNext() {
        return next;
    }

    /**
     * Returns the node to the left of the current node.
     *
     * @return the node to the left of the current node.
     */
    public T getPrevious() {
        return previous;
    }

    /**
     * Resets the previous and next pointers of the current node.
     */
    public void reset() {
        next = null;
        previous = null;
    }

    /**
     * Returns the node containing the specified name.
     *
     * @param nodeName The name to search for.
     * @param node The node to start searching from.
     * @return the node containing the specified name.
     */
    public T searchNodes(String nodeName, T node) {
        return null;
    }

    /**
     * Sets the next pointer to the node to the right of the current node.
     *
     * @param node the node to assign to the next pointer.
     */
    public void setNext(T node) {
        next = node;
    }

    /**
     * Sets the previous pointer to the node to the left of the current node.
     *
     * @param node the node to assign to the previous pointer.
     */
    public void setPrevious(T node) {
        previous = node;
    }
}
