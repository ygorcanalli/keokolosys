package dominio;

import java.util.ArrayList;
import java.util.Collection;

public class PerfilDeExaminador extends Perfil{

    /*private Collection<BancaExaminadora> bancasExaminadoras;*/

    public PerfilDeExaminador(Usuario usuario, Evento evento){
        super(usuario, evento);
        /*bancasExaminadoras = new ArrayList<BancaExaminadora>();*/
    }

    /*public Collection<BancaExaminadora> obterBancasExaminadorasAssociadas(){
        return this.bancasExaminadoras;
    }*/
    
    public Collection<BancaExaminadora> obterBancasExaminadorasAssociadas(){
		Collection<BancaExaminadora> bancasExaminadorasAssociadas = new ArrayList<BancaExaminadora>();
		
		for (BancaExaminadora bancaExaminadora : this.getEvento().getBancasExaminadoras()) {
			if(bancaExaminadora.possuiOExaminador(this))
				bancasExaminadorasAssociadas.add(bancaExaminadora);
		}
		
		return bancasExaminadorasAssociadas;
	}	
}


