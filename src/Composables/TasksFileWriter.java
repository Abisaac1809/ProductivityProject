package Composables;

import java.io.IOException;
import java.nio.file.Paths;

import Repositories.FileManager;
import Repositories.List;
import Repositories.Task;

public class TasksFileWriter {
	public static void updateFile(List<Task> tasks, String username, FileManager fileManager) {
		try {
			for (int i = 0; i < tasks.size(); i++) {
				String text = String.format("%s,%s,%s\n", tasks.get(i).getTitle(), tasks.get(i).getDescription(),
						tasks.get(i).getStatus());
				fileManager.writeTextInFile(text, username + ".tasks.txt", true);
			}
		} catch (IOException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	public static void createTaskFile(String username) {
		try {
			FileManager fileManager = new FileManager(Paths.get("").toAbsolutePath().toString() + "/src/Storage/TasksFiles/");
			fileManager.createFile(username + ".tasks.txt");
		} catch (IOException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
}
