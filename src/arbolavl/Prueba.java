/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbolavl;

/**
 *
 * @author lucasgarcia
 */
public class Prueba {
    public static void main(String[] args){
        ArbolAVL<Integer> arb = new ArbolAVL();
        arb.inserta(10);
        arb.inserta(15);
        arb.inserta(20);
        arb.inserta(25);
        arb.inserta(22);
        
        arb.inOrden();
        
    }
}
