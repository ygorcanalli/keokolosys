package cadastro;

import java.util.Collection;

import transferobject.EventoTO;
import transferobject.InstituicaoTO;
import transferobject.UsuarioTO;
import util.AbstractGUI;

public interface AbstractGUICadastrarEvento extends AbstractGUI {
	
	public void fechar();
	public void atualizarListaDeInstituicoes(Collection<InstituicaoTO> instituicoes);
	public void definirUsuarioResponsavel(UsuarioTO usuarioResponsavel);
	public void criarEvento();
	public EventoTO obterEventoCriado();
	
}
