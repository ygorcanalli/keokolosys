package cadastro;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import transferobject.InstituicaoTO;
import catalago.Pessoal;
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
		viewCadastroDeEvento.tonarVisivel();
	}
	
	
	public Collection<InstituicaoTO> obterInstituicoes(){
		Collection<Instituicao> instituicoes = ControladorDeCadastro.obterTodasInstituicoes();
		Collection<InstituicaoTO> instituicoesTO = new ArrayList<InstituicaoTO>();
		InstituicaoTO instituicaoTO;
		
		for (Instituicao instituicao : instituicoes) {
			instituicaoTO = new InstituicaoTO(instituicao.getNome(), instituicao.getSigla(), instituicao.getLocalizacao());
			instituicoesTO.add(instituicaoTO);
		}
		
		return instituicoesTO;
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
			instituicao = Pessoal.obterInstancia().obterInstituicaoPorSigla(siglaInstituicao);
			
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
		viewCadastroDeEvento.bloquear();
		viewCadastroDeEvento.habilitar();
	}
	
}
