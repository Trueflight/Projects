/*
 * CNT 4504 Project 1
 * Bassam Matloob, Cristina Pollidan, Matthew Goodrich, Stephanie McMahon
 * Last Edited: 5/28/2014
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Class containing all the methods for creating and controlling the user interface
 */
public class Interface {

    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private String choice, hostName;
    private int clientCount;
    private boolean quit;

    /**
     * Prompts the user to select from a menu of 7 choices by typing in the numbers 1-7
     */
    public void choice() {
        boolean error;
        int choice2 = 0;

        System.out.println(" 1) Date and Time \n 2) Uptime \n 3) Memory Use \n"
                + " 4) NetStat \n 5) Current Users \n 6) Running Processes \n 7) Quit");
        System.out.println("Please enter the number of the action you wish to take:");
        do {
            error = false; // prevents the loop from being infinite
            try {
                choice = br.readLine();
                choice2 = Integer.parseInt(choice);
            } catch (IOException ex) {
                System.out.println("An error has occured, please input numbers 1-7");
                error = true;
            } catch (NumberFormatException e) {
                error = true;
            }

            if (choice2 == 7) {
                quit = true;

            } else if (choice2 < 1 || choice2 > 6) { // makes sure that the user inputs numbers 1-7
                System.out.println("That was not a valid input, please input numbers 1-7");
                error = true;
            }
        } while (error == true);
    }

    /**
     * Prompts the user for an integer that is used to determine how many clients the program should simulate
     */
    public void clientCount() {
        boolean error;

        System.out.println("Please input the number of clients you wish to create");
        do {
            error = false; // prevents the loop from being infinite
            try {
                clientCount = Integer.parseInt(br.readLine());
            } catch (NumberFormatException | IOException ex) {
                System.out.println("That was not a valid input, please input an integer");
                error = true;
            }

        } while (error == true);
    }

    /**
     * Returns the hostName
     * @return the hostName
     */
    public String getHostName() {
        return hostName;
    }

    /**
     * Returns the user's menu choice
     * @return The user's choice
     */
    public String getChoice() {
        return choice;
    }

    /**
     * Returns clientCount
     * @return the number of clients the user wants to make
     */
    public int getclientCount() {
        return clientCount;
    }

    /**
     * Returns whether the user wants to exit the program or not
     * @return boolean that is based on user input
     */
    public boolean quit() {
        return quit;
    }
}
