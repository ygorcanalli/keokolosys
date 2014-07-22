package participacao;

import java.util.Collection;

import transferobject.UsuarioTO;
import util.AbstractGUI;

public interface AbstractGUIConcederPrivilegios extends AbstractGUI {
	
	public void atualizarListaDeUsuariosSemParticipacaoNoEvento(Collection<UsuarioTO> usuariosTO);
	public void atualizarListaDeExaminadores(Collection<UsuarioTO> usuariosTO);
	public void atualizarListaDeChairs(Collection<UsuarioTO> usuariosTO);

}
