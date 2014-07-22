package avaliacao;

import java.util.ArrayList;
import java.util.Collection;

import controladorGRASP.ControladorDeAvaliacao;
import dominio.BancaExaminadora;
import dominio.Evento;
import dominio.Instituicao;
import dominio.PerfilDeExaminador;
import dominio.Trabalho;
import dominio.Usuario;
import excecao.ExcecaoDeAvaliacao;
import transferobject.BancaExaminadoraTO;
import transferobject.InstituicaoTO;
import transferobject.TrabalhoTO;
import transferobject.UsuarioTO;
import util.AbstractControle;


public class ControleAssociarTrabalhoABancaExaminadora implements AbstractControle{

	private AbstractGUIAssociarTrabalhoABancaExaminadora viewAssociarTrabalhoABancaExaminadora;
	private AbstractControle caller;
	private Trabalho trabalho;
	private Evento evento;
	
	public ControleAssociarTrabalhoABancaExaminadora(AbstractControle caller, Evento evento, Trabalho trabalho){
		this.caller = caller;
		this.trabalho = trabalho;
		this.evento = evento;
	}

	@Override
	public void inicializarGUI() {
		this.viewAssociarTrabalhoABancaExaminadora = new SwingAssociarTrabalhoABancaExaminadora(this);
		this.viewAssociarTrabalhoABancaExaminadora.inicializar();
		
		atualizarListaDeBancasExaminadoras();
		definirTrabalho();
		tornarGUIVisivel();
	}
	
	private void definirTrabalho(){
		TrabalhoTO trabalhoTO = new TrabalhoTO();
		trabalhoTO.setAutores(trabalho.getAutores());
		trabalhoTO.setTitulo(trabalho.getTitulo());
		trabalhoTO.setResumo(trabalhoTO.getResumo());
		trabalhoTO.setSubmissor(trabalhoTO.getSubmissor());
	}

	private void atualizarListaDeBancasExaminadoras() {
		viewAssociarTrabalhoABancaExaminadora.atualizarListDeBancasExaminadoras(obterTodasBancasExaminadoras());
	}
	
	private Collection<BancaExaminadoraTO> obterTodasBancasExaminadoras(){
		Collection<BancaExaminadora> bancasExaminadoras = ControladorDeAvaliacao.obterTodasAsBancasExaminadorasDoEvento(this.evento);
		Collection<BancaExaminadoraTO> bancasExaminadorasTO = new ArrayList<BancaExaminadoraTO>();
		
		for (BancaExaminadora bancaExaminadora : bancasExaminadoras) {
			bancasExaminadorasTO.add(converterBancaExaminadoraParaBancaExaminadoraTO(bancaExaminadora));
		}
		
		return bancasExaminadorasTO;
	}
	
	private BancaExaminadoraTO converterBancaExaminadoraParaBancaExaminadoraTO(BancaExaminadora bancaExaminadora){
		BancaExaminadoraTO bancaExaminadoraTO = new BancaExaminadoraTO();
		Collection<UsuarioTO> examinadoresTO = new ArrayList<UsuarioTO>();
		
		for (PerfilDeExaminador examinador : bancaExaminadora.getExaminadores()) {
			examinadoresTO.add(converterPerfilDeExaminadorParaUsuarioTO(examinador));
		}
		
		bancaExaminadoraTO.setExaminadores(examinadoresTO);
		return bancaExaminadoraTO;
	}
	
	private UsuarioTO converterPerfilDeExaminadorParaUsuarioTO(PerfilDeExaminador examinador){
		UsuarioTO examinadorTO;
		Usuario usuarioReferenteAoExaminador;
		
		usuarioReferenteAoExaminador = examinador.getUsuario();
		
		String email = usuarioReferenteAoExaminador.getEmail();
		String nome = usuarioReferenteAoExaminador.getNome();
		String ultimoNome = usuarioReferenteAoExaminador.getUltimoNome();
		
		examinadorTO = new UsuarioTO();
		
		examinadorTO.setEmail(email);
		examinadorTO.setNome(nome);
		examinadorTO.setUltimoNome(ultimoNome);
		examinadorTO.setInstituicao(converterInstituicaoParaInstituicaoTO(usuarioReferenteAoExaminador.getInstituicao()));
		
		return examinadorTO;
	}
	
	private InstituicaoTO converterInstituicaoParaInstituicaoTO(Instituicao instituicao){
		return new InstituicaoTO(instituicao.getNome(), instituicao.getSigla(), instituicao.getLocalizacao());
	}

	@Override
	public void tornarGUIVisivel() {
		this.viewAssociarTrabalhoABancaExaminadora.tornarVisivel();		
	}

	@Override
	public void tornarGUIInvisivel() {
		this.viewAssociarTrabalhoABancaExaminadora.tornarInvisivel();
	}

	@Override
	public void bloquearGUI() {
		this.viewAssociarTrabalhoABancaExaminadora.bloquear();
	}

	@Override
	public void desbloquearGUI() {
		atualizarListaDeBancasExaminadoras();
		this.viewAssociarTrabalhoABancaExaminadora.desbloquear();
	}

	@Override
	public void encerrarGUI() {
		caller.desbloquearGUI();
		tornarGUIInvisivel();
	}

	public void fechar() {
		encerrarGUI();
	}

	public void associarABancaExaminadora() {
		BancaExaminadoraTO bancaExaminadoraTO = viewAssociarTrabalhoABancaExaminadora.obterBancaExaminadoraSelecionada();
		BancaExaminadora bancaExaminadora;
		Collection<PerfilDeExaminador> examinadores;
		
		try {
			examinadores = recuperarExaminadores(bancaExaminadoraTO.getExaminadores());
			bancaExaminadora = ControladorDeAvaliacao.obterBancaExaminadoraPelosExaminadores(this.evento, examinadores);
			bancaExaminadora.associarTrabalho(trabalho);
		
			viewAssociarTrabalhoABancaExaminadora.exibirMensagemDeInformacao("Trabalaho: " + trabalho.getTitulo() + " associado a banca examinadora selecionada com sucesso!", "");
			fechar();
			
		} catch (ExcecaoDeAvaliacao e) {
			viewAssociarTrabalhoABancaExaminadora.exibirMensagemDeAviso(e.getMessage(), "");
		}		
	}

	public void gerenciarBancasExaminadoras() {
		bloquearGUI();
		AbstractControle gerenciarBancasExaminadoras = new ControleCadastrarBancaExaminadora(this, this.evento);
		gerenciarBancasExaminadoras.inicializarGUI();
	}
	
	private Collection<PerfilDeExaminador> recuperarExaminadores(Collection<UsuarioTO> examinadoresTO) throws ExcecaoDeAvaliacao{
		Collection<PerfilDeExaminador> examinadores = new ArrayList<PerfilDeExaminador>();
		PerfilDeExaminador examinador;
		String emailDoExaminador = null;
		
		for (UsuarioTO examinadorTO : examinadoresTO) {
			
			emailDoExaminador = examinadorTO.getEmail();
			
			examinador = ControladorDeAvaliacao.obterExaminadorDoEventoPorEmail(evento, emailDoExaminador);
			examinadores.add(examinador);
		}
		
		return examinadores;
	}

	
}
