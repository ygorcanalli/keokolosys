package controladorGRASP;


import java.util.Collection;

import dominio.BancaExaminadora;
import dominio.EstadoAvaliacao;
import dominio.Evento;
import dominio.PerfilDeExaminador;
import dominio.Trabalho;
import excecao.ExcecaoDeAvaliacao;

public class ControladorDeAvaliacao {

    public BancaExaminadora criarBancaExaminadora(Evento evento, Collection<PerfilDeExaminador> examinadores) throws ExcecaoDeAvaliacao {
        return evento.criarBancaExaminadora(examinadores);
    }

    public void associarBancaExaminadoraATrabalho(BancaExaminadora bancaExaminadora, Trabalho trabalho) {
        bancaExaminadora.associarTrabalho(trabalho);
    }
    
    public Collection<BancaExaminadora> listarBancasExaminadoras(Evento evento) {
    	return evento.getBancasExaminadoras();
    }
    
    public Collection<BancaExaminadora> obterBancasExaminadorasAssociadasAoExaminador(PerfilDeExaminador examinador){
    	return examinador.obterBancasExaminadorasAssociadas();
    }

    public void avaliarTrabalho(Trabalho trabalho, PerfilDeExaminador examinador, EstadoAvaliacao resultado) throws ExcecaoDeAvaliacao {
        trabalho.getBancaExaminadoraResponsavel().avaliarTrabalho(trabalho, examinador, resultado);
    }
    
    public EstadoAvaliacao obterEstadoDeTrabalho(Trabalho trabalho) {
    	return trabalho.obterEstado();
    }

}
