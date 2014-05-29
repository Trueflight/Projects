/*
 * CNT 4504 Project 1
 * Bassam Matloob, Cristina Pollidan, Matthew Goodrich, Stephanie McMahon
 * Last Edited: 5/27/2014
 */


import java.io.IOException;
import java.util.ArrayList;

/**
 * Class responsible for handling the individual threads, thread counter, and
 * time taken
 */
public class ThreadClass extends Thread {

    private String hostName;
    private int portNumber;
    private String choice;
    private int i, threadNum;
    private static long timeTaken;
    private static int threadCounter;
    private ArrayList<String> input = new ArrayList<>();

    public ThreadClass() {

    }

    public ThreadClass(String host, int port, int tNum, String msg) {
        hostName = host;
        portNumber = port;
        choice = msg;
        threadNum = tNum;

    }

    @Override
    public void run() {
        try {

            ComSocket comSocket = new ComSocket(hostName, portNumber);
            long startime = System.currentTimeMillis();
            comSocket.comSend(choice);

            // make sure it was not a quit command, and recieve all the data
            if (choice.compareTo("7") != 0) {
                i = Integer.parseInt(comSocket.comReceive());
                for (; i >= 0; i--) {
                    input.add(comSocket.comReceive());
                }
                // calculate the time taken to recive data and print it to the console
                long endtime = System.currentTimeMillis();
                long threadTime = (endtime - startime);
                timeTaken += threadTime;
                System.out.println("Thread " + threadNum + " took " + threadTime + " milliseconds to complete");

                // print the data
                for (int x = 0; x < input.size(); x++) {
                    System.out.println(input.get(x));
                }
            }
            threadCounter++;
            comSocket.comClose();

        } catch (IOException e) {
            System.err.println("IO Exception occured while sending or recieving data from the socket");
        }

    }

    /**
     * Returns threadCounter
     *
     * @return the current number of completed threads
     */
    public int getCounter() {
        return threadCounter;
    }

    /**
     * Returns timeTaken
     *
     * @return the amount of time the thread took to complete
     */
    public long getTimeTaken() {
        return timeTaken;
    }

    public void setCounter(int counter) {
        threadCounter = 0;
    }

    public void setTimeTaken(long time) {
        timeTaken = time;
    }

}
