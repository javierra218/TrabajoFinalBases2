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
class ListaCD <T> implements Iterable<T> {
    
    //Representa el Nodo cabecera de la Lista, no posee informacion 
    private NodoD<T> cabeza;  
    
    //Representa el tamaño de la Lista
    private int tamanio=0; 

    
    /*Constructor de la Clase Lista Circular Doble Enlazada, Crea un nodo que sirve como cabezaecera de la ListaCD*/
    public ListaCD() {
        this.cabeza = new NodoD<T>(null, null, null);
        this.cabeza.setSig(cabeza);
        cabeza.setAnt(cabeza);
    }
    
    //inserta un elemento al incio de la lista, dato es la informacion que se va a almacenar debe ser un objeto
    public void insertarAlInicio(T dato) {
        NodoD<T> x = new NodoD<T>(dato, cabeza.getSig(), cabeza);
        cabeza.setSig(x);
        x.getSig().setAnt(x);
        this.tamanio++;
    }
    
    //inserta al final, dato informacion que se va a almacenar 
    public void insertarAlFinal(T dato){
        NodoD<T>x=new NodoD<T>(dato, cabeza, cabeza.getAnt());
        cabeza.getAnt().setSig(x);
        cabeza.setAnt(x);
        this.tamanio++;
    }
    
    //Metodo que inserta un Elemento  de manera Ordenada desde la cabeza de la Lista
    //info informacion de forma ordenada
     public void insertarOrdenado(T info){
        if (this.esVacia())
            this.insertarAlInicio(info);
        else{
            NodoD<T> x=this.cabeza;
            NodoD<T> y=x;
            x = x.getSig();
            while(x!=this.cabeza){
                Comparable comparador=(Comparable)info;
                int rta=comparador.compareTo(x.getInfo());
                if(rta<0)
                    break;
                y=x;
                x=x.getSig();
            }
            if(x==cabeza.getSig())
                this.insertarAlInicio(info);
            else{
                y.setSig(new NodoD<T>(info, x, y));
                x.setAnt(y.getSig());
                this.tamanio++;
                }
            }
     }
     
     
    //agregar metodo para eliminar y vaciar
     
     //retorna el objeto en pos i
      public T get(int i){
        try {
                NodoD<T> x=this.getPos(i);
                if(x==null)
                    return (null);
                return(x.getInfo());
            }catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
            return (null);
        }
      
      //Metodo que modifica el elemento que se encuentre en una posicion dada
      public void set(int i, T dato){
        try{
            NodoD<T> t=this.getPos(i);        
            if(t!=null)
                 t.setInfo(dato);
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }

      //conocer tam de lista
    public int getTamanio() {
        return tamanio;
    }

    //para ver si en la lista hay algo
    public boolean esVacia(){
        return(cabeza==cabeza.getSig() || this.getTamanio()==0);
    }
    
    
    //Metodo que permite buscar un elemento en la lista si lo encuentra retorna true, de lo contrario false
    public boolean esta(T info)
    {
        return (this.getIndice(info)!=-1);
    }
    
    
    //Metodo que permite obtiene Iterador para una Lista Circular Doble
    
    @Override//asdasdasd
    public Iterator<T> iterator(){
        return (new IteratorLCD<T>(this.cabeza));
    }
    
    //Metodo que permite retornar la informacion de una Lista en un Vector
    public Object[] aVector(){
         if(this.esVacia())
                return (null);
        Object vector[]=new Object[this.getTamanio()];
        Iterator<T> it=this.iterator();
        int i=0;
        while(it.hasNext())
            vector[i++]=it.next();
        return(vector);
    }
    
    
    //Metodo que permite retornar toda la informacion de los elementos de la Lista Circular Doble en un String
    @Override
    public String toString(){
        if (this.esVacia())
            return ("Lista Vacia");
        String r="";
        for(NodoD<T> x=this.cabeza.getSig();x.getInfo()!=null;x=x.getSig())
            r+=x.getInfo().toString()+"<->";
        return(r);
    }
    
    //Metodo de tipo private, que retorna un nodo con la posicion de este en la lista y ejecuta una exception si sucede un error
     @SuppressWarnings("empty-statement")
    private NodoD<T> getPos(int i)throws Exception{
        if(i<0||i>=this.tamanio){
            System.err.println("Error indice no valido en una Lista Circular Doblemente Enlazada");
            return (null);
        }
        NodoD<T> x=cabeza.getSig();
        for( ; i-->0; x=x.getSig());
        return x;
    }
    
    //Metodo que busca un elemento de la lista y devuelve su posicion.Los objetos que se almacenan en la lista deben tener el Método equals
    public int getIndice(T dato)    {
        int i=0;
        for(NodoD<T> x=this.cabeza.getSig();x!=this.cabeza;x=x.getSig()){
            if(x.getInfo().equals(dato))
                return(i);
            i++;
        }
        return (-1);
    }     

    
    
}
