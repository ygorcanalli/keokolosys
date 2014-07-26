package participacao;

import java.util.ArrayList;
import java.util.Collection;

import controladorGRASP.ControladorDeCadastro;
import controladorGRASP.ControladorDeParticipacao;
import dominio.Evento;
import dominio.PerfilDeChair;
import dominio.PerfilDeExaminador;
import dominio.Usuario;
import excecao.ExcecaoDeCadastro;
import transferobject.EventoTO;
import transferobject.UsuarioTO;
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
		atualizarListaDeUsuariosSemParticipacaoNoEvento();
		atualizarListaDeExaminadores();
		atualizarListaDeChairs();
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
		atualizarListaDeExaminadores();
		atualizarListaDeChairs();
		viewConcederPrivilegios.desbloquear();
	}

	private void atualizarListaDeChairs() {
		Collection<PerfilDeChair> perfisDeChair = ControladorDeParticipacao.obterChairsDoEvento(evento);
		
		Collection<UsuarioTO> usuariosTO = new ArrayList<UsuarioTO>();
		
		for (PerfilDeChair perfil: perfisDeChair) {
			UsuarioTO usuarioTO = new UsuarioTO();
			usuarioTO.setEmail(perfil.getUsuario().getEmail());
			usuarioTO.setNome(perfil.getUsuario().getNomeCompleto());
			usuariosTO.add(usuarioTO);
		}
		
		viewConcederPrivilegios.atualizarListaDeChairs(usuariosTO);
		
	}


	private void atualizarListaDeExaminadores() {
		Collection<PerfilDeExaminador> perfisDeExaminadores = ControladorDeParticipacao.obterExaminadoresDoEvento(evento);
		
		Collection<UsuarioTO> usuariosTO = new ArrayList<UsuarioTO>();
		
		for (PerfilDeExaminador perfil: perfisDeExaminadores) {
			UsuarioTO usuarioTO = new UsuarioTO();
			usuarioTO.setEmail(perfil.getUsuario().getEmail());
			usuarioTO.setNome(perfil.getUsuario().getNomeCompleto());
			usuariosTO.add(usuarioTO);
		}
		
		viewConcederPrivilegios.atualizarListaDeExaminadores(usuariosTO);
		
	}

	public void atualizarListaDeUsuariosSemParticipacaoNoEvento() {
		Collection<Usuario> todosUsuarios = ControladorDeCadastro.obterTodosUsuarios();
		Collection<UsuarioTO> usuariosSemParticipacaoTO = new ArrayList<UsuarioTO>();
		
		for (Usuario usuario: todosUsuarios) {
			if (!ControladorDeParticipacao.possuiPerfil(usuario, evento)) {
				UsuarioTO usuarioTO = new UsuarioTO();
				usuarioTO.setEmail(usuario.getEmail());
				usuarioTO.setNome(usuario.getNomeCompleto());
				usuariosSemParticipacaoTO.add(usuarioTO);
			}
		}
		
		viewConcederPrivilegios.atualizarListaDeUsuariosSemParticipacaoNoEvento(usuariosSemParticipacaoTO);
	}
	
	public void acaoConcederPrivilegioDeExaminador(UsuarioTO usuarioTO) {
		Usuario usuario;
		try {
			usuario = ControladorDeCadastro.obterUsuarioPorEmail(usuarioTO.getEmail());
			evento.concederPrivilegioDeExaminador(usuario);
		} catch (ExcecaoDeCadastro e) {
			// TODO Auto-generated catch block
			viewConcederPrivilegios.exibirMensagemDeErro(e.getMessage(), "Erro ao conceder privilégio!");
		}
		
		atualizarListaDeExaminadores();
		atualizarListaDeUsuariosSemParticipacaoNoEvento();
		
	}
	
	public void acaoConcederPrivilegioDeChair(UsuarioTO usuarioTO) {
		Usuario usuario;
		try {
			usuario = ControladorDeCadastro.obterUsuarioPorEmail(usuarioTO.getEmail());
			evento.concederPrivilegioDeChair(usuario);
		} catch (ExcecaoDeCadastro e) {
			// TODO Auto-generated catch block
			viewConcederPrivilegios.exibirMensagemDeErro(e.getMessage(), "Erro ao conceder privilégio!");
		}
		
		atualizarListaDeChairs();
		atualizarListaDeUsuariosSemParticipacaoNoEvento();
	}

	@Override
	public void encerrarGUI() {
		tornarGUIInvisivel();
		caller.desbloquearGUI();
	}

	public void acaoFechar() {
		encerrarGUI();
	}
	


	@Override
	public void inicializarGUI() {
		EventoTO eventoTO = new EventoTO();
		eventoTO.setNome(evento.getNome());

		
		viewConcederPrivilegios = new SwingConcederPrivilegios(this, eventoTO);
		viewConcederPrivilegios.inicializar();
		
	}

}
