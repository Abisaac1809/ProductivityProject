package Helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class TasksFileReader {
    public static ArrayList<String[]> getTasks(String username) {
        ArrayList<String[]> tasks = new ArrayList<String[]>();
        try {
            String path = Paths.get(".").toRealPath().toString() + "/src/Storage/TasksFiles/" + username + ".tasks.txt";
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

    public static ArrayList<String[]> findTasksTitle(ArrayList<String[]> tasks, String query, ArrayList<String[]> finded, int i) {
        if (i == tasks.size()) {
            return finded;
        }
        if (tasks.get(i)[0].equals(query)) {
            finded.add(tasks.get(i));
        }
        return findTasksTitle(tasks, query, finded, i + 1);
    }
    
    public static ArrayList<String[]> findTasksStatus(ArrayList<String[]> tasks, String query, ArrayList<String[]> finded, int i) {
        if (i == tasks.size()) {
            return finded;
        }
        if (tasks.get(i)[2].equals(query)) {
            finded.add(tasks.get(i));
        }
        return findTasksStatus(tasks, query, finded, i + 1);
    }    
}
