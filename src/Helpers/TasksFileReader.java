package Helpers;

import java.util.Scanner;
import Repositories.Task;
import Repositories.ArchiveUtil;
import Repositories.List;

public class TasksFileReader {
    public static List getTasks(String username, ArchiveUtil archiveUtil) {
		List tasks = new List();
		Scanner archivo = archiveUtil.getArchive(username + ".tasks.txt");
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
