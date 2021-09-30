/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicauca.trabajofinalbases2.utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Javier
 */
public class Archivo {
    
    private PrintWriter archivoSalida;
    private FileWriter archivoEscritura;
    private FileReader archivoLectura;
    private BufferedReader archivoEntrada;

    public Archivo() {
        archivoEscritura = null;
        archivoLectura = null;
        archivoEntrada = null;
        archivoSalida = null;
    }

    public List<String> leer(String nombre) {
        String linea;
        List<String> datos = new ArrayList<>();
        try {
            archivoLectura = new FileReader(nombre);
            archivoEntrada = new BufferedReader(archivoLectura);
            while ((linea = archivoEntrada.readLine()) != null) {
                datos.add(linea);
            }
            archivoEntrada.close();
        } catch (IOException ex) {
            System.out.print("Error: " + ex.getMessage());
        }
        return datos;
    }

    public boolean escribir(String nombre, String linea) {
        try {
            
            if (existeArchivo(nombre)) {
                archivoEscritura = new FileWriter(nombre, true);
                BufferedWriter bw = new BufferedWriter(archivoEscritura);
                archivoSalida = new PrintWriter(bw);
                bw.newLine();
            }else{
                archivoEscritura = new FileWriter(nombre, true);
                BufferedWriter bw = new BufferedWriter(archivoEscritura);
                archivoSalida = new PrintWriter(bw);
                
            }
            archivoSalida.print(linea);
            archivoSalida.close();
            return true;
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
    
    public static boolean existeArchivo(String rutaArchivo) {
		File fichero = new File(rutaArchivo);
		return fichero.exists();
	}
    
    
    
    public String leerLineaPorDato(String nombre,int dato){
        String linea="El dato no se encuentra en el archivo";
        String lineaArchivo;
        String [] datos=null;
        int line,cont=0;
        try {
            archivoLectura = new FileReader(nombre);
            archivoEntrada = new BufferedReader(archivoLectura);
            while ((lineaArchivo = archivoEntrada.readLine()) != null) {
                datos=lineaArchivo.split(" ");
                line=Integer.parseInt(datos[0]);
                if (dato==line) {
                    linea="El dato "+dato+" esta en la posicion "+(cont+1)+" del archivo";
                    break;
                }
                cont++;
            }
            archivoEntrada.close();
        } catch (IOException | NumberFormatException e) {
            System.out.print("Error: " + e.getMessage());
        }
        return linea;
    }
    
    
    public  boolean eliminarArchivo(String rutaArchivo)
    {
        boolean bandera = false;
        File fr = null;
        try
        {
        	fr=new File(rutaArchivo);
        	bandera=fr.delete();
             
        }catch (Exception e) {
        	JOptionPane.showMessageDialog(null, e);
			
		}          
        
        return bandera;
    }
    
}
