package controladorGRASP;

import java.util.*;

import catalago.Singleton;
import dominio.Instituicao;
import dominio.Usuario;
import excecao.ExcecaoDeCadastro;

public class ControladorDeCadastro implements Singleton {

	private ControladorDeCadastro instancia;
	private Map<String, Usuario> usuarios;
	
	@Override
	public ControladorDeCadastro obterInsancia() {
		if (instancia == null)
			instancia =  new ControladorDeCadastro();
		
		return instancia;
	}

    public void criarUsuario(String nome, String ultimoNome, String email, String senha, Instituicao instituicao) throws  ExcecaoDeCadastro{
        if(obterUsuario(email) == null){
            Usuario usuario = Usuario.criarUsuario(nome, ultimoNome, email, senha, instituicao);
            usuarios.put(email, usuario);
        }
        else
            throw new ExcecaoDeCadastro("usuario.email.existente");
    }

    

    private Usuario obterUsuario(String email){
        return usuarios.get(email);
    }

}
