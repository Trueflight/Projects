/*
 * Matthew Goodrich
 * COP3538
 * Project 5
 */
package Project5;

/**
 * Class containing all the primary methods for working with a hash table
 * @author Matthew
 */
public class HashTable implements Hashable {

    private Node[] hashTable;
    int size;

    public HashTable(int length) {
        size = length;
        hashTable = new Node[size];
    }

    /**
     * Displays the items stored in the hash table.
     * @param ascending True if items ordered from smallest to largest; otherwise, from largest to smallest.
     */
    @Override
    public void display(boolean ascending) {
        Node temp;

        for (int x = 0; x < hashTable.length; x++) {
            System.out.println("Index: " + x);
            if ((temp = hashTable[x]) == null) {
                System.out.println("This index is empty");
            } else {
                while (temp != null) {
                    System.out.println(temp.getState().toString());
                    temp = temp.getNext();
                }
            }
            System.out.println(" ");
        }
    }

    /**
     * Determines the hash index based on the input value (a state name).
     * @param name The value to calculate the hash index.
     * @return the hash index based on the input value.
     */
    @Override
    public int getHash(String name) {
        int x = 0;
        int sum = 0;
        while (x < name.length()) {
            sum += name.charAt(x);
            x++;
        }
        int result = sum % size;
        return result;
    }

    /**
     * Adds an item to the appropriate location of the hash table.
     * @param item The item to add.
     */
    @Override
    public void insert(Node item) {
        int hash = getHash(item.getState().getName());
        Node temp = hashTable[hash];

        if (isEmpty(hash)) { // Check to see if the hashed index of the array is empty
            hashTable[hash] = item;
        } else if (item.getState().getName().compareTo(temp.getState().getName()) < 0) { // Should the value be inserted at the front
            item.setNext(temp);
            hashTable[hash] = item;
        } else {
            temp = findNode(hashTable[hash], item.getState().getName());
            item.setNext(temp.getNext());
            temp.setNext(item);
        }
    }

    // Recursive method to locate correct location to insert new value
    private Node findNode(Node node, String name) {
        Node temp = node;

        if (temp.getNext() != null && name.compareTo(temp.getNext().getState().getName()) > 0) {
            temp = findNode(temp.getNext(), name);
        }
        return temp;
    }

    /**
     * Determines if the specified index of the hash table is empty.
     * @param index The index of the hash table to test.
     * @return True if the specified index of the hash table is empty; otherwise, false.
     */
    @Override
    public boolean isEmpty(int index) {
        return hashTable[index] == null;
    }

    /**
     * Locates the specified state in the hash table.
     * @param name The state name to find in the hash table.
     * @return A message indicating whether the state was found in the hash table, and the hash and position of the state, if found.
     */
    @Override
    public String findState(String name) {
        int hash = getHash(name); // Calculate the hash for the input value 
        String message;
        int pos = 1;
        Node temp = hashTable[hash]; // Go to the first node in the appropriate linked list

        // Traverse the linked list until the value is found or the end of the list is reached
        while (temp != null && name.compareTo(temp.getState().getName()) != 0) {
            temp = temp.getNext();
            pos++;
        }
        if (temp == null) { // Was the end of the list reached
            message = String.format("%s was not found", name);
        } else {
            message = String.format("%s is located at Hash: %d Position: %d", name, hash, pos);
        }
        return message;
    }

    /**
     * Removes the first node from the hashTable
     * @return The item that was removed.
     */
    @Override
    public Node remove() {
        Node temp;
        int x = 0;
        while (isEmpty(x)) {
            x++;
        }
        temp = hashTable[x];
        hashTable[x] = temp.getNext(); 

        return temp;
    }
}
