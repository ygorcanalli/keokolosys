package administrativo;

import java.util.ArrayList;
import java.util.Collection;

import controladorGRASP.ControladorDeCadastro;
import dominio.Instituicao;
import excecao.ExcecaoDeCadastro;


public class ControleCadastrarInstituicao {
	AbstractGUICadastrarInstituicao viewCadastrarInstituicao;
	
	public void inicializarGUI(){
		viewCadastrarInstituicao = new SwingCadastrarInstituicao(this);
		viewCadastrarInstituicao.inicializar();
		viewCadastrarInstituicao.tonarVisivel();
	}
	
	public void cadastrarInstituicao(String nome, String sigla, String localizacao) throws ExcecaoDeCadastro{
		ControladorDeCadastro.criarInstituicao(nome, sigla, localizacao);
	}
	
	public Collection<String> carregarInstituicoes(){
		Collection<Instituicao> instituicoes = ControladorDeCadastro.obterTodasInstituicoes();
		Collection<String> instituicoesStr = new ArrayList<String>();
		
		for (Instituicao instituicao : instituicoes) {
			instituicoesStr.add(instituicao.getSigla() + " - " + instituicao.getNome());
		}
		
		return instituicoesStr;
	}
}
