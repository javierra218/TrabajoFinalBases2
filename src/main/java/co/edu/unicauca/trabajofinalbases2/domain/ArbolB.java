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
public class ArbolB<T> {

    //orden del árbol
    private int n;

    //numero maximo de llaves
    private int m;

    //numero maximo de apuntadores
    private int m1;

    //raiz del arbol  
    private Pagina raiz;

    //Crea un arbol B  vacio con orden por defecto de 2.
    public ArbolB() {
        this.raiz = null;
        this.n = 2;
        this.m = n * 2;
        this.m1 = (this.m) + 1;
    }

    //Crea un arbol B  vacio con orden especifico
    public ArbolB(int n) { //n indica el orden del arbol
        if (n <= 0) {
            System.err.println("Tamano del orden del arbol no es válido");
            return;
        }
        this.raiz = null;
        this.n = n;
        this.m = n * 2;
        this.m1 = (this.m) + 1;
    }

    //generar getter and setter
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

    public Pagina getRaiz() {
        return raiz;
    }

    public void setRaiz(Pagina raiz) {
        this.raiz = raiz;
    }

    //Metodo que permite insertar un nuevo dato en el arbol B
    //x es el dato a insertar en el arbol
    //retorna la pagina donde se inserto x
    public boolean insertar(T x) {
        //pila para guarddar el camino desde la raiz hasta la pagina donde se inserta x
        Pila<Pagina> pila = new Pila<Pagina>();
        //Para trabajar subir y subir1 por referencia se usa si la pagina se rompe
        T[] subir = (T[]) new Object[1];
        T[] subir1 = (T[]) new Object[1];
        //variables auxiliares 
        int posicion = 0, i = 0, terminar, separar;
        Pagina p = null, nuevo = null, nuevo1;
        if (this.raiz == null) {
            this.raiz = this.crearPagina(x);
        } else {
            posicion = buscar(this.raiz, x, pila);
            if (posicion == -1) {
                return (false);
            } else {
                terminar = separar = 0;
                while ((!pila.esVacia()) && (terminar == 0)) {
                    p = pila.desapilar();
                    if (p.getCont() == this.m) {
                        if (separar == 0) {
                            nuevo = romper(p, null, x, subir);
                            separar = 1;
                        } else {
                            nuevo1 = romper(p, nuevo, subir[0], subir1);
                            subir[0] = subir1[0];
                            nuevo = nuevo1;
                        }
                    } else {
                        if (separar == 1) {
                            separar = 0;
                            i = donde(p, subir[0]);
                            i = insertar(p, subir[0], i);
                            cderechaApunt(p, i + 1);
                            p.getApuntadores()[i + 1] = nuevo;

                        } else {
                            posicion = insertar(p, x, posicion);
                        }
                        terminar = 1;
                    }
                }
                if ((separar == 1) && (terminar == 0)) {
                    this.setRaiz(this.crearPagina(subir[0]));
                    this.raiz.getApuntadores()[0] = p;
                    this.raiz.getApuntadores()[1] = nuevo;
                }
            }
        }
        return (true);
    }

    //este metodo que permite insertar una pagina en el arbol B
    private int insertar(Pagina p, T x, int i) {   //p pagina donde inserta,x apuntador a una pagina, i indica la pos donde se quiere insertar
        int j;
        if (p.getCont() != 0) {
            int compara = ((Comparable) p.getInfo()[i]).compareTo(x);
            if (compara < 0) {
                i++;
            } else {
                j = p.getCont() - 1;
                while (j >= i) {
                    p.getInfo()[j + 1] = p.getInfo()[j];
                    j = j - 1;
                }
            }
        }
        p.setCont(p.getCont() + 1);
        p.getInfo()[i] = x;
        return (i);
    }

    //Metodo que permite retirar un dato del arbol indicada
    private void retirar(Pagina p, int i) {
        while (i < p.getCont() - 1) {
            p.getInfo()[i] = p.getInfo()[i + 1];
            i++;
        }
        p.setCont(p.getCont() - 1);
    }

    //permite crear una pagina en memoria
    private Pagina crearPagina(T x) { //x info que tendra la nueva hoja
        Pagina p = new Pagina(n);
        inicializar(p);
        p.setCont(1);
        p.getInfo()[0] = x;
        return (p);
    }

