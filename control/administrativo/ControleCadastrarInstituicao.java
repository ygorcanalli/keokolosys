package administrativo;

import java.util.ArrayList;
import java.util.Collection;

import transferobject.InstituicaoTO;
import util.AbstractControle;
import controladorGRASP.ControladorDeCadastro;
import dominio.Instituicao;
import excecao.ExcecaoDeCadastro;


public class ControleCadastrarInstituicao implements AbstractControle{

	private AbstractGUICadastrarInstituicao viewCadastrarInstituicao;
	private AbstractControle caller;
	
	public ControleCadastrarInstituicao(AbstractControle caller){
		this.caller = caller;
	}
	
	public void inicializarGUI(){
		viewCadastrarInstituicao = new SwingCadastrarInstituicao(this);
		viewCadastrarInstituicao.inicializar();
		atualizarListaDeInstituicoes();
		
		viewCadastrarInstituicao.tornarVisivel();
	}
	
	private void cadastrarInstituicao(String nome, String sigla, String localizacao){
		try {
			ControladorDeCadastro.criarInstituicao(nome, sigla, localizacao);
		} catch (ExcecaoDeCadastro e) {
			viewCadastrarInstituicao.exibirMensagemDeErro(e.getMessage(), "");
		}
	}
	
	private void atualizarInstituicao(String nome, String sigla, String localizacao){
		try {
			ControladorDeCadastro.criarInstituicao(nome, sigla, localizacao);
		} catch (ExcecaoDeCadastro e) {
			viewCadastrarInstituicao.exibirMensagemDeErro(e.getMessage(), "");
		}
	}
	
	public void atualizarListaDeInstituicoes(){
		viewCadastrarInstituicao.atualizarListaDeInstituicoes(obterInstituicoes());
	}
	
	private Collection<InstituicaoTO> obterInstituicoes(){
		Collection<Instituicao> instituicoes = ControladorDeCadastro.obterTodasInstituicoes();
		Collection<InstituicaoTO> instituicoesTO = new ArrayList<InstituicaoTO>();
		InstituicaoTO instituicaoTO;
		
		for (Instituicao instituicao : instituicoes) {
			instituicaoTO = new InstituicaoTO(instituicao.getNome(), instituicao.getSigla(), instituicao.getLocalizacao());
			instituicoesTO.add(instituicaoTO);
		}
		
		return instituicoesTO;
	}
	
	public void acaoNovo(){
		InstituicaoTO instituicaoTO = viewCadastrarInstituicao.obterInstituicaoCriada();
		cadastrarInstituicao(instituicaoTO.getNome(), instituicaoTO.getSigla(), instituicaoTO.getLocalizacao());
	}
	
	public void acaoSalvar(){
		
	}
	
	public void acaoEditar(){
		
	}
	
	public void acaoExcluir(){
		
	}
	
	public void acaoCancelar(){
		
	}
	
	public void acaoAtualizar(){
		
	}
	
	public void fechar(){
		encerrarGUI();
	}
	
	public void encerrarGUI(){
		viewCadastrarInstituicao.tornarInvisivel();
		caller.desbloquearGUI();
	}
	
	@Override
	public void tornarGUIVisivel() {
		viewCadastrarInstituicao.tornarVisivel();
	}

	@Override
	public void tornarGUIInvisivel() {
		viewCadastrarInstituicao.tornarInvisivel();
	}

	@Override
	public void bloquearGUI() {
		viewCadastrarInstituicao.bloquear();
	}

	@Override
	public void desbloquearGUI() {
		viewCadastrarInstituicao.desbloquear();
	}
}
