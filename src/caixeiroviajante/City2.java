/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caixeiroviajante;

public class City2 {

    //Nome da cidade (serve apenas para identificar a cidade, não é realmente útil para a lógica do algortimo genético)
    private char _nome;

    public City2(char _nome) {
        this._nome = _nome;
    }
    
    public char getName() {
        return _nome;
    }

    //Cálcula a distância que separa esta cidade de outra cidade passada como argumento
    public double distanceTo(City2 city) {

        for (CidadeDistancia cd : Importacao.getArrCidades()) {
            if (cd._nome == this.getName()) {
                switch (city.getName()) {
                    case 'A': {
                        return cd.distanceToA;
                    }
                    case 'B': {
                        return cd.distanceToB;
                    }
                    case 'C': {
                        return cd.distanceToC;
                    }
                    case 'D': {
                        return cd.distanceToD;
                    }
                    case 'E': {
                        return cd.distanceToE;
                    }
                    case 'F': {
                        return cd.distanceToF;
                    }
                    case 'G': {
                        return cd.distanceToG;
                    }
                    case 'H': {
                        return cd.distanceToH;
                    }
                    case 'I': {
                        return cd.distanceToI;
                    }
                }
            }
        }

        return 0.0;
    }

    //Método opticional para representar a cidade em forma de string
    @Override
    public String toString() {
        return String.valueOf(_nome);
    }
}
