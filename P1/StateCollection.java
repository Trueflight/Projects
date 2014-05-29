/*
 Matthew Goodrich 
 COP3538
 Project 1
 */
package Project1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Creates and provides methods for managing a 100 element array of State objects
 *
 * @author Matthew
 */
public class StateCollection {

    private State[] states = new State[100];
    private int nElems = 0, probes = 0, shifts = 0;

    /**
     * Adds an item to the array
     *
     * @param stateitem The state being added.
     */
    public void add(State stateitem) {
        states[nElems] = stateitem;
        nElems++;
    }

    /**
     * Gets the number of items in the array.
     *
     * @return The number of elements.
     */
    public int getElemCount() {
        return nElems;
    }

    /**
     * Gets the number of probes made.
     *
     * @return The number of probes.
     */
    public int getProbeCount() {
        return probes;
    }

    /**
     * Gets the number of shifts made.
     *
     * @return The number of shifts.
     */
    public int getShiftCount() {
        return shifts;
    }

    /**
     * Displays all elements in the array.
     */
    public void display() {
        for (int i = 0; i < nElems; i++) {
            System.out.println(states[i]);
        }
    }

    /**
     * Prints the data for each state to the file States.Data.Out
     *
     * @throws IOException
     */
    public void writeToFile() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("States.Data.Out.txt")); // creates the buffered writer

        for (int i = 0; i < nElems; i++) {
            bw.write(states[i].toString());
            bw.newLine();
            bw.flush();
        }
    }

    /**
     * Sorts the entire array using an insertion sort
     */
    public void sort() {

        for (int i = 0; i < nElems; i++) {
            State temp = states[i];
            int j = i;
            // Loops through the array and compares each state name
            while (j > 0 && (states[j - 1].getName().compareTo(temp.getName()) > 0)) {
                states[j] = states[j - 1];
                shifts++;
                --j;
            }
            states[j] = temp;
        }
    }

    /**
     * Searches the array using the linear Search algorithm
     *
     * @param targetName The name of the target state
     * @return The index variable, -1 if not found, index location of state if found
     */
    public int linearSearch(String targetName) {
        int index = -1;
        probes = 0;
        // Loop through the array until the value is found or end of array is reached
        for (int i = 0; i < nElems && index == -1; i++) {
            probes++;
            if (states[i].getName().compareTo(targetName) == 0) { // compares the name of the state with the target
                index = i;
            }
        }
        return index;
    }

    /**
     * Searches the states array using a binary search algorithm
     *
     * @param targetName The name of the target state
     * @return The index variable, -1 if not found, index location of state if found
     */
    public int binarySearch(String targetName) {
        int index = -1; 
        int lower = 0;
        int upper = nElems - 1;
        int current; 
        probes = 0;

        // Loop until the lower upper indexes switch sides or the value is found
        while (lower <= upper && index == -1) {
            current = (lower + upper) / 2; // Find the mid-point between lower and upper
            probes++;

            if (states[current].getName().compareTo(targetName) == 0) { // Compare value in the array to the search term
                index = current; // Set index variable to the array index with the value
            } 
            else { // If the value did not match the search term
                if (states[current].getName().compareTo(targetName) < 0) { // Select the correct section of the array
                    lower = current + 1;
                } 
                else {
                    upper = current - 1;
                }
            }
        }
        return index;
    }

}
