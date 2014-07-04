package keokolosys;

public class Instituicao {
    private String nome;
    private String sigla;
    private String localizacao;

    private Instituicao(String nome, String sigla, String localizacao) throws ExcecaoDeCadastro{
		this.nome = nome;
		this.sigla = sigla;
		this.localizacao = localizacao;
    }
    
    public Instituicao criarInstituicao(String nome, String sigla, String localizacao) throws ExcecaoDeCadastro{
    	validarDados(nome, sigla, localizacao);
    	return new Instituicao(nome, sigla, localizacao);
    }

    private void validarDados(String nome, String sigla, String localizacao) throws ExcecaoDeCadastro{
        Boolean nomeVazio = (nome == null) || (nome.isEmpty());
        Boolean siglaVazia = (sigla == null) || (sigla.isEmpty());
        Boolean localizacaoVazia = (localizacao == null) || (localizacao.isEmpty());

        if(nomeVazio)
        	throw new ExcecaoDeCadastro("instituicao.nome.vazio");
        
        if(siglaVazia)
        	throw new ExcecaoDeCadastro("instituicao.sigla.vazia");
        
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
}
