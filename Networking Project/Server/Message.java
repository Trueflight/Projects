/*
 * CNT 4504 Project 1
 * Bassam Matloob, Cristina Pollidan, Matthew Goodrich, Stephanie McMahon
 * Last Edited: 5/21/2014
 */


import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Message {

    Process process;
    BufferedReader br;
    String output, outputF;
    int numLines;

    public String parseMessage(String message) throws IOException {
        String ret = "";
        int option = Integer.parseInt(message);
        if (option != 7) {
            switch (option) {
                case 1:
                    ret = linux("date");
                    break;
                case 2:
                    ret = linux("uptime");
                    break;
                case 3:
                    ret = linux("free");
                    break;
                case 4:
                    ret = linux("netstat -v");
                    break;
                case 5:
                    ret = linux("who");
                    break;
                case 6:
                    ret = linux("ps aux");
            }
        } else {
            ret = null;
        }

        return ret;
    }

    private String linux(String command) throws IOException {
        output = "";
        numLines = 0;
        process = Runtime.getRuntime().exec(command);
        br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        while ((outputF = br.readLine()) != null) {
            output += outputF + "\n";
            numLines++;
        }
        outputF = "";
        outputF = Integer.toString(numLines) + "\n";
        outputF += output;
        return outputF;
    }

}
