package Helpers;

import java.util.Scanner;
import Repositories.Task;
import Repositories.ArchiveUtil;
import Repositories.List;

public class TasksFileReader {
    public static List<Task> getTasks(String username, ArchiveUtil archiveUtil) {
		List<Task> tasks = new List<Task>();
		Scanner archivo = archiveUtil.getArchive(username + ".tasks.txt");
		while (archivo.hasNextLine()) {
			String[] fields = archivo.nextLine().split(",");
			Task task = new Task(fields[0], fields[1], fields[2]);
			tasks.add(task);
		}
		archivo.close();
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
