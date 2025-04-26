package Process;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Dates {
    public static int[] getDate(){
        String stringDate = "";
        int[] date;
        date = new int[3];
        Initializer.initializeVector(date);
        LocalDateTime localTime = LocalDateTime.now();
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        
        stringDate = localTime.format(formater);
        Scanner scanner = new Scanner(stringDate);
        scanner.useDelimiter("-");
        date[0] = scanner.nextInt();
        date[1] = scanner.nextInt();
        date[2] = scanner.nextInt();
        
        localTime = null;
        formater = null;
        scanner.close();
        
        return date;
    }
    
    public static int[] getMonthsDays() {
        int year = 0;
        int[] date = getDate();
        int[] normalYear = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int[] leapYear = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        year = date[2];        
        
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
            return leapYear;
        }
        else {
            return normalYear;
        }
    }    
}
