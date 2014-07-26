package cadastro;

import java.util.ArrayList;
import java.util.List;

import participacao.ControleSubmeterVersaoFinalDeTrabalho;
import participacao.SwingGerenciarTrabalhosSubmetidos;
import controladorGRASP.ControladorDeParticipacao;
import dominio.Evento;
import dominio.PerfilDeParticipante;
import dominio.Trabalho;
import dominio.Usuario;
import excecao.ExcecaoDeParticipacao;
import transferobject.TrabalhoTO;
import util.AbstractControle;
import util.Sessao;

public class ControleGerenciarTrabalhosSubmetidos  implements AbstractControle{
	
	AbstractControle caller;
	SwingGerenciarTrabalhosSubmetidos swingGerenciarTrabalhosSubmetidos;
	List<Trabalho> trabalhos;
	Evento evento;
	
	public ControleGerenciarTrabalhosSubmetidos(AbstractControle caller, Evento evento) throws ExcecaoDeParticipacao {
		
		this.caller = caller;
		Usuario usuario = Sessao.getUsuarioLogado();
		PerfilDeParticipante perfilParticipante = (PerfilDeParticipante) usuario.obterPerfilDe(evento, PerfilDeParticipante.class);
		trabalhos = ControladorDeParticipacao.obterTodosTrabalhosSubmetidosPeloParticipante(evento, perfilParticipante);
		this.evento = evento;
		
		inicializarGUI();
		
		List<TrabalhoTO> trabalhoTOs = new ArrayList<TrabalhoTO>();
		for (Trabalho trabalho : trabalhos) {
			TrabalhoTO trabalhoTO = new TrabalhoTO();
			
			trabalhoTO.setAutores(trabalho.getAutores());
			trabalhoTO.setResumo(trabalho.getResumo());
			trabalhoTO.setTitulo(trabalho.getTitulo());
			trabalhoTOs.add(trabalhoTO);
			
			
		}
		
		swingGerenciarTrabalhosSubmetidos.setTrabalhos(trabalhoTOs);
	}
	
	public void submeterVersaoFinal(TrabalhoTO trabalhoTO) 
	{
		Trabalho trabalho = trabalhos.get(trabalhoTO.getPosicao());
		new ControleSubmeterVersaoFinalDeTrabalho(this,trabalho, evento);
	}

	@Override
	public void inicializarGUI() {
		
		swingGerenciarTrabalhosSubmetidos = new SwingGerenciarTrabalhosSubmetidos(this);
		tornarGUIVisivel();
		bloquearGUI();
		swingGerenciarTrabalhosSubmetidos.inicializar();
		
	}

	@Override
	public void tornarGUIVisivel() {		
		swingGerenciarTrabalhosSubmetidos.tornarVisivel();
		
	}

	@Override
	public void tornarGUIInvisivel() {
		swingGerenciarTrabalhosSubmetidos.tornarInvisivel();		
	}

	@Override
	public void bloquearGUI() {
		swingGerenciarTrabalhosSubmetidos.bloquear();
		
	}

	public void debloquearGUICaller() {
		caller.desbloquearGUI();

	}
	
	@Override
	public void desbloquearGUI() {
		swingGerenciarTrabalhosSubmetidos.desbloquear();
		
	}

	@Override
	public void encerrarGUI() {
		// TODO Auto-generated method stub
		
	}

}
