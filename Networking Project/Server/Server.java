/*
 * CNT 4504 Project 1
 * Bassam Matloob, Cristina Pollidan, Matthew Goodrich, Stephanie McMahon
 * Last Edited: 5/24/2014
 */


import java.io.*;

public class Server {

    public static void main(String[] args) {

        String temp;
        boolean status;
        Message msg = new Message();
        String inputLine;
        int portNumber = 3333;
        ServerComSocket comSocket;
        comSocket = new ServerComSocket(portNumber);
        while (true) {
            try {
                status = true;
                comSocket.comConnect();
                while ((inputLine = comSocket.comReceive()) != null) {
                    if ((temp = msg.parseMessage(inputLine)) == null) {
                        System.out.println("Message received and the user decided to quit.");
                        status = false;
                        comSocket.comClose(true);
                        System.exit(0);
                    }
                    if (status) {
                        System.out.println("Message received. ");
                        comSocket.comSend(temp);
                    } else {
                        comSocket.comClose(false);
                    }
                }
            } catch (IOException ex) {
                System.out.println("An error occured during the communication process");
            }
        }
    }
}