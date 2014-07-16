package dominio;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import estadoevento.*;
import excecao.*;


public class Evento{
    private String nome;
    private Instituicao instituicao;
    private Usuario usuarioResponsavel;
    private Date dataMaximaParaSubmissaoDeTrabalhos;
    private Date dataMaximaParaAceitacaoDeTrabalhos;
    private Date dataDeInicio;
    private Date dataDeFim;
    private Map<String, Collection<Perfil>> perfis;
    private Collection<BancaExaminadora> bancasExaminadoras;
    private Collection<Trabalho> trabalhos;
    private EstadoEvento estado;

    private Evento(String nome, Instituicao instituicao, Usuario usuarioResponsavel, Date dataMaximaParaSubmissaoDeTrabalhos, Date dataMaximaParaAceitacaoDeTrabalhos, Date dataDeInicio, Date dataDeFim){
            this.nome = nome;
            this.instituicao = instituicao;
            this.usuarioResponsavel = usuarioResponsavel;
            this.dataMaximaParaSubmissaoDeTrabalhos = dataMaximaParaSubmissaoDeTrabalhos;
            this.dataMaximaParaAceitacaoDeTrabalhos = dataMaximaParaAceitacaoDeTrabalhos;
            this.dataDeInicio = dataDeInicio;
            this.dataDeFim = dataDeFim;
            this.trabalhos = new ArrayList<Trabalho>();
            this.estado = new EstadoEventoAguardando();
            this.perfis = new TreeMap<String, Collection<Perfil>>();
            this.bancasExaminadoras = new ArrayList<BancaExaminadora>();
    }
    
    public static Evento criarEvento(String nome, Instituicao instituicao, Usuario usuarioResponsavel, Date dataMaximaParaSubmissaoDeTrabalhos, Date dataMaximaParaAceitacaoDeTrabalhos, Date dataDeInicio, Date dataDeFim) throws  ExcecaoDeCadastro{
    	validarDados(nome, instituicao, usuarioResponsavel, dataMaximaParaSubmissaoDeTrabalhos, dataMaximaParaAceitacaoDeTrabalhos, dataDeInicio, dataDeFim);
    
    	return new Evento(nome, instituicao, usuarioResponsavel, dataMaximaParaSubmissaoDeTrabalhos, dataMaximaParaAceitacaoDeTrabalhos, dataDeInicio, dataDeFim);
    }

	public EstadoEvento obterEstado(){
		return this.estado;
	}


	/*Referente a banca examinadora*/
    
    public BancaExaminadora criarBancaExaminadora(Collection<PerfilDeExaminador> examinadores) throws ExcecaoDeAvaliacao{
    	validarBancaComoUnica(examinadores);
    	BancaExaminadora bancaExaminadora = BancaExaminadora.criarBancaExaminadora(examinadores);
    	bancasExaminadoras.add(bancaExaminadora);
    	return bancaExaminadora;
    }
    
    public void atualizarBancaExaminadora(BancaExaminadora bancaExaminadora, Collection<PerfilDeExaminador> examinadores) throws ExcecaoDeAvaliacao{
    	if(!bancaExaminadora.formadaPelosExaminadores(examinadores))
    		validarBancaComoUnica(examinadores);
    	
    	bancaExaminadora.atualizarExaminadores(examinadores);
    }
    
    public void removerBancaExaminadora(BancaExaminadora bancaBancaExaminadora) throws ExcecaoDeAvaliacao{
    	if(bancaBancaExaminadora.obterTrabalhosAssociados().size() > 0)
    		throw new ExcecaoDeAvaliacao("evento.banca_examinadora.possui_trabalhos_associados");
    	
    	bancasExaminadoras.remove(bancaBancaExaminadora);
    }
    
    private void validarBancaComoUnica(Collection<PerfilDeExaminador> examinadores) throws ExcecaoDeAvaliacao{
    	BancaExaminadora bancaExaminadora = buscarBancaExaminadoraPelosExaminadores(examinadores);
    	
    	if(bancaExaminadora != null)
    		throw new ExcecaoDeAvaliacao("evento.banca_examinadora.existente");
    }
    
