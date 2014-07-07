package dominio;

import excecao.ExcecaoDeCadastro;


public class Administrador extends Autenticavel {
	
	private Administrador(String email, String senha){
		super(email, senha);
	}
	
	public static Administrador criarAdministrador(String email, String senha) throws ExcecaoDeCadastro{
		validarDados(email, senha);
		
		return new Administrador(email, senha);
	}
	
	private static void validarDados(String email, String senha) throws ExcecaoDeCadastro{
		validarEmail(email);
		validarSenha(senha);
	}
	
	private static void validarEmail(String email) throws ExcecaoDeCadastro{
		Boolean emailVazio = (email == null) || (email.isEmpty());
		
        if (emailVazio)
        	throw new ExcecaoDeCadastro("administrador.email.vazio");
	}
	
	private static void validarSenha(String senha) throws ExcecaoDeCadastro{
        Boolean senhaVazia = (senha == null) || (senha.isEmpty());
        
        if (senhaVazia)
        	throw new ExcecaoDeCadastro("administrador.senha.vazia");
	}
	
	public void atualizarDados(String email, String senha) throws ExcecaoDeCadastro{
		
		validarDados(email, senha);
		
		if(this.email.compareTo(email) != 0)
			this.email = email;
		
		if(this.senha.compareTo(senha) != 0)
			this.senha = senha;		
	}
	
	/*public void setEmail(String email) throws ExcecaoDeCadastro{
		validarEmail(email);
		this.email = email;
	}
	
	public void setSenha(String senha) throws ExcecaoDeCadastro{
		validarSenha(senha);
		this.senha = senha;
	}*/
}
