package caixeiroviajante;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Importacao {

    public static int COLUNA_A = 11;
    public static int COLUNA_B = 12;
    public static int COLUNA_C = 13;
    public static int COLUNA_D = 14;
    public static int COLUNA_E = 15;
    public static int COLUNA_F = 16;
    public static int COLUNA_G = 17;
    public static int COLUNA_H = 18;
    public static int COLUNA_I = 19;

    public static ArrayList<CidadeDistancia> arrCidades = null;

    public static void main(String[] args) {
        importarDistancias();
    }

    public static void importarDistancias() {
        try {
            FileInputStream stream = new FileInputStream("C:\\importacao_ga\\Simula_8.csv");
            InputStreamReader reader = new InputStreamReader(stream);
            BufferedReader br = new BufferedReader(reader);
            String linha = "";
            String[] arrLinha;
            CidadeDistancia cd = null;

            arrCidades = new ArrayList<>();

            br.readLine();

            //Realiza loop pelo arquivo obtendo os dados das cidades e a distancia em relação as outras 
            for (int x = 0; x <= 8; x++) {
                linha = br.readLine();
                
                arrLinha = linha.split(";");
                
                cd = new CidadeDistancia();

                cd._nome = arrLinha[10].charAt(0);
                cd.distanceToA = Double.parseDouble(arrLinha[COLUNA_A]);
                cd.distanceToB = Double.parseDouble(arrLinha[COLUNA_B]);
                cd.distanceToC = Double.parseDouble(arrLinha[COLUNA_C]);
                cd.distanceToD = Double.parseDouble(arrLinha[COLUNA_D]);
                cd.distanceToE = Double.parseDouble(arrLinha[COLUNA_E]);
                cd.distanceToF = Double.parseDouble(arrLinha[COLUNA_F]);
                cd.distanceToG = Double.parseDouble(arrLinha[COLUNA_G]);
                cd.distanceToH = Double.parseDouble(arrLinha[COLUNA_H]);
                cd.distanceToI = Double.parseDouble(arrLinha[COLUNA_I]);

                arrCidades.add(cd);
            }

            //Exibe a importação das cidades 
            for (CidadeDistancia cidadeDistancia : arrCidades) {
                System.err.println(cidadeDistancia.toString());
            }

        } catch (Exception ex) {
            Logger.getLogger(Importacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ArrayList<CidadeDistancia> getArrCidades() {
        return arrCidades;
    }
}
