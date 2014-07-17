package estadoevento;

import enumerados.EnumEstadoEvento;
import excecao.ExcecaoDeCadastro;

public class EstadoEventoAguardando implements EstadoEvento {

    @Override
    public EstadoEvento realizaTransicao(Class<? extends EstadoEvento> estadoDestino) throws ExcecaoDeCadastro {
        if (estadoDestino.equals(EstadoEventoDeferido.class))
            return new EstadoEventoDeferido();

        if (estadoDestino.equals(EstadoEventoIndeferido.class))
            return new EstadoEventoIndeferido();

        throw new ExcecaoDeCadastro("evento.estado.transicao.invalida");
    }

	@Override
	public EnumEstadoEvento obterTipoEnumerado() {
		return EnumEstadoEvento.AGUARDANDO;
	}
    
}
