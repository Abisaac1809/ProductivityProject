package Process;

import java.nio.file.Paths;
import java.util.Scanner;

import Composables.FileManager;
import Repositories.ArchiveUtil;

public class UsersFunctionalities {
	static int loginMenu(Scanner input) {
		int option = 0;
		System.out.println("1. Iniciar Sesión");
		System.out.println("2. Registrarse");
		System.out.println("---------------------");
		System.out.println("3. Salir del Programa");
		System.out.print("- Ingrese su opción: ");
		option = Validations.DataValidations.option(input);
		return option;
	}

	static String signup(Scanner input, ArchiveUtil archiveUtil) {
		System.out.print("- Nombre de usuario: ");
		String username = Validations.UsersValidations.username(false, input, archiveUtil);
		System.out.print("- Contraseña: ");
		String password = input.next();
		String text = String.format("%-20s  %-20s\n", username, Encrypt.encrypt(password));
		archiveUtil.setCreateArchive(text, "users", false);
		System.out.println("Usuario Creado!");
		Validations.FileManager.createHabitsFile(username);
		FileManager.createFileIfNotExists(
				Paths.get("").toAbsolutePath().toString() + "/src/Storage/TasksFiles/" + username + ".tasks.txt");
		text = String.format("%s\n", Encrypt.encrypt(username));
		archiveUtil.setCreateArchive(text, "session", false);
		return username;
	}

	static String signin(Scanner input, ArchiveUtil archiveUtil) {
		System.out.print("- Nombre de usuario: ");
		String username = Validations.UsersValidations.username(true, input, archiveUtil);
		Helpers.UsersFileReader.checkPassword(username, input, archiveUtil);
		String text = String.format("%s\n", Encrypt.encrypt(username));
		archiveUtil.setCreateArchive(text, "session", false);
		return username;
	}
}
