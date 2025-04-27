package Helpers;

import Repositories.ArchiveUtil;
import Repositories.Finance;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class FinanceFileReader {

	public static int cantReg(String file) {
		int num = 0;
		try {
			String path = Paths.get(".").toRealPath().toString() + "/src/Storage/FinanceFiles/" + file;
			File arch = new File(path);
			Scanner read = new Scanner(arch);
			read.nextLine();
			while (read.hasNextLine()) {
				read.nextLine();
				num++;
			}
			read.close();
			return num;
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
		return num;
	}

	public static boolean searchInFile(String text, String file) {
		try {
			String path = Paths.get(".").toRealPath().toString() + "/src/Storage/FinanceFiles/" + file;
			File arch = new File(path);
			Scanner read = new Scanner(arch);
			read.nextLine();
			String[] content = new String[2];
			while (read.hasNextLine()) {
				String line = read.nextLine();
				Scanner Line = new Scanner(line);
				content[0] = Line.next();
				content[1] = Line.next();
				if (content[1].equals(text)) {
					content = null;
					Line.close();
					return false;
				}
				Line.close();
			}
			read.close();
			content = null;
			return true;
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return true;
	}

	public static double fileHeadReading(String file, ArchiveUtil archiveUtil) {
		double num = 0.00;
		try {
			Scanner read = archiveUtil.getArchive(file + ".txt");
			num = Double.parseDouble(read.next());
			read.close();
		} catch (Exception i) {
			System.out.println("Error: " + i.getMessage());
		}
		return num;
	}

	public static void fileBodyReading(Scanner input, String file, Finance userMoney, ArchiveUtil archiveUtil) {
		if (file != null) {
			try {
				Scanner read2 = archiveUtil.getArchive(file + ".txt");
				read2.nextLine();
				Scanner sepa = null;
				while (read2.hasNextLine()) {
					String text = read2.nextLine();
					sepa = new Scanner(text).useDelimiter(" ");
					userMoney.loadDebt(Double.parseDouble(sepa.next()));
					userMoney.loadTitle(sepa.next());
				}
				if (sepa != null) {
					sepa.close();
				}
				read2.close();
			} catch (Exception i) {
				System.out.println("Error: " + i.getMessage());
			}
		}
	}

}
