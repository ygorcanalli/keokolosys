package administrativo;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;

import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import transferobject.AdministradorTO;
import transferobject.EventoTO;
import transferobject.InstituicaoTO;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JTabbedPane;
import javax.swing.JTable;

public class SwingAdministradorHome extends JFrame implements AbstractGUIAdministradorHome
{
	private static final long serialVersionUID = 7379748951410562202L;
	private JPanel contentPane,panelConteudo;
	
	private ControleAdministradorHome controleAdministradorHome;
	
	private JComboBox<String> comboBoxInstituicao;
	
	private JTextField textFieldNomeInstituicao;
	private JTextField textFieldLocalizacao;
	private JTextField textFieldSigla;
	
	private JButton btnNovo;
	private JButton btnSalvar;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JButton btnSair;
	
	private JButton btnAtualizar;
	private JButton btnCancelar; 
	
	private InstituicaoTO[] instituicoes;
	private EventoTO[] eventosAguardandoAprovacao;
	private EventoTO[] eventosDeferidos;
	private JTabbedPane tabbedPane;
	private JPanel defirirIndefirirEventosPanel;
	private JPanel panel_3;
	private JTable tableDeferirIndeferir;
	private JTable tableEventosDeferidos;
	private JButton btnIndeferirEvento;
	private JButton btnDeferirEvento;
	private DefaultTableModel defaultTableModel1;
	private DefaultTableModel defaultTableModel2;
	private JButton btnCancelarEvento;
	private JPanel panel_4;
	private JButton btnDeslogar;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JPanel panel_6;
	private JTextField textFieldEmailNovoAdministrador;
	private JLabel lblSenha;
	private JPasswordField passwordFieldSenhaNovoAdministrador;
	private JPasswordField passwordFieldConfirmacaoSenhaNovoAdministrador;
	
	
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
	public void atualizarListaDeEventosParaDeferir(Collection<EventoTO> eventos) {
		int i = 0;
		this.eventosAguardandoAprovacao = new EventoTO[eventos.size()];
		
		int rowCount = defaultTableModel1.getRowCount();
		for(int j = 0; j < rowCount; j++)
			this.defaultTableModel1.removeRow(0);
		
		this.tableDeferirIndeferir.repaint();
		
		for(EventoTO evento : eventos)
		{
			this.eventosAguardandoAprovacao[i] = evento;i++;
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			defaultTableModel1.addRow(new Object[] {evento.getNome(), evento.getQuantidadeDeInscritos(),df.format(evento.getDataDeInicio())});
		}
	}
	
