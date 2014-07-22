package transferobject;

import java.util.ArrayList;
import java.util.Collection;

public class BancaExaminadoraTO{
	
	private Collection<UsuarioTO> examinadores;
	private Integer quantidadeDeTrabalhosAssociados;
	
	public BancaExaminadoraTO(){
		examinadores = new ArrayList<UsuarioTO>();
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

	public Integer getQuantidadeDeTrabalhosAssociados() {
		return quantidadeDeTrabalhosAssociados;
	}
	
	public void setQuantidadeDeTrabalhosAssociados(Integer quantidadeDeTrabalhosAssociados){
		this.quantidadeDeTrabalhosAssociados = quantidadeDeTrabalhosAssociados;
	}
	
	/*public BancaExaminadoraTO clone(){
		BancaExaminadoraTO bancaExaminadoraTO = new BancaExaminadoraTO();
		bancaExaminadoraTO.examinadores.addAll(this.examinadores);
		return bancaExaminadoraTO;
	}*/

}
