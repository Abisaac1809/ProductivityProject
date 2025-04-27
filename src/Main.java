
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

import Repositories.ArchiveUtil;


// Abisaac Carmona - 32.218.469
// Adriana Ochoa - 31.711.538
// Jose Mendez - 31.390.867
// Gabriel Vieira - 32.297.573
// Lino Gouveia - 31.130.280

public class Main {
	public static void main(String[] args) throws IOException {
		System.out.println("---PRODUCTIVITY PROJECT---\n");
		Scanner input = new Scanner(System.in);
		String route = Paths.get("").toAbsolutePath().toString() + "/src/Storage/";
		ArchiveUtil archiveUtil = Validation.getArchiveUtil(route);
		Development.mainMenu(input, archiveUtil);
		System.out.println("\n---PROGRAMA CERRADO---");
		input.close();
	}
}