	@Override
	public void atualizarListaDeEventosParaCancelar(Collection<EventoTO> eventos) {
		int i = 0;
		this.eventosDeferidos = new EventoTO[eventos.size()];
		
		int rowCount = defaultTableModel2.getRowCount();
		for(int j = 0; j < rowCount; j++)
			this.defaultTableModel2.removeRow(0);
		
		this.tableEventosDeferidos.repaint();
		
		for(EventoTO evento : eventos)
		{
			this.eventosDeferidos[i] = evento;i++;
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			defaultTableModel2.addRow(new Object[] {evento.getNome(), evento.getQuantidadeDeInscritos(),df.format(evento.getDataDeInicio())});
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
	public EventoTO obterEventoAguardandoAvaliacaoSelecionado() {
		return capturarEventoAguardandoAprovacao();
	}
	
	@Override
	public EventoTO obterEventoDeferidoSelecionado() {
		return capturarEventoDeferido();
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
	
	private EventoTO capturarEventoAguardandoAprovacao(){
		int i = tableDeferirIndeferir.getSelectedRow();
		
		if(i >= 0)
			return eventosAguardandoAprovacao[i];
		else
			return null;
	}
	
	private EventoTO capturarEventoDeferido(){
		int i = tableEventosDeferidos.getSelectedRow();
		
		if(i >= 0)
			return eventosDeferidos[i];
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
	
	private void acaoDeferirEvento()
	{
		controleAdministradorHome.acaoDeferirEvento();
	}
	
	private void acaoIndeferirEvento()
	{
		controleAdministradorHome.acaoIndeferirEvento();
	}
	
	private void acaoCancelarEvento()
	{
		controleAdministradorHome.acaoCancelarEvento();
	}
	
	private void acaoDeslogar()
	{
		controleAdministradorHome.acaoDeslogar();
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
		
		while((i < instituicoes.length) && (controleAdministradorHome.compareInstituicoes(instituicoes[i], instituicao) != 0))
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
		this.btnNovo.setEnabled(true);
	}
	
	@Override
	public void habilitarAcaoSalvarInstituicao(){
		this.btnSalvar.setEnabled(true);
	}
	
	@Override
	public void habilitarAcaoAtualizarInstituicao(){
		this.btnAtualizar.setEnabled(true);
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
		this.btnCancelar.setEnabled(true);
	}	
	
	@Override
	public void desabilitarAcaoSelecionarInstituicao(){
		this.comboBoxInstituicao.setEnabled(true);
	}
	
	@Override
	public void desabilitarAcaoNovoInstituicao(){
		this.btnNovo.setEnabled(false);
	}
	
	@Override
	public void desabilitarAcaoSalvarInstituicao(){
		this.btnSalvar.setEnabled(false);
	}
	
	@Override
	public void desabilitarAcaoAtualizarInstituicao(){
		this.btnAtualizar.setEnabled(false);
	}
	
	@Override
	public void desabilitarAcaoCancelarInstituicao(){
		this.btnCancelar.setEnabled(false);
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
	
	@Override
	public AdministradorTO obterDadosDoNovoAdministrador(){
		AdministradorTO administradorTO = new AdministradorTO();
		String email = textFieldEmailNovoAdministrador.getText();
		char[] _senha = passwordFieldSenhaNovoAdministrador.getPassword();
		String senha = new String(_senha);
		
		administradorTO.setSenha(senha);
		administradorTO.setLogin(email);
		
		return administradorTO;
	}
	
	@Override
	public String obterConfirmacaoDeSenhaNovoAdministrador(){
		char[] _confirmacaoSenha = passwordFieldConfirmacaoSenhaNovoAdministrador.getPassword();
		String confirmacaoSenha = new String(_confirmacaoSenha);
		
		return confirmacaoSenha;
	}
	
	private void novoAdministrador(){
		controleAdministradorHome.novoAdministrador();
	}

	/**
	 * Create the frame.
	 */
	private void inicializarFrame() {
		setTitle("Home de Adminstracao");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 575, 424);
		panelConteudo = new JPanel();
		
		setContentPane(panelConteudo);
		panelConteudo.setLayout(new GridLayout(0, 1, 0, 0));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panelConteudo.add(tabbedPane);
		
		panel_4 = new JPanel();
		tabbedPane.addTab("Perfil", null, panel_4, null);
		panel_4.setLayout(null);
		
		btnDeslogar = new JButton("Deslogar");
		btnDeslogar.setBounds(421, 26, 123, 23);
		panel_4.add(btnDeslogar);
		
		btnDeslogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acaoDeslogar();
			}
		});
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(null, "Atualizar senha", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setBounds(28, 53, 313, 245);
		panel_4.add(panel_5);
		
		JLabel label_3 = new JLabel("Senha antiga:");
		
		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		
		JLabel label_4 = new JLabel("Senha nova:");
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setColumns(10);
		
		JButton button = new JButton("Alterar Senha");
		GroupLayout gl_panel_5 = new GroupLayout(panel_5);
		gl_panel_5.setHorizontalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addGap(30)
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_5.createSequentialGroup()
							.addGap(2)
							.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE))
						.addComponent(passwordField_1, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(114, Short.MAX_VALUE))
		);
		gl_panel_5.setVerticalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_5.createSequentialGroup()
					.addContainerGap(29, Short.MAX_VALUE)
					.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addGap(22)
					.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(passwordField_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(button, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addGap(23))
		);
		panel_5.setLayout(gl_panel_5);
		
