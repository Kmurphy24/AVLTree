import java.io.*;
import java.util.*;

public class AVLDriver {

	public static void main(String[] args) {
		String line;
		Book element;
		AVLTree<Book> avl = new AVLTree<Book>();

		try {
			Scanner inFile = new Scanner(new FileInputStream("Books.txt"));
			Scanner keyboard = new Scanner(System.in);

			Scanner testFile = new Scanner(new FileInputStream("TestISBN.txt"));

			PrintWriter out = new PrintWriter(new FileWriter("BSTOutput.txt"));

			while (inFile.hasNextLine()) {
				line = inFile.nextLine();
				Book aBook = new Book(line.substring(0, 10), line.substring(15,
						55).trim(), line.substring(55, 95).trim(),
						Integer.parseInt(line.substring(95, 99)),
						line.substring(104, line.length()));
				avl.add(aBook);
			}
			inFile.close();
			out.println("All " + avl.size()
					+ " elements have been inserted into the " + "AVL tree");
			testFile.close();
			out.close();
		} catch (IOException e) {
			System.out.println("Input file or the test file cannot be opened");
			System.exit(0);
		}
	}
}