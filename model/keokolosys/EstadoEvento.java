package keokolosys;

public interface EstadoEvento {

	EstadoEvento realizaTransicao(Class<? extends EstadoEvento> estadoDestino) throws ExcecaoDeCadastro;
}
