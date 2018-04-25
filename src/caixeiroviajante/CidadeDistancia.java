/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caixeiroviajante;

public class CidadeDistancia {
    public char _nome;
    
    //Distancia em relação às cidades
    public double distanceToA;
    public double distanceToB;
    public double distanceToC;
    public double distanceToD;
    public double distanceToE;
    public double distanceToF;
    public double distanceToG;
    public double distanceToH;
    public double distanceToI;
    
    @Override
    public String toString() {
        return _nome + "\t" + 
               distanceToA + "\t" + 
               distanceToB + "\t" + 
               distanceToC + "\t" + 
               distanceToD + "\t" + 
               distanceToE + "\t" + 
               distanceToF + "\t" + 
               distanceToG + "\t" + 
               distanceToH + "\t" + 
               distanceToI + "\t";
    }
}
