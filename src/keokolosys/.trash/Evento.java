package keokolosys;

import java.util.Collection;
import java.util.Date;

/**
 * Created by keokolo on 07/06/14.
 */
public class Evento {
    private String nome;
    private Usuario usuarioResponsavel;
    private Date dataMaximaParaSubmissaoDeTrabalhos;
    private Date dataMaximaParaAceitacaoDeTrabalhos;
    private Date dataDeInicio;
    private Date dataDeFim;
    private Collection<PerfilDeChair> chairs;
    private ControladorDeParticipacao controladorDeParticipacao;
    private ControladorDeAvaliacao controladorDeAvaliacao;
    private EstadoEvento estado;

    public Evento(String nome, Usuario usuarioResponsavel, Date dataMaximaParaSubmissaoDeTrabalhos, Date dataMaximaParaAceitacaoDeTrabalhos, Date dataDeInicio, Date dataDeFim) throws  ExcecaoDeCadastro{
        if(dadosValidos(nome, usuarioResponsavel, dataMaximaParaSubmissaoDeTrabalhos, dataMaximaParaAceitacaoDeTrabalhos, dataDeInicio, dataDeFim)){
            this.nome = nome;
            this.usuarioResponsavel = usuarioResponsavel;
            this.dataMaximaParaSubmissaoDeTrabalhos = dataMaximaParaSubmissaoDeTrabalhos;
            this.dataMaximaParaAceitacaoDeTrabalhos = dataMaximaParaAceitacaoDeTrabalhos;
            this.dataDeInicio = dataDeInicio;
            this.dataDeFim = dataDeFim;
        }
        else
            throw new ExcecaoDeCadastro("evento.dados.invalidos");
    }

	public EstadoEvento obterEstado(){
		return this.estado;
	}

	public void definirEstado(){
		
	} 

    public void subtmeterTrabalho(PerfilDeParticipante submissor, String titulo, String resumo, String autores, String caminhoArquivoSubmissao) throws ExcecaoDeCadastro{
        Trabalho trabalho = new Trabalho(submissor, titulo, resumo, autores, caminhoArquivoSubmissao);
        controladorDeParticipacao.submeterTrabalho(trabalho);
    }

    public void subtmeterVersaoFinalDeTrabalho(Trabalho trabalho, String caminhoArquivoFinal) throws ExcecaoDeCadastro{
        controladorDeParticipacao.submeterVersaoFinalDeTrabalho(trabalho, caminhoArquivoFinal);
    }


    public void inscreverParticipante(Usuario usuario) throws ExcecaoDeCadastro{
        controladorDeParticipacao.realizarInscricaoNoEvento(usuario, this);
    }

    public void concederPrivilegioDeChair(Usuario usuario) throws ExcecaoDeCadastro{
        Boolean possuiPerfilDeExaminador = usuario.possuiPerfil(this, PerfilDeExaminador.class);
        Boolean possuiPerfilDeParticipante = usuario.possuiPerfil(this, PerfilDeParticipante.class);

        if(possuiPerfilDeExaminador || possuiPerfilDeParticipante)
            throw new ExcecaoDeCadastro("evento.concessao.chair.negada");

        PerfilDeChair perfilDeChair = (PerfilDeChair) usuario.obterPerfilDe(this, PerfilDeChair.class);

        if(perfilDeChair == null) {
            perfilDeChair = new PerfilDeChair(usuario, this);
            usuario.adicionarPerfil(perfilDeChair);
            chairs.add(perfilDeChair);
        }
    }

    public void concederPrivilegioDeExaminador(Usuario usuario) throws ExcecaoDeCadastro{
        Boolean possuiPerfilDeChair = usuario.possuiPerfil(this, PerfilDeExaminador.class);
        Boolean possuiPerfilDeParticipante = usuario.possuiPerfil(this, PerfilDeParticipante.class);

        if(possuiPerfilDeChair || possuiPerfilDeParticipante)
            throw new ExcecaoDeCadastro("evento.concessao.examinador.negada");

        PerfilDeExaminador perfilDeExaminador = (PerfilDeExaminador) usuario.obterPerfilDe(this, PerfilDeExaminador.class);

        if(perfilDeExaminador == null) {
            perfilDeExaminador = new PerfilDeExaminador(usuario, this);
            usuario.adicionarPerfil(perfilDeExaminador);
            controladorDeAvaliacao.adicionarExaminador(perfilDeExaminador);
        }
    }

    private Boolean dadosValidos(String nome, Usuario usuarioResponsavel, Date dataMaximaParaSubmissaoDeTrabalhos, Date dataMaximaParaAceitacaoDeTrabalhos, Date dataDeInicio, Date dataDeFim){
        Boolean nomeValido = (nome != null) && (!nome.equals(""));
        Boolean usuarioResponsavelValido = (usuarioResponsavel != null);
        Boolean _datasValidas = datasValidas(dataMaximaParaSubmissaoDeTrabalhos, dataMaximaParaAceitacaoDeTrabalhos, dataDeInicio, dataDeFim);

        if(nomeValido && usuarioResponsavelValido && _datasValidas)
            return true;

        return  false;
    }

    private Boolean datasValidas(Date dataMaximaParaSubmissaoDeTrabalhos, Date dataMaximaParaAceitacaoDeTrabalhos, Date dataDeInicio, Date dataDeFim){
        Date dataAtual = new Date();

        if(dataAtual.compareTo(dataDeInicio) > 0)
            return false;

        if(dataDeInicio.compareTo(dataDeFim) > 0)
            return false;

        if(dataMaximaParaSubmissaoDeTrabalhos.compareTo(dataMaximaParaAceitacaoDeTrabalhos) > 0)
            return false;

        if(dataMaximaParaSubmissaoDeTrabalhos.compareTo(dataDeInicio) > 0)
            return false;

        return true;
    }

}