/*
 * CNT 4504 Project 1
 * Bassam Matloob, Cristina Pollidan, Matthew Goodrich, Stephanie McMahon
 * Last Edited: 5/28/2014
 */


import java.util.ArrayList;

/**
 * Primary Driver class for the client side of project 1;
 *
 * @author Christina Polidan, Matthew Goodrich
 */
public class Client {

    public static void main(String[] args) {

        if (args.length != 0) {
            ArrayList<ThreadClass> list;
            ThreadClass tc = null, checkCounter;
            int counter, cc;
            Interface inter = new Interface();
            do {
                list = new ArrayList<>();
                counter = 0;
                inter.choice();
                if (!inter.quit()) {
                    inter.clientCount();
                    cc = inter.getclientCount();
                } else {
                    cc = 1;
                }

                for (int i = 1; i <= cc; i++) {
                    tc = new ThreadClass(args[0], 3333, i, inter.getChoice());
                    list.add(tc);
                }
                for (int x = 0; x < list.size(); x++) {
                    list.get(x).run();
                }

                checkCounter = new ThreadClass();
                // prevents the program from calculating the latency before all the threads are completed

                if (!inter.quit()) {
                    while (counter != cc) {
                        counter = checkCounter.getCounter();
                    }
                    System.out.println("The average latency for " + cc + " clients is " + (checkCounter.getTimeTaken() / cc) + " milliseconds"
                    );
                    checkCounter.setCounter(0);
                    checkCounter.setTimeTaken(0);
                }
            } while (!inter.quit());
        } else {
            System.out.println("No hostname was entered, the program will not run");
        }
    }
}