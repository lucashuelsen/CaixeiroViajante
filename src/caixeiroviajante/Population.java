package caixeiroviajante;

/*
 * Gere uma população (conjunto) de rotas (Tour)
 * */
public class Population {
   //Array que contém todas as rotas desta população
    Tour[] _tours;

    //Construtor da classe
    public Population(int populationSize, boolean initialise) {
        _tours = new Tour[populationSize];
        if (initialise) {
            //Cria várias rotas aleatórias
            for (int i = 0; i < populationSize(); i++) {
                Tour newTour = new Tour();
                newTour.generateIndividual();
                saveTour(i, newTour);
            }
        }
    }
    
    //Guarda uma rota do array de rotas
    public void saveTour(int index, Tour tour) {
        _tours[index] = tour;
    }
    
    //Retorna uma rota desta população
    public Tour getTour(int index) {
        return _tours[index];
    }

    //Retorna a melhor rota (a rota com maior fitness) desta população.
    //Como já referi a rota com maior fitness será a rota com o menor comprimento
    public Tour getFittest() {
        Tour fittest = getTour(0);
        for(int i = 1; i < populationSize(); i++) {
            if(fittest.getFitness() <= getTour(i).getFitness()) {
                fittest = getTour(i);
            }
        }
        return fittest;
    }

    //Retorna o comprimento da população (o número de rotas que esta contém)
    public int populationSize() {
    	return _tours.length;
    }
}