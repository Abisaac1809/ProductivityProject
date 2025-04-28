package Repositories;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Maneja las finanzas personales incluyendo saldo, deudas y conceptos asociados.
 * 
 * Permite gestionar un registro de ingresos/gastos, deudas específicas y generales,
 * con validación de entradas y operaciones financieras básicas.
 */
public class Finance {
    
    private List title;
    private List debt;
    private Double money;
    private String SpecifiedDebt;
    
    /**
     * Constructor que inicializa las listas de títulos y deudas.
     */
    public Finance(){
        title = new List();
        debt = new List();
    }
    
    // Getters
    
    /**
     * Obtiene el saldo actual.
     * 
     * @return El saldo actual como Double, puede ser null si no se ha establecido.
     */
    public Double getMoney(){
        return money;
    }
        
    /**
     * Obtiene el título en la posición especificada de la lista de títulos.
     *
     * @param i El índice del título que se desea obtener
     * @return El título en la posición especificada como String
     * @throws IndexOutOfBoundsException si el índice está fuera de los límites de la lista
     */
    public String getTitle(int i){
        return (String) title.get(i);
    }

    /**
     * Obtiene la deuda en la posición especificada de la lista de deudas.
     *
     * @param i El índice de la deuda que se desea obtener
     * @return La deuda en la posición especificada como Double
     * @throws IndexOutOfBoundsException si el índice está fuera de los límites de la lista
     */
    public Double getDebt(int i) {
        return (Double) debt.get(i);
    }

    /**
     * Obtiene la deuda especificada.
     * 
     * @return La deuda especificada como String, puede ser null si no se ha establecido
     */
    public String getSpeDebt(){
        return SpecifiedDebt;
    }
    
    // Setters

    /**
     * Establece un nuevo título en la lista de conceptos.
     * Valida que el concepto no exista previamente.
     *
     * @param text El texto del título que se desea establecer
     */
    public void setTitle(String text){
        String name = valConcept(text,true);
        title.add(name);
    }

    /**
     * Establece una nueva deuda en la lista de deudas.
     * Valida que el monto sea numérico y positivo.
     *
     * @param text El monto de la deuda que se desea establecer como String
     */
    public void setDebt(String text){
        Double amount=valEntry(text);
        debt.add(amount);
    }

    /**
     * Establece el saldo actual.
     * 
     * @param munny El nuevo saldo como Double
     */
    public void setMoney(Double munny){
        money=munny;
    }

    /**
     * Establece la deuda especifica.
     * 
     * @param text La nueva deuda especifica como String
     */
    public void setSpeDebt(String text){
        SpecifiedDebt=text;
    }
    
    // Methods
    
    /**
     * Añade una cantidad al saldo actual.
     * Valida que la entrada sea numérica.
     * 
     * @param text La cantidad a añadir como String
     */
    public void addMoney(String text){
        double munny=valEntry(text);
        money+=munny;
    }
    
    /**
     * Resta una cantidad al saldo actual.
     * Valida que la entrada sea numérica.
     * 
     * @param text La cantidad a restar como String
     */
    public void subtractMoney(String text){
        double munny=valEntry(text);
        money-=munny;
    }
    
    /**
     * Obtiene la cantidad de deudas registradas.
     * 
     * @return El tamaño de la lista de deudas como int
     */
    public int debtLength(){
        return debt.size();
    }
    
    /**
     * Obtiene la cantidad de títulos registrados.
     * 
     * @return El tamaño de la lista de títulos como int
     */
    public int titleLength(){
        return title.size();
    }
    
    /**
     * Elimina una deuda y su título asociado de las listas.
     * 
     * @param i El índice de la deuda/título a eliminar
     * @throws IndexOutOfBoundsException si el índice está fuera de los límites
     */
    public void deleteDebt(int i){
        debt.remove(i);
        title.remove(i);
    }
    
    /**
     * Añade una deuda a la lista sin validación de concepto.
     * 
     * @param num El monto de la deuda a añadir como Double
     */
    public void loadDebt(Double num){
        debt.add(num);
    }
    
