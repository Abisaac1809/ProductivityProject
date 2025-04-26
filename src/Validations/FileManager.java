package Validations;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
import Process.Encrypt;

public class FileManager {
    public static void createArchive(String route) throws IOException {
        if (route != null) {
            try {
                File file = new File(route);
                file.createNewFile();
            }
            catch (IOException e) {
                System.out.println("Error: [" + e.getMessage() + "]");
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
            catch (FileNotFoundException e) {
                System.out.println("¡Lo sentimos!, ha ocurrido un error");
                System.out.println("Error: [No se ha encontrado el archivo]");
                return 0;
            }
        }
        return 0;
    }    

    public static void createHabitsFile(String username) {
        if (username != null) {
            try {
                String path1 = Paths.get(".").toRealPath().toString() + "/src/Storage/HabitsFiles/Habits." + username + ".txt";
                String path2 = Paths.get(".").toRealPath().toString() + "/src/Storage/HabitsFiles/Performance." + username + ".txt";
                File file1 = new File(path1);
                file1.createNewFile();
                File file2 = new File(path2);
                file2.createNewFile();
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    
}
