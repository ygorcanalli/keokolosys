package administrativo;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collection;

import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import transferobject.InstituicaoTO;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JTabbedPane;

public class SwingAdministradorHome extends JFrame implements AbstractGUIAdministradorHome
{
	


	private static final long serialVersionUID = 7379748951410562202L;
	private JPanel contentPane,panelConteudo;
	
	private ControleAdministradorHome controleAdministradorHome;
	
	private JComboBox<String> comboBoxInstituicao;
	
	private JTextField textFieldNomeInstituicao;
	private JTextField textFieldLocalizacao;
	private JTextField textFieldSigla;
	
	private JButton btnNovaOuCancelar;
	private JButton btnSalvar;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JButton btnSair;

	private Acao acaoCorrente = null; 
	
	private InstituicaoTO[] instituicoes;
	private JTabbedPane tabbedPane;
	private JPanel defirirIndefirirEventosPanel;
	private JPanel panel_3;
	
	private enum Acao {
		NOVO, SALVAR, EDITAR, EXCLUIR, CANCELAR, ATUALIZAR, SELECIONAR
	}
	
	
	@Override
	public void atualizarListaDeInstituicoes(Collection<InstituicaoTO> instituicoes) {
		int i = 0;
		this.instituicoes = new InstituicaoTO[instituicoes.size()];	
		String instituicaoExibicao = "";
		
		this.comboBoxInstituicao.removeAllItems();
		this.comboBoxInstituicao.repaint();
		
		for (InstituicaoTO instituicao : instituicoes) {
			this.instituicoes[i] = instituicao; i++;
			
			instituicaoExibicao = String.valueOf(i) + " - " + instituicao.getSigla() + " : " + instituicao.getNome();
			this.comboBoxInstituicao.addItem(instituicaoExibicao);
		}		
	}

	@Override
	public InstituicaoTO obterDadosDaInstituicaoPreenchida(){
		String nomeDaInstituicao = textFieldNomeInstituicao.getText();
		String localizacao = textFieldLocalizacao.getText();
		String sigla = textFieldSigla.getText();
		
		return new InstituicaoTO(nomeDaInstituicao, sigla, localizacao);
	}
	
	
	@Override
	public InstituicaoTO obterInstituicaoSelecionada(){
		return capturarInstituicao();
	}
	
	@Override
	public void exibirInstituicao(InstituicaoTO instituicao) {
		this.textFieldNomeInstituicao.setText(instituicao.getNome());
		this.textFieldLocalizacao.setText(instituicao.getSigla());
		this.textFieldSigla.setText(instituicao.getSigla());
	}
	
	
	private InstituicaoTO capturarInstituicao(){
		int i = comboBoxInstituicao.getSelectedIndex();
		
		if(i >= 0)
			return instituicoes[i];
		else
			return null;
	}
	
	private void acaoSelecionarInstituicao(){
		controleAdministradorHome.acaoSelecionarInstituicao();
	}	
	
	private void acaoNovoInstituicao(){
		controleAdministradorHome.acaoNovoInstituicao();
	}
	
	private void acaoSalvarInstituicao(){
		controleAdministradorHome.acaoSalvarInstituicao();
	}
	
	private void acaoEditarInstituicao(){
		controleAdministradorHome.acaoEditarInstituicao();
	}
	
	private void acaoExcluirInstituicao(){
		controleAdministradorHome.acaoExcluirInstituicao();
	}
	
	private void acaoCancelarInstituicao(){
		controleAdministradorHome.acaoCancelarInstituicao();
	}
	
	private void acaoAtualizarInstituicao(){
		controleAdministradorHome.acaoAtualizarInstituicao();
	}
	
	@Override
	public void fechar(){
		controleAdministradorHome.fechar();
	}
	
