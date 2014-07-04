package keokolosys;

import java.util.ArrayList;
import java.util.Collection;

public class CatalogoDeTrabalhos {
    private Collection<Trabalho> trabalhosNaoAvaliados;
    private Collection<Trabalho> trabalhosAceitos;
    private Collection<Trabalho> trabalhosRejeitados;

    public void adicionarTrabalho(Trabalho trabalho){
        this.trabalhosNaoAvaliados.add(trabalho);
    }

    public Collection<Trabalho> obterTrabalhosAceitos() {
        return  trabalhosAceitos;
    }

    public Collection<Trabalho> obterTrabalhosRejeitados() {
        return trabalhosRejeitados;
    }

    public Collection<Trabalho> obterTrabalhosNaoAvaliados() {
        return trabalhosNaoAvaliados;
    }

    public Collection<Trabalho> obterTrabalhosSubmetidos() {
        Collection<Trabalho> trabalhosSubmetidos = new ArrayList<Trabalho>();
        trabalhosSubmetidos.addAll(trabalhosAceitos);
        trabalhosSubmetidos.addAll(trabalhosRejeitados);
        trabalhosSubmetidos.addAll(trabalhosNaoAvaliados);

        return trabalhosSubmetidos;
    }
}
