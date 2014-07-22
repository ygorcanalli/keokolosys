package participacao;

import controladorGRASP.ControladorDeParticipacao;
import transferobject.TrabalhoTO;
import util.AbstractControle;
import util.Sessao;
import dominio.Evento;
import dominio.PerfilDeParticipante;
import dominio.Trabalho;
import dominio.Usuario;
import excecao.ExcecaoDeParticipacao;


public class ControleSubmeterVersaoFinalDeTrabalho implements AbstractControle {
	private AbstractGUISubmeterVersaoFinalDeTrabalho viewSubmeterVersaoFinalDeTrabalho;
	private Trabalho trabalho;
	private Evento evento;

	AbstractControle caller;
	SwingSubmeterVersaoFinalDeTrabalho swingSubmeterVersaoFinalDeTrabalho = new SwingSubmeterVersaoFinalDeTrabalho(this);
	
	public ControleSubmeterVersaoFinalDeTrabalho(AbstractControle caller,Trabalho trabalho, Evento evento)
	{
		this.trabalho = trabalho;
		this.evento = evento;
		this.caller = caller;
		inicializarGUI();
		swingSubmeterVersaoFinalDeTrabalho.setTitulodotrabalho(trabalho.getTitulo());
		
	}
	
	public void submeterVersaoFinal(TrabalhoTO trabalhoTO) throws ExcecaoDeParticipacao {
		Usuario usuario = Sessao.getUsuarioLogado();
		PerfilDeParticipante perfilParticipante = (PerfilDeParticipante) usuario.obterPerfilDe(evento, PerfilDeParticipante.class);
		//ControladorDeParticipacao.subsubtmeterTrabalho(evento, perfilParticipante, trabalhoTO.getTitulo(), trabalhoTO.getResumo(), trabalhoTO.getAutores(), trabalhoTO.getCaminhoArquivoSubmissao());
		ControladorDeParticipacao.subtmeterVersaoFinalDeTrabalho(evento, trabalho, trabalhoTO.getCaminhoArquivoFinal());
		viewSubmeterVersaoFinalDeTrabalho.exibirMensagemDeInformacao("Submetido com sucesso !!!", "Sucesso");
		encerrarGUI();		
		caller.desbloquearGUI();
	}
	
	@Override
	public void inicializarGUI() {
		swingSubmeterVersaoFinalDeTrabalho = new SwingSubmeterVersaoFinalDeTrabalho(this);
		tornarGUIVisivel();
		
	}

	@Override
	public void tornarGUIVisivel() {
		swingSubmeterVersaoFinalDeTrabalho.inicializar();
		
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
		swingSubmeterVersaoFinalDeTrabalho.tornarInvisivel();
		
	}
	
	public void sair()
	{
		encerrarGUI();		
		caller.desbloquearGUI();
	}
}
