package util;

import dominio.Usuario;

public class Sessao {

	private static Usuario usuarioLogado;
	
	public static void iniciarSessao(Usuario usuario){
		usuarioLogado = usuario;
	}
	
	public static Usuario getUsuarioLogado(){
		return usuarioLogado;
	}
	
	public static void encerrarSessao(){
		usuarioLogado = null;
	}
}
