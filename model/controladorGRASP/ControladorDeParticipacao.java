package controladorGRASP;

import java.util.Collection;

import dominio.Evento;
import dominio.PerfilDeChair;
import dominio.PerfilDeExaminador;
import dominio.PerfilDeParticipante;
import dominio.Trabalho;
import dominio.Usuario;
import excecao.ExcecaoDeCadastro;
import excecao.ExcecaoDeParticipacao;

public final class ControladorDeParticipacao {

	public static void subtmeterTrabalho(Evento evento, PerfilDeParticipante submissor, String titulo, String resumo, String autores, String caminhoArquivoSubmissao) throws ExcecaoDeParticipacao{
		evento.subtmeterTrabalho(submissor, titulo, resumo, autores, caminhoArquivoSubmissao);
	}
	
	public static void subtmeterVersaoFinalDeTrabalho(Evento evento, Trabalho trabalho, String caminhoArquivoFinal) throws ExcecaoDeParticipacao{
		evento.subtmeterVersaoFinalDeTrabalho(trabalho, caminhoArquivoFinal);
	}
	
	public static Collection<Trabalho> obterTodosTrabalhosSubmetidosPeloParticipante(Evento evento, PerfilDeParticipante participante) throws ExcecaoDeParticipacao{
		return evento.obterTodosTrabalhosSubmetidosPeloParticipante(participante);
	}

    public static void realizarInscricaoEmEvento(Evento evento, Usuario usuario) throws ExcecaoDeCadastro{
    	evento.inscreverParticipante(usuario);
    }
    
    public static void concederPrivilegioDeExaminador(Evento evento, Usuario usuario) throws ExcecaoDeCadastro{
    	evento.concederPrivilegioDeExaminador(usuario);
    }
    
    public static void concederPrivilegioDeChair(Evento evento, Usuario usuario) throws ExcecaoDeCadastro{
    	evento.concederPrivilegioDeChair(usuario);
    }
    
    public static Collection<PerfilDeParticipante> obterParticipantesInscritosNoEvento(Evento evento){
    	return evento.obterParticipantes();
    }
    
    public static Collection<PerfilDeExaminador> obterExaminadoresDoEvento(Evento evento){
    	return evento.obterExaminadores();
    }
    
    public static Collection<PerfilDeChair> obterChairsDoEvento(Evento evento){
    	return evento.obterChairs();
    }
    
    public static Boolean possuiInscricao(Usuario usuario, Evento evento) {
    	return (usuario.obterPerfilDe(evento, PerfilDeParticipante.class) != null);
    }
}
