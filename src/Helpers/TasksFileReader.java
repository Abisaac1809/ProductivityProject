package Helpers;

import java.io.FileNotFoundException;
import java.util.Scanner;
import Repositories.Task;
import Repositories.FileManager;
import Repositories.List;

public class TasksFileReader {
    public static List<Task> getTasks(String username, FileManager fileManager) {
		List<Task> tasks = new List<Task>();
        try {
            Scanner archivo = fileManager.getFile(username + ".tasks.txt");
            while (archivo.hasNextLine()) {
				String[] fields = archivo.nextLine().split(",");
				Task task = new Task(fields[0], fields[1], fields[2]);
                tasks.add(task);
            }
            archivo.close();
        } catch (FileNotFoundException | IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return tasks;
    }

    public static List<Task> findTasksTitle(List<Task> tasks, String query, List<Task> finded, int i) {
        if (i == tasks.size()) {
            return finded;
        }
        if (tasks.get(i).getTitle().equals(query)) {
            finded.add(tasks.get(i));
        }
        return findTasksTitle(tasks, query, finded, i + 1);
    }
    
    public static List<Task> findTasksStatus(List<Task> tasks, String query, List<Task> finded, int i) {
        if (i == tasks.size()) {
            return finded;
        }
        if (tasks.get(i).getStatus().equals(query)) {
            finded.add(tasks.get(i));
        }
        return findTasksStatus(tasks, query, finded, i + 1);
    }    
}
