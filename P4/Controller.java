/*
 * Matthew Goodrich
 * COP3538
 * Project 4
 */
package Project4;

import java.io.*;

/**
 * @author Matthew
 */
public class Controller {

    BinaryTree tree = new BinaryTree();
    Stack stack = new Stack();
    int[] deleteA = new int[17];
    BufferedReader br1, br2;

    public void execute() {
        readStateData("States.Input.A.txt", "States.Input.B.txt");
        stack.display();
        transfer();
        tree.display(true);
        tree.display(false);
        deleteStates("States.Delete.txt");
        tree.display(true);
        tree.display(false);
    }

    /**
     * Reads in data from a text file, creates state objects with it and adds the objects to a stack.
     * @param fileName the name of the file to read from
     * @param format char that denotes which format the file is in
     */
    private void readStateData(String file1, String file2) {
        String data1, data2;
        boolean contR = true;

        try {
            br1 = new BufferedReader(new FileReader(file1));
            br2 = new BufferedReader(new FileReader(file2));

            data1 = br1.readLine();
            data2 = br2.readLine();

            // while loop that adds each created state to the stack
            while (contR) {
                if (data1 == null && data2 == null) { // both files empty
                    contR = false;
                } else if (data1 != null && data2 != null) { // both files have more data
                    if (data1.compareTo(data2) < 0) {
                        stack.push(new Node(new State(data1.split(","))));
                        data1 = br1.readLine();
                    } else {
                        stack.push(new Node(new State(data2.split(","))));
                        data2 = br2.readLine();
                    }
                } else if (data1 != null) { // only file1 has data
                    stack.push(new Node(new State(data1.split(","))));
                    data1 = br1.readLine();
                } else { // only file2 has data
                    stack.push(new Node(new State(data2.split(","))));
                    data2 = br2.readLine();
                }
            }

            br1.close();
            br2.close();

        } catch (FileNotFoundException e) {
            System.out.println("State File not found");
        } catch (IOException e) {
            System.out.println("State File IO error");
        } catch (Exception e) {
            System.out.println("The state file was found, but an error occured.");
        }
    }

    /**
     * Pops each state inside the stack and adds it to the binary tree.
     */
    private void transfer() {
        while (!stack.isEmpty()) {
            tree.insert(stack.pop());
        }
    }

    /**
     * Reads int population values from a file, then deletes the corresponding states from the tree
     * @param file The file to be read from
     */
    private void deleteStates(String file) {
        int pop, x = 0;
        String data;

        try {
            br1 = new BufferedReader(new FileReader(file));

            while ((data = br1.readLine()) != null) { // reads in the date and adds it to an array
                deleteA[x] = Integer.parseInt(data);
                x++;
            }
            br1.close();
        } catch (FileNotFoundException e) {
            System.out.println("State File not found");
        } catch (IOException e) {
            System.out.println("State File IO error");
        } catch (Exception e) {
            System.out.println("The state file was found, but an error occured.");
        }

        for (int z = 0; z < deleteA.length; z++) { // uses the data in the array to delete the states
            Node node = tree.delete(deleteA[z]);
            System.out.println(node.getState().toString());
        }
    }
}
