package cadastro;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import catalago.CatalagoDeInstituicoes;
import controladorGRASP.ControladorDeCadastro;
import dominio.Instituicao;
import dominio.Usuario;
import excecao.ExcecaoDeCadastro;

public class ControleCadastrarEvento {
	private AbstractGUICadastrarEvento viewCadastroDeEvento;
	private Usuario usuarioAutenticado;
	
	
	public ControleCadastrarEvento(Usuario usuario){
		this.usuarioAutenticado = usuario;
	}
	
	public void inicializarGUI(){
		viewCadastroDeEvento = new SwingCadastrarEvento(this);
		viewCadastroDeEvento.inicializar();
		
		Collection<String> instituicoes = carregarInstituicoes();
		viewCadastroDeEvento.carregarInstituicoes(instituicoes);
		viewCadastroDeEvento.definirUsuarioResponsavel(usuarioAutenticado.getNome());
		
		viewCadastroDeEvento.tonarVisivel();
	}
	
	private Collection<String> carregarInstituicoes(){
		Collection<Instituicao> instituicoes = ControladorDeCadastro.obterTodasInstituicoes();
		Collection<String> instituicoesStr = new ArrayList<String>();
		
		for (Instituicao instituicao : instituicoes) {
			instituicoesStr.add(instituicao.getSigla());
		}
		
		return instituicoesStr;
	}

	public void criarEvento(){
		String nomeDoEvento = viewCadastroDeEvento.obterNomeDoEvento();
		Date dataDeInicioDoEvento = viewCadastroDeEvento.obterDataDeInicioDoEvento();
		Date dataDeFimDoEvento = viewCadastroDeEvento.obterDataDeFimDoevento();
		Date dataMaximaParaSubmissaoDeTrabalho = viewCadastroDeEvento.obterDataMaximaParaSubmissaoDeTrabalho();
		Date dataMaximaParaAceitacaoDeTrabalho = viewCadastroDeEvento.obterDataMaximaParaAceitacaoDeTrabalho();
		String siglaInstituicao = viewCadastroDeEvento.obterInstituicao();
		
		Instituicao instituicao;
		
		try {
			instituicao = CatalagoDeInstituicoes.obterInstancia().obterInstituicaoPorSigla(siglaInstituicao);
			
			try{
				ControladorDeCadastro.criarEvento(nomeDoEvento, instituicao, this.usuarioAutenticado, dataMaximaParaSubmissaoDeTrabalho, dataMaximaParaAceitacaoDeTrabalho, dataDeInicioDoEvento, dataDeFimDoEvento);
				viewCadastroDeEvento.exibirMensagemDeInformacao("Evento: '" + nomeDoEvento + "' cadastrado com sucesso!", "");
			}
			catch (ExcecaoDeCadastro ec){
				viewCadastroDeEvento.exibirMensagemDeErro(ec.getMessage(), "");
			}

			
		} catch (ExcecaoDeCadastro ec) {
			viewCadastroDeEvento.exibirMensagemDeErro(ec.getMessage(), "");
		} 		
	}
	
	public void incluirNovaInstituicao(){
		viewCadastroDeEvento.fechar();
	}
	
}
