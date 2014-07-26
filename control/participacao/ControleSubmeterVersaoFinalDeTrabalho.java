package participacao;

import controladorGRASP.ControladorDeParticipacao;
import transferobject.TrabalhoTO;
import util.AbstractControle;
import dominio.Evento;
import dominio.Trabalho;
import excecao.ExcecaoDeParticipacao;


public class ControleSubmeterVersaoFinalDeTrabalho implements AbstractControle {
	private AbstractGUISubmeterVersaoFinalDeTrabalho viewSubmeterVersaoFinalDeTrabalho;
	private Trabalho trabalho;
	private Evento evento;

	private AbstractControle caller;
	
	public ControleSubmeterVersaoFinalDeTrabalho(AbstractControle caller,Trabalho trabalho, Evento evento)
	{
		this.trabalho = trabalho;
		this.evento = evento;
		this.caller = caller;
		inicializarGUI();
		viewSubmeterVersaoFinalDeTrabalho.setTitulodotrabalho(trabalho.getTitulo());	
	}
	
	public void submeterVersaoFinal(TrabalhoTO trabalhoTO) {
		//Usuario usuario = Sessao.getUsuarioLogado();
		//PerfilDeParticipante perfilParticipante = (PerfilDeParticipante) usuario.obterPerfilDe(evento, PerfilDeParticipante.class);
		//ControladorDeParticipacao.subsubtmeterTrabalho(evento, perfilParticipante, trabalhoTO.getTitulo(), trabalhoTO.getResumo(), trabalhoTO.getAutores(), trabalhoTO.getCaminhoArquivoSubmissao());
		
		try {
			ControladorDeParticipacao.subtmeterVersaoFinalDeTrabalho(evento, trabalho, trabalhoTO.getCaminhoArquivoFinal());
			viewSubmeterVersaoFinalDeTrabalho.exibirMensagemDeInformacao("Submetido com sucesso !!!", "Sucesso");
			encerrarGUI();		
			caller.desbloquearGUI();

		} catch (ExcecaoDeParticipacao e) {
			viewSubmeterVersaoFinalDeTrabalho.exibirMensagemDeErro(e.getMessage(), "");
		}
	}
	
	@Override
	public void inicializarGUI() {
		viewSubmeterVersaoFinalDeTrabalho = new SwingSubmeterVersaoFinalDeTrabalho(this);
		tornarGUIVisivel();
		
	}

	@Override
	public void tornarGUIVisivel() {
		viewSubmeterVersaoFinalDeTrabalho.inicializar();
		
	}

	@Override
	public void tornarGUIInvisivel() {
		viewSubmeterVersaoFinalDeTrabalho.tornarInvisivel();
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
		tornarGUIInvisivel();
		
	}
	
	public void sair()
	{
		encerrarGUI();		
		caller.desbloquearGUI();
	}
}
