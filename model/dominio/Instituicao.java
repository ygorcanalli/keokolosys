package dominio;

import excecao.ExcecaoDeCadastro;

public class Instituicao {
    private String nome;
    private String sigla;
    private String localizacao;

    private Instituicao(String nome, String sigla, String localizacao){
		this.nome = nome;
		this.sigla = sigla;
		this.localizacao = localizacao;
    }
    
    public static Instituicao criarInstituicao(String nome, String sigla, String localizacao) throws ExcecaoDeCadastro{
    	validarDados(nome, sigla, localizacao);
    	return new Instituicao(nome, sigla, localizacao);
    }

    private static void validarDados(String nome, String sigla, String localizacao) throws ExcecaoDeCadastro{
        validarNome(nome);
        validarSigla(sigla);
        validarLocalizacao(localizacao);
    }
    
    private static void validarNome(String nome) throws ExcecaoDeCadastro{
    	Boolean nomeVazio = (nome == null) || (nome.isEmpty());
    	
        if(nomeVazio)
        	throw new ExcecaoDeCadastro("instituicao.nome.vazio");    	
    }
    
    private static void validarSigla(String sigla) throws ExcecaoDeCadastro{
    	Boolean siglaVazia = (sigla == null) || (sigla.isEmpty());
    	
        if(siglaVazia)
        	throw new ExcecaoDeCadastro("instituicao.sigla.vazia");
    }
    
    private static void validarLocalizacao(String localizacao) throws ExcecaoDeCadastro{
        Boolean localizacaoVazia = (localizacao == null) || (localizacao.isEmpty());
    	
        if(localizacaoVazia)
        	throw new ExcecaoDeCadastro("instituicao.localizacao.vazia");   
    }
    

	public String getNome() {
		return nome;
	}

	public String getSigla() {
		return sigla;
	}

	public String getLocalizacao() {
		return localizacao;
	}
	
	public void setNome(String nome) throws ExcecaoDeCadastro{
		validarNome(nome);
		this.nome = nome;
	}
	
	public void setSigla(String sigla) throws ExcecaoDeCadastro{
		validarSigla(sigla);
		this.sigla = sigla;
	}
	
	public void setLocalizacao(String localizacao) throws ExcecaoDeCadastro{
		validarLocalizacao(localizacao);
		this.localizacao = localizacao;
	}
}