    //permite inicializar una pagina 
    private void inicializar(Pagina p) { //p apunta a una pagina sin inicializar
        int i = 0;
        p.setCont(0);
        while (i < this.m1) {
            p.getApuntadores()[i++] = null;
        }
    }

    //evalua si hay dato dentro del arbol
    public boolean esta(T dato) {
        Pila pi = new Pila();
        return (this.esta(this.raiz, dato, pi) != (-1));
    }

    //determina si un elemento esta dentro del arbol    
    private int esta(Pagina p, T x, Pila<Componente> pila) {  //p contiene la pagina de la busqueda,x posicion del apuntador de las paginas, pi almacena el camino de la busqueda de x
        int i = 0;
        boolean encontro = false;
        int posicion = -1;
        while ((p != null) && !encontro) {
            i = 0;
            int compara = ((Comparable) p.getInfo()[i]).compareTo(x);
            while ((compara < 0) && (i < (p.getCont() - 1))) {
                i++;
                compara = ((Comparable) p.getInfo()[i]).compareTo(x);
            }
            if ((compara > 0)) {
                pila.apilar(new Componente(p, i));
                p = p.getApuntadores()[i];
            } else if ((compara < 0)) {
                pila.apilar(new Componente(p, i + 1));
                p = p.getApuntadores()[i + 1];
            } else {
                pila.apilar(new Componente(p, i));
                encontro = true;
            }
        }
        if (encontro) {
            posicion = i;
        }
        return (posicion);
    }

    /* Metodo que permite encontrar un elemento se encuentra en el arbol
     Se realizo una busqueda de X en el arbol
     pagina que contiene las paginas de la busqueda
     x posicion del apuntador de las paginas
     pi estructura que conteniene el camino de la busqueda de X
     posicion de X dentro de la pagina donde se encontro, de no ser asi retorna -1
     */
    private int buscar(Pagina p, T x, Pila pila) {
        int i = -1, posicion;
        boolean encontro = false;
        posicion = -1;
        while ((p != null) && !(encontro)) {
            pila.apilar(p);
            i = 0;
            int compara = ((Comparable) p.getInfo()[i]).compareTo(x);
            while ((compara < 0) && (i < (p.getCont() - 1))) {
                i++;
                compara = ((Comparable) p.getInfo()[i]).compareTo(x);
            }
            if ((compara > 0)) {
                p = p.getApuntadores()[i];
            } else {
                if (compara < 0) {
                    p = p.getApuntadores()[i + 1];
                } else {
                    encontro = true;
                }
            }
        }
        if (!encontro) {
            posicion = i;
        }
        return (posicion);
    }

    //Metodo que indica el lugar fisico donde se debe insertar el dato x
    // p pagina posible a insertar
    //x dato a insertar
    protected int donde(Pagina p, T x) {
        int i;
        i = 0;
        int compara = ((Comparable) p.getInfo()[i]).compareTo(x);
        while ((compara < 0) && (i < (p.getCont() - 1))) {
            i++;
            compara = ((Comparable) p.getInfo()[i]).compareTo(x);
        }
        return i;
    }

    /*Metodo que permite romper una pagina del arbol en dos, para mantener su orden
      p pagina a romper. <br>
      t apuntador de que ser null la pagina es una hoja del arbol
      x dato a insertar en el arbol
      subir contenedor de la pagina a ascender en el arbol*/
    private Pagina romper(Pagina p, Pagina t, T x, T[] subir) {
        T[] a = (T[]) new Object[m1];
        int i = 0;
        boolean s = false;
        Pagina[] b = new Pagina[this.m1 + 1];
        while (i < this.m && !s) {
            int compara = ((Comparable) p.getInfo()[i]).compareTo(x);
            if (compara < 0) { //<-- X es mayor que el dato del arbol
                a[i] = (T) p.getInfo()[i];
                b[i] = p.getApuntadores()[i];
                p.getApuntadores()[i++] = null;
            } else {
                s = true;
            }
        }
        a[i] = x;
        b[i] = p.getApuntadores()[i];
        p.getApuntadores()[i] = null;
        b[++i] = t;
        while ((i <= this.m)) {
            a[i] = (T) p.getInfo()[i - 1];
            b[i + 1] = p.getApuntadores()[i];
            p.getApuntadores()[i++] = null;
        }
        Pagina q = new Pagina(this.n);
        inicializar(q);
        p.setCont(n);
        q.setCont(n);
        i = 0;
        while (i < this.n) {
            p.getInfo()[i] = a[i];
            p.getApuntadores()[i] = b[i];
            q.getInfo()[i] = a[i + n + 1];
            q.getApuntadores()[i] = b[i + n + 1];
            i++;
        }
        p.getApuntadores()[n] = b[n];
        q.getApuntadores()[n] = b[m1];
        subir[0] = a[n];
        return (q);
    }

