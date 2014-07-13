package dominio;

public abstract class Autenticavel {
	
	protected String email;
	protected String senha;
	
	protected Autenticavel(String email, String senha){
		this.email = email;
		this.senha = senha;
	}
	
    public Boolean realizarAutenticacao(String senha) {
        Boolean senhaCorreta = this.senha.compareTo(senha) == 0;

        return senhaCorreta;
    }
    
    public String getEmail(){
    	return this.email;
    }    
}
