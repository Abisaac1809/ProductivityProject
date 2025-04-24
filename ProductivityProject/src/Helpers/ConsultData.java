package Helpers;

import Repositories.FileManager;
import Repositories.Finance;
import Repositories.Queue;
import Repositories.Stack;
import Validations.DataValidations;
import Validations.FinanceValidation;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Scanner;

public class ConsultData<t>{
    
    public static void stacking (Queue cola, Stack pila){
        int i = cola.getSize();
        for(int j=0; j<i;j++){
            pila.top(cola.getFront());
            cola.cutNode();
        }
    }
    
    public static void showStack(Stack pila) throws IOException{
        LocalDateTime actualDateTime = LocalDateTime.now();
        String path=Paths.get(".").toRealPath().toString()+"/src/Storage/"+"datosusuario_" + actualDateTime+"_serial"+Math.random();
        File file = new File(path);
        FileWriter writer = new FileWriter(file,false);
        int i=pila.getSize();
        for(int j=0; j<i;i++){
            String data=pila.getTop().toString();
            System.out.println(data);
            writer.write(data);
        }
    }
    
    public static Finance financeSearchMenu(Scanner lectura, String username){
        try {
            String file = username+"finances1.txt";
            String path=Paths.get(".").toRealPath().toString()+"/src/Storage/FinancesFiles/";
            FileManager archive = new FileManager(path);
            Finance userMoney = new Finance();
                    int option = 0;
                    if (lectura != null) {
                        if (username != "") {
                            do {
                                System.out.printf("\n-----------------------\n\n");
                                System.out.println("Que dato desea buscar?");
                                System.out.println("1. Saldo");
                                System.out.println("2. Deudas (Por Monto)");
                                System.out.println("3. Deudas (Por Concepto)");
                                System.out.println("------------------------");
                                System.out.println("4. Regresar");
                                System.out.print("\n- Ingrese su opción: ");
                                option = DataValidations.option(lectura);
                                if (option == 1){
                                    userMoney.setMoney(getFinanceBalance(archive, file,lectura));
                                }
                                if (option == 2){
                                    String text="Ingrese el monto por el cual va a buscar: ";
                                    Double monto = FinanceValidation.valEntry(text);
                                    Double[] debts = getFinanceDebtsAmount(archive, file, lectura, monto);
                                    for(int i=0; i<debts.length;i++){
                                        userMoney.setDebt(""+debts[i]);
                                    }
                                }
                                if (option == 3){
                                    String files = path+file;
                                    String text="Ingrese el concepto de la deuda a buscar";
                                    String concept = FinanceValidation.valConcept(text, files, true);
                                    userMoney.setTitle(getFinanceDebtsTitle(archive, file, lectura, concept));
                                }
                                if(option==4){
                                    return userMoney;
                                }
                            }while (option != 4 && username != ""); 
                        }
                    }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    public static double getFinanceBalance(FileManager archive,String file, Scanner lectura) throws FileNotFoundException{
            lectura = archive.getFile(file);
            Double num = Double.parseDouble(lectura.next());
            return num;
    }
    
    public static Double[] getFinanceDebtsAmount(FileManager archive, String file,Scanner lectura,Double amount) throws FileNotFoundException{
        lectura = archive.getFile(file);
        String line;
        Double[] content = new Double[FinanceFileReader.cantReg(file)];
        Scanner sep = null;
        int n=0;
        Double aux;
        lectura.nextLine();
            while(lectura.hasNextLine()){
                line=lectura.nextLine();
                sep = new Scanner(line).useDelimiter(" ");
                aux=Double.parseDouble(sep.next());
                if(aux==amount){
                    content[n]=Double.parseDouble(sep.next());
                    n++;
                }
            }
            sep.close();
        return content;
    }
    
    public static String getFinanceDebtsTitle(FileManager archive, String file,Scanner lectura,String title){
        try {
            lectura = archive.getFile(file);
            String line;
            String aux;
            Scanner sep = null;
            lectura.nextLine();
            while(lectura.hasNextLine()){
                line=lectura.nextLine();
                sep = new Scanner(line).useDelimiter(" ");
                sep.next();
                aux=sep.next();
                if(aux.equals(title)){
                    return aux;
                }
            }
            sep.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return "";
    }
    
}
