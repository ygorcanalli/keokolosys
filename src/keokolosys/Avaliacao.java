package keokolosys;

public class Avaliacao {
    private Trabalho trabalho;
    private PerfilDeExaminador examinador;
    private EstadoAvaliacao resultado;
    
    private Avaliacao(Trabalho trabalho, PerfilDeExaminador examinador, EstadoAvaliacao resultado){
    	this.trabalho = trabalho;
    	this.examinador = examinador;
    	this.resultado = resultado;
    }
    
    public Avaliacao criarAvaliacao(Trabalho trabalho, PerfilDeExaminador examinador, EstadoAvaliacao resultado) throws ExcecaoDeCadastro{
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
    
    private void validarDados(Trabalho trabalho, PerfilDeExaminador examinador) throws ExcecaoDeCadastro{
    	Boolean trabalhoVazio = trabalho == null;
    	Boolean examinadorVazio = examinador == null;
    	
    	if(trabalhoVazio)
    		throw new ExcecaoDeCadastro("avaliacao.trabalho.vazio");
    	
    	if(examinadorVazio)
    		throw new ExcecaoDeCadastro("avaliacao.examinador.vazio");
    }
}
