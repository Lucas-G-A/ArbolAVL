/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbolavl;

/**
 *
 * @author lucasgarcia
 */
public class NodoAVL <T extends Comparable <T>>{
    private T elem;
    private NodoAVL<T> izq, der, papa;
    private int avl;
    
    public NodoAVL() {
        elem=null;
        izq=null;
        der=null;
        papa=null;
        avl=0;
    }
    public NodoAVL(T elem) {
        this();
        this.elem = elem;
    }
    public NodoAVL(T elem, NodoAVL<T> izq, NodoAVL<T> der, NodoAVL<T> papa) {
        this.elem = elem;
        this.izq = izq;
        this.der = der;
        this.papa = papa;
        avl=0;
    }

    public T getElem() {
        return elem;
    }

    public void setElem(T elem) {
        this.elem = elem;
    }

    public NodoAVL<T> getIzq() {
        return izq;
    }

    public void setIzq(NodoAVL<T> izq) {
        this.izq = izq;
    }

    public NodoAVL<T> getDer() {
        return der;
    }

    public void setDer(NodoAVL<T> der) {
        this.der = der;
    }

    public NodoAVL<T> getPapa() {
        return papa;
    }

    public void setPapa(NodoAVL<T> papa) {
        this.papa = papa;
    }

    public int getAvl() {
        return avl;
    }

    public void setAvl(int avl) {
        this.avl = avl;
    }
    
    
    public boolean agrega(NodoAVL<T> a, T dato){
        NodoAVL<T> nuevo = new NodoAVL<>(dato);
        while(a!=null){
            if(a.getElem().equals(dato)){
                if(a.getIzq()==null)
                    a=a.getIzq();
                else{
                    
                }
            }
            else{
                if(a.getElem().compareTo(dato)>0)
                    a=a.getIzq();
                else
                    a=a.getDer();
            }
                
        }
        a=nuevo;
        return true;
    }
    public boolean busqueda(NodoAVL<T> a, T dato){
        if(a==null)
            return false;
        if(a.getElem().equals(dato))
            return true;
        else{
            if(a.getElem().compareTo(dato)>0)
                return busqueda(a.getIzq(),dato);
            else
                return busqueda(a.getDer(), dato);
        }
    }
    public int calculaAltura(){
        return calculaAltura(this, 0);
    }
    private int calculaAltura(NodoAVL<T> a, int n){
        if(a==null){
            return n;
        }
        int a1,a2;
        a1=calculaAltura(a.getIzq(),n+1);
        a2=calculaAltura(a.getDer(), n+1);
        if(a1>a2)
            return a1;
        return a2;
    }
    public static int calculaFe(NodoAVL actual){
        if(actual.getIzq()!=null && actual.getDer()!=null){
            return actual.getDer().calculaAltura()-actual.getIzq().calculaAltura();
        }
        if(actual.getIzq()==null && actual.getDer()==null)
            return 0;
        if(actual.getIzq()==null)
            return actual.getDer().calculaAltura();
        return actual.getIzq().calculaAltura();
    }
}