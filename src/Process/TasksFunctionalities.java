package Process;

import java.util.Scanner;

import Composables.FileManager;
import Repositories.ArchiveUtil;
import Repositories.List;
import Repositories.Task;

public class TasksFunctionalities {
	static void menu(Scanner input, String username, ArchiveUtil archiveUtil) {

		if (input != null && username != null) {
			int option = 0;
			while (option != 4) {
				System.out.println("\n-----TAREAS-----\n");
				System.out.println("1. Crear Tarea");
				System.out.println("2. Buscar Tarea");
				System.out.println("---------------------");
				System.out.println("4. Menu Principal");
				System.out.print("\n- Ingrese su opción: ");
				option = Validations.DataValidations.option(input, 1, 4);
				FileManager.createFileIfNotExists(archiveUtil.getRouter() + username + ".tasks.txt");
				List tasks = Helpers.TasksFileReader.getTasks(username, archiveUtil);
				if (option == 1)
					createTask(input, username, tasks, archiveUtil);
				if (option == 2)
					searchTask(input, username, tasks, archiveUtil);
			}
		}
	}

	private static void createTask(Scanner input, String username, List tasks, ArchiveUtil archiveUtil) {
		if (input != null && username != null && tasks != null) {
			System.out.print("- Ingrese el titulo de la tarea: ");
			String title = Validations.TasksValidations.title(input);
			System.out.print("- Ingrese la descripcion de la tarea: ");
			String description = Validations.TasksValidations.description(input);
			Task task = new Task(title, description, "No Hecha");
			tasks.add(task);
			for (int i = 0; i < tasks.size(); i++) {
				Task currentTask = (Task) tasks.get(i);
				String text = String.format("%s,%s,%s\n", currentTask.getTitle(), currentTask.getDescription(),
						currentTask.getStatus());
				archiveUtil.setCreateArchive(text, username + ".tasks", false);
			}
		}
	}

	private static void searchTask(Scanner input, String username, List tasks, ArchiveUtil archiveUtil) {
		if (input != null && username != null && tasks != null) {
			int field = Validations.TasksValidations.getField(input);
			List finded = new List();
			if (field == 1) {
				System.out.print("- Ingrese el titulo: ");
				String query = Validations.TasksValidations.title(input);
				finded = Helpers.TasksFileReader.findTasksTitle(tasks, query, finded, 0);
			} else if (field == 2) {
				System.out.print("- Ingrese el estado: ");
				String query = Validations.TasksValidations.estatus(input);
				finded = Helpers.TasksFileReader.findTasksStatus(tasks, query, finded, 0);
			}
			if (finded.size() > 0) {
				System.out.println("TAREAS ENCONTRADAS:");
				for (int i = 0; i < finded.size(); i++) {
					Task task = (Task) finded.get(i);
					System.out.printf("%2d.  %-20s  [%s]\n    %s\n", i + 1, task.getTitle(),
							task.getStatus(), task.getDescription());
				}
			} else {
				System.out.println("TAREAS NO ENCONTRADAS!");
			}
		}
	}
}
