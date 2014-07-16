package cadastro;

import java.util.Collection;

import transferobject.InstituicaoTO;
import transferobject.UsuarioTO;
import util.AbstractGUI;

public interface AbstractGUIAtualizarUsuario extends AbstractGUI {

	public String obterConfirmacaoSenha();
	public UsuarioTO obterAtualizacaoDosDadosDoUsuario();
	public void atualizarListaDeInstituicoes(Collection<InstituicaoTO> instituicoes);
	public void exibirDadosDoUsuario(UsuarioTO usuario);

}
