package dominio;

public abstract class Autenticavel {
	
	private String email;
	private String senha;
	
    public Boolean realizarAutenticacao(String senha) {
        Boolean senhaCorreta = this.senha == senha;

        return senhaCorreta;
    }
    
    public String getEmail(){
    	return this.email;
    }
    
}
