package lib;

import util.IllegalArgumentException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JCMD {


    public static void execute(String args, String command) {
        try {
            String command_end = command;

            if (args.equals("H") || args.equals("HELP")) {
                System.out.println(
                        "Java Command Prompt v.1.1\n" +
                        "\n" +
                        "H or HELP:              Displays this message\n" +
                        "C or COMMAND:           Use a windows command located in System32\n" +
                        "P or PROGRAM FILES      Use a command located in Program Files\n" +
                        "P86 or PROGRAM FILES    Use a command located in Program Files (86)");
            } else
            if (args.equals("C") || args.equals("COMMAND")) {
                command = "C:\\Windows\\System32\\";
            } else if (args.equals("CMD")) {
                command = "C:\\Windows\\System32\\cmd.exe";
            } else if (args.equals("P") || args.equals("PROGRAM FILES")) {
                command = "C:\\Program Files\\";
            } else if (args.equals("P86") || args.equals("PROGRAM FILES x86")) {
                command = "C:\\Program Files (x86)\\";
            }  else if (args.equals("") || args.equals(null)) {

            } else {
                throw new IllegalArgumentException("\"unknown argument: '\" + args + \"'\"");

            }
            command = command + command_end;

            Process process = Runtime.getRuntime().exec(command);

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
