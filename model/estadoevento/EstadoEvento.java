package estadoevento;

import excecao.ExcecaoDeCadastro;

public interface EstadoEvento {

	EstadoEvento realizaTransicao(Class<? extends EstadoEvento> estadoDestino) throws ExcecaoDeCadastro;
}