    /**
     * Añade un título a la lista sin validación de duplicados.
     * 
     * @param text El título a añadir como String
     */
    public void loadTitle(String text){
        title.add(text);
    }
    
    /**
     * Obtiene la lista completa de deudas.
     * 
     * @return La lista de deudas como objeto List
     */
    public List getDebtsList(){
        return debt;
    }
    
    /**
     * Obtiene la lista completa de títulos.
     * 
     * @return La lista de títulos como objeto List
     */
    public List getTitlesList(){
        return title;
    }

    /**
     * Genera una representación en String del estado financiero.
     * 
     * @return String con formato "Concepto: [títulos] Deuda: [deuda especifica] Saldo: [saldo]"
     */
    public String toStringContent() {
        String text="";
        if (title.size() != 0) {
            text += "Concepto: "; 
            for(int i=0;i<title.size();i++){
                if(!title.get(i).equals("null")){
                    text +=this.getTitle(i)+" ";
                }  
            }
        }
        if(SpecifiedDebt!=null){
            text += "Deuda: "+SpecifiedDebt+" ";
        }
        if(money!=null){
            text+="Saldo: " +money;
        }
        return text;
    }
    
    // Utilitarias
    
    /**
     * Valida y convierte una entrada de texto a valor numérico positivo.
     * 
     * @param text El texto a convertir en valor numérico
     * @return El valor numérico como double
     * @throws InputMismatchException si la entrada no es numérica
     */
    public double valEntry(String text){
        double amount;
        Scanner enter=new Scanner(System.in);
        while(true){
            try{
                amount=enter.nextDouble();
                amount=Math.abs(amount);
                return amount;
            }
            catch(InputMismatchException ime){
                System.out.print("Error: Ingrese solo numeros\n");
            }
        }
    }
    
    /**
     * Valida un concepto financiero según diferentes criterios.
     * 
     * @param text El concepto a validar
     * @param flag true para validar nuevo concepto, false para buscar existente
     * @return El concepto validado o "hecho" si se eliminó una deuda
     * @throws InputMismatchException si hay error en el formato de entrada
     */
    public String valConcept(String text, boolean flag){
        String concept;
        Scanner enter=new Scanner(System.in);
        boolean check=true;
        while(true){
            try{
                concept=enter.nextLine().replace(" ", "_");
                concept=concept.toLowerCase();
                if(flag==true){
                    for(int i=0;i<titleLength();i++){
                        if(concept.equals(getTitle(i))){
                            check=false;
                        }
                    }
                    if(check){
                        return concept;
                    }else{
                        System.out.println("Error: Concepto ya usado");
                        check=true;
                    }
                }
                if(flag==false){
                    for(int i=0;i<debtLength();i++){
                        if(concept.equals(getTitle(i))){
                            deleteDebt(i);
                            check=false;
                            return "hecho";
                        }
                    }
                    if(check){
                    }else{
                        System.out.println("Error: Concepto no encontrado");
                    }
                }
            }
            catch(InputMismatchException ime){
                System.out.print("Error: "+ime.getMessage());
            }
        }
    }
    
    /**
     * Busca y procesa el pago de una deuda específica.
     * 
     * @param text El concepto de la deuda a buscar
     * @return El nuevo saldo después del pago o el saldo actual si no se pudo pagar
     */
    public double debtSearching(String text) {
        Double c = this.getMoney();
        Double d;
            for(int i=0;i<this.debtLength()-1;i++){
                d = this.getDebt(i);
                if(this.getTitle(i).equals(text)){
                    if(d<=c){
                        System.out.println(this.getDebt(i));
                        this.deleteDebt(i);
                        return c-d;
                    }else{
                        System.out.println("Error: No hay suficiente saldo para pagar esa deuda");
                        return c;
                    }
                }
            }
            System.out.println("Error: Deuda no encontrada");
        return c;
    }
}