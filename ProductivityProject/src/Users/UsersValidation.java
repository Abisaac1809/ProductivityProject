package Users;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import java.nio.file.Paths;

public class UsersValidation {
    static int option(Scanner input) {
        int value;
        while (true) {
            try {
                value = input.nextInt();

                if (value >= 1 && value <= 3) {
                    return value;
                } else {
                    System.out.println("Error: [Opción no válida]");
                    System.out.print("- Ingrese el valor correcto: ");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: [Solo se admiten valores numericos enteros]");
                System.out.print("- Ingrese el valor correcto: ");
                input.nextLine();
            }
        }
    }    
    private static boolean checkChars(String value) {
        String unsupported = "1234567890.,?/:;'!@#$%^&*()[]{}\\|=-+_~";
        if (value != null) {
            for (int i = 0; i < value.length(); i++) {
                for (int j = 0; j < unsupported.length(); j++) {
                    if (unsupported.charAt(j) == value.charAt(i)) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }
    static String username(boolean isLogin, Scanner input) {
        String value = "";
        if (input != null) {
            while (true) {
                value = input.next();
                if (userExists(value) && !isLogin) {
                    System.out.println("Error: [Nombre de usuario ya en uso]");
                    System.out.print("- Ingrese otro nombre de usuario: ");
                } else if (!userExists(value) && isLogin) {
                    System.out.println("Error: [Nombre de usuario no existe]");
                    System.out.print("- Ingrese otro nombre de usuario: ");
                } else if (checkChars(value)) {
                    return value;
                } else {
                    System.out.println("Error: [Caracter invalido encontrado]");
                    System.out.print("- Ingrese un nombre correcto: ");
                }
            }
        }
        return value;
    }
    static void saveUser(String username, String password) {
        if (username != null && password != null) {
            try {
                String path = Paths.get(".").toRealPath().toString() + "/src/Users/users.txt";
                File file = new File(path);
                file.createNewFile();
                FileWriter writer = new FileWriter(path, true);
                writer.write(String.format("%-20s  %-20s\n", username, password));
                writer.close();
                System.out.println("Usuario Creado!");
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    private static boolean userExists(String username) {
        if (username != null) {
            try {
                String path = Paths.get("").toRealPath().toString() + "/src/Users/users.txt";
                File file = new File(path);
                file.createNewFile();
                Scanner fileScanner = new Scanner(file);
                while (fileScanner.hasNextLine()) {
                    if (fileScanner.next().equals(username)) {
                        fileScanner.close();
                        return true;
                    }
                    fileScanner.nextLine();
                }
                fileScanner.close();
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return false;
    }
    static void checkPassword(String username, Scanner input) {
        if (username != null && input != null) {
            while (true) {
                try {
                    String path = Paths.get("").toRealPath().toString() + "/src/Users/users.txt";
                    Scanner file = new Scanner(new File(path));
                    String p = "";
                    System.out.print("- Contraseña: ");
                    String password = Encrypt.encrypt(input.next());
                    while (file.hasNextLine()) {
                        if (file.next().equals(username)) {
                            p = file.next();
                        }
                        file.nextLine();
                    }
                    if (p.equals(password)) {
                        file.close();
                        return;
                    } else {
                        System.out.println("Error: [Contraseña inválida]");
                    }
                    file.close();
                } catch (IOException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
    }
    static void createFinancesFile(String username) {
        if (username != null) {
            try {
                String path1 = Paths.get(".").toRealPath().toString() + "/src/Finances/" + username + "finances1.txt";
                String path2 = Paths.get(".").toRealPath().toString() + "/src/Finances/" + username + "finances2.txt";
                File file1 = new File(path1);
                file1.createNewFile();
                File file2 = new File(path2);
                file2.createNewFile();
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    static void createHabitsFile(String username) {
        if (username != null) {
            try {
                String path1 = Paths.get(".").toRealPath().toString() + "/src/Habits/HabitsDataBase/Habits." + username + ".txt";
                String path2 = Paths.get(".").toRealPath().toString() + "/src/Habits/HabitsDataBase/Performance." + username + ".txt";
                File file1 = new File(path1);
                file1.createNewFile();
                File file2 = new File(path2);
                file2.createNewFile();
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    static void createSessionFile(String username) {
        if (username != null) {
            try {
                String path = Paths.get("").toRealPath().toString() + "/src/Users/session.txt";
                File file = new File(path);
                FileWriter writer = new FileWriter(file,false);
                writer.write(Encrypt.encrypt(username));
                writer.close();
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    private static String getUserEncrypted(String usernameEncrypted) {
        String username = "";
        try {
            String path = Paths.get("").toRealPath().toString() + "/src/Users/users.txt";
            Scanner file = new Scanner(new File(path));
            while (file.hasNextLine()) {
                String user = file.next();
                if (Encrypt.encrypt(user).equals(usernameEncrypted)) {
                    file.close();
                    return user;
                }
            }
            file.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return username;
    }
    static String getSession() {
        try {
            String path = Paths.get("").toRealPath().toString() + "/src/Users/session.txt";
            Scanner file = new Scanner(new File(path));
            String username = "";
            if (file.hasNextLine()) {
                String userSession = file.next();
                username = getUserEncrypted(userSession);
            } else {
                System.out.println("ERROR: [Sesion del usuario invalida!]");
            }
            file.close();
            return username;
        } catch (IOException e) {
            return "";
        }
    }
}
