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
class NodoD<T> {
    
    // Informacion del Nodo
    private T info;      
    
    //Nodo Anterior 
    private NodoD<T> ant;   
    
    //Nodo Siguiente 
    private NodoD<T> sig; 
    
    public NodoD() {
        this.info=null;
        this.ant=null;
        this.sig=null; 
    }
    
    public NodoD(T info, NodoD<T> sig, NodoD<T> ant){        
        this.info=info;
        this.sig=sig;
        this.ant=ant;        
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public NodoD<T> getAnt() {
        return ant;
    }

    public void setAnt(NodoD<T> ant) {
        this.ant = ant;
    }

    public NodoD<T> getSig() {
        return sig;
    }

    public void setSig(NodoD<T> sig) {
        this.sig = sig;
    }
    
    
    
    
}
