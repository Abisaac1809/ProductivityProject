package Main;

import Users.UsersMain;
import Finances.FinanceMain;
import Habits.HabitsMain;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("---PRODUCTIVITY PROJECT---\n");
        String username = UsersMain.main();
        int option = MainDevelopment.mainMenu(username);
        while (option != 5) {
            if (option == 1) FinanceMain.main(username);
            if (option == 2) HabitsMain.HabitsMain(username);
            if (option == 4) {
                System.out.println("");
                username = UsersMain.main();
            }
            option = MainDevelopment.mainMenu(username);
        }
        System.out.println("\n---PROGRAMA CERRADO---");
    }
}
