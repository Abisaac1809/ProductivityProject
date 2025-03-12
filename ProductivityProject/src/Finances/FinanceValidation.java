package Finances;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.InputMismatchException;

public class FinanceValidation {
    static double valEntry(double balance,String text){
        double amount;
        Scanner enter =new Scanner(System.in);
        while(true){
            try{
                System.out.println(text);
                amount=enter.nextDouble();
                amount=Math.abs(amount);
                if(amount>balance){
                    System.out.println("Error: No tienes suficiente dinero si quieres pagar esa deuda");
                }
                else{
                    return amount;
                }
            }
            catch(InputMismatchException ime){
                System.out.println("Error: Por favor, ingrese un numero");
                enter.nextLine();
            }
        }
    }
    
    static double valEntry(String text){
        double amount;
        Scanner enter=new Scanner(System.in);
        while(true){
            try{
                System.out.println(text);
                amount=enter.nextDouble();
                amount=Math.abs(amount);
                return amount;
            }
            catch(InputMismatchException ime){
                System.out.print("Error: Ingrese solo numeros\n");
                enter.nextLine();
            }
        }
    }
    
    static void fileCreate(String file) {
        try{
            String dfault= "0.00";
            String path=Paths.get(".").toRealPath().toString()+"/src/Finances/"+file;
            File newfile = new File(path);
            if(newfile.createNewFile()){
                FileWriter writer = new FileWriter(path);
                writer.write(dfault);
                writer.close();
            }
        } catch(IOException i){
            System.out.println("Error: "+i.getMessage());
        }
    }
    
    static double fileHeadReading(String file) {
        double num=0.00;
        try{
            String path=Paths.get(".").toRealPath().toString()+"/src/Finances/"+file;
            File arch = new File(path);
            Scanner read = new Scanner(arch);
            System.out.println("x");
            num = Double.parseDouble(read.next());
            read.close();
        }catch(IOException i){
            System.out.println("Error: "+i.getMessage());
        }
        return num;
    }
    
    private static int cantReg(String file) {
        int cr =0;
        try{
        String path=Paths.get(".").toRealPath().toString()+"/src/Finances/"+file;
        File arch = new File(path);
        Scanner read = new Scanner(arch);
        while (read.hasNextLine()){
            read.nextLine();
            cr++;
        }
        }catch(IOException i){
            System.out.println("Error: "+i.getMessage());
        }
        return cr;
    }
    
    static void fileWriter1(String file, double add) throws IOException{
        try{
            String path=Paths.get(".").toRealPath().toString()+"/src/Finances/"+file;
            File file1 = new File(path);
            file1.createNewFile();
            FileWriter writer = new FileWriter(file1,false);
            writer.write(Double.toString(add));
            writer.close();
        }catch(IOException i){
            System.out.println("Error: "+i.getMessage());
        }
    }
    
    static double options(){
        Scanner input = new Scanner(System.in);
        double option=0;
        while(true){
            try{
                option=input.nextDouble();
                if(option==1 || option == 2 || option ==3){
                    return option;
                }else{
                    System.out.println("Error: Opcion no valida");
                    System.out.print("-Ingrese su opcion:");
                }
            } catch(InputMismatchException e){
                System.out.println("Error: Por favor ingrese un caracter válido");
                input.nextLine();
            }
        }
    }
}
