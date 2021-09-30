/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicauca.trabajofinalbases2.domain;

import java.util.Iterator;

/**
 *
 * @author Javier
 */
public class IteratorLCD<T> implements Iterator<T> {
    
    // Nodo cabecera de la Lista
    private NodoD<T> cab;            
    
    //Nodo de la Lista a Iterar
    private NodoD<T> posicion; 
    
    //constructor con parametros
    IteratorLCD(NodoD<T> cab) {
        this.cab=cab;                       
        this.posicion=this.cab.getSig();
    }
    
    //Metodo que informa si existe otro elemento en la lista para seguir iterando
    //Se retorna si existen aun datos por iterar en la Lista
    @Override
    public boolean hasNext() {
            return (this.posicion!=this.cab);
    }
    
    //Metodo que retorna un dato de la posición actual del cursor del iterador
    @Override
    public T next() {
        if(!this.hasNext())
            return (null);
        this.posicion=this.posicion.getSig();
        return(this.posicion.getAnt().getInfo());
    }
    
    @Override
    public void remove() {}
 
    
}
