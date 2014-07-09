package cadastro;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import util.Sessao;
import valueobject.EventoVO;
import valueobject.InstituicaoVO;
import valueobject.UsuarioVO;
import controladorGRASP.ControladorDeCadastro;
import controladorGRASP.ControladorDeParticipacao;
import dominio.Evento;
import dominio.Usuario;
import excecao.ExcecaoDeCadastro;

public class ControleUsuarioHome {
	
	private Map<String, Evento> mapaDeEventosDeferidos;
	private AbstractGUIUsuarioHome viewUsuarioHome;
	
	public ControleUsuarioHome() {
		
		mapaDeEventosDeferidos = new TreeMap<String, Evento>();
	}
	
	public void inicializarGUI() {
		viewUsuarioHome = new SwingUsuarioHome(this);
		viewUsuarioHome.inicializar();
	}
	
	public Collection<EventoVO> obterEventosDisponiveis() {
		
		EventoVO eventoVO = null;
		UsuarioVO usuarioVO = null;
		InstituicaoVO instituicaoVO = null;
		
		Collection<Evento> eventos = ControladorDeCadastro.obterTodosEventosDeferidos();
		Collection<EventoVO> eventosDeferidos = new ArrayList<EventoVO>();
		
		for (Evento evento: eventos) {
			eventoVO = new EventoVO();
			eventoVO.setNome(evento.getNome());
			
			usuarioVO = new UsuarioVO();
			usuarioVO.setEmail(evento.getUsuarioResponsavel().getEmail());
			eventoVO.setUsuarioResponsavel(usuarioVO);
			
			instituicaoVO = new InstituicaoVO();
			instituicaoVO.setSigla(evento.getInstituicao().getSigla());
			eventoVO.setInstituicao(instituicaoVO);
			
			mapaDeEventosDeferidos.put(eventoVO.getNome(), evento);
			eventosDeferidos.add(eventoVO);
		}
		
		return eventosDeferidos;
	}
	
	public void realizarInscricaoEmEvento(EventoVO eventoVO) {
		
		Usuario usuario = Sessao.getUsuarioLogado();
		Evento evento = mapaDeEventosDeferidos.get(eventoVO.getNome());
			
			try {
				ControladorDeParticipacao.realizarInscricaoEmEvento(evento, usuario);
			} catch (ExcecaoDeCadastro e) {
				// TODO Auto-generated catch block
				viewUsuarioHome.exibirMensagemDeErro(e.getMessage(), "Erro!");
			}

	}

}