    public BancaExaminadora obterBancaExaminadoraPelosExaminadores(Collection<PerfilDeExaminador> examinadores) throws ExcecaoDeAvaliacao{
    	BancaExaminadora bancaExaminadora = buscarBancaExaminadoraPelosExaminadores(examinadores);
    	
    	if(bancaExaminadora == null)
    		throw new ExcecaoDeAvaliacao("evento.banca_examinadora.inexistente");
    	
    	return bancaExaminadora;
    }
    
    private BancaExaminadora buscarBancaExaminadoraPelosExaminadores(Collection<PerfilDeExaminador> examinadores){
    	for (BancaExaminadora bancaExaminadora : bancasExaminadoras) {
			if(bancaExaminadora.formadaPelosExaminadores(examinadores))
				return bancaExaminadora;
    	}
    	
		return null;
    }

    /*Referente a inscricao de participante e concessao de privilegios*/

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
    	Collection<Perfil> perfisDoTipo = null;
    	
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
            
            perfisDoTipo = perfis.get(tipoPerfil.getName());
            if (perfisDoTipo == null) {
            	perfisDoTipo =  new ArrayList<Perfil>();
            	perfis.put(tipoPerfil.getName(), perfisDoTipo);
            }
    	
    		perfisDoTipo.add(perfil);
    	}
    	else if (!tipoPerfil.isInstance(perfil)){
    		throw new ExcecaoDeCadastro("evento.usuario.possui_perfil");
    	}    	
    }
    
	public Collection<PerfilDeParticipante> obterParticipantes(){
    	Class<PerfilDeParticipante> tipoPerfil = PerfilDeParticipante.class;
    	Collection<Perfil> perfisDoTipoPerfil = perfis.get(tipoPerfil.getName());
    	Collection<PerfilDeParticipante> perfisDeParticipante = new ArrayList<PerfilDeParticipante>();
    	
    	for (Perfil perfil : perfisDoTipoPerfil) {
			perfisDeParticipante.add((PerfilDeParticipante) perfil);
		}
    	 
    	return perfisDeParticipante;
    }
	
	public Collection<PerfilDeExaminador> obterExaminadores(){
    	Class<PerfilDeExaminador> tipoPerfil = PerfilDeExaminador.class;
    	Collection<Perfil> perfisDoTipoPerfil = perfis.get(tipoPerfil.getName());
    	Collection<PerfilDeExaminador> perfisDeExaminador = new ArrayList<PerfilDeExaminador>();
    	
    	for (Perfil perfil : perfisDoTipoPerfil) {
			perfisDeExaminador.add((PerfilDeExaminador) perfil);
		}
    	 
    	return perfisDeExaminador;
    }
	
	public Collection<PerfilDeChair> obterChairs(){
    	Class<PerfilDeChair> tipoPerfil = PerfilDeChair.class;
    	Collection<Perfil> perfisDoTipoPerfil = perfis.get(tipoPerfil.getName());
    	Collection<PerfilDeChair> perfisDeChair = new ArrayList<PerfilDeChair>();
    	
    	for (Perfil perfil : perfisDoTipoPerfil) {
			perfisDeChair.add((PerfilDeChair) perfil);
		}
    	 
    	return perfisDeChair;
    }
    
    
    /*Referete a trabalho*/
    
    public void subtmeterTrabalho(PerfilDeParticipante submissor, String titulo, String resumo, String autores, String caminhoArquivoSubmissao) throws ExcecaoDeParticipacao{
        Trabalho trabalho = Trabalho.criarTrabalho(submissor, titulo, resumo, autores, caminhoArquivoSubmissao);
        this.trabalhos.add(trabalho);
    }

    public void subtmeterVersaoFinalDeTrabalho(Trabalho trabalho, String caminhoArquivoFinal) throws ExcecaoDeParticipacao{
    	if (obterTrabalhosAceitos().contains(trabalho)) {
        	trabalho.submeterVersaoFinal(caminhoArquivoFinal);	
    	} else {
    		throw new ExcecaoDeParticipacao("evento.submeter_versao_final_de_trabalho.nao_pertence_trabalhos_aceitos");
    	}
    }
    
    public Collection<Trabalho> obterTrabalhosAceitos() {
    	Collection<Trabalho> trabalhosAceitos = new ArrayList<Trabalho>();
    	for (Trabalho trabalho: trabalhos) {
    		if (trabalho.obterEstado() == EstadoAvaliacao.ACEITO)
    			trabalhosAceitos.add(trabalho);
    	}
        return  trabalhosAceitos;
    }

    public Collection<Trabalho> obterTrabalhosRejeitados() {
    	Collection<Trabalho> trabalhosRejeitados = new ArrayList<Trabalho>();
    	for (Trabalho trabalho: trabalhos) {
    		if (trabalho.obterEstado() == EstadoAvaliacao.REJEITADO)
    			trabalhosRejeitados.add(trabalho);
    	}
        return  trabalhosRejeitados;
    }

    public Collection<Trabalho> obterTrabalhosNaoAvaliados() {
    	Collection<Trabalho> trabalhosNaoAvaliados = new ArrayList<Trabalho>();
    	for (Trabalho trabalho: trabalhos) {
    		if (trabalho.obterEstado() == EstadoAvaliacao.NAO_AVALIADO)
    			trabalhosNaoAvaliados.add(trabalho);
    	}
        return  trabalhosNaoAvaliados;
    }
    
    public Collection<Trabalho> obterTrabalhosNaoAssociadosABancaExaminadora(){
    	Collection<Trabalho> trabalhosNaoAssociadosABancaExaminadora = new ArrayList<Trabalho>();
    	for (Trabalho trabalho: trabalhos) {
    		if (!trabalho.associadoABancaExaminadora())
    			trabalhosNaoAssociadosABancaExaminadora.add(trabalho);
    	}
        return  trabalhosNaoAssociadosABancaExaminadora;    	
    }

    public Collection<Trabalho> obterTrabalhosSubmetidos() {
        return trabalhos;
    }
    
    public Collection<Trabalho> obterTodosTrabalhosSubmetidosPeloParticipante(PerfilDeParticipante submissor) throws ExcecaoDeParticipacao{
    	Collection<Trabalho> trabalhosSubmetidos = new ArrayList<Trabalho>();
    	
    	if(submissor.getEvento() != this)
    		throw new ExcecaoDeParticipacao("evento.perfil_nao_referente_ao_evento_em_questao");
    	
    	for (Trabalho trabalho : trabalhos) {
			if(trabalho.getSubmissor() == submissor)
				trabalhosSubmetidos.add(trabalho);
		}
    	
    	return trabalhosSubmetidos;
    }
    
 
    /*Referente ao estado do evento*/
    
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
   


	/*Metodos de validacao de dados*/

    private static void validarDados(String nome, Instituicao instituicao, Usuario usuarioResponsavel, Date dataMaximaParaSubmissaoDeTrabalhos, Date dataMaximaParaAceitacaoDeTrabalhos, Date dataDeInicio, Date dataDeFim) throws ExcecaoDeCadastro{
    	validarNome(nome);
    	validarInstituicao(instituicao);
    	validarUsuarioResponsavel(usuarioResponsavel);
        validarDatas(dataMaximaParaSubmissaoDeTrabalhos, dataMaximaParaAceitacaoDeTrabalhos, dataDeInicio, dataDeFim);   
    }
    
    private static void validarNome(String nome) throws ExcecaoDeCadastro{
        Boolean nomeVazio = (nome == null) || (nome.equals(""));
        
    	if(nomeVazio)
        	throw new ExcecaoDeCadastro("evento.nome.vazio");	
    }
    
    private static void validarInstituicao(Instituicao instituicao) throws ExcecaoDeCadastro{
        Boolean instituicaoVazia = (instituicao == null);
        
    	if(instituicaoVazia)
        	throw new ExcecaoDeCadastro("evento.instituicao.vazia");	
    }
    
    private static void validarUsuarioResponsavel(Usuario usuarioResponsavel) throws ExcecaoDeCadastro{
    	Boolean usuarioResponsavelVazio = (usuarioResponsavel == null);
        
        if(usuarioResponsavelVazio)
        	throw new ExcecaoDeCadastro("evento.usuario_responsavel.vazio");
    }
    

    private static void validarDatas(Date dataMaximaParaSubmissaoDeTrabalhos, Date dataMaximaParaAceitacaoDeTrabalhos, Date dataDeInicio, Date dataDeFim) throws ExcecaoDeCadastro{
        Date dataAtual = new Date();

        if(dataAtual.compareTo(dataDeInicio) > 0)
            throw new ExcecaoDeCadastro("evento.data_de_inicio.invalida");

        if(dataDeInicio.compareTo(dataDeFim) > 0)
        	throw new ExcecaoDeCadastro("evento.data_de_fim.invalida");

        if(dataMaximaParaSubmissaoDeTrabalhos.compareTo(dataMaximaParaAceitacaoDeTrabalhos) > 0)
        	throw new ExcecaoDeCadastro("evento.data_maxima_para_aceitacao_de_trabalhos.invalida");

        if(dataMaximaParaAceitacaoDeTrabalhos.compareTo(dataDeInicio) > 0)
        	throw new ExcecaoDeCadastro("evento.data_maxima_para_submissao_de_trabalhos.invalida");
    }
    
    
    
    /*Metodos de get e set*/

	public String getNome() {
		return nome;
	}
	
	public Instituicao getInstituicao(){
		return instituicao;
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
	
    public void atualizarDados(Instituicao instituicao, String nome, Date dataDeInicio, Date dataDeFim, Date dataMaximaParaSubmissaoDeTrabalhos, Date dataMaximaParaAceitacaoDeTrabalhos) throws ExcecaoDeCadastro{
    	validarDados(nome, instituicao, usuarioResponsavel, dataMaximaParaSubmissaoDeTrabalhos, dataMaximaParaAceitacaoDeTrabalhos, dataDeInicio, dataDeFim);
    	
    	if(this.nome.compareTo(nome) != 0)
    		this.nome = nome;
    	
    	if(this.instituicao.compareTo(instituicao) != 0)
    		this.instituicao = instituicao;
    	
    	if(this.dataDeInicio.compareTo(dataDeInicio) != 0)
    		this.dataDeInicio = dataDeInicio;
    	
    	if(this.dataDeFim.compareTo(dataDeFim) != 0)
    		this.dataDeFim = dataDeFim;
    	
    	if(this.dataMaximaParaSubmissaoDeTrabalhos.compareTo(dataMaximaParaSubmissaoDeTrabalhos) != 0)
    		this.dataMaximaParaSubmissaoDeTrabalhos =dataMaximaParaSubmissaoDeTrabalhos;
    	
    	if(this.dataMaximaParaAceitacaoDeTrabalhos.compareTo(dataMaximaParaAceitacaoDeTrabalhos) != 0)
    		this.dataMaximaParaAceitacaoDeTrabalhos = dataMaximaParaAceitacaoDeTrabalhos;
    }

	/*public void setNome(String nome) throws ExcecaoDeCadastro{
		validarNome(nome);
		this.nome = nome;
	}
	
	public void setInstituicao(Instituicao instituicao) throws ExcecaoDeCadastro{
		validarInstituicao(instituicao);
		this.instituicao = instituicao;
	}
	
	public void setDataDeInicio(Date dataDeInicio) throws ExcecaoDeCadastro{
		validarDatas(this.dataMaximaParaSubmissaoDeTrabalhos, this.dataMaximaParaAceitacaoDeTrabalhos, dataDeInicio, this.dataDeFim);
		this.dataDeInicio = dataDeInicio;  
	}
	
	public void setDataDeFim(Date dataDeFim) throws ExcecaoDeCadastro{
		validarDatas(this.dataMaximaParaSubmissaoDeTrabalhos, this.dataMaximaParaAceitacaoDeTrabalhos, this.dataDeInicio, dataDeFim);
		this.dataDeFim = dataDeFim;  
	}
	
	public void setDataMaximaParaSubmissaoDeTrabalhos(Date dataMaximaParaSubmissaoDeTrabalhos) throws ExcecaoDeCadastro{
		validarDatas(dataMaximaParaSubmissaoDeTrabalhos, this.dataMaximaParaAceitacaoDeTrabalhos, this.dataDeInicio, this.dataDeFim);
		this.dataMaximaParaSubmissaoDeTrabalhos = dataMaximaParaSubmissaoDeTrabalhos;  
	}
	
	public void setDataMaximaParaAceitacaoDeTrabalhos(Date dataMaximaParaAceitacaoDeTrabalhos) throws ExcecaoDeCadastro{
		validarDatas(this.dataMaximaParaSubmissaoDeTrabalhos, dataMaximaParaAceitacaoDeTrabalhos, this.dataDeInicio, this.dataDeFim);
		this.dataMaximaParaAceitacaoDeTrabalhos = dataMaximaParaAceitacaoDeTrabalhos;  
	}*/

}
