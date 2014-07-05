package dominio;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import catalago.CatalogoDeTrabalhos;
import estadoevento.*;
import excecao.*;


public class Evento {
    private String nome;
    private Usuario usuarioResponsavel;
    private Date dataMaximaParaSubmissaoDeTrabalhos;
    private Date dataMaximaParaAceitacaoDeTrabalhos;
    private Date dataDeInicio;
    private Date dataDeFim;
    private Map<Class<? extends Perfil>, Perfil> perfis;
    private Collection<BancaExaminadora> bancasExaminadoras;
    private CatalogoDeTrabalhos catalogoDeTrabalhos;
    private EstadoEvento estado;

    private Evento(String nome, Usuario usuarioResponsavel, Date dataMaximaParaSubmissaoDeTrabalhos, Date dataMaximaParaAceitacaoDeTrabalhos, Date dataDeInicio, Date dataDeFim) throws  ExcecaoDeCadastro{
            this.nome = nome;
            this.usuarioResponsavel = usuarioResponsavel;
            this.dataMaximaParaSubmissaoDeTrabalhos = dataMaximaParaSubmissaoDeTrabalhos;
            this.dataMaximaParaAceitacaoDeTrabalhos = dataMaximaParaAceitacaoDeTrabalhos;
            this.dataDeInicio = dataDeInicio;
            this.dataDeFim = dataDeFim;
            this.catalogoDeTrabalhos = new CatalogoDeTrabalhos();
            this.estado = new EstadoEventoAguardando();
    }
    
    public static Evento criarEvento(String nome, Usuario usuarioResponsavel, Date dataMaximaParaSubmissaoDeTrabalhos, Date dataMaximaParaAceitacaoDeTrabalhos, Date dataDeInicio, Date dataDeFim) throws  ExcecaoDeCadastro{
    	validarDados(nome, usuarioResponsavel, dataMaximaParaSubmissaoDeTrabalhos, dataMaximaParaAceitacaoDeTrabalhos, dataDeInicio, dataDeFim);
    
    	return new Evento(nome, usuarioResponsavel, dataMaximaParaSubmissaoDeTrabalhos, dataMaximaParaAceitacaoDeTrabalhos, dataDeInicio, dataDeFim);
    }

	public EstadoEvento obterEstado(){
		return this.estado;
	}

    public void subtmeterTrabalho(PerfilDeParticipante submissor, String titulo, String resumo, String autores, String caminhoArquivoSubmissao) throws ExcecaoDeCadastro{
        Trabalho trabalho = Trabalho.criarTrabalho(submissor, titulo, resumo, autores, caminhoArquivoSubmissao);
        catalogoDeTrabalhos.adicionarTrabalho(trabalho);
    }

    public void subtmeterVersaoFinalDeTrabalho(Trabalho trabalho, String caminhoArquivoFinal) throws ExcecaoDeCadastro{
    	if (catalogoDeTrabalhos.obterTrabalhosAceitos().contains(trabalho)) {
        	trabalho.submeterVersaoFinal(caminhoArquivoFinal);	
    	} else {
    		throw new ExcecaoDeCadastro("evento.submeter_versao_final_de_trabalho.nao_pertence_trabalhos_aceitos");
    	}
    }
    
    public BancaExaminadora criarBancaExaminadora(Collection<PerfilDeExaminador> examinadores) throws ExcecaoDeAvaliacao{
    	BancaExaminadora bancaExaminadora = BancaExaminadora.criarBancaExaminadora(examinadores);
    	bancasExaminadoras.add(bancaExaminadora);
    	return bancaExaminadora;
    }


    public void inscreverParticipante(Usuario usuario) throws ExcecaoDeCadastro{
    	criarPerfil(usuario, PerfilDeParticipante.class);
    }
    
    public void concederPrivilegioDeExaminador(Usuario usuario) throws ExcecaoDeCadastro{
    	criarPerfil(usuario, PerfilDeExaminador.class);
    }
    
    public void concederPrivilegioDeChair(Usuario usuario) throws ExcecaoDeCadastro{
    	criarPerfil(usuario, PerfilDeChair.class);
    }

