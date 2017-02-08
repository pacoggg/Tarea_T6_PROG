/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa;

import java.io.*;

/**
 *
 * @author Paco G.
 */
public class modulos {
   
   private String nif;
   private String nombre;
   private String telefono;
   private String direccion;
   private String deuda;

    public modulos() {
    }

    public modulos(String nif, String nombre, String telefono, String direccion, String deuda) {
        this.nif = nif;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.deuda = deuda;
    }
    
   public void escribir() throws IOException{
    File f;
    FileWriter fich;
    BufferedWriter bw;
    PrintWriter pw;
    f = new File("clientes.dat");
    fich = new FileWriter(f,true);
    bw= new BufferedWriter(fich);
    pw = new PrintWriter(bw);
    pw.write("NIF: "+nif+" "+"NOMBRE: "+nombre+" "+"TELEFONO: "+telefono+" "+"DIRECCION: "+direccion+" "+"DEUDA: "+deuda+"\n");
    pw.close();
    bw.close();
    
   }
   public void listar() throws IOException{
       File f;
       FileReader fr;
       BufferedReader br;
       
       f= new File("clientes.dat");
       fr=new FileReader(f);
       br=new BufferedReader(fr);
       String linea;
       while((linea=br.readLine())!=null){
           System.out.println(linea); 
       }
       br.close();
       fr.close();
   }
   
}
