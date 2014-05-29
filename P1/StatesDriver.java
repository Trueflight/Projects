/* 
 * Author: Matthew Goodrich
 * State Manager
 * Subject: COP3538 
 * Project 1 
 * Creates an array of state objects and preforms various actions with it.
 * Due 2/2/2013
 */
package Project1;


import java.io.*;

/**
 * Primary driver class for project 1. Contains the main method, and two methods for reading in the States.Search and States.Input files.
 *
 * @author Matthew Goodrich
 */
public class StatesDriver {

    // Constants for use in String.format
    private static final String HEADER = "%-15s %-15s %-5s %-10s %-18s %-1s";
    private static final String SEARCH_HEADER = "%-15s %-5s %-15s %-8s";

    public static void main(String[] args) throws IOException {
        String[] searchData = writeSearchData();
        StateCollection statesC = writeStateData();

        System.out.println("Matthew Goodrich \n" + "Project 1 \n");
        System.out.println(String.format(HEADER, "Name", "Capital", "Abbr", "Population", "Region", "Region #"));
        statesC.display(); // displays the StateCollection's array
        statesC.sort(); // calls the insertion sort
        System.out.println("\n" + String.format(HEADER, "Name", "Capital", "Abbr", "Population", "Region", "Region #"));
        statesC.display();
        System.out.println("The shift count for the sort is: " + statesC.getShiftCount());
        System.out.println("\n" + "Linear Search Results:" + "\n");
        System.out.println(String.format(SEARCH_HEADER, "Search String", "Found", " Not Found", "# Probes"));

        // For loop that runs a linear search of StateCollection for each element of dataArray
        for (int i = 0; i < searchData.length; i++) {
            int found = statesC.linearSearch(searchData[i]); // returns -1 for not found and index location for found

            if (found == -1) {
                System.out.println(String.format(SEARCH_HEADER, searchData[i], " ", " X", statesC.getProbeCount()));
            } else {
                System.out.println(String.format(SEARCH_HEADER, searchData[i], "X", " ", statesC.getProbeCount()));
            }
        } 

        System.out.println("\n" + "Binary Search Results:" + "\n");
        System.out.println(String.format(SEARCH_HEADER, "Search String", "Found", " Not Found", "# Probes"));

        // For loop that runs a binary search of StateCollection for each element of dataArray
        for (int i = 0; i < searchData.length; i++) {
            int found = statesC.binarySearch(searchData[i]);

            if (found == -1) {
                System.out.println(String.format(SEARCH_HEADER, searchData[i], " ", " X", statesC.getProbeCount()));
            } else {
                System.out.println(String.format(SEARCH_HEADER, searchData[i], "X", " ", statesC.getProbeCount()));
            }
        } 

        statesC.writeToFile();
    } // end main      

    /**
     * Reads in data from States.Search and adds it to an array.
     *
     * @return The array containing each item to search for.
     */
    private static String[] writeSearchData() {

        String[] dataArrayLocal = new String[15];
        String line;
        int i = 0;

        // creates a buffered reader that reads data from States.Search and adds it to the array
        try (BufferedReader br = new BufferedReader(new FileReader("States.Search.txt"))) {
            while ((line = br.readLine()) != null) {
                dataArrayLocal[i] = line;
                i++;
            } 
            br.close();
        } 
        catch (IOException e) {
            System.out.println("Error with the file or file not found");
        }

        return dataArrayLocal;
    } 

    /**
     * Reads in data from States.Input and creates state objects with it
     *
     * @return The StateCollection object containing all the states created
     */
    private static StateCollection writeStateData() {

        String data;
        StateCollection statesCLocal = new StateCollection();

        // creates a BR that reads data from States.Input, creates a state object with each record and adds it to the collections object
        try (BufferedReader br = new BufferedReader(new FileReader("States.Input.txt"))) {
            while ((data = br.readLine()) != null) {

                State state = new State(data);
                statesCLocal.add(state);

            } 
            br.close();
        } 
        catch (IOException e) {
            System.out.println("Error with the file or file not found");
        } 

        return statesCLocal;
    } 

} 
