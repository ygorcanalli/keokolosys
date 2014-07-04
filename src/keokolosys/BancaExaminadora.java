package keokolosys;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class BancaExaminadora {
    private Set<PerfilDeExaminador> examinadores;
    private Set<Trabalho> trabalhosAssociados;
    private static final Set<Integer> NUMEROS_DE_EXAMINADORES_PERMITIDO = new HashSet<Integer>(1,3);

    public Set<Trabalho> obterTrabalhosAssociados() {
        return trabalhosAssociados;
    }
    
    private BancaExaminadora(PerfilDeExaminador... examinadores) {
    	this.examinadores = new HashSet<PerfilDeExaminador>();
    	this.examinadores.addAll(Arrays.asList(examinadores));
    	
    	this.trabalhosAssociados = new HashSet<Trabalho>();
    }
    
    public static BancaExaminadora criarBancaExaminadora(PerfilDeExaminador... examinadores) throws ExcecaoDeCadastro{
    	validarDados(examinadores);
    	return new BancaExaminadora(examinadores);
    }
    
    /*public static BancaExaminadora criarBancaExaminadora(PerfilDeExaminador examinador1, PerfilDeExaminador examinador2, PerfilDeExaminador examinador3) throws ExcecaoDeCadastro{
    	validarDados(examinador1, examinador2, examinador3);
    	return new BancaExaminadora(examinador1, examinador2, examinador3); 
    }*/
    
    private static void validarDados(PerfilDeExaminador... examinadores) throws ExcecaoDeCadastro{
    	Set<PerfilDeExaminador> setExaminadores = new HashSet<PerfilDeExaminador>();
    	setExaminadores.addAll(Arrays.asList(examinadores));
    	if (setExaminadores.contains(null))
    		throw new ExcecaoDeCadastro("banca_examinadora.examinador.vazio");
    	
    	if(NUMEROS_DE_EXAMINADORES_PERMITIDO.contains(setExaminadores.size()))
    		throw new ExcecaoDeCadastro("banca_examinadora.examinador.repetido");    	
    }
    
    public void associarTrabalho(Trabalho trabalho){
    	trabalhosAssociados.add(trabalho);
    }
   
}
