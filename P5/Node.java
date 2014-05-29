/*
 * Matthew Goodrich
 * COP3538
 * Project 5
 */
package Project5;

/**
 * Class containing the necessary methods for a linked list node
 * @author Matthew
 */
public class Node implements Linkable {

    private Node next;
    private State state;

    public Node(State state) {
        this.state = state;
    }

    /**
     * Returns the state object stored in the current node.
     * @return the state object stored in the current node.
     */
    @Override
    public State getState() {
        return state;
    }

    /**
     * Returns the node to the right of the current node.
     * @return the node to the right of the current node.
     */
    @Override
    public Node getNext() {
        return next;
    }

    /**
     * Sets the next pointer to the node to the right of the current node.
     * @param node the node to assign to the next pointer.
     */
    @Override
    public void setNext(Node node) {
        next = node;
    }

}
