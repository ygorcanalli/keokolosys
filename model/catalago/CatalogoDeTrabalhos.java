package catalago;

import java.util.ArrayList;
import java.util.Collection;

import dominio.EstadoAvaliacao;
import dominio.Trabalho;

public class CatalogoDeTrabalhos {
    private Collection<Trabalho> trabalhos;

    public CatalogoDeTrabalhos() {
    	trabalhos = new ArrayList<Trabalho>();
    }

    public void adicionarTrabalho(Trabalho trabalho){
        this.trabalhos.add(trabalho);
    }

    public Collection<Trabalho> obterTrabalhosAceitos() {
    	Collection<Trabalho> trabalhosAceitos = new ArrayList<Trabalho>();
    	for (Trabalho trabalho: trabalhos) {
    		if (trabalho.obterEstado() == EstadoAvaliacao.ACEITO)
    			trabalhosAceitos.add(trabalho);
    	}
        return  trabalhosAceitos;
    }

    public Collection<Trabalho> obterTrabalhosRejeitados() {
    	Collection<Trabalho> trabalhosRejeitados = new ArrayList<Trabalho>();
    	for (Trabalho trabalho: trabalhos) {
    		if (trabalho.obterEstado() == EstadoAvaliacao.REJEITADO)
    			trabalhosRejeitados.add(trabalho);
    	}
        return  trabalhosRejeitados;
    }

    public Collection<Trabalho> obterTrabalhosNaoAvaliados() {
    	Collection<Trabalho> trabalhosNaoAvaliados = new ArrayList<Trabalho>();
    	for (Trabalho trabalho: trabalhos) {
    		if (trabalho.obterEstado() == EstadoAvaliacao.NAO_AVALIADO)
    			trabalhosNaoAvaliados.add(trabalho);
    	}
        return  trabalhosNaoAvaliados;
    }
    
    public Collection<Trabalho> obterTrabalhosNaoAssociadosABancaExaminadora(){
    	Collection<Trabalho> trabalhosNaoAssociadosABancaExaminadora = new ArrayList<Trabalho>();
    	for (Trabalho trabalho: trabalhos) {
    		if (!trabalho.associadoABancaExaminadora())
    			trabalhosNaoAssociadosABancaExaminadora.add(trabalho);
    	}
        return  trabalhosNaoAssociadosABancaExaminadora;    	
    }

    public Collection<Trabalho> obterTrabalhosSubmetidos() {
        return trabalhos;
    }
}
