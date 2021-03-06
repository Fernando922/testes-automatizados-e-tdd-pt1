package br.com.alura.leilao.model;

import android.support.constraint.Placeholder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Leilao implements Serializable {

    private final String descricao;
    private final List<Lance> lances;
    private double maiorLance = Double.NEGATIVE_INFINITY;  //menor valor possivel
    private double menorLance = Double.POSITIVE_INFINITY;  //maior valor possivel

    public List<Lance> getLances() {
        return lances;
    }

    public void propoe(Lance lance){
        lances.add(lance);
        Collections.sort(lances);
        double valorLance = lance.getValor();
        calculaMaiorLance(valorLance);
        calculaMenorLance(valorLance);
    }

    private void calculaMenorLance(double valorLance) {
        if (valorLance < menorLance){
            menorLance = valorLance;
        }
    }

    private void calculaMaiorLance(double valorLance) {
        if(valorLance > maiorLance){
            maiorLance = valorLance;
        }
    }

    public double getMaiorLance() {
        return maiorLance;
    }


    public Leilao(String descricao) {
        this.descricao = descricao;
        this.lances = new ArrayList<>();
    }

    public String getDescricao() {
        return descricao;
    }

    public double getMenorLance() {
        return menorLance;
    }

    public List<Lance> tresMaioresLances() {
        int quantidadeMaximaDeLances = lances.size();

        if(quantidadeMaximaDeLances > 3){
            quantidadeMaximaDeLances = 3;
        }
        return lances.subList(0, quantidadeMaximaDeLances);
    }
}
