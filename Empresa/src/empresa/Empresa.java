/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author Paco G.
 */
public class Empresa implements Serializable{
    
    
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException, Exception {
        boolean fin=false;
        String cliente_datos[]=new String[5];
        
        while (!fin) {
        System.out.println("Seleccionar una opción:");
        System.out.println("[1] Añadir Cliente");
        System.out.println("[2] Listar Clientes.");
        System.out.println("[3] Buscar Cliente.");
        System.out.println("[4] Borrar Cliente.");
        System.out.println("[5] Borrar fichero de Clientes.");
        System.out.println("[0] Salir.");
        System.out.println("Escriba la selección: ");
        int selec = leeOpcion() ;
        switch (selec){
            case 1:
                nuevo_cliente(); 
            break;
            case 2:
              modulos mod2=new modulos();
              mod2.listar();
            break;
            case 3:
                Scanner entrada=new Scanner(System.in);
                System.out.print("Ingrese NIF a buscar: ");
                String bal = entrada.next();
                modulos mod3=new modulos();
                mod3.buscar(bal);
            break;
            case 4:
                Scanner entrada1=new Scanner(System.in);
                System.out.print("Ingrese NIF a buscar y borrar: ");
                String bal1 = entrada1.next();
                ArrayList<String> list= new ArrayList<>();
                File f;        
                FileReader fr;
                BufferedReader br;
                f= new File("clientes.dat");
                fr=new FileReader(f);
                br=new BufferedReader(fr);
                String linea;
                while((linea=br.readLine())!=null){
                list.add(linea);  
                }
                    //System.out.println(list.toString());
                br.close();
                fr.close();
                for(int i=0;i<list.size();i++){
                    StringTokenizer st = new StringTokenizer(list.get(i),"-");
                    int s=st.countTokens();
                    for (int r=0;r<s;r++){
                        cliente_datos[r]=st.nextToken();
                     }
                    if (cliente_datos[0].equals(bal1)) {
                        System.out.println("Se va a borrar el registro cuyo NIF es: "+ cliente_datos[0]);
                    list.remove(i);
                    escribir_archivo(list);
                    System.out.println("Registro borrado");
                    }
                }
            break;
            case 5:
                Scanner borrar=new Scanner (System.in);
                System.out.println("Estas seguro de borrar el archivo?(s/n)");
                String borr=borrar.next();
                if ("s".equals(borr)){
                    File f1;
                    f1 = new File("clientes.dat");
                    if(f1.delete()){
                        System.out.println("Archivo borrado correctamente.");
                    }
                }else System.out.printf ("Archivo no borrado.\n");
            break;
            case 0:
                System.out.printf ("Fin de la aplicación...\n");
                fin = true;
            break ;
            default:
            System.out.println("Selecciona una opción válida.");
            }
        System.out.println ("Pulsa alguna tecla para continuar.\n");
        pulsacionTecla();
        }
    }
    public static void nuevo_cliente() throws IOException, Exception{
        String[] datos = new String[5];
        String[] cliente = new String[5];
        for (int cl=0;cl<=4;cl++){
          switch(cl){
              case 0: System.out.println("Introduce NIF");
              String nif = lecturaTeclado();
              cliente[cl]=nif;
              break;
              case 1: System.out.println("Introduce Nombre");
              String nombre = lecturaTeclado();
              cliente[cl]=nombre;
              break;
              case 2: System.out.println("Introduce Telefono");
              String telefono = lecturaTeclado();
              cliente[cl]=telefono;
              break;
              case 3: System.out.println("Introduce Direccion");
              String direccion = lecturaTeclado();
              cliente[cl]=direccion;
              break;
              case 4: System.out.println("Introduce Deuda");
              String deuda = lecturaTeclado();
              cliente[cl]=deuda;
              break;
          }  
        }
        modulos mod=new modulos(cliente[0],cliente[1],cliente[2],cliente[3],cliente[4]);
       mod.escribir();
            System.out.println("Nuevo cliente añadido.");

    }
    //---------------------------------------------------------------
 // MÉTODO lecturaTeclado: Captura de una cadena de teclado
 //---------------------------------------------------------------
 private static String lecturaTeclado () throws Exception {
    try {
    InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    BufferedReader reader = new BufferedReader(inputStreamReader);
    String line = reader.readLine();
    return line;
    }
    catch (IOException e) {
    throw e;
    }
 }
 private static int leeOpcion() {
    int nOpcion = 10 ;
    try {
    // Leer desde teclado la opción
    String cOpcion = lecturaTeclado();
    nOpcion = Integer.parseInt(cOpcion) ;
    } catch(NumberFormatException e) {
    System.err.println("NO ES UN NÚMERO VÁLIDO: Vuelve a intentarlo.");}
    catch(IOException ex) {
    System.err.println("NO ES UN NÚMERO VÁLIDO: Vuelve a intentarlo.");}
    catch(Exception exc) {
    System.err.println("NO ES UN NÚMERO VÁLIDO: Vuelve a intentarlo.");}
    return (nOpcion) ;
 }
 //---------------------------------------------------------------
 // MÉTODO pulsacionTecla: Captura de un tecla de teclado
 //---------------------------------------------------------------
 private static int pulsacionTecla () throws Exception {
    try {
    InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    BufferedReader reader = new BufferedReader(inputStreamReader);
    int c = reader.read();
    return c;
    }
    catch (IOException e) {
    throw e;
    }
 }
 public static void escribir_archivo (ArrayList<String> list) throws IOException{
        try {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("clientes.dat"))) {
                for(String x:list)
                {
                    writer.write(x);
                    writer.newLine();
                }
            }
        }catch(IOException e)
        {
            e.getStackTrace();
         }
 }
}
