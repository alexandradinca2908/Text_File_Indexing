import helpers.Index;
import static helpers.FileCheck.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		//  Init command variable
		Scanner in = new Scanner(System.in);
		String command = in.nextLine();

		//  Init indexing instance
		HashMap<String, ArrayList<String>> indexDB = Index.indexDB;

		while (!command.equals("exit")) {
			if (command.contains("index")) {
				//  Extract file
				String pathToFile = command.split(" ")[1];
				File file = new File(pathToFile);

				//  Check existence
				if (!file.exists()) {
					System.out.println("File not found");
					command = in.nextLine();
					continue;
				}

				//  Tokenize
				if (file.isFile()) {
					tokenizeFile(file);
				} else if (file.isDirectory()) {
					tokenizeDir(file);
				} else {
					System.out.println("This is not a file nor a directory");
					command = in.nextLine();
					continue;
				}

				System.out.println("Successful tokenization for " + pathToFile);
			} else if (command.contains("query")) {
				String givenWord = command.split(" ")[1];

				if (indexDB.containsKey(givenWord)) {
					for (String filename : indexDB.get(givenWord)) {
						System.out.printf(filename + " ");
					}
				} else {
					System.out.println("No file contains \"" + givenWord + "\"");
				}

				System.out.println();

			//  Case for typos/wrong command
			} else {
				System.out.println("Unknown command");
			}

			//  Take next input
			command = in.nextLine();
		}
	}
}