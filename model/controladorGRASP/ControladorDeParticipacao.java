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

public class ControladorDeParticipacao {

	public void subtmeterTrabalho(Evento evento, PerfilDeParticipante submissor, String titulo, String resumo, String autores, String caminhoArquivoSubmissao) throws ExcecaoDeParticipacao{
		evento.subtmeterTrabalho(submissor, titulo, resumo, autores, caminhoArquivoSubmissao);
	}
	
	public void subtmeterVersaoFinalDeTrabalho(Evento evento, Trabalho trabalho, String caminhoArquivoFinal) throws ExcecaoDeParticipacao{
		evento.subtmeterVersaoFinalDeTrabalho(trabalho, caminhoArquivoFinal);
	}

    public void realizarInscricaoEmEvento(Evento evento, Usuario usuario) throws ExcecaoDeCadastro{
    	evento.inscreverParticipante(usuario);
    }
    
    public void concederPrivilegioDeExaminador(Evento evento, Usuario usuario) throws ExcecaoDeCadastro{
    	evento.concederPrivilegioDeExaminador(usuario);
    }
    
    public void concederPrivilegioDeChair(Evento evento, Usuario usuario) throws ExcecaoDeCadastro{
    	evento.concederPrivilegioDeChair(usuario);
    }
    
    public Collection<PerfilDeParticipante> obterParticipantesInscritosNoEvento(Evento evento){
    	return evento.obterParticipantes();
    }
    
    public Collection<PerfilDeExaminador> obterExaminadoresDoEvento(Evento evento){
    	return evento.obterExaminadores();
    }
    
    public Collection<PerfilDeChair> obterChairsDoEvento(Evento evento){
    	return evento.obterChairs();
    }    
}
