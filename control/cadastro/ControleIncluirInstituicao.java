package cadastro;

import controladorGRASP.ControladorDeCadastro;
import excecao.ExcecaoDeCadastro;
import transferobject.InstituicaoTO;
import util.AbstractControle;


public class ControleIncluirInstituicao implements AbstractControle{
	
	private AbstractControle caller;
	private AbstractGUIIncluirInstituicao viewIncluirInstituicao;
	
	public ControleIncluirInstituicao(AbstractControle caller) {
		this.caller = caller;
	}

	@Override
	public void inicializarGUI() {
		viewIncluirInstituicao = new SwingIncluirInstituicao(this);
		
		tornarGUIVisivel();
	}

	@Override
	public void tornarGUIVisivel() {
		viewIncluirInstituicao.tornarVisivel();
	}

	@Override
	public void tornarGUIInvisivel() {
		viewIncluirInstituicao.tornarInvisivel();
	}

	@Override
	public void bloquearGUI() {
		viewIncluirInstituicao.bloquear();
	}

	@Override
	public void desbloquearGUI() {
		viewIncluirInstituicao.desbloquear();
	}

	@Override
	public void encerrarGUI() {
		viewIncluirInstituicao.tornarInvisivel();
		caller.desbloquearGUI();
	}
	
	public void fechar(){
		encerrarGUI();
	}
	
	private void incluirInstituicao(String nome, String sigla, String localizacao) throws ExcecaoDeCadastro{
		ControladorDeCadastro.criarInstituicao(nome, sigla, localizacao);
		viewIncluirInstituicao.exibirMensagemDeInformacao("Instituição: " + sigla + " incluída com sucesso!", "");
	}

	public void incluir() {
		InstituicaoTO instituicaoTO = viewIncluirInstituicao.obterDadosDaInstituicaoPreenchida();
		
		try {
			incluirInstituicao(instituicaoTO.getNome(), instituicaoTO.getSigla(), instituicaoTO.getLocalizacao());
		} catch (ExcecaoDeCadastro e) {
			viewIncluirInstituicao.exibirMensagemDeErro(e.getMessage(), "");
		}
	}
	
}
