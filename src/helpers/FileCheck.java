package helpers;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public final class FileCheck {
	public static void tokenizeFile(File file) {
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		HashMap<String, ArrayList<String>> indexDB = Index.indexDB;

		try {
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);

			StreamTokenizer st = new StreamTokenizer(bufferedReader);

			int token = st.nextToken();

			//  Keep looking for words until reaching EOF
			while (token != StreamTokenizer.TT_EOF) {
				//  We only look for words, ignoring other symbols
				if (token == StreamTokenizer.TT_WORD) {
					//  Lowercase all words to avoid multiple inputs of the same word
					String word = st.sval.toLowerCase();

					//  Remove trailing dots
					if (word.charAt(word.length() - 1) == '.') {
						word = word.substring(0, word.length() - 1);
					}

					//  Add word to Database if it's new
					if (!indexDB.containsKey(word)) {
						ArrayList<String> newArr = new ArrayList<>();
						newArr.add(file.getName());

						indexDB.put(word, newArr);

					//  Add filename to list if not done already
					} else {
						if (!indexDB.get(word).contains(file.getName())) {
							indexDB.get(word).add(file.getName());
						}
					}
				}
				token = st.nextToken();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	//  Traverse non-empty directories and recursively
	//  search until the entire hierarchy has been exhausted
	public static void tokenizeDir(File dir) {
		if (dir.listFiles().length > 0) {
			for (File file : dir.listFiles()) {
				if (file.isDirectory()) {
					tokenizeDir(file);
				} else if (file.isFile()) {
					tokenizeFile(file);
				}
			}
		}
	}
}
