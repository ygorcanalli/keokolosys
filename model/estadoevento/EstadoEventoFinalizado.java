package estadoevento;

import excecao.ExcecaoDeCadastro;

public class EstadoEventoFinalizado implements EstadoEvento{
	
	@Override
	public EstadoEvento realizaTransicao(Class<? extends EstadoEvento> estadoDestino) throws ExcecaoDeCadastro {
		throw new ExcecaoDeCadastro("evento.estado.transicao.invalida");
	}
	
}
