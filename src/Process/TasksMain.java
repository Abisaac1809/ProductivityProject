package Process;

import java.util.Scanner;

import Functionalities.TasksFunctionalities;
import Repositories.ArchiveUtil;

public class TasksMain {
	public static void main(String username, Scanner input, ArchiveUtil archiveUtil) {
		Validations.TasksValidations.setTasksRouter(archiveUtil);
		TasksFunctionalities.menu(input, username, archiveUtil);
	}
}
