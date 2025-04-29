package Helpers;

import Repositories.ArchiveUtil;
import Repositories.Task;
import Structures.List;
import java.util.Scanner;

public class TasksFileReader {
    public static List getTasks(String file1, ArchiveUtil archiveUtil) {
		List tasks = new List();
		Scanner archivo = archiveUtil.getArchive(file1);
		while (archivo.hasNextLine()) {
			String[] fields = archivo.nextLine().split(",");
			Task task = new Task(fields[0], fields[1], fields[2]);
			tasks.add(task);
		}
		archivo.close();
        return tasks;
    }

    public static List findTasksTitle(List tasks, String query, List finded, int i) {
        if (i == tasks.size()) {
            return finded;
        }
		Task task = (Task) tasks.get(i);
        if (task.getTitle().equals(query)) {
            finded.add(tasks.get(i));
        }
        return findTasksTitle(tasks, query, finded, i + 1);
    }
    
    public static List findTasksStatus(List tasks, String query, List finded, int i) {
        if (i == tasks.size()) {
            return finded;
        }
		Task task = (Task) tasks.get(i);
        if (task.getStatus().equals(query)) {
            finded.add(tasks.get(i));
        }
        return findTasksStatus(tasks, query, finded, i + 1);
    }    
}
