package avaliacao;

import java.util.ArrayList;
import java.util.Collection;

import transferobject.AvaliacaoTO;
import util.AbstractControle;
import util.Sessao;
import controladorGRASP.ControladorDeAvaliacao;
import dominio.Avaliacao;
import dominio.EstadoAvaliacao;
import dominio.Evento;
import dominio.PerfilDeExaminador;
import dominio.Trabalho;
import dominio.Usuario;
import excecao.ExcecaoDeAvaliacao;


public class ControleAvaliarTrabalho implements AbstractControle{
	private AbstractGUIAvaliarTrabalho viewAvaliarTrabalho;
	private Trabalho trabalho;
	private Evento evento;
	private AbstractControle caller;
	
	public ControleAvaliarTrabalho(AbstractControle caller,Trabalho trabalho,Evento evento){
		this.caller = caller;
		this.evento = evento;
		inicializarGUI();

		this.trabalho = trabalho;
		viewAvaliarTrabalho.setTituloTrabalho(trabalho.getTitulo());
		viewAvaliarTrabalho.setResumoTrabalho(trabalho.getResumo());
		viewAvaliarTrabalho.setNomeArquivo(trabalho.getCaminhoArquivoSubmissao());
		
		Collection<AvaliacaoTO> avaliacaoTOs = new ArrayList<AvaliacaoTO>();
		for (Avaliacao avaliacao : trabalho.getAvaliacoes()){
			
			AvaliacaoTO avaliacaoTO = new AvaliacaoTO();
			
			PerfilDeExaminador perfilDeExaminador = avaliacao.getExaminador();
			if (perfilDeExaminador != null){
				Usuario usuario = perfilDeExaminador.getUsuario();
				
				if (usuario != null) {
					avaliacaoTO.setNomeExaminador(usuario.getNome());
				}				
			}
			
			if (avaliacao.getResultado() != null) {
				EstadoAvaliacao estadoAvaliacao =  avaliacao.getResultado();
				avaliacaoTO.setEstadoAvaliacao(estadoAvaliacao);
			}
			avaliacaoTOs.add(avaliacaoTO);
		}
		
		viewAvaliarTrabalho.setAvaliacoes(avaliacaoTOs);
		tornarGUIVisivel();
	}
	
	public void avaliarTrabalho(AvaliacaoTO avaliacaoTO) {		
	
		PerfilDeExaminador perfilExaminador = (PerfilDeExaminador) Sessao.getUsuarioLogado().obterPerfilDe(evento, PerfilDeExaminador.class);

		try {

			ControladorDeAvaliacao.avaliarTrabalho(trabalho, perfilExaminador, avaliacaoTO.getEnumEstadoTrabalho());
			viewAvaliarTrabalho.exibirMensagemDeInformacao("Avaliado com sucesso", "Sucesso");
			encerrarGUI();
			caller.desbloquearGUI();

		} catch (ExcecaoDeAvaliacao e) {
			viewAvaliarTrabalho.exibirMensagemDeErro(e.getMessage(), "");
		}		
	}

	@Override
	public void inicializarGUI() {
		viewAvaliarTrabalho = new SwingAvaliarTrabalho(this);
		
		viewAvaliarTrabalho.inicializar();		
		tornarGUIVisivel();
		
	}

	@Override
	public void tornarGUIVisivel() {
		viewAvaliarTrabalho.tornarVisivel();
		
	}

	@Override
	public void tornarGUIInvisivel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void bloquearGUI() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void desbloquearGUI() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void encerrarGUI() {
		viewAvaliarTrabalho.tornarInvisivel();
		
	}
	
}
