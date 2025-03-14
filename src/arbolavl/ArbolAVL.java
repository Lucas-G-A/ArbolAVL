/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package arbolavl;

import java.util.ArrayList;

/**
 *
 * @author lucasgarcia
 */
public class ArbolAVL <T extends Comparable <T>> {
    private NodoAVL<T> raiz;
    private NodoAVL<T> papa;
    private int cont;
    
    public ArbolAVL(){
        raiz=null;
        papa=null;
        cont=0;
    }
    public ArbolAVL(NodoAVL<T> raiz){
        this.raiz=raiz;
        papa=null;
        cont=1;
    }
    public ArbolAVL(NodoAVL<T> raiz, NodoAVL<T> papa){
        this.raiz=raiz;
        this.papa=papa;
        cont=1;
    }
    
    public void inserta(T elem){
        NodoAVL<T> actual = raiz;
        if(raiz==null){
            raiz=new NodoAVL(elem);
            return;
        }
        boolean encontrado=false;
        NodoAVL<T> padre=actual;
        while(actual!=null && !encontrado){
            padre=actual;
            if(elem.compareTo(actual.getElem())<0)
                actual=actual.getIzq();
            else{
                if(elem.compareTo(actual.getElem())>0)
                    actual=actual.getDer();
                else
                    encontrado = true;
            }
        }
        if(encontrado)
            return;
        NodoAVL<T> nuevo = new NodoAVL(elem);
        if(elem.compareTo(padre.getElem())<=0){
            padre.setIzq(nuevo);
        }
        else{
            padre.setDer(nuevo);
        }
        nuevo.setPapa(padre);
        cont++;
        nuevo=nuevo.getPapa();
        int izq,der;
        boolean termine=false;
        while(nuevo!=null && !termine){
            System.out.println("entro con: " + nuevo.getElem());
            izq=0;
            der=0;
            if(nuevo.getIzq()!=null)
                izq=nuevo.getIzq().calculaAltura();
            if(nuevo.getDer()!=null)
                der=nuevo.getDer().calculaAltura();
            nuevo.setAvl(der-izq);
            if(der-izq < -1 || der-izq >1){
                rota(nuevo);
                System.out.println("roto");
            }
            nuevo=nuevo.getPapa();
        }
    }
    
    private NodoAVL<T> rota(NodoAVL<T> actual){
            NodoAVL<T> papa,alfa,beta,gamma,a,b,c,d;
            papa=actual.getPapa();
        if(actual.getAvl()==-2 && actual.getIzq().getAvl()<0){ // IZQ-IZQ
            System.out.println("IZQ-IZQ");
            alfa=actual;
            beta=alfa.getIzq();
            gamma=alfa.getIzq();
            a = gamma.getIzq();
            b = gamma.getDer();
            c = beta.getDer();
            d = alfa.getDer();
            alfa.setIzq(c);
            if(c!=null)
                c.setPapa(alfa);
            alfa.setDer(d);
            if(d!=null)
                d.setPapa(alfa);
            beta.setIzq(gamma);
            gamma.setPapa(beta);
            beta.setDer(alfa);
            alfa.setPapa(beta);
            if(papa!=null){
                if(beta.getElem().compareTo(papa.getElem())<0)
                    papa.setIzq(beta);
                else
                    papa.setDer(beta);
            }
            return beta;
        }
        else if(actual.getAvl()==-2 && actual.getIzq().getAvl()>0){ //IZQ-DER
            System.out.println("IZQ-DER");
            alfa=actual;
            beta=actual.getIzq();
            gamma=beta.getDer();
            a = beta.getIzq();
            b=gamma.getIzq();
            c=gamma.getDer();
            d=alfa.getDer();
            beta.setIzq(a);
            a.setPapa(beta);
            beta.setDer(b);
            b.setPapa(beta);
            gamma.setIzq(beta);
            beta.setPapa(gamma);
            alfa.setIzq(c);
            c.setPapa(alfa);
            alfa.setDer(d);
            d.setPapa(alfa);
            gamma.setDer(alfa);
            alfa.setPapa(gamma);
            if(papa!=null){
                if(gamma.getElem().compareTo(papa.getElem())<=0)
                    papa.setIzq(gamma);
                else
                    papa.setDer(gamma);
            }
            return gamma;
        }
        else if(actual.getAvl()==2 && actual.getDer().getAvl()<0){ //DER-IZQ
            System.out.println("DER-IZQ");
            alfa = actual;
            a = alfa.getIzq();
            beta = alfa.getDer();
            gamma = beta.getIzq();
            b = gamma.getIzq();
            c = gamma.getDer();
            d = beta.getDer();
            alfa.setDer(b);
            b.setPapa(alfa);
            beta.setIzq(c);
            c.setPapa(beta);
            beta.setDer(d);
            d.setPapa(beta);
            gamma.setIzq(alfa);
            alfa.setPapa(gamma);
            gamma.setDer(beta);
            beta.setPapa(gamma);
            if(papa!=null){
                if(gamma.getElem().compareTo(papa.getElem())<0)
                    papa.setIzq(gamma);
                else
                    papa.setDer(gamma);
                gamma.setPapa(papa);
                //actualizar alturas
                return gamma;
            }
        }
        else if (actual.getAvl()==2 && actual.getDer().getAvl()>0){ //DER-DER
            System.out.println("DER-DER");
        }
        return actual; //ACABAR ROTACIONES
    }
    
