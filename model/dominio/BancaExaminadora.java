package dominio;


import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import excecao.ExcecaoDeAvaliacao;


public class BancaExaminadora {
    private Set<PerfilDeExaminador> examinadores;
    private Set<Trabalho> trabalhosAssociados;
    private static final Set<Integer> NUMEROS_DE_EXAMINADORES_PERMITIDO = new HashSet<Integer>(1,3);

    public Set<Trabalho> obterTrabalhosAssociados() {
        return trabalhosAssociados;
    }
    
    private BancaExaminadora(Collection<PerfilDeExaminador> examinadores) {
    	this.examinadores = new HashSet<PerfilDeExaminador>();
    	this.examinadores.addAll(examinadores);
    	
    	this.trabalhosAssociados = new HashSet<Trabalho>();
    }
    
    public static BancaExaminadora criarBancaExaminadora(Collection<PerfilDeExaminador> examinadores) throws ExcecaoDeAvaliacao{
    	validarDados(examinadores);
    	return new BancaExaminadora(examinadores);
    }
    
    /*public static BancaExaminadora criarBancaExaminadora(PerfilDeExaminador examinador1, PerfilDeExaminador examinador2, PerfilDeExaminador examinador3) throws ExcecaoDeCadastro{
    	validarDados(examinador1, examinador2, examinador3);
    	return new BancaExaminadora(examinador1, examinador2, examinador3); 
    }*/
    
    private static void validarDados(Collection<PerfilDeExaminador> examinadores) throws ExcecaoDeAvaliacao{
    	Set<PerfilDeExaminador> setExaminadores = new HashSet<PerfilDeExaminador>();
    	setExaminadores.addAll(examinadores);
    	if (setExaminadores.contains(null))
    		throw new ExcecaoDeAvaliacao("banca_examinadora.examinador.vazio");
    	
    	if(NUMEROS_DE_EXAMINADORES_PERMITIDO.contains(setExaminadores.size()))
    		throw new ExcecaoDeAvaliacao("banca_examinadora.examinador.repetido");    	
    }
    
    public void associarTrabalho(Trabalho trabalho){
    	// Realiza a associassão com trabalho de maneira espelhada
    	trabalhosAssociados.add(trabalho);
    	trabalho.atribuirBancaExaminadoraResponsavel(this);
    }
    
    public void avaliarTrabalho(Trabalho trabalho, PerfilDeExaminador examinador, EstadoAvaliacao resultado) throws ExcecaoDeAvaliacao {
    	validarAvaliacao(trabalho, examinador);
    	Avaliacao avaliacao = Avaliacao.criarAvaliacao(trabalho, examinador, resultado);
    	trabalho.atribuirAvaliacao(avaliacao);
    }
    
    public Set<PerfilDeExaminador> getExaminadores(){
    	return this.examinadores;
    }

    public Boolean possuiOExaminador(PerfilDeExaminador examinador){
    	return this.examinadores.contains(examinador);
    }
    
    public Integer obterNumeroDeExaminadores() {
    	return examinadores.size();
    }
    
    private void validarAvaliacao(Trabalho trabalho, PerfilDeExaminador examinador) throws ExcecaoDeAvaliacao {
    	Boolean trabalhoNaoPertencente = !trabalhosAssociados.contains(trabalho);
    	Boolean examinadorNaoPertencente = !examinadores.contains(examinador);
    	
    	if (trabalhoNaoPertencente)
    		throw new ExcecaoDeAvaliacao("banca_examinadora.trabalho_nao_pertencente");
    	
    	if (examinadorNaoPertencente)
    		throw new ExcecaoDeAvaliacao("banca_examinadora.examinador.nao_pertence");
    }
   
}
