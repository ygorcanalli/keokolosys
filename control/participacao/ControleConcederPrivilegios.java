package participacao;

import dominio.Evento;
import transferobject.EventoTO;
import util.AbstractControle;

public class ControleConcederPrivilegios implements AbstractControle {
	
	AbstractGUIConcederPrivilegios viewConcederPrivilegios;
	AbstractControle caller;
	Evento evento;
	
	public ControleConcederPrivilegios(AbstractControle caller, Evento evento) {
		this.caller = caller;
		this.evento = evento;
		
		inicializarGUI();
		tornarGUIVisivel();
	}
	

	@Override
	public void tornarGUIVisivel() {
		viewConcederPrivilegios.tornarVisivel();	
	}

	@Override
	public void tornarGUIInvisivel() {
		viewConcederPrivilegios.tornarInvisivel();
	}

	@Override
	public void bloquearGUI() {
		viewConcederPrivilegios.bloquear();
	}

	@Override
	public void desbloquearGUI() {
		atualizarListaDeUsuariosSemParticipacaoNoEvento();
		atualizatListaDeExaminadores();
		atualizarListaDeChairs();
		viewConcederPrivilegios.desbloquear();
	}

	private void atualizarListaDeChairs() {
		
	}


	private void atualizatListaDeExaminadores() {

		
	}


	@Override
	public void encerrarGUI() {
		tornarGUIInvisivel();
		caller.desbloquearGUI();
	}

	public void acaoFechar() {
		encerrarGUI();
	}
	
	public void atualizarListaDeUsuariosSemParticipacaoNoEvento() {
		
	}


	@Override
	public void inicializarGUI() {
		EventoTO eventoTO = new EventoTO();
		eventoTO.setNome(evento.getNome());

		
		viewConcederPrivilegios = new SwingConcederPrivilegios(this, eventoTO);
		viewConcederPrivilegios.inicializar();
		
	}

}
