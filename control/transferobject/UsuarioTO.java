package transferobject;

public class UsuarioTO {
	
	private String email;
	private String nome;
    private String ultimoNome;
    private InstituicaoTO instituicao;
    
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
    
    

}
