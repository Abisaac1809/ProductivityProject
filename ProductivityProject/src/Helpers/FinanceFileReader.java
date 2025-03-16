package Helpers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class FinanceFileReader {
    public static boolean searchInFile(String text, String file){
        try{
        String path=Paths.get(".").toRealPath().toString()+"/src/Storage/FinancesFiles/"+file;
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
    
    public static double fileHeadReading(String file) {
        double num=0.00;
        try{
            String path=Paths.get(".").toRealPath().toString()+"/src/Storage/FinancesFiles/"+file;
            File arch = new File(path);
            Scanner read = new Scanner(arch);
            num = Double.parseDouble(read.next());
            read.close();
        }catch(IOException i){
            System.out.println("Error: "+i.getMessage());
        }
        return num;
    }
    
    public static void fileBodyReading(String file,ArrayList<String> debts) {
        try{
            String path=Paths.get(".").toRealPath().toString()+"/src/Storage/FinancesFiles/"+file;
            File arch = new File(path);
            Scanner read = new Scanner(arch);
                read.nextLine();
                while(read.hasNextLine()){
                    String text=read.nextLine();
                    debts.add(text);
                }
            read.close();
        }catch(IOException i){
            System.out.println("Error: "+i.getMessage());
        }
    }
    
    public static double debtSearching(String file,ArrayList<String> debts,String text) {
        try{
            String path=Paths.get(".").toRealPath().toString()+"/src/Storage/FinancesFiles/"+file;
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
}