    //Metodo que permite correr los apuntadores hacia la izquierda
    protected void cizquierda_apunt(Pagina p, int i, int j) {
        while (i < j) {
            p.getApuntadores()[i] = p.getApuntadores()[i + 1];
            i++;
        }
        p.getApuntadores()[i] = null;
    }

    //Metodo que permite correr los apuntadores hacia la derecha
    protected void cderechaApunt(Pagina p, int i) {
        int j;
        j = p.getCont();
        while (j > i) {
            p.getApuntadores()[j] = p.getApuntadores()[j - 1];
            j--;
        }
    }

    //metodos cambio y unir
    // Metodo que permite realizar las modificaciones al eliminar un dato, para que el arbol conserve sus caracteristicas
    protected void cambio(Pagina p, Pagina q, Pagina r, int i, T x) {
        int k;
        T t;
        int compara = ((Comparable) r.getInfo()[r.getCont() - 1]).compareTo(x);
        if (compara < 0) {
            t = (T) q.getInfo()[i];
            retirar(q, i);
            k = 0;
            k = insertar(p, t, k);
            t = (T) r.getInfo()[r.getCont() - 1];
            retirar(r, r.getCont() - 1);
            k = i;
            if (k == -1) {
                k = 0;
            }
            k = insertar(q, t, k);
        } else {
            t = (T) q.getInfo()[i];
            retirar(q, i);
            k = p.getCont() - 1;
            if (k == -1) {
                k = 0;
            }
            k = insertar(p, t, k);
            t = (T) r.getInfo()[0];
            retirar(r, 0);
            k = i;
            if (q.getCont() != 0) {
                if (k > q.getCont() - 1) {
                    k = q.getCont() - 1;
                }
            }
            k = insertar(q, t, k);
        }
    }

    private void unir(Pagina q, Pagina r, Pagina p, int i, Pila<Componente> pila, T x, int posicion) {
        int terminar = 0, j = 0, k;
        Pagina t = null;
        Componente objeto = new Componente();
        retirar(p, posicion);
        int compara = ((Comparable) r.getInfo()[0]).compareTo(x);
        if (compara > 0) {
            t = p;
            p = r;
            r = t;
        }
        while (terminar == 0) {
            /*1*/ if ((r.getCont() < this.n) && (p.getCont() > this.n)) {
                cambio(r, q, p, i, x);
                r.getApuntadores()[r.getCont()] = p.getApuntadores()[0];
                this.cizquierda_apunt(p, 0, p.getCont() + 1);
                terminar = 1;
            } /*2*/ else if ((p.getCont() < this.n) && (r.getCont() > this.n)) {
                cambio(p, q, r, i, x);
                this.cderechaApunt(p, 0);
                p.getApuntadores()[0] = r.getApuntadores()[r.getCont() + 1];
                r.getApuntadores()[r.getCont() + 1] = null;
                terminar = 1;
            } else {
                j = r.getCont();
                r.getInfo()[j++] = q.getInfo()[i];
                k = 0;
                while (k <= p.getCont() - 1) {
                    r.getInfo()[j++] = (T) p.getInfo()[k++];
                }
                r.setCont(j);
                this.retirar(q, i);
                k = 0;
                j = this.m - p.getCont();
                while (p.getApuntadores()[k] != null) {
                    r.getApuntadores()[j++] = p.getApuntadores()[k++];
                }
                p = null;
                /*3*/ if (q.getCont() == 0) {
                    q.getApuntadores()[i + 1] = null;
                    /*4*/ if (pila.esVacia()) {
                        q = null;
                    }
                } else {
                    this.cizquierda_apunt(q, i + 1, q.getCont() + 1);
                }
                /*5*/ if (q != null) {
                    /*6*/ if (q.getCont() >= this.n) {
                        terminar = 1;
                    } else {
                        t = q;
                        /*7*/ if (!pila.esVacia()) {
                            objeto = pila.desapilar();
                            q = objeto.getP();
                            i = objeto.getV();
                            compara = ((Comparable) q.getInfo()[0]).compareTo(x);
                            if (compara <= 0) {
                                p = t;
                                r = q.getApuntadores()[i - 1];
                                i = i - 1;
                            } else {
                                r = t;
                                p = q.getApuntadores()[i + 1];
                            }
                        } else {
                            terminar = 1;
                        }
                    }
                } else {
                    terminar = 1;
                    this.setRaiz(r);
                }
            }
        }
    }

