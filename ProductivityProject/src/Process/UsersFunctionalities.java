package Process;

import java.nio.file.Paths;
import java.util.Scanner;

import Repositories.FileManager;

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

	static String signup(Scanner input) {
		System.out.print("- Nombre de usuario: ");
		String username = Validations.UsersValidations.username(false, input);
		System.out.print("- Contraseña: ");
		String password = input.next();
		FileManager fileManager = Validations.UsersValidations.getFileManager(username);
		Composables.UsersFileWriter.saveUser(username, Encrypt.encrypt(password), fileManager);
		Validations.FileManager.createHabitsFile(username);
		Composables.TasksFileWriter.createTaskFile(username);
		Composables.UsersFileWriter.createSessionFile(username, fileManager);
		return username;
	}

	static String signin(Scanner input) {
		System.out.print("- Nombre de usuario: ");
		String username = Validations.UsersValidations.username(true, input);
		Helpers.UsersFileReader.checkPassword(username, input);
		FileManager fileManager = Validations.UsersValidations.getFileManager(username);
		Composables.UsersFileWriter.createSessionFile(username, fileManager);
		return username;
	}
}
