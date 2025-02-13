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

                if (value == 1 || value == 2) {
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
        for (int i = 0; i < value.length(); i++) {
            for (int j = 0; j < unsupported.length(); j++) {
                if (unsupported.charAt(j) == value.charAt(i)) {
                    return false;
                }
            }
        }
        return true;
    }
    static String username(boolean isLogin, Scanner input) {
        String value;
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
    static void saveUser(String username, String password) {
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
    private static boolean userExists(String username) {
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
        return false;
    }
    static void checkPassword(String username, Scanner input) {
        while (true) {
            try {
                String path = Paths.get("").toRealPath().toString() + "/src/Users/users.txt";
                Scanner file = new Scanner(new File(path));
                String p = "";
                System.out.print("- Contraseña: ");
                String password = encrypt(input.next());
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
    private static boolean arrayContains(String value, String[] array) {
        if (array != null && value != null) {
            for (int i = 0; i < array.length; i++) {
                if (array[i].equals(value)) return true;
            }
        }
        return false;
    }
    private static int getIndex(String value, String[] array) {
        int index = -1;
        if (array != null && value != null) {
            for (int i = 0; i < array.length; i++) {
                if (array[i].equals(value)) return i;
            }
        }
        return index;
    }
    static String encrypt(String word) {
        String encryp = "";
        if (word != null) {
            String symb[] = {".",",","*","!","@","#", "?", "/", ";", ":", "$", "%", "&", "(", ")", "[", "]", "{", "}", "'", "\"", "=", "+", "-", "_" };
            String num[] = {"1","2","3","4","5","6","7","8","9","0"};
            String lower[] = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
            String upper[] = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

            for (int i = 0; i < word.length(); i++) {
                int index;
                if (arrayContains(String.valueOf(word.charAt(i)), lower)) {
                    index = i + getIndex(String.valueOf(word.charAt(i)), lower);
                    while (index > (symb.length - 1)) index -= symb.length;
                    encryp += symb[index];
                } else if (arrayContains(String.valueOf(word.charAt(i)), upper)) {
                    index = i + getIndex(String.valueOf(word.charAt(i)), upper);
                    while (index > (num.length - 1)) index -= num.length;
                    encryp += num[index];
                } else if (arrayContains(String.valueOf(word.charAt(i)), num)) {
                    index = i + getIndex(String.valueOf(word.charAt(i)), num);
                    while (index > (lower.length - 1)) index -= lower.length;
                    encryp += lower[index];
                } else if (arrayContains(String.valueOf(word.charAt(i)), symb)) {
                    index = i + getIndex(String.valueOf(word.charAt(i)), symb);
                    while (index > (upper.length - 1)) index -= upper.length;
                    encryp += upper[index];
                }
            }
        }
        return encryp;
    }
    static void createFinancesFile(String username) {
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
    static void createHabitsFile(String username) {
        try {
            String path = Paths.get(".").toRealPath().toString() + "/src/Habits/HabitsDataBase/Habits." + username + ".txt";
            File file = new File(path);
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
