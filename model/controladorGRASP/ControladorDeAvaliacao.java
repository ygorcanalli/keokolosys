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
    	return obterTodasAsBancasExaminadorasDoEvento(evento);
    }
    
    public static Collection<BancaExaminadora> obterBancasExaminadorasAssociadasAoExaminador(PerfilDeExaminador examinador){
    	return examinador.obterBancasExaminadorasAssociadas();
    }
    
    public static BancaExaminadora obterBancaExaminadoraPelosExaminadores(Evento evento, Collection<PerfilDeExaminador> examinadores) throws ExcecaoDeAvaliacao{
    	return evento.obterBancaExaminadoraPelosExaminadores(examinadores);
    }
    
    public static Collection<BancaExaminadora> obterTodasAsBancasExaminadorasDoEvento(Evento evento){
    	return evento.getBancasExaminadoras();
    }
    
    public static Collection<PerfilDeExaminador> obterTodosExaminadoresDoEvento(Evento evento){
    	return evento.obterExaminadores();
    }
    
    public static PerfilDeExaminador obterExaminadorDoEventoPorEmail(Evento evento, String email) throws ExcecaoDeAvaliacao{
    	return evento.obterExaminadorPorEmail(email);
    }

    public static void avaliarTrabalho(Trabalho trabalho, PerfilDeExaminador examinador, EstadoAvaliacao resultado) throws ExcecaoDeAvaliacao {
        trabalho.getBancaExaminadoraResponsavel().avaliarTrabalho(trabalho, examinador, resultado);
    }
    
    public static EstadoAvaliacao obterEstadoDeTrabalho(Trabalho trabalho) {
    	return trabalho.obterEstado();
    }

}
