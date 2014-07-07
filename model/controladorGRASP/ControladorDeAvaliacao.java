package controladorGRASP;


import java.util.Collection;

import dominio.BancaExaminadora;
import dominio.EstadoAvaliacao;
import dominio.Evento;
import dominio.PerfilDeExaminador;
import dominio.Trabalho;
import excecao.ExcecaoDeAvaliacao;

public final class ControladorDeAvaliacao {

    public static BancaExaminadora criarBancaExaminadora(Evento evento, Collection<PerfilDeExaminador> examinadores) throws ExcecaoDeAvaliacao {
        return evento.criarBancaExaminadora(examinadores);
    }

    public static void associarBancaExaminadoraATrabalho(BancaExaminadora bancaExaminadora, Trabalho trabalho) throws ExcecaoDeAvaliacao {
        bancaExaminadora.associarTrabalho(trabalho);
    }
    
    public static Collection<BancaExaminadora> listarBancasExaminadoras(Evento evento) {
    	return evento.getBancasExaminadoras();
    }
    
    public static Collection<BancaExaminadora> obterBancasExaminadorasAssociadasAoExaminador(PerfilDeExaminador examinador){
    	return examinador.obterBancasExaminadorasAssociadas();
    }

    public static void avaliarTrabalho(Trabalho trabalho, PerfilDeExaminador examinador, EstadoAvaliacao resultado) throws ExcecaoDeAvaliacao {
        trabalho.getBancaExaminadoraResponsavel().avaliarTrabalho(trabalho, examinador, resultado);
    }
    
    public static EstadoAvaliacao obterEstadoDeTrabalho(Trabalho trabalho) {
    	return trabalho.obterEstado();
    }

}
