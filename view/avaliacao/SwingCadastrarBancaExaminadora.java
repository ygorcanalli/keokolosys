package avaliacao;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import transferobject.BancaExaminadoraTO;
import transferobject.UsuarioTO;
import avaliacao.ControleCadastrarBancaExaminadora;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class SwingCadastrarBancaExaminadora extends JFrame implements AbstractGUICadastrarBancaExaminadora{

	private static final long serialVersionUID = 2681768088968592067L;
	private JPanel contentPane;
	
	private JButton btnNovo;
	private JButton btnSalvar;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JButton btnSair;
	private JButton btnAdicionar;
	private JButton btnRemover;
	private JButton btnAtualizar;
	private JButton btnCancelar;
	
	private JComboBox<String> comboBoxBancasExaminadoras;
	private JComboBox<String> comboBoxExaminadoresNaoPertencentesABancaSelecionada;
	private JList<String> listExaminadores;
	
	private BancaExaminadoraTO bancasExaminadoras[];
	private UsuarioTO examinadoresPertencesABancaSelecionada[];
	private UsuarioTO examinadoresNaoPertencentesABancaSelecionada[];
	
	private ControleCadastrarBancaExaminadora controleCadastroBancaExaminadora;


	public SwingCadastrarBancaExaminadora(ControleCadastrarBancaExaminadora controleCadastrarBancaExaminadora) {
		this.controleCadastroBancaExaminadora = controleCadastrarBancaExaminadora;
		inicializarFrame();
	}
	
	private void fechar() {
		controleCadastroBancaExaminadora.fechar();
	}
	
	@Override
	public void inicializar() {
		inicializarFrame();
	}

	@Override
	public void tornarVisivel(){
		this.setVisible(true);
	}
	
	@Override
	public void tornarInvisivel(){
		this.setVisible(false);
	}
	
	@Override
	public void bloquear(){
		this.setEnabled(false);
	}
	
	@Override
	public void desbloquear(){
		this.setEnabled(true);
	}

	@Override
	public void exibirMensagemDeErro(String mensagem, String titulo){
		JOptionPane.showMessageDialog(this, mensagem, titulo, JOptionPane.ERROR_MESSAGE);
	}
	
	@Override
	public void exibirMensagemDeAviso(String mensagem, String titulo){
		JOptionPane.showMessageDialog(this, mensagem, titulo, JOptionPane.WARNING_MESSAGE);
	}
	
	@Override
	public void exibirMensagemDeInformacao(String mensagem, String titulo){
		JOptionPane.showMessageDialog(this, mensagem, titulo, JOptionPane.INFORMATION_MESSAGE);
	}
	
	@Override
	public Integer exibirMensagemDeConfirmacao(String mensagem, String titulo, Object[] opcoes, Object opcaoPadrao){
		return JOptionPane.showOptionDialog(this, mensagem, titulo, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcaoPadrao);
	}

	private void acaoSelecionarBancaExaminadora(){
		controleCadastroBancaExaminadora.acaoSelecionarBancaExaminadora();
	}
	
	private void acaoSelecionarExaminadorNaoPertencenteABanca(){
		controleCadastroBancaExaminadora.acaoSelecionarExaminadorNaoPertencenteABanca();
	}

	
	private void acaoNovo(){
		controleCadastroBancaExaminadora.acaoNovo();
	}
	
	private void acaoSalvar(){
		controleCadastroBancaExaminadora.acaoSalvar();
	}
	
	private void acaoEditar(){
		controleCadastroBancaExaminadora.acaoEditar();
	}
	
	private void acaoExcluir(){
		controleCadastroBancaExaminadora.acaoExcluir();
	}
	
	private void acaoCancelar(){
		controleCadastroBancaExaminadora.acaoCancelar();
	}
	
	private void acaoAtualizar(){
		controleCadastroBancaExaminadora.acaoAtualizar();
	}

	private void acaoAdicionarExaminador(){
		controleCadastroBancaExaminadora.acaoAdicionarExaminador();
	}
	
	private void acaoRemoverExaminador(){
		controleCadastroBancaExaminadora.acaoRemoverExaminador();
	}

	@Override
	public void atualizarListaDeBancasExaminadoras(Collection<BancaExaminadoraTO> bancasExaminadoras) {
		int i = 0;
		this.bancasExaminadoras = new BancaExaminadoraTO[bancasExaminadoras.size()];
		
		comboBoxBancasExaminadoras.removeAllItems();
		
		for (BancaExaminadoraTO bancaExaminadora : bancasExaminadoras) {
			String examinadorExibicao = "";
			String bancaExaminadoraExibicao = "";
			
			this.bancasExaminadoras[i] = bancaExaminadora; i++;
			
			for (UsuarioTO examinador : bancaExaminadora.getExaminadores()) {
				examinadorExibicao += examinador.getEmail() + "; ";
			}
			
			bancaExaminadoraExibicao += String.valueOf(i) + " - " + examinadorExibicao;
			comboBoxBancasExaminadoras.addItem(bancaExaminadoraExibicao);
		}		
	}

	@Override
	public void atualizarListaDeExaminadoresNaoPertencentesABanca(Collection<UsuarioTO> examinadores) {
		int i = 0;
		this.examinadoresNaoPertencentesABancaSelecionada = new UsuarioTO[examinadores.size()];
		
		comboBoxExaminadoresNaoPertencentesABancaSelecionada.removeAllItems();
		
		String examinadorExibicao = "";
		
		for (UsuarioTO examinador : examinadores) {
			this.examinadoresNaoPertencentesABancaSelecionada[i] = examinador; i++;
			
			examinadorExibicao = examinador.getNome() + " : " + examinador.getEmail() + ";";
			comboBoxExaminadoresNaoPertencentesABancaSelecionada.addItem(examinadorExibicao);
		}
		
		comboBoxExaminadoresNaoPertencentesABancaSelecionada.repaint();
	}
	
	@Override
	public void atualizarListaDeExaminadoresPertencesABanca(Collection<UsuarioTO> examinadores) {
		int i = 0;
		this.examinadoresPertencesABancaSelecionada = new UsuarioTO[examinadores.size()];
		
		String exibicao = "";
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		
		for (UsuarioTO examinador : examinadores) {
			this.examinadoresPertencesABancaSelecionada[i] = examinador;
			
			exibicao = examinador.getNome();
			exibicao += examinador.getUltimoNome();
			exibicao += examinador.getInstituicao().getSigla();
			exibicao += examinador.getEmail();
			
			listModel.add(i, exibicao);
			i++;
		}
		
		listExaminadores.setModel(listModel);
		listExaminadores.setSelectedIndex(0);
		listExaminadores.repaint();
	}
	
	private BancaExaminadoraTO capturarBancaExaminadora(){
		int i = comboBoxBancasExaminadoras.getSelectedIndex();
		
		if(i >= 0)
			return bancasExaminadoras[i];
		else
			return null;
	}

	private UsuarioTO capturarExaminadorNaoPertencente(Integer index){
		int i = index; 
		
		if(i >= 0)
			return examinadoresNaoPertencentesABancaSelecionada[i];
		else
			return null;
	}
	
	private UsuarioTO capturarExaminadorPertencente(Integer index){
		int i = index;
		
		if(i >= 0)
			return examinadoresPertencesABancaSelecionada[i];
		else
			return null;
	}

	@Override
	public BancaExaminadoraTO obterDadosDaBancaExaminadoraPreenchida() {
		BancaExaminadoraTO bancaExaminadoraTO = new BancaExaminadoraTO();
		Collection<UsuarioTO> examinadores = new ArrayList<UsuarioTO>();
		
		for (int i = 0; i < listExaminadores.getModel().getSize(); i++) {
			examinadores.add(capturarExaminadorPertencente(i));
		}
		
		bancaExaminadoraTO.setExaminadores(examinadores);
		
		return bancaExaminadoraTO;
	}

	@Override
	public BancaExaminadoraTO obterBancaExaminadoraSelecionada() {
		return capturarBancaExaminadora();
	}
	
	@Override
	public UsuarioTO obterExaminadorPertencenteSelecionado() {
		return capturarExaminadorPertencente(listExaminadores.getSelectedIndex());
	}

	@Override
	public UsuarioTO obterExaminadorNaoPertencenteSelecionado() {
		return capturarExaminadorNaoPertencente(comboBoxExaminadoresNaoPertencentesABancaSelecionada.getSelectedIndex());
	}

	
	@Override
	public void definirSelecaoBancaExaminadora(BancaExaminadoraTO bancaExaminadora) {
		int i = 0;
		
		while((i < bancasExaminadoras.length) && (controleCadastroBancaExaminadora.compareBancas(bancasExaminadoras[i], bancaExaminadora) != 0))
			i++;
		
		if(i < bancasExaminadoras.length)
			this.comboBoxBancasExaminadoras.setSelectedIndex(i);
		else
			this.comboBoxBancasExaminadoras.setSelectedIndex(-1);
	}

	@Override
	public void removerSelecaoBancaExaminadora() {
		this.comboBoxBancasExaminadoras.setSelectedIndex(-1);
	}
	
	@Override
	public void removerSelecaoExaminadorNaoPertencenteABanca() {
		this.comboBoxExaminadoresNaoPertencentesABancaSelecionada.setSelectedIndex(-1);
	}
	
	private void limparListaDeExaminadores(){
		listExaminadores.clearSelection();
		DefaultListModel<String> listModelExaminadores = new DefaultListModel<String>();
		listExaminadores.setModel(listModelExaminadores);
	}


	@Override
	public void limparFormulario() {
		removerSelecaoBancaExaminadora();
		removerSelecaoExaminadorNaoPertencenteABanca();
		limparListaDeExaminadores();
	}

	@Override
	public void exibirBancaExaminadora(BancaExaminadoraTO bancaExaminadora) {
		atualizarListaDeExaminadoresPertencesABanca(bancaExaminadora.getExaminadores());
	}
	
	@Override
	public void habilitarAcaoSelecionarBanca() {
		this.comboBoxBancasExaminadoras.setEnabled(true);
	}
	
	@Override
	public void habilitarAcaoSelecionarExaminadorNaoPertencente(){
		this.comboBoxExaminadoresNaoPertencentesABancaSelecionada.setEnabled(true);
	}

	@Override
	public void habilitarAcaoNovo() {
		this.btnNovo.setEnabled(true);
	}

	@Override
	public void habilitarAcaoSalvar() {
		this.btnSalvar.setEnabled(true);
	}

	@Override
	public void habilitarAcaoEditar() {
		this.btnEditar.setEnabled(true);
	}

	@Override
	public void habilitarAcaoAtualizar() {
		this.btnAtualizar.setEnabled(true);
	}

	@Override
	public void habilitarAcaoExcluir() {
		this.btnExcluir.setEnabled(true);
	}

	@Override
	public void habilitarAcaoCancelar() {
		this.btnCancelar.setEnabled(true);
	}

	@Override
	public void desabilitarAcaoSelecionarBanca() {
		this.comboBoxBancasExaminadoras.setEnabled(false);
	}
	
	@Override
	public void desabilitarAcaoSelecionarExaminadorNaoPertencente() {
		this.comboBoxExaminadoresNaoPertencentesABancaSelecionada.setEnabled(false);
	}

	@Override
	public void desabilitarAcaoNovo() {
		this.btnNovo.setEnabled(false);
	}

	@Override
	public void desabilitarAcaoSalvar() {
		this.btnSalvar.setEnabled(false);
	}

	@Override
	public void desabilitarAcaoEditar() {
		this.btnEditar.setEnabled(false);
	}

	@Override
	public void desabilitarAcaoAtualizar() {
		this.btnAtualizar.setEnabled(false);
	}

	@Override
	public void desabilitarAcaoExcluir() {
		this.btnExcluir.setEnabled(false);
	}

	@Override
	public void desabilitarAcaoCancelar() {
		this.btnCancelar.setEnabled(false);
	}
	
	@Override
	public void habilitarAcaoAdicionarExaminador() {
		this.btnAdicionar.setEnabled(true);
	}

	@Override
	public void desabilitarAcaoAdicionarExaminador() {
		this.btnAdicionar.setEnabled(false);
	}
	
	@Override
	public void habilitarAcaoRemoverExaminador() {
		this.btnRemover.setEnabled(true);
	}

	@Override
	public void desabilitarAcaoRemoverExaminador() {
		this.btnRemover.setEnabled(false);
	}
	
	private void inicializarFrame(){
		setResizable(false);
		setTitle("Cadastro de Banca Examinadora");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 580, 426);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Banca examinadora", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
						.addComponent(panel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE))
					.addGap(24))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		
		comboBoxBancasExaminadoras = new JComboBox<String>();
		comboBoxBancasExaminadoras.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(comboBoxBancasExaminadoras.getSelectedItem() != null)
					comboBoxBancasExaminadoras.setToolTipText(comboBoxBancasExaminadoras.getSelectedItem().toString());
			}
		});
		comboBoxBancasExaminadoras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxBancasExaminadoras.getSelectedIndex() >= 0)
					acaoSelecionarBancaExaminadora();
			}
		});
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(comboBoxBancasExaminadoras, 0, 479, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(comboBoxBancasExaminadoras, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		
		btnNovo = new JButton("Nova");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acaoNovo();
			}
		});
		btnNovo.setMnemonic('N');
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acaoSalvar();
			}
		});
		btnSalvar.setMnemonic('S');
		
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acaoEditar();
			}
		});
		btnEditar.setMnemonic('E');
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acaoExcluir();	
			}
		});
		btnExcluir.setMnemonic('X');
		
		btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechar();
			}
		});
		btnSair.setMnemonic('r');
		
		btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acaoAtualizar();
			}
		});
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acaoCancelar();
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNovo, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnAtualizar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnSalvar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnCancelar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnEditar, GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnSair, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(40, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNovo)
						.addComponent(btnSalvar)
						.addComponent(btnEditar)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnExcluir)
							.addComponent(btnSair)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAtualizar)
						.addComponent(btnCancelar))
					.addContainerGap(20, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblNomeExaminador = new JLabel("Examinador:");
		
		comboBoxExaminadoresNaoPertencentesABancaSelecionada = new JComboBox<String>();
		comboBoxExaminadoresNaoPertencentesABancaSelecionada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acaoSelecionarExaminadorNaoPertencenteABanca();
			}
		});
		
		btnAdicionar = new JButton("Adicionar >>");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acaoAdicionarExaminador();
			}
		});
		btnAdicionar.setMnemonic('A');
		
		listExaminadores = new JList<String>();
		
		btnRemover = new JButton("Remover <<");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acaoRemoverExaminador();
			}
		});
		btnRemover.setMnemonic('R');
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNomeExaminador)
						.addComponent(comboBoxExaminadoresNaoPertencentesABancaSelecionada, 0, 477, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnAdicionar)
								.addComponent(btnRemover))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(listExaminadores, GroupLayout.PREFERRED_SIZE, 382, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNomeExaminador)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBoxExaminadoresNaoPertencentesABancaSelecionada, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnAdicionar)
							.addGap(8)
							.addComponent(btnRemover))
						.addComponent(listExaminadores, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				fechar();
			}
		});
	}
}
