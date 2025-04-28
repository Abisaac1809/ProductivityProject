package Helpers;

import java.util.Scanner;

import Repositories.ArchiveUtil;

public class HabitsFileReader {
	public static void getDailyHabits(String route, ArchiveUtil archiveUtil, String[] dailyHabits,
			int[] dailyHabitMinutes) {

		if (archiveUtil != null && dailyHabits != null && dailyHabitMinutes != null
				&& dailyHabits.length == dailyHabitMinutes.length) {

			String line = "";
			int i = 0;
			try (Scanner fileReader = archiveUtil.getArchive(route)) {
				while (fileReader.hasNextLine() && i < dailyHabits.length) {

					line = fileReader.nextLine();
					Scanner lineReader = new Scanner(line);
					lineReader.useDelimiter("#");

					while (lineReader.hasNext()) {
						dailyHabits[i] = lineReader.next();
						dailyHabitMinutes[i] = lineReader.nextInt();
					}
					lineReader.close();
					i++;
				}
			} catch (Exception e) {
				System.out.println("¡Lo sentimos!, ha ocurrido un error");
				System.out.println("Error: [No se ha encontrado el archivo]");
			}
		}
	}

	public static void getHabitTimeSpentDaily(String route, ArchiveUtil archiveUtil, int[][][] habitTimeSpentDaily) {

		if (route != null && habitTimeSpentDaily != null) {

			try (Scanner fileReader = archiveUtil.getArchive(route)) {
				while (fileReader.hasNextLine()) {
					for (int i = 0; i < habitTimeSpentDaily.length; i++) {
						for (int j = 0; j < habitTimeSpentDaily[i].length; j++) {
							for (int k = 0; k < habitTimeSpentDaily[i][j].length; k++) {
								habitTimeSpentDaily[i][j][k] = fileReader.nextInt();
							}
						}
					}
					fileReader.nextLine();
					if (!fileReader.hasNextInt())
						return;
				}
			} catch (Exception ex) {
				System.out.println("¡Lo sentimos!, ha ocurrido un error");
				System.out.println("Error: [No se ha encontrado el archivo]");
				System.out.println(ex.getMessage());
			}
		}
	}
}
