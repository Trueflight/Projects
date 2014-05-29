/*
 * Matthew Goodrich
 * COP3538
 * Project 1
 */
package Project1;

import Project2.*;

/**
 * Creates the state object and provides getName and toString methods
 */
public class State {

    private static final String ROW = "%-15s %-15s %-5s %-10s %-18s %-1s";
    private String name, capital, abbr, region;
    private int pop, regionNum;

    /**
     * Constructor that uses .substring to split up the data string and create the State object.
     *
     * @param data The data from the file sent in from StatesDriver
     */
    public State(String data) {
        name = data.substring(0, 15).trim();
        capital = data.substring(15, 30).trim();
        abbr = data.substring(30, 32).trim();
        pop = Integer.parseInt(data.substring(32, 40).trim());
        region = (data.substring(40, 55)).trim();
        regionNum = Integer.parseInt(data.substring(55).trim());
    } 

    /**
     * Returns the name of the state.
     *
     * @return The name of the state.
     */
    public String getName() {
        return name;
    } 

    /**
     * toString that returns the state's info in a formatted manner.
     *
     * @return The state's info.
     */
    @Override
    public String toString() {
        return String.format(ROW, name, capital, abbr, pop, region, regionNum);
    } 

} 

