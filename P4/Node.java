/*
 * Matthew Goodrich
 * COP3538
 * Project 4
 */
package Project4;

/**
 * Class containing the necessary methods for a linked list node
 * @author Matthew
 */
public class Node implements Linkable {

    private Node leftchild, rightchild, next, previous;
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
     * Returns the current node's left child node.
     * @return the current node's left child node.
     */
    @Override
    public Node getLeftChild() {
        return leftchild;
    }

    /**
     * Returns the current node's right child node.
     * @return the current node's right child node.
     */
    @Override
    public Node getRightChild() {
        return rightchild;
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
     * Returns the node to the left of the current node.
     * @return the node to the left of the current node.
     */
    @Override
    public Node getPrevious() {
        return previous;
    }

    /**
     * Resets the previous and next pointers of the current node.
     */
    @Override
    public void reset() {
        rightchild = null;
        leftchild = null;
    }

    /**
     * Sets the current node's left child node.
     * @param node
     */
    @Override
    public void setLeftChild(Node node) {
        leftchild = node;
    }

    /**
     * Sets the current node's right child node.
     * @param node
     */
    @Override
    public void setRightChild(Node node) {
        rightchild = node;
    }

    /**
     * Sets the next pointer to the node to the right of the current node.
     * @param node the node to assign to the next pointer.
     */
    @Override
    public void setNext(Node node) {
        next = node;
    }

    /**
     * Sets the previous pointer to the node to the left of the current node.
     * @param node the node to assign to the previous pointer.
     */
    @Override
    public void setPrevious(Node node) {
        previous = node;
    }
}
