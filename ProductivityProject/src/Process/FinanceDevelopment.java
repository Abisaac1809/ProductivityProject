package Process;
import Validations.FinanceValidation;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
import Helpers.FinanceFileReader;
import Validations.FinanceValidation;
import Composables.FinanceFileWriter;

public class FinanceDevelopment {
    
    public static void menu(String username,ArrayList<String> debts){
        try{
        double option=0;
        if (username != null) {
            String file1 = "Finances."+username+".txt";
            String file2 = "History."+username+".txt";
            FinanceDevelopment.funDefault(file1, file2);
            System.out.println("\n");
            while(option>=0 && option<=5){
                FinanceFileReader.fileBodyReading(file1, debts);
                option=FinanceDevelopment.menuFinance(file1);
                if(option==1){
                    FinanceDevelopment.addAmount(file1,debts);
                }
                if(option==2){
                    FinanceDevelopment.subtractAmount(file1,debts);
                }
                if(option==3){
                    FinanceDevelopment.addDebt(file1, debts);
                }
                if(option==4){
                    FinanceDevelopment.subtractDebt(file1, debts);
                }
                if(option==5){
                    FinanceDevelopment.showDebts(username,file1, debts);
                }
            }
        }
        }catch(IOException e){
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    public static void funDefault(String file1, String file2) throws IOException{
        if (file1 != null && file2 != null) {
            FinanceFileWriter.fileCreate(file1);
            FinanceFileWriter.fileCreate(file2);
        }
    }
    
    public static double menuFinance(String file1) throws IOException{
        double option = 0;
        if (file1 != null) {
            double num =FinanceFileReader.fileHeadReading(file1);
            DecimalFormat df = new DecimalFormat("#0.00");
            String numd = df.format(num);
            System.out.print("-------Finanzas-------\n");
            System.out.println("\nSu saldo es de: "+numd+"\n");
            System.out.println("1. Añadir saldo");
            System.out.println("2. Restar saldo");
            System.out.println("3. Agregar Deuda");
            System.out.println("4. Quitar/Pagar Deuda");
            System.out.println("5. Mostrar Deudas");
            System.out.println("6. Regresar al menú principal\n");
            System.out.print("-Ingrese su opción: ");
            option = FinanceValidation.options();
        }
        return option;
    }
    public static void addAmount(String file1,ArrayList<String> debts) throws IOException{
        if (file1 != null) {
            FinanceFileWriter.fileCreate(file1);
            double add =0;
            String text = "Ingrese el saldo a agregar (Se tomará el valor absoluto): ";
            double num =FinanceFileReader.fileHeadReading(file1);
            System.out.println(num);
            DecimalFormat df = new DecimalFormat("#0.00");
            String numd = df.format(num);
            System.out.println("-----------------------");
            System.out.println("\nSaldo disponible: "+numd);
            add=FinanceValidation.valEntry(text);
            num+=add;
            int i=debts.size();
            FinanceFileWriter.fileWriter1(file1, num,debts,i);
        }
    }
    
    public static void subtractAmount(String file1,ArrayList<String> debts) throws IOException{
        if (file1 != null) {
            FinanceFileWriter.fileCreate(file1);
            double subtract =0;
            String text = "Ingrese cuanto saldo quitar: ";
            double num =FinanceFileReader.fileHeadReading(file1);
            DecimalFormat df = new DecimalFormat("#0.00");
            String numd = df.format(num);
            System.out.println("-----------------------");
            System.out.println("\nSaldo disponible: "+numd);
            subtract=FinanceValidation.valEntry(num,text);
            num-=subtract;
            int i=debts.size();
            FinanceFileWriter.fileWriter1(file1, num,debts,i);
        }
    }
    
    public static void addDebt(String file1,ArrayList<String> debts){
        if (file1 != null) {
            FinanceFileWriter.fileCreate(file1);
            double num =FinanceFileReader.fileHeadReading(file1);
            String text = "Ingrese el monto de su deuda: ";
            double debt = FinanceValidation.valEntry(text);
            text="ingrese el concepto de esta deuda";
            String concept = FinanceValidation.valConcept(text,file1,true);
            String adding=Double.toString(debt)+" "+concept;
            debts.add(adding);
            int i = debts.size();
            FinanceFileWriter.fileWriter1(file1, num,debts,i);
        }
    }
    
    public static void subtractDebt(String file1,ArrayList<String> debts){
        if (file1 != null) {
            FinanceFileWriter.fileCreate(file1);
            double num;
            String text = "Ingrese el concepto de la deuda a pagar: ";
            String debt = FinanceValidation.valConcept(text,file1,false);
            int i=debts.size();
            num=FinanceFileReader.debtSearching(file1, debts, debt);
            i=debts.size();
            FinanceFileWriter.fileWriter1(file1, num,debts,i);
        }
    }
    
    public static void showDebts(String username, String file1,ArrayList<String> debts){
        if (file1 != null) {
            FinanceFileReader.fileBodyReading(file1, debts);
            System.out.println("-----------------------\nDeudas de "+username+": ");
            System.out.println("Monto:                Concepto:");
            Scanner read = null;
            String[] content = new String[2];
            for(int i=0;i<debts.size()-1;i++){
                String text = debts.get(i);
                read = new Scanner (text).useDelimiter(" ");
                content[0] = read.next();
                content[1] = read.next();
                System.out.printf("%8s %20s",content[0],content[1]+"\n");
            }
            read.close();
        }
    }
}