    private void criarPerfil(Usuario usuario, Class<? extends Perfil> tipoPerfil) throws ExcecaoDeCadastro{
    	Perfil perfil = usuario.obterPerfil(this);
    	
    	if(perfil == null)
    	{
            try {
				perfil = tipoPerfil.getConstructor(Usuario.class, Evento.class).newInstance(usuario, this);
				
			} catch (InstantiationException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
            
            usuario.adicionarPerfil(perfil);
            perfis.put(tipoPerfil, perfil);    		
    	}
    	else if (!tipoPerfil.isInstance(perfil)){
    		throw new ExcecaoDeCadastro("evento.usuario.possui_perfil");
    	}    	
    }

    private static void validarDados(String nome, Usuario usuarioResponsavel, Date dataMaximaParaSubmissaoDeTrabalhos, Date dataMaximaParaAceitacaoDeTrabalhos, Date dataDeInicio, Date dataDeFim) throws ExcecaoDeCadastro{
        Boolean nomeVazio = (nome == null) || (nome.equals(""));
        Boolean usuarioResponsavelVazio = (usuarioResponsavel != null);
        
        if(nomeVazio)
        	throw new ExcecaoDeCadastro("evento.nome.vazio");
        
        if(usuarioResponsavelVazio)
        	throw new ExcecaoDeCadastro("evento.usuario_responsavel.vazio");
        
        validarDatas(dataMaximaParaSubmissaoDeTrabalhos, dataMaximaParaAceitacaoDeTrabalhos, dataDeInicio, dataDeFim);   
    }

    private static void validarDatas(Date dataMaximaParaSubmissaoDeTrabalhos, Date dataMaximaParaAceitacaoDeTrabalhos, Date dataDeInicio, Date dataDeFim) throws ExcecaoDeCadastro{
        Date dataAtual = new Date();

        if(dataAtual.compareTo(dataDeInicio) > 0)
            throw new ExcecaoDeCadastro("evento.data_de_inicio.invalida");

        if(dataDeInicio.compareTo(dataDeFim) > 0)
        	throw new ExcecaoDeCadastro("evento.data_de_fim.invalida");

        if(dataMaximaParaSubmissaoDeTrabalhos.compareTo(dataMaximaParaAceitacaoDeTrabalhos) > 0)
        	throw new ExcecaoDeCadastro("evento.data_maxima_para_aceitacao_de_trabalhos.invalida");

        if(dataMaximaParaSubmissaoDeTrabalhos.compareTo(dataDeInicio) > 0)
        	throw new ExcecaoDeCadastro("evento.data_maxima_para_submissao_de_trabalhos.invalida");
    }

	public String getNome() {
		return nome;
	}

	public Usuario getUsuarioResponsavel() {
		return usuarioResponsavel;
	}

	public Date getDataMaximaParaSubmissaoDeTrabalhos() {
		return dataMaximaParaSubmissaoDeTrabalhos;
	}

	public Date getDataMaximaParaAceitacaoDeTrabalhos() {
		return dataMaximaParaAceitacaoDeTrabalhos;
	}

	public Date getDataDeInicio() {
		return dataDeInicio;
	}

	public Date getDataDeFim() {
		return dataDeFim;
	}
    
	public Collection<BancaExaminadora> getBancasExaminadoras() {
		return bancasExaminadoras;
	}
	
	
	public void cancelar() {
		try {
		    this.alterarEstado(EstadoEventoCancelado.class);
		} catch (ExcecaoDeCadastro excecaoDeCadastro) {
		    excecaoDeCadastro.printStackTrace();
		}
	}

	public void indeferir() {
		try {
			this.alterarEstado(EstadoEventoIndeferido.class);
		} catch (ExcecaoDeCadastro excecaoDeCadastro) {
			excecaoDeCadastro.printStackTrace();
		}
	}
	
	public void deferir() {
	    try {
	    	this.alterarEstado(EstadoEventoDeferido.class);
	    } catch (ExcecaoDeCadastro excecaoDeCadastro) {
	    	excecaoDeCadastro.printStackTrace();
	    }
	}
	
	public void finalizar() {
	    try {
	    	this.alterarEstado(EstadoEventoFinalizado.class);
	    } catch (ExcecaoDeCadastro excecaoDeCadastro) {
	    	excecaoDeCadastro.printStackTrace();
	    }
	}
	
	private void alterarEstado(Class<? extends EstadoEvento> estadoDestino) throws ExcecaoDeCadastro {
		this.estado = this.estado.realizaTransicao(estadoDestino);
	}
   
}
