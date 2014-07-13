package transferobject;

import java.util.Collection;

public class BancaExaminadoraTO {
	
	private Collection<UsuarioTO> examinadores;
	
	public BancaExaminadoraTO(){
		
	}
	
	public BancaExaminadoraTO(Collection<UsuarioTO> examinadores){
		this.examinadores = examinadores;
	}

	public Collection<UsuarioTO> getExaminadores() {
		return examinadores;
	}

	public void setExaminadores(Collection<UsuarioTO> examinadores) {
		this.examinadores = examinadores;
	}
}
