package cadastro;

import java.util.Collection;

import transferobject.InstituicaoTO;
import transferobject.UsuarioTO;
import util.AbstractGUI;

public interface AbstractGUICadastrarUsuario extends AbstractGUI {

	public UsuarioTO obterUsuarioCriado();
	public void atualizarListaDeInstituicoes(Collection<InstituicaoTO> instituicoes);
	public String obterConfirmacaoSenha();
}
