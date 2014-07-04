package keokolosys;


/**
 * Created by Luiz Fernando on 09/06/2014.
 */
public class EstadoEventoCancelado implements EstadoEvento {
	
	@Override
	public EstadoEvento realizaTransicao(Class<? extends EstadoEvento> estadoDestino) throws ExcecaoDeCadastro {
		throw new ExcecaoDeCadastro("evento.estado.transicao.invalida");
	}
}
