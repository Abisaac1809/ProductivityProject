package Process;

import Repositories.ArchiveUtil;
import java.io.IOException;

import Composables.FileManager;

public class HabitsMain {

	public static void HabitsMain(String user, ArchiveUtil archiveUtil) throws IOException {

		if (user != null) {

			String habitsRoute = "";
			String performanceUserRoute = "";

			Validations.HabitsValidations.setHabitsRouter(archiveUtil);

			int rows = 0;
			String[] dailyHabits;
			String[] routes;
			int[] dailyHabitMinutes;
			int[][][] habitTimeSpentDaily;

			habitsRoute = "Habits." + user;
			performanceUserRoute = "Performance." + user;
			FileManager.createFileIfNotExists(archiveUtil.getRouter() + habitsRoute + ".txt");
			FileManager.createFileIfNotExists(archiveUtil.getRouter() + performanceUserRoute + ".txt");

			// nuevo
			rows = (Validations.HabitsValidations.userHasHabits(habitsRoute, performanceUserRoute, archiveUtil))
					? (FileManager.determineRows(archiveUtil.getRouter() + habitsRoute + ".txt"))
					: 0;

			dailyHabits = new String[rows];
			routes = new String[2];
			dailyHabitMinutes = new int[rows];
			habitTimeSpentDaily = new int[12][31][rows];

			Initializer.initializeVector(routes);
			Initializer.initializeVector(dailyHabits);
			Initializer.initializeVector(dailyHabitMinutes);
			Initializer.initializeArray(habitTimeSpentDaily);
			routes[0] = habitsRoute;
			routes[1] = performanceUserRoute;

			Helpers.HabitsFileReader.getDailyHabits(habitsRoute, archiveUtil, dailyHabits, dailyHabitMinutes);
			Helpers.HabitsFileReader.getHabitTimeSpentDaily(performanceUserRoute, archiveUtil, habitTimeSpentDaily);

			HabitsFunctionalities.habitsManagmentMenu(routes, dailyHabits, dailyHabitMinutes, habitTimeSpentDaily,
					archiveUtil);

			dailyHabits = null;
			dailyHabitMinutes = null;
			habitTimeSpentDaily = null;
		}
	}
}
