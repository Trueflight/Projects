/*
 * Matthew Goodrich
 * COP3538
 * Project 3
 */
package Project3;

import java.io.*;

/**
 * This is the driver class for Project3. It handles calling all the methods, moving the states to and from PQs and stacks, and writing to and from the various files.
 *
 * @author Matthew
 */
public class Controller {

    private PriorityQueue<State> pQ = new PriorityQueue();
    private Stack<State> stackA = new Stack();
    private Stack<State> stackD = new Stack();
    private Stack<State> stackU = new Stack();
    private Stack<State>[] sArray = new Stack[]{stackA, stackD, stackU};

    /**
     * Primary method that controls the program by calling methods.
     */
    public void execute() {
        readStateData("States.Input.txt", 'A');
        pQ.frontDisplay();
        pQ.rearDisplay();
        readStateData("States.Trans.txt", 'B');
        System.out.println("Add Stack:");
        sArray[0].display();
        System.out.println("Delete Stack:");
        sArray[1].display();
        System.out.println("Update Stack:");
        sArray[2].display();
        for (int x = 0; x < 3;) {
            stackManager(x);
            x++;
        }
        pQ.frontDisplay();
        pQ.rearDisplay();
        try {
            pQWriteToFile();
        } catch (IOException ex) {
            System.out.println("An IO error has occured while writing to a file");
        }

    }

    /**
     * Reads in data from a text file, creates state objects with it and adds it to the PQ or one of the stacks, depending on the file being read from
     *
     * @param fileName the name of the file to read from
     * @param format char that denotes which format the file is in
     */
    private void readStateData(String fileName, char format) {

        boolean delete;
        String data, temp;
        State state;
        int loc = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            // while loop that adds each created state to the stack

            while ((data = br.readLine()) != null) {
                delete = false;
                if (format == 'A') {
                    state = new State(data, delete);
                    pQ.insert(state);
                } else {
                    temp = data.substring(0, 1); // gets the first char of the file
                    if (temp.compareTo("A") == 0) {
                        loc = 0;
                    } else if (temp.compareTo("D") == 0) {
                        delete = true;
                        loc = 1;
                    } else if (temp.compareTo("U") == 0) {
                        loc = 2;
                    }
                    state = new State(data.substring(1), delete);
                    sArray[loc].push(state);
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
     * Pops the items in each stack and either adds, removes, or both the item from the pQ
     *
     * @param x denotes the stack to be worked with
     */
    private void stackManager(int x) {
        int loc = x;
        State temp;
        while (!sArray[loc].isEmpty()) {
            temp = sArray[loc].pop();

            if (loc == 0) { // the add stack
                pQ.insert(temp);
            } else if (loc == 1) { // the delete stack
                pQ.remove(temp.getName());

            } else if (loc == 2) { // the update stack
                pQ.remove(temp.getName());
                pQ.insert(temp);
            }
        }
    }

    /**
     * Removes each state item from the PQ and writes the toString to the file States.Output.txt
     *
     * @throws IOException
     */
    private void pQWriteToFile() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("States.Output.txt")); // creates the buffered writer
        State state;

        while (!pQ.isEmpty()) {
            state = pQ.remove();
            bw.write(state.toString());
            bw.newLine();
            bw.flush();
        }
    }
}
