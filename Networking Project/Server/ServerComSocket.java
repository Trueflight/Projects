/*
 * CNT 4504 Project 1
 * Bassam Matloob, Cristina Pollidan, Matthew Goodrich, Stephanie McMahon
 * Last Edited: 5/24/2014
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerComSocket {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    ServerComSocket(int portNumber) {

        try {
            serverSocket = new ServerSocket(portNumber);
            System.out.println("Socket has been created");

        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
            System.exit(0);
        }

    }

    public void comConnect() {
        try {
            clientSocket = serverSocket.accept();
            System.out.println("Socket has been connected. ");
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            System.out.println("Exception caught when trying to connect");
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    public boolean comSend(String message) {
        System.out.println("Sending a reply ...");
        out.println(message);
        return true;
    }

    public String comReceive() throws IOException {
        System.out.println("Waiting to receive a message ...");
        return in.readLine();
    }

    public void comClose(boolean end) {

        try {
            if (end) {
                clientSocket.close();
            }
            serverSocket.close();
        } catch (IOException e) {
            System.out.println("Error occured while attempting to close the socket");
        }
    }

}
