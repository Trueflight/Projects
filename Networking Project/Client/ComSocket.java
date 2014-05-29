/*
 * CNT 4504 Project 1
 * Bassam Matloob, Cristina Pollidan, Matthew Goodrich, Stephanie McMahon
 * Last Edited: 5/28/2014
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * This class handles the creation of sockets
 */
public class ComSocket {

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    
    /**
     * Constructor 
     * @param hostName the name of the server host
     * @param portNumber the name of the server port
     */
    ComSocket(String hostName, int portNumber) {

        try {

            clientSocket = new Socket(hostName, portNumber);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        } catch (IOException e) {
            System.out.println("There was an error creating the socket");
            System.out.println(e.getMessage());
            System.exit(0);
        }

    }

    public boolean comSend(String message) throws IOException {
        out.println(message);
        return true;
    }

    public String comReceive() throws IOException {
        return in.readLine();
    }
    
    /**
     * Closes the socket. Call this to end the program
     */
    public void comClose() {
        try {
            clientSocket.close();
        } catch (IOException ex) {
            System.out.println("Error occured while closing the socket");
        }

    }
}
