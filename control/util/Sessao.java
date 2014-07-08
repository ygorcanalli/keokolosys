package util;

import dominio.Usuario;

public class Sessao {

	private Usuario usuarioLogado;
	
	public Sessao(Usuario usuarioLogado){
		this.usuarioLogado = usuarioLogado;
	}
	
	
	public Usuario getUsuarioLogado(){
		return this.usuarioLogado;
	}
	
}
