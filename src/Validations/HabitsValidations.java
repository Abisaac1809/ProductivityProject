package Validations;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

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
    
    public static boolean userHasHabits(String habitsRoute, String performanceRoute) throws IOException  {
        
        if (habitsRoute != null && performanceRoute != null) {
            try (Scanner fileReader = new Scanner(new File(habitsRoute))) {
                return fileReader.hasNext();
            }
            catch (FileNotFoundException e) {
                Validations.FileManager.createArchive(habitsRoute);
                Validations.FileManager.createArchive(performanceRoute);
                return true;
            }
        }
        return false;
    }    
}
