package avaliacao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import participacao.ControleSubmeterVersaoFinalDeTrabalho;
import transferobject.TrabalhoTO;
import util.AbstractControle;
import dominio.Evento;
import dominio.Trabalho;

public class ControleGerenciarAvaliacao implements AbstractControle{

	private AbstractGUIGerenciarAvaliacao abstractGUIGerenciarAvaliacao;
	private Evento evento;
	private AbstractControle caller;
	private Map<String,Trabalho> titulosTrabalhos;
	public ControleGerenciarAvaliacao(AbstractControle caller,Evento evento) {
		this.caller = caller;
		caller.bloquearGUI();
		this.evento = evento;
		inicializarGUI();
		
	}
	@Override
	public void inicializarGUI() {
		titulosTrabalhos = new HashMap<>();
		abstractGUIGerenciarAvaliacao = new SwingGerenciarAvaliacao(this);
		abstractGUIGerenciarAvaliacao.inicializar();
		atualizarListaTrabalho();
	}

	@Override
	public void tornarGUIVisivel() {
		abstractGUIGerenciarAvaliacao.tornarVisivel();
		
	}

	@Override
	public void tornarGUIInvisivel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void bloquearGUI() {
		abstractGUIGerenciarAvaliacao.bloquear();
		
	}

	@Override
	public void desbloquearGUI() {
		abstractGUIGerenciarAvaliacao.desbloquear();
		atualizarListaTrabalho();
		
	}

	@Override
	public void encerrarGUI() {
		caller.desbloquearGUI();		
	}
	
	public void avaliarTrabalho(TrabalhoTO trabalhoTO) {
		Trabalho trabalho = titulosTrabalhos.get(trabalhoTO.getTitulo());
		new ControleAvaliarTrabalho(this,trabalho, evento);

	}
	private void atualizarListaTrabalho() {
		Collection<Trabalho> trabalhos = evento.obterTrabalhosNaoAvaliados();		
		Collection<TrabalhoTO> trabalhoTOs = new ArrayList<TrabalhoTO>();
		
		for (Trabalho trabalho : trabalhos) {
			TrabalhoTO trabalhoTO =new TrabalhoTO();
			trabalhoTO.setTitulo(trabalho.getTitulo());
			titulosTrabalhos.put(trabalho.getTitulo(), trabalho);
			trabalhoTOs.add(trabalhoTO);
			
		}
		
		abstractGUIGerenciarAvaliacao.setTrabalhosNaoAvaliados(trabalhoTOs);

	}

}
