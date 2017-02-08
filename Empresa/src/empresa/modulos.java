/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa;

import java.io.*;
import java.util.StringTokenizer;

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
    String datos[] = {"NIF: ", "NOMBRE: ", "TELEFONO: ", "DIRECCION: ", "DEUDA: "};
    String cliente_datos[] = new String[5];

    public modulos() {
    }

    public modulos(String nif, String nombre, String telefono, String direccion, String deuda) {
        this.nif = nif;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.deuda = deuda;
    }

    public void escribir() throws IOException {
        File f;
        FileWriter fich;
        BufferedWriter bw;
        PrintWriter pw;
        f = new File("clientes.dat");
        fich = new FileWriter(f, true);
        bw = new BufferedWriter(fich);
        pw = new PrintWriter(bw);
        pw.write(nif + "-" + nombre + "-" + telefono + "-" + direccion + "-" + deuda + "\n");
        pw.close();
        bw.close();
        //pw.write("NIF: "+nif+" "+"NOMBRE: "+nombre+" "+"TELEFONO: "+telefono+" "+"DIRECCION: "+direccion+" "+"DEUDA: "+deuda+"\n");
    }

    public void listar() throws IOException {
        File f;
        BufferedReader br = null;

        try {
            f = new File("clientes.dat");
            br = new BufferedReader(new FileReader(f));
            String linea;
            int reg = 1;
            while ((linea = br.readLine()) != null) {
                System.out.println(" ---------------- Registro " + reg + ": ------------------");
                StringTokenizer st = new StringTokenizer(linea, "-");
                int s = st.countTokens();
                for (int r = 0; r < s; r++) {
                    System.out.println(datos[r] + st.nextToken());
                }
                reg++;
            }
        } catch (IOException e) {
            System.err.println("Error");
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }

    public void buscar(String baliza) throws IOException {
        File f;
        BufferedReader br = null;

        try {
            f = new File("clientes.dat");
            br = new BufferedReader(new FileReader(f));
            String linea;
            int reg = 1;
            while ((linea = br.readLine()) != null) {
                System.out.println(" ---------------- Registro " + reg + ": ------------------");
                StringTokenizer st = new StringTokenizer(linea, "-");
                int s = st.countTokens();
                for (int r = 0; r < s; r++) {
                    cliente_datos[r] = st.nextToken();
                }
                //String baliza = "07877298M";
                if (cliente_datos[0].equals(baliza)) {
                    for (int r1 = 0; r1 < s; r1++) {
                        System.out.println(datos[r1] + cliente_datos[r1]);
                    }
                } else {
                    System.out.println("El registro no coincide con lo solicitado");
                }
                reg++;
            }
        } catch (IOException e) {
            System.err.println("Error");
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }

}
