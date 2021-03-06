package estadoevento;

import enumerados.EnumEstadoEvento;
import excecao.ExcecaoDeCadastro;


public class EstadoEventoIndeferido implements EstadoEvento {

	@Override
	public EstadoEvento realizaTransicao(Class<? extends EstadoEvento> estadoDestino) throws ExcecaoDeCadastro {
		throw new ExcecaoDeCadastro("evento.estado.transicao.invalida");
	}
	
	@Override
	public EnumEstadoEvento obterTipoEnumerado() {
		return EnumEstadoEvento.INDEFERIDO;
	}
}
