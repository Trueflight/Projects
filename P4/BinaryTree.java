/*
 * Matthew Goodrich
 * COP3538
 * Project 4
 */
package Project4;

/**
 *
 * @author Matthew
 */
public class BinaryTree implements Treeable {

    Node root;

    /**
     * Removes an item with the specified population from the tree. Note: The isEmpty method should be called first to prevent errors.
     * @param pop The value to search for to delete.
     * @return The item that was deleted.
     */
    @Override
    public Node delete(int pop) {
        boolean found = false, isLeftChild = false;
        Node parent = root, temp = root;

        while (!found && temp != null) { // Search the tree for the specified node
            if (pop == temp.getState().getPop()) { // Once the node to delete is found
                found = true;
                // Determine how many children the node has and call the appropriate delete method
                if (temp.getLeftChild() == null && temp.getRightChild() == null) {
                    deleteNoChildren(parent, isLeftChild); // See slide# 25
                } else if (temp.getLeftChild() == null || temp.getRightChild() == null) {
                    deleteSingleChild(parent, isLeftChild, temp); // See slide# 26
                } else {
                    deleteWithChildren(parent, isLeftChild, temp); // See slide# 27
                }
            } else { // Go to the next node
                parent = temp;
                if (pop < temp.getState().getPop()) {
                    isLeftChild = true;
                    temp = temp.getLeftChild();
                } else {
                    isLeftChild = false;
                    temp = temp.getRightChild();
                }
            }
        }
        return temp;
    }

    /**
     * Deletes a node that has no children
     * @param parent The node being removed
     * @param parentsLeftChild Boolean that determines if the node has a left child
     */
    private void deleteNoChildren(Node parent, boolean parentsLeftChild) {
        if (parent == root) {
            root = null;
        } else if (parentsLeftChild) {
            parent.setLeftChild(null);
        } else {
            parent.setRightChild(null);
        }
    }

    /**
     * Deletes a node with only one child
     * @param parent The parent of the node being removed
     * @param parentsLeftChild Boolean that determines if the node has a left child
     * @param temp The node being removed
     */
    private void deleteSingleChild(Node parent, boolean parentsLeftChild, Node temp) {
        if (temp.getLeftChild() == null) { // Determine which child exists
            if (parent == root) {
                root = temp.getRightChild();
            } else if (parentsLeftChild) { // Determine path from parent deleted node is on
                parent.setLeftChild(temp.getRightChild()); // Update parent’s left pointer
            } else {
                parent.setRightChild(temp.getRightChild()); // Update parent’s right pointer
            }
        } else {
            if (parent == root) {
                root = temp.getLeftChild();
            } else if (parentsLeftChild) { // Determine path from parent deleted node is on
                parent.setLeftChild(temp.getLeftChild()); // Update parent’s left pointer
            } else {
                parent.setRightChild(temp.getLeftChild()); // Update parent’s right pointer
            }
        }
    }

    /**
     * Deletes a node with multiple (two) children
     * @param parent The parent of the node being removed
     * @param parentsLeftChild Boolean that determines if the node has a left child
     */
    private void deleteWithChildren(Node parent, boolean parentsLeftChild, Node node) {
        Node lastNode = null;

        if (parentsLeftChild) { // If the node to delete is parent’s left child
            lastNode = node.getRightChild(); // Start with the node’s right child path
            while (lastNode.getLeftChild() != null) { // Find lowest left child on the path
                lastNode = lastNode.getLeftChild();
            }
            // Update the leftChild pointers of the parent node and the last node
            lastNode.setLeftChild(node.getLeftChild());
            parent.setLeftChild(node.getRightChild());
        } else { // If the node to delete is parent’s left child
            lastNode = node.getLeftChild(); // Start with the node’s left child path
            while (lastNode.getRightChild() != null) { // Find lowest right child on the path
                lastNode = lastNode.getRightChild();
            }
            // Update the rightChild pointers of the parent node and the last node
            lastNode.setRightChild(node.getRightChild());
            parent.setRightChild(node.getLeftChild());
        }
    }

    /**
     * Displays the items stored in the tree.
     * @param ascending True if items ordered from smallest to largest; otherwise, from largest to smallest.
     */
    @Override
    public void display(boolean ascending) {
        if (ascending) {
            System.out.println("State Data Ascending by Population:");
            displayTreeLNR(root); // Ascending order
            System.out.println(" ");
        } else {
            System.out.println("State Data Descending by Population:");
            displayTreeRNL(root); // Descending order
            System.out.println(" ");
        }
    }

    /**
     * Recursive method to display the tree in LNR format
     * @param node
     */
    private void displayTreeLNR(Node node) {
        if (node != null) {
            displayTreeLNR(node.getLeftChild());
            System.out.println(node.getState().toString());
            displayTreeLNR(node.getRightChild());
        }
    }

    /**
     * Recursive method to display the tree in RNL format
     * @param node
     */
    private void displayTreeRNL(Node node) {
        if (node != null) {
            displayTreeRNL(node.getRightChild());
            System.out.println(node.getState().toString());
            displayTreeRNL(node.getLeftChild());
        }
    }

    /**
     * Adds an item to the appropriate location of the tree.
     * @param item The item to add.
     */
    @Override
    public void insert(Node item) {
        boolean inserted = false;
        Node temp = root;

        if (temp == null) {
            root = item;
        } else {
            while (!inserted) {
                if (item.getState().getPop() < temp.getState().getPop()) {
                    if (temp.getLeftChild() == null) {
                        temp.setLeftChild(item);
                        inserted = true;
                    } else {
                        temp = temp.getLeftChild();
                    }
                } else {
                    if (temp.getRightChild() == null) {
                        temp.setRightChild(item);
                        inserted = true;
                    } else {
                        temp = temp.getRightChild();
                    }
                }
            }
        }
    }

    /**
     * Determines if the tree is empty.
     * @return True if the tree is empty; otherwise, false.
     */
    @Override
    public boolean isEmpty() {
        return root == null;
    }
}
