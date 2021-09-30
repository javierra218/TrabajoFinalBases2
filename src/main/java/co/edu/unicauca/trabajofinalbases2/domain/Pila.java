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
class Pila<T> {
    
    //Elemento que se ubica en el tope de la Pila
    private Nodo<T> tope; 
    
    //Tamaño de la Pila
    private int tamanio;
    
    //constructor vacio de la pila
    public Pila(){
        this.tope = null;
        this.tamanio = 0;
    }
    
    //insertar en la pila
    public void apilar(T info){
        if(this.esVacia())
            this.tope = new Nodo<T>(info, null);
        else
            this.tope=new Nodo<T>(info, this.tope);        
        this.tamanio++;
    }
    
    //retira y devuelve un elemento de la pila
    public T desapilar(){
        if(this.esVacia())
            return (null);
        Nodo<T> x=this.tope;
        this.tope = tope.getSig();     
        this.tamanio--;
        if(tamanio==0)
            this.tope=null;
        return(x.getInfo());
    }
    
    //elimina todo
    public void vaciar(){        
        this.tope = null; 
        this.tamanio=0;           
    }

    public T getTope(){
        return (this.tope.getInfo());
    }

    public int getTamanio() {
        return tamanio;
    }
    
    //verifica si esta vacio
    public boolean esVacia() {
        return(this.tope==null||this.tamanio==0);
    }
    
    
    //convierte a una cadena de string
    @Override
    public String toString( )
    {
        String msj ="";
        Nodo<T> p = tope;
        while(p != null){
            msj += p.getInfo().toString()+"->";
            p = p.getSig();
        }
        return msj;
    }
    
    
    
}
