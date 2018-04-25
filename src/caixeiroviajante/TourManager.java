package caixeiroviajante;

import java.util.ArrayList;
/*
 * Classe que guarda todas as cidades destino de uma rota (tour)
 * */
public class TourManager {
	//Uma lista que contém todas as cidades que o mercador terá de visitar
    private static ArrayList<City2> destinationCities = new ArrayList<>();

    //Adiciona uma cidade à lista de cidades
    public static void addCity(City2 city) {
        destinationCities.add(city);
    }
    
    //Retorna a cidade que se encontra na posição 'index' da lista de cidades
    public static City2 getCity(int index){
        return destinationCities.get(index);
    }
    
    //Retorna o número de cidades da lista
    public static int numberOfCities(){
        return destinationCities.size();
    }
}