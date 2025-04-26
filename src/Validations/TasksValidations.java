package Validations;

import Repositories.FileManager;
import Repositories.MissingArgumentException;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Scanner;

public class TasksValidations {
    public static String title(Scanner input) {
        while (true) {
            input.nextLine();
            String value = input.nextLine();

            if (value.length() <= 20) {
                return value;
            } else {
                System.out.println("Error: [Titulo excede los 20 caracteres permitidos]");
                System.out.print("- Ingrese el valor correcto: ");
            }
        }
    }
    
    public static String description(Scanner input) {
        while (true) {
            String value = input.nextLine();
            if (value.length() <= 50) {
                return value;
            } else {
                System.out.println("Error: [Titulo excede los 50 caracteres permitidos]");
                System.out.print("- Ingrese el valor correcto: ");
            }
            input.nextLine();
        }
    }
    
    public static int getField(Scanner input) {
        System.out.println("1. Titulo");
        System.out.println("2. Estado");
        System.out.print("- Ingrese el campo: ");
        return Validations.DataValidations.option(input, 1, 2);
    }
    
    public static String estatus(Scanner input) {
        while (true) {
            input.nextLine();
            String value = input.nextLine();
            if (value.equals("Hecha") || value.equals("No Hecha")) {
                return value;
            } else {
                System.out.println("Error: [Estado invalido]");
                System.out.print("- Ingrese el valor correcto: ");
            }
        }
    }    

	public static FileManager getFileManager(String username) {
		try {
			FileManager fileManager = new FileManager(Paths.get("").toAbsolutePath().toString() + "/src/Storage/TasksFiles/");
			return fileManager;
		} catch (FileNotFoundException | MissingArgumentException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}
