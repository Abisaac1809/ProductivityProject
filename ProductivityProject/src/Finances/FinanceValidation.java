package Finances;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.InputMismatchException;

public class FinanceValidation {
    static int valEntry(int balance,String text){
        int amount;
        Scanner enter =new Scanner(System.in);
        while(true){
            try{
                System.out.println(text);
                amount=enter.nextInt();
                if(amount>balance){
                    System.out.println("Error: No tienes suficiente dinero si quieres pagar esa deuda");
                }
                else{
                    return amount;
                }
            }
            catch(InputMismatchException ime){
                System.out.println("Error: Por favor, ingrese un numero");
                enter.next();
            }
        }
    }
    
    static int valEntry(String text){
        int amount;
        Scanner enter=new Scanner(System.in);
        while(true){
            try{
                System.out.println(text);
                amount=enter.nextInt();
                return amount;
            }
            catch(InputMismatchException ime){
                System.out.println("Error: Ingrese solo numeros");
            }
        }
    }
    
    static boolean itExist(String file1) throws IOException{
        String path=Paths.get(".").toRealPath().toString()+"/src/Finances/"+file1;
        File file = new File(path);
        return file.exists();
    }
    
    static void fileCreate(String file) throws IOException{
        try{
            String path=Paths.get(".").toRealPath().toString()+"/src/Finances/"+file;
            File newfile = new File(path);
            newfile.createNewFile();
            FileWriter writer = new FileWriter(path);
            writer.write(0);
            writer.close();
        } catch(IOException i){
            
        }
    }
    
    static int fileHeadReading(String file) throws IOException{
        String path=Paths.get(".").toRealPath().toString()+"/src/Finances/"+file;
        Scanner read = new Scanner(path);
        int num = read.nextInt();
        return num;
    }
    
    static int cantReg(String file) throws IOException{
        int cr =0;
        String path=Paths.get(".").toRealPath().toString()+"/src/Finances/"+file;
        Scanner read = new Scanner(path);
        while (read.hasNextLine()){
            read.nextLine();
            cr++;
        }
        return cr;
    }
    
    static void fileWriter1(String file, int add) throws IOException{
        String path=Paths.get(".").toRealPath().toString()+"/src/Finances/"+file;
        File oldfile = new File(path);
        Scanner read = new Scanner(oldfile);
        int num = read.nextInt();
        int cr=cantReg(file);
        String x = add+"";
        String[] concept = new String[cr-1];
        double[] mun = new double[cr-1];
        for(int i=0; i<cr;i++){
            mun[i]=read.nextDouble();
            concept[i]=read.next();
        }
        oldfile.delete();
        File newfile = new File(path);
        newfile.createNewFile();
        FileWriter writer = new FileWriter(newfile,true);
        for(int i=0; i<=cr;i++){
            if(i==0){
                writer.write(x);
            }else{
                String n=mun[i]+"";
                writer.write("\n");
                writer.write(n+" "+concept[i]);
            }
        }
        writer.close();
    }
    
    static int options(){
        Scanner input = new Scanner(System.in);
        int option=0;
        while(true){
            try{
                option=input.nextInt();
                return option;
            } catch(InputMismatchException e){
                System.out.println("Por favor ingrese un caracter válido");
            }
        }
    }
}
