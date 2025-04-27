package Validations;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

import Composables.FileManager;
import Repositories.ArchiveUtil;
import Repositories.MissingArgumentException;

public class HabitsValidations {
    public static boolean habitNameExist(String[] dailyHabits,int index, String newName) {
        if (index == dailyHabits.length) {
            return false;
        }
        else if (newName.equals(dailyHabits[index])) {
            return true;
        }
        else {
            return habitNameExist(dailyHabits, index + 1, newName);
        }
    }
    
    public static boolean userHasHabits(String habitsRoute, String performanceRoute, ArchiveUtil archiveUtil) throws IOException  {
        
        if (habitsRoute != null && performanceRoute != null) {
            try (Scanner fileReader = archiveUtil.getArchive(habitsRoute + ".txt")) {
                return fileReader.hasNext();
            }
            catch (Exception e) {
				FileManager.createFileIfNotExists(archiveUtil.getRouter() + habitsRoute);
				FileManager.createFileIfNotExists(archiveUtil.getRouter() + performanceRoute);
                return true;
            }
        }
        return false;
    }    

	public static void setHabitsRouter(ArchiveUtil archiveUtil) {
		try {
			archiveUtil.setRouter(Paths.get("").toAbsolutePath().toString() + "/src/Storage/HabitsFiles/");
		} catch (FileNotFoundException | MissingArgumentException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