    private void borraAVL(NodoAVL<T> actual){
        if(actual == null){
            return;
        }
        if(actual.getIzq()==null && actual.getDer()==null){ //cero hijos
            if(actual.equals(raiz)){
                raiz=null;
            }
            else{
                NodoAVL<T> padre = actual.getPapa();
                if(padre.getIzq().equals(actual))
                    padre.setIzq(null);
                else
                    padre.setDer(null);
            }
            cont--;
        }
        else{
            if(actual.getIzq()!=null && actual.getDer()!=null){ //dos hijos
                NodoAVL<T> suc=actual.getDer();
                while(suc.getIzq()!=null)
                    suc=suc.getIzq();
                T temp = suc.getElem();
                actual.setElem(temp);
                NodoAVL<T> padre = actual.getPapa();
                if(suc.equals(actual.getDer()))
                    padre.setIzq(suc.getDer());
                else
                    padre.setDer(actual.getDer());
                cont--;
                if(suc.getDer()!=null){
                    suc.getDer().setPapa(padre);
                }
            }
            else{ //un hijo
                NodoAVL<T> hijo;
                if(actual.getIzq()!=null){ //solo izq
                    hijo=actual.getIzq();
                }
                else{ // solo der
                    hijo=actual.getDer();
                }
                if(actual.equals(raiz)){
                    raiz=hijo;
                    cont--;
                    return;
                }
                NodoAVL<T> padre = actual.getPapa();
                if(padre.getIzq().equals(actual))
                    padre.setIzq(hijo);
                else
                    padre.setDer(hijo);
                hijo.setPapa(padre);
                cont--;
            }
        }
    }
    private NodoAVL<T> busqueda(NodoAVL<T> a, T dato){
        if(a==null)
            return null;
        if(a.getElem().equals(dato))
            return a;
        else{
            if(a.getElem().compareTo(dato)>0)
                return busqueda(a.getIzq(),dato);
            else
                return busqueda(a.getDer(), dato);
        }
    }
    
        public void inOrden(){
        ArrayList<T> datos = new ArrayList<>();
        inOrden(raiz, datos);
        for(T es: datos){
            System.out.println(es);
        }       
    }
    private void inOrden(NodoAVL<T> act, ArrayList<T> lista){
        if(act==null){
            return;
        }
        inOrden(act.getIzq(), lista);
        lista.add(act.getElem());
        inOrden(act.getDer(), lista);
    }
    
    public int calculaAltura(){
        return calculaAltura(raiz, 0);
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
    
}
