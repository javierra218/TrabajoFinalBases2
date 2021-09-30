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
class Nodo<T> {
    
    //Informacion del Nodo
    private T info;
   
    //Siguiente Nodo 
    private Nodo<T> sig;
    
    //constructor vacio
    public Nodo(){
        this.info=null;
        this.sig=null;        
    }
    
    //constructor que inicializa
    public Nodo(T info, Nodo<T> sig){
        this.info=info;
        this.sig=sig;
    }

     ///probablemente toca cambiarlos a protegidos
    public T getInfo() {
        return info;
    }

    public Nodo<T> getSig() {
        return sig;
    }

    //nuevo Nuevo objeto que desea almacenarse en el Nodo
    public void setInfo(T nuevo) {
        this.info = nuevo;
    }

    //nuevo Nuevo Nodo siguiente
    public void setSig(Nodo<T> nuevo) {
        this.sig = nuevo;
    }
    
    
    
    
}
