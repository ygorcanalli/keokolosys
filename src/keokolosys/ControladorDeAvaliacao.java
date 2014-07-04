package keokolosys;


import java.util.Collection;

/**
 * Created by keokolo on 07/06/14.
 */
public class ControladorDeAvaliacao {
    private Collection<PerfilDeExaminador> examinadores;
    private Collection<BancaExaminadora> bancasExaminadoras;
    private CatalogoDeTrabalhos catalogoDeTrabalhos;

    public BancaExaminadora criarBancaExaminadora(Collection<PerfilDeExaminador> examinadores) {
        return null;
    }

    public Boolean associarBancaExaminadoraATrabalho(BancaExaminadora bancaExaminadora, Trabalho trabalho) {
        return null;
    }

    public Avaliacao avaliarTrabalho(Trabalho trabalho) {
        return null;
    }

    public static void adicionarExaminador(PerfilDeExaminador perfilDeExaminador){
        examinadores.add(perfilDeExaminador);
    }

}
