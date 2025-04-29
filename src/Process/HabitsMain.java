package Process;

import Composables.FileManager;
import Functionalities.HabitsFunctionalities;
import Repositories.ArchiveUtil;
import java.io.IOException;
import java.time.LocalDateTime;

public class HabitsMain {

	public static void HabitsMain(String user, ArchiveUtil archiveUtil) throws IOException {

		if (user != null) {

			String habitsRoute = "";
			String performanceUserRoute = "";
			LocalDateTime actualDateTime = LocalDateTime.now();
            int rand = (int) (Math.random() * 20000) + 10000;
            String file1 = user + "habits_" + actualDateTime.now().toString().replace(':', '-') +"_"+rand+".txt";
			Validations.HabitsValidations.setHabitsRouter(archiveUtil);
			file1=FileManager.getToFile(file1, archiveUtil.getRouter());

			int rows = 0;
			String[] dailyHabits;
			String[] routes;
			int[] dailyHabitMinutes;
			int[][][] habitTimeSpentDaily;

			habitsRoute = file1;
			file1 = user + "performance_" + actualDateTime.now().toString().replace(':', '_') +"_"+rand+".txt";
			file1=FileManager.getToFile(file1, archiveUtil.getRouter());
			performanceUserRoute = file1;

			FileManager.createFileIfNotExists(archiveUtil.getRouter() + habitsRoute);
			FileManager.createFileIfNotExists(archiveUtil.getRouter() + performanceUserRoute);

			rows = (Validations.HabitsValidations.userHasHabits(habitsRoute, performanceUserRoute, archiveUtil))
					? (FileManager.determineRows(archiveUtil.getRouter() + habitsRoute))
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
