/*
 * Matthew Goodrich
 * COP3538
 * Project 2
 */
package Project2;

import java.io.*;

/**
 * This is the driver class for Project2. It handles calling all the methods, moving the items from stacks to queues, and writing to and from the various files.
 *
 * @author Matthew
 */
public class Controller {

    private Stack stateS;
    private PriorityQueue pQ0 = new PriorityQueue(10);
    private PriorityQueue pQ1 = new PriorityQueue(10);
    private PriorityQueue pQ2 = new PriorityQueue(10);
    private PriorityQueue pQ3 = new PriorityQueue(10);
    private PriorityQueue[] pQArray = new PriorityQueue[]{pQ0, pQ1, pQ2, pQ3};

    /**
     * Primary method that controls the program by calling methods.
     */
    public void execute() {

        readStateData("States.A.Input.txt", 'A');
        stateS.display();
        stackTransfer();
        pQDisplay();
        readStateData("States.B.Input.txt", 'B');
        stackTransfer();
        pQDisplay();

        // loop that runs pQWriteToFile for each element of pQArray
        try {
            for (int x = 0; x < pQArray.length;) {
                pQWriteToFile(x);
                x++;
            }
        } catch (IOException ex) {
            System.out.println("An IO error has occured while writing to a file");
        }
    }

    /**
     * Reads in data from a text file, creates state objects with it and adds the objects to a stack.
     *
     * @param fileName the name of the file to read from
     * @param format char that denotes which format the file is in
     */
    private void readStateData(String fileName, char format) {

        stateS = new Stack(50);
        String data;
        State state;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            // while loop that adds each created state to the stack

            while ((data = br.readLine()) != null) {
                if (format == 'A') {
                    state = new State(data);

                } else {
                    String[] data2 = data.split(",");
                    state = new State(data2);
                }
                if (!stateS.isFull()) {
                    stateS.push(state);
                } else {
                    System.out.println("Could not push " + state.getName() + " because the stack was full");
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
     * Pops each object in stateS and places it in the appropriate pQ If the pQ is full it removes an item before adding the new one.
     */
    private void stackTransfer() {
        int x;
        State removed;

        while (!stateS.isEmpty()) {
            State state = stateS.pop();
            x = state.getRegionNum();

            if (!pQArray[x].isFull()) {
                pQArray[x].insert(state);
            } else { // removes an item, prints a statement about it, and then adds the new item
                System.out.println(" ");
                System.out.println("Tried to insert " + state.getName() + " into Priority Queue " + x + ", but the queue is full.");
                removed = pQArray[x].remove();
                System.out.println("Removed " + removed.getName() + " from Priority Queue " + x + ".");
                pQArray[x].insert(state);
                System.out.println("Inserted " + state.getName() + " from Priority Queue " + x + ".");
                System.out.println(" ");
            }

        }
    }

    /**
     * Prints out the contents of all four pQs.
     */
    private void pQDisplay() {
        for (int x = 0; x < pQArray.length;) {
            System.out.println("Priority Queue for Region " + x);
            pQArray[x].display();
            System.out.println(" ");
            x++;
        }
    }

    /**
     * Prints the specified queue out to a file
     *
     * @param queueNum determines which Queue that will be printed out
     * @throws IOException
     */
    private void pQWriteToFile(int queueNum) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("Queue" + queueNum + ".Input.txt")); // creates the buffered writer
        State state;

        while (!pQArray[queueNum].isEmpty()) {
            state = pQArray[queueNum].remove();
            bw.write(state.toString());
            bw.newLine();
            bw.flush();
        }
    }

} // end Controller class
