package transferobject;

public class InstituicaoTO {
	
	private String nome;
    private String sigla;
    private String localizacao;
    
    
    public InstituicaoTO(){
    }
    
	public InstituicaoTO(String nome, String sigla, String localizacao) {
		super();
		this.nome = nome;
		this.sigla = sigla;
		this.localizacao = localizacao;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getSigla() {
		return sigla;
	}
	
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
	public String getLocalizacao() {
		return localizacao;
	}
	
	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}
	
	@Override
	public String toString(){
		return this.sigla + "-" + this.nome;
	}
}
