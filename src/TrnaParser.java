import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class TrnaParser {
	private Path fFilePath;
	private static Charset ENCODING = StandardCharsets.UTF_8;

	private String name, acodon, isotype, sequence;
	private Float fenergy;

	private static Species spec;

	public TrnaParser(String fileName) {
		fFilePath = Paths.get(fileName);
	}

	public void processLineByLine() throws IOException {
		spec = new Species();
		spec.userInput();
		try (Scanner scanner = new Scanner(fFilePath, ENCODING.name())) {
			while (scanner.hasNextLine()) {
				processLine1(scanner.nextLine());
				processLine2(scanner.nextLine());
				processLine3(scanner.nextLine());
			}
		}
	}

	protected void processLine1(String line) {
		// use a second Scanner to parse the content of each line
		Scanner scanner = new Scanner(line);
		scanner.useDelimiter(" ");
		if (scanner.hasNext()) {
			// assumes the line has a certain structure
			String temp = scanner.next();
			temp = temp.substring(1);
			this.name = temp;
			scanner.next();
			scanner.next();
			this.isotype = scanner.next();
			this.acodon = scanner.next();
		} else {
			System.out.println("Empty or invalid line. Unable to process.");
		}
	}

	protected void processLine2(String line) {
		// use a second Scanner to parse the content of each line
		Scanner scanner = new Scanner(line);
		scanner.useDelimiter(" ");
		if (scanner.hasNext()) {
			// assumes the line has a certain structure
			this.sequence = scanner.next();
		} else {
			System.out.println("Empty or invalid line. Unable to process.");
		}
	}

	protected void processLine3(String line) {
		// use a second Scanner to parse the content of each line
		Scanner scanner = new Scanner(line);
		scanner.useDelimiter(" ");
		if (scanner.hasNext()) {
			// assumes the line has a certain structure
			scanner.next();
			String temp = scanner.next();
			temp = temp.substring(1, temp.length() - 2);
			this.fenergy = Float.parseFloat(temp);

			Trna t = new Trna(this.name, this.acodon, this.isotype,
					this.sequence, this.fenergy);
			spec.addTrna(t);
		} else {
			System.out.println("Empty or invalid line. Unable to process.");
		}
	}

	public static void main(String args[]) throws IOException {
		String inputFile = "C:\\Temp\\test.txt";
		TrnaParser parser = new TrnaParser(inputFile);
		parser.processLineByLine();
		
		System.out.println(spec.showTrnaList());
	}

}