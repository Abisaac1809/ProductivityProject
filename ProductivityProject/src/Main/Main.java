package Main;

import Users.UsersMain;
import Finances.FinanceMain;
import Habits.HabitsMain;
import java.io.IOException;
import java.util.Scanner;

// Abisaac Carmona - 32.218.469
// Adriana Ochoa - 31.711.538
// Jose Mendez - 31.390.867
// Gabriel Vieira - 32.297.573

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("---PRODUCTIVITY PROJECT---\n");
        Scanner input = new Scanner(System.in);
        String username = UsersMain.main(input);
        int option = MainDevelopment.mainMenu(username, input);
        while (option != 5) {
            if (option == 1) FinanceMain.main(username);
            if (option == 2) HabitsMain.HabitsMain(username);
            // TODO: add tasks main implementation
            if (option == 4) {
                System.out.println("");
                username = UsersMain.main(input);
            }
            option = MainDevelopment.mainMenu(username, input);
        }
        System.out.println("\n---PROGRAMA CERRADO---");
        input.close();
    }
}
