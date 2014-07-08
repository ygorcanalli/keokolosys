package cadastro;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

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
			instituicoesStr.add(instituicao.getSigla() + " - " + instituicao.getNome());
		}
		
		return instituicoesStr;
	}

	public void criarEvento(String nomeDoEvento, Instituicao instituicao, Usuario usuarioResponsavel, Date dataMaximaParaSubmissaoDeTrabalho, Date dataMaximaParaAceitacaoDeTrabalho, Date dataDeInicio, Date dataDeFim) throws ExcecaoDeCadastro{
		ControladorDeCadastro.criarEvento(nomeDoEvento, instituicao, usuarioResponsavel, dataMaximaParaSubmissaoDeTrabalho, dataMaximaParaAceitacaoDeTrabalho, dataDeInicio, dataDeFim);
	}
	
	public void incluirNovaInstituicao(){
		viewCadastroDeEvento.fechar();
	}
	
}