		panel_6 = new JPanel();
		tabbedPane.addTab("Cadastrar novo administrador", null, panel_6, null);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(null, "Dados do novo administrador", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_panel_6 = new GroupLayout(panel_6);
		gl_panel_6.setHorizontalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_7, GroupLayout.PREFERRED_SIZE, 519, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(39, Short.MAX_VALUE))
		);
		gl_panel_6.setVerticalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_7, GroupLayout.PREFERRED_SIZE, 268, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(71, Short.MAX_VALUE))
		);
		
		JLabel lblEmail = new JLabel("Email:");
		
		textFieldEmailNovoAdministrador = new JTextField();
		textFieldEmailNovoAdministrador.setColumns(10);
		
		lblSenha = new JLabel("Senha:");
		
		passwordFieldSenhaNovoAdministrador = new JPasswordField();
		
		JLabel lblConfirmaoDeSenha = new JLabel("Confirmação de senha:");
		
		passwordFieldConfirmacaoSenhaNovoAdministrador = new JPasswordField();
		
		JButton btnAdicionarAdministrador = new JButton("Adicionar administrador");
		btnAdicionarAdministrador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				novoAdministrador();
			}
		});
		GroupLayout gl_panel_7 = new GroupLayout(panel_7);
		gl_panel_7.setHorizontalGroup(
			gl_panel_7.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_7.createSequentialGroup()
					.addGap(24)
					.addGroup(gl_panel_7.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAdicionarAdministrador)
						.addComponent(passwordFieldConfirmacaoSenhaNovoAdministrador, GroupLayout.PREFERRED_SIZE, 446, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblConfirmaoDeSenha)
						.addComponent(passwordFieldSenhaNovoAdministrador, GroupLayout.PREFERRED_SIZE, 443, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSenha)
						.addComponent(textFieldEmailNovoAdministrador, GroupLayout.PREFERRED_SIZE, 441, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEmail))
					.addContainerGap(39, Short.MAX_VALUE))
		);
		gl_panel_7.setVerticalGroup(
			gl_panel_7.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_7.createSequentialGroup()
					.addGap(20)
					.addComponent(lblEmail)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textFieldEmailNovoAdministrador, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblSenha)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(passwordFieldSenhaNovoAdministrador, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblConfirmaoDeSenha)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(passwordFieldConfirmacaoSenhaNovoAdministrador, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnAdicionarAdministrador)
					.addContainerGap(28, Short.MAX_VALUE))
		);
		panel_7.setLayout(gl_panel_7);
		panel_6.setLayout(gl_panel_6);
		
		defirirIndefirirEventosPanel = new JPanel();
		tabbedPane.addTab("Deferir/Inderir eventos", null, defirirIndefirirEventosPanel, null);
		defirirIndefirirEventosPanel.setLayout(null);
		
		defaultTableModel1 = new DefaultTableModel(new Object[][] {},new String[] {"Nome do Evento", "Quantidade de Inscritos", "Data de Inicio"})
		{
		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column)
		    {
		      return false;
		    }
		 };
		tableDeferirIndeferir = new JTable(defaultTableModel1);
		tableDeferirIndeferir.setBounds(10, 40, 534, 257);
		tableDeferirIndeferir.getColumnModel().getColumn(1).setMaxWidth(50);
		defirirIndefirirEventosPanel.add(tableDeferirIndeferir);
		
		btnIndeferirEvento = new JButton("Indeferir Evento");
		btnIndeferirEvento.setBounds(413, 323, 131, 23);
		defirirIndefirirEventosPanel.add(btnIndeferirEvento);
		
		btnDeferirEvento = new JButton("Deferir Evento");
		btnDeferirEvento.setBounds(266, 323, 138, 23);
		defirirIndefirirEventosPanel.add(btnDeferirEvento);
		
		btnDeferirEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acaoDeferirEvento();
			}
		});
		
		btnIndeferirEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acaoIndeferirEvento();
			}
		});
		
		JLabel lblNomeDoEvento = new JLabel("Nome do Evento");
		lblNomeDoEvento.setBounds(10, 11, 131, 14);
		defirirIndefirirEventosPanel.add(lblNomeDoEvento);
		
		JLabel lblQuantidadeDeInscritos = new JLabel("Quantidade de Inscritos");
		lblQuantidadeDeInscritos.setBounds(212, 11, 138, 14);
		defirirIndefirirEventosPanel.add(lblQuantidadeDeInscritos);
		
		JLabel lblDataDeInicio = new JLabel("Data de Inicio");
		lblDataDeInicio.setBounds(404, 11, 103, 14);
		defirirIndefirirEventosPanel.add(lblDataDeInicio);
		
		defaultTableModel2 = new DefaultTableModel(new Object[][] {},new String[] {"Nome do Evento", "Quantidade de Inscritos", "Data de Inicio"})
		{
		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column)
		    {
		      return false;
		    }
		 };
		contentPane = new JPanel();
		tabbedPane.addTab("Cadastrar Instituicao", null, contentPane, null);
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel lblNome = new JLabel("Nome:");
		
		this.textFieldNomeInstituicao = new JTextField();
		this.textFieldNomeInstituicao.setColumns(10);
		
		JLabel lblLocalizacao = new JLabel("Localizacao:");
		
		this.textFieldLocalizacao = new JTextField();
		this.textFieldLocalizacao.setColumns(10);
		
		JLabel lblSigla = new JLabel("Sigla:");
		
		this.textFieldSigla = new JTextField();
		this.textFieldSigla.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		this.btnNovo = new JButton("Nova");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				acaoNovoInstituicao();
			}
		});
		this.btnNovo.setMnemonic('N');
		
		this.btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				acaoSalvarInstituicao();					
			}
		});
		
		this.btnSalvar.setMnemonic('r');
		
		this.btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				acaoEditarInstituicao();
			}
		});
		this.btnEditar.setMnemonic('E');
		
		this.btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
		
		btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acaoAtualizarInstituicao();
			}
		});
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acaoCancelarInstituicao();
			}
		});
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNovo, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnAtualizar, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addComponent(btnSalvar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnCancelar, 0, 0, Short.MAX_VALUE)
						.addComponent(btnEditar, GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSair, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(44, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNovo)
						.addComponent(btnSalvar)
						.addComponent(btnEditar)
						.addComponent(btnExcluir)
						.addComponent(btnSair))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAtualizar)
						.addComponent(btnCancelar))
					.addContainerGap(30, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 542, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldNomeInstituicao, GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE)
						.addComponent(lblNome)
						.addComponent(lblLocalizacao)
						.addComponent(textFieldLocalizacao, GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE)
						.addComponent(lblSigla)
						.addComponent(textFieldSigla, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNome)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textFieldNomeInstituicao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblLocalizacao)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textFieldLocalizacao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblSigla)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textFieldSigla, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
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
		contentPane.add(panel, BorderLayout.NORTH);
		
		panel_3 = new JPanel();
		tabbedPane.addTab("Cancelar Evento", null, panel_3, null);
		panel_3.setLayout(null);
		tableEventosDeferidos = new JTable(defaultTableModel2);
		tableEventosDeferidos.setBounds(10, 41, 534, 257);
		tableEventosDeferidos.getColumnModel().getColumn(1).setMaxWidth(50);
		panel_3.add(tableEventosDeferidos);
		
		JLabel label = new JLabel("Nome do Evento");
		label.setBounds(20, 11, 131, 14);
		panel_3.add(label);
		
		JLabel label_1 = new JLabel("Quantidade de Inscritos");
		label_1.setBounds(222, 11, 138, 14);
		panel_3.add(label_1);
		
		JLabel label_2 = new JLabel("Data de Inicio");
		label_2.setBounds(414, 11, 103, 14);
		panel_3.add(label_2);
		
		btnCancelarEvento = new JButton("Cancelar Evento");
		btnCancelarEvento.setBounds(384, 323, 160, 23);
		panel_3.add(btnCancelarEvento);
		
		btnCancelarEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acaoCancelarEvento();
			}
		});
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				fechar();
			}
		});
	}
}
