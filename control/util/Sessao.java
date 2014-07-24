package util;

import dominio.Administrador;
import dominio.Usuario;

public class Sessao {

	private static Usuario usuarioLogado;
	private static Administrador adminLogado;
	
	public static void iniciarSessao(Usuario usuario){
		usuarioLogado = usuario;
	}
	
	public static void iniciarSessaoDeAdministrador(Administrador admin){
		adminLogado = admin;
	}
	
	public static Usuario getUsuarioLogado(){
		return usuarioLogado;
	}
	
	public static Administrador getAdminLogado(){
		return adminLogado;
	}
	
	public static void encerrarSessao(){
		usuarioLogado = null;
		adminLogado = null;
	}
}
