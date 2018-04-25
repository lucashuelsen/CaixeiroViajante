package caixeiroviajante;

/*
 * Classe principal
 * Cria as cidades que irão pertencer a cada rota
 * Cria a primeira população (conjunto de rotas) e evoluí essa população utilizando o algoritmo genético (GA)
 * */
public class App2 {

    public static final int QUANTIDADE_DE_GERACOES = 200;
    
    public static void main(String args[]) {
        Tour tourInicial = null;
        Tour tourFinal = null;
        
        //Realiza a importação da planilha no arquivo C:\importacao_ga\Simula_8.csv
        Importacao.importarDistancias();
        
        /* Crias as cidades que irão pertencer a cada rota */
        TourManager.addCity(new City2('A'));
        TourManager.addCity(new City2('B'));
        TourManager.addCity(new City2('C'));
        TourManager.addCity(new City2('D'));
        TourManager.addCity(new City2('E'));
        TourManager.addCity(new City2('F'));
        TourManager.addCity(new City2('G'));
        TourManager.addCity(new City2('H'));
        TourManager.addCity(new City2('I'));
        
        //Cria uma população de rotas (conjunto de rotas)
        Population pop = new Population(TourManager.numberOfCities(), true);

        //Armazena a primeira população gerada, está irá passar pelo processo de evolução definido abaixo 
        tourInicial = pop.getFittest();
        
        //Evoluí a população por 200 gerações
        for (int i = 0; i < QUANTIDADE_DE_GERACOES; i++) {
            pop = GA.evolvePopulation(pop);
        }

        //Armazena a população gerada após o processo de evolução do GA
        tourFinal = pop.getFittest();
        
        //Imprime o tamanho da população 
        System.out.println("Tamanho da População: " + TourManager.numberOfCities() + " Cidades");
        //Imprime a população que será evoluída 
        System.out.println("População Inicial: " + tourInicial.toString());
        //Imprime o comprimento da melhor rota da população inicial
        System.out.println("Distância Inicial: " + (int) tourInicial.getDistance());
        //Imprime o comprimento da melhor rota da população final
        System.out.println("Distância Final: " + (int) tourFinal.getDistance());
        //Imprime o tamanho da população 
        System.out.println("Quantidade de gerações: " + QUANTIDADE_DE_GERACOES);
        //Imprime a solução encontrada para o problema
        System.out.println("População Final (Solução):" + tourFinal.toString());
    }
}
