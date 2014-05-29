/*
 * Matthew Goodrich
 * COP3538
 * Project 5
 */
package Project5;

import java.io.*;

/**
 *
 * @author Matthew
 */
public class Controller {

    String[] queryA = new String[10];
    HashTable table = new HashTable(10);
    private static final String ROW = "%-15s %-15s %-5s %-10s %-18s %-1s";
    
    
    /**
     * Primary control method for entire program
     */
    public void execute() {
        readStateData("States.Input.txt", false);
        System.out.println("Hash Table List:");
        System.out.println(String.format(ROW, "Name", "Capital", "Abbr", "Pop", "Region", "Region #"));
        table.display(true);
        readStateData("States.Query.txt", true);
        System.out.println("Search Results: ");
        stateSearch();
        writeToFile();
    }
    
    /**
     * Searches the hash table for specific states and print out whether or not they are found.
     */
    private void stateSearch() {
        for (int x = 0; x < queryA.length; x++) {
            System.out.println(table.findState(queryA[x]));
        }
    }
    
    /**
     * 
     * @param fileName Name of the file being read from.
     * @param Query boolean to determine if the file contains search targets.
     */
    private void readStateData(String fileName, boolean Query) {
        String data;
        Node node;
        int x = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            if (Query) {
                while ((data = br.readLine()) != null) {
                    queryA[x] = data;
                    x++;
                }
            } else {
                while ((data = br.readLine()) != null) {
                    if (!data.contains(",")) {
                        node = new Node(new State(data));
                    } else {
                        node = new Node(new State(data.split(",")));
                    }
                    table.insert(node);
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("State File not found");
        } catch (IOException e) {
            System.out.println("State File IO error");
        } catch (Exception e) {
            System.out.println("The state file was found, but an error occured.");
        }
    }

    /**
     * Sends the contents of the hash table to a file.
     */
    private void writeToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("States.Output.txt"))) {
            while (!table.isEmpty(9)) {

                bw.write(table.remove().getState().toString());
                bw.newLine();

                bw.flush();
            }
            bw.close();
        } catch (IOException ex) {
            System.out.println("Error writing the data to a file");
        }
    }
}
