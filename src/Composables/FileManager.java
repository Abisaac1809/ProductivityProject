package Composables;

import Repositories.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileManager {
	public static boolean createFileIfNotExists(String path) {
		if (path != null) {
			try {
				File file = new File(path);
				if (!file.exists()) {
					file.createNewFile();
					return false;
				}
				return true;
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
		return false;
	}

	public static void emptyFile(String path) {
		if (path != null) {
			try {
				File file = new File(path);
				FileWriter fileWriter = new FileWriter(file, false);
				if (file.exists()) {
					fileWriter.write("");
				}
				fileWriter.close();
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
		}

	}

	public static void fileWriter1(String file, double add, List<Double> debts, List<String> title, int i) {
		try {
			String path = Paths.get(".").toRealPath().toString() + "/src/Storage/FinanceFiles/" + file;
			File file1 = new File(path);
			file1.createNewFile();
			FileWriter writer = new FileWriter(file1, false);
			writer.write(Double.toString(add));
			writer.close();
			fileWriter1(file1, debts, title, i, 0);
			debts.removeAll();
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	public static void fileWriter1(File file, List<Double> debts, List<String> title, int i, int j) {
		try {
			if (j == i) {
				return;
			} else {
				File file1 = file;
				file1.createNewFile();
				FileWriter writer2 = new FileWriter(file1, true);
				writer2.write("\n" + debts.get(j) + " " + title.get(j));
				writer2.close();
				fileWriter1(file, debts, title, i, j + 1);
			}

		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	public static void addNewLine(String path) {
		if (path != null) {
			try {
				File file = new File(path);
				FileWriter fileWriter = new FileWriter(file, true);
				fileWriter.write("\n");
				fileWriter.close();
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
	}

    public static int determineRows(String route) {
            
        if (route != null) { 
            int count = 0;
            try (Scanner fileReader = new Scanner(new File(route))) {
                while (fileReader.hasNextLine()) {
                    fileReader.nextLine();
                    count++;
                }
                return count; 
            }
            catch (Exception e) {
                System.out.println("¡Lo sentimos!, ha ocurrido un error");
                System.out.println("Error: [No se ha encontrado el archivo]");
                return 0;
            }
        }
        return 0;
    }    
}
