/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicauca.trabajofinalbases2.presentation;

import co.edu.unicauca.trabajofinalbases2.utilities.Archivo;
import javax.swing.JOptionPane;

/**
 *
 * @author Javier
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        int n =0;
        String p;
        boolean flag=false;
        Interfaz inf ;
        
        if (!Archivo.existeArchivo("datos.txt")) {
            do {
                p = JOptionPane.showInputDialog(" Ingrese  el numero de registros para generar el archivo");
                try {
                    n = Integer.parseInt(p);
                    flag = false;
                    if (n <= 0) {
                        JOptionPane.showMessageDialog(null, "el numero ingresado es menor o igual a cero");
                        flag = true;
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "el valor ingresado no es un numero");
                    flag = true;
                }

            } while (flag);
        }
          
        
        inf=new Interfaz(n);
        inf.setVisible(true);
        
    }
    
}
