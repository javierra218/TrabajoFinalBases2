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
public class Pagina<T> {
    
    //orden del arbol
    private int n; 
    
    //numero maximo de llaves
    private int m; 
    
    
    //numero maximo de apuntadores
    private int m1;
    

    //numero de llaves de pagina
    private int cont;  
    
    //llaves clasificadas ascendentemente
    private T[] info; 
    
    //direcciones de los hijos de la pagina 
    private Pagina[] apuntadores; 
    
    
    
    //Crear Pagina con orden especifico
    public Pagina(int n){ //n indica numero de orden de la pagina
        this.n = n;
        this.m = n*2;
        this.m1 = this.m+1;
        this.info= (T[]) new Object[m];
        for(int i=0; i<info.length;i++){
            info[i]=null;
        }
        this.apuntadores = new Pagina[this.m1];
        for(int i=0; i<apuntadores.length;i++){
            apuntadores[i]=null;
        }
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getM1() {
        return m1;
    }

    public void setM1(int m1) {
        this.m1 = m1;
    }

    public int getCont() {
        return cont;
    }

    public void setCont(int cont) {
        this.cont = cont;
    }

    public T[] getInfo() {
        return info;
    }

    public void setInfo(T[] info) {
        this.info = info;
    }

    public Pagina[] getApuntadores() {
        return apuntadores;
    }

    public void setApuntadores(Pagina[] apuntadores) {
        this.apuntadores = apuntadores;
    }
    
   @Override
    public String toString(){
        String msg= "  Informacion de la pagina";
        int i=0;
        while(i<this.getCont()){
            msg+=" --> "+this.info[i++].toString();
        }
        return msg;
    }
    
    
    
}
