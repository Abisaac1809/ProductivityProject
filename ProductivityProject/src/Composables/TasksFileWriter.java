package Composables;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

import Repositories.List;
import Repositories.Task;

public class TasksFileWriter {
	public static void updateFile(List<Task> tasks, Scanner input, String username) {
		try {
			String path = Paths.get(".").toRealPath().toString() + "/src/Storage/TasksFiles/" + username + ".tasks.txt";
			File file = new File(path);
			file.createNewFile();
			FileWriter writer = new FileWriter(path);
			for (int i = 0; i < tasks.size(); i++) {
				writer.write(String.format("%s,%s,%s\n", tasks.get(i).getTitle(), tasks.get(i).getDescription(),
						tasks.get(i).getStatus()));
			}
			writer.close();
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
