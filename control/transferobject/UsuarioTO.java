package transferobject;

public class UsuarioTO {
	
	private String email;
	private String nome;
    private String ultimoNome;
    private InstituicaoTO instituicao;
    private String senha;
    
    public UsuarioTO(){
    	
    }
    

	public UsuarioTO(String email, String senha, String nome, String ultimoNome,
			InstituicaoTO instituicao) {
		super();
		this.email = email;
		this.senha = senha;
		this.nome = nome;
		this.ultimoNome = ultimoNome;
		this.instituicao = instituicao;
	}
    
    
	public UsuarioTO(String email, String nome, String ultimoNome,
			InstituicaoTO instituicao) {
		super();
		this.email = email;
		this.nome = nome;
		this.ultimoNome = ultimoNome;
		this.instituicao = instituicao;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getUltimoNome() {
		return ultimoNome;
	}
	public void setUltimoNome(String ultimoNome) {
		this.ultimoNome = ultimoNome;
	}
	public InstituicaoTO getInstituicao() {
		return instituicao;
	}
	public void setInstituicao(InstituicaoTO instituicao) {
		this.instituicao = instituicao;
	}
	
	public void setSenha(String senha){
		this.senha = senha;
	}
    
	public String getSenha(){
		return this.senha;
	}
    

}
