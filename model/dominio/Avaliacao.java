package dominio;

import excecao.ExcecaoDeAvaliacao;

public class Avaliacao {
    private Trabalho trabalho;
    private PerfilDeExaminador examinador;
    private EstadoAvaliacao resultado;
    
    private Avaliacao(Trabalho trabalho, PerfilDeExaminador examinador, EstadoAvaliacao resultado){
    	this.trabalho = trabalho;
    	this.examinador = examinador;
    	this.resultado = resultado;
    }
    
    public static Avaliacao criarAvaliacao(Trabalho trabalho, PerfilDeExaminador examinador, EstadoAvaliacao resultado) throws ExcecaoDeAvaliacao{
    	validarDados(trabalho, examinador);
    	return new Avaliacao(trabalho, examinador, resultado);
    }
    
    public EstadoAvaliacao getResultado(){
    	return this.resultado;
    }
    
    public Trabalho getTrabalho(){
    	return this.trabalho;
    }
    
    public PerfilDeExaminador getExaminador(){
    	return this.examinador;
    }
    
    private static void validarDados(Trabalho trabalho, PerfilDeExaminador examinador) throws ExcecaoDeAvaliacao{
    	Boolean trabalhoVazio = trabalho == null;
    	Boolean examinadorVazio = examinador == null;
    	
    	if(trabalhoVazio)
    		throw new ExcecaoDeAvaliacao("avaliacao.trabalho.vazio");
    	
    	if(examinadorVazio)
    		throw new ExcecaoDeAvaliacao("avaliacao.examinador.vazio");
    }
}
