package catalago;
import java.util.HashMap;
import java.util.Map;

import dominio.Instituicao;
import excecao.ExcecaoDeCadastro;

public class CatalogoDeInstituicoes implements Singleton{
	
	private Map<String ,Instituicao> instituicoes;
	private CatalogoDeInstituicoes instancia;
	
	@Override
	public CatalogoDeInstituicoes obterInsancia() {
		if (instancia == null)
			instancia =  new CatalogoDeInstituicoes();
		
		return instancia;
	}
	
	public CatalogoDeInstituicoes() {
		// TODO Auto-generated constructor stub
		this.instituicoes = new HashMap<String, Instituicao>();
	}
	
	public void criarInstituicao(String nome, String sigla, String localizacao) throws ExcecaoDeCadastro{
        if(instituicoes.containsKey(sigla))
        	throw new ExcecaoDeCadastro("catalogo_de_insticoes.sigla.existente");
        
		Instituicao instituicao = Instituicao.criarInstituicao(nome, sigla, localizacao);
        instituicoes.put(instituicao.getSigla(), instituicao);
    }

}
