package Main;

import Users.UsersMain;
import Finances.FinanceMain;
import Habits.HabitsMain;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("---PRODUCTIVITY PROJECT---\n");
        Scanner input = new Scanner(System.in);
        String username = UsersMain.main(input);
        int option = MainDevelopment.mainMenu(username);
        while (option != 5) {
            if (option == 1) FinanceMain.main(username);
            if (option == 2) HabitsMain.HabitsMain(username);
            if (option == 4) {
                System.out.println("");
                username = UsersMain.main(input);
            }
            option = MainDevelopment.mainMenu(username);
        }
        System.out.println("\n---PROGRAMA CERRADO---");
        input.close();
    }
}
