import Repositories.ArchiveUtil;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Validation {
    static int option(Scanner input) {
        int value;
        while (true) {
            try {
                value = input.nextInt();

                if (value >= 1 && value <= 6) {
                    return value;
                } else {
                    System.out.println("Error: [Opción no válida]");
                    System.out.print("- Ingrese el valor correcto: ");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: [Solo se admiten valores numéricos enteros]");
                System.out.print("- Ingrese el valor correcto: ");
                input.nextLine();
            }
        }
    }
    static void logout() {
        try {
            String path = Paths.get("").toRealPath().toString() + "/src/Storage/UsersFiles/session.txt";
            File file = new File(path);
            file.delete();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
	static ArchiveUtil getArchiveUtil(String route) {
		try {
			return new ArchiveUtil(route);
		} catch (FileNotFoundException | IllegalArgumentException e) {
			System.out.println("Error: " + e.getMessage());
			return null;
		}
	}
}
