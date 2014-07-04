package keokolosys;


import java.util.Collection;

/**
 * Created by keokolo on 07/06/14.
 */
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

    public void avaliarTrabalho(Trabalho trabalho, PerfilDeExaminador examinador, EstadoAvaliacao resultado) throws ExcecaoDeAvaliacao {
        trabalho.getBancaExaminadoraResponsavel().avaliarTrabalho(trabalho, examinador, resultado);
    }
    
    public EstadoAvaliacao obterEstadoDeTrabalho(Trabalho trabalho) {
    	return trabalho.obterEstado();
    }

}
