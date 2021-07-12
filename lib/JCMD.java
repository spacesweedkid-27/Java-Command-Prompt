package lib;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

class IllegalArgumentException extends Exception {

	public IllegalArgumentException(String message) {
		super("Programm " + message + " does not exist");
	}

}

public class JCMD {

	public static void execute(String command) {
		try {

			String path = null;

			File f32 = new File("C:\\Windows\\System32\\" + command);
			if (f32.exists())
				path = f32.getAbsolutePath();
			File f = new File("C:\\Program Files\\" + command);
			if (f.exists())
				path = f.getAbsolutePath();
			File f86 = new File("C:\\Program Files (x86)\\" + command);
			if (f86.exists())
				path = f86.getAbsolutePath();

			if (path == null) {
				throw new IllegalArgumentException(command);
			}
			run(path);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	private static void run(String path) throws IOException {
		System.out.println(path);

		Process process = Runtime.getRuntime().exec(path);

		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String line;
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
		}

		reader.close();
	}

}
