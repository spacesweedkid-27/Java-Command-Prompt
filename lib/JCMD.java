package lib;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

class IllegalArgumentException extends Exception {


    public IllegalArgumentException(String message) {
        super(message);
    }

}

public class JCMD {
    public static HashMap<String, String> commands = new HashMap<String, String>();

    public JCMD() {
        commands.put("C", "C:\\Windows\\System32\\");
        commands.put("COMMAND", "C:\\Windows\\System32\\");
        commands.put("CMD", "C:\\Windows\\System32\\cmd.exe");
        commands.put("P", "C:\\Program Files\\");
        commands.put("PROGRAM FILES", "C:\\Program Files\\");
        commands.put("P86", "C:\\Program Files (x86)\\");
        commands.put("PROGRAM FILES (X86)", "C:\\Program Files (x86)\\");
        commands.put("", "");
    }

    public static void execute(String args, String command) {
        try {
            if (commands.get(args) != null && !(args.equals("H") || args.equals("HELP"))) {

                String real_command = commands.get(args) + command;
                System.out.println(real_command);

                Process process = Runtime.getRuntime().exec(real_command);

                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }

                reader.close();
            } else if (args.equals("H") || args.equals("HELP")) {
                System.out.println(
                        "Java Command Prompt v.1.1\n" +
                                "\n" +
                                "H or HELP:              Displays this message\n" +
                                "C or COMMAND:           Use a windows command located in System32\n" +
                                "P or PROGRAM FILES      Use a command located in Program Files\n" +
                                "P86 or PROGRAM FILES    Use a command located in Program Files (86)");


            } else {
                throw new IllegalArgumentException("unknown argument '" + args + "'");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
