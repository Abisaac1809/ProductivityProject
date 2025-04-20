package Tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TasksValidation {
    static int option(Scanner input, int min, int max) {
        int value;
        while (true) {
            try {
                value = input.nextInt();

                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.println("Error: [Opción no válida]");
                    System.out.print("- Ingrese el valor correcto: ");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: [Solo se admiten valores numericos enteros]");
                System.out.print("- Ingrese el valor correcto: ");
                input.nextLine();
            }
        }
    }    
    static ArrayList<String[]> getTasks(String username) {
        ArrayList<String[]> tasks = new ArrayList<String[]>();
        try {
            String path = Paths.get(".").toRealPath().toString() + "/src/Tasks/" + username + ".tasks.txt";
            Scanner archivo = new Scanner(new File(path));
            while (archivo.hasNextLine()) {
                tasks.add(archivo.nextLine().split(","));
            }
            archivo.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return tasks;
    }
    static String title(Scanner input) {
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
    static String description(Scanner input) {
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
    static void updateFile(ArrayList<String[]> tasks, Scanner input, String username) {
        try {
            String path = Paths.get(".").toRealPath().toString() + "/src/Tasks/" + username + ".tasks.txt";
            File file = new File(path);
            file.createNewFile();
            FileWriter writer = new FileWriter(path);
            for (int i = 0; i < tasks.size(); i++) {
                writer.write(String.format("%s,%s,%s\n",tasks.get(i)[0],tasks.get(i)[1], tasks.get(i)[2]));
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    static int getField(Scanner input) {
        System.out.println("1. Titulo");
        System.out.println("2. Estado");
        System.out.print("- Ingrese el campo: ");
        return option(input, 1, 2);
    }
    static String estatus(Scanner input) {
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
    static ArrayList<String[]> findTasksTitle(ArrayList<String[]> tasks, String query, ArrayList<String[]> finded, int i) {
        if (i == tasks.size()) {
            return finded;
        }
        if (tasks.get(i)[0].equals(query)) {
            finded.add(tasks.get(i));
        }
        return findTasksTitle(tasks, query, finded, i + 1);
    }
    static ArrayList<String[]> findTasksStatus(ArrayList<String[]> tasks, String query, ArrayList<String[]> finded, int i) {
        if (i == tasks.size()) {
            return finded;
        }
        if (tasks.get(i)[2].equals(query)) {
            finded.add(tasks.get(i));
        }
        return findTasksStatus(tasks, query, finded, i + 1);
    }
}
