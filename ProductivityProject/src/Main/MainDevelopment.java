package Main;

public class MainDevelopment {
    static int mainMenu(String username) {
        int option;
        System.out.printf("\n---BIENVENIDO %s---\n\n", username);
        System.out.println("1. Finanzas");
        System.out.println("2. Hábitos");
        System.out.println("3. Tareas");
        System.out.println("------------------------");
        System.out.println("4. Cerrar Sesión");
        System.out.println("5. Salir del Programa");
        System.out.print("\n- Ingrese su opción: ");
        option = MainValidation.option();
        return option;
    }
}