    //Metodo que retorna un iterador con las hojas del Arbol B
    public ListaCD<T> getHojas() {
        ListaCD<T> l = new ListaCD();
        getHojas(this.raiz, l);
        return l;
    }

    //Metodo de tipo privado que retorna un iterador con las hojas del Arbol B
    private void getHojas(Pagina<T> r, ListaCD<T> l) {
        if (r == null) {
            return;
        }
        if (this.esHoja(r)) {
            for (int j = 0; j < r.getCont(); j++) {
                l.insertarAlFinal(r.getInfo()[j]);
            }
        }
        for (int i = 0; i < r.getCont() + 1; i++) {
            getHojas(r.getApuntadores()[i], l);
        }
    }

    //Metodo que retorna un iterador con las hojas del Arbol B
    public int contarHojas() {
        return contarHojas(this.raiz);
    }

    //Metodo de tipo privado que retorna un iterador con las hojas del Arbol B
    private int contarHojas(Pagina<T> r) {
        if (r == null) {
            return 0;
        }
        int cont = 0;
        if (this.esHoja(r)) {
            cont++;
        }
        for (int i = 0; i < r.getCont() + 1; i++) {
            cont += contarHojas(r.getApuntadores()[i]);
        }
        return (cont);
    }

    //Metodo que permite determinar si una pagina es hoja
    protected boolean esHoja(Pagina p) {
        int j = 0;
        while ((p.getApuntadores()[j] == null) && (j < (p.getCont() - 1))) {
            j++;
        }
        return (p.getApuntadores()[j] == null);
    }

    //Metodo que permite conocer si un Arbol B se encuenta vacio
    public boolean esVacio() {
        return (this.raiz == null);
    }

    //Metodo que permite obtener el peso del Arbol B
    public int getPeso() {
        if (this.esVacio()) {
            return (0);
        }
        return (getPeso(this.getRaiz()));
    }

    //Metodo de tipo privado que permite conocer el numero de elemento del Arbol B
    private int getPeso(Pagina<T> r) {
        if (r == null) {
            return 0;
        }
        int cont = 0;
        cont += r.getCont();
        for (int i = 0; i < r.getCont() + 1; i++) {
            cont += getPeso(r.getApuntadores()[i]);
        }
        return (cont);
    }

    //Metodo que permite obtener la altura del Arbol B
    public int getAltura() {
        return (getAltura(this.getRaiz()));
    }

    //Metodo de tipo privado que permite conocer la altura del Arbol B
    private int getAltura(Pagina<T> r) {
        int alt = 0;
        while (r != null) {
            alt++;
            r = r.getApuntadores()[0];
        }
        return (alt);
    }

    //imprime el arbolB
    public String imprime() {
        String msg = "";
        return (this.imprime(this.raiz, msg));
    }

    //metodo que permite imprimir el arbolB
    private String imprime(Pagina r, String msg) {
        int i = 0;
        while (i <= r.getCont()) {
            if (r.getApuntadores()[0] == null) {
                msg += r.toString();
                break;
            } else {
                msg += r.toString() + "  pagina = " + i + "   ES =" + r.getApuntadores()[i].toString() + "\n";

                if (!this.esHoja(r.getApuntadores()[i])) {
                    msg += this.imprime(r.getApuntadores()[i], msg);
                }
            }
            i++;
        }
        return msg;
    }

    public Iterator<T> inOrden() {
        ListaCD<T> l = new ListaCD();
        inOrden(this.raiz, l);
        return (l.iterator());
    }

    //Metodo que retorna un iterador con el recorrido in Orden del Arbol
    private void inOrden(Pagina<T> r, ListaCD<T> l) {
        if (r == null) {
            return;
        }
        inOrden(r.getApuntadores()[0], l);
        for (int j = 0; j < r.getCont(); j++) {
            l.insertarAlFinal(r.getInfo()[j]);
        }
        for (int i = 1; i < r.getCont() + 1; i++) {
            inOrden(r.getApuntadores()[i], l);
        }
    }

}
