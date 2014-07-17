package estadoevento;

import enumerados.EnumEstadoEvento;
import excecao.ExcecaoDeCadastro;

public class EstadoEventoDeferido implements EstadoEvento {

	 @Override
	public EstadoEvento realizaTransicao(Class<? extends EstadoEvento> estadoDestino) throws ExcecaoDeCadastro {
		if (estadoDestino.equals(EstadoEventoCancelado.class))
			return new EstadoEventoCancelado();
	
		if (estadoDestino.equals(EstadoEventoFinalizado.class))
			return new EstadoEventoFinalizado();
	
		throw new ExcecaoDeCadastro("evento.estado.transicao.invalida");
	}
	 
		@Override
		public EnumEstadoEvento obterTipoEnumerado() {
			return EnumEstadoEvento.DEFERIDO;
		}
}
