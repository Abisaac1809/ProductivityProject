package Finances;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
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
                    System.out.println("Error: No tienes suficiente dinero si quieres retirar ese monto");
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
    
    static String valConcept(String text,String file,boolean flag){
        String concept;
        Scanner enter=new Scanner(System.in);
        while(true){
            try{
                System.out.println(text);
                concept=enter.nextLine().replace(" ", "_");
                concept=concept.toLowerCase();
                if(flag==true){
                    if(searchInFile(concept,file)){
                        return concept;
                    }else{
                        System.out.println("Error: Concepto ya usado");
                    }
                }
                if(flag==false){
                    if(!searchInFile(concept,file)){
                        return concept;
                    }else{
                        System.out.println("Error: Concepto no encontrado");
                    }
                }
            }
            catch(InputMismatchException ime){
                System.out.print("Error: "+ime.getMessage());
                enter.nextLine();
            }
        }
    }
    
    static boolean searchInFile(String text, String file){
        try{
        String path=Paths.get(".").toRealPath().toString()+"/src/Finances/"+file;
            File arch = new File(path);
            Scanner read = new Scanner(arch);
            read.nextLine();
            String[] content= new String[2];
            while(read.hasNextLine()){
                String line=read.nextLine();
                Scanner Line = new Scanner(line);
                content[0]=Line.next();
                content[1]=Line.next();
                if(content[1].equals(text)){
                    content=null;
                    return false;
                }
            }
            content=null;
            return true;
        }catch(IOException e){
            System.out.println("Error: "+e.getMessage());
        }
        return true;
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
            num = Double.parseDouble(read.next());
            read.close();
        }catch(IOException i){
            System.out.println("Error: "+i.getMessage());
        }
        return num;
    }
    
    static void fileBodyReading(String file,ArrayList<String> debts) {
        try{
            String path=Paths.get(".").toRealPath().toString()+"/src/Finances/"+file;
            File arch = new File(path);
            Scanner read = new Scanner(arch);
            if(read.hasNextLine()){
                read.nextLine();
                while(read.hasNextLine()){
                    String text=read.nextLine();
                    debts.add(text);
                }
            }
            read.close();
        }catch(IOException i){
            System.out.println("Error: "+i.getMessage());
        }
    }
    
    static double debtSearching(String file,ArrayList<String> debts,String text) {
        try{
            String path=Paths.get(".").toRealPath().toString()+"/src/Finances/"+file;
            File arch = new File(path);
            Scanner read = new Scanner(arch);
            Double c = Double.parseDouble(read.next());
            read.nextLine();
            String[] content= new String[2];
            int i=0;
            while(read.hasNextLine()){
                String line=read.nextLine();
                Scanner Line = new Scanner(line);
                content[0]=Line.next();
                content[1]=Line.next();
                Double d = Double.parseDouble(content[0]);
                if(content[1].equals(text)){
                    if(d<=c){
                        System.out.println(debts.get(i));
                        debts.remove(i);
                        read.close();
                        content=null;
                        return c-d;
                    }else{
                        System.out.println("Error: No hay suficiente saldo para pagar esa deuda");
                        content=null;
                        return c;
                    }
                }
                i++;
            }
            System.out.println("Error: Conjunto no encontrado");
            read.close();
        }catch(IOException i){
            System.out.println("Error: "+i.getMessage());
        }
        return 0;
    }
    

    
    static void fileWriter1(String file, double add,ArrayList<String> debts,int i){
        try{
            String path=Paths.get(".").toRealPath().toString()+"/src/Finances/"+file;
            File file1 = new File(path);
            file1.createNewFile();
            FileWriter writer = new FileWriter(file1,false);
            writer.write(Double.toString(add));
            writer.close();
            fileWriter1(file1, debts,i,0);
            debts.removeAll(debts);
        }catch(IOException e){
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    static void fileWriter1(File file, ArrayList<String> debts,int i,int j){
        try{
            if(j==i){
                return;
            }else{
            File file1 = file;
            file1.createNewFile();
            FileWriter writer2 = new FileWriter(file1,true);
            writer2.write("\n"+debts.get(j));
            writer2.close();
            fileWriter1(file,debts,i,j+1);
            }
            
        }catch(IOException e){
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    static double options(){
        Scanner input = new Scanner(System.in);
        double option=0;
        while(true){
            try{
                option=input.nextDouble();
                if(option==1 || option == 2 || option ==3 || option == 4 || option == 5 || option == 6){
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
