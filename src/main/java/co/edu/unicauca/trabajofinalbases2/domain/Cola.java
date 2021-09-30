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
public class Cola<T> {
    
    //Nodo incial de la Cola
    private NodoD<T> inicio;          
    
    //Tamaño de la cola 
    private int tamanio;    
    
    
    public Cola(){
        this.inicio=new NodoD<T> (null,null,null);
        this.inicio.setSig(inicio);
        inicio.setAnt(inicio); 
        this.tamanio=0;
    }
    
    public void enColar(T info){
        NodoD<T>x=new NodoD<T>(info,inicio,inicio.getAnt());
        inicio.getAnt().setSig(x);
        inicio.setAnt(x);
        this.aumentarTamanio();
    }
    
    public T deColar(){ 
        if(this.esVacia())
            return (null);
        NodoD<T> x=this.inicio.getSig();	   
        this.inicio.setSig(x.getSig());
        x.getSig().setAnt(inicio);
        x.setSig(null);
        x.setAnt(null);
        this.tamanio--;
        return(x.getInfo());     
    }
    
    public void vaciar(){
        this.inicio.setSig(this.inicio);
        this.inicio.setAnt(this.inicio); 
        this.tamanio=0;
    }
    
    protected NodoD<T> getInicio(){
        return this.inicio;
    }
    
    public T getInfoInicio(){
        return this.inicio.getSig().getInfo();
    }
    
    protected void aumentarTamanio() {
        this.tamanio++;
    }
    
    protected void setInicio(NodoD<T> ini){
        this.inicio =  ini;
    }
    
    public int getTamanio(){
        return(this.tamanio);
    }
    
    public boolean esVacia(){
         return(this.getTamanio()==0);           
    }
    
    @Override
    public String toString( )
    {
        String msj ="";
        NodoD<T> c = this.inicio.getSig();
        while(c != inicio){
            msj += c.getInfo().toString()+"->";
            c = c.getSig();
        }
        return msj;
    }
    
}
