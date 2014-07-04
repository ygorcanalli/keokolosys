package keokolosys;

import java.util.Collection;

public class ControladorDeParticipacao {

    private Collection<PerfilDeParticipante> participantes;
    private CatalogoDeTrabalhos catalogoDeTrabalhos;

    public static void realizarInscricaoNoEvento(Usuario usuario, Evento evento) throws ExcecaoDeCadastro{
        Boolean possuiPerfilDeExaminador = usuario.possuiPerfil(evento, PerfilDeExaminador.class);
        Boolean possuiPerfilDeChair = usuario.possuiPerfil(evento, PerfilDeChair.class);

        if(possuiPerfilDeExaminador || possuiPerfilDeChair)
            throw new ExcecaoDeCadastro("controladordeparticipacao.inscricao.participante.negada");

        PerfilDeParticipante perfilDeParticipante = (PerfilDeParticipante) usuario.obterPerfilDe(evento, PerfilDeParticipante.class);

        if(perfilDeParticipante == null) {
            perfilDeParticipante = new PerfilDeParticipante(usuario, evento);
            usuario.adicionarPerfil(perfilDeParticipante);
            participantes.add(perfilDeParticipante);
        }
    }

    public static void submeterTrabalho(Trabalho trabalho) {
        catalogoDeTrabalhos.adicionarTrabalho(trabalho);
    }

    public static void submeterVersaoFinalDeTrabalho(Trabalho trabalho, String caminhoArquivoFinal) throws ExcecaoDeCadastro{
        trabalho.definirVersaoFinal(caminhoArquivoFinal);
    }

    public Collection<PerfilDeParticipante> obterParticipantes() {
       return participantes;
    }
}
