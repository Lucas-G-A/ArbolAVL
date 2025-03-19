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
        if(elem.compareTo(padre.getElem())<0){
            padre.setIzq(nuevo);
        }
        else{
            padre.setDer(nuevo);
        }
        nuevo.setPapa(padre);
        cont++;
        boolean termine=false;
        while(padre!=null && !termine){
            if(nuevo.getElem().compareTo(padre.getElem())<0)
                padre.setAvl(padre.getAvl()-1);
            else
                padre.setAvl(padre.getAvl()+1);

            if(padre.getAvl()>1 || padre.getAvl()<-1){
                padre=rota(padre);
                actualizarFE(padre);
            }
            nuevo=padre;
            padre=padre.getPapa();
        }
    }
    private void actualizarFE(NodoAVL<T> actual){
        if(actual==null)
            return;
        int izq=0,der=0;
        if(actual.getIzq()!=null)
            izq=actual.getIzq().calculaAltura();
        if(actual.getDer()!=null)
            der=actual.getDer().calculaAltura();
        actual.setAvl(der-izq);
        actualizarFE(actual.getIzq());
        actualizarFE(actual.getDer());
    }
    
    private NodoAVL<T> rota(NodoAVL<T> actual){
            NodoAVL<T> papa,alfa,beta,gamma,a,b,c,d;
            papa=actual.getPapa();
        if(actual.getAvl()==-2 && actual.getIzq().getAvl()<0){ // IZQ-IZQ
            alfa=actual;
            beta=alfa.getIzq();
            gamma=beta.getIzq();
            c = beta.getDer();
            d = alfa.getDer();
            alfa.setIzq(c);
            if(c!=null)
                c.setPapa(alfa);
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
            else{
                raiz=beta;
            }
            beta.setPapa(papa);
            return beta;
        }
        else if(actual.getAvl()==-2 && actual.getIzq().getAvl()>0){ //IZQ-DER
            alfa=actual;
            beta=actual.getIzq();
            gamma=beta.getDer();
            a = beta.getIzq();
            b=gamma.getIzq();
            c=gamma.getDer();
            d=alfa.getDer();
            beta.setIzq(a);
            if(a!=null)
                a.setPapa(beta);
            beta.setDer(b);
            if(b!=null)
                b.setPapa(beta);
            gamma.setIzq(beta);
            beta.setPapa(gamma);
            alfa.setIzq(c);
            if(c!=null)
                c.setPapa(alfa);
            alfa.setDer(d);
            if(d!=null)
                d.setPapa(alfa);
            gamma.setDer(alfa);
            alfa.setPapa(gamma);
            if(papa!=null){
                if(gamma.getElem().compareTo(papa.getElem())<=0)
                    papa.setIzq(gamma);
                else
                    papa.setDer(gamma);
            }
            else{
                raiz=gamma;
            }
            gamma.setPapa(papa);
            return gamma;
        }
        else if(actual.getAvl()==2 && actual.getDer().getAvl()<0){ //DER-IZQ
            alfa = actual;
            a = alfa.getIzq();
            beta = alfa.getDer();
            gamma = beta.getIzq();
            b = gamma.getIzq();
            c = gamma.getDer();
            d = beta.getDer();
            alfa.setDer(b);
            if(b!=null)
                b.setPapa(alfa);
            beta.setIzq(c);
            if(c!=null)
                c.setPapa(beta);
            beta.setDer(d);
            if(d!=null)
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
                //actualizar alturas
            }
            else{
                raiz=gamma;
            }
            gamma.setPapa(papa);
            return gamma;
        }
        else if (actual.getAvl()==2 && actual.getDer().getAvl()>0){ //DER-DER
            alfa=actual;
            beta=alfa.getDer();
            gamma=beta.getDer();
            b=beta.getIzq();
            c=gamma.getIzq();
            d=gamma.getDer();
            beta.setIzq(alfa);
            alfa.setPapa(beta);
            beta.setDer(gamma);
            gamma.setPapa(beta);
            alfa.setDer(b);
            if(b!=null)
                b.setPapa(alfa);
            if(papa!=null){
                if(beta.getElem().compareTo(papa.getElem())<=0)
                    papa.setIzq(beta);
                else
                    papa.setDer(beta);
            }
            else{
                raiz=beta;
            }
            beta.setPapa(papa);
            return beta;           
        }
        return actual; //ACABAR ROTACIONES
    }
    public void borrar(T elem){
        NodoAVL<T> actual = busqueda(raiz, elem);
        borraAVL(actual);
        if(actual==null){
            return;
        }
        NodoAVL<T> padre=actual.getPapa();
        boolean termine=false;
        actualizarFE(actual);
        while(padre!=null && !termine){

            actualizarFE(padre);
            if(padre.getAvl()>1 || padre.getAvl()<-1){
                padre=rota(padre);
                actualizarFE(padre);
            }
            actual=padre;
            padre=padre.getPapa();
        }
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
                if(padre.getIzq() != null && padre.getIzq().equals(actual))
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
                NodoAVL<T> padreSuc = suc.getPapa();
                if(suc.getDer() != null) {
                    NodoAVL<T> hijoSucDer = suc.getDer();
                    if(padreSuc.getIzq().equals(suc)) {
                        padreSuc.setIzq(hijoSucDer);
                    }
                    else {
                        padreSuc.setDer(hijoSucDer);
                    }
                    hijoSucDer.setPapa(padreSuc);
                }
                else {
                    if(padreSuc.getIzq().equals(suc)) {
                        padreSuc.setIzq(null);
                    } 
                    else {
                        padreSuc.setDer(null);
                    }
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
                }
                else{
                    NodoAVL<T> padre = actual.getPapa();
                    if(padre.getIzq() != null && padre.getIzq().equals(actual))
                        padre.setIzq(hijo);
                    else
                        padre.setDer(hijo);
                    hijo.setPapa(padre);
                }
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
        ArrayList<String> datos = new ArrayList<>();
        inOrden(raiz, datos);
        for(String es: datos){
            System.out.println(es);
        }       
    }
    private void inOrden(NodoAVL<T> act, ArrayList<String> lista){
        if(act==null){
            return;
        }
        inOrden(act.getIzq(), lista);
        lista.add(act.getElem().toString() + "(FE: " + act.getAvl() + ")");
        inOrden(act.getDer(), lista);
    }
    
    public void porAltura(){
        ArrayList<T> datos = new ArrayList<>();
        porAltura(raiz, datos);
        for(T es: datos){
            System.out.println(es);
        } 
    }
    private void porAltura(NodoAVL<T> act, ArrayList<T> lista){
        if(act==null){
            return;
        }
        lista.add(act.getElem());
        porAltura(act.getIzq(), lista);
        porAltura(act.getDer(), lista);
    }
    
    public void preOrden(){
        ArrayList<String> datos = new ArrayList<>();
        preOrden(raiz, datos);
        for(String es: datos){
            System.out.println(es);
        }       
    }
    private void preOrden(NodoAVL<T> act, ArrayList<String> lista){
        if(act==null){
            return;
        }
        lista.add(act.getElem().toString() + "(FE: " + act.getAvl() + ")");
        preOrden(act.getIzq(), lista);
        preOrden(act.getDer(), lista);
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
