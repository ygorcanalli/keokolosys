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
	
	public void atualizarInstituicao(Instituicao instituicao, String nome, String sigla, String localizacao) throws ExcecaoDeCadastro{
		if(instituicao.getNome().compareTo(nome) != 0)
			instituicao.setNome(nome);
		
		if(instituicao.getSigla().compareTo(sigla) != 0)
		{
			validarSiglaComoUnica(sigla);
	        instituicao.setSigla(sigla);
		}
		
		if(instituicao.getLocalizacao().compareTo(localizacao) != 0)
			instituicao.setLocalizacao(localizacao);
	}
	
	private void validarSiglaComoUnica(String sigla) throws ExcecaoDeCadastro{
        if(instituicoes.containsKey(sigla))
        	throw new ExcecaoDeCadastro("catalogo_de_insticoes.sigla.existente");
	}

}
