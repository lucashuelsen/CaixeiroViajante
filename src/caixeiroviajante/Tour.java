package caixeiroviajante;

import java.util.ArrayList;
import java.util.Collections;

public class Tour {
    //Guarda as cidades da rota (tour) numa determinada ordem
    private ArrayList<City2> _tour = new ArrayList<>();
    //Fitness da rota - Representa o quão boa é a rota. O fitness é inversamente proporcional à distancia, pois queremos encontrar a menor rota
    private double _fitness = 0;
    //Representa o comprimento total desta rota
    private double _distance = 0;
    
    //Construtores da classe
    public Tour(){
        for (int i = 0; i < TourManager.numberOfCities(); i++) {
            _tour.add(null);
        }
    }
    
    public Tour(ArrayList<City2> tour){
        _tour = tour;
    }

    //Cria uma nova rota aleatória
    public void generateIndividual() {
        for(int cityIndex = 0; cityIndex < TourManager.numberOfCities(); cityIndex++) {
        	setCity(cityIndex, TourManager.getCity(cityIndex));
        }
        Collections.shuffle(_tour); //Reordena a rota original de forma aleatória
    }
    
    //Retorna a cidade na posição 'index' desta tour
    public City2 getCity(int index){
        return _tour.get(index);
    }

    //Altera a cidade na posição 'position' desta tour
    public void setCity(int position, City2 city) {
        _tour.set(position, city);
        //Quando a composição da rota muda, resetamos os valores do fitness e da distancia
        _fitness = 0;
        _distance = 0;
    }
    
    //Calcula o fitness desta rota
    public double getFitness(){
        if (_fitness == 0) {
        	_fitness = 1/(double)getDistance();
        }
        return _fitness;
    }
    
    //Calcula o comprimento total da rota
    public double getDistance(){
        if (_distance == 0){
            double tourDistance = 0;
            for (int cityIndex=0; cityIndex < tourSize(); cityIndex++) {
                City2 fromCity = getCity(cityIndex);
                City2 destinationCity;
                //Coloca a ultima cidade da rota igual à primeira 
                //Isto porque segundo o problema o mercador deve voltar à cidade de partida
                if(cityIndex+1 < tourSize()){
                    destinationCity = getCity(cityIndex+1);
                }else{
                    destinationCity = getCity(0);
                }
                //Calcula a distancia entre duas cidades e soma ao comprimento total da rota
                tourDistance += fromCity.distanceTo(destinationCity);
            }
            _distance = tourDistance;
        }
        return _distance;
    }

    //Retorna o comprimento total da rota
    public int tourSize() {
        return _tour.size();
    }
    
    //Verifica se a rota já contém uma cidade
    public boolean containsCity(City2 city){
        return _tour.contains(city);
    }
    
    //Verifica se uma rota é igual a outra rota
    public boolean equals(Object object){
    	Tour tour2;
    	if(object instanceof Tour){
    		tour2 = (Tour) object;
    	}else{
    		return false;
    	}
    	if(tourSize() == tour2.tourSize()){
    		City2 city1, city2;
    		for(int cityIndex = 0; cityIndex < tourSize(); cityIndex++){
    			city1 = getCity(cityIndex);
    			city2 = tour2.getCity(cityIndex);
    			if(!city1.equals(city2)){
    				return false;
    			}
    		}
    		return true;
    	}
    	return false;
    }
    
    //Representação textual de uma rota
    @Override
    public String toString() {
        String geneString = new String();
        for (int i = 0; i < tourSize(); i++) {
            geneString += getCity(i)+" -> ";
        }
        return geneString;
    }
}