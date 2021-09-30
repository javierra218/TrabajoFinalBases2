/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicauca.trabajofinalbases2.domain;

/**
 *
 * @author Javier
 */
public class Componente {
    
   //pagina que contiene el apuntador a la proxima pagina
    private Pagina p; 
    
   //indice del apuntador de la proxima pagina
    private int v; 

    public Componente() {
    }

    public Componente(Pagina p, int v) {
        this.p = p;
        this.v = v;
    }

    public Pagina getP() {
        return p;
    }

    public void setP(Pagina p) {
        this.p = p;
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }
    
}
