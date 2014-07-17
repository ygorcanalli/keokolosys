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
import java.util.Collection;

public class SwingCadastrarBancaExaminadora extends JFrame implements AbstractGUICadastrarBancaExaminadora{

	private static final long serialVersionUID = 2681768088968592067L;
	private JPanel contentPane;
	
	private JButton btnNovaOuCancelar;
	private JButton btnSalvar;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JButton btnSair;
	private JButton btnAdicionar;
	private JButton btnRemover;
	
	private JComboBox<String> comboBoxBancaExaminadora;
	private JComboBox<String> comboBoxExaminadora;
	private JList<String> listExaminadores;
	
	private ControleCadastrarBancaExaminadora controleCadastroBancaExaminadora;
	
	private enum Acao {
		NOVO, SALVAR, EDITAR, EXCLUIR, CANCELAR, ATUALIZAR, SELECIONAR
	}

	private Acao acaoCorrente;

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

	
	public void acaoSelecionar(){
		controleCadastroBancaExaminadora.acaoSelecionar();
	}	
	
	public void acaoNovo(){
		controleCadastroBancaExaminadora.acaoNovo();
	}
	
	public void acaoSalvar(){
		controleCadastroBancaExaminadora.acaoSalvar();
	}
	
	public void acaoEditar(){
		controleCadastroBancaExaminadora.acaoEditar();
	}
	
	public void acaoExcluir(){
		controleCadastroBancaExaminadora.acaoExcluir();
	}
	
	public void acaoCancelar(){
		controleCadastroBancaExaminadora.acaoCancelar();
	}
	
	public void acaoAtualizar(){
		controleCadastroBancaExaminadora.acaoAtualizar();
	}

	public void acaoAdicionarExaminador(){
		controleCadastroBancaExaminadora.acaoAdicionarExaminador();
	}
	
	public void acaoRemoverExaminador(){
		controleCadastroBancaExaminadora.acaoRemoverExaminador();
	}

	@Override
	public void atualizarListaDeBancasExaminadoras(Collection<BancaExaminadoraTO> bancasExaminadoras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void atualizarListaDeExaminadores(Collection<UsuarioTO> examinadores) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BancaExaminadoraTO obterDadosDaBancaExaminadoraPreenchida() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BancaExaminadoraTO obterBancaExaminadoraSelecionada() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void definirSelecaoBancaExaminadora(
			BancaExaminadoraTO bancaExaminadora) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void definirSelecaoExaminador(UsuarioTO examinador) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removerSelecaoBancaExaminadora() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void removerSelecaoExaminador() {
		// TODO Auto-generated method stub
		
	}
	
	private void limparListaDeExaminadores(){
		listExaminadores.clearSelection();
		DefaultListModel<String> listModelExaminadores = new DefaultListModel<String>();
		listExaminadores.setModel(listModelExaminadores);
	}

	@Override
	public void habilitarAcaoAdicionarExaminador() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void desabilitarAcaoAdicionarExaminador() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void limparFormulario() {
		removerSelecaoBancaExaminadora();
		removerSelecaoExaminador();
		limparListaDeExaminadores();
	}

	@Override
	public void exibirBancaExaminadora(BancaExaminadoraTO bancaExaminadora) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void habilitarAcaoSelecionar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void habilitarAcaoNovo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void habilitarAcaoSalvar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void habilitarAcaoEditar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void habilitarAcaoAtualizar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void habilitarAcaoExcluir() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void habilitarAcaoCancelar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void desabilitarAcaoSelecionar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void desabilitarAcaoNovo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void desabilitarAcaoSalvar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void desabilitarAcaoEditar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void desabilitarAcaoAtualizar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void desabilitarAcaoExcluir() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void desabilitarAcaoCancelar() {
		// TODO Auto-generated method stub
		
	}
	
	private void inicializarFrame(){
		setResizable(false);
		setTitle("Cadastro de Banca Examinadora");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 580, 396);
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
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE))
					.addGap(24))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(30, Short.MAX_VALUE))
		);
		
		
		comboBoxBancaExaminadora = new JComboBox<String>();
		comboBoxBancaExaminadora.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(comboBoxBancaExaminadora, 0, 479, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(comboBoxBancaExaminadora, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		
		btnNovaOuCancelar = new JButton("Nova");
		btnNovaOuCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if((acaoCorrente == Acao.EDITAR) || (acaoCorrente == Acao.NOVO))
				{
					acaoCorrente = Acao.CANCELAR;
					acaoCancelar();
				}
				else
				{
					acaoCorrente = Acao.NOVO;
					acaoNovo();
				}
			}
		});
		btnNovaOuCancelar.setMnemonic('N');
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(acaoCorrente == Acao.NOVO)
				{
					acaoCorrente = Acao.SALVAR;
					acaoSalvar();
				}
				else
				{
					acaoCorrente = Acao.ATUALIZAR;
					acaoAtualizar();
				}
			}
		});
		btnSalvar.setMnemonic('S');
		
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acaoCorrente = Acao.EDITAR;
				acaoEditar();
			}
		});
		btnEditar.setMnemonic('E');
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acaoCorrente = Acao.EXCLUIR;
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
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNovaOuCancelar, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnSair, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(52, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNovaOuCancelar)
						.addComponent(btnSalvar)
						.addComponent(btnEditar)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnExcluir)
							.addComponent(btnSair)))
					.addContainerGap(55, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblNomeExaminador = new JLabel("Examinador:");
		
		comboBoxExaminadora = new JComboBox<String>();
		comboBoxExaminadora.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acaoSelecionar();
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
						.addComponent(comboBoxExaminadora, 0, 477, Short.MAX_VALUE)
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
					.addComponent(comboBoxExaminadora, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
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
