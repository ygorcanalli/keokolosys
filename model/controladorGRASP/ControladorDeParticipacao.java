package controladorGRASP;

import java.util.Collection;
import java.util.List;

import dominio.Evento;
import dominio.PerfilDeChair;
import dominio.PerfilDeExaminador;
import dominio.PerfilDeParticipante;
import dominio.Trabalho;
import dominio.Usuario;
import excecao.ExcecaoDeCadastro;
import excecao.ExcecaoDeParticipacao;

public final class ControladorDeParticipacao {

	public static Trabalho subtmeterTrabalho(Evento evento, PerfilDeParticipante submissor, String titulo, String resumo, String autores, String caminhoArquivoSubmissao) throws ExcecaoDeParticipacao{
		return evento.subtmeterTrabalho(submissor, titulo, resumo, autores, caminhoArquivoSubmissao);
	}
	
	public static void subtmeterVersaoFinalDeTrabalho(Evento evento, Trabalho trabalho, String caminhoArquivoFinal) throws ExcecaoDeParticipacao{
		evento.subtmeterVersaoFinalDeTrabalho(trabalho, caminhoArquivoFinal);
	}
	
	public static List<Trabalho> obterTodosTrabalhosSubmetidosPeloParticipante(Evento evento, PerfilDeParticipante participante) throws ExcecaoDeParticipacao{
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
    
    public static Boolean possuiPrivilegioDeChair(Usuario usuario, Evento evento) {
    	return (usuario.obterPerfilDe(evento, PerfilDeChair.class) != null);
    }
    
    public static Boolean possuiPrivilegioDeExaminador(Usuario usuario, Evento evento) {
    	return (usuario.obterPerfilDe(evento, PerfilDeExaminador.class) != null);
    }
    
    public static Boolean possuiPerfil(Usuario usuario, Evento evento) {
    	return (usuario.obterPerfil(evento) != null);
    }
}
