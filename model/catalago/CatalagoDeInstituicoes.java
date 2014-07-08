package catalago;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import dominio.Instituicao;
import excecao.ExcecaoDeCadastro;

public class CatalagoDeInstituicoes{
	
	private Map<String, Instituicao> instituicoes;
	private static CatalagoDeInstituicoes instancia;
	
	public CatalagoDeInstituicoes() {
		// TODO Auto-generated constructor stub
		this.instituicoes = new HashMap<String, Instituicao>();
	}	
	
	public static synchronized  CatalagoDeInstituicoes obterInstancia() {
		if (instancia == null)
			instancia =  new CatalagoDeInstituicoes();
		
		return instancia;
	}
		
	public void criarInstituicao(String nome, String sigla, String localizacao) throws ExcecaoDeCadastro{
		validarSiglaComoUnica(sigla);
		
		Instituicao instituicao = Instituicao.criarInstituicao(nome, sigla, localizacao);
        instituicoes.put(instituicao.getSigla(), instituicao);
    }
	
	public Collection<Instituicao> obterInstituicoes(){
		return instituicoes.values();
	}
	
	public Instituicao obterInstituicaoPorSigla(String sigla) throws ExcecaoDeCadastro{
		Instituicao instituicao = instituicoes.get(sigla); 
		
		if(instituicao == null)
			throw new ExcecaoDeCadastro("catalago_de_instituicoes.instituicao.nao_localizado");
		
		return instituicao;
	}
	
	public void atualizarInstituicao(Instituicao instituicao, String nome, String sigla, String localizacao) throws ExcecaoDeCadastro{
		if(sigla.compareTo(instituicao.getSigla()) != 0)
			validarSiglaComoUnica(sigla);
		
		instituicao.atualizarDados(nome, sigla, localizacao);
	}
	
	private void validarSiglaComoUnica(String sigla) throws ExcecaoDeCadastro{
        if(instituicoes.containsKey(sigla))
        	throw new ExcecaoDeCadastro("catalogo_de_insticoes.sigla.existente");
	}

}
