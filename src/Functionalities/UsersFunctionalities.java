package Functionalities;

import Composables.FileManager;
import Repositories.ArchiveUtil;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Scanner;

public class UsersFunctionalities {
	public static int loginMenu(Scanner input) {
		int option = 0;
		System.out.println("1. Iniciar Sesión");
		System.out.println("2. Registrarse");
		System.out.println("---------------------");
		System.out.println("3. Salir del Programa");
		System.out.print("- Ingrese su opción: ");
		option = Validations.DataValidations.option(input);
		return option;
	}

	public static String signup(Scanner input, ArchiveUtil archiveUtil) {
        LocalDateTime actualDateTime = LocalDateTime.now();
        int rand = (int) (Math.random() * 20000) + 10000;
		System.out.print("- Nombre de usuario: ");
		String username = Validations.UsersValidations.username(false, input, archiveUtil);
		System.out.print("- Contraseña: ");
		String password = input.next();
		String text = String.format("%-20s  %-20s\n", username, encrypt(password));
		archiveUtil.setCreateArchive(text, "users", false);
		System.out.println("Usuario Creado!");
		Composables.FileManager.createHabitsFile(username);
        String file1 = username + "finances_" + actualDateTime.now().toString().replace(':', '_') +"_"+rand;
        file1=FileManager.getToFile(file1, archiveUtil.getRouter());
		FileManager.createFileIfNotExists(
				Paths.get("").toAbsolutePath().toString() + "/src/Storage/FinanceFiles/" + file1);
		text = String.format("%s\n", encrypt(username));
		archiveUtil.setCreateArchive(text, "session", false);
		return username;
	}

	public static String signin(Scanner input, ArchiveUtil archiveUtil) {
		System.out.print("- Nombre de usuario: ");
		String username = Validations.UsersValidations.username(true, input, archiveUtil);
		Helpers.UsersFileReader.checkPassword(username, input, archiveUtil);
		String text = String.format("%s\n", encrypt(username));
		archiveUtil.setCreateArchive(text, "session", false);
		return username;
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
    public static String encrypt(String word) {
        String encryp = "";
        if (word != null) {
            String symb[] = {".",",","*","!","@","#", "?", "/", ";", "-", "$", "%", "&", "(", ")", "[", "]", "{", "}", "'", "\"", "=", "+", "-", "_" };
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
}
