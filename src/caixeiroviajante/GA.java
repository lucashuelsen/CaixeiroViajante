package caixeiroviajante;

/*
* Gere a forma como funciona o algoritmo genético que irá evoluir as populações
*/
public class GA{
	//A probabilidade (0 a 1) de uma mutação ocorrer 
    private static final double _mutationRate = 0.0015;
    //Número de rotas que irão competir entre si para entrarem para o processo de crossover
    private static final int _tournamentSize = 5;
    //Se for igual a true, então a melhor rota de uma população será sempre mantida
    private static final boolean _elitism = true;
    //Serve apenas para contar o número de mutações que ocorreu numa população
    private static int _mutations = 0;

    //Evolui uma população para a geração seguinte
    public static Population evolvePopulation(Population pop) {
        Population newPopulation = new Population(pop.populationSize(), false);
        
        //Se o _elitims for verdadeiro então a rota mais curta da população será sempre preservada (pelo menos até aparecer uma melhor)
        int elitismOffset = 0;
        if (_elitism) {
            newPopulation.saveTour(0, pop.getFittest());
            elitismOffset = 1;
        }

        //Crossover population
        for(int i = elitismOffset; i < newPopulation.populationSize(); i++) {
            //Escolhe dois pais para o crossover
        	//Os dois pais escolhidos serão os vencedores de cada um dos respetivos torneios
            Tour parent1 = tournamentSelection(pop);
            Tour parent2 = tournamentSelection(pop);
            //Cria a rota descendente
            Tour child = crossover(parent1, parent2);
            //Adiciona a rota descendente à nova população
            newPopulation.saveTour(i, child);
        }

        //Muta a nova população um pouco para adicionar novo material genético
        _mutations = 0;
        for(int i = elitismOffset; i < newPopulation.populationSize(); i++) {
            mutate(newPopulation.getTour(i));
        }
        return newPopulation;
    }

    //Aplica o processo de crossover a um conjunto de país e gera uma rota filha
    public static Tour crossover(Tour parent1, Tour parent2) {
        //Cria uma rota filha vazia
        Tour child = new Tour();

        //Posições de inicio e fim aleatórias para o primeiro pai
        int startPos = (int)(Math.random() * parent1.tourSize());
        int endPos = (int)(Math.random() * parent1.tourSize());

        //Percorre e adiciona a sub-rota vinda do primeiro pai ao filho
        for (int i = 0; i < child.tourSize(); i++) {
            //Caso a posição de inicio seja menor do que a de fim
            if (startPos < endPos && i > startPos && i < endPos) {
                child.setCity(i, parent1.getCity(i));
            }//Caso a posição de fim seja menor do que a de início
            else if(startPos > endPos) {
                if(!(i < startPos && i > endPos)) {
                    child.setCity(i, parent1.getCity(i));
                }
            }
        }

        //Percorre a rota do segundo pai e adiciona as cidades restantes ao filho pela ordem em que são encontradas no segundo pai
        for (int i = 0; i < parent2.tourSize(); i++) {
            //Se o filho não tem esta cidade então adicionamos
            if (!child.containsCity(parent2.getCity(i))) {
                //Percorre o filho para encontrar a primeira posição livre
                for (int ii = 0; ii < child.tourSize(); ii++) {
                    //Adiciona a cidade
                    if (child.getCity(ii) == null) {
                        child.setCity(ii, parent2.getCity(i));
                        break;
                    }
                }
            }
        }
        return child;
    }

    //Muta uma rota utilizando o método de mutação de trocas
    private static void mutate(Tour tour) {
        //Percorre as cidades da rota
        for(int tourPos1=0; tourPos1 < tour.tourSize(); tourPos1++){
            if(Math.random() < _mutationRate){
            	_mutations++;
                int tourPos2 = (int)(tour.tourSize() * Math.random());

                //Obtém as cidades alvo (as que queremos trocar)
                City2 city1 = tour.getCity(tourPos1);
                City2 city2 = tour.getCity(tourPos2);

                //Troca a posição de uma cidade com a outra
                tour.setCity(tourPos2, city1);
                tour.setCity(tourPos1, city2);
            }
        }
    }

    //No torneio é selecionada a melhor rota dentro de um sub-conjunto de rotas aleatórias da população
    //Essa rota será posteriormente utilizada no processo de crossover
    private static Tour tournamentSelection(Population pop) {
        //Cria uma população para o torneio
        Population tournament = new Population(_tournamentSize, false);
        //Seleciona rotas aleatórias para serem utilizadas no torneio
        //E adiciona-as ao torneio
        for(int i = 0; i < _tournamentSize; i++) {
            int randomId = (int)(Math.random() * pop.populationSize());
            tournament.saveTour(i, pop.getTour(randomId));
        }
        //Obtém a melhor rota do torneio
        Tour fittest = tournament.getFittest();
        return fittest;
    }
    
    //Um getter que pode ser útil
    public static int getMutations(){
    	return _mutations;
    }
}