	public SwingAdministradorHome(ControleAdministradorHome controleAdministradorHome)
	{		
		inicializarFrame();
		this.controleAdministradorHome = controleAdministradorHome;
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
	
	@Override
	public void limparFormularioInstituicao() {
		this.textFieldNomeInstituicao.setText("");
		this.textFieldSigla.setText("");
		this.textFieldLocalizacao.setText("");
	}
	
	@Override
	public void removerSelecaoInstituicao(){
		this.comboBoxInstituicao.setSelectedIndex(-1);
	}
	
	@Override
	public void definirSelecaoInstituicao(InstituicaoTO instituicao){
		int i = 0;
		
		while((i < instituicoes.length) && (instituicoes[i].getSigla().compareTo(instituicao.getSigla()) != 0))
			i++;
		
		if(i < instituicoes.length)
			this.comboBoxInstituicao.setSelectedIndex(i);
		else
			this.comboBoxInstituicao.setSelectedIndex(-1);
	}


	
	@Override
	public void habilitarAcaoSelecionarInstituicao(){
		this.comboBoxInstituicao.setEnabled(true);
	}
		
	@Override
	public void habilitarAcaoNovoInstituicao(){
		this.btnNovaOuCancelar.setText("Nova");
		this.btnNovaOuCancelar.setEnabled(true);
	}
	
	@Override
	public void habilitarAcaoSalvarInstituicao(){
		this.btnSalvar.setText("Salvar");
		this.btnSalvar.setEnabled(true);
	}
	
	@Override
	public void habilitarAcaoAtualizarInstituicao(){
		this.btnSalvar.setText("Atualizar");
		this.btnSalvar.setEnabled(true);
	}
	
	@Override
	public void habilitarAcaoEditarInstituicao(){
		this.btnEditar.setEnabled(true);
	}
	
	@Override
	public void habilitarAcaoExcluirInstituicao(){
		this.btnExcluir.setEnabled(true);
	}
	
	@Override
	public void habilitarAcaoCancelarInstituicao(){
		this.btnNovaOuCancelar.setText("Cancelar");
		this.btnNovaOuCancelar.setEnabled(true);		
	}	
	
	@Override
	public void desabilitarAcaoSelecionarInstituicao(){
		this.comboBoxInstituicao.setEnabled(true);
	}
	
	@Override
	public void desabilitarAcaoNovoInstituicao(){
		this.btnNovaOuCancelar.setEnabled(false);
	}
	
	@Override
	public void desabilitarAcaoSalvarInstituicao(){
		this.btnSalvar.setEnabled(false);
	}
	
	@Override
	public void desabilitarAcaoAtualizarInstituicao(){
		
	}
	
	@Override
	public void desabilitarAcaoCancelarInstituicao(){

	}	
	
	@Override
	public void desabilitarAcaoEditarInstituicao(){
		this.btnEditar.setEnabled(false);
	}
	
	@Override
	public void desabilitarAcaoExcluirInstituicao(){
		this.btnExcluir.setEnabled(false);
	}
	
	
	@Override
	public void tornarVisivel() {
		this.setVisible(true);
	}

	@Override
	public void tornarInvisivel() {
		this.setVisible(false);
	}

	@Override
	public void bloquear() {
		this.setEnabled(true);
	}

	@Override
	public void desbloquear() {
		this.setEnabled(false);
	}

	@Override
	public void inicializar() {
		inicializarFrame();
	}
	

	/**
	 * Create the frame.
	 */
	private void inicializarFrame() {
		setTitle("Home de Adminstracao");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 557, 395);
		panelConteudo = new JPanel();
		
		setContentPane(panelConteudo);
		panelConteudo.setLayout(new GridLayout(0, 1, 0, 0));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panelConteudo.add(tabbedPane);
		contentPane = new JPanel();
		tabbedPane.addTab("Cadastrar Instituicao", null, contentPane, null);
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		this.btnNovaOuCancelar = new JButton("Nova");
		btnNovaOuCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if((acaoCorrente == Acao.EDITAR) || (acaoCorrente == Acao.NOVO))
				{
					acaoCorrente = Acao.CANCELAR;
					acaoCancelarInstituicao();
				}
				else
				{
					acaoCorrente = Acao.NOVO;
					acaoNovoInstituicao();
				}
			}
		});
		this.btnNovaOuCancelar.setMnemonic('N');
		
		this.btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(acaoCorrente == Acao.NOVO)
				{
					acaoCorrente = Acao.SALVAR;
					acaoSalvarInstituicao();
				}
				else
				{
					acaoCorrente = Acao.ATUALIZAR;
					acaoAtualizarInstituicao();
				}
			}
		});
		
		this.btnSalvar.setMnemonic('r');
		
		this.btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				acaoCorrente = Acao.EDITAR;
				acaoEditarInstituicao();
			}
		});
		this.btnEditar.setMnemonic('E');
		
		this.btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				acaoCorrente = Acao.EXCLUIR;
				acaoExcluirInstituicao();			
			}
		});
		this.btnExcluir.setMnemonic('x');
		
		this.btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fechar();
			}
		});
		this.btnSair.setMnemonic('r');
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(this.btnNovaOuCancelar, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(this.btnSalvar, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.btnEditar, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.btnExcluir, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.btnSair, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(21, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
					.addContainerGap(25, Short.MAX_VALUE)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.btnNovaOuCancelar)
						.addComponent(this.btnSalvar)
						.addComponent(this.btnEditar)
						.addComponent(this.btnExcluir)
						.addComponent(this.btnSair))
					.addContainerGap())
		);
		panel_2.setLayout(gl_panel_2);
		
		JLabel lblNome = new JLabel("Nome:");
		
		this.textFieldNomeInstituicao = new JTextField();
		this.textFieldNomeInstituicao.setColumns(10);
		
		JLabel lblLocalizacao = new JLabel("Localizacao:");
		
		this.textFieldLocalizacao = new JTextField();
		this.textFieldLocalizacao.setColumns(10);
		
		JLabel lblSigla = new JLabel("Sigla:");
		
		this.textFieldSigla = new JTextField();
		this.textFieldSigla.setColumns(10);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(this.textFieldNomeInstituicao, GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
						.addComponent(lblNome)
						.addComponent(lblLocalizacao)
						.addComponent(this.textFieldLocalizacao, GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
						.addComponent(lblSigla)
						.addComponent(this.textFieldSigla, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNome)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.textFieldNomeInstituicao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblLocalizacao)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(this.textFieldLocalizacao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblSigla)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.textFieldSigla, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(12, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		comboBoxInstituicao = new JComboBox<String>();
		comboBoxInstituicao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxInstituicao.getSelectedIndex() >= 0)
					acaoSelecionarInstituicao();
			}
		});
		
		JLabel lblInstituicao = new JLabel("Instituicao:");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(this.comboBoxInstituicao, 0, 488, Short.MAX_VALUE)
						.addComponent(lblInstituicao))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblInstituicao)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(this.comboBoxInstituicao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(panel_1, BorderLayout.CENTER);
		contentPane.add(panel_2, BorderLayout.SOUTH);
		contentPane.add(panel, BorderLayout.NORTH);
		
		defirirIndefirirEventosPanel = new JPanel();
		tabbedPane.addTab("Deferir/Inderir eventos", null, defirirIndefirirEventosPanel, null);
		
		panel_3 = new JPanel();
		tabbedPane.addTab("Cancelar Evento", null, panel_3, null);
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				fechar();
			}
		});
	}
}
