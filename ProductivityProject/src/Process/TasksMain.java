package Process;

import Process.TasksFunctionalities;
import java.util.Scanner;

public class TasksMain {
    public static void main(String username, Scanner input) {
        TasksFunctionalities.menu(input, username);
    }
}
